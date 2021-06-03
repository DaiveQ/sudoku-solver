package sudoku_solver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebInterface {

	private final static String GENERATOR_URL = "https://printablecreative.com/wp-content/themes/pct/sudoku-generator/server/gen.php";

	public static int[][] generatePuzzle(int size, int difficulty) throws IOException, IndexOutOfBoundsException {
		{
			// check for valid parameters
			boolean invalid_params = false;
			if (size != 4 && size != 9) {
				System.out.println("Invalid size. Must be 4 or 9");
				invalid_params = true;
			}
			if (difficulty < 1 || difficulty > 4) {
				System.out.println("Invalid difficulty. Must be between 1 and 4 (inclusive)");
				invalid_params = true;
			}
			if (invalid_params) {
				System.exit(0);
			}
		}

		Document doc = Jsoup.connect(GENERATOR_URL)
				.data("size", String.valueOf(size))
				.data("difficulty", String.valueOf(difficulty))
				.post();

		{
			// Hacky method of removing solution from response and converting json response to valid html only
			// This is done to avoid a json dependency as there are only two objects and only one value is needed
			String tmpStrDoc = doc.body().toString();
			int index = tmpStrDoc.indexOf("/table");
			// Trim leading and ending information
			doc = Jsoup.parse(tmpStrDoc.substring(17, index + 9).replace("\\", ""));
		}

		Elements rows = doc.select("table").select("tbody").select("tr");

		int[][] puzzle = new int[size][size];

		// request returns puzzle and solution. First var size rows are puzzle and latter var size rows are solution.
		for (int i = 0; i < size; i++) {
			// Get list of items form each row
			Elements items = rows.get(i).select("td");

			for (int j = 0; j < size; j++) {
				String text = items.get(j).text();

				// trim garbage. The html isn't properly formatted by the website
				text = text.replace("</td>", "");
				text = text.replace("</tr>", "");
				text = text.replace("</table>", "");

				try {
					puzzle[i][j] = Integer.parseInt(text);
				} catch (NumberFormatException e) {
					// Exception thrown when cell is blank. In code, a blank cell is represented by 0
					puzzle[i][j] = 0;
				}
			}
		}
		return puzzle;
	}
}
