import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * Controller class for managing the graphical user interface and handling the methods 
 * of the FXML objects within the add mode scene.
 * This scene is displayed when the user inputs a new word into the dictionary.
 */
public class DictionaryAddSceneController {
	// scene controller object for switching scenes
	private SceneController scene = new SceneController();
	
	// instance of DictionaryData for accessing dictionary data
	private DictionaryData dictionary = DictionaryData.getInstance();

    @FXML // TextArea for the word
    private TextArea textAreaWord;

    @FXML // TextArea for word's definition
    private TextArea textAreaDefenition;

    @FXML // Button to save the word and its definition to the dictionary
    private Button saveButton;
    
    @FXML
    /* Initializes the FXML objects within the add mode scene with the desired attributes */
	private void initialize() {
    	// set wrap text to true for cleaner display
    	textAreaWord.setWrapText(true);
		textAreaDefenition.setWrapText(true);
    }

    @FXML
    /*  Handles the event when the save button is pressed. Adds the inputed word and its 
     * definition to the dictionary */
    public void saveButtonPressed(ActionEvent event) {
        // add the word and definition to the dictionary
        dictionary.addWord(textAreaWord.getText(), textAreaDefenition.getText());
        
        // clear the text areas again
        clearTextArea();
    	
    	try { // switch scenes
			scene.switchToDisplayScene(event);
		} catch (IOException e) { // If unable to load, throw an error
			System.out.println("Unexpected error!");
		}
    }
    
    /* Utility method - clears the text areas */
    private void clearTextArea() {
        textAreaWord.clear();
        textAreaDefenition.clear();
    }

}
