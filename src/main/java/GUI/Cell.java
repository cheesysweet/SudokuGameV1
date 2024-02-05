package GUI;

import Main.Constants;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Class responsible for each cell and holds it´s values.
 */
public class Cell extends JTextField implements Constants{

    private int number; // value in cell
    private int correctNumber; // correct value
    private CellStatus cellStatus; // cell status

    /**
     * Creates a cell and adds a keyListener and limits input amount to 1 character
     */
    public Cell() {
        super();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c<'1') || (c>'9')) && (c!=KeyEvent.VK_BACK_SPACE)) {
                    e.consume(); // Ignores event if it´s not a number between 1 and 9.
                } else {
                    if (Cell.super.isEditable()) {
                        clearText();
                        setCellStatus(c);
                    }
                }
            }
        });
        this.setDocument(new LimitJTextField()); // Limits input to 1 char

        super.setHorizontalAlignment(JTextField.CENTER);
        super.setBackground(BACKGROUND);
        super.setFont(FONT);
    }

    /**
     * Clears text field if new number is entered
     */
    private void clearText() {
        this.setText("");
    }


    /**
     * stores the number that's saved in the cell, the correct number to solve the game and sets the status of the cell
     * @param number in the cell
     */
    public void setNumber(int number) {
        this.number = number;
        if (NUMBERS.contains(number)) {
            cellStatus = CellStatus.GIVEN;
        } else {
            cellStatus = CellStatus.GUESS;
        }
        paint();
    }

    /**
     * displays the given numbers and makes the fields uneditable
     */
    public void paint() {
        if (cellStatus == CellStatus.GIVEN) {
            super.setText(number+"");
            super.setEditable(false);
            super.setForeground(COLOR_GIVEN);
        }
        if (cellStatus == CellStatus.GUESS) {
            super.setText("");
            super.setEditable(true);
            super.setForeground(COLOR_GUESS);
        }
        if (cellStatus == CellStatus.CORRECT_GUESS) {
            super.setForeground(COLOR_GIVEN);
            super.setBackground(COLOR_CORRECT);
        }
        if (cellStatus == CellStatus.WRONG_GUESS) {
            super.setForeground(COLOR_GIVEN);
            super.setBackground(COLOR_WRONG);
        }
    }

    /**
     * Sets the current number of cell
     * @param c entered number
     */
    private void setCellStatus(char c) {
        int number = Character.getNumericValue(c);
        if (number == correctNumber) {
            cellStatus = CellStatus.CORRECT_GUESS;
        } else {
            cellStatus = CellStatus.WRONG_GUESS;
        }
    }

    /**
     * @return status of the cell
     */
    public CellStatus getCellStatus() {
        return cellStatus;
    }

    /**
     * used for testing
     * @return current cell number
     */
    public int getNumber() {
        return number;
    }

    /**
     * used for testing
     * @return correct cell number
     */
    public int getCorrectNumber() {
        return correctNumber;
    }

    /**
     * used for testing
     * @param cellStatus sets the cellStatus
     */
    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    /**
     * Sets the correct number of the cell
     * @param i cells number
     */
    public void setCorrectNumber(int i) {
        this.correctNumber = i;
        this.setBackground(BACKGROUND);
    }

    /**
     * Limits the amount of input character to 1
     */
    private static class LimitJTextField extends PlainDocument
    {
        LimitJTextField() {
            super();
        }
        public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
            if (text == null)
                return;
            if ((getLength() + text.length()) <= MAX_INPUT_AMOUNT) {
                super.insertString(offset, text, attr);
            }
        }
    }
}
