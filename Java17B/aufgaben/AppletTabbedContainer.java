import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class AppletTabbedContainer extends JApplet{
	//automatisch über Eclipse ergänzt
	private static final long serialVersionUID = 3170046800011323373L;

	private JTabbedPane registerPanel;
	private JButton button1, button2;
	private JLabel label1, label2;
	private JPanel panel1, panel2;
	
	@Override
	public void init() {
		//die Labels
		label1 = new JLabel("Ich bin auf dem ersten Register");
		label2 = new JLabel("Ich bin auf dem zweiten Register");
		
		//die Buttons
		button1 = new JButton("Ich bin auf dem ersten Register");
		button2 = new JButton("Ich bin auf dem zweiten Register");
		
		//die Panels
		panel1 = new JPanel();
		panel1.add(button1);
		panel1.add(label1);
		
		panel2 = new JPanel();
		panel2.add(button2);
		panel2.add(label2);
			
		
		//ein neues JTabbedPane
		registerPanel = new JTabbedPane();
        //die Register hinzufügen
        //übergeben werden die kompletten Panels
		registerPanel.add("Register 1", panel1);
		registerPanel.add("Register 2", panel2);

        //den Container hinzufügen
		add(registerPanel);
		setSize(500,200);
	}
}
