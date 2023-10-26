/* ####################################################
 * 
 * Einsendeaufgabe 3.5
 * 
 * #################################################### */
import javax.swing.JOptionPane;

public class Java03b_einsend05 {

	// 4 Methoden für alle Rechenoperator. Deren return Ergebnis ist das für den
	// jeweilgen Operator entsprechende Ergebnis als Typ double

	static double addieren(double zahl1, double zahl2) {
		return (zahl1 + zahl2);
	}

	static double subtrahieren(double zahl1, double zahl2) {
		return (zahl1 - zahl2);
	}

	static double multiplizieren(double zahl1, double zahl2) {
		return (zahl1 * zahl2);
	}

	static double dividieren(double zahl1, double zahl2) {
		return (zahl1 / zahl2);
	}

	// Die Potenzmethode multipliziert die erste Zahl auf die 1 so oft, wie es
	// die zweite Zahl angibt
	// Zurückgegeben wird das Ergebnis als Typ double
	static double potenz(double zahl1, double zahl2) {
		double ergebnis = 1;
		for (int i = 0; i < zahl2; i++) {
			ergebnis *= zahl1;
		}
		return ergebnis;
	}

	// Hier beginnt die Rechnung
	public static void main(String[] args) {

		String eingabe; // Platzhalter für Nutzereingaben
		double zahl1, zahl2, ergebnis = 0; // Eingabe zweier Werte vom Nutzer
											// und Endergebnis
		char rechenoperator; // Indikator, welcher Rechenoperator genutzt werden
								// soll als char

		zahl1 = Double.parseDouble(JOptionPane
				.showInputDialog("Zahl 1 eingeben:"));
		zahl2 = Double.parseDouble(JOptionPane
				.showInputDialog("Zahl 2 eingeben:"));

		eingabe = JOptionPane
				.showInputDialog("Welcher Rechenoperator soll's sein? Tippen Sie ein:\n\n+\n-\n*\n/\n\np (für Potenz)");
		rechenoperator = eingabe.charAt(0); // Den Operator als Char rausfiltern
											// mit charAt()

		// Ein Switch Case nutze ich hier, da er bei einem falschen Operator
		// gleich abbricht und nicht noch die Rechnung ausstellt, die eh 0 ist
		switch (rechenoperator) {
		case '+':
			ergebnis = addieren(zahl1, zahl2);
			System.out.println("Ihre Rechnung:\n" + zahl1 + " "
					+ rechenoperator + " " + zahl2 + " lautet: " + ergebnis);
			break;
		case '-':
			ergebnis = subtrahieren(zahl1, zahl2);
			System.out.println("Ihre Rechnung:\n" + zahl1 + " "
					+ rechenoperator + " " + zahl2 + " lautet: " + ergebnis);
			break;
		case '*':
			ergebnis = multiplizieren(zahl1, zahl2);
			System.out.println("Ihre Rechnung:\n" + zahl1 + " "
					+ rechenoperator + " " + zahl2 + " lautet: " + ergebnis);
			break;
		case '/':
			ergebnis = dividieren(zahl1, zahl2);
			System.out.println("Ihre Rechnung:\n" + zahl1 + " "
					+ rechenoperator + " " + zahl2 + " lautet: " + ergebnis);
			break;
		case 'p':
			ergebnis = potenz(zahl1, zahl2);
			System.out.println("Ihre Rechnung:\n" + zahl1 + " " + "hoch" + " "
					+ zahl2 + " lautet: " + ergebnis);
			break;

		default:
			System.out.println("Ungültiger Rechenoperator. Abgebrochen.");
			break;
		}
	}

}
