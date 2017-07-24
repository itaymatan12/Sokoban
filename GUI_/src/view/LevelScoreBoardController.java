package view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.User;
import entities.UserRecord;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LevelScoreBoardController implements Initializable
{
	// Local Variables
	private MainWindowController mainWindow;
	private ObservableList<UserRecord> data;

	private FXMLLoader fxmlLoader;
	private Stage stagePlayerScore;

	@FXML
	PlayerScoreBoardController playerScore; // Use to show this Player's scores

	@FXML
	private Button closeButton;

	@FXML
	private TableView<UserRecord> table;

	@FXML
	private TableColumn<UserRecord, Integer> userId;

	@FXML
	private TableColumn<UserRecord, Integer> userSteps;

	@FXML
	private TableColumn<UserRecord, Integer> userTime;

	// C'tor
	public LevelScoreBoardController(MainWindowController mainWindow)
	{
		this.mainWindow = mainWindow;
		stagePlayerScore = null;
		playerScore = new PlayerScoreBoardController(mainWindow);
		table = new TableView<UserRecord>();
		userId = new TableColumn<UserRecord,Integer>();	
		userSteps = new TableColumn<UserRecord,Integer>();	
		userTime = new TableColumn<UserRecord,Integer>();	

		try
		{
			// LevelScoreBoard Window
			fxmlLoader = new FXMLLoader(getClass().getResource("PlayerScoreBoard.fxml"));
			fxmlLoader.setController(playerScore);
			Parent root8 = (Parent) fxmlLoader.load();
			stagePlayerScore = new Stage();
			stagePlayerScore.initStyle(StageStyle.UTILITY);
			stagePlayerScore.setResizable(false);
			stagePlayerScore.setScene(new Scene(root8));
		}

		catch(Exception e)
		{
			mainWindow.displayMessage(e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		userId.setCellValueFactory(new PropertyValueFactory<UserRecord,Integer>("UserId"));
		userSteps.setCellValueFactory(new PropertyValueFactory<UserRecord,Integer>("Steps"));
		userTime.setCellValueFactory(new PropertyValueFactory<UserRecord,Integer>("Time"));

		// OnMouseClick - when use click on row
		table.setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>()
		{
			@Override
			public void handle(javafx.scene.input.MouseEvent event)
			{
				UserRecord p = table.getSelectionModel().getSelectedItem();
				playerScore.setUserId(p.getUserId());

				mainWindow.getChosenPlayerScores(p.getUserId());

				if(stagePlayerScore != null)
					stagePlayerScore.show();
			}
		});
	}

	// Function "setScores" - set Table's data
	public void setScores(List<UserRecord> pr)
	{
		System.out.println("85");
		if(pr != null)
		{
			
			System.out.println("86");
			table.setEditable(true);
			data = FXCollections.observableList(pr);
			table.setItems(data);
			table.getColumns().clear();
			table.getColumns().add(userId);
			table.getColumns().add(userSteps);
			table.getColumns().add(userTime);
		}
	}

	// Function "showPlayerScores" - open PlayerScoreTable
	public void showPlayerScores()
	{
		if(stagePlayerScore != null)
			stagePlayerScore.show();
	}

	// Function "setPlayerScores" - set Chosen Player Table's data
	public void setPlayerScores(List<UserRecord> pr)
	{
		if(pr != null && playerScore!= null)
		{
			playerScore.setScores(pr);
		}
	}

	// Function "SortBySteps" - sort Level's scores by steps
	public void SortBySteps()
	{
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	mainWindow.sortLevelScoresBySteps();
		    }
		});
	}

	// Function "SortByTime" - sort Level's scores by time
	public void SortByTime()
	{
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
				mainWindow.sortLevelScoresByTime();
		    }
		});

	}

	// Function "closeWindow" - hide control's stage
	public void closeWindow()
	{
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.hide();
	}
}
