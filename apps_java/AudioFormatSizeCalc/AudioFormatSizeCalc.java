package audioformatsizecalc;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AudioFormatSizeCalc extends JFrame {

	/**
	 * Karim Kiel
	 * 21/10/2020
	 * Because I want to know the proper file sizes sometimes before render :D
	 */
	private static final long serialVersionUID = -5740360278883773641L;

	JButton btnCalc = new JButton("Calculate");
	JButton btnReset = new JButton("Reset");
	
	String[] times = {"Seconds", "Minutes"};
	JComboBox<String> cbTime = new JComboBox<String>(times);
	JTextField tfTime = new JTextField();
	
	JLabel lbl_mp3_320 = new JLabel("MP3 320kbps");
	JLabel lbl_mp3_192 = new JLabel("MP3 192kbps");
	JLabel lbl_mp3_128 = new JLabel("MP3 128kbps");
	JLabel lbl_wav = new JLabel("WAV");
	JLabel lbl_flac = new JLabel("FLAC");
	JLabel lbl_ogg = new JLabel("OGG");
	
	JTextField tf_mp3_320 = new JTextField();
	JTextField tf_mp3_192 = new JTextField();
	JTextField tf_mp3_128 = new JTextField();
	JTextField tf_wav = new JTextField();
	JTextField tf_flac = new JTextField();
	JTextField tf_ogg = new JTextField();
	
	JPanel mainPanel = new JPanel();
	
	class MeinListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("calc")) {
				
				try {
					double sec = Double.parseDouble(tfTime.getText());
					double mp3_320, mp3_192, mp3_128, wav, ogg, flac = 0;
					
					//Werte für eine Sekunde
					mp3_320 = 0.04003333;
					mp3_192 = 0.02401666;
					mp3_128 = 0.016; 
					wav = 0.2646;
					flac = 0.124383333333;
					ogg = 0.017566666666;

					if(cbTime.getSelectedIndex() == 0) { //seconds selected
						System.out.println("sec calc");
						tf_mp3_320.setText(String.valueOf(Math.round(mp3_320*sec)) + " Mb");
						tf_mp3_192.setText(String.valueOf(Math.round(mp3_192*sec)) + " Mb");
						tf_mp3_128.setText(String.valueOf(Math.round(mp3_128*sec)) + " Mb");
						
						tf_wav.setText(String.valueOf(Math.round(wav*sec)) + " Mb");
						tf_flac.setText(String.valueOf(Math.round(flac*sec)) + " Mb");
						tf_ogg.setText(String.valueOf(Math.round(ogg*sec)) + " Mb");
					}
					
					if(cbTime.getSelectedIndex() == 1) { //minutes selected
						System.out.println("min calc");
						tf_mp3_320.setText(String.valueOf(Math.round(mp3_320*sec*60)) + " Mb");
						tf_mp3_192.setText(String.valueOf(Math.round(mp3_192*sec*60)) + " Mb");
						tf_mp3_128.setText(String.valueOf(Math.round(mp3_128*sec*60)) + " Mb");
						
						tf_wav.setText(String.valueOf(Math.round(wav*sec*60)) + " Mb");
						tf_flac.setText(String.valueOf(Math.round(flac*sec*60)) + " Mb");
						tf_ogg.setText(String.valueOf(Math.round(ogg*sec*60)) + " Mb");
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Please enter a number!");
				}
				
			} else { //reset button
				tfTime.setText("");
				tf_mp3_320.setText("");
				tf_mp3_192.setText("");
				tf_mp3_128.setText("");
				tf_wav.setText("");
				tf_flac.setText("");
				tf_ogg.setText("");
			}
		}
	}
	
	public AudioFormatSizeCalc(String titel) {
		super(titel);
		
		setLayout(new GridLayout(1,0));
		mainPanel.setLayout(new GridLayout(0,2));

		//Listener
		MeinListener listener = new MeinListener();
		btnCalc.setActionCommand("calc");
		btnCalc.addActionListener(listener);
		btnReset.addActionListener(listener);
		
		mainPanel.add(cbTime);
		mainPanel.add(tfTime);
		
		mainPanel.add(lbl_mp3_320);
		mainPanel.add(tf_mp3_320);
		mainPanel.add(lbl_mp3_192);
		mainPanel.add(tf_mp3_192);
		mainPanel.add(lbl_mp3_128);
		mainPanel.add(tf_mp3_128);
		
		mainPanel.add(lbl_wav);
		mainPanel.add(tf_wav);
		mainPanel.add(lbl_flac);
		mainPanel.add(tf_flac);
		mainPanel.add(lbl_ogg);
		mainPanel.add(tf_ogg);
		
		mainPanel.add(btnCalc);
		mainPanel.add(btnReset);
		
		add(mainPanel);
		
		tf_mp3_320.setEditable(false);
		tf_mp3_192.setEditable(false);
		tf_mp3_128.setEditable(false);
		tf_wav.setEditable(false);
		tf_flac.setEditable(false);
		tf_ogg.setEditable(false);
		

		
		//Last window operations
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new AudioFormatSizeCalc("Audio Format Size Calculator");
	}

}
