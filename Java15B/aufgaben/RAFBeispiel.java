import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JOptionPane;

public class RAFBeispiel {

	public static void main(String[] args) {

		try {
			RandomAccessFile datei = new RandomAccessFile("test.txt", "rw");
			
			//datei.writeBytes("Write this in that bloody text file mate");
			for (int i = 0; i<20; i++) {
				datei.writeByte(i);
			}
			
			
			
			datei.close();
			System.out.println("File created without errors");
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Something's fishy here");
		} catch (IOException ee) {
			JOptionPane.showMessageDialog(null, "Something's fishy here");
		}
		
	}

}
