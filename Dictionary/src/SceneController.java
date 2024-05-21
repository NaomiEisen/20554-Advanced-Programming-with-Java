import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * SceneController class responsible for switching between FXML scenes.
 */
public class SceneController {
 private Stage stage;
 private Scene scene;
 private Parent root;
 
 /* Switches to DisplayScene; the scene where dictionary content is displayed */
 public void switchToDisplayScene(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("DictionaryDisplayScene.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
 
 /* Switches to AddScene; the scene where the user inputs a new word */
 public void switchToAddScene(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("DictionaryAddScene.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
}
