package Main;

import java.util.Collections;

/**
 * Class responsible for generating a valid Sudoku grid represented by a 2D array. Follows the Singleton Design Pattern.
 */
public class SudokuSolution implements Constants {
    private final SudokuHelper helper = SudokuHelper.INSTANCE;
    private final int[][] grid = new int[GRID_SIZE][GRID_SIZE];

    /**
     * Fills the grid with numbers.
     */
    public SudokuSolution() {
        fillGrid();
    }

    /**
     * A recursive function to check possible combinations of numbers until the grid is completed with valid number patterns.
     * @return true if the creation of the grid was successful, otherwise false.
     */
    private boolean fillGrid() {
        int col = 0;
        int row = 0;
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            row = Math.floorDiv(i, GRID_SIZE);
            col = i % GRID_SIZE;
            if (grid[row][col] == 0) {
                Collections.shuffle(NUMBERS);
                for (int num : NUMBERS) {
                    if (helper.elementNotInArray(grid[row], num)) {
                        int[] column = helper.getColumn(grid, col);
                        if (helper.elementNotInArray(column, num)) {
                            int[][] square = helper.getSquare(grid, col, row);
                            if (helper.elementNotinSquare(square, num)) {
                                grid[row][col] = num;
                                if (helper.gridIsComplete(grid)) {
                                    return true;
                                } else {
                                    if (fillGrid()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
        grid[row][col] = 0;
        return false;
    }

    /**
     * @return the Sudoku board as a 2D array.
     */
    public int[][] getGrid() {
        return grid;
    }
}
