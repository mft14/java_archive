package leconverteur;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class LeConverteur extends JFrame {

	/**
	 * Karim Kiel
	 * 12/05/2020
	 * en ecoutant Mattrach :D
	 */
	private static final long serialVersionUID = 260135236777947113L;

	private JPanel panelNorth = new JPanel();
	private JPanel panelCenter = new JPanel();
	private JPanel panelSouth = new JPanel();
	
	JLabel lblMeter = new JLabel("Meter");
	JLabel lblKilometer = new JLabel("Kilometer");
	JLabel lblSeconds = new JLabel("Seconds");
	JLabel lblMinutes = new JLabel("Minutes");
	JLabel lblHours = new JLabel("Hours");
	JLabel lblKMH = new JLabel("KM/H");
	
	JTextField tfMeter = new JTextField();
	JTextField tfKilometer = new JTextField();
	JTextField tfSeconds = new JTextField();
	JTextField tfMinutes = new JTextField();
	JTextField tfHours = new JTextField();
	JTextField tfKMH = new JTextField();
	
	
	
	private JLabel lblTitle = new JLabel("LE CONVERTEUR");
	
	String[] converteurSelection = {
			"--------Select---------",
			"Distance / Time = Speed", 
			"Time * Speed = Distance",
			"Distance / Speed = Time"};
	JComboBox<String> cbConverteurSelection = new JComboBox<String>(converteurSelection);
	
	private JButton btnCalculate = new JButton();
	private JButton btnReset = new JButton();
	
	//Constructeur
	public LeConverteur(String titel) {
		super(titel);
		this.setLayout(new BorderLayout());
		
//		Image icon = new ImageIcon(this.getClass().getResource("/img/bild.png")).getImage();
//		this.setIconImage(icon);
		
		panelNorth = createPanelNorth();
		panelSouth = createPanelSouth();
		
		this.add(panelNorth, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
		
		cbConverteurSelection.setSelectedIndex(0);
		
		//Listener
		MyListener listener = new MyListener();
		btnCalculate.addActionListener(listener);
		btnReset.addActionListener(listener);
		cbConverteurSelection.addItemListener(listener);
		
		
		//Last window operations
		setSize(300,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel createPanelNorth() {
		JPanel tempPanel = new JPanel();
		tempPanel.add(lblTitle);
		tempPanel.add(cbConverteurSelection);
		return tempPanel;
	}

	
	private JPanel createPanelSouth() {
		JPanel tempPanel = new JPanel();
		JButton btnCalculate = new JButton("Calculate ");
		JButton btnReset = new JButton("Reset");
		
		tempPanel.add(btnCalculate);
		tempPanel.add(btnReset);
		
		return tempPanel;
	}
	
	
	///////////////////////////// Converteur Creation
	private void contentDistanceTime_Speed() { //Distance / Time = Speed
		System.out.println("dt Speed is angekomm");
		panelCenter.removeAll();
		panelCenter.setBorder(new TitledBorder("GridLayout"));
		panelCenter.setLayout(new GridLayout(0,2,10,10));
		
		componentsForDistance();
		componentsForTime();
		
		panelCenter.validate();

	}
	private void contentTimeSpeed_Distance() { //Time * Speed = Distance
		
		panelCenter.removeAll();
		panelCenter.setBorder(new TitledBorder("GridLayout"));
		panelCenter.setLayout(new GridLayout(0,2,10,10));
		
		componentsForTime();
		componentsForSpeed();
		
		panelCenter.validate();
		
	}
	private void contentDistanceSpeed_Time() { //Distance / Speed = Time1
		
		panelCenter.removeAll();
		panelCenter.setBorder(new TitledBorder("GridLayout"));
		panelCenter.setLayout(new GridLayout(0,2,10,10));
		
		componentsForDistance();
		componentsForSpeed();
		
		panelCenter.validate();
		
	}
	
	///////////////////////////// Creating Buttons 'n Stuff
	
	private void componentsForDistance() {
		panelCenter.add(tfMeter);
		panelCenter.add(tfKilometer);
		
		tfMeter.setBorder(new TitledBorder("METER"));
		tfKilometer.setBorder(new TitledBorder("KILOMETER"));
	}
	private void componentsForTime() {
		panelCenter.add(tfSeconds);
		panelCenter.add(tfMinutes);
		panelCenter.add(tfHours);
		
		tfSeconds.setBorder(new TitledBorder("Seconds"));
		tfMinutes.setBorder(new TitledBorder("Minutes"));
		tfHours.setBorder(new TitledBorder("Hours"));
	}
	private void componentsForSpeed() {
		panelCenter.add(tfKMH);
		tfKMH.setBorder(new TitledBorder("Km/H"));
	
	}
	
	
	///////////////////////////// MyListener
	class MyListener implements ActionListener, ItemListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		} //actionPerformed end

		@Override
		public void itemStateChanged(ItemEvent e) {
			Object trigger = e.getSource();
			
			if(trigger instanceof JComboBox) {
				
				if(cbConverteurSelection.getSelectedIndex() == 1) {
					contentDistanceTime_Speed();
				}
				if(cbConverteurSelection.getSelectedIndex() == 2) {
					contentTimeSpeed_Distance();
				}
				if(cbConverteurSelection.getSelectedIndex() == 3) {
					contentDistanceSpeed_Time();
				}
				
			}			
		}
	}

	public static void main(String[] args) {
		new LeConverteur("Le Converteur");
	}

}


//
//
///////////////Distance Time Calc
//function calc_dt() { 
//    
//    var ausgabe = "";
//    var ergebnis = 0;
//    
//    var sec = document.getElementById('dt_sec').value;
//    var min = document.getElementById('dt_min').value;
//    var hour = document.getElementById('dt_hour').value;
//    var meter = document.getElementById('dt_meter').value;
//    var km = document.getElementById('dt_km').value;
//    
//    if ((meter == 0) && (km == 0) && (sec == 0) && (min == 0) && (hour == 0)) {
//        ausgabe = "<b style=\"color:red;\">A distance AND time value is missing</b>";
//        document.getElementById('txtOutput').innerHTML = ausgabe;
//    } else if ((meter == 0) && (km == 0)) {
//        ausgabe = "<b style=\"color:red;\">A distance value is missing</b>";
//        document.getElementById('txtOutput').innerHTML = ausgabe;
//    } else if ( (sec == 0) && (min == 0) && (hour == 0) ) {
//        ausgabe = "<b style=\"color:red;\">A time    value is missing</b>";
//        document.getElementById('txtOutput').innerHTML = ausgabe;
//    } else {
//        //ParseInt ist wichtig, sonst hatte er die Zahlen aneinanderverkettet
//        ergebnis = ((parseInt(meter)) + (parseInt(km)*1000)) / ((parseInt(hour)*3600) + (parseInt(min)*60) + parseInt(sec));
//        //ergebnis = Math.round(ergebnis * 3.6); //runden
//        ausgabe = "Average speed: " + (ergebnis*3.6) + "km/h <br> in m/s: " + ergebnis;
//        //print out
//        document.getElementById('txtOutput').innerHTML = ausgabe;
//        
//        
//        console.log("sec: " + sec + " min: " + min + " hour: " + hour);
//        console.log("meter: " + meter + " km: " + km);
//        
//    }
//}
//
///////////////Time Speed Calc
//function calc_ts() { 
//    
//    var ausgabe = "";
//    var ergebnis = 0;
//    
//    var sec = document.getElementById('ts_sec').value;
//    var min = document.getElementById('ts_min').value;
//    var hour = document.getElementById('ts_hour').value;
//    var kmh = document.getElementById('ts_kmh').value;
//    
//    if ((kmh == 0) && (sec == 0) && (min == 0) && (hour == 0)) {
//        ausgabe = "<b style=\"color:red;\">A speed AND time value is missing</b>";
//        document.getElementById('txtOutput2').innerHTML = ausgabe;
//    } else if (kmh == 0) {
//        ausgabe = "<b style=\"color:red;\">A speed value is missing</b>";
//        document.getElementById('txtOutput2').innerHTML = ausgabe;
//    } else if ( (sec == 0) && (min == 0) && (hour == 0) ) {
//        ausgabe = "<b style=\"color:red;\">A time   value is missing</b>";
//        document.getElementById('txtOutput2').innerHTML = ausgabe;
//    } else {
//        //ParseInt ist wichtig, sonst hatte er die Zahlen aneinanderverkettet
//        ergebnis = (parseInt(kmh)/3.6) * ((parseInt(hour)*3600) + (parseInt(min)*60) + parseInt(sec));
//        //ergebnis = Math.round(ergebnis * 3.6); //runden
//        ausgabe = "Distance in meter: " + (ergebnis) + "<br>in km: " + (ergebnis/1000);
//        //print out
//        document.getElementById('txtOutput2').innerHTML = ausgabe;
//        
//        console.log("sec: " + sec + " min: " + min + " hour: " + hour);
//        console.log("kmh: " + kmh);
//    } //else end
//    
//}        
//
//function calc_ds() {  //distance speed
//    
//    var ausgabe = "";
//    var ergebnis = 0;
//    var totalSeconds = 0;
//    var hh = 0;
//    var mm = 0;
//    var ss = 0;
//    
//    var kmh = document.getElementById('ds_kmh').value;
//    var meter = document.getElementById('ds_meter').value;
//    var km = document.getElementById('ds_km').value;
//    
//    if ((kmh == 0) && (meter == 0) && (km == 0) ) {
//        ausgabe = "<b style=\"color:red;\">A distance AND speed value is missing</b>";
//        document.getElementById('txtOutput3').innerHTML = ausgabe;
//    } else if (kmh == 0) {
//        ausgabe = "<b style=\"color:red;\">A speed value is missing</b>";
//        document.getElementById('txtOutput3').innerHTML = ausgabe;
//    } else if ( (meter == 0) && (km == 0) ) {
//        ausgabe = "<b style=\"color:red;\">A distance    value is missing</b>";
//        document.getElementById('txtOutput3').innerHTML = ausgabe;
//    } else {
//        //ParseInt ist wichtig, sonst hatte er die Zahlen aneinanderverkettet
//        ergebnis = ((parseInt(meter)) + (parseInt(km)*1000)) / (parseInt(kmh)/3.6);
//        ergebnis = Math.round(ergebnis); //runden, sonst komische Zahlen
//        totalSeconds = Math.round(ergebnis);
//        
//        //////////Calculate in normal time 
//        hh = Math.floor(ergebnis/3600);
//        ergebnis %= 3600;
//        mm = Math.floor(ergebnis/60);
//        ss = ergebnis % 60;
//        
//        ausgabe = "Time in seconds: " + totalSeconds + "<br>Time in hh:mm:ss format =  " + hh + ":" + mm + ":" + ss;
//        
//        document.getElementById('txtOutput3').innerHTML = ausgabe;
//    } //else end
//    
//}           