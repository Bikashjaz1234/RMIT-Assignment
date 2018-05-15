# myTeam.py
# ---------
# Licensing Information: Please do not distribute or publish solutions to this
# project. You are free to use and extend these projects for educational
# purposes. The Pacman AI projects were developed at UC Berkeley, primarily by
# John DeNero (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# For more info, see http://inst.eecs.berkeley.edu/~cs188/sp09/pacman.html

from captureAgents import CaptureAgent
import random, time, util
from util import nearestPoint
from game import Directions
import game


#################
# Team creation #
#################

def createTeam(firstIndex, secondIndex, isRed,
               first='OffensiveAgent', second='DefensiveReflexAgent'):
    """
  This function should return a list of two agents that will form the
  team, initialized using firstIndex and secondIndex as their agent
  index numbers.  isRed is True if the red team is being created, and
  will be False if the blue team is being created.
  As a potentially helpful development aid, this function can take
  additional string-valued keyword arguments ("first" and "second" are
  such arguments in the case of this function), which will come from
  the --redOpts and --blueOpts command-line arguments to capture.py.
  For the nightly contest, however, your team will be created without
  any extra arguments, so you should make sure that the default
  behavior is what you want for the nightly contest.
  """

    # The following line is an example only; feel free to change it.
    return [eval(first)(firstIndex), eval(second)(secondIndex)]


##########
# Agents #
##########

class SmartAgent(CaptureAgent):

    def registerInitialState(self, gameState):
      CaptureAgent.registerInitialState(self, gameState)
      self.search_deep = 3
      self.boundary = []
      if self.red:
          b = (gameState.data.layout.width - 2) >>1
      else:
          b = ((gameState.data.layout.width - 2) >>1 )|1

      self.boundary = [(b, i) for i in range(1, gameState.data.layout.height-1) if not gameState.hasWall(b, i)]

      # self.legalPositions = gameState.data.layout.walls.asList(False)

    def chooseAction(self, gameState):
        actions = gameState.getLegalActions(self.index)
        values = [self.evaluate(gameState, a) for a in actions]
        maxVal = max(values)
        ans = [action for action, value in zip(actions, values) if value == maxVal]

        return random.choice(ans)

    def getSuccessor(self, gameState, action):

        successor = gameState.generateSuccessor(self.index, action)

        pos = successor.getAgentState(self.index).getPosition()
        if pos != nearestPoint(pos):
            return successor.generateSuccessor(self.index, action)
        else:
            return successor

    def evaluate(self, gameState, action):

        return self.getFeatures(gameState, action) * self.getWeights(gameState, action)

    def getFeatures(self, gameState, action):

        features = util.Counter()
        successor = self.getSuccessor(gameState, action)
        features['successorScore'] = self.agent.getScore(successor)
        return features

    def getWeights(self, gameState, action):

        return {'successorScore': 1.0}

    def boundaryTravel(self, gameState):
        return (0, 0), (0, 0)


