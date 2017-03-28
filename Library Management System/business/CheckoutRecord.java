package project.business;

import java.io.Serializable;
import java.util.*;


public class CheckoutRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CheckoutRecordEntry> list;
	public CheckoutRecord(){ 
		list = new ArrayList<CheckoutRecordEntry>();
	}
	public void addToCheckoutRecord(CheckoutRecordEntry obj){
		list.add(obj);
	}
	public ArrayList<CheckoutRecordEntry> getList() {
		return list;
	}
	
	
}
