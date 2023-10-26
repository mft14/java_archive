/* ####################################################
 * 
 * Einsendeaufgabe 3.3
 * 
 * #################################################### */
public class Java03b_einsend03 {

	public static void main(String[] args) {

		int counter = 1; // ein Zähler, wo der Wert starten soll
		int limit = 100; // die Obergrenze des Zahlenlaufs

		System.out.println("Normaler Wert \tDoppelter Wert\n");

		while (counter <= limit) {
			System.out.println(counter + "\t\t" + (counter * 2));
			counter++;
		}

	}

}
