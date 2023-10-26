import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * Student: Karim Kiel
 * Datum 06/04/2020
 * Code: JAVA 9B-XX1-N01
 */

/* ####################################################
 * 
 * Einsendeaufgabe 9.2
 * 
 * #################################################### */

public class FensterVerschieben extends JFrame {
	
	//Buttons erstellen und Namen geben
	private JButton btnOben   = new JButton("Oben");
	private JButton btnRechts = new JButton("Rechts");
	private JButton btnUnten  = new JButton("Unten");
	private JButton btnLinks  = new JButton("Links");
	//Integers: xPos und yPos für die Fensterposition
	private int xPos, yPos; 
	//fensterBreite-,Höhe für flexible Fenstergrößen, für Aufgabe Java9B = 200*200
	private int fensterBreite = 200;
	private int fensterHoehe  = 200; 
	
	Dimension desktop; //Dimensionobjekt nötig für die Ermittlung der Bildschirmauflösung
	
	public FensterVerschieben(String titel) {
		super(titel); //Fenstertitel übergeben an JFrame
		//Der ActionListener für die Buttons
		MyButtonListener listener = new MyButtonListener();
		
		//BorderLayout erstellen und setLayout darauf setzen
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);
		bLayout.setVgap(25); //Aufgabe 9.2 Punkt 3 = Abstand von 25px vertikal einfügen 
		
		//in "desktop" wird das Dimensionobjekt gespeichert, nötig für 
		//die Ermittlung der Bildschirmauflösung
		desktop = Toolkit.getDefaultToolkit().getScreenSize();
		
		/*Beim Start, Fenster auf eine zufällige Bildschirmposition setzen.
		Mithilfe der Auflösung minus Fenstergröße vermeidet man eine 
		Position außerhalb des Bildschirmes. */
		xPos = (int)(Math.random() * (desktop.width - fensterBreite)); 
		yPos = (int)(Math.random() * (desktop.height - fensterHoehe));
		
		this.setLocation(xPos, yPos); //setze Fenster
		this.setSize(fensterBreite,fensterHoehe); //Vordefinierte Fenstergröße
		
		//ActionCommands für Buttons setzen
		btnOben.setActionCommand("oben");
		btnRechts.setActionCommand("rechts");
		btnUnten.setActionCommand("unten");
		btnLinks.setActionCommand("links");
		
		//Buttons hinzufügen + BorderLayout Position
		add(btnOben, BorderLayout.NORTH);
		add(btnRechts, BorderLayout.EAST);
		add(btnUnten, BorderLayout.SOUTH);
		add(btnLinks, BorderLayout.WEST);
		
		//ActionListener für alle Buttons
		btnOben.addActionListener(listener);
		btnRechts.addActionListener(listener);
		btnUnten.addActionListener(listener);
		btnLinks.addActionListener(listener);
		
		//Abschließende Fenstereigenschaften
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Innere Klasse für die ActionListener
	class MyButtonListener implements ActionListener {
		
		/*
		 * Die Buttons werden mit getActionCommand().equals("") abgefangen
		 * Danach wird geprüft, ob die xPos oder yPos außerhalb des Bildschirmes gelangt
		 * Die Variable "schrittweite" speichert die Pixelanzahl, wie weit das Fenster rutschen soll
		 * Die schrittweite wird mit der aktuellen xPos / yPos subtrahiert (linker bzw. oberer Rand) 
		 * bzw. addiert (rechter bzw. unterer Rand) , dies wird vorher mit einer If Bedingung abgefragt  
		 * und rutsche das Fenster dann aus dem Bild, würde es dann einfach fest an den Rand gesetzt.
		 * 
		 * Beim linken und oberen Rand wird das Fenster einfach auf Position x=0 / y=0 gesetzt, beim
		 * rechten und unteren Rand wird die Desktopauflösung minus Fenstergröße berechnet und dieser
		 * Wert wird als Haltepunkt genommen. 
		 * (Bei mir ist die Auflösung 1920*1080 ; 200px ist das Fenster, also stoppt das Fenster 
		 * bei mir bei 1720 bzw. 880 Pixel. Dank der Berechnung passt sich das Programm dann an.  
		 * 
		 * Springt das Fenster beim Klick noch nicht aus dem Bildschirm, so wird der "else" Zweig
		 * aktiv und dort wird die aktuelle xPos/yPos mit der schrittweite jeweils addiert (rechts/unten) 
		 * oder subtrahiert (links/oben). setLocation() muss zum Verschieben immer wieder ausgeführt werden.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			int schrittweite = 10;
			
			//Button oben gedrückt
			if (e.getActionCommand().equals("oben")) { 
				//Geht das Fenster unter 0, setze Fenster auf Position 0
				//sonst verschiebe Fenster
				//das gleiche Muster wie bei den anderen auch, nur mit versch. +, -, xPos und yPos
				if ((yPos - schrittweite) <= 0 )  {
					yPos = 0;
					setLocation(xPos, yPos);
				} else {
					yPos = yPos - schrittweite;
					setLocation(xPos, yPos);
				}
				
			}
			//Button rechts gedrückt
			if (e.getActionCommand().equals("rechts")) { 
				//Hier also statt 0 die Desktopbreite - Fensterbreite, wenn xPos größer als diese wird
				if((xPos + schrittweite) >= (desktop.width - fensterBreite) ) {
					xPos = (desktop.width - fensterBreite);
					setLocation(xPos, yPos);
				} else {
					xPos = xPos + schrittweite;
					setLocation(xPos, yPos);
				}
			}
			//Button unten gedrückt
			if (e.getActionCommand().equals("unten")) {
				
				if((yPos + schrittweite) >= (desktop.height - fensterHoehe) )  {
					yPos = desktop.height - fensterHoehe;
					setLocation(xPos, yPos);
				} else {
					yPos = yPos + schrittweite;
					setLocation(xPos, yPos);
				}

			}
			//Button links gedrückt
			if (e.getActionCommand().equals("links")) {
				
				if((xPos - schrittweite) <= 0)  {
					xPos = 0;
					setLocation(xPos, yPos);
				} else {
					xPos = xPos - schrittweite;
					setLocation(xPos, yPos);
				}
			}
		} //actionPerformed end 
	} //Innere Klasse end
	

	public static void main(String[] args) {
		//Erstellung des Fensters
		new FensterVerschieben("Java9B Einsendeaufgabe 2 - Fenster durch Buttons verschieben");
	}

}
