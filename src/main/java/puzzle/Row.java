package puzzle;

public class Row extends Sector {
	public Row (int[][] puzzle, int index) {
		super(getExistingValues(puzzle, index));
	}

	private static int[] getExistingValues(int[][] puzzle, int index) {
		return puzzle[index];
	}
}
