package nearestNeigh;

// Create a class for sotre point and axis.
public class KDTreeNode {
	private Point point;
	private char axis;
	private KDTreeNode leftChild, rightChild;
	public KDTreeNode(Point point, char axis) {
		super();
		this.point = point;
		this.axis = axis;
	}
	
	public KDTreeNode(Point point) {
		super();
		this.point = point;
	}
	
	public char getAxis() {
		return axis;
	}
	
	public void setAxis(char axis) {
		this.axis = axis;
	}
	
	public KDTreeNode getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(KDTreeNode leftChild) {
		this.leftChild = leftChild;
	}
	
	public KDTreeNode getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(KDTreeNode rightChild) {
		this.rightChild = rightChild;
	}
	
	public Point getPoint() {
		return point;
	}
	
	
}