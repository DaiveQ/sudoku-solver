package puzzle;

public class Col extends Sector {
	public Col(int[][] puzzle, int index) {
		super(getExistingValues(puzzle, index));
	}

	private static int[] getExistingValues(int[][] puzzle, int index) {
		int[] colValues = new int[puzzle.length];
		for (int i = 0; i < puzzle.length; i++) {
			colValues[i] = puzzle[i][index];
		}
		return colValues;
	}
}