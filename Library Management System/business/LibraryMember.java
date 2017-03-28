package project.business;

import java.io.Serializable;
import java.time.LocalDate;

public final class LibraryMember extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3195127107077682396L;
	private final String memberID;
	private CheckoutRecordEntry checkoutEntry;
	private CheckoutRecord checkoutrecord;
	
	public LibraryMember (String f, String l, String t, Address a, String ID){
		super(f, l, t, a);
		memberID = ID;
		checkoutrecord=new CheckoutRecord();
	}
	public String getMemberID() {
		return memberID;
	}
	
	public CheckoutRecordEntry getCheckoutEntry() {
		return checkoutEntry;
	}
	public void setCheckoutEntry(CheckoutRecordEntry checkoutEntry) {
		this.checkoutEntry = checkoutEntry;
	}
	public CheckoutRecord getCheckoutrecord() {
		return checkoutrecord;
	}
	public void setCheckoutrecord(CheckoutRecord checkoutrecord) {
		this.checkoutrecord = checkoutrecord;
	}
	public void checkOut(BookCopy b, LocalDate d, LocalDate duedate){ // self defined
		b.changeAvailability();
		checkoutEntry = new CheckoutRecordEntry();
		CheckoutRecordEntry ce = checkoutEntry.creatCheckoutRecordEntry(b, d, duedate);
		//checkoutrecord = new CheckoutRecord();
		checkoutrecord.addToCheckoutRecord(ce);
	}
	/*@Override{
	public String toString(){
		String str=memberID;
		return str;
	}*/
	//private static final long serialVersionUID = 42L;

}
