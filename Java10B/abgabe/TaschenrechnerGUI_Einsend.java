/*
 * Karim Kiel
 * Aufgabe 2 in Java10
 * Meine Kommentare sind mit KK: am Anfang markiert
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class TaschenrechnerGUI_Einsend extends JFrame{

	//automatisch �ber Eclipse eingef�gt
	private static final long serialVersionUID = 3006212012028893840L;
	
	//die Komponente
	//zwei Eingabefelder
	private JTextField eingabe1, eingabe2;
	//vier RadioButtons und eine Gruppe
	private JRadioButton addition, subtraktion, multiplikation, division;
	//zwei Schaltfl�chen
	private JButton schaltflaecheBerechnen, schaltflaecheBeenden;
	//ein Label f�r die Ausgabe
	private JLabel ausgabe;
	
	//KK: Initialiseren vom String Array und Auswahlmen� � la ComboBox
	private String[] operatorenListe = {"Addieren", "Subtrahieren", "Multiplizieren", "Dividieren"};
	private JComboBox<String>operatoren = new JComboBox<String>(operatorenListe);
	
	//die innere Klasse f�r die Ereignisverarbeitung
	class MeinListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//KK: Quelle in ein Objekt packen
			Object tempCombobox = e.getSource();
			
			//KK: mit instanceof dann Aktionen abfangen und 
			//mit dem Index pr�fen und ausf�hren lassen
			if(!eingabe1.getText().equals("") || !eingabe2.getText().equals("")) { //KK: Erst ausf�hren, wenn was eingetippt wurde
				if(tempCombobox instanceof JComboBox) {
					if(operatoren.getSelectedIndex() == 0 ) {
						ausgabe.setText(berechnen());
					}
					if(operatoren.getSelectedIndex() == 1) {
						ausgabe.setText(berechnen());
					}
					if(operatoren.getSelectedIndex() == 2) {
						ausgabe.setText(berechnen());
					}
					if(operatoren.getSelectedIndex() == 3) {
						ausgabe.setText(berechnen());
					}
				}
			}
			
			//wurde auf Beenden geklickt?
			//dann das Programm beenden
			if (e.getActionCommand().equals("ende")) 
				System.exit(0);
			//wurde auf Berechnen geklickt?
			//dann eine Methode f�r die Berechnung aufrufen
			//und das Ergebnis anzeigen
			if (e.getActionCommand().equals("rechnen"))
				ausgabe.setText(berechnen());
		}
	}
	
	//der Konstruktor
	public TaschenrechnerGUI_Einsend(String titel) {
		
		super(titel);
		
		//insgesamt 4 Panels
		JPanel panelEinAus, panelBerechnung, panelButtons, gross;
		
		//die Panels �ber die Methoden erstellen
		panelEinAus = panelEinAusErzeugen();
		panelBerechnung = panelBerechnungErzeugen();
		panelButtons = panelButtonErzeugen();
		
		//das Border-Layout benutzen
		//es ist Standard und muss nicht gesetzt werden
		
		//die Panels hinzuf�gen
		//die beiden gr��eren Panel fassen wir noch einmal zusammen
		gross = new JPanel();
		gross.add(panelEinAus);
		gross.add(panelBerechnung);
		//die beiden kommen in die Mitte
		add(gross,BorderLayout.CENTER);
		//das Panel mit den Buttons nach rechts
		add(panelButtons, BorderLayout.EAST);

		//die Standard-Aktion setzen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//packen und anzeigen
		pack();
		setVisible(true);
		
		//Gr��en�nderungen sind nicht zugelassen
		//damit das m�hsam erstellte Layout nicht durcheinander kommt
		setResizable(false);
	}

	//die Methode erzeugt das Panel f�r die Ein- und Ausgabe
	//und liefert es zur�ck
	private JPanel panelEinAusErzeugen() {
		JPanel tempPanel = new JPanel();
		//es enth�lt die Eingabefelder mit beschreibendem Text und die Ausgabe
		//die L�nge der Felder ist auf 10 Zeichen beschr�nkt
		eingabe1 = new JTextField(10);
		eingabe2 = new JTextField(10);
		ausgabe = new JLabel("");
		
		//das Panel bekommt ein GridLaoyut mit 2 Spalten und etwas Abstand
		tempPanel.setLayout(new GridLayout(0,2,10,10));
		//ein beschreibendes Label f�r die erste Eingabe
		tempPanel.add(new JLabel("Zahl 1:"));
		//das erste Eingabefeld
		tempPanel.add(eingabe1);
		
		//und jetzt das zweite Eingabefeld
		tempPanel.add(new JLabel("Zahl 2: "));
		tempPanel.add(eingabe2);
		
		//und nun das Ausgabefeld f�r das Ergebnis
		tempPanel.add(new JLabel("Ergebnis: "));
		tempPanel.add(ausgabe);
		
		//einen Rahmen um das Panel ziehen
		tempPanel.setBorder(new TitledBorder("Ein- und Ausgabe"));
		
		//das Panel zur�ckliefern
		return tempPanel;
	}
	
	//die Methode erzeugt das Panel f�r die Auswahl
	//der Rechenoperation und liefert es zur�ck
	private JPanel panelBerechnungErzeugen() {
		JPanel tempPanel = new JPanel();
		
		//das Panel bekommt ein GridLaoyut mit 1 Spalte
		tempPanel.setLayout(new GridLayout(0,1));
		tempPanel.add(operatoren); //KK: Statt den RadioButtons nun die ComboBox hinzuf�gen
		
		//KK: ActionListener f�r die Combobox hinzuf�gen
		MeinListener listener = new MeinListener();
		operatoren.addActionListener(listener);
		
		//und einen Rahmen
		tempPanel.setBorder(new TitledBorder("Operation: "));

		//das Panel zur�ckliefern
		return tempPanel;
	}
	
	//die Methode erzeugt das Panel f�r die Schaltfl�chen
	//und liefert es zur�ck
	private JPanel panelButtonErzeugen() {
		JPanel tempPanel = new JPanel();

		schaltflaecheBeenden = new JButton(" Beenden ");
		//das Aktion-Command setzen
		schaltflaecheBeenden.setActionCommand("ende");
		schaltflaecheBerechnen = new JButton("Berechnen");
		schaltflaecheBerechnen.setActionCommand("rechnen");
		
		//Zwischenpanel f�r die Schaltfl�chen
		//ebenfalls ein GridLayout
		tempPanel.setLayout(new GridLayout(0,1));
		tempPanel.add(schaltflaecheBerechnen);
		//ein leeres Label einf�gen
		tempPanel.add(new JLabel());
		tempPanel.add(schaltflaecheBeenden);
		
		//die Schaltfl�chen mit dem Listener verbinden
		MeinListener listener = new MeinListener();
		schaltflaecheBeenden.addActionListener(listener);
		schaltflaecheBerechnen.addActionListener(listener);
		
		//das Panel zur�ckliefern
		return tempPanel;
	}
	
	//die Methode berechnet das Ergebnis und liefert es als 
	//Zeichenkette zur�ck
	private String berechnen() {
		
		//ergebnis muss initialisiert werden
		double zahl1, zahl2, ergebnis = 0;
		boolean fehlerFlag = false;
	
		//jetzt mit Fehlerbehandlung und Ausgabe der Meldung durch eine eigene Methode
		try {
			zahl1 = Double.parseDouble(eingabe1.getText());
		}
		catch (Exception NumberFormatException) {
			fehlermeldung(eingabe1);
			return ("Nicht definiert");
		}
		
		try {
			zahl2 = Double.parseDouble(eingabe2.getText());
		}
		catch (Exception NumberFormatException) {
			fehlermeldung(eingabe2);
			//das Ergebnis ist nicht definiert
			return ("Nicht definiert");
		}
		//welche Operation ist ausgew�hlt?
		
		
		if (operatoren.getSelectedIndex() == 0)
			ergebnis = zahl1 + zahl2;
		if (operatoren.getSelectedIndex() == 1)
			ergebnis = zahl1 - zahl2;
		if (operatoren.getSelectedIndex() == 2)
			ergebnis = zahl1 * zahl2;
		//bei der Division �berpr�fen wir den zweiten Wert auf 0
		if (operatoren.getSelectedIndex() == 3) {
			if (zahl2 != 0)
				ergebnis = zahl1 / zahl2;
			else 
				fehlerFlag = true;
		}
		//wenn es keine Probleme gegeben hat, liefern wir das Ergebnis zur�ck
		if (fehlerFlag == false) {
			//die Formatvorgabe �ber die Klasse DecimalFormat
			DecimalFormat formatFolge = new DecimalFormat("0.##");
			//das Format anwenden, das Ergebnis ist eine Zeichenkette
			return (formatFolge.format(ergebnis));
		}
		else
			return ("Nicht definiert");
	}
	
	private void fehlermeldung(JTextField eingabefeld) {
		JOptionPane.showMessageDialog(this, "Ihre Eingabe ist nicht g�ltig","Eingabefehler", JOptionPane.ERROR_MESSAGE);
		eingabefeld.requestFocus();
	}
	public static void main(String[] args) {
		new TaschenrechnerGUI_Einsend("Taschenrechner mit JComboBox");
	}
}
