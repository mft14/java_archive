import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WeekdayCalc extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7242947622015980790L;
	int day, daycode, month, monthcode, year, yearcode,  weekdaycode, result = 0;
	double yeartemp;
	boolean isLeapYear;
	String currentDateConnected, typedDateConnected = "";
	
	
	JLabel labelTitle = new JLabel("<html><u>Weekday Calculator</u></html>");
	JLabel labelInfo = new JLabel("<html>Calculate Weekday <br>between 1900 - 2099!</html>");
	JLabel labelDay = new JLabel("Day:");
	JLabel labelMonth = new JLabel("Month:");
	JLabel labelYear = new JLabel("Year:");
	JTextField tfDay = new JTextField();
	JTextField tfMonth = new JTextField();
	JTextField tfYear = new JTextField();
	JButton btnCalc = new JButton("Calculate");
	JButton btnReset = new JButton("Reset");
	
	String[] weekday = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	public WeekdayCalc(String titel) {
		
		this.setTitle(titel);
		this.setLayout(new GridLayout(6,2,10,10));
		
		labelInfo.setFont(new Font("Bold", Font.PLAIN, 16));
		labelTitle.setFont(new Font("Bold", Font.PLAIN, 16));
		labelInfo.setBounds(0, 100, 100, 100);
		//alignments
		labelInfo.setHorizontalAlignment(JLabel.CENTER);
		labelDay.setHorizontalAlignment(JLabel.RIGHT);
		labelMonth.setHorizontalAlignment(JLabel.RIGHT);
		labelYear.setHorizontalAlignment(JLabel.RIGHT);
		//Add Listeners
		MyListener mylistener = new MyListener();
		tfDay.setName("tfday");
		tfMonth.setName("tfmonth");
		tfYear.setName("tfyear");
		btnCalc.setActionCommand("calculate");
		btnReset.setActionCommand("reset");
		
		tfDay.addKeyListener(mylistener);
		tfMonth.addKeyListener(mylistener);
		tfYear.addKeyListener(mylistener);
		btnCalc.addActionListener(mylistener);
		btnReset.addActionListener(mylistener);

		//add stuff
		this.add(labelInfo);
		this.add(labelTitle);
		this.add(labelDay);
		this.add(tfDay);
		this.add(labelMonth);
		this.add(tfMonth);
		this.add(labelYear);
		this.add(tfYear);
		this.add(btnCalc);
		this.add(btnReset);
		
		//Last window operations
		this.pack();
		tfDay.requestFocus();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new WeekdayCalc("Weekday Calculator");
	}
	
	
	private void calc() {
		
		double temp1 = 0, temp2 = 0, temp3 = 0;
		String yearstring;
		day = 0;
		month = 0;
		year = 0;
		currentDateConnected = "";
		typedDateConnected = "";
		
		////Get the values and check, if they are valid
		
		//Get Year
		if (Integer.parseInt(tfYear.getText()) < 1900) {
			year = 1900;
			tfYear.setText("1900");
		} else if (Integer.parseInt(tfYear.getText()) > 2099) {
			year = 2099;
			tfYear.setText("2099");
		} else {
			year = Integer.parseInt(tfYear.getText());
		}
		
		//Is it a leap year or not
		if ((year % 400) == 0) {
			isLeapYear = true;
		} else if (((year % 4) == 0) && ((year % 100) != 0)) {
			isLeapYear = true;
		} else {
			isLeapYear = false;
		}
		
		//Get month
		if (Integer.parseInt(tfMonth.getText()) < 1) {
			month = 1;
			tfMonth.setText("1");
		} else if (Integer.parseInt(tfMonth.getText()) > 12) {
			month = 12;
			tfMonth.setText("12");
		} else {
			month = Integer.parseInt(tfMonth.getText());
		}
		
		//Get day
		if (Integer.parseInt(tfDay.getText()) < 1) {
			day = 1;
			tfDay.setText("1");
		} else if (Integer.parseInt(tfDay.getText()) > 31) {
			day = 31;
			tfDay.setText("31");
		}
		
		if(month == 4 || month == 6 || month == 9 || month == 11 ) { //if the month with 30 days
			day=30; //set 30 days
			tfDay.setText("30");
		} else if (month == 2) { //if February 
			if (isLeapYear == true) {
				day = 29;
				tfDay.setText("29");
			} else {
				day = 28;
				tfDay.setText("28");
			}
		} 
		
		day = Integer.parseInt(tfDay.getText());
		
		//monthcode calc
		if (month == 1 && isLeapYear == false) {monthcode = 6;} else {monthcode = 5;}
		if (month == 2 && isLeapYear == false) {monthcode = 2;} else {monthcode = 1;}
		
		if (month == 3) {monthcode = 2;}if (month == 4) {monthcode = 5;} 
		if (month == 5) {monthcode = 0;}if (month == 6) {monthcode = 3;}if (month == 7) {monthcode = 5;}if (month == 8) {monthcode = 1;} 
		if (month == 9) {monthcode = 4;}if (month == 10) {monthcode = 6;}if (month == 11) {monthcode = 2;}if (month == 12) {monthcode = 4;}
		
		//yearcode calc
		yearstring = tfYear.getText(); //get year as string
		yearstring = yearstring.substring(2, 4); //substring last two digits
		yeartemp = Double.parseDouble(yearstring); //put these two digits in yeartemp
		
		//temps are just there to save the results in between
		temp1 = Math.floor(yeartemp/4); //calc with yeartemp
		temp2 = yeartemp + temp1;
		temp3 = temp2 % 7;
		yearcode = (int) temp3;
		
		//If year is between 1900 - 1999, add +1 at yearcode
		if (year >=1900 && year <= 1999) {yearcode++;}
		
		//final calculation
		weekdaycode = ((day + monthcode + yearcode) % 7);
		
		//Say WAS or WILL BE , compare with today
	    LocalDateTime currentDateObj = LocalDateTime.now();  
	    String currentDate = currentDateObj.toString();
	    
	    //Get current date, save it as long string
	    currentDateConnected = currentDate.substring(0, 4) + currentDate.substring(5, 7) + currentDate.substring(8, 10);
	    //Get typed date, save it as long string	    
	    typedDateConnected = tfYear.getText();
	    if (month < 10) {
	    	typedDateConnected += "0";
	    }
	    typedDateConnected += tfMonth.getText();
	    if (day < 10) {
	    	typedDateConnected += "0";
	    }
	    typedDateConnected += tfDay.getText();
	    
	    //result look like this: 20200818 (yyyymmdd), then comparing
	    
	    //Output for the user. Checking typed and current date for the right sentence
	    if (Integer.parseInt(currentDateConnected) > Integer.parseInt(typedDateConnected)) {
	    	JOptionPane.showMessageDialog(null, "The " + day + "." + month + "." +year+ " was " +weekday[weekdaycode]);
		} else if (Integer.parseInt(currentDateConnected) == Integer.parseInt(typedDateConnected))  {
			JOptionPane.showMessageDialog(null, "Today, the " + day + "." + month + "." +year+ " is " +weekday[weekdaycode]);	
		}
	    else {
			JOptionPane.showMessageDialog(null, "The " + day + "." + month + "." +year+ " will be " +weekday[weekdaycode]);
		}
	} //done 
	
	class MyListener implements ActionListener, KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				
				//Jump to next Textfields by pressing ENTER
				if (e.getComponent().getName().equals("tfday")) {
					tfMonth.requestFocus();
				} 
				if (e.getComponent().getName().equals("tfmonth")) {
					tfYear.requestFocus();
				} 
				if (e.getComponent().getName().equals("tfyear")) {
					btnCalc.doClick();
				} 

            }
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("calculate")) {
				try { 
					calc();
				} catch (NumberFormatException e2) {//if trash typed
					JOptionPane.showMessageDialog(null, "Error, please recheck the values. Years only between 1900 - 2099");
				}
			} else if (e.getActionCommand().equals("reset")) {//reset
				tfDay.setText("");
				tfMonth.setText("");
				tfYear.setText("");
			} else if (e.getActionCommand().equals("exit")) {
				
			}
		}
		
	}

}
