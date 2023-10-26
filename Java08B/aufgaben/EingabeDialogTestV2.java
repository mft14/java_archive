/*####################################
 * Ein Test der Klasse EingabeDialogV2
 * Die Klasse wird �ber einen Verweis 
 * eingebunden
 * ###################################
 * BITTE BEACHTEN!
 * Sie m�ssen den Verweis zu der Klasse
 * neu aufbauen.
 ####################################*/

//f�r den Ausgabedialog
import javax.swing.JOptionPane;
//f�r die Klasse der Ausnahme AbbruchException
import eindialog.AbbruchException;
//f�r die Klasse mit dem Eingabedialog
import eindialog.EingabeDialog;

public class EingabeDialogTestV2 {
	public static void main(String[] args) {
		int intWert = 0;
		double doubleWert = 0;
		
		//der Aufruf der Methode einlesen() mit einem int-Typen
		try {
				intWert = EingabeDialog.einlesen(intWert);
				JOptionPane.showMessageDialog(null,"Ihre Eingabe war " + intWert);
		}
		//die Behandlung der eigenen Exception
		catch (AbbruchException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
		//der Aufruf der Methode einlesen() mit einem double-Typen
		try {
				doubleWert = EingabeDialog.einlesen(doubleWert);
				JOptionPane.showMessageDialog(null,"Ihre Eingabe war " + doubleWert);
		}
		//die Behandlung der eigenen Exception
		catch (AbbruchException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}
}

