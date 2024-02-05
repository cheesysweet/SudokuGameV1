package GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Custom JComboBox used by the user to choose desired difficultly level.
 */
public class DifficultyMenu extends JComboBox<String> implements ActionListener {

    /**
     * Constructor adding the difficulty to the combo box as well as the action listener listening for changes to the difficulty.
     * @param board current sudoku board
     */
    public DifficultyMenu(Board board) {
        super(new String[]{"Easy", "Medium", "Hard", "Expert"});
        addActionListener(e -> {
            if (this.getSelectedItem() == "Easy") {
                board.setDifficulty(Difficulty.EASY);
            } else if (this.getSelectedItem() == "Medium") {
                board.setDifficulty(Difficulty.MEDIUM);
            } else if (this.getSelectedItem() == "Hard") {
                board.setDifficulty(Difficulty.HARD);
            } else if (this.getSelectedItem() == "Expert") {
                board.setDifficulty(Difficulty.EXPERT);
            }
        });
    }
}
