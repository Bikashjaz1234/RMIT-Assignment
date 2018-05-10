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

class Agent(CaptureAgent):
    """
  choose the action of max score.
  """

    def registerInitialState(self, gameState):
      CaptureAgent.registerInitialState(self, gameState)

      self.boundaryTop = True
      self.depth = 3
      self.boundaries = self.boundaryTravel(gameState)
      self.isRed = False

      if gameState.getAgentState(self.index).getPosition()[0] == 1:
        self.isRed = True

    def chooseAction(self, gameState):
        start = time.time()
        if time.time() - start > 0.9:
          print 'eval time for agent %d: %.4f' % (self.index, time.time() - start)

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
        features['successorScore'] = self.getScore( self.getSuccessor(gameState, action))
        return features

    def getWeights(self, gameState, action):

        return {'successorScore': 1.0}

    def boundaryTravel(self, gameState):
        return (0, 0), (0, 0)


class OffensiveAgent(Agent):

    def getAction(self, gameState):
        opps = {}
        dirt = {'north': (0, 1), 'south': (0, -1), 'east': (1, 0), 'west': (-1, 0)}
        gWeights = {'distance': 5, 'scared': 5}
        for e in self.getOpponents(gameState):
            opps[e] = gameState.getAgentState(e).getPosition()

        def getNewPosition(current_pos, action):
            act = []
            for d in dirt:
              if str(action).lower() == d:
                act = dirt[d]
                break
            return (current_pos[0] + act[0], current_pos[1] + act[1])

        def getGhostActions(currPos):
            actions = []
            walls = gameState.getWalls().asList()

            mx = max([wall[0] for wall in walls])
            my = max([wall[1] for wall in walls])

            for key in dirt:
                act = dirt[key]
                newPos = (int(currPos[0] + act[0]), int(currPos[1] + act[1]))
                if newPos not in walls:
                    if (1 <= newPos[0] < mx) and (1 <= newPos[1] < my):
                        actions.append(key.title())

            return actions

        def expectation(gamestate, position, legalActions):
            gDict = {}
            for action in legalActions:
              newPos = getNewPosition(position, action)
              gDict[action] = self.getMazeDistance(position, newPos) * gWeights['distance']
            minAct = min(gDict)
            for act in gDict:
              gDict[act] = 0.2 / len(legalActions)
              if gDict[act] == minAct:
                  gDict[act] = 0.8

            return gDict

        def gEval(gamestate, opponents, opponent):
            ret = self.getMazeDistance(gamestate.getAgentState(self.index).getPosition(), opponents[opponent]) * gWeights['distance']

            if gamestate.getAgentState(opponent).scaredTimer != 0:
                ret *= -1

            return ret

        def minimax(gamestate, depth, agent, opponents, alpha=-float('inf'), beta=float('inf')):

            legalActions = [action for action in gamestate.getLegalActions(self.index) if action != Directions.STOP]
            actions = {}
            minVal = float('inf')
            maxVal = -float('inf')

            if agent == self.index:
                for act in legalActions:
                    eval = self.evaluate(gamestate, act)
                    value = eval
                    if depth != self.depth:
                        value = eval + minimax(self.getSuccessor(gamestate, act), depth, agent + 1, opponents, alpha,
                                               beta)
                    maxVal = max(maxVal, value)
                    if beta < maxVal:
                        return maxVal
                    else:
                        alpha = max(alpha, maxVal)
                    if depth == 1:
                        actions[value] = act
                if depth == 1:
                    return actions[maxVal]
                return maxVal
            else:
                for opponent in opponents:
                  if gamestate.getAgentState(opponent).getPosition() is None: continue

                  legalAct = getGhostActions(opponents[opponent])
                  expect = expectation(gamestate, opponents[opponent], legalAct)
                  for act in legalAct:
                    new_opponents = opponents.copy()
                    new_opponents[opponent] = getNewPosition(opponents[opponent], act)
                    ghost_val = gEval(gamestate, new_opponents, opponent) * expect[act]
                    value = ghost_val + minimax(gamestate, depth + 1, self.index, new_opponents, alpha, beta)
                    minVal = min(minVal, value)
                    if minVal < alpha: return minVal
                    else: beta = min(beta, minVal)
                if minVal == float('inf'):
                    return 0
                return minVal

        return minimax(gameState, 1, self.index, opps)

    def getFeatures(self, gameState, action):
        features = util.Counter()
        successor = self.getSuccessor(gameState, action)
        mState = successor.getAgentState(self.index)
        features['successorScore'] = self.getScore(successor)
        mPos = mState.getPosition()
        enemy = []
        ghost = []
        invader = []
        regGhosts = []
        scaredGhosts = []

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
            gEval = 0.0
            sDistance = 0.0
            for g in ghost:
              if g.scaredTimer == 0:
                regGhosts.append(g)
              elif g.scaredTimer > 0:
                scaredGhosts.append(g)
            if len(regGhosts) > 0:
                gEval = min([self.getMazeDistance(mPos, g.getPosition()) for g in regGhosts])
                if gEval <= 1:
                  gEval = -float('inf')

            if len(scaredGhosts) > 0:
              sDistance = min([self.getMazeDistance(mPos, g.getPosition()) for g in scaredGhosts])
            if sDistance < gEval or gEval == 0:
                if sDistance == 0:
                  features['ghostScared'] = -10
            features['distanceToGhost'] = gEval

        fList = self.getFood(successor).asList()
        if len(fList) > 0:
            minDistance = min([self.getMazeDistance(mPos, f) for f in fList])
            features['distanceToFood'] = minDistance
            features['foodRemaining'] = len(fList)

        capsules = self.getCapsules(gameState)
        if len(capsules) > 0:
            minDistance = min([self.getMazeDistance(mPos, capsule) for capsule in capsules])
            if minDistance == 0: minDistance = -100
            features['distanceToCapsules'] = minDistance

        rev = Directions.REVERSE[gameState.getAgentState(self.index).configuration.direction]

        if action == Directions.STOP:
          features['stop'] = 1
        if action == rev:
          features['reverse'] = 1

        return features

    def getWeights(self, gameState, action):
        return {'successorScore': 100, 'invaderDistance': -50, 'distanceToFood': -1, 'foodRemaining': -1,
                'distanceToGhost': 2, 'ghostScared': -1, 'distanceToCapsules': -1, 'stop': -100, 'reverse': -20}


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
