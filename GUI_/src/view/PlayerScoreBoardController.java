package view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.UserRecord;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class PlayerScoreBoardController implements Initializable
{
	// Local Variables
	private ObservableList<UserRecord> data;
	private MainWindowController mainWindow;
	private int userId;

	@FXML
	private Button closeButton;

	@FXML
	private TableView<UserRecord> table;

	@FXML
	private TableColumn<UserRecord, String> levelName;

	@FXML
	private TableColumn<UserRecord, Integer> userSteps;

	@FXML
	private TableColumn<UserRecord, Integer> userTime;

	// C'tor
	public PlayerScoreBoardController(MainWindowController mainwindow)
	{
		this.mainWindow = mainwindow;
		table = new TableView<UserRecord>();
		levelName = new TableColumn<UserRecord,String>();	
		userSteps = new TableColumn<UserRecord,Integer>();	
		userTime = new TableColumn<UserRecord,Integer>();	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		levelName.setCellValueFactory(new PropertyValueFactory<UserRecord,String>("LevelName"));
		userSteps.setCellValueFactory(new PropertyValueFactory<UserRecord,Integer>("Steps"));
		userTime.setCellValueFactory(new PropertyValueFactory<UserRecord,Integer>("Time"));
	}

	// Function "setScores" - set Table's data
	public void setScores(List<UserRecord> pr)
	{
		if(pr != null)
		{
			table.setEditable(true);
			data = FXCollections.observableList(pr);
			table.setItems(data);
			table.getColumns().clear();
			table.getColumns().add(levelName);
			table.getColumns().add(userSteps);
			table.getColumns().add(userTime);
		}
	}
	
	
	public void sortBySteps()
	{
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	mainWindow.sortUserRecordBySteps(getUserId());
		    }
		});
	}
	
	public void sortByTime()
	{
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	mainWindow.sortUserRecordByTime(getUserId());
		    }
		});
	}

	public void sortByLexicalOrder()
	{
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	mainWindow.sortUserRecordByLexicalOrder(getUserId());
		    }
		});
	}
	
	// Function "closeWindow" - hide control's stage
	public void closeWindow()
	{
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.hide();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
