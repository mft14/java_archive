public class TabellenArray {

	public static void main(String[] args) {

		int[][] Tabelle = { { 3, 5, 7, 6 }, { 2, 1, 0, 9 } };

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println("Die Tabelle bei " + i + " " + j + " hat: "	+ Tabelle[i][j]);
			}
		}

	}

}
