package application;

import project.business.*;

import java.io.IOException;

import project.business.LibrarySystemException;
import project.business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCopyController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtBookISBN;
    
    @FXML 
    private Label lblMessage;
    
    public void btnSubmitButtonClicked(ActionEvent event) throws IOException, LibrarySystemException {
    	try {
    		System.out.println(txtBookISBN.getText());
    		SystemController sc=new SystemController();
    		boolean check=sc.addBookCopy(txtBookISBN.getText());
    		if(check==true){
    			Book book=sc.searchBook(txtBookISBN.getText());
    			lblMessage.setText("Copy Added. Total book copy: " + book.getNumCopies());
    		}    		
		} catch (LibrarySystemException e) {
			lblMessage.setText(e.getMessage());
		}
    	
    }
    
    public void btnExitButtonClicked(ActionEvent event) throws IOException {
		// get a handle to the stage
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		// do what you have to do
		stage.close();
	}


}

