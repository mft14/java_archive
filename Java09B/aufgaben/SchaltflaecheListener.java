import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchaltflaecheListener implements ActionListener {

	TextTauscherGUI referenz;
	
	public SchaltflaecheListener(TextTauscherGUI referenz) {
		this.referenz = referenz;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("exit".equals(e.getActionCommand())) {
			System.exit(0);
		} else if ("tauschen".equals(e.getActionCommand())) {
			referenz.texteTauschen();
		}
	}
}