class OffensiveAgent(SmartAgent):

    def getWeights(self, gameState, action):

        return {'successorScore': 100, 'invaderDistance': -50, 'distanceToFood': -1, 'foodRemaining': -1,
                'distanceToGhost': 2, 'ghostScared': -1, 'distanceToCapsules': -1, 'stop': -100, 'reverse': -20}

    def getAction(self, gameState):

        def getVal(depth, gamestate, decay):
            new_state = gamestate.deepCopy()
            legalActions = [action for action in new_state.getLegalActions(self.index) if action != Directions.STOP]
            currDirection = new_state.getAgentState(self.index).configuration.direction
            revDirection = Directions.REVERSE[currDirection]

            if revDirection in legalActions and len(legalActions) > 1:
                legalActions.remove(revDirection)
            if depth <= 0:
                next_state = new_state.generateSuccessor(self.index, random.choice(legalActions))
                return max([self.evaluate(next_state, Directions.STOP)])

            retList = []
            for l in legalActions:
                tmp = self.evaluate(new_state.generateSuccessor(self.index, l), Directions.STOP) + decay * getVal(
                    depth - 1, new_state.generateSuccessor(self.index, l), decay)
                retList.append(tmp)

            return max(retList)

        opps = {}
        gWeights = {'distance': 5, 'scared': 5}
        for e in self.getOpponents(gameState):
            opps[e] = gameState.getAgentState(e).getPosition()

        legalActions = [action for action in gameState.getLegalActions(self.index) if action != Directions.STOP]
        retAct = None

        valList = [getVal(self.search_deep, gameState.generateSuccessor(self.index, l), 0.3) for l in legalActions]
        bestAction = max(valList)

        for (x, y) in zip(valList, legalActions):
            if x == bestAction:
                retAct = y

        return retAct





    def getFeatures(self, gameState, action):
        features = util.Counter()
        successor = self.getSuccessor(gameState, action)

        mState = successor.getAgentState(self.index)
        features['successorScore'] = self.getScore(successor)

        mPos = mState.getPosition()

        enemy = []
        ghost = []
        invader = []

        #get enemy
        for i in self.getOpponents(successor):
          enemy.append(successor.getAgentState(i))

        for i in enemy:
          if not i.isPacman and i.getPosition() != None:
            ghost.append(i)
          elif i.isPacman and i.getPosition() != None:
            invader.append(i)

        features['invaderDistance'] = 0.0
        if len(invader) > 0:
            features['invaderDistance'] = min([self.getMazeDistance(mPos, i.getPosition()) for i in invader]) + 1

        if len(ghost) > 0:
            positions = [agent.getPosition() for agent in ghost]
            closest = min(positions, key=lambda x: self.getMazeDistance(mPos, x))
            closestDist = self.getMazeDistance(mPos, closest)
            features['ghostScared'] = 5
            if closestDist <= 5:
                features['distanceToGhost'] = closestDist
            else:
                features['distanceToGhost'] = min([successor.getAgentDistances()[i] for i in self.getOpponents(successor)])


        if len(invader) > 0:
            positions = [i.getPosition() for i in invader]
            closest = min(positions, key=lambda x: self.getMazeDistance(mPos, x))
            closestDist = self.getMazeDistance(mPos, closest)
            if closestDist < 4:
                features['distanceToGhost'] = closestDist
            else:
                features['distanceToGhost'] = 0

        #get foodlist
        fList = self.getFood(successor).asList()
        if len(fList) > 0:
            minDistance = min([self.getMazeDistance(mPos, f) for f in fList])
            features['distanceToFood'] = minDistance
            features['foodRemaining'] = len(fList)

        #get capsule
        capsules = self.getCapsules(successor)
        if len(capsules) > 0:
            minDistance = min([self.getMazeDistance(mPos, capsule) for capsule in capsules])
            if minDistance == 0: minDistance = -100
            features['distanceToCapsules'] = minDistance
        else:
            features['distanceToCapsules'] = 0

        #get boundary
        disBoundary = min([self.getMazeDistance(mPos, self.boundary[i]) for i in range(len(self.boundary))])
        features['disBoundary'] = disBoundary

        #get carrying
        features['carrying'] = successor.getAgentState(self.index).numCarrying

        rev = Directions.REVERSE[gameState.getAgentState(self.index).configuration.direction]

        if action == Directions.STOP:
          features['stop'] = 1
        if action == rev:
          features['reverse'] = 1

        return features




