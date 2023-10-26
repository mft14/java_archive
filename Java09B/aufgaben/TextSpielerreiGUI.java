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
	private JButton btnGross = new JButton("Größer");
	private	JButton btnKlein = new JButton("Kleiner");
	private int schriftGroesse = 10;

	public TextSpielerreiGUI(String title) {
		super(title);
		
		//GridLayout mit zwei Zeilen und Spalten
		GridLayout gLayout = new GridLayout(2,2); 
		this.setLayout(gLayout); //Layout setzen
		
		setSize(600, 200); //Fenstergröße festlegen

		//Buttons und Labels hinzufügen
		btnGross.setActionCommand("gross");
		btnKlein.setActionCommand("klein");
	
		//Hier wird der Schriftgrößenindikator beim Starten gesetzt und nimmt sich den Wert unserer
		//Variable schriftGroesse
		lblGroessenIndikator.setText("Aktuelle Schriftgröße " + Integer.toString(schriftGroesse));
		
		//die adaptive Listener Klasse
		MyCompactListener listener = new MyCompactListener();
		addWindowListener(listener);
		
		//Buttons/Labels hinzufügen
		add(btnKlein);
		add(btnGross);
		add(lblGroessenIndikator);
		add(lblAusgabe);
		
		//die ActionListener für die Buttons hinzufügen
		btnGross.addActionListener(listener);
		btnKlein.addActionListener(listener);
		
		//Abschließende Fenstereigenschaften
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
			if(e.getActionCommand().equals("gross") ) { //Fängt den Button ab und vergrößert Schrift
				schriftGroesse++; 
			}
			if(e.getActionCommand().equals("klein") ) { //Fängt den Button ab und verkleinert Schrift
				schriftGroesse--;
			}
			//Die Schrift nach jedem Klick neu definieren
			lblAusgabe.setFont(new Font("Arial", Font.PLAIN, schriftGroesse)); 
			//Bei jedem Klicken muss das Label für die Schriftgröße immer aktualisiert werden
			lblGroessenIndikator.setText("Aktuelle Schriftgröße " + Integer.toString(lblAusgabe.getFont().getSize()));
		}
	}
	
	public static void main(String[] args) {
		//Fenster erstellen
		TextSpielerreiGUI t = new TextSpielerreiGUI("Java9B Aufgabe 1 - Aktuelle Textgröße wiedergeben");
	}
}
