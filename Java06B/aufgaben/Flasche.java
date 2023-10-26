
public class Flasche {
	
	int groesse = 0;
	int kapazitaet = 0;
	String material = "unbekannt";
	
	Flasche(int groesse) {
		this.groesse = groesse;
	}
	
	Flasche(int groesse, int kapazitaet, String material) {
		
		this.groesse = groesse;
		this.kapazitaet = kapazitaet;
		this.material = material;
		
	}
	
	public void ausgeben() {
		System.out.println("Größe: " + getGroesse());
		System.out.println("Kapazität: " + getKapazitaet());
		System.out.println("Material: " + getMaterial());
	}
	
	//Getter Setter

	public int getGroesse() {
		return groesse;
	}

	public int getKapazitaet() {
		return kapazitaet;
	}

	public String getMaterial() {
		return material;
	}
	
	////////// Java6B Aufgabe 1.1
	/*
	 * Konstruktor wird beim Erstellen eines Objektes aufgerufen und initialisiert die notwendigen
	 * Variablen wie bei einer herkömmlichen Methode durch Parameter
	 * 
	 * Finalisierer tut das Gleiche beim Zerstören eines Objektes (durch objehtname = null)
	 * 
	 * 1.2 Ein Standardkonstruktor wird immer erstellt, wenn man selber keinen Konstruktor erstellt. 
	 * Dieser beinhaltet eine leere Parameterlsite. 
	 * Daher setzt man beim Erstellen eines Objektes auch immer die Klammern am Ende ()
	 * 
	 * Erstellt man einen eigenen Konstruktor, wird kein Standardkonstruktor erstellt. 
	 * 
	 * 
	 * */
	
	

}
