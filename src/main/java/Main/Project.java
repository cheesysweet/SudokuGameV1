package Main;

import GUI.SudokuFrame;

import java.util.Arrays;

public class Project {
    public static void main(String[] args) {
        int[][] completeGrid = new SudokuSolution().getGrid();

        System.out.println("Solution: ");
        for (int[] row : completeGrid) {
            System.out.println(Arrays.toString(row));
        }

        new SudokuFrame(completeGrid);
    }
}
