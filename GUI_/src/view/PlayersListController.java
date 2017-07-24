package view;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import entities.DbManager;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PlayersListController implements Initializable
{
	// Local Variables
	private User myUser;
	private ObservableList<User> data;
	private boolean Getflag;
	private MainWindowController mainWindow;
	private DbManager manager;

	@FXML
	private Button closeButton;

	@FXML
	private TextField nameText;

	@FXML
	private TableColumn<User, Integer> userId;

	@FXML
	private TableView<User> table;
	
	
	// C'tor
	public PlayersListController(MainWindowController o)
	{
		this.manager = new DbManager();
		Getflag = false;
		this.mainWindow = o;
		table = new TableView<User>();
		userId = new TableColumn<User,Integer>();	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

		userId.setCellValueFactory(new PropertyValueFactory<User,Integer>("UserId"));

		// OnMouseClick - when use click on row
		table.setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>()
		{
			@Override
			public void handle(javafx.scene.input.MouseEvent event)
			{
				myUser = table.getSelectionModel().getSelectedItem();

				if(myUser != null)
				{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Hello");
					alert.setHeaderText(null);
					alert.setContentText("Hello " +myUser.getUserId() + "!");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK)
					{
						if(Getflag == true)
						{
							mainWindow.addUserScore();
							Getflag = false;
						}

						Stage stage = (Stage) closeButton.getScene().getWindow();
						stage.hide();
					}
				}
			}
		});
	}

	// Getters and Setters
	public User getAcount()
	{
		return myUser;
	}
	

	public void SetFlag(boolean flag)
	{
		this.Getflag = flag;
	}
	
	
	public void setUsersList(List<User> players)
	{
		table.setEditable(true);
		data = FXCollections.observableList(players);
		table.setItems(data);
		table.getColumns().clear();
		table.getColumns().add(userId);
	}


	// Function "closeWindow" - hide control's stage
	public void closeWindow()
	{
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.hide();
	}

	// Function "AddRow" - user wants to add a new Player
	public void AddRow()
	{
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Create new User");
		dialog.setHeaderText(null);
		dialog.setContentText("Enter your Id:");
		dialog.initStyle(StageStyle.UTILITY);

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
		{
			if(manager.UserisExist(Integer.parseInt(result.get())))	
			{
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("There is already User with this User Id ,please create a user with a different Id");
				alert.show();
			}
			else
			{
				mainWindow.addUser(Integer.parseInt(result.get()));
				mainWindow.getUsers();
			}
		}
	}

	public User getMyUser() {
		return myUser;
	}

	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}

	public ObservableList<User> getData() {
		return data;
	}

	public void setData(ObservableList<User> data) {
		this.data = data;
	}

	public TableColumn<User, Integer> getUserId() {
		return userId;
	}

	public void setUserId(TableColumn<User, Integer> userId) {
		this.userId = userId;
	}

	public TableView<User> getTable() {
		return table;
	}

	public void setTable(TableView<User> table) {
		this.table = table;
	}
}
