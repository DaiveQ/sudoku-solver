package sudoku_solver;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			int[][] puzzle = WebInterface.generatePuzzle(9, 1);
			Solver.solve(puzzle);
		} catch (IOException e) {
			System.out.println("Failed to generate puzzle.");
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Unexpected response body. Not enough rows returned.");
			e.printStackTrace();
		}

	}
}
