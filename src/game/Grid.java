package game;

/**
 * 
 * @author kd
 *
 */
public interface Grid<T> {
	
	void setCell(int row, int column, T item);
	
	T getCell(int row, int column);
	
	int getNumRows();
	
	int getNumColumns();
	
	boolean equals(Object object);
	
	/**
	 * A representation of the game printed out as a 
	 * text array (maze).
	 * @return		Return formatted grid of game
	 */
	public String toString();

	

}
