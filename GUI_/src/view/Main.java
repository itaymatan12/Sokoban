package view;
	
import controller.MyController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * the Main class run's the whole game and here we also doing the connections of the Observer pattern
 * Controller =new Controller(Model,View);
 * Model.addObserver(Controller);
 * View.addObserver(Controller);
 *
 */

public class Main extends Application
{
	
	@Override
	public void start(Stage primaryStage)
	{
		//https://github.com/itaymatan12/Sokobanfinal.git
		
		try {
				//loading the fxml 

				FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
				BorderPane root = (BorderPane)loader.load();//border pane display
				MainWindowController view =loader.getController();
				Scene scene = new Scene(root,500,500);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				
				
				//closing from the X button
				primaryStage.setOnCloseRequest(e ->
				{
					e.consume();
					//Alert alert = new Alert(AlertType.CONFIRMATION);
					//alert.setTitle("Confirmation Dialog");
					//alert.setHeaderText(null);
					//alert.setContentText("Are you sure you want to exit?");

					//Optional<ButtonType> result = alert.showAndWait();
					//if (result.get() == ButtonType.OK)
						view.exit();
				});
				
				//binding the view, controller and model
				init(view);
			
				primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//function that connect view,controller and model
	public void init(MainWindowController view)
	{
		MyModel model = new MyModel();
		MyController controller = new MyController(model,view);
		
		model.addObserver(controller);
		view.addObserver(controller);
		
	}
	
	//lunching the GUI as a thread
	public static void main(String[] args) {
		launch(args);
	}
}