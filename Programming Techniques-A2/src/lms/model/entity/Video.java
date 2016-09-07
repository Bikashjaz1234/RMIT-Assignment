package lms.model.entity;

import lms.model.util.DateTime;

public class Video extends Holding
{
	//set variables for video.
	private int maxLoanPeriod = 7;
	private int loanPeriod = 0;
	private int lateFee = 0;
	public Video(String holdingId, String title, int loanFee) {
		super(holdingId, title);
		this.loanFee = loanFee;
	}
	
	//method for get the max loan period.
	@Override
	public int getMaxLoanPeriod() {
		// TODO Auto-generated method stub
		return this.maxLoanPeriod;
	}
	
	//method for get the default loan fee
	@Override
	public int getDefaultLoanFee() {
		// TODO Auto-generated method stub
		return this.loanFee;
	}
	
	/*
	 *The function of calculateLateFee. 
	 *1. calculate how much day that they late (loanPeriod).
	 *2. if the day (loanPeriod) more than 7 days (maxLoanPeriod), calculate the fee of late.
	 *3. else return 0;
	 */
	@Override
	public int calculateLateFee(DateTime dateReturned) {
		loanPeriod = DateTime.diffDays(dateReturned, this.getBorrowDate());
		System.out.println("The video loanTime is :" + loanPeriod);
		if (loanPeriod > this.maxLoanPeriod){
			lateFee = (int) (loanFee * 0.5 * (loanPeriod - maxLoanPeriod));
		}else{
			lateFee = 0;
		}
		return lateFee;
	}
	
	//a method for get loan period.
	@Override
	public int getLoanPeriod() {
		// TODO Auto-generated method stub
		return this.loanPeriod;
	}


	
}