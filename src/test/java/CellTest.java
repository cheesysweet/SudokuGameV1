import GUI.Cell;
import GUI.CellStatus;
import Main.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test cases for methods used in the Cell class
 */
public class CellTest {

    // creates two different cells
    Cell cell = new Cell();
    Cell cell1 = new Cell();

    /**
     * Initiate on of the cells with a given number and the correctNumber
     */
    @Before
    public void init() {
        cell.setNumber(2);
        cell.setCorrectNumber(3);
    }

    /**
     * Test to make sure only cell textField with given number is not editable
     */
    @Test
    public void testPrivateEditableMethod() {
        assertTrue(cell1.isEditable());
        assertFalse(cell.isEditable());
    }

    /**
     * Test the cells textField contains the correct string.
     */
    @Test
    public void testText() {
        assertEquals("", cell1.getText());
        assertEquals("2", cell.getText());
    }

    /**
     * Tests the cells background color in different states.
     */
    @Test
    public void testBackground() {
        cell.setCellStatus(CellStatus.CORRECT_GUESS);
        cell.paint();
        assertEquals(Constants.BACKGROUND, cell1.getBackground()); // no entered input
        assertEquals(Constants.COLOR_CORRECT, cell.getBackground());  // contains a correct guessed number
        assertNotEquals(Constants.COLOR_WRONG, cell.getBackground());
    }

    /**
     * Tests the cells status
     */
    @Test
    public void testCellStatus() {
        assertEquals(CellStatus.GIVEN, cell.getCellStatus());
    }

    /**
     * Validates the cells stored number
     */
    @Test
    public void testNumber() {
        assertEquals(2, cell.getNumber());
        assertNotEquals(3, cell.getNumber());
    }

    /**
     * Validates the cells correct number
     */
    @Test
    public void testCorrectNumber() {
        assertEquals(3, cell.getCorrectNumber());
    }

    /**
     * Generates a new cell after tests are completed
     */
    @After
    public void destroy() {
        cell = new Cell();
    }
}
