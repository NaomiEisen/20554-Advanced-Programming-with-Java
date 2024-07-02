# Four In A Row Game

"Four In A Row" is a classic connect-four game implemented in JavaFX. Players alternate turns to drop colored tokens into a seven-column, six-row vertically suspended grid. The objective is to be the first to form a horizontal, vertical, or diagonal line of four of one's own tokens.

## Features
- Interactive Gameplay: Engage in a classic game of strategy against another player, with easy-to-use graphical user interface.
- Dynamic Messaging: Real-time feedback on game events like illegal moves, victories, or ties through dialog alerts.
  
## Components
### FourInARow: 
The main application class that sets up the game window and loads the interface.
### FourInARowController: 
Manages user interactions, including token placement and game resets.
### FourInARowModel: 
Handles the game's logic, such as checking for wins and managing the game grid state.
## Messages: 
Provides alerts and messages to the players for various game states and actions.

## How to Play
1. Start the Game: Launch the application to open the game window.
2. Player Interaction:
- Players click buttons above each column to drop their tokens into that column.
- The game automatically alternates between pink and blue tokens.
3. Winning the Game:
The first player to align four tokens horizontally, vertically, or diagonally wins the game.
If all columns are filled without any player forming a line of four, the game ends in a tie.
4. Restarting the Game:
Players can press the 'clear' button to reset the game at any time.

## Requirements
- Java 8 or later.
- JavaFX library.
