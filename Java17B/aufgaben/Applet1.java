import java.applet.Applet;
import java.awt.Graphics;


public class Applet1 extends Applet {

	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("Hallo Welt", 20, 20);
		g.drawString("Es grüßt dich Max Meier", 20, 60);
	}

}
