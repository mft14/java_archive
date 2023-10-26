import javax.swing.JFrame;
import javax.swing.JLabel;

public class SwingFenster extends JFrame {
	
	public SwingFenster(String titel) {
		super(titel);
		JLabel l = new JLabel("Hello World");
		add(l);
	}
	public static void main(String[] args) {
		SwingFenster f = new SwingFenster("Moin");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.pack();
		f.setVisible(true);
	}
}
