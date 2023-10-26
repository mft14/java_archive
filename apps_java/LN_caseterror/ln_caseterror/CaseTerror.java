package ln_caseterror;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class CaseTerror extends JFrame {
	
	/**
	 * Karim Kiel
	 * created 10/05/2020
	 * 
	 */
	private static final long serialVersionUID = -2058801785297285681L;
	
	JLabel lblTitle = new JLabel("Casing Around");
	JLabel lblIcon = new JLabel();
	JLabel lblInput = new JLabel("Input");
	JLabel lblOutput = new JLabel("Output");
	JButton btnConvert = new JButton("Convert");
	JButton btnCopy = new JButton("Copy Text");
	JButton btnReset = new JButton("Reset");
	
	JTextArea taInput = new JTextArea();
	JTextArea taOutput = new JTextArea();
	
	JScrollPane spInput = new JScrollPane(taInput);
	JScrollPane spOutput = new JScrollPane(taOutput);
	
	JPanel panelTitle = new JPanel();
	JPanel panelIO = new JPanel();
	JPanel panelButtons = new JPanel();
	
	String[] selection = {"Upper-Lower --> ExAmPlE", "Double-Upper-Lower --> eExXaAmMpPlLeE",
			"Upper Case --> EXAMPLE", "Lower Case --> example", "Gaps between letters --> E x a m p l e "};
	
	JComboBox <String> cbSelection = new JComboBox <String>(selection);
	
	ButtonGroup bgSelectCT = new ButtonGroup();
	JRadioButton radioCT = new JRadioButton("Upper Lower Case");
	JRadioButton radioCTDouble = new JRadioButton("Double Upper Lower Case");
	JRadioButton radioCTUpper = new JRadioButton("Upper  Case");
	JRadioButton radioCTLower = new JRadioButton("Lower Case");
	
	boolean wasClicked = false;
	
	//Save the picture
	Image icon = new ImageIcon(this.getClass().getResource("/img/mf_caseterror.png")).getImage();
	private Font myFont = new Font("Arial", Font.BOLD, 16); //Font settings
	
	public CaseTerror(String titel) {
		super(titel);
		this.setIconImage(icon);
		
		bgSelectCT.add(radioCT); //ButtonGroup 
		bgSelectCT.add(radioCTDouble);
		bgSelectCT.add(radioCTUpper);
		bgSelectCT.add(radioCTLower);
		
		setLayout(new BorderLayout());
		
		//Fonts
		lblTitle.setFont(myFont);
		lblInput.setFont(myFont);
		lblOutput.setFont(myFont);
		taInput.setFont(myFont);
		taOutput.setFont(myFont);
		btnConvert.setFont(myFont);
		btnCopy.setFont(myFont);
		btnReset.setFont(myFont);
		
		//Adding Time
		
		//------------ Top Panel
		this.add(panelTitle, BorderLayout.NORTH);
		panelTitle.setBorder(new EmptyBorder(20, 20, 20, 20));
//		panelTitle.setLayout(new GridLayout(0,2));
		panelTitle.add(new JLabel(new ImageIcon(icon))); //icon Bild
		
		panelTitle.add(lblTitle);
		//create gap between icon and combobox using Padding-like border
		lblTitle.setBorder(new EmptyBorder(0, 20, 0, 20));
		
		panelTitle.add(cbSelection);
		
		//first disabled, enable when click on textarea first time
		cbSelection.setPrototypeDisplayValue("text here that makes this whole thing wide and so");
		
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		radioCT.setSelected(true);
		
		//------------ Center Panel
		this.add(panelIO,BorderLayout.CENTER);
		panelIO.setLayout(new GridLayout(0,1,0,22));
		lblInput.setHorizontalAlignment(JLabel.CENTER);
		panelIO.setBorder(new TitledBorder("Input Outputs"));
		
		panelIO.add(spInput); //TextAreas are in the "ScrollPanes"
		panelIO.add(spOutput);
		
		//This is for line breaking
		taInput.setLineWrap(true);
		taInput.setWrapStyleWord(true);
		taOutput.setLineWrap(true);
		taOutput.setWrapStyleWord(true);
		
		//Actually not needed but letting it in? Ka, was das genau macht xD
		taInput.setAutoscrolls(true);
		taOutput.setAutoscrolls(true);
		
		taInput.setText("Enter your text here...");
		taOutput.setText("Output comes here");
		taOutput.setEditable(false); //No writing in the output Textarea
		
		//------------ South Panel
		
		this.add(panelButtons,BorderLayout.SOUTH);
		panelButtons.setLayout(new FlowLayout());
		panelButtons.add(btnConvert);
		panelButtons.add(btnCopy);
		panelButtons.add(btnReset);
		
		//Listeners
		MyListener listener = new MyListener();
		btnConvert.addActionListener(listener); //ActionListener
		btnCopy.addActionListener(listener);
		btnReset.addActionListener(listener);
		
		cbSelection.addItemListener(listener); //ItemListener
		
		taInput.addMouseListener(listener); //MouseListener
		
		//Action Commands
		btnConvert.setActionCommand("convert");
		btnCopy.setActionCommand("copy");
		btnReset.setActionCommand("reset");
		
		setMinimumSize(new Dimension(400,700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	class MyListener implements ActionListener, ItemListener, MouseListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("convert")) {
				
				String txtInput = taInput.getText(); //save Text to a string
				
				if(txtInput.trim().isEmpty()) { //if Textarea is empty, do warning
					JOptionPane.showMessageDialog(null, "Please enter a text!");
				} else { //if not empty, GO ON
					
					if(cbSelection.getSelectedIndex() == 0) { //Upper Lower Case
						createUpperLowerCase(false);
					}
					if(cbSelection.getSelectedIndex() == 1) { //Upper Lower Double Case 
						createUpperLowerCase(true);
					}
					if(cbSelection.getSelectedIndex() == 2) { //only Uppercase
						createUpperCaseOnly();
					}
					if(cbSelection.getSelectedIndex() == 3) { //only Lowercase
						createLowerCaseOnly();
					}
					if(cbSelection.getSelectedIndex() == 4) { //Gaps
						createUpperCaseWithGaps();
					}
				}
				
			} //convert Button Action END
			
			if(e.getActionCommand().equals("copy")) {
				
				//Copy to clipboard
				StringSelection stringSelection = new StringSelection (taOutput.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
				clpbrd.setContents (stringSelection, null);
				System.out.println("Copied text"); 
				
			} //copy Button Action end
			
			if(e.getActionCommand().equals("reset")) {
				taInput.setText("");
				taOutput.setText("");
			} //convert Button Action End
			
		} //ActionPerformed end

		@Override
		public void itemStateChanged(ItemEvent e) {

			Object trigger = e.getSource();
			
			if(trigger instanceof JComboBox) {
				//If input text is empty, do nothing yet
				if(taInput.getText().trim().isEmpty()) { 
					
				} else { //otherwise GO
					
					if(cbSelection.getSelectedIndex() == 0) {
						createUpperLowerCase(false);
					}
					if(cbSelection.getSelectedIndex() == 1) {
						createUpperLowerCase(true);
					}
					if(cbSelection.getSelectedIndex() == 2) {
						createUpperCaseOnly();
					}
					if(cbSelection.getSelectedIndex() == 3) {
						createLowerCaseOnly();
					}
					if(cbSelection.getSelectedIndex() == 4) {
						createUpperCaseWithGaps();
					}
					
				} //check if empty test
			} //instanceof end
		} //itemstatechanged end

		@Override
		public void mouseClicked(MouseEvent e) {
			Object trigger = e.getSource();
			if(trigger instanceof JTextArea) {

				if(wasClicked == false) { //removes the first text one time
					taInput.setText("");
					taOutput.setText("");
					//enable ComboBox
					cbSelection.setEnabled(true);
					
					wasClicked = true; //ths bool makes this action just 1x
				}
				
			}
		}
		@Override		public void mouseEntered(MouseEvent arg0) {		}
		@Override		public void mouseExited(MouseEvent arg0) {		}
		@Override		public void mousePressed(MouseEvent arg0) {		}
		@Override		public void mouseReleased(MouseEvent arg0) {		}
		
	} //Constructor End
	
	private void createUpperLowerCase(boolean makeDouble) {
		String txtInput = taInput.getText(); //save Text to a string
		
		if(txtInput.trim().isEmpty()) { //if Textarea is empty, do warning
			JOptionPane.showMessageDialog(null, "Please enter a text!");
		} else { //if not empty, GO ON
			String lastLetter = ""; //Capturing the last letter cuz needed
			lastLetter = lastLetter + txtInput.charAt(txtInput.length()-1); //-1 otherwise outofbounce
			String txtOutput = ""; //storing the result here

				for (int i = 0; i < txtInput.length()-1; i++) {
					txtOutput = txtOutput + txtInput.toUpperCase().charAt(i);
					txtOutput = txtOutput + txtInput.toLowerCase().charAt(i+1);
					if(!makeDouble) {
						i++; //THIS is the key line for doubling!
					}
				} //for end
				//Grade zahlen nimmt er nicht an, iwie so
				if(txtInput.length() % 2 == 0) {
				} else {
					txtOutput = txtOutput + lastLetter.toUpperCase();
				}
				taOutput.setText(txtOutput);
		}
	} // UpperLowerCase void ends here
	
	private void createUpperCaseOnly() {
		taOutput.setText(taInput.getText().toUpperCase());
	}
	private void createLowerCaseOnly() {
		taOutput.setText(taInput.getText().toLowerCase());
	}
	private void createUpperCaseWithGaps() {
		String txtInput = taInput.getText(); //save Text to a string
		String txtOutput = "";
		//txtInput.toUpperCase();
		
		for (int i = 0; i < txtInput.length(); i++) {
			txtOutput = txtOutput + txtInput.charAt(i);
			txtOutput = txtOutput + " ";
		} //for end
				
		taOutput.setText(txtOutput);
	}
	
	public static void main(String[] args) {
		new CaseTerror("Casing Around");
		
	}

}
