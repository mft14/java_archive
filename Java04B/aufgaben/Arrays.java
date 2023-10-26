import javax.swing.JOptionPane;


public class Arrays {

	public static void main(String[] args) {

		int[] noten = new int[6];
		double durchschnitt = 0;
		
		for (int i = 0; i < noten.length; i++) {
			noten[i] = Integer.parseInt(JOptionPane.showInputDialog("Note " + (i+1) + " eingeben"));
		}
		for (int i = 0; i < noten.length; i++) {
			durchschnitt += noten[i];
		}
		durchschnitt = (durchschnitt / noten.length);
		
		System.out.println("Der Durchschnitt beträgt " + durchschnitt);
		
		System.out.println("Alle Noten: ");
		
		for (int element : noten) {
			System.out.println("Note: " + element);
			
		}
		
		
		
		
	}

}
