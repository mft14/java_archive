/* ######################################################
 * Einsendeaufgabe 5.1
 * ###################################################### */
//Name: Karim Kiel
//Datum 11/01/2020
// JAVA 5B-XX1-N01
class Fernseher {
	
	int lautstaerke = 0; // Lautst�rke von 0 - 100%. 
	String programm = ""; //Programm als Typ String
	boolean istEingeschaltet = false; // Zustand an oder aus als boolean
	
	//Die Methode gibt den aktuellen Zustand aller Instanzvariablen wieder.
	//Sie wird auch in jeder anderen Methode am Ende wie aus der Aufgabenstellung 
	//her gew�nscht, aufgerufen
	public void zustand() {
		System.out.println("Fernsehlautst�rke ist auf " + getLautstaerke() + "%");
		System.out.println("Aktuelles Programm: " + getProgramm());		
		System.out.println("Fernseher ist angeschaltet! " + IstEingeschaltet() +" \n");
	}
	
	//Die Methode �ndert die Lautst�rke. Ich arbeite mit Prozent, also wenn jemand unter 0 oder �ber 100 geht, wird 
	//der Wert einfach auf 0 bzw. 100 gesetzt. Au�erdem nur, wenn der Fernseher eingeschaltet ist.
	public void aendereLautstaerke(int neueLautstaerke) {

		if(istEingeschaltet) {
			if(neueLautstaerke <= 0) {
				setLautstaerke(0);
				zustand();
			} else if (neueLautstaerke >= 100) {
				lautstaerke = 100;
				zustand();
			} else {
				lautstaerke = neueLautstaerke;
				zustand();
			}
			
		} else {
			System.out.println("Bitte den Fernseher anschalten, um die Lautst�rke anzupassen!\n");
		}
		
	}
	
	//Hier kann man das Programm �ndern. Der Programmname wird als String gespeichert.
	public void aendereProgramm(String neuesProgramm) {
		setProgramm(neuesProgramm);
		zustand();
	}
	
	//Fernseher anschalten mit istEingeschaltet = true
	public void anschalten() {
		istEingeschaltet = true;
		zustand();
	}
	//Fernseher ausschalten mit istEingeschaltet = false
	public void ausschalten() {
		istEingeschaltet = false;
		zustand();
	}
	
	//Getter und Setter f�r die Instanzvariablen. Get...() f�r den aktuellen R�ckgabewert,
	// Set...() um die Werte der Variablen zu ver�ndern
	public int getLautstaerke() {
		return lautstaerke;
	}
	public void setLautstaerke(int lautstaerke) {
		this.lautstaerke = lautstaerke;
	}
	public String getProgramm() {
		return programm;
	}
	public void setProgramm(String programm) {
		this.programm = programm;
	}
	public boolean IstEingeschaltet() {
		return istEingeschaltet;
	}
	public void setIstEingeschaltet(boolean istEingeschaltet) {
		this.istEingeschaltet = istEingeschaltet;
	}

}

	//Main Methode. 
public class Java05b_einsendaufgabe5_1 {

	public static void main(String[] args) {

		Fernseher f1 = new Fernseher();
		f1.anschalten();
		
		f1.aendereLautstaerke(50);
		f1.aendereLautstaerke(120);
		
		f1.aendereProgramm("Das Erste");
		f1.aendereProgramm("ZDF");
		f1.aendereProgramm("3Sat");
		
		f1.ausschalten();
		//Lautst�rke l�sst sich nicht �ndern, wenn Fernseher aus ist, hier ein Test
		f1.aendereLautstaerke(30);

	}
	
}
