/*########################################
 * Ein Problem durch vertauschte Argumente
 * beim Aufruf einer Methode
######################################## */

public class Java08b_01_06 {
	//die Methode zur Subtraktion
	static int subtraktion(int wert1, int wert2) {
		if(wert1 < wert2) {
			return (wert2 - wert1);
		} else if(wert1 == wert2) {
			return 0;
		}
		return (wert1 - wert2);
	}
	
	public static void main(String[] args) {
		int zahl;
		//eigentlich soll 20-10 gerechnet werden
		zahl = subtraktion(30,20);
		System.out.println("Zahl hat den Wert: " + zahl);
	}
}
