package nearestNeigh;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class is required to be implemented. Kd-tree implementation.
 *
 * @author Jeffrey, Youhan for start-up Code
 * @author Harold Zang for impliment code
 */
public class KDTreeNN implements NearestNeigh {

	private KDTreeNode rootNode;
	private char[] axises = { 'x', 'y' };

	@Override
	public void buildIndex(List<Point> points) {
		ArrayList<Point> alPoints = (ArrayList<Point>) points;
		rootNode = this.constructKDTree(alPoints, 0);
	}

	@Override
	public List<Point> search(Point searchTerm, int k) {
		ArrayList<Point> results = new ArrayList<Point>();
		KDTreeNode node;
		ReturnResult returnResult = new ReturnResult();
		returnResult.k = k;
		returnResult.distanceStandard = 0.00;
		returnResult.biggestDistancePointInResult = null;
		node = rootNode;
		this.searchClosestDistanceRec(node, searchTerm, results, returnResult);
		return results;
	}

	@Override
	public boolean addPoint(Point point) {
		// To be implemented.
		KDTreeNode currentNode, nextNode;
		Point currentPoint;
		currentNode = rootNode;

		while (true) {
			currentPoint = currentNode.getPoint();
			if (currentPoint.equals(point)) {
				return false;
			}
			if (currentNode.getAxis() == 'x') {
				if (point.lat > currentPoint.lat) {
					nextNode = currentNode.getRightChild();
					if (nextNode == null) {
						// set next axis of node to be 'y' because current is
						// 'x'
						nextNode = new KDTreeNode(point, 'y');
						currentNode.setRightChild(nextNode);
						return true;
					}
				} else {
					// if two values are equal, choose as same as current value
					// is smaller than targe value
					nextNode = currentNode.getLeftChild();
					if (nextNode == null) {
						nextNode = new KDTreeNode(point, 'y');
						currentNode.setLeftChild(nextNode);
						return true;
					}
				}
			} else {
				if (point.lon > currentPoint.lon) {
					nextNode = currentNode.getRightChild();
					if (nextNode == null) {
						// set next axis of node to be 'y' because current is
						// 'x'
						nextNode = new KDTreeNode(point, 'x');
						currentNode.setRightChild(nextNode);
						return true;
					}
				} else {
					nextNode = currentNode.getLeftChild();
					if (nextNode == null) {
						nextNode = new KDTreeNode(point, 'x');
						currentNode.setLeftChild(nextNode);
						return true;
					}
				}
			}
			currentNode = nextNode;
		}
	}

