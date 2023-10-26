package editor;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

/*
 * Erstellt Aktionen, die direkt viele Werte übermitteln. Kann man für Toolbars verwenden (einfach JToolBar leiste .... und dann 
 * leiste.add(neuAct, oeffnenAct usw.) oder im Menü (menuDatei.add(neuAct, oeffnenAct, etc etc))
 * 
 * Examples
 * 
 * neuAct = new MeineAktionen("Neu...", iconNewFile, "Erstellt ein neues Dokument", KeyStroke.getKeyStroke('N',InputEvent.CTRL_DOWN_MASK), "neu");
 */

class MeineAktionen extends AbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3120496162380632772L;

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
//			dateiNeu();
		}
	}
}