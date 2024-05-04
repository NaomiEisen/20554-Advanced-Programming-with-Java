/**
 * Game class.
 * Runs the game, handling the 'logic' of the program.
 * Responsible for the functions and interactions between the player and the game.
 */
import java.util.ArrayList;

public class Game {
	// player's and computer's deck of cards
	private DeckOfCards _player = new DeckOfCards(); 
	// player's cards that are "on the table"
	private ArrayList<Card> _playerPlayCards = new ArrayList<Card>();  
	// computer's cards that are "on the table"
	private ArrayList<Card> _computerPlayCards = new ArrayList<Card>(); 
	
	// the number of cards used in the war move
	private static final int CARDS_FOR_WAR = 3;
	
	// round's condition
	private StatusRound _stRound;
	
	// object for printing the game's content
	private static Graphics gc = new Graphics(); 
	
	/**
	 * Game object constructor. 
	 * Launches the game.
	 */
	public Game() {
		// display the starting message of the game
		gc.startMessage();
		// activate the game
		startGame();
		// display the result of the game
		finishGame();
	}
	
	
	/* startGame method keeping the game running -
	 * until one of the players wins \ tie  */
	private void startGame() {
		do {
			round();
			_player.updateStatusGame(); // check if someone won
		} while (_player.getStatusGame() == StatusGame.PLAY);
	}
	
	/* battleRound method configures one round of game. */
	private void round() {
		// take the top card of player's pile
		_playerPlayCards.add(_player.removePlayerCard());
		_computerPlayCards.add(_player.removeComputerCard());
		
		// create card object of the last taken card
		Card playersCard = _playerPlayCards.get(_playerPlayCards.size()-1);
		Card computersCard = _computerPlayCards.get(_computerPlayCards.size()-1);
		
		// check cards value
		int playersValue = playersCard.getCardValue();
		int computersValue = computersCard.getCardValue();
		
		// print cards
		String st = "Your " + playersCard.toString() +"Rival's " +
				computersCard.toString();
		gc.cardsOnTableMessage(st);
		
		// decide the next move
		compare(playersValue , computersValue);
		switch (_stRound) {
			case WIN_ROUND:
				win();
				break;
			case LOSE_ROUND:
				lose();
				break;
			case WAR:
				war();
				break;
		}
	}
	
	/* compare method compares value of two cards */
	private void compare(int playersValue, int computersValue) {
		if (playersValue > computersValue) 
			_stRound = StatusRound.WIN_ROUND;
		else if (playersValue == computersValue)
			_stRound = StatusRound.WAR;
		else
			_stRound = StatusRound.LOSE_ROUND;
	}
	
	/* win method populate all the cards to the end of player's pile. */
	private void win() {
		_player.addToPlayersPile(_playerPlayCards);
		_player.addToPlayersPile(_computerPlayCards);
		
		cleanTable(); // clean table after copying
		gc.winRoundMessage();
	}
	
	/* lose method populate all the cards to the end of computer's pile. */
	private void lose() {
		_player.addToComputersPile(_computerPlayCards);
		_player.addToComputersPile(_playerPlayCards);

		cleanTable(); // clean table after copying
		gc.loseRoundMessage();
	}
	
	/* cleanTable method clears the arrays that contain the used cards 
	 *  for the current war when one of the players wins this round. */
	private void cleanTable() {
		_playerPlayCards.clear();
		_computerPlayCards.clear();
	}

	/* war method configures "war" move */
	private void war() {
		gc.warMessage(); 
		
		// determine if "war" can be executed 
		_player.updateStatusWar();
		switch (_player.getStatusWar()) {
		// if player don't have enough cards for war
		case COMPUTER:
			// then the player loses the game
			gc.loseInWarMove();
			gc.loseGameMessage();
			System.exit(0);
			break;
		// if computer don't have enough cards for war
		case PLAYER:
			// then the player wins the game
			gc.winInWarMove();
			gc.winGameMessage();
			System.exit(0);
			break;
		// if both players don't have enough cards for war
		case TIE:
			// it is considered a tie
			gc.tieMessage();
			System.exit(0);
			break;
		// war can be executed
		case NONE:
			warRoundMove();
			break;
		} 
	}
	
	/* warRoundMove method executes "regular" war round */
	private void warRoundMove() {
		// take two cards from each players pile
		for (int i =0 ; i < CARDS_FOR_WAR ; i++) {
				_playerPlayCards.add(_player.removePlayerCard());
				_computerPlayCards.add(_player.removeComputerCard());
		}
		
		// display the two cards
		String stPlayer = "Yours first two cards: \n";
		String srComputer = "Rivals first two cards: \n";
		for (int i =1 ; i < CARDS_FOR_WAR ; i++) {
			stPlayer += (_playerPlayCards.get(_playerPlayCards.size()-i)).toString();
			srComputer += (_computerPlayCards.get(_computerPlayCards.size()-i)).toString();
		}
		gc.warRoundMessage(stPlayer + srComputer );
		
		// continue to regular round - the third and decisive card
		round();
	}
	
	/* finishGame method displays the result */
	private void finishGame() {
		switch (_player.getStatusGame()) {
		case WIN_GAME:
			gc.winGameMessage();
			break;
		case LOSE_GAME:
			gc.loseGameMessage();
			break;
		case TIE:
			gc.tieMessage();
			break;
		default:
			gc.tieMessage();
		}
	}

} // end of class Game

