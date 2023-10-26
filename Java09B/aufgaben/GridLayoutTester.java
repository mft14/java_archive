import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutTester extends JFrame {
	
	public GridLayoutTester() {
		super("GridLayout Testerei");
			
			JButton btn1 = new JButton("Button 1");
			JButton btn2 = new JButton("Button 2");
			JButton btn3 = new JButton("Button 3");
			JButton btn4 = new JButton("Button 4");
			JButton btn5 = new JButton("Button 5");
			
//			GridLayout gl = new GridLayout(2,0);
//			setLayout(gl);
			
			setLayout(null);
			this.setSize(600,600);
			btn1.setBounds(30, 30, 100, 40);
			
			this.add(btn1);
//			this.add(btn2);
//			this.add(btn3);
//			this.add(btn4);
//			this.add(btn5);
			
			this.pack();
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);

		
	}
	

	public static void main(String[] args) {
		GridLayoutTester g = new GridLayoutTester();
	}

}
