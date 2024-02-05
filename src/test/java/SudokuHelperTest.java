import Main.SudokuHelper;
import Main.SudokuSolution;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

/**
 * Test class that tests methods of the {@link SudokuHelper} class.
 */
public class SudokuHelperTest {
    private final SudokuHelper helper = SudokuHelper.INSTANCE;

    /**
     * Validate that method getSquare() returns the correct square of the grid based on its coordinate input.
     */
    @Test
    public void testGetSquare() {
        int[][] grid = new SudokuSolution().getGrid();
        int[][] square = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(grid[i], 0, square[i], 0, 3);
        }
        assertTrue(Arrays.deepEquals(square, helper.getSquare(grid, 1, 1)));
    }


    /**
     * Validate that method elementNotInArray correctly checks if the given int is contained inside the given array.
     */
    @Test
    public void testElementNotInArray() {
        assertAll("Validate that method checking if element exists in array or not is correct.",
                () -> assertTrue(helper.elementNotInArray(new int[]{1, 2, 3}, 4)),
                () -> assertFalse(helper.elementNotInArray(new int[]{1, 2, 3}, 3)),
                () -> assertFalse(helper.elementNotInArray(new int[]{101, 201, 301}, 201))
        );
    }

    /**
     * Validate that method elementNotInSquare correctly checks if the given int is contained inside the given 2d array.
     */
    @Test
    public void testElementNotInSquare() {
        int[][] square = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        assertAll("Validate that method checking if elements exists in square or not is correct.",
                () -> assertTrue(helper.elementNotinSquare(square, 10)),
                () -> assertFalse(helper.elementNotinSquare(square, 5)),
                () -> assertFalse(helper.elementNotinSquare(square, 3))
        );
    }
}
