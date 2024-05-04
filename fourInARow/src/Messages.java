import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * Class Messages.
 * Displays messages of game condition.
 */
public class Messages {
	// Alert object for class usage
	private Alert alert = new Alert(null);
	
	/**
	 * message for a full column condition
	 */
	public void fullColumn() {
		alert.setAlertType(AlertType.ERROR);
		alert.setTitle("Illigal move");
		alert.setHeaderText("Full Columm");
		alert.setContentText("Try another column.");
		alert.showAndWait();
	}
	
	/**
	 * message for a tie condition.
	 */
	public void tie() {
		alert.setAlertType(AlertType.INFORMATION);
		alert.setTitle("Tie");
		alert.setHeaderText("It's a tie!");
		alert.setContentText("You're both pretty good ;)\n"
				+ "Please press 'clear' to restart the game.");
		alert.showAndWait();
	}
	
	/**
	 * message for the end of the game.
	 */
	public void endOfGame() {
		alert.setAlertType(AlertType.ERROR);
		alert.setTitle("Illigal move");
		alert.setHeaderText("Game Over");
		alert.setContentText("please press 'clear' to restart the game.");
		alert.showAndWait();
	}

	/**
	 * message for victory.
	 * @param playersColor - the color of the winning player's token.
	 */
	public void Victory(Token playersColor) {
		alert.setAlertType(AlertType.ERROR);
		alert.setTitle("Victory");
		alert.setHeaderText("VICTORY");
		String player = playersColor == Token.PINK? "Pink" : "Blue" ;
		alert.setContentText("Congrats! " + player + " player won!");
		alert.showAndWait();

	}
} // end of class Messages
