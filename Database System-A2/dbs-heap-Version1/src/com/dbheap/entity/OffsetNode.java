package com.dbheap.entity;

public class OffsetNode {
	private long offset;
	private OffsetNode nextNode;
	
	public OffsetNode(long offset) {
		this.offset = offset;
	}

	public OffsetNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(OffsetNode nextNode) {
		this.nextNode = nextNode;
	}

	public long getOffset() {
		return offset;
	}
	
	public boolean hasNextNode() {
		return this.nextNode != null;
	}
}
