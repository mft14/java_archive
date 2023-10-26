
public class LokaleVariablen {

	static void gibVarAus() {
		int gibVarAus = 4;
		System.out.println(gibVarAus);
	}
	
	public static void main(String[] args) {

		gibVarAus();
		int gibVarAus = 10;
		
		System.out.printf("Werte beider: %d", gibVarAus);
		
	}
	
	//Lokale Variablen sind nur innerhalb von Anweisungsblöcken lesbar

}
