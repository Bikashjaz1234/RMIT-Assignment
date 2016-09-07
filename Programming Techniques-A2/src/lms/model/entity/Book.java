package lms.model.entity;

import lms.model.util.DateTime;

public class Book extends Holding
{
	//the variables of Book.
	private int maxLoanPeriod = 28;
	private int loanPeriod = 0;
	private int lateFee = 0;
	private int lateFeeRate = 2;
	public Book(String holdingId, String title) {
		super(holdingId, title);
		loanFee = 10;
	}

	/*
	 *The function of calculateLateFee. 
	 *1. calculate how much day that they late (loanPeriod).
	 *2. if the day (loanPeriod) more than 28 days (maxLoanPeriod), calculate the fee of late.
	 *3. else return 0;
	 */
	@Override
	public int calculateLateFee(DateTime dateReturned) {
		loanPeriod = DateTime.diffDays(dateReturned, this.getBorrowDate());
		//System.out.println("The book loanTime is :" + loanPeriod);
		if (loanPeriod > this.maxLoanPeriod){
			lateFee = lateFeeRate * (loanPeriod - maxLoanPeriod);
		}else{
			lateFee = 0;
		}
		return lateFee;
	}
	

	@Override
	public int getMaxLoanPeriod() {
		// TODO Auto-generated method stub
		return this.maxLoanPeriod;
	}

	@Override
	public int getDefaultLoanFee() {
		// TODO Auto-generated method stub
		return this.loanFee;
	}

	@Override
	public int getLoanPeriod() {
		// TODO Auto-generated method stub
		return this.loanPeriod;
	}
	
}