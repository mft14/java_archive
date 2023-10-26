import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TextTauscherGUI extends JFrame {
	SchaltflaecheListener listener;
	JLabel lbl1 = new JLabel("Guten Morgen");
	JLabel lbl2 = new JLabel("Moin, Servus");
	
	JButton btnTauschen = new JButton("Texte tauschen");
	JButton btnExit = new JButton("Programm beenden");
	
	//Inner Class
	public class SchaltflaecheListenerInner implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if("exit".equals(e.getActionCommand())) {
				System.exit(0);
			} else if ("tauschen".equals(e.getActionCommand())) {
				texteTauschen();
			}
		}
	}

	//Constructor
	public TextTauscherGUI(String title) {
		super(title);
		this.setLayout(new FlowLayout());
		
		//SchaltflaecheListener listener = new SchaltflaecheListener(this);
		btnTauschen.addActionListener(new SchaltflaecheListenerInner());
		btnExit.addActionListener(new SchaltflaecheListenerInner());
		
		btnTauschen.setActionCommand("tauschen");
		btnExit.setActionCommand("exit");
		
		add(lbl1);
		add(lbl2);
		add(btnTauschen);
		add(btnExit);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		
	}
	
	public void texteTauschen() {
		String temp;
		temp = lbl1.getText();
		lbl1.setText(lbl2.getText());
		lbl2.setText(temp);
	}

	public static void main(String[] args) {
		TextTauscherGUI t = new TextTauscherGUI("Hier werden Texte getauscht");
	}

}
