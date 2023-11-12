package ln_pachecker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PAChecker extends JFrame {
	
	/*
	 * Made by Karim Kiel
	 * First version as JS App on http://metalfortress.de/codefun/mf_pachecker.html
	 * written 09/05/2020 during Corona Pandemie :D
	 * v 1.0
	 */
	private static final long serialVersionUID = 5355917972779643517L;
	
	//Panels
	private JPanel panelHeader;
	private JPanel panelCenter = new JPanel();
	private JPanel panelPro1;
	private JPanel panelPro2;
	private JPanel panelResult;
	
	//Methods, that need to be seen in ActionListener
	private JLabel lblResult = new JLabel("Waiting for a result.....");
	private JLabel lblSave = new JLabel("");
	private JLabel lblPrice1 = new JLabel("Price 1");
	private JLabel lblPrice2 = new JLabel("Price 2");
	private JLabel lblAmount1 = new JLabel("Amount 1");
	private JLabel lblAmount2 = new JLabel("Amount 2");
	
	//TextFields
	private JTextField tfPrice1 = new JTextField();
	private JTextField tfPrice2 = new JTextField();
	private JTextField tfAmount1 = new JTextField();
	private JTextField tfAmount2 = new JTextField();
	
	//Buttons
	private JButton btnCalc = new JButton("Calculate!");		
	private JButton btnReset = new JButton("Reset");	
	
	//Image Loader
	Image icon = new ImageIcon(this.getClass().getResource("/img/mf_pachecker.png")).getImage();
	
	private Font biggerFont = new Font("Arial", Font.BOLD, 16);
	private Font bigFont = new Font("Arial", Font.BOLD, 16);
	
	// done
	
	public PAChecker(String title) {
		super(title);
		this.setIconImage(icon); //JFrame icon
		
		//look and feeeeel
		String lookAndFeel = UIManager. getSystemLookAndFeelClassName();
		try { UIManager.setLookAndFeel(lookAndFeel); } 
		catch (Exception e) { /*kann leer bleiben*/ } 
		
		//Layout overall and for center area
		this.setLayout(new BorderLayout());
		panelCenter.setLayout(new GridLayout(0,1,10,10));
		
		//Font Size
		lblResult.setFont(biggerFont);
		lblPrice1.setFont(bigFont);
		lblPrice2.setFont(bigFont);
		lblAmount1.setFont(bigFont);
		lblAmount2.setFont(bigFont);
		tfAmount1.setFont(bigFont);
		tfAmount2.setFont(bigFont);
		tfPrice1.setFont(bigFont);
		tfPrice2.setFont(bigFont);
		
		//Panels to objects from Methods
		panelHeader = panelHeaderCreate();
		panelPro1 = panelPro1Create();
		panelPro2 = panelPro2Create();
		panelResult = panelResultCreate();
		
		//Adding time
		this.add(panelHeader,BorderLayout.NORTH);
		this.add(panelCenter,BorderLayout.CENTER);
		this.add(panelResult,BorderLayout.SOUTH);
		
		//Adding Inputpanels to Centerpanel
		panelCenter.add(panelPro1);
		panelCenter.add(panelPro2);
		
		//Last window operations
		this.pack();
		this.setMinimumSize(new Dimension(350,500));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	//Panel Methods
	////////////////////////
	
	public JPanel panelHeaderCreate() {
		JPanel tempPanel = new JPanel(); //store Panel in temp
		JLabel title = new JLabel("Price-Amount Checker");
		
		title.setFont(biggerFont); //set Font
		tempPanel.setLayout(new GridLayout(0,2));  //layout
		tempPanel.add(new JLabel(new ImageIcon(icon)));
		tempPanel.add(title); //adding
		
		return tempPanel;
	}
	public JPanel panelPro1Create() {
		JPanel tempPanel = new JPanel();
		//set Layout and border
		tempPanel.setLayout(new GridLayout(2,2));
		
		//adding time
		tempPanel.add(lblPrice1);
		tempPanel.add(tfPrice1);
		tempPanel.add(lblAmount1);
		tempPanel.add(tfAmount1);
		//KeyListenerTime
		MyListener keyListener = new MyListener();
		tfAmount1.addKeyListener(keyListener);
		tfPrice1.addKeyListener(keyListener);
		tfAmount1.setName("tfA1");
		tfPrice1.setName("tfP1");
		
		return tempPanel;
	}
	public JPanel panelPro2Create() {
		JPanel tempPanel = new JPanel();
		
		//set Layout and border
		tempPanel.setLayout(new GridLayout(2,2));
		
		//adding time
		tempPanel.add(lblPrice2);
		tempPanel.add(tfPrice2);
		tempPanel.add(lblAmount2);
		tempPanel.add(tfAmount2);
		
		//KeyListenerTime
		MyListener keyListener = new MyListener();
		tfAmount2.addKeyListener(keyListener);
		tfPrice2.addKeyListener(keyListener);
		tfAmount2.setName("tfA2");
		tfPrice2.setName("tfP2");
		
		return tempPanel;
	}
	public JPanel panelResultCreate() {
		JPanel tempPanel = new JPanel();

		//set Fonts and Layout
		btnCalc.setFont(biggerFont);
		btnReset.setFont(biggerFont);
		tempPanel.setLayout(new GridLayout(0,1));
		
		//adding time
		tempPanel.add(btnCalc);
		tempPanel.add(btnReset);
		tempPanel.add(lblResult);
		tempPanel.add(lblSave);
		
		//make the result in the middle of the STREET
		lblResult.setHorizontalAlignment(JLabel.CENTER);
		lblSave.setHorizontalAlignment(JLabel.CENTER);
		
		//Listener time
		MyListener listener = new MyListener();
		btnCalc.addActionListener(listener);
		btnReset.addActionListener(listener);
		btnCalc.setActionCommand("calc");
		btnReset.setActionCommand("reset");
		return tempPanel;
	}
	
	////////////////////////
	//Panel Methods END
	
	class MyListener implements ActionListener, KeyListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//variables for easier calculating
			double price1, price2, amount1, amount2, res1, res2 = 0;
			
			if(e.getActionCommand().equals("calc")) { //when calc
				
				try { //try/catch for entering shit in TextFields
					String temp; //storing to replace , with .
					DecimalFormat df = new DecimalFormat("0.##"); //Decimal Format 
					
					temp = tfPrice1.getText();
					temp = temp.replace(',', '.');
					price1 = Double.parseDouble(temp);
					
					temp = tfPrice2.getText();
					temp = temp.replace(',', '.');
					price2 = Double.parseDouble(temp);
					
					temp = tfAmount1.getText();
					temp = temp.replace(',', '.');
					amount1 = Double.parseDouble(temp);
					
					temp = tfAmount2.getText();
					temp = temp.replace(',', '.');
					amount2 = Double.parseDouble(temp);
					
					res1 = price1 / amount1; //Calculation
					res2 = price2 / amount2;
					
					if(res1 < res2) { //Product 1 cheaper
						lblResult.setText("Product 1 is cheaper");
						
						lblSave.setText("You save " + df.format((res2 - res1)*amount1) + " €");
						//Make colors to see better
						lblPrice1.setForeground(Color.GREEN);
						lblAmount1.setForeground(Color.GREEN);
						lblPrice2.setForeground(Color.RED);
						lblAmount2.setForeground(Color.RED);
						lblResult.setForeground(Color.BLUE);
					}
					
					if(res1 > res2) { //Product 2 cheaper
						lblResult.setText("Product 2 is cheaper");
						lblSave.setText("You save " + df.format((res1 - res2)*amount2) + " €");
						
						lblPrice1.setForeground(Color.RED);
						lblAmount1.setForeground(Color.RED);
						lblPrice2.setForeground(Color.GREEN);
						lblAmount2.setForeground(Color.GREEN);
						lblResult.setForeground(Color.BLUE);

					}
					
					if(res1 == res2) { //Both are equal
						lblResult.setText("Both are equal");
						lblSave.setText("You save 0 €");
						
						lblPrice1.setForeground(Color.GREEN);
						lblAmount1.setForeground(Color.GREEN);
						lblPrice2.setForeground(Color.GREEN);
						lblAmount2.setForeground(Color.GREEN);
						lblResult.setForeground(Color.BLUE);
					}
					
				} catch (NumberFormatException e2) {
					//When something fails, 
					lblResult.setForeground(Color.RED);
					lblResult.setText("Missing fields");
				}
			}
			
			//Reset Function
			if(e.getActionCommand().equals("reset")) {
				//resetting
				tfPrice1.setText("");
				tfPrice2.setText("");
				tfAmount1.setText("");
				tfAmount2.setText("");
				lblResult.setText("");
				lblSave.setText("");
				
				lblPrice1.setForeground(Color.BLACK);
				lblAmount1.setForeground(Color.BLACK);
				lblPrice2.setForeground(Color.BLACK);
				lblAmount2.setForeground(Color.BLACK);
				
				tfPrice1.requestFocus(); //request Focus on first JTextField
			}
		} //actionPerformed end

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				
				//Jump to next Textfields by pressing ENTER
				if (e.getComponent().getName().equals("tfP1")) {
					tfAmount1.requestFocus();
				} 
				if (e.getComponent().getName().equals("tfA1")) {
					tfPrice2.requestFocus();
				} 
				if (e.getComponent().getName().equals("tfP2")) {
					tfAmount2.requestFocus();
				} 
				if (e.getComponent().getName().equals("tfA2")) {
					btnCalc.doClick();
				} 
            }
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyTyped(KeyEvent e) {
			Object trigger = e.getSource();
			char c = e.getKeyChar(); //get chars
			if(trigger instanceof JTextField) {
				
				if( c==KeyEvent.VK_PERIOD  || c==KeyEvent.VK_ENTER || c==KeyEvent.VK_COMMA || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE) {
					//exceptions of fields, that aren't pressed
				} else if(c==KeyEvent.VK_R) {

				}
				else if(!Character.isDigit(c)) { //filter out numbers, a ! is needed to switch
						getToolkit().beep(); //error sound
						e.consume(); //doesn't let something write
						lblResult.setForeground(Color.RED);
						lblResult.setText("Please use numbers or . and , only");
				}
			} //if instance of END
		} //keyTyped end
	} //MyListener Class end

	public static void main(String[] args) {

		new PAChecker("Price-Amount-Checker");
		
	}

}
