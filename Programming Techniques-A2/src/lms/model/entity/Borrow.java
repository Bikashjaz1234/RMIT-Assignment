package lms.model.entity;

import lms.model.util.DateTime;

/*
 * A class that for store the borrow information.
 * 1. store the data: who borrowed which book on which day.
 * 2. write multiple method to make sure other class can user it.
 */

public class Borrow {
	protected String memberID;
	protected String holdingId;
	private DateTime borrowDate;
	
	//store which people borrowed which book on which day
	public Borrow(String holdingId, String memberID, DateTime borrowDate){
		this.holdingId = holdingId;
		this.memberID = memberID;
		this.borrowDate = borrowDate;
	}
	
	public String getHoldingId() {
		return memberID;
	}
	
	public String getMemberId() {
		return holdingId;
	}
	
	public DateTime getBorrowDate() {
		borrowDate = new DateTime();
		return borrowDate;
	}
	
	public String toString(){
		StringBuffer su = new StringBuffer();
		su.append(holdingId);
		su.append(":");
		su.append(memberID);
		su.append(":");
		su.append(borrowDate);
		return su.toString();
	}
	
	public void print(){
		System.out.println("Member Id:\t\t\t" + this.getMemberId());
		System.out.println("Holding Id:\t\t\t" + this.getHoldingId());
		System.out.println("Borrow Date:\t\t" + this.getBorrowDate());
		System.out.println("");
	}
}
