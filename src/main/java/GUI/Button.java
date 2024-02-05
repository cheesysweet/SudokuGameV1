package GUI;

import Main.Constants;
import Main.SudokuSolution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JButton class used for creating different buttons
 */
public class Button extends JButton implements ActionListener, Constants {

    private final Board board; // Sudoku board

    /**
     * Setts up the button with name, size and adds action listener
     * Stores the sudoku board
     * @param name  of the button
     * @param board sudoku board
     */
    public Button(String name, Board board) {
        super();
        this.board = board;
        this.setText(name);
        this.setPreferredSize(new Dimension(CELL_SIZE * 2, CELL_SIZE/2));
        this.addActionListener(this);
    }

    /**
     * Performs action depending on what button is pressed
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(NEW_GAME)) {
            board.showBoard(new SudokuSolution().getGrid());
        }
        if (action.equals(CHECK_GAME)) {
            board.solved();
        }
    }
}
