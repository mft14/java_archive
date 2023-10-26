import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TextSpielerreiGUI extends JFrame {
	
	//Buttons und Labels erstellen
	private JLabel lblAusgabe = new JLabel("", JLabel.CENTER);
	private JLabel lblGroessenIndikator = new JLabel("", JLabel.CENTER);
	private JButton btnGross = new JButton("Gr��er");
	private	JButton btnKlein = new JButton("Kleiner");
	private int schriftGroesse = 10;

	public TextSpielerreiGUI(String title) {
		super(title);
		
		//GridLayout mit zwei Zeilen und Spalten
		GridLayout gLayout = new GridLayout(2,2); 
		this.setLayout(gLayout); //Layout setzen
		
		setSize(600, 200); //Fenstergr��e festlegen

		//Buttons und Labels hinzuf�gen
		btnGross.setActionCommand("gross");
		btnKlein.setActionCommand("klein");
	
		//Hier wird der Schriftgr��enindikator beim Starten gesetzt und nimmt sich den Wert unserer
		//Variable schriftGroesse
		lblGroessenIndikator.setText("Aktuelle Schriftgr��e " + Integer.toString(schriftGroesse));
		
		//die adaptive Listener Klasse
		MyCompactListener listener = new MyCompactListener();
		addWindowListener(listener);
		
		//Buttons/Labels hinzuf�gen
		add(btnKlein);
		add(btnGross);
		add(lblGroessenIndikator);
		add(lblAusgabe);
		
		//die ActionListener f�r die Buttons hinzuf�gen
		btnGross.addActionListener(listener);
		btnKlein.addActionListener(listener);
		
		//Abschlie�ende Fenstereigenschaften
		this.setVisible(true);
		this.setLocationRelativeTo(null); //kurze Variante um Fenster in die Mitte zu setzen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class MyCompactListener extends WindowAdapter implements ActionListener {
		
		@Override
		public void windowOpened(WindowEvent e) { //Beim Starten ein Text eingeben als Anzeige
			String eingabe = "";
			eingabe = JOptionPane.showInputDialog("Geben Sie einen Text ein:");
			lblAusgabe.setText(eingabe);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("gross") ) { //F�ngt den Button ab und vergr��ert Schrift
				schriftGroesse++; 
			}
			if(e.getActionCommand().equals("klein") ) { //F�ngt den Button ab und verkleinert Schrift
				schriftGroesse--;
			}
			//Die Schrift nach jedem Klick neu definieren
			lblAusgabe.setFont(new Font("Arial", Font.PLAIN, schriftGroesse)); 
			//Bei jedem Klicken muss das Label f�r die Schriftgr��e immer aktualisiert werden
			lblGroessenIndikator.setText("Aktuelle Schriftgr��e " + Integer.toString(lblAusgabe.getFont().getSize()));
		}
	}
	
	public static void main(String[] args) {
		//Fenster erstellen
		TextSpielerreiGUI t = new TextSpielerreiGUI("Java9B Aufgabe 1 - Aktuelle Textgr��e wiedergeben");
	}
}
