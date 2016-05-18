package ui;

import java.util.Scanner;
import game.VacuumGame;

/**
 * A class that runs the console-based version of the 
 * Vacuum Race game.
 * 
 * Class is responsible for:
 * 
 * 1. Launching the Vacuum Race Game
 * 2. Displaying a text version of the game board, along
 * with other pertinent information such as current score
 * 3. Prompting user for new moves, reading those moves,
 * and updating the game accordingly.
 * 4. Displaying a message when the game terminates
 * 
 * @author kd
 *
 */
public class TextUI implements UI {
	
	private VacuumGame game;

	public TextUI(VacuumGame game) {
		this.game = game;
	}
	
	/**
	 * Gets VacuumGame.
	 * @return
	 */
	public VacuumGame getGame(){
		return this.game;
	}

	/**
	 * Launches Vacuum Race game by reading grid setup file and 
	 * continuing to prompt for moves while the game is in legal
	 * progress.
	 * 
	 */
	@Override
	public void launchGame() {

		// Scanner that will be used to read responses
		Scanner input = new Scanner(System.in);
		
		String newLine = System.getProperty("line.separator");
		// Display initial instructions to user
		System.out.println("Welcome to the Great Vacuum Race!" + newLine);
		System.out.println("The rules are simple. use a,s,d,w to move vacuum 1"
				+ " and j,k,l,i to move vacuum 2. Collect points by landing on "
				+ "the dirty spaces (.) or the dustballs (o), and don't forget "
				+ "to empty your vacuum at the dumpsters (U) so you can keep "
				+ "on getting points for cleaning. You can store max 5 dirt at once.");
		System.out.println(newLine + "Okay, time to tidy up! Let the Vacuum Race begin.");

		// Set up and print the board, and launch the game
		while (!this.game.gameOver()){
			System.out.println(newLine + this.game.getGrid().toString());
			
			System.out.println("Enter a move: ");
			char nextMove = input.next().charAt(0);
				
			// if move was legal and applicable, update the board.
			if (this.game.move(nextMove)) {
				// show current scores and fullness
				this.displayScore();
			}
			else {
				System.out.println("Bad move, bro. Try again.");
			}				
		}
		input.close(); 		
		this.displayWinner();
	}
	
	/**
	 * Displays a message containing the winner of the game and 
	 * their winning score.
	 */
	@Override
	public void displayWinner() {
		// Note: this code is consistent with GUI getWinner() code. 
		// However, if there is a tie, method defaults to
		// P2 winning. Left alone for consistency, but it's a bug.
		String message;
		
	    int won = this.game.getWinner();

		if (won == 1) {
			message = "Congratulations Player 1! You won the game with a score of " + 
					this.game.getVacuumOne().getScore() + ".";
		} else { 
			message = "Congratulations Player 2! You won the game with a score of " + 
					this.game.getVacuumTwo().getScore() + ".";
		}
		System.out.println(message);
	}
	
		
	/**
	 * Prints current scores and fullness levels for Vacuums 1 and 2 
	 * to help user(s) keep track of their progress.
	 * 
	 */
	private void displayScore(){
		System.out.println("Vacuum 1: Current score "
				+ this.game.getVacuumOne().getScore() + ", Current fullness "
				+ this.game.getVacuumOne().getFullness() + "(max " 
				+ this.game.getVacuumOne().getCapacity() + ")");
		System.out.println("Vacuum 2: Current score "
				+ this.game.getVacuumTwo().getScore() + ", Current fullness "
				+ this.game.getVacuumTwo().getFullness() + "(max " 
				+ this.game.getVacuumTwo().getCapacity() + ")");
	}
}
