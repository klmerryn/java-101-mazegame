package sprites;

/**
 * A class representing the active players of the Vacuum Race
 * game. Vacuums are able to move around the game board and
 * accrue points by interacting with (cleaning) other Sprites
 * that have associated point values (Dirt, Dust). 
 * 
 * Vacuum class is responsible for:
 * 
 * 1. Keeping track of Vacuum's position, capacity, current
 * fullness, and score, and reporting those values as needed 
 * to other classes.
 * 2. Adjusting its coordinates when given a specific move.
 * 3. Keeping track of what kind of cell (Sprite) it is
 * resting on at any given point in the game.
 * 4. Cleaning a cell if it has the required capacity, adjusting
 * its score as appropriate.
 * 5. Emptying its contents at a Dumpster and adjusting its
 * capacity as appropriate.
 * 
 * 
 * @author kd
 *
 */
public class Vacuum extends Sprite implements Moveable {
	
	private int score = game.Constants.INIT_SCORE;
	private int capacity = game.Constants.CAPACITY;
	private int fullness = game.Constants.EMPTY;
	private Sprite under; //I want private Sprite<T> under or something

	/**
	 * Construct Vacuum
	 * @param symbol	Token ("1" or "2") representing Vacuum on game board.
	 * @param row		Row coordinate showing position of current Vacuum.
	 * @param column	Column coordinate showing position of current Vacuum.
	 * @param score		Integer representing points value of dirt cleaned.
	 * 					Higher scores are better.
	 * @param capacity	Total fullness ability of Vacuum (constant at 5)
	 * @param fullness	Fullness of volume, where 5 indicates a full vacuum unable to clean
	 * @param under		What kind of Sprite the Vacuum is acting upon (dirt, dust, or clean hallway)
	 */
	public Vacuum(char symbol, int row, int column, int capacity) {
		super(symbol, row, column);
		this.under = new CleanHallway(game.Constants.CLEAN,
				this.getRow(), this.getColumn());
	}

	/**
	 * Get current score for Vacuum, representing
	 * the point value of the dirt it has cleaned. The
	 * vacuum with the highest score at the end of the
	 * game is declared the winner.
	 * 
	 * @return	score	Points accumulated by Vacuum
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Returns maximum capacity (Dirt and DustBall storage) for 
	 * Vacuum. Once Vacuum's fullness reaches capacity, it must be 
	 * emptied at a Dumpster sprite to allow for more dirt 
	 * collection. 
	 * 
	 * @return	Maximum capacity (highest number of Dirts a Vacuum can 
	 * contain at any one time).
	 */
	public int getCapacity(){
		return this.capacity;
	}
	
	/**
	 * Indicates type of Sprite (tile) the vacuum is
	 * on.
	 * @return	under	Dirt, Dust, or CleanHallway Sprite
	 */
	public Sprite getUnder() {
		return this.under;
	}

	/**
	 * Replaces Sprite (tile) type that vacuum
	 * is resting on with new under. 
	 * @param	under		Dirt, Dust, or CleanHallway Sprite
	 */
	public void setUnder(Sprite under) {
		this.under = under;
	}
	
	/**
	 * Display current fullness of vacuum
	 * @return	Fullness (min: 0, max: 5)
	 */
	public int getFullness(){
		return this.fullness;
	}

	/**
	 * Moves the Vacuum to a new location specified
	 * by a (row, column) coordinate.
	 */
	@Override
	public void moveTo(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Empties the vacuum, returning fullness to 0. 
	 * Method is called only when vacuum reaches a Dumpster.
	 */
	public void empty() {
		this.fullness = game.Constants.EMPTY;
	}

	/**
	 * If vacuum is not filled to capacity, cleans the
	 * tile the vacuum is occupying by removing Dirt or
	 * Dustball instances, increments fullness variable
	 * to reflect higher Dirt content in the vacuum,
	 * increases the vacuum's score by the dirt's score value,
	 * changes cell's contents to CleanHallway to reflect 
	 * cleaning, and returns True.
	 * 
	 * Returns False if vacuum is too full to continue
	 * cleaning or if tile is not a cleanable class.   
	 */
	public boolean clean(int score){
		// Originally, this method also set this.under to CleanHallway.
		// However, that would have worked better if the method returned void
		// instead of bool. Now, clean() triggers the change of
		// this.under to CleanHallway in the VacuumGame class.
		
		boolean cleaned = false;
		if (this.fullness < this.capacity && (this.under instanceof Dirt)) {
 			this.fullness += game.Constants.FULLNESS_INC;
			this.score += score;
			cleaned = true;
			}
		return cleaned;
		}
		
	}

