package project.business;

import java.util.List;


import project.dataaccess.DataAccess;
import project.dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException;
	public boolean addBookCopy(String isbn) throws LibrarySystemException;
	public Book searchBook(String isbn);
	public void login(String id, String password) throws LoginException;
	
	public void addNewMember(String f,String l,String s,String c, String st,String z,String t,String ID);
	
	public LibraryMember searchMember(String memeberID);//selfadded method
}
