
public class NurLokaleVariablenNutzen {

	static void berechnung(int eingabe[][]) {
		int zahl, lange,breite;
		
		eingabe[0][0] = 3;
		eingabe[0][1] = 3;
		eingabe[0][2] = 3;
		
		eingabe[1][0] = 5;
		eingabe[1][1] = 5;
		eingabe[1][2] = 5;

	}
	
	public static void main(String[] args) {

		int[][] kisten = new int[2][3];
		
		berechnung(kisten);
		
		System.out.println(kisten[0][0] + kisten[0][1] + kisten[0][2]);
		
	}

}
