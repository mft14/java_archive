package hfberech;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class HFberech extends JFrame {
	
	//Labels and Textfields
	private JLabel lblSendeleistung = new JLabel("Sendeleistung in Watt: ");
	private JLabel lblEingangsspannung = new JLabel("Eingangsspannung in Mikrovolt: ");
	private JLabel lblATSender = new JLabel("AT Sender in Meter: ");
	private JLabel lblATEmpfaenger = new JLabel("AT Empfänger in Meter: ");
	private JLabel lblReichweite = new JLabel("Reichweite in Kilometer: ");
	private JLabel lblWelllaenge = new JLabel("Wellenlänge in Meter: ");
	
	private JTextField tfSendeleistung = new JTextField();
	private JTextField tfEingangsspannung = new JTextField();
	private JTextField tfATSender = new JTextField();
	private JTextField tfATEmpfaenger = new JTextField();
	private JTextField tfReichweite = new JTextField();
	private JTextField tfWellenlaenge = new JTextField();
	
	//Checkbox Array
	String[] LeistungAuswahl = {"Sendeleistung", "Eingangsspannung", "Antennenhöhe Sender", "Antennenhöhe Empfänger", 
			"Reichweite"}; //TODO Evtl. muss Wellenlänge noch mit rein
	JComboBox <String> cbAuswahl = new JComboBox <String>(LeistungAuswahl); // 
	
	private JButton btnBerechnen = new JButton("Berechnen!");
	private JButton btnReset = new JButton("Eingaben löschen");
	
	private static double sendeleistung = 0;
	private static double eingangsspannung = 0;
	private static double at_sender = 0; // in meter
	private static double at_empfaenger = 0; //in meter
	private static double reichweite = 0; //in kilometer km
	private static double wellenlaenge = 0;
	
	private static double rw = 0;
	private static double sw = 0;
	
	private static double e = eingangsspannung/1000000;
	private static double f = ((2*3.1415)/wellenlaenge);
	
	private int whatsSelected = 0;
	
	//wb = was berechnen
	private enum wb {SENDELEISTUNG, EINGANGSSPANNUNG, ATSENDER, ATEMPFAENGER, REICHWEITE, WELLENLAENGE};
	
	class MeinListener implements ActionListener, ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getSource() instanceof JComboBox) {
				//Getriggert beim Wechseln der ComboBox oben
				if(cbAuswahl.getSelectedIndex() == wb.SENDELEISTUNG.ordinal() ) 		auswaehlen( wb.SENDELEISTUNG.ordinal() );
				if(cbAuswahl.getSelectedIndex() == wb.EINGANGSSPANNUNG.ordinal() ) 		auswaehlen( wb.EINGANGSSPANNUNG.ordinal() );
				if(cbAuswahl.getSelectedIndex() == wb.ATSENDER.ordinal() ) 				auswaehlen( wb.ATSENDER.ordinal() );
				if(cbAuswahl.getSelectedIndex() == wb.ATEMPFAENGER.ordinal() ) 			auswaehlen( wb.ATEMPFAENGER.ordinal() );
				if(cbAuswahl.getSelectedIndex() == wb.REICHWEITE.ordinal() ) 			auswaehlen( wb.REICHWEITE.ordinal() );
				if(cbAuswahl.getSelectedIndex() == wb.WELLENLAENGE.ordinal() ) 			auswaehlen( wb.WELLENLAENGE.ordinal() );
			}
		}

		//Getriggert von der combobox mit Parameter
		private void auswaehlen(int hf) { if(hf == 0) { //Sendeleistung
				tfSendeleistung.setEnabled(false); 
				whatsSelected = 0;
				tfSendeleistung.setText("0");
			} else { tfSendeleistung.setEnabled(true); }
			
			if(hf == 1) { //Eingangsspannung
				tfEingangsspannung.setEnabled(false); 
				whatsSelected = 1;	
				tfEingangsspannung.setText("0");
			} else { tfEingangsspannung.setEnabled(true); }
			
			if(hf == 2) { //AT Sender
				tfATSender.setEnabled(false); 
				whatsSelected = 2;
				tfATSender.setText("0");
			} else { tfATSender.setEnabled(true); }
						
			if(hf == 3) {tfATEmpfaenger.setEnabled(false); whatsSelected = 3;		} else { tfATEmpfaenger.setEnabled(true); }
			if(hf == 4) {tfReichweite.setEnabled(false); whatsSelected = 4;			} else { tfReichweite.setEnabled(true); }
			if(hf == 5) {tfWellenlaenge.setEnabled(false); whatsSelected = 5;		} else { tfWellenlaenge.setEnabled(true); }
		}

		@Override
		public void actionPerformed(ActionEvent e) { //Button Press
			if(e.getSource() == btnBerechnen) berechnen();
			if(e.getSource() == btnReset) reset();
		}
		
	} 
	
	public HFberech(String titel) {
		
		super(titel);
		
		MeinListener listener = new MeinListener();
		setLayout(new BorderLayout());
		
		tfSendeleistung.setEnabled(false);
		add(panelCheckbox(), BorderLayout.NORTH);
		add(panelEingabe(), BorderLayout.CENTER);
		add(panelButtons(), BorderLayout.SOUTH);
		
		btnBerechnen.addActionListener(listener);
		btnReset.addActionListener(listener);
		cbAuswahl.addItemListener(listener);
		
		//Last window operations
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	//Panel für die Checkbox
	private JPanel panelCheckbox() {
		JPanel tempPanel = new JPanel();
		tempPanel.setBorder(new TitledBorder("Wählen sie, was sie berechnen möchten"));
		tempPanel.add(cbAuswahl);
		return tempPanel;
	}
	
	//Panel der Eingabe
	private JPanel panelEingabe() {
		JPanel tempPanel = new JPanel();
		tempPanel.setBorder(new TitledBorder("Eingabe"));
		tempPanel.setLayout(new GridLayout(6,2));
		
		tempPanel.add(lblSendeleistung);
		tempPanel.add(tfSendeleistung);
		
		tempPanel.add(lblEingangsspannung);
		tempPanel.add(tfEingangsspannung);
		
		tempPanel.add(lblATSender);
		tempPanel.add(tfATSender);
		
		tempPanel.add(lblATEmpfaenger);
		tempPanel.add(tfATEmpfaenger);
		
		tempPanel.add(lblReichweite);
		tempPanel.add(tfReichweite);
		
		tempPanel.add(lblWelllaenge);
		tempPanel.add(tfWellenlaenge);
		
		return tempPanel;
	}
	
	//Panel mit den Buttons unten
	private JPanel panelButtons() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(0,2));
		tempPanel.setBorder(new EmptyBorder(20,20,20,20));
		tempPanel.add(btnBerechnen);
		tempPanel.add(btnReset);
		
		return tempPanel;
	}
	
	//Berechnen Methode
	private void berechnen() {
		DecimalFormat df = new DecimalFormat("0.##"); //Für zwei Stellen hinter dem Komma
		System.out.println("whatsSelected: " + whatsSelected); //Check
		//TODO
		
		try {
			
			sendeleistung = Double.parseDouble(tfSendeleistung.getText());
			eingangsspannung = Double.parseDouble(tfEingangsspannung.getText());
			at_sender = Double.parseDouble(tfATSender.getText());
			at_empfaenger = Double.parseDouble(tfATEmpfaenger.getText());
			reichweite = Double.parseDouble(tfReichweite.getText());
			wellenlaenge = Double.parseDouble(tfWellenlaenge.getText());
			
			//&& eingangsspannung != 0 && antennenhoehe_empfaenger != 0 && antennenhoehe_sender != 0 && reichweite != 0
			if(whatsSelected == 0 ) { //Sendeleistung berechnen
				sendeleistung = 1 /  ( Math.pow(((28*3.1415*at_sender*at_empfaenger)/ ((reichweite*reichweite)*f*eingangsspannung)), 2));  
				System.out.println("Der Sender hat " + sendeleistung + "Watt Sendeleistung");
			}
			else if(whatsSelected == 1) { //Eingangsspannung berechnen
				eingangsspannung =  ((Math.sqrt(sendeleistung)*28*3.1415*at_sender*at_empfaenger) / 
						((reichweite*reichweite)*f));
				System.out.println("Die Eingangsspannung ist " + eingangsspannung + "µV");
			}
			else if(whatsSelected == 2) { //Antennhöhe Sender berechnen
				at_sender = 1 / ((Math.sqrt(sendeleistung)*28*3.1415*at_empfaenger) / 
						((reichweite*reichweite)*f*eingangsspannung)); 
				System.out.println("Die TX-Antennenhöhe ist " + at_sender + "m");
			}
			else if(whatsSelected == 3) { //Antennhöhe Empfänger berechnen
				at_empfaenger = 1/((Math.sqrt(sendeleistung)*28*3.1415*at_sender)/((reichweite*reichweite)*f*eingangsspannung));
				System.out.println("Die RX-Antennenhöhe ist " + at_empfaenger + "m");
			}
			
			else if(whatsSelected == 4) { //Reichweite berechnen
				rw = Math.sqrt((Math.sqrt(sendeleistung)*28*3.1415*at_sender*at_empfaenger)/(f*e))/1000;
				sw = ((Math.sqrt(at_empfaenger)+Math.sqrt(at_sender))*3.6);
				System.out.println("Sichtweite beachten!");
				System.out.println(df.format(sw)+"km Sichtweite");
				System.out.println(df.format(rw)+"km Reichweite");
				
				if(rw > sw) {
					System.out.println("Wenn die Sichtweite deutlich unter der Reichweite liegt, ist mit schwächeren Signalen zu rechnen (-40db)");
				}
			}
			else if(whatsSelected == 5) {
				System.out.println("Ohne Wellenlänge geht nichts");
			}
			
		
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Bitte Eingabe überprüfen!");
		}
	}
	
	private void reset() {
		tfATEmpfaenger.setText("0");
		tfATSender.setText("0");
		tfEingangsspannung.setText("0");
		tfReichweite.setText("0");
		tfSendeleistung.setText("0");
		tfWellenlaenge.setText("0");
		
		sendeleistung = 0;
		eingangsspannung = 0;
		at_sender = 0; // in meter
		at_empfaenger = 0; //in meter
		reichweite = 0; //in kilometer km
		wellenlaenge = 0;
		rw = 0;
		sw = 0;
	}
	
	//Starte Programm
	public static void main(String[] args) {	new HFberech("HF Berechnung"); }
}
