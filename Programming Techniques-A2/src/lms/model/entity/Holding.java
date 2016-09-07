package lms.model.entity;
import java.util.Scanner;
import lms.model.util.DateTime;

/*
 * a class for holding.
 * first create Holding for store holding information. Store ID and title.
 * also create multiple methods to make sure that other can use it.
 * 
 */
public abstract class Holding implements SystemOperations
{
	//The variables of Holding.
	protected String holdingId;
	private String title;
	private DateTime borrowDate;
	private boolean status;
	private boolean tempRm;
	private boolean onLoan;
	private int totalFee;
	private int lateFee;
	protected int loanFee;
	protected int credit;
	protected DateTime returnDate;
	protected int returnDay;
	protected int returnMonth;
	protected int returnYear;
	protected boolean dateFlag = true;


	public Holding(String holdingId, String title)
	{
		this.holdingId = holdingId;
		this.title = title;
	}

	public String getId() {
		return holdingId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getLoanFee(){
		return loanFee;
	}
	
	public int getTotalFee(){
		return totalFee;
	}

	
	public boolean getStatus(){
		return this.status;
	}

	public boolean isTempRm() {
		return tempRm;
	}

	//set the book or video temporary Remove (set it deactivate); 
	public void setTempRm(boolean tempRm) {
		this.tempRm = tempRm;
		deactivate();
	}
	
	public DateTime getBorrowDate() {
		borrowDate = new DateTime();
		return borrowDate;
	}
	
	public void setBorrowDate(DateTime borrowDate) {
		this.borrowDate = borrowDate;
	}

	public boolean isOnLoan() {
		return onLoan;
	}
	
	public void setOnLoan(boolean onLoan) {
		 this.onLoan = onLoan;
	}
	
	//a method of borrow Holding. if a user borrow a book or video, it set the borrow date to current date. and then, set the status is on loan.
	public boolean borrowHolding(){
		if (this.isOnLoan()){
			return false;
		}else{
			if (this.status){
				this.borrowDate = new DateTime();
				this.onLoan = true;
				return true;
			}else{
				return false;
			}
		}
	}
	/*
	 * a method for return holding.
	 * 1.get the return date.
	 * 2.get the default loan fee.
	 * 3.If return late, use the method of "calculateLateFee".
	 * 4.if does not return late, told user that late fee is 0.
	 */
	public boolean returnHolding(DateTime dateReturned){
		System.out.println("Return date is: " + dateReturned);
		System.out.println("borrow date is: " + borrowDate);
		int loanDays = DateTime.diffDays(dateReturned, borrowDate);
		System.out.println("loanDays is: " + loanDays);
		System.out.println("LoanPeriod is: " + getMaxLoanPeriod());
		if (loanDays >= 0){
			int loanFee = this.getDefaultLoanFee();
			if(loanDays > this.getMaxLoanPeriod()){
				lateFee = this.calculateLateFee(dateReturned);
				this.totalFee = lateFee;
				System.out.println("the lateFee is:" + lateFee);
				//System.out.println("total fee is:" + totalFee);
				return true;
			}else{
				this.totalFee = 0;
				this.onLoan = false;
				System.out.println("The late Fee is: 0");
				return true;
			}
		}else{
			dateFlag = false;
			return false;
		}
	}
		
	//print method of print all things.
	public void print(){
		System.out.println("ID:\t\t\t" + this.getId());
		System.out.println("Title:\t\t\t" + this.getTitle());
		System.out.println("Loan Fee:\t\t" + this.getDefaultLoanFee());
		System.out.println("Max Loan Period:\t" + this.getMaxLoanPeriod());
		System.out.println("");
	}
	
	public String toString(){
		StringBuffer sh = new StringBuffer();
		sh.append(this.getId());
		sh.append(":");
		sh.append(this.getTitle());
		sh.append(":");
		sh.append(this.getDefaultLoanFee());
		sh.append(":");
		sh.append(this.getMaxLoanPeriod());
		return sh.toString();
	}
	
	//activate method. if the book is on loan, return false, else set is true.
	public boolean activate(){
		if(this.isOnLoan()){
			return false;
		}else{
			this.status = true;
			return true;
		}
	}
	
	//activate method. if the book is on loan, return false, else set is false.
	public boolean deactivate(){
		if(this.isOnLoan()){
			return false;
		}else{
			this.status = false;
			return true;
		}
	}
	
	public abstract int getMaxLoanPeriod();
	
	public abstract int getLoanPeriod();
	
	public abstract int getDefaultLoanFee();

	public abstract int calculateLateFee(DateTime dateReturned);

	
}