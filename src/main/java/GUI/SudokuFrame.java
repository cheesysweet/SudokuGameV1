package GUI;

import Main.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Class responsible for creating the gui window.
 */
public class SudokuFrame extends JFrame implements Constants{
    private Board board = new Board(); // Panel that hold all the cells.

    /**
     * Creates the gui frame
     */
    public SudokuFrame(int[][] completeGrid) {
        this.setTitle("Sudoku");

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(board, BorderLayout.CENTER);

        JPanel menu = new JPanel(new FlowLayout());
        menu.add(new Button(NEW_GAME, board));
        menu.add(new Button(CHECK_GAME, board));
        menu.add(new JLabel("Difficulty: "));
        menu.add(new DifficultyMenu(board));
        container.add(menu, BorderLayout.SOUTH);
        board.showBoard(completeGrid);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
