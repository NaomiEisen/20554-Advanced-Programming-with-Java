import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
/**
 * FourInARowController Class.
 * Controller for the graphics of the game.
 * Controller for managing the graphics of the 
 * 'Four In A Row' game.
 */
public class FourInARowController {
	// size of the grid
	private static final int NUM_COLUMN_GRID = 7;
	private static final int NUM_ROWS_GRID = 6;
	
	// token's radius
	private static final int TOKEN_RADIUS = 20;
	
	// array of buttons for each column in the grid
	private Button columButtons[];
	
	// FourInARowModel object instance for handling the gameplay logic
	private FourInARowModel model = new FourInARowModel();
	
	@FXML private GridPane gridPane;

	
	/**
	 * Sets user's data 
	 */
    public void initialize() {
    	/* create the column buttons */
    	columButtons = new Button[NUM_COLUMN_GRID];
		for (int i = 0; i < NUM_COLUMN_GRID ; i++) {
			columButtons[i] = new Button("" + (i + 1));
			columButtons[i].setMaxWidth(Double.MAX_VALUE); // set the button to fill the grid's cell
			columButtons[i].setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {buttonPressed(e);} 
			});
			// add button to the accurate position in the grid
			gridPane.add(columButtons[i], i, NUM_ROWS_GRID); 
		}

		/* create tokens */
		for (int i = 0; i < NUM_COLUMN_GRID; i++) { // irritate through column
			for (int j = 0; j < NUM_ROWS_GRID - 1; j++) { // irritate through row
				Circle token = new Circle(TOKEN_RADIUS);
				token.setFill(Color.TRANSPARENT); // set the fill color to transparent
				gridPane.add(token, i, j); // add token to grid
				GridPane.setMargin(token, new Insets(5, 5, 5, 5)); // set margin of token
			}
		}
    }

    /**
     * Clears the game's board and resets the game.
     * @param event - pressed button action of 'clear' button
     */
    @FXML
    void clearButtonPressed(ActionEvent event) {
		for (int i = 0; i < NUM_COLUMN_GRID; i++) { // irritate through column
			for (int j = 0; j < NUM_ROWS_GRID - 1; j++) { // irritate through row
				colorToken(j, i, Color.TRANSPARENT); // Set the fill color to transparent
			}
		}
		// reset model
		model.clearGridModel();
	}

	/**
	 * Handles players move; the drop of token in the corresponding column in the board.
	 * 
	 * @param event - pressed button action of the corresponding button
	 */
	@FXML
	void buttonPressed(ActionEvent event) {
		// get the button's column index
		int columnDropped = GridPane.getColumnIndex((Node) event.getSource());
		
		// get the row of the dropped token
		int rowDropped = model.dropToken(columnDropped);
		
		if (rowDropped >= 0) { // if the token dropped successfully
			
			// color the grid accordingly
			Paint tokenColor = (model.gerCurrentPlayerColor() == Token.PINK) ? Color.PINK : Color.CADETBLUE;
			colorToken(rowDropped, columnDropped, tokenColor);
			
			// check if the current player won this round.
			model.checkWin(columnDropped, rowDropped, model.gerCurrentPlayerColor());
			if (!model.getEndOfGame()) { // if player did not won
				model.checkTie(); // check if it's a tie
			}
		}
	}
    
	/**
	 * Colors the token in the specified position with the desired color.
	 * Represents currents player's token drop.
	 * @param row - desired token's row index
	 * @param column - desired token's column index
	 * @param color- desired token's color
	 */
    public void colorToken(int row, int column, Paint color) {
    	// get the desired token by the inputed coordinate
		Node node = getNodeByCoordinate(row, column);
		
		if (node instanceof Circle)
			((Circle) node).setFill(color); // set the color
		
		else // handle the case where the node is not a Circle
			System.err.println("Unexpected error!"); // not supposed to happen!
	}
    
    /**
     * Method for finding a node in the grid based on the given coordinate.
     * @param row - desired node's row index
     * @param column - desired node's column index
     * @return - the node
     */
    private Node getNodeByCoordinate(Integer row, Integer column) {
        for (Node node : gridPane.getChildren()) { // grid's iterator
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }
        return null; // node not found
    }
} // end of class FourInARowController

