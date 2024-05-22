import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.util.Map;

/**
 * Controller class for managing the graphical user interface and handling the methods 
 * of the FXML objects within the display scene.
 */
public class DictionaryDisplaySceneController {
	// scene controller object for switching scenes
	private SceneController scene = new SceneController(); 
	
	// instance of DictionaryData for accessing dictionary data
	private DictionaryData dictionary = DictionaryData.getInstance(); 
	
	// ObservableList object for storing and displaying filtered words
	private ObservableList<String> filteredWords; 

	@FXML // ListView for displaying the words of the dictionary
    private ListView<String> listView;
	
	@FXML // TextField for searching a word in the dictionary
	private TextField searchTextField;

	@FXML // Button to add predefined values to the dictionary
	private Button autoFillButton;

	@FXML // Button to reset the program
	private Button resetButton;

	@FXML // Button to add a new word to the dictionary
	private Button addButton;

    @FXML // Button to enable editing the selected dictionary value
    private Button editButton;

    @FXML // Button to delete the selected value
    private Button deleteButton;

    @FXML // Button to save the updated value in the dictionary
    private Button doneButton;
    
    @FXML // VBox object that contains the text areas
    private VBox vboxTextArea;

    @FXML // TextArea for the word
    private TextArea textAreaWord;

    @FXML // TextArea for word's definition
    private TextArea textAreaDefinition;
    
