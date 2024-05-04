/**
 * Card class.
 * Represents a playing card.
 */
public class Card {
	private final String FACE;
	private final String SUIT;
	private final int VALUE;
	
	/**
	 * Constructor for card object.
	 * @param cardFace : type of card ("Ace", "King", "Number"...)
	 * @param cardSuit : card's element ("Hearts", "Diamonds"...)
	 */
	public Card(String cardFace, String cardSuit) {
		this.FACE = cardFace;
		this.SUIT = cardSuit;
		this.VALUE = determineValue(cardFace);
	}
	
	/**
	 * getCardValue method returns the card's value attribute.
	 * @return	card's value.
	 */
	public int getCardValue() {
		return this.VALUE;
	}
	
	/**
	 * toString method.
	 * Prints the card attributes.
	 */
	public String toString() {
		return "card: " + FACE + " of " + SUIT + "\n";
	}
	
	/* determines the value of a card according to its "face" attribute*/
	private int determineValue(String cardFace) {
		int value = 0;
		switch(cardFace) {
		  case "Ace":
		    value = 3;
		    break;
		  case "Deuce":
			  value = 2;
		    break;
		  case "Three":
			  value = 3;
			    break;
		  case "Four":
			  value = 4;
			    break;
		  case "Five":
			  value = 5;
			    break;
		  case "Six":
			  value = 6;
			    break;
		  case "Seven":
			  value = 7;
			    break;
		  case "Eight":
			  value = 8;
			    break;
		  case "Nine":
			  value = 9;
			    break;
		  case "Ten":
			  value = 10;
			  break;
		  case "Jack":
			  value = 14;
			    break;
		  case "king":
			  value = 12;
			    break;
		  case "Queen":
			  value = 11;
			    break;
		} // end of switch
		return value;
	}
} // end of class Card
