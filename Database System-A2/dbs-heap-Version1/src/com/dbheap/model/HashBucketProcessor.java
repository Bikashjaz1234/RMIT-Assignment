package com.dbheap.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class HashBucketProcessor {
	private static HashBucketProcessor instance;
	private Hashtable<Integer, ArrayList<Long>> slots;
	public static final int SLOT_SIZE = 1;
	private int dataSize;
	
	private HashBucketProcessor() {}
	
	public static HashBucketProcessor getInstance() {
		if (instance == null)
			instance = new HashBucketProcessor();
		return instance;
	}
	
	public int getDataSize() {
		return dataSize;
	}

	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}

	public void initSlots() {
		slots = new Hashtable<Integer, ArrayList<Long>>();
		
		for (int i = 0; i < SLOT_SIZE; i++) {
			slots.put(i, new ArrayList<Long>());
		}
	}
	
	public Hashtable<Integer, ArrayList<Long>> getSlots() {
		if (slots == null) {
			this.initSlots();
		}
		return slots;
	}

	public void addOffset(int hashcode, long offset) {
		ArrayList<Long> offsets;
		int slotsNum = Math.abs(hashcode % SLOT_SIZE);
		offsets = slots.get(slotsNum);
		offsets.add(offset);
	}
	
	public List<Long> getOffsets(int hashcode) {
		int slotsNum = Math.abs(hashcode % SLOT_SIZE);
		return slots.get(slotsNum);
	}
}
