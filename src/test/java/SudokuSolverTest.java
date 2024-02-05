import GUI.Difficulty;
import Main.Constants;
import Main.SudokuSolver;
import org.junit.Test;
import Main.SudokuSolution;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class that tests methods of the {@link SudokuSolver} class.
 */
public class SudokuSolverTest implements Constants {
    private final int marginOfError = 10;
    private final SudokuSolver sudokuSolver = new SudokuSolver();

    /**
     * Validate that method getting the amount of possible solution to an incomplete Sudoku grid does so correctly.
     */
    @Test
    public void testGetAmountOfSolutions() {
        int[][] exampleGrid1 = {{2, 9, 5, 7, 4, 3, 8, 6, 1},
                                {4, 3, 1, 8, 6, 5, 9, 2, 7},
                                {8, 7, 6, 1, 9, 2, 5, 4, 3},
                                {3, 8, 7, 4, 5, 9, 2, 1, 6},
                                {6, 1, 2, 3, 8, 7, 4, 9, 5},
                                {5, 4, 9, 2, 1, 6, 7, 3, 8},
                                {7, 6, 3, 5, 2, 4, 1, 8, 9},
                                {9, 2, 8, 6, 7, 1, 3, 5, 4},
                                {1, 5, 4, 9, 3, 8, 6, 0, 0}};

        int[][] exampleGrid2 = {{2, 9, 5, 7, 4, 3, 8, 6, 1},
                                {4, 3, 1, 8, 6, 5, 9, 0, 0},
                                {8, 7, 6, 1, 9, 2, 5, 4, 3},
                                {3, 8, 7, 4, 5, 9, 2, 1, 6},
                                {6, 1, 2, 3, 8, 7, 4, 9, 5},
                                {5, 4, 9, 2, 1, 6, 7, 3, 8},
                                {7, 6, 3, 5, 2, 4, 1, 8, 9},
                                {9, 2, 8, 6, 7, 1, 3, 5, 4},
                                {1, 5, 4, 9, 3, 8, 6, 0, 0}};


        assertAll("Validate that the amount of available solution to an incomplete board are calculated correctly",
                () -> assertEquals(1, sudokuSolver.getAmountOfSolutions(exampleGrid1)),
                () -> assertEquals(2, sudokuSolver.getAmountOfSolutions(exampleGrid2))
        );
    }

    /**
     * Validate that method generating an incomplete Sudoku grid from a complete one does so correctly.
     */
    @Test
    public void testGenerateIncompleteGrid() {
        int[][] completeGrid = new SudokuSolution().getGrid();
        int[][] incompleteGrid = sudokuSolver.generateIncompleteGrid(completeGrid, Difficulty.MEDIUM);
        assertTrue(compareGrids(completeGrid, incompleteGrid));
    }

    /**
     * Validate that method generating an easy to solve incomplete Sudoku grid from a complete one does so correctly.
     */
    @Test
    public void testEasyDifficulty() {
        int[][] completeGrid = new SudokuSolution().getGrid();
        int[][] incompleteGrid = sudokuSolver.generateIncompleteGrid(completeGrid, Difficulty.EASY);
        int amountOfEmptyCells = getAmountOfEmptyCells(incompleteGrid);
        assertAll("Make sure easy grid is generated correctly.",
                () -> assertTrue(amountOfEmptyCells <= 40),
                () -> assertTrue(amountOfEmptyCells >= 40 - marginOfError)
        );
    }

    /**
     * Validate that method generating a medium difficulty incomplete Sudoku grid from a complete one does so correctly.
     */
    @Test
    public void testMediumDifficulty() {
        int[][] completeGrid = new SudokuSolution().getGrid();
        int[][] incompleteGrid = sudokuSolver.generateIncompleteGrid(completeGrid, Difficulty.MEDIUM);
        int amountOfEmptyCells = getAmountOfEmptyCells(incompleteGrid);
        assertAll("Make sure medium grid is generated correctly.",
                () -> assertTrue(amountOfEmptyCells <= 45),
                () -> assertTrue(amountOfEmptyCells >= 45 - marginOfError)
        );
    }

    /**
     * Validate that method generating a hard difficulty incomplete Sudoku grid from a complete one does so correctly.
     */
    @Test
    public void testHardDifficulty() {
        int[][] completeGrid = new SudokuSolution().getGrid();
        int[][] incompleteGrid = sudokuSolver.generateIncompleteGrid(completeGrid, Difficulty.HARD);
        int amountOfEmptyCells = getAmountOfEmptyCells(incompleteGrid);
        assertAll("Make sure hard grid is generated correctly.",
                () -> assertTrue(amountOfEmptyCells <= 50),
                () -> assertTrue(amountOfEmptyCells >= 50 - marginOfError)
        );
    }

    /**
     * Validate that method generating an expert difficulty incomplete Sudoku grid from a complete one does so correctly.
     */
    @Test
    public void testExpertDifficulty() {
        int[][] completeGrid = new SudokuSolution().getGrid();
        int[][] incompleteGrid = sudokuSolver.generateIncompleteGrid(completeGrid, Difficulty.EXPERT);
        int amountOfEmptyCells = getAmountOfEmptyCells(incompleteGrid);
        assertAll("Make sure expert grid is generated correctly.",
                () -> assertTrue(amountOfEmptyCells <= 55),
                () -> assertTrue(amountOfEmptyCells >= 55 - marginOfError)
        );
    }

    /**
     * Takes a complete and an incomplete Sudoku grid that the valid numbers contained in the incomplete grid matches
     * those of the complete board (i.e they can have the same solution).
     * @param completeGrid the complete grid
     * @param incompleteGrid the incomplete grid
     * @return true if the incomplete board matches the complete board, otherwise false.
     */
    private boolean compareGrids(int[][] completeGrid, int[][] incompleteGrid) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (NUMBERS.contains(incompleteGrid[i][j]) && incompleteGrid[i][j] != completeGrid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getAmountOfEmptyCells(int[][] grid) {
        int counter = 0;
        for (int[] row : grid) {
            for (int cell : row) {
                if (!NUMBERS.contains(cell)) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
