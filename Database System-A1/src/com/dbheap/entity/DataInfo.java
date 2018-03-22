package com.dbheap.entity;

public class DataInfo {
	private String columes;
	private int recordFixedSize;
	private int recordFixedStringLength;
	public int getRecordFixedStringLength() {
		return recordFixedStringLength;
	}
	public void setRecordFixedStringLength(int recordFixedStringLength) {
		this.recordFixedStringLength = recordFixedStringLength;
	}
	public String getColumes() {
		return columes;
	}
	public void setColumes(String columes) {
		this.columes = columes;
	}
	public int getRecordFixedSize() {
		return recordFixedSize;
	}
	public void setRecordFixedSize(int recordFixedSize) {
		this.recordFixedSize = recordFixedSize;
	}
	
	public boolean isSubStringInTragetColume(String subStr, String colume, String dataLine) {
		String[] columeVals = dataLine.split("\t");
		String[] columesTitle = columes.split("\t");
		for (int i = 0; i < columeVals.length; i++) {
			if (columesTitle[i].equals(colume)) {
				return columeVals[i].toLowerCase().contains(subStr.toLowerCase());
			}
		}
		return false;
	}
	
	public void printData(String dataLine) {
		String[] columeVals = dataLine.split("\t");
		String[] columeTitle = this.columes.split("\t");
		for (int i = 0; i < columeVals.length; i++) {
			System.out.print(columeTitle[i]);
			System.out.print(":");
			System.out.print(columeVals[i].trim());
			System.out.print(" ");
		}
		System.out.println();
	}
}
