# 🃏 War Of Cards Game 

"WarOfCards" is an engaging card game implemented in Java, designed to simulate the classic card game "War" between a player and the computer.
<p align="left">
  <img src="https://github.com/NaomiEisen/20554-Advanced-Programming-with-Java/assets/166138356/34c7ef4d-1a07-4362-a3b0-8f8473f30940" alt="WarOfCards" width="250" height="127">
</p>
 ![WarOfCardsPlay](https://github.com/NaomiEisen/20554-Advanced-Programming-with-Java/assets/166138356/34c7ef4d-1a07-4362-a3b0-8f8473f30940)
 ![WarOfCards2](https://github.com/NaomiEisen/20554-Advanced-Programming-with-Java/assets/166138356/aae9baa2-f07e-4d18-803a-1a480f11a519)
 ![WarOfCardsPlay3](https://github.com/NaomiEisen/20554-Advanced-Programming-with-Java/assets/166138356/a46d2961-7df9-4f05-a582-8a5503181e5b)
 ![WarOfCardsPlay4](https://github.com/NaomiEisen/20554-Advanced-Programming-with-Java/assets/166138356/3bd1ca10-7e7f-4b4d-9d86-c9b82805ae71)

## Features
- Interactive Gameplay: Players engage in a card battle against the computer, drawing cards from their respective decks and comparing their values to determine the winner of each round.
- Graphical User Interface: Utilizes JOptionPane to provide interactive dialogues that inform the player of game progress, wins, losses, and ties.
- Dynamic Deck Management: The game handles two decks of cards, managing shuffles, splits, and card transfers between decks during gameplay.
- War Mechanics: Implements the "war" scenario when players draw cards of equal value, leading to additional draws and increased stakes.
Endgame Scenarios: Provides specific dialogs for winning, losing, or tying the game, enhancing the game experience with thematic graphics and messages.

## How It Works
Classes and Their Roles
- **Main:** Launches the game by creating an instance of Game.
- **DeckOfCards:** Manages the deck operations, including shuffling, splitting the deck between the player and the computer, and updating game statuses based on the deck's state.
- **Card:** Represents individual playing cards, each associated with a face, suit, and computed value based on the card's rank.
- **Graphics:** Handles all user interactions through dialog messages using various icons to represent game states and actions.
- **Status Classes:**  Represent different game conditions and outcomes for the player and computer, including round results and overall game status.
  
## Game Flow
1. Initialization: At startup, the deck is created, shuffled, and split evenly between the player and the computer.
2. Gameplay Loop:
- Players alternately draw cards from the top of their decks.
- The game compares the values of the drawn cards to determine the round winner.
- Cards are transferred to the winner's deck, or additional cards are drawn in the event of a "war".
3. Checking Game Status: After each round, the game checks if either player has won by accumulating all cards or lost by depleting their deck.
  
## Graphical Interface
- Dialogs: Interactive dialogs guide the player through the game, providing options to continue or exit after each round and displaying messages corresponding to different game events like wins, losses, and ties.
  
## Requirements
- Java Development Kit (JDK)
- Swing library (included with JDK)

## Credits
### Icons
All icons used in the game are sourced from [flaticon](https://www.flaticon.com/).
