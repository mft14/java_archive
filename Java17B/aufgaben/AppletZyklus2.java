import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JTextArea;

public class AppletZyklus2 extends JApplet {

	
	private JTextArea ausgabe;
	//überschriebene Methoden
	
	//die überschriebenen Methoden
		@Override
		public void paint(Graphics g) {
			//die Methode der übergeordneten Klasse aufrufen
			super.paint(g);
			statusZeigen("paint()");
			ausgabe.setText(ausgabe.getText() + "\nNach der Methode paint()");
		}

		@Override
		public void init() {
			super.init();
			ausgabe = new JTextArea();
			add(ausgabe);
			statusZeigen("init()");
			ausgabe.setText(ausgabe.getText() + "\nNach der Methode init()");
		}

		@Override
		public void start() {
			super.start();
			statusZeigen("start()");
			ausgabe.setText(ausgabe.getText() + "\nNach der Methode start()");
		}

		@Override
		public void stop() {
			super.stop();
			statusZeigen("stop()");
			ausgabe.setText(ausgabe.getText() + "\nNach der Methode stop()");
		}
		
		@Override
		public void destroy() {
			super.destroy();
			statusZeigen("destroy()");
			ausgabe.setText(ausgabe.getText() + "\nNach der Methode destroy()");
		}
	
	
	
	private void statusZeigen(String string) {
		showStatus("Die Methode " + string + " wird gerade ausgeführt.");
		System.out.println(string);
		
	}
	
}
