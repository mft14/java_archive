/*
 * Karim Kiel
 * Aufgabe 3 in Java10
 * Layouts wechseln per Knopfdruck
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class LayoutWechselnGUI_Einsend extends JFrame {
	
	//ein Panel für die Optionsfelder und die Layoutveränderung
	private JPanel panelLayouts = new JPanel();
	
	//JButtons zu Showzwecken
	private JButton button1 = new JButton("Button 1");
	private JButton button2 = new JButton("Button 2");
	private JButton button3 = new JButton("Button 3");
	private JButton button4 = new JButton("Button 4");
	
	//RadioButtons für das Ändern hier erstellen + ButtonGroup
	private JRadioButton radioLayout1 = new JRadioButton("Layout 1 - FlowLayout");
	private JRadioButton radioLayout2 = new JRadioButton("Layout 2 - GridLayout");
	private JRadioButton radioLayout3 = new JRadioButton("Layout 3 - BorderLayout");
	private ButtonGroup layoutAendernButtonGroup = new ButtonGroup();
	
	public LayoutWechselnGUI_Einsend(String titel) {
		super(titel);
		
		//Das außenliegende Layout
		setLayout(new GridLayout(2,0,0,20));

		//RadioButtons zur ButtonGroup hinzufügen
		layoutAendernButtonGroup.add(radioLayout1);
		layoutAendernButtonGroup.add(radioLayout2);
		layoutAendernButtonGroup.add(radioLayout3);
		
		/*
		 * Panels hinzufügen. Das Panel mit den RadioButtons kommt in eine Methode mit 
		 * Rückgabetyp Panel, weil dieser nicht verändert wird. Das Panel für die Layouts 
		 * hingegen ist ein Panel, worin ich den Inhalt über void Methoden erstelle. 
		 * Mehr dazu weiter unten im Code.
		 */
		add(panelAuswahlErzeugen()); //Panel für RadioButtons
		add(panelLayouts); //Panels mit dem Layoutveränderungen
		
		//Listener für die RadioButtons
		MeinListener listener = new MeinListener();
		radioLayout1.addItemListener(listener);
		radioLayout2.addItemListener(listener);
		radioLayout3.addItemListener(listener);
		
		//RadioButton 1 soll standardmäßig ausgewählt werden, so kann man sich Code im
		//Konstruktor sparen
		radioLayout1.setSelected(true);
		
		//Restliche Fenstereinstellungen
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class MeinListener implements ItemListener {

		//Sofortiges Ändern beim Auswählen von RadioButtons
		//mithilfe von ItemListener, getSource() und instanceof
		@Override
		public void itemStateChanged(ItemEvent e) {
			Object trigger = e.getSource();
			
			if(trigger instanceof JRadioButton) {
				
				if(radioLayout1.isSelected()) {
					erstelleFlowLayout(); //Wähle FlowLayout aus
				}
				if(radioLayout2.isSelected()) {
					erstelleGridLayout(); //Wähle GridLayout aus
				}
				if(radioLayout3.isSelected()) {
					erstelleBorderLayout(); //Wähle BorderLayout aus
				}
			}
		}
	}
	
	/*
	 * Bei jeder Änderung des Layouts muss zunächst einmal der Inhalt des Panels mit der
	 * Methode "removeAll()" gelöscht werden, weil sich ohne diese die Buttons und Layouts
	 * mit den dadrunterliegenden praktisch überlagern würden und zum Teil man nicht mehr
	 * auf die Buttons klicken kann. Sobald Buttons und Layouts neu hinzugefügt wurden, muss 
	 * am Ende immer die Methode revalidate() des Panels aufgerufen werden. Diese
	 * sorgt quasi dafür, dem Layout Manager zu sagen, dass Veränderungen stattgefunden
	 * haben und er die Einstellungen resettet und die Komponenten "neumalen" kann.
	 */
	
	private void erstelleFlowLayout() {
		panelLayouts.removeAll(); //Komponenten des Panels entfernen
		panelLayouts.setBorder(new TitledBorder("FlowLayout")); //Bordertitel anpassen
		panelLayouts.setLayout(new FlowLayout()); //Neues Layout setzen
		buttonsErzeugen(); //buttons erzeugen
		panelLayouts.validate(); //Einstellungen resetten und neumalen
	}
	private void erstelleGridLayout() { //Selbes Spiel, nur ein 2x2 GridLayout
		panelLayouts.removeAll();
		panelLayouts.setBorder(new TitledBorder("GridLayout"));
		panelLayouts.setLayout(new GridLayout(2,2,10,10));
		buttonsErzeugen();
		panelLayouts.validate();
	}
	private void erstelleBorderLayout() {//Selbes Spiel, nur ein BorderLayout
		panelLayouts.removeAll(); 
		panelLayouts.setBorder(new TitledBorder("BorderLayout"));
		panelLayouts.setLayout(new BorderLayout());
		buttonsErzeugen();
		panelLayouts.validate();
	}
	
	private void buttonsErzeugen() {
		//Wenn im getLayout() der "BorderLayout" als String wiedergefunden 
		//wird, müssen die Buttons noch richtig hingesetzt werden
		if (getLayout().toString().contains("BorderLayout")) { //suche "BorderLayout" bei getLayout()
			panelLayouts.add(button1,BorderLayout.NORTH);
			panelLayouts.add(button2,BorderLayout.WEST);
			panelLayouts.add(button3,BorderLayout.EAST);
			panelLayouts.add(button4,BorderLayout.SOUTH);
		} else {//Ansonsten ganz normal Buttons hinzufügen
			panelLayouts.add(button1);
			panelLayouts.add(button2);
			panelLayouts.add(button3);
			panelLayouts.add(button4);
		}
	}
	
	//Panel der RadioButtons als Methode mit Rückgabetyp JPanel
	private JPanel panelAuswahlErzeugen() {
		//JPanel temporär erstellen bzw. ablegen
		JPanel tempPanel = new JPanel();
		
		//Bordertitel setzen
		tempPanel.setBorder(new TitledBorder("Layout ändern"));
		tempPanel.setLayout(new GridLayout(3,0,10,10)); //Passendes Layout nehmen, hier GridLayout
		tempPanel.add(radioLayout1); //RadioButtons zum Panel hinzufügen
		tempPanel.add(radioLayout2);
		tempPanel.add(radioLayout3);
		
		return tempPanel; //Wiedergeben mit return
	}

	public static void main(String[] args) {
		//Fenster erstellen und los geht's!
		new LayoutWechselnGUI_Einsend("Layout wechseln auf Knopfdruck");
	}

}
