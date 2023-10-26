
/* ######################################################
 * Einsendeaufgabe 5.2
 * ###################################################### */
//Name: Karim Kiel
//Datum 11/01/2020
// JAVA 5B-XX1-N01
class Listenelement {
	String daten;
	Listenelement naechster, listenEnde; //ListenEnde als neues Objekt

	//Nach wie vor wird hier f�r "naechster" das Ende mit null gesetzt, wenn setDaten aufgerufen wird
	//Ein zus�tzlicher Eintrag "listenEnde = this" setzt damit f�r das erstellte Objekt 
	//listenEnde der Klasse Listenelement das Ende. 
	
	void setDaten(String datenNeu) {
		daten = datenNeu;
		naechster = null;
        listenEnde = this; 
	}
	
	//Beim Anh�ngen geht man nun nicht mehr die komplette Liste durch
	//sondern h�ngt die Daten mit dem zus�tzlichen "listenEnde" direkt ans Ende.
	//Das Durchlaufen f�llt weg

    void anhaengen(String datenNeu) {          
        listenEnde.naechster = new Listenelement(); 
        listenEnde.naechster.setDaten(datenNeu); 
        listenEnde = listenEnde.naechster;          
     System.out.println("Daten " + datenNeu + " wurden eingef�gt."); 
  } 
		
	void ausgeben() {
		System.out.println(daten);
		if (naechster != null)
			naechster.ausgeben();
	}
	
	/* ######################################################
	 * Einsendeaufgabe 5.3
	 * ###################################################### */
	//Bei der R�ckw�rtsausgabe muss man das Stack Prinzip anwenden, ausschlaggebend ist hierbei,
	//dass die Konsolenausgabe nach dem Methodenaufruf stattfinden muss

	void rueckwaertsAusgeben() {
		if (naechster == null) {
			System.out.println("Liste wird nun r�ckw�rts ausgegeben");
		System.out.println(daten);
		}
		else {
			naechster.rueckwaertsAusgeben();
			System.out.println(daten);
		}
	}

}

public class Java05b_einsendaufgabe5_2und5_3 {
	
	public static void main(String[] args) {
		Listenelement listenAnfang = new Listenelement();
	
		listenAnfang.setDaten("Element 1");

		for (int element = 2; element < 21; element ++)
			listenAnfang.anhaengen ("Element " + element);
			
		listenAnfang.ausgeben();
		System.out.println("-------");
		listenAnfang.rueckwaertsAusgeben(); //Liste r�ckw�rts ausgeben
		
	}
	
}

