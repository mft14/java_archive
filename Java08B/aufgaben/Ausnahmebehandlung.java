import javax.swing.JOptionPane;

public class Ausnahmebehandlung {
	
	public static void dividieren() {
		double ergebnis, zahl1, zahl2 = 0;
		try {
			zahl1 = Integer.parseInt(JOptionPane.showInputDialog("Zahl 1"));
			zahl2 = Integer.parseInt(JOptionPane.showInputDialog("Zahl 2"));		
			ergebnis = zahl1 / zahl2;
			System.out.println("Das Ergebnis ist: " + ergebnis);
		} 	catch (NumberFormatException e) {
			System.out.println("Haben sie hier Buchstaben eingetippt?");
		}
		
		catch (ArithmeticException e) {
			System.out.println("Haben sie vllt durch 0 geteilt?");
		}
	}
	
	private static void aufruf() {
		try {
			System.out.println(10/0);
		} catch(Exception e) {
			System.out.println("Das hat nicht geklappt");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}

	public static void main(String[] args) {

			aufruf();
	}

}
