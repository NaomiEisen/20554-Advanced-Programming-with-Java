/**
 * FourInARowModel Class.
 * Responsible for managing the gameplay logic of the 
 * 'Four In A Row' game, handling player movements, user input, 
 * and game state transitions.
 */
public class FourInARowModel {
	// game's board size
	public static final int NUM_COLUMN_BOARD = 7; 
	public static final int NUM_ROWS_BOARD = 5;
	
	// number of consecutive tokens that requires to win
	public static final int LINE_OF_FOUR = 4;
	
	// array representation of game's board
	private Token[][] modelGrid = new Token[NUM_ROWS_BOARD][NUM_COLUMN_BOARD];
	
	// token row's position in each column
	private int[] tokenPosition = new int[NUM_COLUMN_BOARD];
	
	// false - pink player's turn ; true - blue player's turn
	private boolean playersTurn = false; 
	
	// counter for number of full columns
	private int fullColumnCounter = 0;
	
	// determine end of current game
	private boolean endGame = false;
	
	// Message object instance for displaying messages
	private Messages message = new Messages();
	
	public FourInARowModel() {
		// set tokenPosition to the starting row index 
		clearGridModel();
	}
	
	/**
	 * Handles players move; the drop of token in the corresponding column in the board.
	 * @param column - the column of the dropped token
	 * @return - row's index of the dropped token.
	 */
	public int dropToken (int column) {
		if (!endGame) {
			if (tokenPosition[column] > 0) {
			switchPlayerTurn(); // switch turns
			tokenPosition[column] -= 1; // decrement the token position value by 1
			
			// set the corresponding color in grid
			modelGrid[tokenPosition[column]][column] = gerCurrentPlayerColor();
			
			// if the column is filled by the current round
			if (tokenPosition[column] == 0)
				fullColumnCounter++;

			return tokenPosition[column]; // return the row's position
			
		} else // column is full
			message.fullColumn();
			return -1;
			
		} // game ended
		message.endOfGame();
		return -1;
	}
	
	/**
	 * Returns the color of the current player's token.
	 * @return - enum type of current player's token
	 */
	public Token gerCurrentPlayerColor() {
		return playersTurn? Token.BLUE : Token.PINK;
	}
	
	/**
	 * Switches the current player for the current round.
	 */
	private void switchPlayerTurn() {
		 playersTurn = !playersTurn;
	}
	
	/**
	 * Clears the grid representation of the class, 
	 * resetting it to its initial values.
	 */
	public void clearGridModel() {
		int i, j = 0;
		for (j = 0; j < NUM_COLUMN_BOARD; j++) {
			// reset tokenPosition array
			tokenPosition[j] = NUM_ROWS_BOARD;
			for (i = 0; i < NUM_ROWS_BOARD; i++) {
				// reset modelGrid array
				modelGrid[i][j] = null;
			}
		}
		
		endGame = false; // reset endGame variable
		fullColumnCounter = 0; // reset fullColumnConter variable
	}
	
	/**
	 * Determines if the current player has won.
	 * @param column - column's index of the current dropped token
	 * @param row - row's index of the current dropped token
	 * @param playersColor - color of the current player's token
	 */
	public void checkWin(int column, int row, Token playersColor) {
	    // vertical line
	    if (checkLine(column, row, 0, -1, playersColor)) 
	    	victory(playersColor);
	    
	    for (int offset = 0; offset < LINE_OF_FOUR; ++offset) {
	        // horizontal line
	        if (checkLine(column - (LINE_OF_FOUR-1) + offset, row, 1, 0, playersColor))
	        	victory(playersColor);
	        
	        // leading diagonal
	        if (checkLine(column - (LINE_OF_FOUR-1) + offset, row + (LINE_OF_FOUR-1) - offset,
	        		1, -1, playersColor))
	        	victory(playersColor);

	        // trailing diagonal
	        if (checkLine(column - (LINE_OF_FOUR-1) + offset, row - (LINE_OF_FOUR-1) + offset,
	        		1, 1, playersColor)) 
	        	victory(playersColor);
	    }
	}
	
	/**
	 * Checks for four consecutive tokens in a line.
	 * @param columnIn - starting column's index for checking
	 * @param rowIn - starting row's index for checking
	 * @param colDiff - column's difference in each check
	 * @param rowDiff - row's difference in each check
	 * @param playersColor - token's color to check
	 * @return - true if player has won ; false otherwise
	 */
	private boolean checkLine(int columnIn, int rowIn, int colDiff, int rowDiff, Token playersColor) {
	    for (int i = 0; i < LINE_OF_FOUR; ++i) {
	    	// coordinate of the current position to check
	        int calumn = columnIn + (colDiff * i);
	        int row = rowIn + (rowDiff * i);

	        // if position is out of boards bound, return false
	        if (calumn < 0 || calumn > NUM_COLUMN_BOARD - 1) {return false;}
	        if (row < 0 || row > NUM_ROWS_BOARD - 1) {return false;}

	        // if the token in the current position is in rival's color return false
	        if (playersColor != modelGrid[row][calumn]) {return false;}
	    }

	    // line of four is founded
	    return true;
	}
	
	/**
	 * Checks if the game board is full; if so, displays a message indicating 
	 * a tie status. Sets the game state to end.
	 */
	public void checkTie() {
		if (fullColumnCounter == NUM_COLUMN_BOARD) { // it's a tie
			endGame = true; 
			message.tie(); // display the relevant message
		}
	}
	
	/**
	 * Victory game's status. Displays relevant message and sets the game state 
	 * to end.
	 * @param playersColor - the color of the winning player's token
	 */
	private void victory(Token playersColor) {
		message.Victory(playersColor); // display the relevant message
		endGame = true;
	}
	
	/**
	 * Get game's state.
	 * @return - endGame value
	 */
	public boolean getEndOfGame() {
		return endGame;
	}
} // end of class FourInARowModel
