import Main.Constants;
import Main.SudokuHelper;
import Main.SudokuSolution;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

/**
 * Test class that tests methods of the {@link SudokuSolution} class.
 */
public class SudokuSolutionTest implements Constants {
    private final SudokuSolution sudokuSolution = new SudokuSolution();

    /**
     * Validates that method checking if cells are within range does so correctly.
     */
    @Test
    public void testCellsAreWithinRange() {
        assertTrue(cellsAreWithinRange());
    }

    /**
     * Validate that method checking if all the rows in the solution are valid (according to the rules of Sudoku) does so
     * correctly.
     */
    @Test
    public void testRowsAreValid() {
        assertTrue(rowsAreValid());
    }

    /**
     * Validate that method checking if all the columns in the solution are valid (according to the rules of Sudoku) does so
     * correctly.
     */
    @Test
    public void testColumnsAreValid() {
        assertTrue(columnsAreValid());
    }

    /**
     * Validate that method checking if all the nine inner 3x3 squares in the solution are valid (according to the rules
     * of Sudoku) does so correctly.
     */
    @Test
    public void testInnerSquaresAreValid() {
        assertTrue(innerSquaresAreValid());
    }


    private boolean cellsAreWithinRange() {
        int[][] grid = sudokuSolution.getGrid();
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell < 1 || cell > 9) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean rowsAreValid() {
        int[][] grid = sudokuSolution.getGrid();
        boolean[] unique = new boolean[GRID_SIZE + 1];
        for (int r = 0; r < GRID_SIZE; r++) {
            Arrays.fill(unique, false);
            for (int c = 0; c < GRID_SIZE; c++) {
                int cell = grid[r][c];
                if (unique[cell]) {
                    return false;
                }
                unique[cell] = true;
            }
        }
        return true;
    }

    private boolean columnsAreValid() {
        int[][] grid = sudokuSolution.getGrid();
        boolean[] unique = new boolean[GRID_SIZE + 1];
        for (int r = 0; r < GRID_SIZE; r++) {
            Arrays.fill(unique, false);
            for (int c = 0; c < GRID_SIZE; c++) {
                int cell = grid[c][r];
                if (unique[cell]) {
                    return false;
                }
                unique[cell] = true;
            }
        }
        return true;
    }

    private boolean innerSquaresAreValid() {
        int[][] grid = sudokuSolution.getGrid();
        boolean[] unique = new boolean[GRID_SIZE + 1];
        for (int i = 0; i < GRID_SIZE - 2; i += 3) {
            for (int j = 0; j < GRID_SIZE; j += 3) {
                Arrays.fill(unique, false);
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        int rowNum = i + k;
                        int colNum = j + l;
                        int cell = grid[rowNum][colNum];
                        if (unique[cell]) {
                            return false;
                        }
                        unique[cell] = true;
                    }
                }
            }
        }
        return true;
    }
}
