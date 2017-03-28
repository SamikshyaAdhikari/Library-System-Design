package project.business;

import java.time.LocalDate;
import java.util.HashMap;





import project.dataaccess.Auth;
import project.dataaccess.DataAccess;
import project.dataaccess.DataAccessFacade;
import project.dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

	@Override
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password does not match password on record");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	
	public LibraryMember searchMember(String memeberID){
		DataAccess da = new DataAccessFacade();
		return da.searchMember(memeberID);
		
	}
	
	
	@Override
	public Book searchBook(String isbn) {
		DataAccess da = new DataAccessFacade();
		return da.searchBook(isbn);
	}
		
	
	public boolean addBookCopy(String isbn) throws LibrarySystemException {
		Book book = searchBook(isbn);
		if(book == null) throw new LibrarySystemException("No book with isbn " + isbn 
			+ " is in the library collection!");
		book.addCopy();
		
		DataAccess da = new DataAccessFacade();
		da.saveNewBook(book);
		return true;
	}
	
	@Override
	public void checkoutBook(String memberId, String isbn)
			throws LibrarySystemException {
		LibraryMember m = searchMember(memberId);
		if(m==null){
			throw new LibrarySystemException ("there is no such member");
		}
		Book b = searchBook(isbn);
		BookCopy bCopy;
		if(b.isAvailable()==true){
			bCopy = b.getNextAvailableCopy();
		}
		else {
			throw new LibrarySystemException ("item is not available");
		}
		LocalDate duedate = b.getDueDate();
		m.checkOut(bCopy, LocalDate.now(), duedate);
		DataAccess da = new DataAccessFacade();
		da.updateMember(m);
		da.updateBook(b);
	}
	
	@Override
	public void addNewMember(String ID,String f,String l,String s,String c, String st,String z,String t){
		System.out.println(ID);
		DataAccess da = new DataAccessFacade();
		LibraryMember member = new LibraryMember(ID,f,l,new Address( s,  c,  st,  z),t);
		da.saveNewMember(member);
	}
	
	
}
