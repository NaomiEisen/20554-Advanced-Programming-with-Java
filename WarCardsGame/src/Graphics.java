/**
 * Graphics class.
 * Contains configurations for various outputs and their settings.
 */
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class Graphics {
	/* icons for different outputs */
	private ImageIcon oneCard = new ImageIcon("src/oneCard.png");
	private ImageIcon twoCard = new ImageIcon("src/twoCards.png");
	private ImageIcon winRound = new ImageIcon("src/win.png");
	private ImageIcon winGame = new ImageIcon("src/winGame.png");
	private ImageIcon loseRound = new ImageIcon("src/lose.png");
	private ImageIcon loseGame = new ImageIcon("src/dead.png");
	private ImageIcon swords = new ImageIcon("src/swords.png");
	
	public void startMessage() {
		String message = "Press \"ok\" you ready to start WAR OF CARDS Game!";
			JOptionPane.showConfirmDialog(null, message, "Welcome :)",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
	}

	public void cardsOnTableMessage(String theCards) {
		JOptionPane.showConfirmDialog(null, theCards, "Playing",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, oneCard);
	}
	
	public void winRoundMessage() {
		String message = "Congrats! you won this round :) \nAre you ready for your next move?";
		int choice = JOptionPane.showConfirmDialog(null, message, "Winner of this round",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, winRound);
		dealWithChoise(choice);
	}
	
	public void loseRoundMessage() {
		String message = "Oh no! You've LOST this round :(\nAre you ready to strike back??";
		int choice = JOptionPane.showConfirmDialog(null, message, "Loser of this round",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, loseRound);
		dealWithChoise(choice);
	}
	
	public void warMessage() {
		String message = "It looks like we have a tie...\n" + 
						"You have no other option but to go to WAR.";
		JOptionPane.showConfirmDialog(null, message, "Playing",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, swords);
	}
	
	public void warRoundMessage(String theCards) {
		String message = theCards + "\n and the last but not least...>>";
		JOptionPane.showConfirmDialog(null, message, "In war",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, twoCard);
	}
	
	public void winInWarMove() {
		String message = "It seems like your enemy has run out of cards.\n" + 
				"Finish him.";
		JOptionPane.showConfirmDialog(null, message, "FINISH HIM",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
	}
	
	public void loseInWarMove() {
		String message = "Oops. There are not enough cards in your pile!\n" + 
				"This means only one thing...[press to see]";
		JOptionPane.showConfirmDialog(null, message, ":(",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
	}
	
	public void winGameMessage() {
		String message = "Flawless victory! \nYou have won the WAR!";
		JOptionPane.showConfirmDialog(null, message, "End of game",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, winGame);
	}
	
	public void loseGameMessage() {
		String message = "You've been DEAFETED.";
		JOptionPane.showConfirmDialog(null, message, "End of game",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, loseGame);
	}
	
	public void tieMessage() {
		String message = "You're pretty tough to play with...\n" +
						"It's a tie ;)";
		JOptionPane.showConfirmDialog(null, message, "End of game",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
	}
	
	private void dealWithChoise(int choice) {
		if (choice == JOptionPane.NO_OPTION) {
			JOptionPane.showConfirmDialog(null, "GAME OVER", "bye... :(",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}	
	}
}// end of class Graphics
