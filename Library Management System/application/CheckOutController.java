package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import project.business.Book;
import project.business.BookCopy;
import project.business.CheckoutRecord;
import project.business.CheckoutRecordEntry;
import project.business.LibraryMember;
import project.business.LibrarySystemException;
import project.business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



public class CheckOutController {

	@FXML
	private TextField txtMemberID;

	@FXML
	private Button btnCancel;

	@FXML
	private TableColumn<CheckoutView, String> MemberID;

	@FXML
	private Label lblMessage;

	@FXML
	private TableColumn<CheckoutView, String> colDueDate;

	@FXML
	private TableColumn<CheckoutView, String> ISBN;

	@FXML
	private TableView<CheckoutView> tblCheckout;

	@FXML
	private TableColumn<CheckoutView, String> colCheckoutDate;

	@FXML
	private TableColumn<CheckoutView, String> colCopyNumber;

	@FXML
	private Button btnSubmit;

	@FXML
	private TextField txtBookISBN;

	ArrayList<CheckoutView> list;
	@FXML
	void btnSubmitButtonClicked(ActionEvent event) throws IOException {
		if (txtMemberID.getText().isEmpty()) {
			lblMessage.setText("You have to enter member id!");
		} else {
			try {
				// System.out.println(txtMemberID.getText());
				// System.out.println(txtBookISBN.getText());
				SystemController sc = new SystemController();
				sc.checkoutBook(txtMemberID.getText(), txtBookISBN.getText());
				
				LibraryMember l = sc.searchMember(txtMemberID.getText());
				System.out.println("checkOUt "+l);
				//TableView<CheckoutView> table = getTable();
				MemberID.setCellValueFactory(new PropertyValueFactory<CheckoutView, String>(
						"mid"));
				colDueDate.setCellValueFactory(new PropertyValueFactory<CheckoutView, String>(
						"dueDate"));
				colCheckoutDate
						.setCellValueFactory(new PropertyValueFactory<CheckoutView, String>(
								"checkOutDate"));
				colCopyNumber
						.setCellValueFactory(new PropertyValueFactory<CheckoutView, String>(
								"bookCopyNo"));
				ISBN.setCellValueFactory(new PropertyValueFactory<CheckoutView, String>(
						"isbn"));
				tblCheckout.setItems(getData(l));
//				table.setLayoutX(100);
//				table.setLayoutY(200);
//				table.setPrefSize(550, 200);
				lblMessage.setText("Checkout Successful!!");
				//txtMemberID.setText("");

			} catch (LibrarySystemException e) {
				 lblMessage.setText(e.getMessage());
				//System.out.println(e.getMessage());
			}
		}
	}

	// final ObservableList<>
	@SuppressWarnings("unchecked")
	public TableView<CheckoutView> getTable() {
		//tblCheckout = new TableView<CheckoutView>();

		/*MemberID=new TableColumn<>("MemberID");
		colDueDate = new TableColumn<>("DueDate");
		colCheckoutDate = new TableColumn<>("CheckOutDate");
		colCopyNumber = new TableColumn<>("BookCopyNo");
		ISBN = new TableColumn<>("ISBN");*/

//		MemberID.setCellValueFactory(new PropertyValueFactory<CheckoutView, String>(
//				"MemberID"));
		/*colDueDate.setCellValueFactory(new PropertyValueFactory<CheckoutView, String>(
				"dueDate"));
		colCheckoutDate
				.setCellValueFactory(new PropertyValueFactory<CheckoutView, String>(
						"checkOutDate"));
		colCopyNumber
				.setCellValueFactory(new PropertyValueFactory<CheckoutView, String>(
						"bookCopyNo"));
		ISBN.setCellValueFactory(new PropertyValueFactory<CheckoutView, String>(
				"isbn"));*/

		//tblCheckout.getColumns().addAll(colDueDate, colCheckoutDate, colCopyNumber, ISBN);

		return tblCheckout;
	}

	private final ObservableList<CheckoutView> getData(LibraryMember l) {
		ArrayList<CheckoutRecordEntry> cr = l.getCheckoutrecord().getList();
		list = new ArrayList<>();		
		for (CheckoutRecordEntry cre : cr) {
			list.add(new CheckoutView(cre,txtMemberID.getText()));
			
		}
		System.out.println(list);
		ObservableList<CheckoutView> checkout = FXCollections
				.observableArrayList(list);
		return checkout;
	}


	public void btnExitButtonClicked(ActionEvent event) throws IOException {
		// get a handle to the stage
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		// do what you have to do
		stage.close();
	}
}
