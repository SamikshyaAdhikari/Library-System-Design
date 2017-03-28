package project.business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookCopy bc;
	private LocalDate current;
	private LocalDate due;
	
	public BookCopy getBc() {
		return bc;
	}

	public LocalDate getCurrent() {
		return current;
	}

	public LocalDate getDue() {
		return due;
	}

	public CheckoutRecordEntry(){}
	
	public CheckoutRecordEntry(BookCopy bc, LocalDate current, LocalDate due){
		this.bc= bc;
		this.current=current;
		this.due=due;
	}
	public CheckoutRecordEntry creatCheckoutRecordEntry(BookCopy b, LocalDate d, LocalDate duedate){
		return new CheckoutRecordEntry(b,d,duedate);		
	}
}
