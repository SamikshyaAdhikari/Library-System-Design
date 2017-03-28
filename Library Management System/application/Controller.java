package application;

import java.io.IOException;

import project.business.LoginException;
import project.business.SystemController;
import project.dataaccess.Auth;
import project.dataaccess.TestData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FadeTransition;

public class Controller {
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLogOut;
	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtPassword;

	@FXML
	private Label lblStatus;

	@FXML
	private Button btnAddMember;

	@FXML
	private Button btnCheckOut;

	@FXML
	private Button btnAddBookCopy;

	@FXML
	public Button btnExit;

	@FXML
	private VBox dataPane;

	@FXML
	public void btnLoginButtonClicked(ActionEvent event) throws IOException,
			LoginException {
		try {
			
			SystemController sc = new SystemController();
			if (txtUsername.getText().equals("")
					&& txtPassword.getText().equals("")) {
				lblStatus.setText("Enter Username and Password");
				lblStatus.setVisible(true);
			} else {
				sc.login(txtUsername.getText(), txtPassword.getText());			
				if (sc.currentAuth == Auth.BOTH) {	
					Parent home_page_parent = FXMLLoader.load(getClass()
							.getResource("BothForm.fxml"));					
					Scene scene = new Scene(home_page_parent);					
					scene.setRoot(home_page_parent);
					Stage app_stage = (Stage) ((Node) event.getSource()).getScene()
							.getWindow();
					app_stage.setTitle("Loged On As Both");		
					scene.getStylesheets( ).add(getClass().getResource("Login.css").toExternalForm());
					app_stage.hide();					
					app_stage.setScene(scene);					
					app_stage.show();					
				}
				if (sc.currentAuth == Auth.LIBRARIAN) {
					Parent home_page_parent1 = FXMLLoader.load(getClass()
							.getResource("LibrerianHome.fxml"));					
					Scene scene = new Scene(home_page_parent1);					
					scene.setRoot(home_page_parent1);
					Stage app_stage1 = (Stage) ((Node) event.getSource()).getScene()
							.getWindow();
					app_stage1.setTitle("Loged On As Laibrerian");	
					scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
					app_stage1.hide();					
					app_stage1.setScene(scene);					
					app_stage1.show();					
				}
				if (sc.currentAuth == Auth.ADMIN) {				
					Parent home_page_parent2 = FXMLLoader.load(getClass()
							.getResource("AdminHome.fxml"));					
					Scene scene = new Scene(home_page_parent2);					
					scene.setRoot(home_page_parent2);
					Stage app_stage2 = (Stage) ((Node) event.getSource()).getScene()
							.getWindow();
					app_stage2.setTitle("LOged On As Admin");	
					scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
					app_stage2.hide();					
					app_stage2.setScene(scene);					
					app_stage2.show();
					
				}
				
			}
		} catch (LoginException e) {
			lblStatus.setText(e.getMessage());
			lblStatus.setVisible(true);

		}

	}


	public void btnLogOutButtonClicked(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource(
				"Login.fxml"));
		Scene scene = new Scene(home_page_parent);
		scene.setRoot(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene()
				.getWindow();
		app_stage.hide();
		app_stage.setScene(scene);
		app_stage.show();

	}

	public void btnExitButtonClicked(ActionEvent event) throws IOException {

		// get a handle to the stage
		Stage stage = (Stage) btnExit.getScene().getWindow();
		// do what you have to do
		stage.close();

	}

	public void btnCancelClicked(ActionEvent event) throws IOException {

		// get a handle to the stage
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		// do what you have to do
		stage.close();

	}
	
	 @FXML
	    void btnbtnCheckOutClicked(ActionEvent event) throws IOException {
		 Parent home_page_parent2 = FXMLLoader.load(getClass()
					.getResource("Chechout.fxml"));					
			Scene scene = new Scene(home_page_parent2);					
			scene.setRoot(home_page_parent2);
			Stage app_stage2 = (Stage) ((Node) event.getSource()).getScene()
					.getWindow();
			app_stage2.setTitle("Check Out");	
			scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			app_stage2.hide();					
			app_stage2.setScene(scene);					
			app_stage2.show();
	    }
	  @FXML
	    void btnAddMemberButtonClicked(ActionEvent event) throws IOException {
		  Parent home_page_parent2 = FXMLLoader.load(getClass()
					.getResource("AddMember.fxml"));					
			Scene scene = new Scene(home_page_parent2);					
			scene.setRoot(home_page_parent2);
			Stage app_stage2 = (Stage) ((Node) event.getSource()).getScene()
					.getWindow();
			app_stage2.setTitle("Add Member");	
			scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			app_stage2.hide();					
			app_stage2.setScene(scene);					
			app_stage2.show();
	    }
	  
	  @FXML
	    void btnAddCopyButtonClicked(ActionEvent event) throws IOException {
		  Parent home_page_parent2 = FXMLLoader.load(getClass()
					.getResource("BookCopy.fxml"));					
			Scene scene = new Scene(home_page_parent2);					
			scene.setRoot(home_page_parent2);
			Stage app_stage2 = (Stage) ((Node) event.getSource()).getScene()
					.getWindow();
			app_stage2.setTitle("Add Member");	
			scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			app_stage2.hide();					
			app_stage2.setScene(scene);					
			app_stage2.show();
	    }


}
