package project.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 */
final public class Book implements Serializable {
	
	private static final long serialVersionUID = 6110690276685962829L;
	private BookCopy[] copies;
	private List<Author> authors;
	private String isbn;
	private String title;
	private int maxCheckoutLength;
	
	
	public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		this.authors = Collections.unmodifiableList(authors);
		copies = new BookCopy[]{new BookCopy(this, 1, true)};
		
	}
	
	public void updateCopies(BookCopy copy) {
		for(int i = 0; i < copies.length; ++i) {
			BookCopy c = copies[i];
			if(c.equals(copy)) {
				copies[i] = copy;
				
			}
		}
	}

	
	public List<Integer> getCopyNums() {
		List<Integer> retVal = new ArrayList<>();
		for(BookCopy c : copies) {
			retVal.add(c.getCopyNum());
		}
		return retVal;
		
	}
	
	public void addCopy() {
		BookCopy[] newArr = new BookCopy[copies.length + 1];
		System.arraycopy(copies, 0, newArr, 0, copies.length);
		newArr[copies.length] = new BookCopy(this, copies.length +1, true);
		copies = newArr;
	}
	
	
	@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(ob.getClass() != getClass()) return false;
		Book b = (Book)ob;
		return b.isbn.equals(isbn);
	}
	
	
	public boolean isAvailable() {
		if(copies == null) {
			return false;
		}
		return Arrays.stream(copies)
				     .map(l -> l.isAvailable())
				     .reduce(false, (x,y) -> x || y);
	}
	@Override
	public String toString() {
		return "isbn: " + isbn + ", maxLength: " + maxCheckoutLength + ", available: " + isAvailable();
	}
	
	public int getNumCopies() {
		return copies.length;
	}
	
	public String getTitle() {
		return title;
	}
	public BookCopy[] getCopies() {
		return copies;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public BookCopy getNextAvailableCopy() {	
		Optional<BookCopy> optional 
			= Arrays.stream(copies)
			        .filter(x -> x.isAvailable()).findFirst();
		return optional.isPresent() ? optional.get() : null;
	}
	
	public BookCopy getCopy(int copyNum) {
		for(BookCopy c : copies) {
			if(copyNum == c.getCopyNum()) {
				return c;
			}
		}
		return null;
	}
	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}
	
	public LocalDate getDueDate(){ //self added method//sammy
		LocalDate todayDate = LocalDate.now();
		System.out.println("Today's Date:"+todayDate);
		int month = todayDate.getMonthValue();
		int year = todayDate.getYear();
		
	    int day = todayDate.getDayOfMonth();
	    YearMonth ym = YearMonth.of(year, month);
    	int monthEndDate= ym.lengthOfMonth();
    	int daysNextMonth = (day+getMaxCheckoutLength())-monthEndDate;
    	
    	if(daysNextMonth < 0){ // returns if due date is within this month 
    		day = day+getMaxCheckoutLength();
    	    return LocalDate.of(year, month, day);
    	}
    	else{
    		month+=1;
    	}
    	int newMonth = month + 1; // updating month to check number of days on it 
    	if(newMonth > 12){
    		year +=1;
    		newMonth = 1;
    		ym = YearMonth.of(year, newMonth);
    	}
    	monthEndDate = ym.lengthOfMonth();
    	
    	while(daysNextMonth > monthEndDate){ // iterates over as long as due date is not less than 
    										// number of days in a given month	
    		month += 1;
    		if (month >12){ // month can't be greater than 12
    			year += 1;
    			month = 1;
    		}
    		
    		newMonth = month + 1; // now looking into number of days in the next month to come
    		if(newMonth>12){ // month can't be greater than 12
    			newMonth = 1;
    			int newYear = year+1 ;
    			ym = YearMonth.of(newYear, newMonth);
    		}
    		else{
    			ym = YearMonth.of(year, newMonth);
    		}
    		monthEndDate= ym.lengthOfMonth(); // end date of next month
    		int dueDay = daysNextMonth; // temporary variable to store the due date
     		daysNextMonth = daysNextMonth-monthEndDate;
     		if(daysNextMonth<0){ // we can safely break out if due date is within last date of the month
     			daysNextMonth = dueDay;
     			break;
     		}
    	}
    	day = daysNextMonth;
	    
	    return LocalDate.of(year, month, day);
	}
	
}
