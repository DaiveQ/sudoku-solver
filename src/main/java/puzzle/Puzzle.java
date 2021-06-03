package puzzle;

public class Puzzle {
	// TODO: Convert to non-static
	private static int[][] puzzle;

	private static int size;

	private static Row[] rows;
	private static Col[] cols;
	private static Box[] boxes;

	public Puzzle(int[][] puzzle) {
		Puzzle.puzzle = puzzle;
		size = puzzle.length;

		Box.setBoxDimension(size);

		rows = new Row[size];
		cols = new Col[size];
		boxes = new Box[size];

		for (int i = 0; i < size; i++) {
			rows[i] = new Row(puzzle, i);
			cols[i] = new Col(puzzle, i);
			boxes[i] = new Box(puzzle, i);
		}
	}

	public int[][] getPuzzle() {
		return puzzle;
	}

	public static int getSize() {
		return size;
	}

	public static Row getRow(int i) {
		return rows[i];
	}

	public static Col getCol(int i) {
		return cols[i];
	}

	public static Box getBox(int i) {
		return boxes[i];
	}

	public static int getValue(int i, int j) {
		return puzzle[i][j];
	}

	public static void setValue(int value, int i, int j) {
		puzzle[i][j] = value;
		updatePossibleValues(value, i, j);
	}

	public static void updatePossibleValues(Integer value, int i, int j) {
		rows[i].removePossibleValue(value);
		cols[j].removePossibleValue(value);
		boxes[Box.getBoxIndex(i, j)].removePossibleValue(value);
	}

	public static boolean checkIfSolved() {
		for (Row row : rows) {
			if (row.getNumberOfPossibleValues() != 0) {
				return false;
			}
		}
		return true;
	}

	public static void printPuzzle() {
		for (int[] row : puzzle) {
			System.out.print("|");
			for (int cell : row) {
				if (cell == 0)
					System.out.print(" |");
				else
					System.out.print(cell + "|");
			}
			System.out.println();
		}
		System.out.println();

	}
}
