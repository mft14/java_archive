import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class TestPane extends JFrame {
	
	boolean isFlow = true;
	
	JButton button1 = new JButton("Ändern");
	JButton button2 = new JButton("Testoooo2");
	JButton button3 = new JButton("Testoooo3");
	JButton button4 = new JButton("Testoooo4");
	JPanel panel1 = new JPanel();
	
	
	public TestPane() {
		
		

		add(panel1);
		panel1.add(button1);
		panel1.add(button2);
		panel1.add(button3);
		panel1.add(button4);
		panel1.setLayout(new FlowLayout());
		panel1.setBorder(new TitledBorder("Das sind die Buttons"));
		
		/////////////////////////////////////
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (isFlow) {
					panel1.removeAll();
					panel1.setLayout(new GridLayout(2,2));
					
					panel1.add(button1);
					panel1.add(button2);
					panel1.add(button3);
					panel1.add(button4);
					
					panel1.revalidate();
					isFlow = false;
					System.out.println(panel1.getLayout().toString().contains("BorderLayout"));
				} else {
					panel1.removeAll();
					panel1.setLayout(new FlowLayout());
					
					panel1.add(button1);
					panel1.add(button2);
					panel1.add(button3);
					panel1.add(button4);
					
					panel1.revalidate();
					isFlow = true;
					System.out.println(panel1.getLayout().toString());
				}
				
				
			}
		});
		/////////////////////////////////////
		
		
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new TestPane();
	}

}
