package GUI;

import Main.Constants;
import Main.SudokuSolution;
import Main.SudokuSolver;

import javax.swing.*;
import java.awt.*;

/**
 * Class responsible for creation of 9x9 grid that displays the cells
 */
public class Board extends JPanel implements Constants{
    private Difficulty difficulty = Difficulty.EASY;  // starting difficulty, can be changed by the user

    private final Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE]; // creates a 2d array of cells

    /**
     * Sets the size of the panel
     * Creates cells and stores them in the 2d array,
     */
    public Board() {
        super.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        super.setPreferredSize(new Dimension(CELL_SIZE* GRID_SIZE, CELL_SIZE* GRID_SIZE));

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col] = new Cell();
                super.add(cells[row][col]);
            }
        }
    }

    /**
     * Stores the correct number of each cell and creates an incomplete grid
     * @param completeGrid a complete grid
     */
    public void showBoard(int[][] completeGrid) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].setCorrectNumber(completeGrid[row][col]);
            }
        }
        SudokuSolver sudokuSolver = new SudokuSolver();
        int[][] incompleteGrid = sudokuSolver.generateIncompleteGrid(completeGrid, difficulty);
        setCellStatus(incompleteGrid);
    }

    /**
     * Sets cell status for incomplete grid
     * @param incompleteGrid incomplete grid
     */
    public void setCellStatus(int[][] incompleteGrid) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].setNumber(incompleteGrid[row][col]);
            }
        }
    }

    /**
     * Checks if the sudoku board is solved
     * @return true if board is solved
     */
    public boolean solved() {
        boolean solved = true;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].paint();
                if (cells[row][col].getCellStatus() == CellStatus.GUESS || cells[row][col].getCellStatus() == CellStatus.WRONG_GUESS) {
                    solved = false;
                }
            }
        }
        if (solved) {
            // JFrame used for popup window displaying that sudoku board is solved
            JOptionPane.showMessageDialog(null, "Congratulation!");
        }
        return solved;
    }

    /**
     * Sets difficulty and creates a new grid
     * @param difficulty difficulty level
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        showBoard(new SudokuSolution().getGrid());
    }

    /**
     * used for testing
     * @return difficulty level
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * used for testing
     * @return cell array
     */
    public Cell[][] getCells() {
        return cells;
    }
}
