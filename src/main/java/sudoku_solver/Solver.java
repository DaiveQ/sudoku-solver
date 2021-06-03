package sudoku_solver;

import puzzle.Box;
import puzzle.Puzzle;
import puzzle.Sector;

import java.util.ArrayList;

// TODO: Use recursion to solve for multiple permutations (i.e., when multiple branches exist, explore both)
// This will require using Puzzle as an object and removing the static-ness from the class

public class Solver {

	public static void solve(int[][] puzzle_data) {
		Puzzle puzzle = new Puzzle(puzzle_data);
		Puzzle.printPuzzle();

		boolean solved = false;

		while (!solved) {
			boolean updated = false;
			updated = onlyOptionSolver();

			if (!updated) {
				updated = crossCheckSolver();
			}

			if (!updated) {
				System.out.println("Puzzle could not be solved");
				break;
			} else {
				solved = Puzzle.checkIfSolved();
			}

		}

		System.out.println("Solver has run to completion");

	}

	private static boolean onlyOptionSolver() {
		boolean updated = false;

		for (int i = 0; i < Puzzle.getSize(); i++) {
			if (!Puzzle.getRow(i).isSolved()) {
				int value = onlyOptionSolver(Puzzle.getRow(i));
				if (value != -1) {
					for (int j = 0; j < Puzzle.getSize(); j++) {
						if (Puzzle.getValue(i, j) == 0) {
							Puzzle.setValue(value, i, j);
							updated = true;
							System.out.println("Only option row solve of " + value + "@" + i + "," + j);
							Puzzle.printPuzzle();
							break;
						}
					}
				}
			}
			if (!Puzzle.getCol(i).isSolved()) {
				int value = onlyOptionSolver(Puzzle.getCol(i));
				if (value != -1) {
					for (int j = 0; j < Puzzle.getSize(); j++) {
						if (Puzzle.getValue(j, i) == 0) {
							Puzzle.setValue(value, j, i);
							updated = true;
							System.out.println("Only option col solve of " + value + "@" + j + "," + i);
							Puzzle.printPuzzle();
							break;
						}
					}
				}
			}
		}

		for (int i = 0; i < Puzzle.getSize(); i += Box.getBoxDimension()) {
			for (int j = 0; j < Puzzle.getSize(); j += Box.getBoxDimension()) {
				int index = Box.getBoxIndex(i, j);
				int value = onlyOptionSolver(Puzzle.getBox(index));
				if (value != -1) {
					for (int iOffset = 0; iOffset < Box.getBoxDimension(); iOffset++) {
						for (int jOffset = 0; jOffset < Box.getBoxDimension(); jOffset++) {
							if (Puzzle.getValue(i + iOffset, j + jOffset) == 0) {
								Puzzle.setValue(value, i + iOffset, j + jOffset);
								updated = true;
								System.out.println("Only option box solve of " + value + "@" + (i + iOffset) + "," + (j + jOffset));
								Puzzle.printPuzzle();
								break;
							}
						}
					}
				}
			}
		}

		return updated;
	}

	// Tries to solve sector when only one option remains
	// Return value if solvable with this method. Otherwise, return -1
	private static int onlyOptionSolver(Sector sector) {
		if (sector.getNumberOfPossibleValues() == 1) {
			return sector.getPossibleValues().get(0);
		} else {
			return -1;
		}
	}

	private static boolean crossCheckSolver() {
		boolean updated = false;
		for (int i = 0; i < Puzzle.getSize(); i++) {
			for (int j = 0; j < Puzzle.getSize(); j++) {
				if (Puzzle.getValue(i, j) == 0) {
					ArrayList<Integer> values = (ArrayList<Integer>) Puzzle.getRow(i).getPossibleValues().clone();
					ArrayList<Integer> colValues = Puzzle.getCol(j).getPossibleValues();
					ArrayList<Integer> boxValues = Puzzle.getBox(Box.getBoxIndex(i,j)).getPossibleValues();
					values.removeIf(value -> !colValues.contains(value) || !boxValues.contains(value));

					if (values.size() == 1) {
						int value =  values.get(0);
						Puzzle.setValue(value, i, j);
						updated = true;
						System.out.println("Cross check solve of " + value + "@" + i + "," + j);
						Puzzle.printPuzzle();
					}
				}
			}
		}
		return updated;
	}

}
