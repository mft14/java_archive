/* ################################
 * Die Klasse f�r den Wein
 * Jetzt mit Plausibilit�tspr�fung  
 ##################################*/
//package java08b_01_05;

public class Wein {
	private int alter;
	private double grundpreis;
	private double preisProFlasche;
	
	//der StandardkKonstruktor
	public Wein() {
		//er ruft �ber this() den Konstruktor mit den beiden Parametern auf
		//und �bergibt die Standardwerte
		this(1, 10);
	}
	
	//der Konstruktor setzt das Alter
	//der Preis erh�lt einen Standardwert
	public Wein(int alter) {
		//er ruft ebenfalls �ber this den Konstruktor mit den beiden Parametern auf
		this(alter, 10);
	}
	
	//der Konstruktor setzt den Preis
	//das Alter erh�lt einen Standardwert
	public Wein(double grundpreis) {
		//und dieser Konstruktor ruft auch den Konstruktor mit den beiden Parametern auf
		this(1, grundpreis);
	}
	
	//der Konstruktor setzt das Alter und den Grundpreis
	//er enth�lt jetzt Plausibilit�tspr�fungen
	public Wein(int alter, double grundpreis) {
		//die Plausibilit�tspr�fung f�r das Alter
		if (alter > 0)
			this.alter = alter;
		//sonst den Standardwert 1 setzen
		else
			this.alter = 1;
		//und auch f�r den Preis
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
