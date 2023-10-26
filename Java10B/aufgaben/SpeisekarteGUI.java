import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class SpeisekarteGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4354216154940332465L;
	JButton btnBestello = new JButton("Bestello"), btnBeenden = new JButton("Beenden");
	JLabel lblEssen = new JLabel("Ihr Essen"), lblBeilage = new JLabel("Ihre Beilage");
	JRadioButton rdKoefte = new JRadioButton("K�ftespie�");
	JRadioButton rdCurrywurst = new JRadioButton("Currywurst");
	JCheckBox chbPommes = new JCheckBox("Pommes");
	JCheckBox chbReis = new JCheckBox("Reis");
	
	String[] essenListe = {"Currywurst", "Pommes", "K�fte", "H�hnchen", "Schnitzel"	};
	JList<String> auswahlListe = new JList<String>(essenListe);
	
	//JComboBox<String> cbAuswahl = new JComboBox<String>();
	ButtonGroup btngEssen = new ButtonGroup();
		
	
	public SpeisekarteGUI(String titel) {
		super(titel);
		
		GridLayout gLayout = new GridLayout(0,2);
		gLayout.setHgap(20);
		setLayout(gLayout);
		
		AktionsListener listener = new AktionsListener();
		
		
		btngEssen.add(rdCurrywurst);
		btngEssen.add(rdKoefte);
		
		this.add(lblEssen);
		this.add(lblBeilage);
		
		this.add(auswahlListe);
		this.add(chbPommes);
		this.add(rdCurrywurst);
		this.add(chbReis);
		
		this.add(btnBestello);
		this.add(btnBeenden);
		
		rdCurrywurst.setVisible(false);
		
		btnBestello.addActionListener(listener);
		btnBestello.setActionCommand("bestello");
		btnBeenden.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		this.setSize(300, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	
	////////////////////INNER KLASSE
	
	class AktionsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String dank = "\n\nBestello und Kuss geht raus. \nIhr MF-Halal";
			
			if(e.getActionCommand().equals("bestello")) {
				
				
				
				//Currywurst
//				if(rdCurrywurst.isSelected() == true &&
//						chbPommes.isSelected() == false &&
//						chbReis.isSelected() == false ) {
//					JOptionPane.showMessageDialog(Speisekarte.this, "Currywurst ohne Beilagen bestellt." + dank);
//				}
//				if(rdCurrywurst.isSelected() == true && chbPommes.isSelected() == true) {
//					JOptionPane.showMessageDialog(Speisekarte.this, "Currywurst mit Pommes bestellt." + dank);
//				}
//				if(rdCurrywurst.isSelected() == true && chbReis.isSelected() == true) {
//					JOptionPane.showMessageDialog(Speisekarte.this, "Currywurst mit Reis bestellt." + dank);
//				}
//				if(rdCurrywurst.isSelected() == true && 
//						chbReis.isSelected() == true &&
//						chbPommes.isSelected()== true) {
//					JOptionPane.showMessageDialog(Speisekarte.this, "Currywurst mit Reis&Pommes bestellt." + dank);
//				}
				//K�fte
//				if(rdKoefte.isSelected() == true &&
//						chbPommes.isSelected() == false &&
//						chbReis.isSelected() == false ) {
//					JOptionPane.showMessageDialog(Speisekarte.this, "K�ftespie� ohne Beilagen bestellt." + dank);
//				}
//				if(rdKoefte.isSelected() == true && chbPommes.isSelected() == true) {
//					JOptionPane.showMessageDialog(Speisekarte.this, "K�ftespie� mit Pommes bestellt." + dank);
//				}
//				if(rdKoefte.isSelected() == true && chbReis.isSelected() == true) {
//					JOptionPane.showMessageDialog(Speisekarte.this, "K�ftespie� mit Reis bestellt." + dank);
//				}
//				if(rdKoefte.isSelected() == true && 
//						chbReis.isSelected() == true &&
//						chbPommes.isSelected()== true) {
//					JOptionPane.showMessageDialog(Speisekarte.this, "K�ftespie� mit Reis&Pommes bestellt." + dank);
//				}
				
				//Nix bestellot
				
//				if(!rdCurrywurst.isSelected() && !rdKoefte.isSelected()) {
//					JOptionPane.showMessageDialog(Speisekarte.this, "Nix bestellt, Kek." + dank);
//				}
				
				
				
				
			}
			
		}
		
	}
	
	

	public static void main(String[] args) {
		new SpeisekarteGUI("Speisekarte");
	}

}
