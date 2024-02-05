package Main;

import GUI.Difficulty;

import java.util.Random;

/**
 * Class that contains functionality for solving an incomplete grid, as well as generating an incomplete grid from a
 * given solution.
 */
public class SudokuSolver implements Constants {
    private final SudokuHelper helper = SudokuHelper.INSTANCE;

    // this instance variable is needed since it has to be accessed in both solveBoard() and getAmountOfSolutions()
    // not optimal, but it works for now
    private int amountOfSolutions = 0;

    /**
     * A recursive function to check possible combinations of numbers until the board is completed with valid number patterns.
     * @return true if the creation of the board was successful, otherwise false.
     */
    private boolean solveGrid(int[][] grid) {
        int col = 0;
        int row = 0;
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            row = Math.floorDiv(i, GRID_SIZE);
            col = i % GRID_SIZE;
            if (grid[row][col] == 0) {
                for (int num : NUMBERS) {
                    if (helper.elementNotInArray(grid[row], num)) {
                        int[] column = helper.getColumn(grid, col);
                        if (helper.elementNotInArray(column, num)) {
                            int[][] square = helper.getSquare(grid, col, row);
                            if (helper.elementNotinSquare(square, num)) {
                                grid[row][col] = num;
                                if (helper.gridIsComplete(grid)) {
                                    amountOfSolutions++;
                                    break;
                                } else {
                                    if (solveGrid(grid)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
        grid[row][col] = 0;
        return false;
    }

    /**
     * @param grid the grid bo be checked
     * @return the amount of solutions available to the given grid
     */
    public int getAmountOfSolutions(int[][] grid) {
        amountOfSolutions = 0;
        solveGrid(grid);
        return amountOfSolutions;
    }

    /**
     * Takes a complete grid as argument, then removes a random amount of numbers from it while still making sure only
     * one solution is available. Variable attempts can be used to modify the difficulty level.
     * @param grid the complete grid
     * @param difficulty the difficulty to be used.
     * @return an incomplete version of the complete grid, with hidden values represented by 0's
     */
    public int[][] generateIncompleteGrid(int[][] grid, Difficulty difficulty) {
        int approximateNumberRemoval = getApproximateNumbersToRemove(difficulty);  // increase this value to generate a more difficult grid to solve
        while (approximateNumberRemoval > 0) {
            int row = new Random().nextInt(GRID_SIZE);
            int col = new Random().nextInt(GRID_SIZE);
            while (grid[row][col] == 0) {
                row = new Random().nextInt(GRID_SIZE);
                col = new Random().nextInt(GRID_SIZE);
            }
            int backup = grid[row][col];
            grid[row][col] = 0;

            int[][] gridCopy = new int[GRID_SIZE][GRID_SIZE];
            System.arraycopy(grid, 0, gridCopy, 0, GRID_SIZE);
            int numOfSolutions = getAmountOfSolutions(gridCopy);
            if (numOfSolutions != 1) {
                grid[row][col] = backup;
            }
            approximateNumberRemoval--;
        }
        return grid;
    }

    private int getApproximateNumbersToRemove(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> 40;
            case MEDIUM -> 45;
            case HARD -> 50;
            case EXPERT -> 55;
        };
    }
}
