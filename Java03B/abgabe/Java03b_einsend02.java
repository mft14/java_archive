/* ####################################################
 * 
 * Einsendeaufgabe 3.2
 * 
 * #################################################### */
public class Java03b_einsend02 {

	public static void main(String[] args) {

		int limit = 50; // anpassbares Zahlenlimit, für die Aufgabe auf 50
						// initialisiert

		for (int i = 1; i <= limit; i++) { // Ich nehme dias von der For Schleife
											// initialisierte i Variable als Zähler
			System.out.print(i + ",");
			if (i == (limit - 1)) { 
				System.out.print(i + 1);
				i++;
			}
		}
	}
}
