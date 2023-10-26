
public class Wein {
	
	private int alter, menge, wert;
	
	Wein(int alter, int menge) {
		
		this.alter = alter;
		this.menge = menge;
		
	}
	
	protected int altern() {
		alter++;
		wertBerechnen();
		return wert;
	}
	
	protected int trinken() {
		menge--;
		wertBerechnen();
		return wert;
	}
	
	private int wertBerechnen() {
		wert = alter * menge;
		return wert;
	}

}
