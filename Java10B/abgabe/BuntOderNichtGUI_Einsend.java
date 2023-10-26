import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class BuntOderNichtGUI_Einsend extends JFrame{
	//automatisch über Eclipse erzeugt
	private static final long serialVersionUID = 2007237315429965675L;

	//die verfügbaren Farben werden in einem Array ablegt
	private String[] farbAuswahl = {"Rot", "Gelb", "Blau", "Grün", "Schwarz"};
	

	//die Komponenten
	//für die Auswahl farbig oder nicht über RadioButtons
	private JRadioButton bunt, nichtBunt;
	private ButtonGroup gruppe;
	//für die Auswahl groß oder kleine
	private JCheckBox gross;
	//für die Auswahl der Farben
	private JComboBox <String> auswahl;
	//für das Beenden
	private JButton schaltflaecheBeenden;
	//für die Anzeige
	private JLabel anzeige;
	
	//die innere Klasse für die Listener
	//Sie implementiert jetzt auch den ItemListener
	class MeinListener implements ActionListener, ItemListener {

		//die Methode des ActionListeners
		//für die Farbauswahl und die Schaltfläche
		@Override
		public void actionPerformed(ActionEvent e) {
			//zum einfacheren Zugriff die Quelle in einem Object ablegen
			Object ausloeser = e.getSource();
			//Wer hat das Ereignis ausgelöst?
			//die ComboBox?
			if (ausloeser instanceof JComboBox) {
				//was ist auswgewählt?
				if (auswahl.getSelectedItem().toString().equals("Rot"))
					anzeige.setForeground(Color.RED);
				if (auswahl.getSelectedItem().toString().equals("Gelb"))
					anzeige.setForeground(Color.YELLOW);
				if (auswahl.getSelectedItem().toString().equals("Blau"))
					anzeige.setForeground(Color.BLUE);
				if (auswahl.getSelectedItem().toString().equals("Grün"))
					anzeige.setForeground(Color.GREEN);
				if (auswahl.getSelectedItem().toString().equals("Schwarz"))
					anzeige.setForeground(Color.BLACK);
			}
			//die Schaltfläche
			if (ausloeser instanceof JButton) {
				//eigentlich ist die Abfrage nicht nötig
				//das macht uns das Leben aber leichter, wenn neue Schaltflächen dazu kommen
				if (e.getActionCommand().equals("Beenden"))
					System.exit(0);
			}
		}

		//für die CheckBox und die RadioButtons 
		@Override
		public void itemStateChanged(ItemEvent e) {
			//zum einfacheren Zugriff die Quelle in einem Object ablegen
			Object ausloeser = e.getSource();
			//Wer hat das Ereignis ausgelöst?
			//die Checkbox?
			if (ausloeser instanceof JCheckBox) {
				//ist die CheckBox markiert?
				if (gross.isSelected() == true)
					anzeige.setFont(new Font("Arial", Font.PLAIN,30));
				else
					anzeige.setFont(new Font("Arial", Font.PLAIN,14));
			}
			//oder waren es die RadioButtons
			if (ausloeser instanceof JRadioButton) {				
				//ist bunt markiert?
				if (bunt.isSelected() == true)
					auswahl.setEnabled(true);
				else
					auswahl.setEnabled(false);
			}
			
			/*
			 * KK: Hier ändert sich die Farbe auf schwarz, ohne dabei den Wert in der 
			 * Combobox zu ändern.
			 */
				if (bunt.isSelected() == true)	{
				    auswahl.setSelectedIndex(0);
				} else {
					anzeige.setForeground(Color.BLACK);
				}
		}
	}
	
	//der Konstruktor
	public BuntOderNichtGUI_Einsend(String titel) {
		super(titel);
		
		gross = new JCheckBox("Größer darstellen");
		
		//die Liste erzeugen
		//die Daten kommen aus dem Array farbAuswahl
		auswahl = new JComboBox<String> (farbAuswahl);
		
		//die Schaltfläche
		schaltflaecheBeenden = new JButton("Beenden");
		
		//die RadioButtons und die Gruppe
		bunt = new JRadioButton("bunt");
		nichtBunt = new JRadioButton("einfarbig");
		
		gruppe = new ButtonGroup();
		gruppe.add(bunt);
		gruppe.add(nichtBunt);
		
		//das Label
		anzeige = new JLabel("Der Text");
		//die Schriftart etc. setzen
		anzeige.setFont(new Font("Arial", Font.PLAIN,14));
		
		//ein Gridlayout mit 2 Spalten und etwas Abstand
		setLayout(new GridLayout(0,2,10,10));

		//die Komponenten hinzufügen
		//erst das Label
		add(anzeige);
		//daneben kommt die Checkbox
		add(gross);
		//der erste RadioButton
		add(bunt);
		//daneben die ComboBox
		add(auswahl);
		//der zweite RadioButton
		add(nichtBunt);
		//und links unten die Schaltfläche
		add(schaltflaecheBeenden);
		
		//die Listener verbinden
		MeinListener listener = new MeinListener();
		schaltflaecheBeenden.addActionListener(listener);
		gross.addItemListener(listener);
		
		//für die RadioButtons muss das für jeden Button passieren, nicht für die Gruppe!
		bunt.addItemListener(listener);
		nichtBunt.addItemListener(listener);
		auswahl.addActionListener(listener);
		
		//KK: Nach der Listener Vergabe  den "Bunt" Radiobutton auswählen, sodass
		//dies gleich als Trigger erkannt wird. Der Rest wird im Itemlistener erledigt
		bunt.setSelected(true);

		//die Standard-Aktion setzen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//das Fenster "packen" und anzeigen
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new BuntOderNichtGUI_Einsend("Wie hätten Sie es denn gerne?");
	}
}


