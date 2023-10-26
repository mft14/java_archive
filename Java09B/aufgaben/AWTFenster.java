import java.awt.Frame;
import java.awt.Label;

public class AWTFenster extends Frame {
	
	public AWTFenster(String fenstertitel) {
		super(fenstertitel);
		
		Label l = new Label("Hallo Welt");
		add(l);
		
	}

	public static void main(String[] args) {

		AWTFenster f = new AWTFenster("Moini");
		f.pack();
		f.setVisible(true);
		
	}

}
