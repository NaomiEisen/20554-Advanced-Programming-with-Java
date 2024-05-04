import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class Painter.
 * Activates the Painter application.
 */
public class Painter extends Application{ 
	@Override
	public void start(Stage stage) throws Exception{ 
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("Painter.fxml")); 
		Scene scene = new Scene(root); 
		stage.setTitle("Painter :)"); 
		stage.setScene(scene); 
		stage.show(); 
	} 
	
	/**
	 * main method to run the program
	 */
	public static void main(String[] args) { 
		launch(args); 
	} 
} // end class Painter
