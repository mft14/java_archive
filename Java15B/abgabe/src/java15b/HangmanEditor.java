package java15b;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class HangmanEditor extends JFrame  {
	
	/**
	 * Karim Kiel
	 * 03/10/2020
	 * Java15B Einsendaufgabe
	 * 15B-XX1-K02
	 */
	private static final long serialVersionUID = 7865666697309728586L;
	
	//Instanzvariablen
	JButton btnReset = new JButton("Liste zur�cksetzen");
	JButton btnNeuesWort = new JButton("Neues Wort hinzuf�gen");
	//Textfield zum Eintragen neuer W�rter
	//Textarea zum Anzeigen der W�rter (nicht �nderbar)
	JTextField tfWoerter = new JTextField();
	JTextArea taListeAnzeigen = new JTextArea();
	
	JPanel panelButtons = new JPanel();
	JPanel panelWoerter = new JPanel();
	//String als Ablage der W�rter aus der Datei
	String wortliste = ""; 
	
	
	//die innere Klasse f�r den ActionListener
	class MeinListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("reset")) {
				listeZuruecksetzen();
			}
				
			if (e.getActionCommand().equals("neuesWort"))
				neuesWort();
		}
	}
	
	public HangmanEditor(String title) {
		super(title);
		
		MeinListener listener = new MeinListener();
		BorderLayout blayout = new BorderLayout();
		setLayout(blayout);
		//ActionCommands
		btnReset.addActionListener(listener);
		btnNeuesWort.addActionListener(listener);
		btnReset.setActionCommand("reset");
		btnNeuesWort.setActionCommand("neuesWort");
		//Panels
		panelButtons.setLayout(new GridLayout(1,1,10,10));
		panelButtons.add(tfWoerter);
		panelButtons.add(btnNeuesWort);
		//Textarea einrichten
		taListeAnzeigen.setBorder(new TitledBorder("Liste der aktuell gespeicherten W�rter"));
		taListeAnzeigen.setEditable(false);
		//Border f�r's Textfeld
		tfWoerter.setBorder(new TitledBorder("Hier neue W�rter eintragen!"));
		//Ins Borderlayout einf�gen		
		this.add(panelButtons, BorderLayout.NORTH);
		this.add(taListeAnzeigen, BorderLayout.CENTER);
		this.add(btnReset, BorderLayout.SOUTH);
		//restliche Fenstereinstellungen
		setSize(500,400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		//die hangmanwoerter.txt Datei lesen Methode
		dateiInitialisieren();
	}
	
	//die Datei lesen
	private void dateiInitialisieren() {
		try (FileReader datei = new FileReader("hangmanwoerter.txt"))  {
			tfWoerter.read(datei, null); //Lesen der Datei, Inhalt ins Textfeld
			wortliste = tfWoerter.getText(); //Speicher Inhalt vom TF in String
			tfWoerter.setText(""); //L�sche TF Inhalt wieder
			woerterAnzeigen(); //Aktualisiere Liste
		} catch (IOException e) {//Wenn Datei fehlerhaft, wird eine neue erstellt, sobald man neues Wort einf�gt. 
			JOptionPane.showMessageDialog(this, "Beim Laden ist ein Problem aufgetreten. Die Liste wird neu erstellt.");
			taListeAnzeigen.setText("F�gen Sie ein neues Wort hinzu, um eine neue Liste anzufangen!");
		}
	}
	
//Die Methode schreibt alle gespeicherten W�rter in die Textarea
private void woerterAnzeigen() {
	int wortCounter = 0; //Anzahl der W�rter werden hier gespeichert
	String listeAnzeigen = ""; //F�r das R�sum� am Ende
	StringBuilder wortBuilder = new StringBuilder(wortliste);//Stringbuilder f�r einfacheres Bearbeiten
	
	//KK1: Einzelne W�rter rauspicken mit StringBuilder
	while (wortBuilder.indexOf(",") > -1) { //wenn kein Komma, gehe raus aus Schleife
		listeAnzeigen += (  wortBuilder.substring(0, wortBuilder.indexOf(",")) + "\n" );//substring vom Anfang bis zum n�chsten Komma 
		wortBuilder.delete(0, wortBuilder.indexOf(",") + 1); //Rausl�schen des Wortes, +1 f�r das KOmma
		wortCounter++; //W�rter um 1 erh�hen als Counter
	} //while end
	listeAnzeigen += ("\n W�rteranzahl: " + wortCounter ); //Zeige die Anzahl der W�rter
	taListeAnzeigen.setText(listeAnzeigen); //Am Ende in die Textarea setzen
}

private void neuesWort() {
	if(tfWoerter.getText().equals("")) { //Wenn man "nichts" hinzuf�gt
		JOptionPane.showMessageDialog(null, "Bitte ein Wort eintragen!", "Fehler", JOptionPane.ERROR_MESSAGE);
	} else { //sonst neues Wort einf�gen
		JOptionPane.showMessageDialog(null, "Das Wort \"" + tfWoerter.getText() + "\" wurde hinzugef�gt!");
		wortliste = wortliste.replaceAll("\\d",""); //Alle Zahlen rauswerfen
		wortliste += (tfWoerter.getText() + ","); //neues Wort hinzuf�gen, Komma am Ende f�r den substring
		wortliste = wortliste.replaceAll("\\d",""); //Nochmal Zahlen rauswerfen, falls Benutzer Zahlen eingef�gt hat
		wortliste = wortliste.replaceAll("\\s+", ""); //Entfernt Leerzeichen, Umbr�che, Tabs, etc.
		int anzahlKomma = wortliste.length() - wortliste.replace(",", "").length(); //W�rter z�hlen
		wortliste += (anzahlKomma); //Anzahl der W�rter hinten dranh�ngen
		
		tfWoerter.setText(""); //Textfeld leeren
		
		//Schreibvorgang
		try (FileWriter datei = new FileWriter("hangmanwoerter.txt")) { //den Inhalt aus dem Feld schreiben 
			tfWoerter.setText(wortliste);//Liste kurz ins Textfeld packen
			tfWoerter.write(datei);  //Daten speichern
			tfWoerter.setText(""); //Textfeld wieder leeren
		} catch (IOException e) { //Exception
			JOptionPane.showMessageDialog(this, "Beim Schreiben ist ein Problem aufgetreten", "Fehler", JOptionPane.ERROR_MESSAGE); 
		}//Schreibvorgang Ende
		woerterAnzeigen();//Wortliste direkt aktualisieren
	}
}
	
private void listeZuruecksetzen() {
	//Best�tigungsdialog, ob Liste zur�ckgesetzt werden soll
	int dialogButton = JOptionPane.showConfirmDialog(null, "Soll die W�rterliste gel�scht werden?", "Zur�cksetzen", JOptionPane.YES_NO_OPTION);
	if(dialogButton == JOptionPane.YES_OPTION) { //Ja geklickt, Liste wird gel�scht
		
		try (FileWriter datei = new FileWriter("hangmanwoerter.txt")) { //den Inhalt aus dem Feld schreiben 
			tfWoerter.setText("");
			tfWoerter.write(datei);
			wortliste = "";
			woerterAnzeigen();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Beim Schreiben ist ein Problem aufgetreten"); 
		}
	} else {
		woerterAnzeigen();
	}
}
	
	public static void main(String[] args) {
		new HangmanEditor("HangmanFX Wort Editor");
	}

}
