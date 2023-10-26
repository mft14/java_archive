import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class ContainerTest extends JFrame {
	
	public ContainerTest() {
		
		JPanel panel1 = new JPanel();
		FlowLayout flayout = new FlowLayout();
		JRadioButton test1 = new JRadioButton("Test");
		JRadioButton test2 = new JRadioButton("Test");
		
		String[] inhalt = {"Eins", "Zwei", "Drei", "Vier"};
		JList <String> liste = new JList<String>(inhalt);
		
		JScrollPane jspane = new JScrollPane(liste);
		liste.setVisibleRowCount(3);
		this.add(jspane);
		
		
		this.setLayout(flayout);
		
		panel1.setBorder(new TitledBorder("Testorino"));
		panel1.add(test1);
		panel1.add(test2);
		
		ButtonGroup gruppe = new ButtonGroup(); 
		test1 = new JRadioButton("Schnitzel"); 
		test2 = new JRadioButton("Currywurst"); 
		test1.setSelected(true); 
		gruppe.add(test1); 
		gruppe.add(test2);
		
		
		
		this.add(panel1);
		
		this.add(test1);
		this.add(test2);
		

		
		this.setSize(500,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {

		ContainerTest c = new ContainerTest();
	}

}
