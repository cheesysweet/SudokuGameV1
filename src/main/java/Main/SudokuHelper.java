package Main;

/**
 * Singleton helper class that contains general helper methods used by both the SudokuSolution and the SudokuSolver class.
 */
public class SudokuHelper implements Constants {
    public static final SudokuHelper INSTANCE  = new SudokuHelper();

    private SudokuHelper() {}

    /**
     * @param arr an array of integers
     * @param checkValue the number to be checked if it is contained inside arr.
     * @return true if arr contains checkValue, otherwise false.
     */
    public boolean elementNotInArray(int[] arr, int checkValue) {
        for (int element : arr) {
            if (element == checkValue) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if grid is complete, otherwise false.
     */
    public boolean gridIsComplete(int[][] grid) {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (grid[r][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * @param square 3x3 inner square of the grid.
     * @param checkValue the number to be checked if it is contained inside square.
     * @return true if square contains checkValue, otherwise false.
     */
    public boolean elementNotinSquare(int[][] square, int checkValue) {
        return elementNotInArray(square[0], checkValue) && elementNotInArray(square[1], checkValue) && elementNotInArray(square[2], checkValue);
    }

    /**
     * @param col index representing which column the cell belongs to.
     * @param row index representing which row the cell belongs to.
     * @return the 3x3 square that the given cell belongs to.
     */
    public int[][] getSquare(int[][] grid, int col, int row) {
        int[][] square = new int[3][3];
        if (row < 3) {
            if (col < 3) {
                for (int x = 0; x < 3; x++) {
                    System.arraycopy(grid[x], 0, square[x], 0, 3);
                }
            } else if (col < 6) {
                for (int x = 0; x < 3; x++) {
                    System.arraycopy(grid[x], 3, square[x], 0, 3);
                }
            } else {
                for (int x = 0; x < 3; x++) {
                    System.arraycopy(grid[x], 6, square[x], 0, 3);
                }
            }
        } else if (row < 6) {
            if (col < 3) {
                for (int x = 3; x < 6; x++) {
                    System.arraycopy(grid[x], 0, square[x - 3], 0, 3);
                }
            } else if (col < 6) {
                for (int x = 3; x < 6; x++) {
                    System.arraycopy(grid[x], 3, square[x - 3], 0, 3);
                }
            } else {
                for (int x = 3; x < 6; x++) {
                    System.arraycopy(grid[x], 6, square[x - 3], 0, 3);
                }
            }
        } else {
            if (col < 3) {
                for (int x = 6; x < 9; x++) {
                    System.arraycopy(grid[x], 0, square[x - 6], 0, 3);
                }
            } else if (col < 6) {
                for (int x = 6; x < 9; x++) {
                    System.arraycopy(grid[x], 3, square[x - 6], 0, 3);
                }
            } else {
                for (int x = 6; x < 9; x++) {
                    System.arraycopy(grid[x], 6, square[x - 6], 0, 3);
                }
            }
        }
        return square;
    }

    /**
     * @param grid the grid to be used
     * @param col the column number
     * @return array of values in given column
     */
    public int[] getColumn(int[][] grid, int col) {
        int[] column = new int[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++){
            column[i] = grid[i][col];
        }
        return column;
    }

}
