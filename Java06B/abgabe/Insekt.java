
class Insekt {
	
	int laenge;
	int gewicht;
	
	Insekt(int laenge, int gewicht) {
		this.laenge = laenge;
		this.gewicht = gewicht;
	}
	
	void essen() {
		laenge = laenge + 1;
		gewicht = gewicht + 1;
	}
	
	void ausgabe() {
		System.out.println("Das Insekt ist " + laenge + " cm lang und wiegt " + gewicht + " Gramm.");
	}
	
/*	@Override
	public String toString() { 
		String ausgabe; 
		ausgabe = "[" + this.getClass(); 
		ausgabe = ausgabe + " Länge: " + laenge; 
		ausgabe = ausgabe + " Gewicht: " + gewicht + "]"; 
		return ausgabe; 
	} 
*/

}


