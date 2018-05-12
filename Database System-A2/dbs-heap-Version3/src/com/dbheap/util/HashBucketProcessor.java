package com.dbheap.util;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;


public class HashBucketProcessor {
	private static HashBucketProcessor instance;
	private long[] offsets;
	private int slotSize = -1;
	
	private HashBucketProcessor() {}
	
	public static HashBucketProcessor getInstance() {
		if (instance == null)
			instance = new HashBucketProcessor();
		return instance;
	}
	
	public long[] getOffsets() {
		return offsets;
	}
	
	
	public void setSlotSize(int slotSize) {
		this.slotSize = slotSize;
	}

	public void initSlots(int recordAmount) {
		slotSize = (int)(recordAmount / 0.7);
		if (slotSize > 0) {
			offsets = new long[this.slotSize];
			Arrays.fill(offsets, -1l);
		} else {
			System.err.println("Please set your record amount!");
		}
	}
	

	public void addOffset(int hashcode, long offset) {
		if (offsets[hashcode] == -1l) {
			offsets[hashcode] = offset;
		} else {
			while (++hashcode < this.slotSize) {
				if (offsets[hashcode] == -1) {
					offsets[hashcode] = offset;
					return;
				}
			}
			System.err.println("Record insert failure!");
			
		}
	}
	
	public int hashCode(String str) {
		int result = 0;
		byte[] letters;
		
		letters = str.trim().getBytes(StandardCharsets.US_ASCII);
		for (byte letter: letters) {
			result += letter;
		}
		
		return result % this.slotSize;
	}
}
