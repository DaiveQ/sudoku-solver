package puzzle;

import java.util.ArrayList;

// Sector is defined as a row, column, or box (e.g., 3x3 box in 9x9 puzzle)
public abstract class Sector {
	protected ArrayList<Integer> possibleValues;
	private boolean solved = false;


	protected Sector(int[] sectorValues) {
		possibleValues = new ArrayList<>();
		for (int i = 1; i <= sectorValues.length; i++) {
			possibleValues.add(i);
		}

		for (Integer value : sectorValues) {
			possibleValues.remove(value);
		}
	}

	public ArrayList<Integer> getPossibleValues() {
		return possibleValues;
	}

	public void removePossibleValue(Integer value) {
		possibleValues.remove(value);
		checkIfSolved(getNumberOfPossibleValues());
	}

	public int getNumberOfPossibleValues() {
		return possibleValues.size();
	}

	protected void checkIfSolved(int numberOfPossibleValues) {
		if (numberOfPossibleValues == 0) {
			solved = true;
		}
	}

	public boolean isSolved() {
		return solved;
	}
}