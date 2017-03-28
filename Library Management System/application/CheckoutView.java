package application;

import project.business.CheckoutRecordEntry;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CheckoutView {

	private SimpleStringProperty dueDate;
	private SimpleStringProperty checkOutDate;
	private SimpleStringProperty bookCopyNo;
	private SimpleStringProperty isbn;
	private SimpleStringProperty mid;
	
	
	public CheckoutView(CheckoutRecordEntry  cre,String mid) {
		dueDate=new SimpleStringProperty(cre.getDue().toString());
		checkOutDate=new SimpleStringProperty(cre.getCurrent().toString());
		bookCopyNo=new SimpleStringProperty(Integer.toString(cre.getBc().getCopyNum()));
		isbn=new SimpleStringProperty(cre.getBc().getBook().getIsbn());
		this.mid=new SimpleStringProperty(mid);
	}

	public String getDueDate(){
		return dueDate.get();
	}
	public String getCheckOutDate(){
		return checkOutDate.get();
	}
	public String getBookCopyNo(){
		return bookCopyNo.get();
	}
	public String getIsbn(){
		return isbn.get();
	}
	public String getMid(){
		return mid.get();
	}



	@Override
	public String toString() {
		return "CheckOut Records [dueDate=" + dueDate + ", checkOutDate=" + checkOutDate + ", bookCopyNo=" + bookCopyNo
				+ ", isbn=" + isbn + "]";
	}
}
