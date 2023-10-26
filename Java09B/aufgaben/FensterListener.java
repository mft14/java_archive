import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

public class FensterListener implements WindowListener {

	HalloWeltGUI temp;

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {
		//Beim ÷ffnen des Programmes direkt nach nem Namen fragen
		String eingabe;
		eingabe = JOptionPane.showInputDialog("Wie heiﬂen Sie?");
		temp = (HalloWeltGUI)e.getSource();
		temp.setTextLabel2(eingabe);
	}

}
