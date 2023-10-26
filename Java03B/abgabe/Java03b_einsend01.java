/* ####################################################
 * 
 * Einsendeaufgabe 3.1
 * 
 * #################################################### */
import javax.swing.JOptionPane;

public class Java03b_einsend01 {

	public static void main(String[] args) {

		int jahreszahl; // Das Jahr als Integer
		boolean istSchaltjahr; // Setzt Eingabe auf true, wenn es ein Schaltjahr ist

		jahreszahl = Integer.parseInt(JOptionPane
				.showInputDialog("Bitte geben Sie eine Jahreszahl ein: "));

		if ((jahreszahl % 400) == 0) {
			istSchaltjahr = true;
		} else if (((jahreszahl % 4) == 0) && ((jahreszahl % 100) != 0)) {
			istSchaltjahr = true;
		} else {
			istSchaltjahr = false;
		}

		System.out.print("Das Jahr " + jahreszahl + " ist ");
		if (istSchaltjahr) {
			System.out.print("ein Schaltjahr!");
		} else {
			System.out.print("leider kein Schaltjahr!");
		}

	}

}
