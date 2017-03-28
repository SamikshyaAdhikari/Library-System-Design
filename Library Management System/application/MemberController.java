package application;

import java.io.IOException;

import project.business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MemberController {
	//Added by Samikshya
		@FXML
		private TextField txtMemberId;
		
		@FXML
		private TextField txtFirstName;
		
		@FXML
		private TextField txtLastName;
		
		@FXML
		private TextField txtStreet;
		
		@FXML
		private TextField txtCity;
		
		@FXML
		private TextField txtState;
		
		@FXML
		private TextField txtZip;
		
		@FXML
		private TextField txtPhoneNo;
		
		@FXML
		private Label lblMessage;
		  @FXML
		    private Button btnSubmit;
		

		    @FXML
		    private Button btnReset;
		    
		    @FXML
		    private Button btnCancel;
		    
		public void btnSubmitButtonClicked(ActionEvent event) throws IOException {
			if(txtMemberId.getText().equals("")){
				lblMessage.setText("Please enter memberID");
			}
			else if(txtFirstName.getText().equals("")){
				lblMessage.setText("Please enter firstName");
			}
			else if(txtLastName.getText().equals("")){
				lblMessage.setText("Please enter lastName");
			}
			else if(txtStreet.getText().equals("")){
				lblMessage.setText("Please enter street");
			}
			else if(txtCity.getText().equals("")){
				lblMessage.setText("Please enter city");
			}
			else if(txtState.getText().equals("")){
				lblMessage.setText("Please enter state");
			}
			else if(txtZip.getText().equals("")){
				lblMessage.setText("Please enter zip");
			}
			else if(txtPhoneNo.getText().equals("")){
				lblMessage.setText("Please enter phoneno");
			}
			else {
				new SystemController().addNewMember(txtMemberId.getText(),txtFirstName.getText(),txtLastName.getText()
						,txtStreet.getText(),txtCity.getText(),txtState.getText(),txtZip.getText(),txtPhoneNo.getText());
				reset();
				lblMessage.setText("Saved Successfully");
			
			}
			lblMessage.setVisible(true);
		}

		public void btnResetButtonClicked(ActionEvent event) throws IOException {
			reset();

		}

		public void btnExitButtonClicked(ActionEvent event) throws IOException {
			 // get a handle to the stage
		    Stage stage = (Stage) btnCancel.getScene().getWindow();
		    // do what you have to do
		    stage.close();
		}

	public void reset(){
			txtMemberId.setText("");
			txtFirstName.setText("");
			txtLastName.setText("");
			txtMemberId.setText("");
			txtPhoneNo.setText("");
			txtState.setText("");
			txtStreet.setText("");
			txtZip.setText("");
			txtCity.setText("");
		}
}
