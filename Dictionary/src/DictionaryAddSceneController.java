import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class DictionaryAddSceneController {
	private SceneController scene = new SceneController();
	private DictionaryData dictionary = DictionaryData.getInstance();

    @FXML
    private TextArea textAreaWord;

    @FXML
    private TextArea textAreaDefenition;

    @FXML
    private Button saveButton;
    
    @FXML
	private void initialize() {
    	textAreaWord.setWrapText(true);
		textAreaDefenition.setWrapText(true);
    }

    @FXML
    public void saveButtonPressed(ActionEvent event) {
    	
    	// Get the text from each TextArea
        String word = textAreaWord.getText();
        String definition = textAreaDefenition.getText();
        
        // Add the word and definition to the dictionary
        dictionary.addWord(word, definition);
        
        // clear the text areas again
        clearTextArea();
    	
    	try { // switch scenes
			scene.switchToDisplayScene(event);
		} catch (IOException e) {
			System.out.println("Unexpected error!");
		}
    }
    
    private void clearTextArea() {
        textAreaWord.clear();
        textAreaDefenition.clear();
    }

}