class ReflexCaptureAgent(CaptureAgent):
  """
  A base class for reflex agents that chooses score-maximizing actions
  """

  def registerInitialState(self, gameState):
    self.start = gameState.getAgentPosition(self.index)
    CaptureAgent.registerInitialState(self, gameState)

  def chooseAction(self, gameState):
    """
    Picks among the actions with the highest Q(s,a).
    """
    actions = gameState.getLegalActions(self.index)

    # You can profile your evaluation time by uncommenting these lines
    # start = time.time()
    values = [self.evaluate(gameState, a) for a in actions]
    # print 'eval time for agent %d: %.4f' % (self.index, time.time() - start)

    maxValue = max(values)
    bestActions = [a for a, v in zip(actions, values) if v == maxValue]

    foodLeft = len(self.getFood(gameState).asList())

    if foodLeft <= 2:
      bestDist = 9999
      for action in actions:
        successor = self.getSuccessor(gameState, action)
        pos2 = successor.getAgentPosition(self.index)
        dist = self.getMazeDistance(self.start, pos2)
        if dist < bestDist:
          bestAction = action
          bestDist = dist
      return bestAction

    return random.choice(bestActions)

  def getSuccessor(self, gameState, action):
    """
    Finds the next successor which is a grid position (location tuple).
    """
    successor = gameState.generateSuccessor(self.index, action)
    pos = successor.getAgentState(self.index).getPosition()
    if pos != nearestPoint(pos):
      # Only half a grid position was covered
      return successor.generateSuccessor(self.index, action)
    else:
      return successor

  def evaluate(self, gameState, action):
    """
    Computes a linear combination of features and feature weights
    """
    features = self.getFeatures(gameState, action)
    weights = self.getWeights(gameState, action)
    return features * weights

  def getFeatures(self, gameState, action):
    """
    Returns a counter of features for the state
    """
    features = util.Counter()
    successor = self.getSuccessor(gameState, action)
    features['successorScore'] = self.getScore(successor)
    return features

  def getWeights(self, gameState, action):
    """
    Normally, weights do not depend on the gamestate.  They can be either
    a counter or a dictionary.
    """
    return {'successorScore': 1.0}


class OffensiveReflexAgent(ReflexCaptureAgent):
  """
  A reflex agent that seeks food. This is an agent
  we give you to get an idea of what an offensive agent might look like,
  but it is by no means the best or only way to build an offensive agent.
  """

  def getFeatures(self, gameState, action):
    features = util.Counter()
    successor = self.getSuccessor(gameState, action)
    foodList = self.getFood(successor).asList()
    features['successorScore'] = -len(foodList)  # self.getScore(successor)

    # Compute distance to the nearest food

    if len(foodList) > 0:  # This should always be True,  but better safe than sorry
      myPos = successor.getAgentState(self.index).getPosition()
      minDistance = min([self.getMazeDistance(myPos, food) for food in foodList])
      features['distanceToFood'] = minDistance
    return features

  def getWeights(self, gameState, action):
    return {'successorScore': 100, 'distanceToFood': -1}


class DefensiveReflexAgent(ReflexCaptureAgent):
  """
  A reflex agent that keeps its side Pacman-free. Again,
  this is to give you an idea of what a defensive agent
  could be like.  It is not the best or only way to make
  such an agent.
  """

  def getFeatures(self, gameState, action):
    features = util.Counter()
    successor = self.getSuccessor(gameState, action)

    myState = successor.getAgentState(self.index)
    myPos = myState.getPosition()

    # Computes whether we're on defense (1) or offense (0)
    features['onDefense'] = 1
    if myState.isPacman: features['onDefense'] = 0

    # Computes distance to invaders we can see
    enemies = [successor.getAgentState(i) for i in self.getOpponents(successor)]
    invaders = [a for a in enemies if a.isPacman and a.getPosition() != None]
    features['numInvaders'] = len(invaders)
    if len(invaders) > 0:
      dists = [self.getMazeDistance(myPos, a.getPosition()) for a in invaders]
      features['invaderDistance'] = min(dists)

    if action == Directions.STOP: features['stop'] = 1
    rev = Directions.REVERSE[gameState.getAgentState(self.index).configuration.direction]
    if action == rev: features['reverse'] = 1

    return features

  def getWeights(self, gameState, action):
    return {'numInvaders': -1000, 'onDefense': 100, 'invaderDistance': -10, 'stop': -100, 'reverse': -2}
