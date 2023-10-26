import javax.swing.JOptionPane;

public class EigeneAusnahmen {
	
	

	public static void main(String[] args) {

		String nachricht = "";
		Eingabedialog eingabe = new Eingabedialog();
		
		nachricht = eingabe.eingeben("Bitte eingeben");
		System.out.println(nachricht);
		//nachricht = JOptionPane.showInputDialog("Bitte eingeben");
		
		
	}

}