	@Override
	public boolean deletePoint(Point point) {
		// find the position of point to delete
		KDTreeNode currentNode, deleteNode, tempNode, newSubTreeRootNode;
		Point deletePoint;
		currentNode = rootNode;
		deleteNode = rootNode;
		tempNode = rootNode;
		Comparator<Point> comparator;
		while (true) {
			deletePoint = deleteNode.getPoint();
			if (deletePoint.equals(point)) {
				break;
			}
			if (deleteNode.getAxis() == 'x') {
				if (point.lat > deletePoint.lat) {
					deleteNode = deleteNode.getRightChild();
				} else {
					// if two values are equal, choose as same as current value
					// is smaller than targe value
					deleteNode = deleteNode.getLeftChild();
				}
			} else {
				if (point.lon > deletePoint.lon) {
					deleteNode = deleteNode.getRightChild();
				} else {
					deleteNode = deleteNode.getLeftChild();
				}
			}
			if (deleteNode == null) {
				return false;
			}
			if (!tempNode.getPoint().equals(currentNode.getPoint())) {
				currentNode = tempNode;
			}
			tempNode = deleteNode;
		}

		// remove target node straightly if it is a leaf node
		if (deleteNode.getLeftChild() == null && deleteNode.getRightChild() == null) {
			if (currentNode.getAxis() == 'x') {
				if (currentNode.getPoint().lat < deleteNode.getPoint().lat) {
					currentNode.setRightChild(null);
				} else {
					currentNode.setLeftChild(null);
				}
			} else {
				if (currentNode.getPoint().lon < deleteNode.getPoint().lon) {
					currentNode.setRightChild(null);
				} else {
					currentNode.setLeftChild(null);
				}
			}
			return true;
		}

		ArrayList<Point> subTreeList = new ArrayList<Point>();
		iterateTreeToListRec(deleteNode, subTreeList);

		// sort subTreeList
		if (deleteNode.getAxis() == 'x') {
			comparator = new Comparator<Point>() {

				@Override
				public int compare(Point point1, Point point2) {
					if (point1.lat < point2.lat) {
						return -1;
					}
					return 1;
				}

			};
		} else {
			comparator = new Comparator<Point>() {

				@Override
				public int compare(Point point1, Point point2) {
					if (point1.lon < point2.lon) {
						return -1;
					}
					return 1;
				}
			};
		}
		subTreeList.sort(comparator);
		int median = subTreeList.size() / 2;
		boolean removeFlag = false;
		// remove point and node
		for (int i = 0; i + median < subTreeList.size(); i++) {
			deletePoint = subTreeList.get(median + i);
			if (deletePoint.equals(point)) {
				subTreeList.remove(deletePoint);
				removeFlag = true;
				break;
			}
		}

		if (removeFlag) {
			int depth;
			if (deleteNode.getAxis() == 'x') {
				depth = 0;
			} else {
				depth = 1;
			}
			newSubTreeRootNode = this.constructKDTree(subTreeList, depth);
			if (currentNode.getAxis() == 'x') {
				if (currentNode.getPoint().lat < newSubTreeRootNode.getPoint().lat) {
					currentNode.setLeftChild(newSubTreeRootNode);
				} else {
					currentNode.setRightChild(newSubTreeRootNode);
				}
			} else {
				if (currentNode.getPoint().lon < newSubTreeRootNode.getPoint().lon) {
					currentNode.setRightChild(newSubTreeRootNode);
				} else {
					currentNode.setLeftChild(newSubTreeRootNode);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isPointIn(Point point) {
		System.out.println("0-----" + point.id);
		// To be implemented.
		KDTreeNode currentNode, nextNode;
		Point currentPoint;
		currentNode = rootNode;

		while (true) {
			currentPoint = currentNode.getPoint();
			System.out.println("------>" + currentPoint.id);
			if (currentPoint.equals(point)) {
				return true;
			}
			if (currentNode.getAxis() == 'x') {
				if (point.lat > currentPoint.lat) {
					nextNode = currentNode.getRightChild();
				} else {
					// if two values are equal, choose as same as current value
					// is smaller than targe value
					nextNode = currentNode.getLeftChild();
				}
			} else {
				if (point.lon > currentPoint.lon) {
					nextNode = currentNode.getRightChild();
				} else {
					nextNode = currentNode.getLeftChild();
				}
			}
			if (nextNode == null) {
				break;
			}
			currentNode = nextNode;
		}
		return false;
	}
	
	/*
	 * 用递归构造整个树。 分别创建针对x轴和y轴数值排序的comparator，每次分别对对应的sublist分别进行排序 然后分别取中值来作为对应subtree的跟节点
	 * 问一下：若两个相比较的坐标值相等 则取数组中下标较大的一个作为subtree的根节点，来保证相同数值的数会出现在左边subtree
	 * */
	private KDTreeNode constructKDTree(ArrayList<Point> points, int depth) {
		int length = points.size();
		Point point, tempPoint;
		KDTreeNode node, leafNode;
		ArrayList<Point> leftList, rightList;
		char axis = axises[depth % 2];

		if (length == 1) {
			leafNode = new KDTreeNode(points.get(0), axis);
			return leafNode;
		}

		if (length == 2) {
			if (axis == 'x') {
				if (points.get(0).lat < points.get(1).lat) {
					node = new KDTreeNode(points.get(1), axis);
					leafNode = new KDTreeNode(points.get(0), axis);
					node.setLeftChild(leafNode);
				} else {
					node = new KDTreeNode(points.get(0), axis);
					leafNode = new KDTreeNode(points.get(1), axis);
					node.setLeftChild(leafNode);
				}
			} else {
				if (points.get(0).lon < points.get(1).lon) {
					node = new KDTreeNode(points.get(1), axis);
					leafNode = new KDTreeNode(points.get(0), axis);
					node.setLeftChild(leafNode);
				} else {
					node = new KDTreeNode(points.get(0), axis);
					leafNode = new KDTreeNode(points.get(1), axis);
					node.setLeftChild(leafNode);
				}
			}
			return node;
		}

		Comparator<Point> comparator;
		// sort arrayList based on x axis
		if (axis == 'x') {
			comparator = new Comparator<Point>() {

				@Override
				public int compare(Point point1, Point point2) {
					if (point1.lat < point2.lat) {
						return -1;
					}
					// if two values are equal, we put it in left child
					return 1;
				}

			};
		} else {
			comparator = new Comparator<Point>() {

				@Override
				public int compare(Point point1, Point point2) {
					if (point1.lon < point2.lon) {
						return -1;
					}
					return 1;
				}

			};
		}

		// sort list to find median value
		points.sort(comparator);
		int median = length / 2;
		point = points.get(median);
		// check whether next point has the same point axis value
		tempPoint = points.get(median + 1);

		// if two nodes have same value, one point will be in left child node
		if (axis == 'x') {
			if (point.lat == tempPoint.lat) {
				point = tempPoint;
				median += 1;
			}
		} else {
			if (point.lon == tempPoint.lon) {
				point = tempPoint;
				median += 1;
			}
		}

		node = new KDTreeNode(point, axis);

		leftList = new ArrayList<Point>(points.subList(0, median));
		rightList = new ArrayList<Point>(points.subList(median + 1, length));

		node.setLeftChild(constructKDTree(leftList, depth + 1));
		node.setRightChild(constructKDTree(rightList, depth + 1));
		return node;
	}

	/*
	 * Iterate all kdtree node and save them into a list
	 */
	private void iterateTreeToListRec(KDTreeNode rootNode, ArrayList<Point> points) {
		if (rootNode.getLeftChild() != null) {
			iterateTreeToListRec(rootNode.getLeftChild(), points);
		}
		if (rootNode.getRightChild() != null) {
			iterateTreeToListRec(rootNode.getRightChild(), points);
		}
		points.add(rootNode.getPoint());
	}
	
	/*
	 * 递归方法来对最短距离进行搜索
	 * */
	private ReturnResult searchClosestDistanceRec(KDTreeNode node, Point point, ArrayList<Point> results,
			ReturnResult returnResult) {
		KDTreeNode nextNode;
		Point currentPoint;
		char axis;
		double currentDistance, distanceStandard = returnResult.distanceStandard;
		Point biggestDistancePointInResult = returnResult.biggestDistancePointInResult;
		int k = returnResult.k;
		boolean isNextNodeRightNode = false, isClosePoint = false;
		currentPoint = node.getPoint();
		axis = node.getAxis();
		// 找出为什么本应是最短距离节点之一的节点 id29 没有出现在结果集中
		// 因为 id63 不是最短的节点之一， 导致我们不会去搜寻另一侧的subtree
		if(node.getPoint().id.equals("id63")){
			System.out.println("-=-=-=-=-=");
		}
		
		// 险判断当前节点是否为叶子节点, 如果搜寻本应去左边的subtree， 但左边的subtree为空的话，则选择右边的subtree进行搜索 直到获得Leaf node
		// find out leaf node
		if (axis == 'x') {
			if (currentPoint.lat < point.lat) {
				nextNode = node.getRightChild();
				if (nextNode == null) {
					nextNode = node.getLeftChild();
				} else {
					isNextNodeRightNode = true;
				}
			} else {
				nextNode = node.getLeftChild();
				if (nextNode == null) {
					nextNode = node.getRightChild();
				} else {
					isNextNodeRightNode = false;
				}
			}
		} else {
			if (currentPoint.lon < point.lon) {
				nextNode = node.getRightChild();
				if (nextNode == null) {
					nextNode = node.getLeftChild();
				} else {
					isNextNodeRightNode = true;
				}
			} else {
				nextNode = node.getLeftChild();
				if (nextNode == null) {
					nextNode = node.getRightChild();
					isNextNodeRightNode = true;
				} else {
					isNextNodeRightNode = false;
				}
			}
		}

		// check whether nextNode is a leaf node
		if (nextNode == null) {
			if(!point.cat.equals(currentPoint.cat)){
				return returnResult;
			}
			currentDistance = currentPoint.distTo(point);

			// To check whether results list has more space
			// 如果结果集仍然有可用的空间， 则插入当前距离的节点 并且检查是否当前point的距离是结果集中的最大距离
			if (k > 0) {
				results.add(currentPoint);
				k--;
				// set biggest distance in the result
				if (currentDistance > distanceStandard) {
					biggestDistancePointInResult = currentPoint;
					returnResult.k = k;
					returnResult.distanceStandard = currentDistance;
					returnResult.biggestDistancePointInResult = currentPoint;
					return returnResult;
				} else {
					returnResult.k = k;
					return returnResult;
				}
			} else {
				// 如果结果集已满 则删除其中的最大距离节点 插入新节点如果新节点的距离更近
				if(currentDistance < distanceStandard){
					results.remove(biggestDistancePointInResult);
					results.add(currentPoint);
					returnResult = searchBiggestDistancePoint(results, point, returnResult);
					return returnResult;
				}
			}
		}else{
			// current node is not a leaf node
			// 当前节点不是一个叶子节点 则继续递归下一个节点
			returnResult = searchClosestDistanceRec(nextNode, point, results, returnResult);
			distanceStandard = returnResult.distanceStandard;
			k = returnResult.k;
			biggestDistancePointInResult = returnResult.biggestDistancePointInResult;
			// id63 can't enter the mapping results cause we lose to compare distance or id29
			currentDistance = currentPoint.distTo(point);
			
			// 判断是否将当前节点加入到结果集当中
			if(k > 0){
				if((currentDistance > distanceStandard) && currentPoint.cat.equals(point.cat)){
					returnResult.biggestDistancePointInResult = currentPoint;
					returnResult.distanceStandard = currentDistance;
					results.add(returnResult.biggestDistancePointInResult);
					isClosePoint = true;
					k--;
					returnResult.k = k;
				}else if(currentPoint.cat.equals(point.cat)){
					results.add(currentPoint);
					isClosePoint = true;
					k--;
					returnResult.k = k;
				}
			}else{
				if((currentDistance < distanceStandard) && currentPoint.cat.equals(point.cat)){
					results.remove(biggestDistancePointInResult);
					results.add(currentPoint);
					returnResult = this.searchBiggestDistancePoint(results, point, returnResult);
					isClosePoint = true;
				}
			}
			
			// if current point is one of closest we will check another sub-branch
			// 如果当前节点是最近的节点之一， 则对当前节点的另一个subtree进行搜索和查询
			if(isClosePoint){
				// we've already checked right sub branch
				if(isNextNodeRightNode){
					if(node.getLeftChild() != null)
						returnResult = this.searchClosestDistanceRec(node.getLeftChild(), point, results, returnResult);
				}else{
					if(node.getRightChild() != null)
						returnResult = this.searchClosestDistanceRec(node.getRightChild(), point, results, returnResult);
				}
			}
		}
		return returnResult;
	}
	
	private ReturnResult searchBiggestDistancePoint(ArrayList<Point> points, Point point, ReturnResult returnResult){
		double currentDistance, biggestDistance = 0.00;
		Point resultPoint = null;
		for(Point tempPoint : points){
			currentDistance = tempPoint.distTo(point);
			if(currentDistance > biggestDistance){
				biggestDistance = currentDistance;
				resultPoint = tempPoint;
			}
		}
		returnResult.biggestDistancePointInResult = resultPoint;
		returnResult.distanceStandard = biggestDistance;
		return returnResult;
	}
	
	class ReturnResult{
		int k;
		double distanceStandard;
		Point biggestDistancePointInResult;
	}
}