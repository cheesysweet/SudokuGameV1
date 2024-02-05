import GUI.Board;
import Main.SudokuSolution;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class that tests the functionality of GUI buttons.
 */
public class ButtonTest {

    /**
     * Validates that SudokuSolutions generate a new grid.
     */
    @Test
    public void newGameButton() {
        int[][] completeGrid1 = new SudokuSolution().getGrid();
        int[][] completeGrid2 = new SudokuSolution().getGrid();

        assertNotEquals(completeGrid1, completeGrid2);
    }

    /**
     * Validates that the check game correctly verifies if a board is solved or not.
     */
    @Test
    public void checkGameButton() {
        int[][] completeGrid = new SudokuSolution().getGrid();
        Board board = new Board();
        board.setCellStatus(completeGrid);

        assertTrue(board.solved());
    }
}

