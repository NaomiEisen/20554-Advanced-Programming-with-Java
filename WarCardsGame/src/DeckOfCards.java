/**
 * DeckOfCards class.
 * Represents the player's and computer's piles of cards.
 */
import java.util.Random;
import java.util.ArrayList;

public class DeckOfCards {
	// number of cards in a whole deck
	private static final int NUMBER_OF_CARDS = 52; 
	// number of cards used in war move
	private static final int CARDS_FOR_WAR = 3;
	// ratio for creating accurate number of each card
	private static final int RATIO = 13; 
	
	// Representation of players cards pile in arrays
	private ArrayList<Card> _playerPile = new ArrayList<Card>();
	private ArrayList<Card> _computerPile = new ArrayList<Card>();
	
	// game's condition
	private StatusGame _stGame;
	// war move's condition
	private StatusWar _stWar;
	
	/**
	 * Constructor for filling players's deck of Cards
	 */
	public DeckOfCards() {
		// create a whole deck in computer's deck
		createWholeDeck();
		
		// shuffle the deck
		shuffle();
		
		// populate half of the cards in player's deck
		split();
		
		_stGame = StatusGame.PLAY;
 
	}
		
	/**
	 * Checks if players pile is empty.
	 * 
	 * @return	true; the pile is empty. false; the pile isn't empty.
	 */
	public boolean PlayersPileEmpty() {
		if (_playerPile.size() == 0)
			return true;
		return false;
	}
	
	/**
	 * Checks if players pile is full.
	 * @return	true; the pile is full. false; the pile isn't full.
	 */
	public boolean PlayersPileFull() {
		if (_playerPile.size() == NUMBER_OF_CARDS)
			return true;
		return false;
	}
	
	/** 
	 * Checks if one of the players won.
	 *  winWar ; player has won the game.
	 *  loseWar ; player has lose the game.
	 *  play ; none of the players has won yet.
	 */
	public void updateStatusGame() {
		if (PlayersPileEmpty())
			_stGame = StatusGame.WIN_GAME;
		else if (PlayersPileFull())
			_stGame = StatusGame.LOSE_GAME;
		else
			_stGame = StatusGame.PLAY;
	}
	
	/**
	 * Returns game's status.
	 * @return	game's status.
	 */
	public StatusGame getStatusGame() {
		return _stGame;
	}
	
	/**
	 * Determines if a "regular" war move can be executed.
	 * war status changes accordingly to the scenarios:
	 *  COMPUTER/PLAYER; when only one of them has less that 3 cards in his pile.
	 *   TIE; when both have less that 3 cards in their pile.
	 *   NONE; when both have at least 3 cards in their pile.
	 */
	public void updateStatusWar() {
		if (_playerPile.size() < CARDS_FOR_WAR && _computerPile.size() < CARDS_FOR_WAR )
			_stWar = StatusWar.TIE;
		else if (_playerPile.size() < CARDS_FOR_WAR) 
			_stWar = StatusWar.COMPUTER;
		else if (_computerPile.size() < CARDS_FOR_WAR )
			_stWar = StatusWar.PLAYER;
		else
			_stWar = StatusWar.NONE;
	}
	
	/**
	 * Returns a string with the war's status value.
	 * @return	war's status.
	 */
	public StatusWar getStatusWar() {
		return _stWar;
	}
	
	/**
	 * Removes one card from the top of the player's pile and returns it.
	 * If the pile is empty' return null.
	 * @return	the card on the top of the player's pile.
	 */
	public Card removePlayerCard() {
		if (!PlayersPileEmpty())
			return _playerPile.remove(0);
		else
			return null;
	}
	
	/**
	 * Removes one card from the top of the computer's pile and returns it.
	 * If the pile is empty' return null.
	 * @return the card on the top of the computer's pile.
	 */
	public Card removeComputerCard() {
		if (_computerPile.size() != 0)
			return _computerPile.remove(0);
		else
			return null;
	}
	
	/**
	 * Adds all the cards from the "table" to player's pile.
	 * @param arrOfCards	the array that consists of the cards on the "table"
	 */
	public void addToPlayersPile(ArrayList<Card> arrOfCards) {
		_playerPile.addAll(arrOfCards);
	}
	
	/**
	 * Adds all the cards from the "table" to computer's pile.
	 * @param arrOfCards	the array that consists of the cards on the "table"
	 */
	public void addToComputersPile(ArrayList<Card> arrOfCards) {
		for (int i = 0 ; i < arrOfCards.size() ; i++) 
			_computerPile.add(arrOfCards.get(i));
	}

	
	/* private utility methods */
	/* Creates a whole deck of cards */
	private void createWholeDeck() {
		// options for cards
		String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven",
				"Eight", "Nine", "Ten", "Jack", "Queen", "king"};
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
		
		// populates computerDeck array with cards
		for (int i = 0 ; i < NUMBER_OF_CARDS ; i++) {
			_computerPile.add(new Card(faces[i%RATIO], suits[i/RATIO]));
		}
	}
	
	/* place half of the cards from computer's deck to player's deck */
	private void split() {
		for (int i = 0; i < NUMBER_OF_CARDS/2; i++) {
			_playerPile.add(_computerPile.remove(i));
		}
	}
	
	/* Shuffles deck of cards in computer's pile */
	private void shuffle() {
		Random random = new Random(); // random object for shuffling the deck
		for (int i = 0; i < _computerPile.size(); i++) {
			// pick random index to swap
			int randomIndexToSwap = random.nextInt(_computerPile.size());
			// swap cards
			Card temp = _computerPile.get(randomIndexToSwap);
			_computerPile.set(randomIndexToSwap, _computerPile.get(i));
			_computerPile.set(i, temp);
		}
	}
} // end of class DeckOfCards
