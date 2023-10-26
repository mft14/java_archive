import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JLabel;

public class Applet2 extends JApplet {
	
	private JLabel ausgabe;
	
	public void paint(Graphics g) {
		super.paint(g);
		ausgabe.setText("Es grüßt dich MAx Meier");
	}

	
	@Override
	public void init() {
		super.init();
		//das Label erzeugen
		ausgabe = new JLabel();
		add(ausgabe);
	}
}
