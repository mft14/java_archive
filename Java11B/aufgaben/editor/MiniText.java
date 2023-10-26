package editor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLEditorKit;

public class MiniText extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7648753304321869274L;
	private JEditorPane eingabeFeld;
	private HTMLEditorKit htmlFormat;
	
	JMenuBar menuBar = new JMenuBar();  						// Menüleiste
	JMenu menuDatei = new JMenu("Datei"); 						// Menükategorie
	JMenuItem itemDateiNeu = new JMenuItem("Neu"); 				// Menüaktion
	JMenu menuOeffnen = new JMenu("Öffnen"); 						// Menükategorie
	JMenuItem itemDateiOeffnen = new JMenuItem("Öffnen"); 		// Menüaktion
	JMenuItem itemDateiSpeichern = new JMenuItem("Speichern"); 	// Menüaktion
	JMenuItem itemDateiDrucken = new JMenuItem("Drucken"); 		// Menüaktion
	JMenuItem itemDateiBeenden = new JMenuItem("Beenden"); 		// Menüaktion
	
	JPopupMenu kontext;
	
	ImageIcon iconNewFile = new ImageIcon(getClass().getResource("/icons/newFile.gif"));
	ImageIcon iconOpenFile = new ImageIcon(getClass().getResource("/icons/openFile.gif"));
	ImageIcon iconSaveFile = new ImageIcon(getClass().getResource("/icons/saveFile.gif"));
	ImageIcon iconSaveAsFile = new ImageIcon(getClass().getResource("/icons/saveAsFile.png"));
	ImageIcon iconCloseWindow = new ImageIcon(getClass().getResource("/icons/close.gif"));
	
	ImageIcon iconPrint = new ImageIcon(getClass().getResource("/icons/imprimante-drucker-printer.png"));
	ImageIcon iconWeb = new ImageIcon(getClass().getResource("/icons/terre-erde.png"));
	
	ImageIcon iconBold = new ImageIcon(getClass().getResource("/icons/bold.png"));
	ImageIcon iconItalic = new ImageIcon(getClass().getResource("/icons/italic.png"));
	ImageIcon iconUnderlined = new ImageIcon(getClass().getResource("/icons/underlined.png"));
	
	ImageIcon iconAlignLeft = new ImageIcon(getClass().getResource("/icons/alignment_left.png"));
	ImageIcon iconAlignCenter = new ImageIcon(getClass().getResource("/icons/alignment_center.png"));
	ImageIcon iconAlignRight = new ImageIcon(getClass().getResource("/icons/alignment_right.png"));
	ImageIcon iconAlignJustify = new ImageIcon(getClass().getResource("/icons/alignment_justify.png"));
	
	File datei;
	String url = "";
	
	private MeineAktionen 	oeffnenAct, speichernAct, beendenAct, neuAct,
	druckenAct, speichernUnterAct, webOeffnenAct;
	
	//KONSTRUKTOR
	public MiniText(String titel) {
		super(titel);

		//die Aktionen erstellen
		neuAct = new MeineAktionen("Neu...", iconNewFile, "Erstellt ein neues Dokument", KeyStroke.getKeyStroke('N',InputEvent.CTRL_DOWN_MASK), "neu");
		oeffnenAct = new MeineAktionen("Öffnen...", iconOpenFile, "Öffnet ein vorhandenes Dokument", KeyStroke.getKeyStroke('O',InputEvent.CTRL_DOWN_MASK),"laden");
		speichernAct = new MeineAktionen("Speichern", iconSaveFile, "Speichert das aktuelle Dokument", KeyStroke.getKeyStroke('S',InputEvent.CTRL_DOWN_MASK), "speichern");
		speichernUnterAct = new MeineAktionen("Speichern Unter", iconSaveAsFile, "Speichert das aktuelle Dokument unter...", KeyStroke.getKeyStroke('S',InputEvent.SHIFT_DOWN_MASK), "speichern");
		beendenAct = new MeineAktionen("Beenden", iconCloseWindow, "Beenden", null, "beenden");
		druckenAct = new MeineAktionen("Drucken", iconPrint, "Drucken Sie das Dokument", null, "drucken");
		webOeffnenAct = new MeineAktionen("Webseite", iconWeb, "Eine Webseite öffnen", null, "webladen");
		
		setLayout(new BorderLayout());
		
		// Menü
		menuErstellen();

		//Dem Eingabefeld ein HTMLEditorKit geben
		eingabeFeld = new JEditorPane();
		htmlFormat = new HTMLEditorKit();
		eingabeFeld.setEditorKit(htmlFormat);
		
		add(new JScrollPane(eingabeFeld), BorderLayout.CENTER);
		add(symbolleiste(), BorderLayout.NORTH);
		kontextMenu(); //Kontextmenü erstellen
		
		eingabeFeld.addMouseListener(new MeinKontextMenuListener());
		
		//Letzte Einstellungen
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(600,200));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		eingabeFeld.requestFocus();
	}
	
	
	class MeineAktionen extends AbstractAction {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -2396529495476757777L;

		public MeineAktionen(String text, ImageIcon icon, String bildschirmtipp,
				KeyStroke shortcut, String actionText) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, bildschirmtipp);
			putValue(ACCELERATOR_KEY, shortcut);
			putValue(ACTION_COMMAND_KEY, actionText);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("neu")) {
				dateiNeu();
			}
			if(e.getActionCommand().equals("laden")) {
				dateiLaden();
			}
			if(e.getActionCommand().equals("speichern")) {
				dateiSpeichern();
			}
			if(e.getActionCommand().equals("beenden")) {
				beenden();
			}
			if(e.getActionCommand().equals("webladen")) {
				webLaden();
			}
			if(e.getActionCommand().equals("drucken")) {
				if(e.getSource() instanceof JButton) {
					drucken(false);
				} else {
					drucken(true);
				}
				
			}
		}

	}
	
	class MeinKontextMenuListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			if(e.isPopupTrigger()) {
				kontext.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	
	private JToolBar symbolleiste() {
		JToolBar leiste = new JToolBar();
		
		leiste.add(neuAct);
		leiste.add(oeffnenAct);
		leiste.add(speichernAct);
		leiste.add(speichernUnterAct);
		leiste.add(beendenAct);
		leiste.addSeparator();
		leiste.add(druckenAct);
		leiste.add(webOeffnenAct);
		leiste.addSeparator();

		Action fettFormat = new StyledEditorKit.BoldAction();
		fettFormat.putValue(Action.SHORT_DESCRIPTION, "Fett formatieren");
		fettFormat.putValue(Action.LARGE_ICON_KEY, iconBold);
		leiste.add(fettFormat);
		
		Action italicFormat = new StyledEditorKit.ItalicAction();
		italicFormat.putValue(Action.SHORT_DESCRIPTION, "Kursiv formatieren");
		italicFormat.putValue(Action.LARGE_ICON_KEY, iconItalic);
		leiste.add(italicFormat);
		
		Action underlinedFormat = new StyledEditorKit.UnderlineAction();
		underlinedFormat.putValue(Action.SHORT_DESCRIPTION, "Unterstrichen formatieren");
		underlinedFormat.putValue(Action.LARGE_ICON_KEY, iconUnderlined);
		leiste.add(underlinedFormat);
		
		leiste.addSeparator();
		
		Action alignLeftFormat = new StyledEditorKit.AlignmentAction("Linksbündig", StyleConstants.ALIGN_LEFT);
		alignLeftFormat.putValue(Action.SHORT_DESCRIPTION, "Linksbündig ausrichten");
		alignLeftFormat.putValue(Action.LARGE_ICON_KEY, iconAlignLeft);
		leiste.add(alignLeftFormat);
		
		Action alignCenterFormat = new StyledEditorKit.AlignmentAction("Mittig", StyleConstants.ALIGN_CENTER);
		alignCenterFormat.putValue(Action.SHORT_DESCRIPTION, "Mittig ausrichten");
		alignCenterFormat.putValue(Action.LARGE_ICON_KEY, iconAlignCenter);
		leiste.add(alignCenterFormat);
		
		Action alignRightFormat = new StyledEditorKit.AlignmentAction("Rechtsbündig", StyleConstants.ALIGN_RIGHT);
		alignRightFormat.putValue(Action.SHORT_DESCRIPTION, "Rechtsbündig ausrichten");
		alignRightFormat.putValue(Action.LARGE_ICON_KEY, iconAlignRight);
		leiste.add(alignRightFormat);
		
		Action alignJustifyFormat = new StyledEditorKit.AlignmentAction("Blocksatz", StyleConstants.ALIGN_JUSTIFIED);
		alignJustifyFormat.putValue(Action.SHORT_DESCRIPTION, "Blocksatz");
		alignJustifyFormat.putValue(Action.LARGE_ICON_KEY, iconAlignJustify);
		leiste.add(alignJustifyFormat);
		
		return leiste;
	}
	
	private void dateiNeu() {
		
		if((JOptionPane.showConfirmDialog(this, "Wollen Sie wirklich ein neues Dokument erstellen?", "Neue Datei", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {
			eingabeFeld.setText("");
			eingabeFeld.setForeground(Color.WHITE);
			datei = null;
		}
	}
	
	public void dateiLaden() {
		MiniTextDialoge mtdialoge = new MiniTextDialoge();
		File dateiLokal = mtdialoge.oeffnenDialogZeigen();
		
		if(dateiLokal != null) {
			try {
				eingabeFeld.read(new FileReader(dateiLokal), null);
				datei = dateiLokal;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Beim Laden hat es ein Fehler gegeben", "Fehler", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void dateiSpeichern() {
		
		if(datei == null) { //Beim ersten Speichern
			MiniTextDialoge mtdialoge = new MiniTextDialoge();
			datei = mtdialoge.speichernDialogZeigen();
		}
		
		if(datei != null) { //Sonst einfach überschreiben
			try {
				OutputStream output = new FileOutputStream(datei);
				htmlFormat.write(output, eingabeFeld.getDocument(), 0, eingabeFeld.getDocument().getLength());
				
			} catch (IOException | BadLocationException e) {
				JOptionPane.showMessageDialog(this, "Beim Speichern hat es ein Fehler gegeben", "Fehler", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	public void dateiSpeichernUnter() {
		MiniTextDialoge mtdialoge = new MiniTextDialoge();
		File dateiLokal = mtdialoge.speichernDialogZeigen();
		if (dateiLokal != null) {
			datei = dateiLokal;
			dateiSpeichern();
		}
	}
	
	public void webLaden() {
		
		//Wenn Adresse leer, zeige Standardtext, sonst nimm das zuvor eingetippte
		if(url.equals("")) {
			url = JOptionPane.showInputDialog(this, "Webseiten URL eintragen", "Webseiten Laden", JOptionPane.QUESTION_MESSAGE);
		} else {
			url = (String)JOptionPane.showInputDialog(this, "Webseiten URL eintragen", "Webseiten Laden", JOptionPane.QUESTION_MESSAGE, null, null, url);
		}
		
		if (url != null) {
			eingabeFeld.setText("");
			try {
				eingabeFeld.setPage(url);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Beim Laden ist ein Problem aufgetreten.");
			}
		}
	}
	
	public void drucken(boolean dialogAnzeigen) {

		try {
			if(dialogAnzeigen == true) {
				eingabeFeld.print();
			} else {
				eingabeFeld.print(null, null, false, null, null, true);
			}
		} catch (PrinterException e) {
			System.out.println("Fehler beim Drucken");
			JOptionPane.showMessageDialog(null, "Es gab einen Fehler beim Drucken");
		}
		
	}
	
	public void beenden() {
		System.exit(0);
	}
	
	public void menuErstellen() {
		this.setJMenuBar(menuBar);
		
		menuBar.add(menuDatei);
		menuDatei.add(neuAct);
		
		menuDatei.add(menuOeffnen);
		menuOeffnen.add(oeffnenAct);
		menuOeffnen.add(speichernAct);
		
		menuDatei.add(speichernUnterAct);
		menuDatei.addSeparator();
		menuDatei.add(druckenAct);
		menuDatei.add(webOeffnenAct);
		menuDatei.addSeparator();
		menuDatei.add(beendenAct);
	}
	
	private void kontextMenu() {
		//kontext ist eine Instanzvariable vom Typ JPopupMenu
		kontext = new JPopupMenu();
		//den Eintrag Neu hinzufügen
		kontext.add(neuAct);
		//die Einträge zum Öffnen
		kontext.add(oeffnenAct);
		kontext.add(webOeffnenAct);
	}

	public static void main(String[] args) {
		new MiniText("Minitext Editor");
	}
	
}