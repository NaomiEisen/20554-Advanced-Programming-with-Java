import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FourInARow Class.
 * Launches the 'Four in a Row' game program.
 */
public class FourInARow extends Application {
	@Override
	public void start(Stage stage) throws Exception{ 
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("FourInARow.fxml")); 
		Scene scene = new Scene(root); 
		stage.setTitle("Four In A Row"); 
		stage.setScene(scene); 
		stage.show(); 
	} 
	
	/**
	 * main method to run the program
	 */
	public static void main(String[] args) { 
		launch(args); 
	} 
} // end class FourInARow
