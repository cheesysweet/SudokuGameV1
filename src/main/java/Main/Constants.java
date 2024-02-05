package Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility interface used to define constant values.
 */
public interface Constants {
    int GRID_SIZE = 9;
    int CELL_SIZE = 60; // Pixel size of a cell
    int MAX_INPUT_AMOUNT = 1; // Amount of chars that can be entered in a cell

    List<Integer> NUMBERS = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    // Grid design
    Color BACKGROUND = new Color(240, 240, 240);
    Color COLOR_GIVEN = Color.BLACK;
    Color COLOR_GUESS = Color.GRAY;
    Color COLOR_CORRECT = new Color(0, 240, 0);
    Color COLOR_WRONG = new Color(240, 0, 0);
    Font FONT = new Font("SansSerif", Font.BOLD, 20);

    // Strings
    String NEW_GAME = "New Game";
    String CHECK_GAME = "Check Game";
}
