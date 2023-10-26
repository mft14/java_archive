import javax.swing.JOptionPane;

public class Eingabe {

	public static void main(String[] args) {

		int eingabe;
		double eingabe2, eingabe3;

		eingabe = Integer.parseInt(JOptionPane.showInputDialog("INT 1"));
		eingabe2 = Double.parseDouble(JOptionPane.showInputDialog("DOUBLE 2"));
		eingabe3 = Double.parseDouble(JOptionPane.showInputDialog("DOUBLE 3"));

		System.out.println("Ergebnis: " + (eingabe + eingabe2 + eingabe3));

	}
}
