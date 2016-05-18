package game;

import java.util.Arrays;

/**
 * A class which creates and stores a 2D Array ([][]) of
 * generic type. 
 * 
 * ArrayGrid is responsible for:
 * 1. Storing, getting and setting cells in the grid, specified by 
 * row and column coordinates.
 * 2. Printing a formatted text representation of the grid
 * 3. Determining if two ArrayGrids are equal (contain objects of
 * the same class and value at the same indices).
 *  
 * @author kd
 *
 */
public class ArrayGrid<T> implements Grid<T> {

	private T[][] gameArrayGrid;
	private int numRows;
	private int numColumns;
	
	@SuppressWarnings("unchecked") // Aware I am casting an Obj[][] as T[][]
	public ArrayGrid(int numRows, int numColumns){
		this.gameArrayGrid = (T[][]) new Object[numRows][numColumns];
		this.numRows = numRows;
		this.numColumns = numColumns;
		}

	@Override
	public void setCell(int row, int column, T t){
		this.gameArrayGrid[row][column] = t;
	}
	
	@Override
	public T getCell(int row, int column){
		return this.gameArrayGrid[row][column];
	}
	
	@Override
	public int getNumRows(){
		return this.numRows;
	}
	
	@Override public int getNumColumns(){
		return this.numColumns;
	}
	/**
	 * Compares ArrayGrid with input object to 
	 * determine if they are equal.
	 * 
	 * Two ArrayGrids are equal if in each coordinate position, 
	 * their game arrays contain the same values
	 * (a deepEquals() equality of Sprite types and indices).
	 * 
	 * @param	obj		Foreign object tested for equality
	 * @return	isEqual	True only if corresponding elements
	 * in each position of grid are equal.
	 */
	@Override
	public boolean equals(Object obj){
		boolean isEqual = false;
		if (this == obj) {
			isEqual = true;
		}
		else if (obj instanceof ArrayGrid) {
			ArrayGrid<?> otherArray = (ArrayGrid<?>) obj; 
			isEqual = Arrays.deepEquals(otherArray.gameArrayGrid, this.gameArrayGrid);
			}
		return isEqual;
	}

	/**
	 * Print the game board (grid) using the appropriate
	 * symbols for each Sprite. 
	 */
	@Override
	public String toString(){
		String gridPrintOut = "";
		String newLine = System.getProperty("line.separator");
		
		for (int i = 0; i < this.numRows; i ++){
			for (int j = 0; j < this.numColumns; j ++) {
				gridPrintOut += this.gameArrayGrid[i][j].toString();
			}
			gridPrintOut += newLine;
			}
		return gridPrintOut.trim(); // remove the extra newline character
	}

}