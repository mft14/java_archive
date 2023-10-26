
public class Biene extends Insekt {

	int geschwindigkeit;
	
	Biene(int laenge, int gewicht, int geschwindigkeit) {
		super(laenge, gewicht);
		this.geschwindigkeit = geschwindigkeit;
	}
	
	public String toString() {
		String ausgabe = "";
		ausgabe = super.toString();
		return ausgabe;
	}
	
	@Override
	void ausgabe() {
		System.out.println("Die Biene ist " + laenge + " cm lang, wiegt " + gewicht + " Gramm "
				+ "und fliegt " + geschwindigkeit + " km/h schnell");
		
	}

}
