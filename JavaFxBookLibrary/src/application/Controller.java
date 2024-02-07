
package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Controller implements Initializable {

    @FXML
    private TextField tfPages;

    @FXML
    private TableColumn<Books, String> colAuthor;

    @FXML
    private TextField tfAuthor;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Books> tableView;

    @FXML
    private TableColumn<Books, Integer> colPages;

    @FXML
    private TableColumn<Books, String> colYear;

    @FXML
    private AnchorPane stage;

    @FXML
    private TextField tfTitle;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField tfYear;

    @FXML
    private TextField tfId;

    @FXML
    private TableColumn<Books, Integer> colId;

    @FXML
    private TableColumn<Books, String> colTitle;

    @FXML
    private Pane pane;
    

    @FXML
    void btnClear(ActionEvent event) {
    	
    	if(event.getSource() == btnClear) {
    	    tfId.setText("");
    	    tfAuthor.setText("");
    	    tfTitle.setText("");
    	    tfYear.setText("");
    	    tfPages.setText("");
    	}

    }
    @FXML
    private Button btnClear;


    @FXML
    void insertAction(ActionEvent event) {
    	
    	
    	if(event.getSource() == btnInsert) {
    		insertAlert();
    		insertRecord();
    		showBook();
    		blankField();
    		
    	}
    }
    
    public void blankField() {
    	if(tfId.getText().isEmpty()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setContentText("please fill all fields");
    		alert.setHeaderText("Error");
    		alert.setResizable(false);
    		alert.setTitle("Error");
    	}
    }
    
    public void insertAlert() {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setTitle("Successfull");
    	alert.setContentText("data added Successfull");
    	alert.setHeaderText("INSERT");
    	alert.showAndWait();
    	
    }
    
    public void deleteAlert() {
    	Alert alert = new Alert(Alert.AlertType.WARNING);
    	alert.setTitle(" Deletetion Successfull");
    	alert.setContentText("data deleted Successfull");
    	alert.setHeaderText("DELETE");
    	alert.showAndWait();
    	
    }
    
    public void updatetAlert() {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setTitle("Successfull");
    	alert.setContentText("data updated Successfull");
    	alert.setHeaderText("UPDATE");
    	alert.showAndWait();
    	
    }
    
    
    
    public void updateRecord() {
        String query =  "UPDATE books SET title = '"+ tfTitle.getText()+ "', author = '"+ tfAuthor.getText()+ "', year = '"+ tfYear.getText()+  "' , pages = "+ tfPages.getText()+ " WHERE id = " +tfId.getText() + " ";

         executeQuery(query);
         showBook();
         
       
    }

    @FXML
    void updateAction(ActionEvent event) {
           if(event.getSource() == btnUpdate) {
        	   updatetAlert();
        	   updateRecord();
        	   showBook();
           }
    	
    }

    @FXML
    void deleteAction(ActionEvent event) {
            if(event.getSource() == btnDelete) {
            	deleteAlert();
            	deleteRecord();
            	showBook();
            }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		showBook();
		
		
	}
	public void deleteRecord() {
		String query = " DELETE FROM books WHERE id = "+ tfId.getText()+"";
		executeQuery(query);
		showBook();
	}
	
	
	public void insertRecord() {
		String query = "INSERT INTO books VALUES("+tfId.getText()+ ",'"+ tfTitle.getText()+ "','"+tfAuthor.getText()+ "','"+tfYear.getText()+"',"+tfPages.getText()+"); ";

		executeQuery(query);
		showBook();
	}
	
	private void executeQuery(String query) {
		DbUtil connection = new DbUtil();
		Connection conn;
		conn = connection.getConnection();	
		Statement st;
		
		
		try {
			
			st = conn.createStatement();
			st.executeUpdate(query);
			
		}catch(Exception e) {
			System.out.println("Error inserting data to databse ! " + e.getMessage());
		}
		
	}

	public ObservableList<Books> getBookList(){
		
		ObservableList<Books> booklist = FXCollections.observableArrayList();
		
		DbUtil connection = new DbUtil();
		Connection conn;
		conn = connection.getConnection();	
		Statement st;
		ResultSet rs;
		String insert = "SELECT * FROM books";
		try {
			
			st = conn.createStatement();
			rs = st.executeQuery(insert);
			
			while(rs.next()) {
				
				Books book;
				book = new Books(rs.getInt("id"),rs.getString("title"),rs.getString("author"),rs.getString("year"),rs.getInt("pages"));
				
				booklist.add(book);
			}
			
		}catch(Exception e) {
			System.out.println("Error Retrieving data from database !" + e.getMessage());
		}
		return booklist;
		
		
	}
	public void showBook() {
		
		ObservableList<Books> list = getBookList();
		
		colId.setCellValueFactory(new PropertyValueFactory<Books,Integer>("id"));
		colTitle.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
		colAuthor.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
		colYear.setCellValueFactory(new PropertyValueFactory<Books,String>("year"));
		colPages.setCellValueFactory(new PropertyValueFactory<Books,Integer>("pages"));
		
		tableView.setItems(list);
	}


}
