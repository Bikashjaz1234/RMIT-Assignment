package nearestNeigh;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class is required to be implemented.  Kd-tree implementation.
 *
 * @author Jeffrey, Youhan for start-up code
 * @author Harold Zang for implement the code
 */
public class KDTreeNN implements NearestNeigh{
	
	private KDTreeNode rootNode;
	private char[] axises = {'x', 'y'};

    @Override
    public void buildIndex(List<Point> points) {
        ArrayList<Point> alPoints = (ArrayList<Point>)points;
        rootNode = this.constructKDTree(alPoints, 0);
    }

    @Override
    public List<Point> search(Point searchTerm, int k) {
        // To be implemented.
        return new ArrayList<Point>();
    }

    @Override
    public boolean addPoint(Point point) {
        // To be implemented.
    	return false;
    }

    @Override
    public boolean deletePoint(Point point) {
        return false;
    }

    @Override
    public boolean isPointIn(Point point) {
        // To be implemented.
    	KDTreeNode currentNode, nextNode;
    	Point currentPoint;
    	currentNode = rootNode;
    	
    	while(true){
    		currentPoint = currentNode.getPoint();
    		if(currentPoint.equals(point)){
        		return true;
        	}
        	if(currentNode.getAxis() == 'x'){
        		if(point.lat > currentPoint.lat){
        			nextNode = currentNode.getRightChild();
        		}else{
        			// if two values are equal, choose as same as current value is smaller than target value
        			nextNode = currentNode.getLeftChild();
        		}
        	}else{
        		if(point.lon > currentPoint.lat){
        			nextNode = currentNode.getRightChild();
        		}else{
        			nextNode = currentNode.getLeftChild();
        		}
        	}
        	if(nextNode == null){
        		break;
        	}
        	currentNode = nextNode;
    	}
        return false;
    }

   private KDTreeNode constructKDTree(ArrayList<Point> points, int depth){
	   int length = points.size();
	   Point point, tempPoint;
	   KDTreeNode node, leafNode;
	   ArrayList<Point> leftList, rightList;
	   char axis = axises[depth%2];
	   
	   if(length == 1){
		   leafNode = new KDTreeNode(points.get(0), axis);
		   return leafNode;
	   }
	   
	   if(length == 2){
		   if(axis == 'x'){
			   if(points.get(0).lat < points.get(1).lat){
				   node = new KDTreeNode(points.get(1), axis);
				   leafNode = new KDTreeNode(points.get(0), axis);
				   node.setLeftChild(leafNode);
			   }else{
				   node = new KDTreeNode(points.get(0), axis);
				   leafNode = new KDTreeNode(points.get(1), axis);
				   node.setLeftChild(leafNode);
			   }
		   }else{
			   if(points.get(0).lon < points.get(1).lon){
				   node = new KDTreeNode(points.get(1), axis);
				   leafNode = new KDTreeNode(points.get(0), axis);
				   node.setLeftChild(leafNode);
			   }else{
				   node = new KDTreeNode(points.get(0), axis);
				   leafNode = new KDTreeNode(points.get(1), axis);
				   node.setLeftChild(leafNode);
			   }
		   }
		   return node;
	   }
	   
	   Comparator<Point> comparator;
	   // sort arrayList based on x axis
	   if(axis == 'x'){
		   comparator = new Comparator<Point>(){

			@Override
			public int compare(Point point1, Point point2) {
				if(point1.lat < point2.lat){
					return -1;
				}
				// if two values are equal, we put it in left child
				return 1;
			}
			   
		   };
	   }else{
		   comparator = new Comparator<Point>(){

			@Override
			public int compare(Point point1, Point point2) {
				if(point1.lon < point2.lon){
					return -1;
				}
				return 1;
			}
			   
		   };
	   }
	   
	   // sort list to find median value
	   points.sort(comparator);
	   int median = length/2;
	   point = points.get(median);
	   // check whether next point has the same point axis value
	   System.out.println("length------>" + points.size());
	   if(points.size() < 4){
		   for(Point temp : points){
			   System.out.println(temp);
		   }
	   }
	   tempPoint = points.get(median + 1);
	   
	   // if two nodes have same value, one point will be in left child node
	   if(axis == 'x'){
		   if(point.lat == tempPoint.lat){
			   point = tempPoint;
			   median += 1;
		   }
	   }else{
		   if(point.lon == tempPoint.lon){
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
    * */
   private void iterateTreeToListRec(KDTreeNode rootNode, ArrayList<Point> points){
	   if(rootNode.getLeftChild() != null){
		   iterateTreeToListRec(rootNode.getLeftChild(), points);
	   }
	   if(rootNode.getRightChild() != null){
		   iterateTreeToListRec(rootNode.getRightChild(), points);
	   }
	   points.add(rootNode.getPoint());
   }
}