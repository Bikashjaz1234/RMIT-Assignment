# search.py
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


"""
In search.py, you will implement generic search algorithms which are called by
Pacman agents (in searchAgents.py).
"""

import util

class SearchProblem:
    """
    This class outlines the structure of a search problem, but doesn't implement
    any of the methods (in object-oriented terminology: an abstract class).

    You do not need to change anything in this class, ever.
    """

    def getStartState(self):
        """
        Returns the start state for the search problem.
        """
        util.raiseNotDefined()

    def isGoalState(self, state):
        """
          state: Search state

        Returns True if and only if the state is a valid goal state.
        """
        util.raiseNotDefined()

    def getSuccessors(self, state):
        """
          state: Search state

        For a given state, this should return a list of triples, (successor,
        action, stepCost), where 'successor' is a successor to the current
        state, 'action' is the action required to get there, and 'stepCost' is
        the incremental cost of expanding to that successor.
        """
        util.raiseNotDefined()

    def getCostOfActions(self, actions):
        """
         actions: A list of actions to take

        This method returns the total cost of a particular sequence of actions.
        The sequence must be composed of legal moves.
        """
        util.raiseNotDefined()


def tinyMazeSearch(problem):
    """
    Returns a sequence of moves that solves tinyMaze.  For any other maze, the
    sequence of moves will be incorrect, so only use this for tinyMaze.
    """
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return  [s, s, w, s, w, w, s, w]

def depthFirstSearch(problem):
    """
    Search the deepest nodes in the search tree first.

    Your search algorithm needs to return a list of actions that reaches the
    goal. Make sure to implement a graph search algorithm.

    To get started, you might want to try some of these simple commands to
    understand the search problem that is being passed in:

    print "Start:", problem.getStartState()
    print "Is the start a goal?", problem.isGoalState(problem.getStartState())
    print "Start's successors:", problem.getSuccessors(problem.getStartState())
    """
    "*** YOUR CODE HERE ***"
    #print "Start:", problem.getStartState()
    #print "Is the start a goal?", problem.isGoalState(problem.getStartState())
    #print "Start's successors:", problem.getSuccessors(problem.getStartState())
    start = problem.getStartState()
    path = util.Stack()
    isVisited = {}

    dfs_recurse(problem, start, path, isVisited)
    direction = []

    while not path.isEmpty():
        node = path.pop()
        #print node
        direction.insert(0, node[1])

    return direction
    #util.raiseNotDefined()

def breadthFirstSearch(problem):
    """Search the shallowest nodes in the search tree first."""
    "*** YOUR CODE HERE ***"
    queue = util.Queue()
    start = problem.getStartState()

    current_state = start
    path = {}
    isVisited = {}
    flagBFS = False

    # direction, cost, parent
    '''
    Using path dictionary to store the information,
    then use these informaiton to create a path to the
    end point.
    @start is the point that it wants to go.
    '''
    path[start] = [None, 0, None]
    
    isVisited[current_state] = True
    queue.push(current_state)
    
    

    while not queue.isEmpty():
    	
    	if flagBFS == False:
    		current_state = queue.pop()
        
        if problem.isGoalState(current_state):
            flagBFS = True
            break

        for successor in problem.getSuccessors(current_state):
            neigh_node = successor[0] #neighbour Node
            direction = successor[1]
            cost = successor[2]


            if neigh_node not in isVisited:
                isVisited[neigh_node] = True
                queue.push(neigh_node)
                path[neigh_node] = [direction, 0, current_state]

    direction = []


    while path[current_state][2] is not None:
        direction.insert(0, path[current_state][0])
        current_state = path[current_state][2]
        #print path[current_state][2]

    return direction
    #util.raiseNotDefined()

def uniformCostSearch(problem):
    """Search the node of least total cost first."""
    "*** YOUR CODE HERE ***"
    
    queue = util.PriorityQueue()
    start = problem.getStartState()

    # current node && cost
    current_state = (start, 0)

    path = {}
    # direction, cost, parent
    path[start] = [None, 0, None]

    queue.push(current_state, current_state[1])

    while not queue.isEmpty():
        current_state = queue.pop()
        if problem.isGoalState(current_state[0]):
            break

        for successor in problem.getSuccessors(current_state[0]):
            
            successor_name = successor[0]
            d = successor[1]
            c = successor[2]
            #print successor[0]

            if successor_name not in path:
                successor = (successor_name, successor[2]+current_state[1])
                queue.push(successor, successor[1])
                path[successor_name] = [d, successor[1], current_state[0]]
                
            else:
                if current_state[1] + c < path[successor_name][1]:
                    path[successor_name][0] = d
                    path[successor_name][1] = current_state[1] + c
                    path[successor_name][2] = current_state[0]

    direction = []

    current_node = current_state[0]
    while path[current_node][2] is not None:

        direction.insert(0, path[current_node][0])
        current_node = path[current_node][2]

    return direction
    #util.raiseNotDefined()

def nullHeuristic(state, problem=None):
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0

def aStarSearch(problem, heuristic=nullHeuristic):
    """Search the node that has the lowest combined cost and heuristic first."""
    "*** YOUR CODE HERE ***"
    #Give INF is very big number
    INF = 999999999999 #infinite
    start = problem.getStartState()

    closedSet = set()
    openSet = set()
    queue = util.PriorityQueue()

    gScore = {}
    gScore[start] = 0

    fScore = {}
    fScore[start] = gScore.get(start, INF) + heuristic(start, problem)

    #direction, parent
    path = {start:(None, None)}

    queue.push(start, fScore.get(start, INF))
    openSet.add(start)

    while not queue.isEmpty():
        current_state = queue.pop()

        if problem.isGoalState(current_state):
            break

        openSet.remove(current_state)
        closedSet.add(current_state)

        for successor in problem.getSuccessors(current_state):
            neigh_node = successor[0]
            direction = successor[1]
            cost = successor[2]
            if neigh_node in closedSet:
                continue

            if neigh_node not in openSet:
                openSet.add(neigh_node)
                queue.push(neigh_node, fScore.get(neigh_node, INF))

            neigh_gScore = gScore.get(current_state, INF) + cost
            if neigh_gScore >= gScore.get(neigh_node, INF):
                continue

            path[neigh_node] = (direction, current_state)
            gScore[neigh_node] = neigh_gScore
            fScore[neigh_node] = gScore[neigh_node] + heuristic(neigh_node, problem)

            queue.update(neigh_node, fScore.get(neigh_node, INF))



    direction = []
    current_node = current_state
    while path[current_node][1] is not None:

        direction.insert(0, path[current_node][0])
        current_node = path[current_node][1]

    return direction
    #util.raiseNotDefined()

def dfs_recurse(problem, start, path, isVisited):
    isVisited[start] = True
    if problem.isGoalState(start):
        return True
    else:
        for successor in problem.getSuccessors(start):
            if successor[0] not in isVisited:
                path.push(successor)
                if dfs_recurse(problem, successor[0], path, isVisited):
                    return True
    path.pop()

# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch
