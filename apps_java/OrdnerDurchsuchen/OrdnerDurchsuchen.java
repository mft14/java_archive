import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class OrdnerDurchsuchen {

	public static void main(String[] args) {
		
		/* 
		 * Karim Kiel
		 * 26/10/2020 wegen dem Video von 
		 * https://youtu.be/YdG2MQ5bahs
		 */
		
		//TODO Auch Unterordner anzeigen lassen, das fehlt noch
		
		String folder = "";

		JFileChooser openRep = new JFileChooser();
		openRep.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		//Dialog anzeigen und Status holen
		int status = openRep.showOpenDialog(null);
		
		if (status == JFileChooser.APPROVE_OPTION) { //Wenn File ausgesucht
			folder = openRep.getSelectedFile().toString(); //packe Pfad in String
			File rep = new File(folder); //Packe Pfad in eine File Klasse
		    File filesList[] = rep.listFiles(); //Da mehrere Zeilen, braucht's ein String
		    
		    System.out.println("List of files and directories in the specified directory:\n----------------------------\n");
		      for(File file : filesList) {
		         System.out.println(file.getAbsolutePath());
		      }
		}
	}
}
