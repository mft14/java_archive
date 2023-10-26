import javax.swing.JOptionPane;

/* #################################################### 



 Einsendeaufgabe 2.2 



 #################################################### */

public class Java02b_einsend02 {

	public static void main(String[] args) {

		// Initialisieren aller nötigen Variablen

		int intVar1, intVar2;

		double doubleVar;

		// Eingabe kommt als String und wird hier als Integer umgewandelt

		intVar1 = Integer.parseInt(JOptionPane
				.showInputDialog("Bitte 1. Ganzzahl eingeben: "));

		intVar2 = Integer.parseInt(JOptionPane
				.showInputDialog("Bitte 2. Ganzzahl eingeben: "));

		// Hier darf man den Typecast nicht vergessen wegen der Nachkommastellen

		doubleVar = (double) intVar1 / intVar2;

		// Ausgabe

		System.out.println("Ihre Zahlen waren " + intVar1 + " und " + intVar2
				+ ".");

		System.out.printf("Das Ergebnis einer Division der Zahlen lautet: "
				+ doubleVar);

		// Programm richtig beenden

		System.exit(0);

	}

}