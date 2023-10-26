import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Xml1 extends JFrame {

	//automatisch über Eclipse ergänzt
	private static final long serialVersionUID = 3645377409368775710L;
	//für das Eingabefeld
	private JTextArea feld;
	//für die Schaltflächen
	private JButton einlesen, beenden;
	
	//die innere Klasse für den ActionListener
	class MeinListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//wurde auf Lesen geklickt?
			if (e.getActionCommand().equals("lesen"))
				//dann die XML-Datei einlesen
				dateiLesen();
			
			//wurde auf Beenden geklickt?
			if (e.getActionCommand().equals("ende"))
				System.exit(0);
		}
	}

	//der Konstruktor
	public Xml1(String titel) {
		super(titel);
		//für das Panel mit den Schaltflächen 
		JPanel tempPanel;
		//ein neues Eingabefeld erstellen
		feld = new JTextArea();
		//die Schaltflächen
		einlesen = new JButton("Einlesen");
		einlesen.setActionCommand("lesen");
		beenden = new JButton("Beenden");
		beenden.setActionCommand("ende");
		
		MeinListener listener = new MeinListener();
		einlesen.addActionListener(listener);
		beenden.addActionListener(listener);
				
		//ein BorderLayout anwenden
		setLayout(new BorderLayout());
		//das Eingabefeld mit Scrollbars
		add(new JScrollPane(feld), BorderLayout.CENTER);
		//ein Panel für die Schaltflächen
		tempPanel = new JPanel();
		tempPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		tempPanel.add(einlesen);
		tempPanel.add(beenden);
		add(tempPanel,BorderLayout.SOUTH);
		
		//Größe setzen, Standard-Verhalten festlegen und anzeigen
		setMinimumSize(new Dimension(400,300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	//die Methode zum Lesen
	private void dateiLesen() {
		//für die Ereignisse
		int ereignis;
		//zum Lesen der XML-Datei
		//für den Stream
		InputStream eingabeStream;
		//die "Fabrik"
		XMLInputFactory xmlInput;
		//für den Parser
		XMLStreamReader xmlParserIn;
		
		//die Ausnahmebehandlung ist zwingend erforderlich
		try {
			//einen Eingabestream für die Datei erzeugen
			//die Datei muss im Projektordner liegen
			eingabeStream = new FileInputStream( "test.xml" );
			//eine neue Instanz für die Factory
			xmlInput = XMLInputFactory.newInstance();
			//für den Parser einen StreamReader erzeugen
			xmlParserIn = xmlInput.createXMLStreamReader(eingabeStream);
			//solange es noch weitere Elemente gibt
			while (xmlParserIn.hasNext()) {
				//das nächste Ereignis holen und verarbeiten
		        ereignis = xmlParserIn.next();
		        switch (ereignis)  {
		        	//das Ende
		        	case XMLStreamConstants.END_DOCUMENT:
		        		//schließen
		        		xmlParserIn.close();
		        	break;
		            //der Start eines Elements
		        	case XMLStreamConstants.START_ELEMENT:
		        		feld.setText(feld.getText() + "Start: " + xmlParserIn.getLocalName() + '\n');
		            break;
		            //die Zeichen
		        	case XMLStreamConstants.CHARACTERS:
		        		//wenn es keine Sonderzeichen sind
		        		if (xmlParserIn.isWhiteSpace() == false)
		        			feld.setText(feld.getText() +  xmlParserIn.getText() + '\n');
		        	break;
		            //das Ende eines Elements
		        	case XMLStreamConstants.END_ELEMENT:
		        		feld.setText(feld.getText() + "Ende: " + xmlParserIn.getLocalName() + '\n');
		            break;
		        }
		    }
		}
		catch (IOException e ) {
			JOptionPane.showMessageDialog(this, "Beim Laden ist ein Problem aufgetreten");
	    }
	    catch (XMLStreamException e ) {
	    	JOptionPane.showMessageDialog(this, "Beim Verarbeiten ist ein Problem aufgetreten");
	    }
	}
	
	public static void main(String[] args) {
		new Xml1("Der erste XML-Test");
		}
	
}
