package sprites;

public class Dirt extends Sprite {
	
	// Value for cleanup - do I need this or can I just construct it?
	protected int value;
	
	/**
	 * Constructs Dirt
	 * @param symbol	. or o if dirt or Dustball
	 * @param row		location of dirt (which array)
	 * @param column	location of dirt (which element of an array)
	 * @param value		points for cleaning up this dirt
	 */
	public Dirt(char symbol, int row, int column, int value) {
		super(symbol, row, column);
		this.value = value; //maybe I don't need this if I declare it at the beginning but I think i do?
	}

	/**
	 * Getter method for Value (points) of cleaning dirt
	 * @return value	1 or 2 depending on identity of dirt		
	 */
	public int getValue() {
		return value; // or this.value??
	}

	// do I need this method?
	public void setValue(int value) {
		this.value = value;
	}

	
}
