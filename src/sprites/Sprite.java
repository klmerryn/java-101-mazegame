package sprites;

/**
 * Sprite class. Initialize characters in
 * Vacuum Race game.
 * @author kd
 *
 */
public abstract class Sprite {
	
	protected char symbol;
	protected int row;
	protected int column;
	
	/**
	 * 
	 * @param symbol	
	 * @param row
	 * @param column
	 */
	public Sprite(char symbol, int row, int column) {
		this.symbol = symbol;
		this.row = row;
		this.column = column;
	}	
	
	/**
	 * Returns display symbol for Sprite.
	 * @return
	 */
	public char getSymbol() {
		return this.symbol;
	}

	/**
	 * Returns Row coordinate for Sprite's position in game.
	 * @return	row
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Returns Column coordinate for Sprite's position in game.
	 * @return
	 */
	public int getColumn() {
		return this.column;
	}

	/**
	 * Returns a String representation of the Sprite.
	 */
	@Override
	public String toString() {
		return String.valueOf(this.symbol);
	}
	
	/**
	 * Returns True if two Sprite objects are equal.
	 * Sprite equality is defined as two objects of the same
	 * Class type, with the same display symbol, at the same
	 * coordinates in the grid. 
	 */
	@Override
	public boolean equals(Object other) {
		boolean isEqual = false;
		
		if (other == this) {
			isEqual = true;
		}
		else if (other instanceof Sprite) {
			if (((Sprite) other).getSymbol() == this.getSymbol()
					&& ((Sprite) other).getRow() == this.getRow()
					&& ((Sprite) other).getColumn() == this.getColumn()
					&& ((Sprite) other).getClass() == this.getClass());
			isEqual = true;
		}	
		return isEqual;
	}
}
