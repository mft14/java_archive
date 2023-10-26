import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HalloWeltGUI extends JFrame {
	
	JLabel a = new JLabel("Hallo Welt");
	JLabel a2 = new JLabel();
	JButton exit = new JButton("Beenden");
	
	public HalloWeltGUI(String fenstertitel) {
		super(fenstertitel);
		
		setLayout(new FlowLayout());
		
		this.addWindowListener(new FensterListener());
		
		add(a);
		add(a2);
		add(exit);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new HalloWeltGUI("Neues Programmfenster");
	}
	
	public void setTextLabel2(String text) {
		a2.setText(text);
	}

}
