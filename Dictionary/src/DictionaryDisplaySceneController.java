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

public class DictionaryDisplaySceneController {
	private SceneController scene = new SceneController();
	private DictionaryData dictionary = DictionaryData.getInstance();;
	 private ObservableList<String> filteredWords;

	@FXML
    private ListView<String> listView;
	
	 @FXML
	private TextField searchTextField;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;
    

    @FXML
    private Button DoneButton;
    
    @FXML
    private VBox vbox;

    @FXML
    private TextArea textAreaWord;

    @FXML
    private TextArea textAreaDefenition;
    
	@FXML
	private void initialize() {
		textAreaWord.setWrapText(true);
		textAreaDefenition.setWrapText(true);


		// 
		filteredWords = FXCollections.observableArrayList();
		listView.setItems(filteredWords);

		searchTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updateFilteredWords(newValue);
			}
		});

		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				displayWordDefinition(newValue);
			}
		});

		updateFilteredWords("");
	}

	private void updateFilteredWords(String query) {
		filteredWords.clear();
		if (query == null || query.isEmpty()) {
			filteredWords.addAll(dictionary.getDictionary().keySet());
		} else {
			for (Map.Entry<String, String> entry : dictionary.getDictionary().entrySet()) {
				if (entry.getKey().toLowerCase().contains(query.toLowerCase())) {
					filteredWords.add(entry.getKey());
				}
			}
		}
	}

	private void displayWordDefinition(String word) {
		if (word != null && dictionary.getDictionary().containsKey(word)) {
			textAreaWord.setText(word);
			textAreaDefenition.setText(dictionary.getDefinition(word));
		} else {
			clearTextArea();
		}
	}

    @FXML
    public void DeleteButtonPressed(ActionEvent event) {
    	 String word = textAreaWord.getText();
    	 dictionary.removeWord(word);
    	 clearTextArea();
    	 updateFilteredWords("");

    }

    @FXML
    public void EditButtonPressed(ActionEvent event) {
    	DoneButton.setVisible(true);
    	 vbox.getStyleClass().add("highlight");
    	 textAreaWord.getStyleClass().add("highlighted");
    	setEditableTextAreas(true);
    	// vbox.setStyle("-fx-border-color: #f1c8d0; -fx-border-radius:10; -fx-background-color: #f6e9ec");
    	
    	String word = textAreaWord.getText();
   	 	dictionary.removeWord(word);
    }
    
    @FXML
    public void DoneButtonPressed(ActionEvent event) {
    	// Get the text from each TextArea
        String word = textAreaWord.getText();
        String definition = textAreaDefenition.getText();
        
        textAreaWord.getStyleClass().remove("highlighted");
        
        
        // Add the word and definition to the dictionary
        dictionary.addWord(word, definition);
        
        setEditableTextAreas(false);
        DoneButton.setVisible(false);
        updateFilteredWords("");
    }


    @FXML
    public void addButtonPressed(ActionEvent event) {
    	try {
			scene.switchToAddScene(event);
		} catch (IOException e) {
			System.out.println("Unexpected error!");
		}
    }

    @FXML
    public void displayAllButtonPressed(ActionEvent event) {
    	dictionary.displayAllWords();
    }

    @FXML
    public void resetButtonPressed(ActionEvent event) {
    	dictionary.clearAllWords();
    	clearTextArea();
    	updateFilteredWords("");

    }

    
    private void clearTextArea() {
        textAreaWord.clear();
        textAreaDefenition.clear();
    }
    
    private void setEditableTextAreas(boolean editable) {
    	textAreaWord.setEditable(editable);
    	textAreaDefenition.setEditable(editable);
    }

}
