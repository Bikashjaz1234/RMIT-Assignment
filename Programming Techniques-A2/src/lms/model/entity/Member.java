package lms.model.entity;

import lms.model.exception.FullException;
import lms.model.util.DateTime;


public abstract class Member implements SystemOperations{
	//multiple variable for member function.
	protected String memberID;	
	private String fullName;
	protected int credit;
	private boolean status = true;
	private int loanFee;
	private Holding[] holdings;
	private static final int MAX_HOLDING = 5;
	private int currentHoldingNum = 0;
	protected boolean checkReturn = false;	//variable for check return is success or not.
	
	public Member(String memberID, String fullName, int credit){
		this.memberID = memberID;
		this.fullName = fullName;
		this.credit = credit;
	}
	
	public String getId() {
		return memberID;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public boolean activate() {
		return true;
	}
	
	public boolean deactivate() {
		return true;
	}
	
	public int getCredit(){
		return credit;
	}
	
	public boolean getCheckReturn(){
		return this.checkReturn;
	}
	
	/*
	 * a function for update Remain credit.
	 * if credit more than loan fee, calculate the remain credit, and tell user that you borrow success.
	 * else tell user that you do not have enough credit.
	 */
	public boolean updateRemainingCredit(int loanFee){
		if (credit >= loanFee){
			credit = credit - loanFee;
			System.out.println("You borrow succeed, and your credit remain:" + credit);
			return true;
		}else{
			System.out.println("You do not have enough credit!");
			return false;
		}
	}
	
	public void print(){
		System.out.println("ID:\t\t\t" + this.getId());
		System.out.println("Title:\t\t\t" + this.getFullName());
		System.out.println("Max Credit:\t\t" + this.getMaxCredit());
		System.out.println("Current Credit:\t\t" + this.getCredit());
		System.out.println("");
	}
	
	public String toString(){
		StringBuffer su = new StringBuffer();
		su.append(this.getId());
		su.append(":");
		su.append(this.getFullName());
		su.append(":");
		su.append(this.getMaxCredit());
		return su.toString();
	}
	
	public abstract int getMaxCredit();
	
	public abstract int calculateRemainingCredit();
	
	public abstract int resetCredit();
	
	//a method that to check that they have enough credit or not.
	public boolean checkAllowedCreditOverdraw(int loanFee){
		if (credit >= loanFee){
			System.out.println("you current credit:" + credit);
			return true;
		}else{
			System.out.println("you current credit:" + credit);
			System.out.println("Your credit is not enough");
			return false;
		}
		
	}
	
	public abstract boolean borrowHolding(Holding holding);
	
	/*
	 * Return Holding Function
	 * 1. check the status, if the status is true (this means that the book can be borrowed).
	 * 2. then check the book is on loan or not.
	 * 3. get the return date, and make sure that the date is older than borrow date.
	 * 4. check that they have enough credit or it is a Premium Member.
	 * 5. calculate the late fee (if return book or video late).
	 * 6. if the return date is early than borrow date, tell user that input valid date.
	 */
	public boolean returnHolding(Holding holding, DateTime returnDate){
		if(this.status && holding.isOnLoan()){
			holding.returnHolding(returnDate);
			if(holding.dateFlag){
				if (memberID.charAt(0) == 'p' || (credit >= holding.getTotalFee() && memberID.charAt(0) == 's')){
					credit = credit - holding.getTotalFee();
					holding.setOnLoan(false);
					System.out.println("your credit remain:" + credit);
					//if return successful, set the check return is true.
					checkReturn = true;
					System.out.println("Return successful!");
					return true;
				}else{
					System.out.println("Return failed. You do not have enough credit, and you also is not a Premium Member!");
					checkReturn = false;
					return false;
				}
			}else{
				System.out.println("Please enter a valid date!");
				checkReturn = false;
				return false;
			}
		}else{
			checkReturn = false;
			return false;
		}
	}
	
	/*
	 * a function that push the data to Holding. and also use exception to throw the error message.
	 */
	public void pushToHoldings(Holding holding) throws FullException{
		if(this.currentHoldingNum < Member.MAX_HOLDING){
			this.holdings[this.currentHoldingNum - 1] = holding;
			this.currentHoldingNum++;
		}else{
			throw new FullException("Can't borrow any more");
		}
	}
	
	//the function to get current holdings.
	public Holding[] getCurrentHoldings(){
		return this.holdings;
	}

}

