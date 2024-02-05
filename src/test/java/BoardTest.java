import GUI.Board;
import GUI.Cell;
import GUI.Difficulty;
import Main.Constants;
import Main.SudokuSolution;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test methods and classes used in Board class
 */
public class BoardTest {
    // Generates two different Boards for testing
    Board board = new Board();
    Board board1 = new Board();

    /**
     * Initiates one of the Boards with a new difficulty level and the other with a solved board
     */
    @Before
    public void init() {
        board1.setDifficulty(Difficulty.EXPERT);
        board.setCellStatus(new SudokuSolution().getGrid());
    }

    /**
     * Validates the Boards difficulty level
     */
    @Test
    public void testDifficulty() {
        assertEquals(Difficulty.EASY, board.getDifficulty());
        assertNotEquals(Difficulty.HARD, board.getDifficulty());
        assertEquals(Difficulty.EXPERT, board1.getDifficulty());
    }

    /**
     * Validates the amount of cells in the board.
     */
    @Test
    public void testCellArray() {
        int amountOfCells = getAmountOfCells(board.getCells());
        assertEquals(Constants.GRID_SIZE * Constants.GRID_SIZE, amountOfCells);
    }

    /**
     * calculate amount of cells
     * @param cells 2d array of cells
     * @return number of cells
     */
    private int getAmountOfCells(Cell[][] cells) {
        return cells.length * cells[0].length;
    }

    /**
     * Validates if the board is solved or not
     */
    @Test
    public void testSolvedBoard() {
        assertTrue(board.solved());
        assertFalse(board1.solved());
    }

    /**
     * Generates a new Board after tests are completed
     */
    @After
    public void destroy() {
        board1 = new Board();
    }
}
