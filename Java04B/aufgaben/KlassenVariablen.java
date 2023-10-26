// das STATIC muss benutzt werden, damit die Variablen auch überall gesehen werden können

public class KlassenVariablen {

	static int varA;

	static void gibAus() {
		varA = 4;
		System.out.println("Der Wert von varA ist nun: " + varA);
	}
	
	public static void main(String[] args) {

		varA = 5;
		System.out.println(varA);
		gibAus();
		System.out.println(varA);
		
	}

}
