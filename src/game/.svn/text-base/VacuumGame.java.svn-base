package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;

/**
 * A class that represents the basic functionality of the vacuum game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class VacuumGame {

    // a random number generator to move the DustBalls
    private Random random;

    // the grid
    private Grid<Sprite> grid;

    // the first player
    private Vacuum vacuum1;

    /// the second player
    private Vacuum vacuum2;

    // the dirt (both static dirt and mobile dust balls)
    private List<Dirt> dirts;

    // the dumpsters
    private List<Dumpster> dumpsters;
    
    // char[] of legal keyboard inputs
    private char[] vacuumMoves = new char[]{Constants.P1_LEFT, 
    		Constants.P1_RIGHT, Constants.P1_UP, Constants.P1_DOWN, 
    		Constants.P2_LEFT, Constants.P2_RIGHT, Constants.P2_UP, Constants.P2_DOWN};
    
    
    /**
     * Gets grid (ArrayGrid), which is a 2D array (T[][])
     * that holds the current state of the game.
     * 
     * @return	grid	
     */
    public Grid<Sprite> getGrid(){
    	return this.grid;
    }
    
    /**
     * Gets number of Rows in the game grid.
     * @return	Number of rows
     */
    public int getNumRows(){
    	return this.grid.getNumRows();
    }

    /**
     * Gets number of Columns in game grid.
     * @return	Number of rows
     */
    public int getNumColumns(){
    	return this.grid.getNumColumns();
    }
    
    /**
     * Gets and returns Vacuum 1 sprite (Player 1).
     * @return	Vacuum 1
     */
    public Vacuum getVacuumOne(){
    	return this.vacuum1;
    }
    
    /**
     * Gets and returns Vacuum 2 sprite. (Player 2).
     * @return Vacuum 2.
     */
    public Vacuum getVacuumTwo(){
    	return this.vacuum2;
    }
    
    /**
     * Tests if user's keystroke command for move is a valid input.
     * Helper method for move(). 
     * 
     * @param move		Character Input representing keystroke
     * @return boolean	True for accepted keystroke commands
     */
    private boolean legalInput(char moveCharacter){
    	boolean legal = false;
    	for (char move: this.vacuumMoves) {
    		if (moveCharacter == move) {
    			legal = true;
    			break;
    		}
    	}
    	return legal;
    }    
    
    /**
     * Determines which vacuum, if any, is the target of the user's 
     * keystroke move, and returns that vacuum (or null if invalid
     * move).
     * 
     * Helper method for move().
     * 
     * @param move	User-input keystroke command.
     * @return		Activated Vacuum.
     */
    private Vacuum whichVacuum(char move){
    	Vacuum vacuum;
    	
    	switch (move) {
    	
    	case game.Constants.P1_LEFT:
    	case game.Constants.P1_RIGHT:
    	case game.Constants.P1_UP:
    	case game.Constants.P1_DOWN:
    		vacuum = vacuum1;
    		break;
    		
    	case game.Constants.P2_LEFT:
    	case game.Constants.P2_RIGHT:
    	case game.Constants.P2_UP:
    	case game.Constants.P2_DOWN:
    		vacuum = vacuum2;
    		break;
    		
    	default:
    		vacuum = null;
    		break;
    	}
    return vacuum;	
    }
        
	/**
	 * Converts a character input to a move by  
	 * providing new coordinates for the Vacuum 
	 * that reflect the direction indicated by 
	 * the user's keyboard input.
	 * 
	 * Helper method for move().
	 * 
	 * @param 	move	User-input keystroke command.
	 * @return 	coordTo	New row and column coordinates of
	 * Vacuum after proposed move.
	 */
    private int[] tryCoord(char move, Vacuum vacuum){
    	int[] coordTo;
    	
    	switch (move) {
    	
    	case game.Constants.P1_LEFT:
    	case game.Constants.P2_LEFT:
    		coordTo = new int[]{vacuum.getRow(),
    				vacuum.getColumn() + game.Constants.LEFT};
    		break;
    		
    	case game.Constants.P1_RIGHT:
    	case game.Constants.P2_RIGHT:
    		coordTo = new int[]{vacuum.getRow(),
    				vacuum.getColumn() + game.Constants.RIGHT};
    		break;
		
    	case game.Constants.P1_UP:
    	case game.Constants.P2_UP:
    		coordTo = new int[]{vacuum.getRow() + game.Constants.UP,
    				vacuum.getColumn()};
    		break;
    	
    	case game.Constants.P1_DOWN:
    	case game.Constants.P2_DOWN:
    		coordTo = new int[]{vacuum.getRow() + game.Constants.DOWN,
    				vacuum.getColumn() };
    		break;
    		
    	default:
    		coordTo = new int[]{0,0};
    		break; // not necessarily needed
    	}    	
    	return coordTo;
    }
    
     /**
     * Returns True if target cell (defined by (row, column)
     * coordinates) is permitted destination for Sprite to move. 
     * Permitted destination cells are cells in game's GridArray 
     * of types Dirt (Dirt, DustBall) or CleanHallway. 
     *  
     * @param row	Target row (x-coordinate) 
     * @param col	Target column (y-coordinate)
     * @return		True if destination cell is not occupied
     * by a Wall or a Vacuum.
     */
    private boolean isLegalCell(int row, int col){
    	return !(this.grid.getCell(row,  col) instanceof Wall)
    			&& !(this.grid.getCell(row,  col) instanceof Vacuum);  
    }

    /**
     * Moves single DustBall randomly by generating random row
     * or column adjustment (4 possible adjustments, plus the
     * default result of 0 adjustment).
     * If the new coordinates are legal, moves the DustBall.
     *
     * Helper method for dustMoveAll().
     * Restrictions: DustBall cannot land on Wall, Vacuum, or Dumpster.
     *  
     * @param dust	Instance of DustBall sprite to move.
     */
    private void dustRandomMove(DustBall dust){
    	
    	// (There may be more efficient way to do this but I'm not sure.)
    	// Note: Original implementation had DustBalls moving randomly 
    	// anywhere on the board. However, according to Piazza @486
    	// (Jonathan), Dustballs may only move to adjacent cells.
    	
    	int randRow = 0; // default is no adjustment
		int randCol = 0;
    	int i = this.random.nextInt()%3;
    	
    	switch(i) {
		
		case 0:
			randRow = dust.getRow();
			randCol = dust.getColumn() + game.Constants.LEFT;
			break;
		
		case 1:
			randRow = dust.getRow();
			randCol = dust.getColumn() + game.Constants.RIGHT;
			break;
			
		case 2:
			randRow = dust.getRow()  + game.Constants.UP;
			randCol = dust.getColumn();
			break;
		
		case 3:
			randRow = dust.getRow() + game.Constants.DOWN;
			randCol = dust.getColumn();
			break;
		}
		
		// Check for legality, then do the move. Set old DustBall cell to Dirt. 	
		if ((isLegalCell(randRow, randCol)) && !(grid.getCell(randRow,  randCol)
				instanceof Dumpster) && !(grid.getCell(randRow, randCol) instanceof Vacuum)) {
			this.grid.setCell(dust.getRow(), dust.getColumn(), new Dirt(game.Constants.DIRT,
					dust.getRow(), dust.getColumn(), game.Constants.DIRT_SCORE));
			dust.moveTo(randRow, randCol);
			this.grid.setCell(randRow, randCol, dust);
		}
    }
    
    /**
     * Moves all DustBalls in this.dirts list randomly, then
     * updates dirts list to reflect changes. 
     * 
     * Helper method for move().
     */
    private void dustMoveAll(){
    	
    	// Move the DustBalls
    	for (Dirt singleDustBall: this.dirts){
    		if (singleDustBall instanceof DustBall) {
    			dustRandomMove((DustBall) singleDustBall);
    		}
    	}
    	
    	// Update the Dirts list to reflect vanished and added
    	for (Dirt dirt: this.dirts) {
    		if (!this.dirts.contains(dirt)) {
    			this.dirts.add(dirt);
    		}
    	}	
    }
    
    /**
     * Moves P1 or P2's Vacuum to new cell conditional on the following.
     *   1. User input must be legal keystroke command.
     *   2. Destination cell must not be occupied by Wall or Vacuum.
     *   
     * If a Vacuum lands on a Dumpster, it is emptied.
     * Once a Vacuum has moved, the VacuumGame is updated to reflect
     * the changes in cell occupancy. 
     * 
     * A Vacuum attempts to clean the new tile it is occupying,
     * then all DustBall Sprites move randomly on the board,
     * and the game's list of Dirts updates to reflect changes.
     *           
     * @param 	nextMove	User-entered keystroke command.
     * @return	moved		True if Vacuum moves successfully.
     */
    public boolean move(char nextMove){
    	boolean moved = false;
    	Vacuum activeVacuum = whichVacuum(nextMove);
    	
    	// activeVacuum only null if invalid move. Otherwise, proceed with move
    	if (!(activeVacuum == null)){
    		int[] tryCoordMove =  tryCoord(nextMove, activeVacuum);
    		if (legalInput(nextMove) && isLegalCell(tryCoordMove[0], tryCoordMove[1])) {
    			
    			// compute prospective score increase for clean
    			int score = getScore(tryCoordMove[0], tryCoordMove[1]);
    			
    			// Empty Vacuum, if possible
    	    	if (activeVacuum.getUnder() instanceof Dumpster) {
    				activeVacuum.empty();
    	    	}
    			
    			// Set Vacuum.under to occupy current cell (replacing Vacuum)
    	    	this.grid.setCell(activeVacuum.getRow(), activeVacuum.getColumn(), 
    	        			activeVacuum.getUnder());    		    
    	    	
    	    	// Now move the Vacuum 
    			activeVacuum.setUnder(this.grid.getCell(tryCoordMove[0], tryCoordMove[1]));    			
    			activeVacuum.moveTo(tryCoordMove[0],  tryCoordMove[1]);
    			this.grid.setCell(tryCoordMove[0],  tryCoordMove[1], activeVacuum);
    			
    			moved = true;
    			    	    	
    			// Clean, if possible
    			if (activeVacuum.clean(score)) {
    				this.dirts.remove(activeVacuum.getUnder());
    				activeVacuum.setUnder(new CleanHallway(game.Constants.CLEAN, 
    						activeVacuum.getRow(), activeVacuum.getColumn()));
    			}
    			
    			// Move DustBalls and update dirts list to reflect changes
    			this.dustMoveAll(); // better style to have this. on methods too? or just bare method? 
    		}
    	}
    	return moved;
    }
    
    /**
     * Returns points value for successful cleaning
     * of the dirt sprite (Dirt or Dust).
     * 
     * @param	row		Row coordinate of target Sprite
     * @param	column	Column coordinate of target Sprite
     * @return			Points gained if cleaned (default: 0).
     */
    private int getScore(int row, int column){
    	int scoreValue = 0;
    	Sprite cell = this.grid.getCell(row, column);
    	if (cell instanceof Dirt){
    		scoreValue = ((Dirt) cell).getValue();
    	}
    	return scoreValue;
    }
    /**
     * Returns True if the Vacuum Race game is over.
     * Game ends when there are no more dirts to clean, and
     * both Vacuums have been emptied.
     * 
     * @return	boolean		True if game over conditions are met. 
     */
    public boolean gameOver() {
    	
    	// Note: Piazza @256: neither Vacuum.under may be Dirt.
    	// Checking fullness and Vacuum.under() may be overkill
    	return (this.dirts.size() == 0
    			&& this.getVacuumOne().getFullness() == 0
    			&& this.getVacuumTwo().getFullness() == 0
    			&& !(this.getVacuumOne().getUnder() instanceof Dirt)
    			&& !(this.getVacuumTwo().getUnder() instanceof Dirt));
    }
    
    /**
     * Returns the player number of the winning Vacuum.
     * Vacuum wins if it has a higher score.
     * 
     * @return	Integer number of winning Player (1 or 2).
     */
    public int getWinner() {

    	// Note: there is a bug in starter code.
    	// Win defaults to P2 if tie. 
    	// Code left unchanged for consistency and due to autotesting.
    	
    	if (this.vacuum1.getScore() > this.vacuum2.getScore()){
    		return Character.getNumericValue(game.Constants.P1);
    		}
    	return Character.getNumericValue(game.Constants.P2);
    	}
        
    /**
     * Creates a new VacuumGame that corresponds to the given input text file.
     * Assumes that the input file has one or more lines of equal lengths, and
     * that each character in it (other than newline) is a character that 
     * represents one of the Sprites in this game.
     * @param 	layoutFileName	Path to the input grid file
     * @throws	IOException		Exception if invalid filename 
     */
    public VacuumGame(String layoutFileName) throws IOException {
        this.dirts = new ArrayList<Dirt>();
        this.dumpsters = new ArrayList<Dumpster>(); // Jen: may not need this
        this.random = new Random();
        
        // open the file, read the contents, and determine 
        // dimensions of the grid
        int[] dimensions = getDimensions(layoutFileName);
        this.grid = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);

        // open the file again, read the contents, and store them in grid
        Scanner sc = new Scanner(new File(layoutFileName));
        
        // Examine one line at a time to parse symbols into Sprites.
        // Lines are broken into char[] which are then read one index at a time.
        int r = 0;
        while (sc.hasNextLine() && r < dimensions[0]) {
        	char[] tokens = sc.nextLine().toCharArray();
        	for (int c = 0; c < dimensions[1]; c++) {
            	if (tokens[c] == game.Constants.CLEAN) {
            		CleanHallway cleanSprite = new CleanHallway(game.Constants.CLEAN, r, c);
            		this.grid.setCell(r, c, (CleanHallway) cleanSprite); // too much casting?
            	}
            	else if (tokens[c] == game.Constants.DIRT) {
            		Dirt dirtSprite = new Dirt(game.Constants.DIRT, r, c, game.Constants.DIRT_SCORE);
            		this.dirts.add(dirtSprite);
            		this.grid.setCell(r, c, (Dirt) dirtSprite);
            	}
            	else if (tokens[c] == game.Constants.DUMPSTER) {
            		Dumpster dumpsterSprite = new Dumpster(game.Constants.DUMPSTER, r, c);
            		this.grid.setCell(r, c, (Dumpster) dumpsterSprite);
            		this.dumpsters.add(dumpsterSprite);
            	}
            	else if (tokens[c] == game.Constants.DUST_BALL) {
            		DustBall dustBallSprite = new DustBall(game.Constants.DUST_BALL, r, c, game.Constants.DUST_BALL_SCORE);
            		this.grid.setCell(r, c, (DustBall) dustBallSprite);
            		this.dirts.add(dustBallSprite);
            	}
            	else if (tokens[c] == game.Constants.WALL) {
            		Wall wallSprite = new Wall(game.Constants.WALL, r, c);
            		this.grid.setCell(r, c, (Wall) wallSprite);
            	}
            	else if (tokens[c] == game.Constants.P1){
            		Vacuum vacuumSprite = new Vacuum(game.Constants.P1, r, c, game.Constants.CAPACITY);
            		this.grid.setCell(r, c, (Vacuum) vacuumSprite); // or should it be CleanHallway? TBD.
            		vacuum1 = (Vacuum) this.grid.getCell(r, c); // casting; will this work out?
            	}
            	else if (tokens[c] == game.Constants.P2){
            		Vacuum vacuumSprite = new Vacuum(game.Constants.P2, r, c, game.Constants.CAPACITY);
            		this.grid.setCell(r, c, (Vacuum) vacuumSprite);
            		vacuum2 = (Vacuum) this.grid.getCell(r, c);
            	}
            	else {
            		System.out.println("Character mismatch in starter file at"
            				+ " (" + r + ", " + c + ")");
            	}
        	}
        	r++;
        }
        sc.close();
    }

        
    /**
     * Returns the dimensions of the grid in the file named layoutFileName.
     * @param layoutFileName path of the input grid file
     * @return an array [numRows, numCols], where numRows is the number
     * of rows and numCols is the number of columns in the grid that
     * corresponds to the given input grid file
     * @throws IOException
     */
    private int[] getDimensions(String layoutFileName) throws IOException {       

        Scanner sc = new Scanner(new File(layoutFileName));

        // find the number of columns
        String nextLine = sc.nextLine();
        int numCols = nextLine.length();

        int numRows = 1;

        // find the number of rows
        while (sc.hasNext()) {
            numRows++;
            nextLine = sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }
    
    
    /**
     * Returns the Sprite at any given location on the game board.
     * @param i		Row coordinate
     * @param j		Column Coordinate
     * @return		Sprite at (row, column).
     */
	public Sprite getSprite(int i, int j) {
		return this.getGrid().getCell(i, j);
	}

}
