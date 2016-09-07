package lms.model.entity;

public class PremiumMember extends Member{
	//variables for Premium Member.
	private String premimumMemberId;
	private String premiumMemberName;
	private static int credit = 45;
	private boolean status = true;
	private int loanFee = 0;
	

	public PremiumMember(String premimumMemberId, String premiumMemberName){
		super(premimumMemberId, premiumMemberName,credit);
	}
	
	//a method to get the max credit.
	@Override
	public int getMaxCredit() {
		// TODO Auto-generated method stub
		return this.credit;
	}
	
	//a method to calculate remain credit.
	@Override
	public int calculateRemainingCredit() {
		// TODO Auto-generated method stub
		if (credit >= loanFee){
			credit = credit - loanFee;
			System.out.println("You borrow succeed, and your credit remain:" + credit);
			return credit;
		}else{
			System.out.println("You do not have enough credit!");
			return credit;
		}
	}
	
	//a method to reset credit.
	@Override
	public int resetCredit() {
		// TODO Auto-generated method stub
		return 45;
	}
	
	/*
	 * A method for borrow holding.
	 * when a user borrow a book, it will check that the book is activity or not.
	 * and then check that the user has enough credit or not, and also check that the book or video is on loan or not.
	 * if these all are true, then, take the loan fee from the credit.
	 * (for example, if user borrow a book, it will minus 10 credit.)
	 * and then, tell user that their credit remain.
	 */
	@Override
	public boolean borrowHolding(Holding holding){
		if(this.status && checkAllowedCreditOverdraw(holding.getLoanFee()) && !holding.isOnLoan()){
			super.credit = super.credit - holding.getLoanFee();
			holding.setOnLoan(true);
			System.out.println("After borrow, your credit remain:" + super.credit);
			return true;
		}else{
			return false;
		}
	}

}
