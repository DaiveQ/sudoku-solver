package puzzle;

public class Box extends Sector {

	private static int BOX_DIMENSION = 0;

	public Box(int[][] puzzle, int index) {
		super(getExistingValues(puzzle, index));
	}

	protected static int[] getExistingValues(int[][] puzzle, int index) {
		int[] boxValues = new int[puzzle.length];

		int boxRow = index / BOX_DIMENSION; // row of the box (by boxes)
		int boxColumn = index % BOX_DIMENSION; // column of the box (by boxes)
		for (int i = 0; i < BOX_DIMENSION; i++) {
			for (int j = 0; j < BOX_DIMENSION; j++) {
				int value = puzzle[BOX_DIMENSION * boxRow + i][BOX_DIMENSION * boxColumn + j];
				boxValues[i + j * BOX_DIMENSION] = value;
			}
		}

		return boxValues;

	}

	public static int getBoxIndex(int i, int j) {
		return BOX_DIMENSION * (i / BOX_DIMENSION) + j / BOX_DIMENSION;
	}

	public static void setBoxDimension(int size) {
		Box.BOX_DIMENSION = (int) Math.sqrt(size);
	}

	public static int getBoxDimension() {
		return Box.BOX_DIMENSION;
	}
}
