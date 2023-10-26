import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class TaschenrechnerGUI extends JFrame {
	
	private JTextField eingabe1, eingabe2;
	private JRadioButton addition, subtraktion, multiplikation, division;
	private JButton btnBerechnen, btnBeenden;
	private JComboBox operatoren;
	String[] operatorenListe = {"Addieren", "Subtrahieren", "Multiplizieren", "Dividieren"};
	private JLabel ausgabe;
	
	
	public TaschenrechnerGUI(String titel) {
		super(titel);
		
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			System.out.println("Look and Feel wurde nicht übernommen");
		}
		
		this.setLayout(new FlowLayout());
		
		this.add(panelEinAusErzeugen());
		this.add(panelBerechnungErzeugen());
		this.add(panelButtonErzeugen());
		
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		

	}
	
	private JPanel panelEinAusErzeugen() {
		JPanel tempPanel = new JPanel();
		
		eingabe1 = new JTextField(10);
		eingabe2 = new JTextField(10);
		ausgabe = new JLabel("");
		tempPanel.setLayout(new GridLayout(0,2,10,10));
		
		tempPanel.add(new JLabel("Zahl 1"));
		tempPanel.add(eingabe1);
		
		tempPanel.add(new JLabel("Zahl 2"));
		tempPanel.add(eingabe2);
		
		tempPanel.add(new JLabel("Ergebnis: ")); 
		tempPanel.add(ausgabe);
		
		tempPanel.setBorder(new TitledBorder("Ein- und Ausgabe"));
		
		return tempPanel;
	}
	
	private JPanel panelBerechnungErzeugen() {
		JPanel tempPanel = new JPanel();
		
		addition 		= new JRadioButton("Addition");
		subtraktion 	= new JRadioButton("Subtraktion");
		division 		= new JRadioButton("Division");
		multiplikation 	= new JRadioButton ("Multiplikation");
		
		ButtonGroup gruppe = new ButtonGroup();
		gruppe.add(addition);
		gruppe.add(subtraktion);
		gruppe.add(division);
		gruppe.add(multiplikation);
		addition.setSelected(true);
		
		tempPanel.setLayout(new GridLayout(0,1));
		tempPanel.add(addition);
		tempPanel.add(subtraktion);
		tempPanel.add(division);
		tempPanel.add(multiplikation);
		
		tempPanel.setBorder(new TitledBorder("Operation"));
		
		return tempPanel;
	}
	
	private JPanel panelButtonErzeugen() {
		JPanel tempPanel = new JPanel();

		btnBerechnen = new JButton("Berechnen");
		btnBeenden   = new JButton("Beenden");
		
		tempPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		tempPanel.add(btnBerechnen);
		tempPanel.add(btnBeenden);
		
		MeinListener listener = new MeinListener();
		btnBerechnen.addActionListener(listener);
		btnBeenden.addActionListener(listener);
		btnBerechnen.setActionCommand("berechnen");
		btnBeenden.setActionCommand("beenden");
		
		return tempPanel;
	}
	
	class MeinListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getActionCommand().equals("berechnen")) {

				double zahl1, zahl2, ergebnis = 0;
				
				try {
					
					zahl1 = Double.parseDouble(eingabe1.getText());
					zahl2 = Double.parseDouble(eingabe2.getText());
					
					if(addition.isSelected()) {
						ergebnis = zahl1 + zahl2;
					}
					if(subtraktion.isSelected()) {
						ergebnis = zahl1 - zahl2;
					}
					if(multiplikation.isSelected()) {
						ergebnis = zahl1 * zahl2;
					}
					if(division.isSelected()) {
						ergebnis = zahl1 / zahl2;
					}
					DecimalFormat df = new DecimalFormat("0.##");
					ausgabe.setText(df.format(ergebnis));
					
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Fehler bei der Eingabe, bitte erneut versuchen");
					eingabe1.setText("");
					eingabe2.setText("");
					ausgabe.setText("");
				}
				
			}
			if(e.getActionCommand().equals("beenden")) {
				System.exit(0);
			}
			
			
			
			
		}
		
	}


	public static void main(String[] args) {

		new TaschenrechnerGUI("Taschenrechner V1");
	}

}
