/* ################################
 * Die Klasse für den Wein
 * Jetzt mit Plausibilitätsprüfung  
 ##################################*/
//package java08b_01_05;

public class Wein {
	private int alter;
	private double grundpreis;
	private double preisProFlasche;
	
	//der StandardkKonstruktor
	public Wein() {
		//er ruft über this() den Konstruktor mit den beiden Parametern auf
		//und übergibt die Standardwerte
		this(1, 10);
	}
	
	//der Konstruktor setzt das Alter
	//der Preis erhält einen Standardwert
	public Wein(int alter) {
		//er ruft ebenfalls über this den Konstruktor mit den beiden Parametern auf
		this(alter, 10);
	}
	
	//der Konstruktor setzt den Preis
	//das Alter erhält einen Standardwert
	public Wein(double grundpreis) {
		//und dieser Konstruktor ruft auch den Konstruktor mit den beiden Parametern auf
		this(1, grundpreis);
	}
	
	//der Konstruktor setzt das Alter und den Grundpreis
	//er enthält jetzt Plausibilitätsprüfungen
	public Wein(int alter, double grundpreis) {
		//die Plausibilitätsprüfung für das Alter
		if (alter > 0)
			this.alter = alter;
		//sonst den Standardwert 1 setzen
		else
			this.alter = 1;
		//und auch für den Preis
		if (grundpreis > 9)
			this.grundpreis = grundpreis;
		else
			this.grundpreis = 10;
	}
	
	//die Methode berechnet den Preis pro Flasche
	public void preisBerechnen() {
		preisProFlasche = alter * grundpreis;
	}
	
	//die Methode liefert den Preis pro Flasche
	public double getPreisProFlasche() {
		preisBerechnen();
		return preisProFlasche;
	}
}