	@FXML
	/* Initializes the FXML objects within the display scene with the desired attributes */
	private void initialize() {
		// set wrap text to true for cleaner display
		textAreaWord.setWrapText(true);
		textAreaDefinition.setWrapText(true);
		
		// set away focus from the search TextField
		//searchTextField.fo
		
		// set vbox's style to it's default style
		vboxTextArea.getStyleClass().add("default");

		// create dynamic list
		filteredWords = FXCollections.observableArrayList();
		
		// set the filteredWords list as the data source for the listView
		listView.setItems(filteredWords);

		// add a change listener to the text content of the TextField
		searchTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override // Override the changed method, will be executed whenever the text in the TextField is changed
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updateFilteredWords(newValue); // update the words in the listView
			}
		});

		// add a change listener to the selectedItemProperty of the listView
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override // Override the changed method, will be executed whenever the selected item is changed
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				displayWordDefinition(newValue); // display the selected item
			}
		});

		// set listView to display all dictionary content
		updateFilteredWords("");
	}

	@FXML
	/*  Handles the event when the add button is pressed. Switches to the scene for
	 * adding a new word, enabling the user to input the desired values for the new word */
	public void addButtonPressed(ActionEvent event) {
		try { // switch scene
			scene.switchToAddScene(event);
		} catch (IOException e) { // If unable to load, throw an error
			System.out.println("Unexpected error!");
		}
	}

	@FXML
	/* Handles the event when the edit button is pressed. Enables the user to edit
	 * the selected value */
	public void EditButtonPressed(ActionEvent event) {
		// enable the Done button
		doneButton.setDisable(false);
		
		// disable the edit and the delete buttons
		disableTextAreaButtons(true);
		disableDictionaryActions(true);

		// highlight the VBox
		setVBoxHighlight(true);

		// enable editing the text areas
		setEditableTextAreas(true);
		
		// delete the previous version of this word
		dictionary.removeWord(textAreaWord.getText());
	}

	@FXML
	/*  Handles the event when the done button is pressed. Saves the updated version
	 * of the edited word */
	public void doneButtonPressed(ActionEvent event) {
		// Add the word and definition to the dictionary
		dictionary.addWord(textAreaWord.getText(), textAreaDefinition.getText());

		// set VBox style to it's default style
		setVBoxHighlight(false);

		// disable editing of the text areas
		setEditableTextAreas(false);
		
		// enable add/reset/autoFill buttons
		disableDictionaryActions(false);

		// disable the Done button
		doneButton.setDisable(true);

		// update listView to display all dictionary content
		updateFilteredWords("");
	}
	 
	 
    @FXML 
    /*  Handles the event when the delete button is pressed. Deletes the selected 
     * value from the dictionary */
    public void DeleteButtonPressed(ActionEvent event) {
		// remove word from dictionary
		dictionary.removeWord(textAreaWord.getText());
		
		// disable the edit and the delete buttons
    	disableTextAreaButtons(true);
    	disableDictionaryActions(false);
    	
		// set VBox style to it's default style
		setVBoxHighlight(false);

		// clear the text areas
		clearTextArea();

		// set listView to display all dictionary content
		updateFilteredWords("");
    }


    @FXML
    /* Automatically populates the dictionary with predefined content for testing purposes */
    void autoFillButtonPressed(ActionEvent event) {
    	// add predefined content
    	dictionary.autoFill();
    	
    	// update listView 
    	updateFilteredWords("");
    }


    @FXML
    public void displayAllButtonPressed(ActionEvent event) {
    	dictionary.displayAllWords();
    }

    @FXML
    /*  Handles the event when the reset button is pressed. Resets the dictionary program  */
    public void resetButtonPressed(ActionEvent event) {
    	// clear dictionary
    	dictionary.clearAllWords();
    	
    	// clear the text areas
    	clearTextArea();
    	
    	// disable the edit, delete and done buttons
    	disableTextAreaButtons(true);
    	doneButton.setDisable(true);
    	
    	// update the listView
    	updateFilteredWords("");

    }
    
    /* Utility method - updates the ListView values based on the given query */
	private void updateFilteredWords(String query) {
		// clear the current filteredWords
		filteredWords.clear();

		// if query is empty, display all dictionary content
		if (query == null || query.isEmpty())
			filteredWords.addAll(dictionary.getDictionary().keySet());

		 // display only the words that match the query
		else {
			for (Map.Entry<String, String> entry : dictionary.getDictionary().entrySet()) {
				if (entry.getKey().toLowerCase().contains(query.toLowerCase()))
					filteredWords.add(entry.getKey());
			}
		}
	}

	/* Utility method - displays the specified word and it's definition in the text areas */
	private void displayWordDefinition(String word) {
		if (word != null && dictionary.getDictionary().containsKey(word)) {
			// display the word if its exists in the dictionary
			textAreaWord.setText(word);
			textAreaDefinition.setText(dictionary.getDefinition(word));
			
			// enable the edit and the delete buttons
	    	disableTextAreaButtons(false);
			
		} else // if not - clear the text areas
			clearTextArea();
	}

	/* Utility method - clears the text areas */
    private void clearTextArea() {
        textAreaWord.clear();
        textAreaDefinition.clear();
    }
    
    /* Utility method - sets the 'editable' attribute of the text area based on the 
     * provided value */
    private void setEditableTextAreas(boolean editable) {
    	textAreaWord.setEditable(editable);
    	textAreaDefinition.setEditable(editable);
    }
    
    /* Utility method - sets the 'disable' attribute of the buttons within the text area
     *  based on the provided value */
    private void disableTextAreaButtons(boolean disable) {
    	deleteButton.setDisable(disable);
    	editButton.setDisable(disable);
    }
    
    /* Utility method - sets the 'disable' attribute of the dictionary elements based on 
     * the provided value */
    private void disableDictionaryActions(boolean disable) {
    	addButton.setDisable(disable);
    	autoFillButton.setDisable(disable);
    	resetButton.setDisable(disable);
    	searchTextField.setDisable(disable);
    	listView.setDisable(disable);
    }
    
    /* Utility method - sets VBox highlight style accordingly to the inputed value */
    private void setVBoxHighlight(boolean highlight) {
    	vboxTextArea.getStyleClass().clear(); // clear existing styles
    	
        if (highlight) // if highlight is true - sets the highlight style for the VBox
        	vboxTextArea.getStyleClass().add("highlight");
    }

} // end of class DictionaryDisplaySceneController
