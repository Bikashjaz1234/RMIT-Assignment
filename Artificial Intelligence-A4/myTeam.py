# myTeam.py
# ---------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


from captureAgents import CaptureAgent
import random, time, util
from game import Directions
from util import nearestPoint
import game

#################
# Team creation #
#################

def createTeam(firstIndex, secondIndex, isRed,
               first = 'OffensiveAgent', second = 'DummyAgent'):
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

class DummyAgent(CaptureAgent):
  """
  A Dummy agent to serve as an example of the necessary agent structure.
  You should look at baselineTeam.py for more details about how to
  create an agent as this is the bare minimum.
  """

  def registerInitialState(self, gameState):
    """
    This method handles the initial setup of the
    agent to populate useful fields (such as what team
    we're on).

    A distanceCalculator instance caches the maze distances
    between each pair of positions, so your agents can use:
    self.distancer.getDistance(p1, p2)

    IMPORTANT: This method may run for at most 15 seconds.
    """

    '''
    Make sure you do not delete the following line. If you would like to
    use Manhattan distances instead of maze distances in order to save
    on initialization time, please take a look at
    CaptureAgent.registerInitialState in captureAgents.py.
    '''
    CaptureAgent.registerInitialState(self, gameState)

    '''
    Your initialization code goes here, if you need any.
    '''


  def chooseAction(self, gameState):
    """
    Picks among actions randomly.
    """
    actions = gameState.getLegalActions(self.index)

    '''
    You should change this in your own agent.
    '''

    return random.choice(actions)

