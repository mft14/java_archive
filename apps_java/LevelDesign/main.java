import java.util.Timer;
import java.util.TimerTask;

public class main {
	

	public static void main(String[] args) {
		
	
		int breite = 32;
		int hoehe = 32;
		
		String[][] feld = new String[hoehe][breite]; 
		int xHoehe,yBreite;
		
		//Generate field
		for (int i = 0; i < hoehe; i++) {
			for (int j = 0; j < breite; j++) {
				feld[i][j] = "#";
			}
		}
		
		//Starting point
//		x = (int)(Math.random() * hoehe + 0);
//		y = (int)(Math.random() * breite + 0);
		xHoehe = 30;
		yBreite = 10;
		//Control
		System.out.println(xHoehe + " und " + yBreite);
		
		//Setting starting point
		feld[xHoehe][yBreite] = ";";
		boolean obenFrei = false;
		boolean untenFrei = false;
		boolean rechtsFrei = false;
		boolean linksFrei = false;

		/////////////////////////generate path
		
		for (int i = 0; i < 120; i++) {
			
			//rechts frei und wenn rechts am Ende ist
			if((yBreite + 2) < (breite) && feld[xHoehe][yBreite + 2].equals("#")) {
				rechtsFrei = true;
			}
			
			//oben frei
			if((xHoehe - 2) < (hoehe) && feld[xHoehe - 2][yBreite].equals("#")) {
				obenFrei = true;
			}
			
			if((rechtsFrei == true) && (obenFrei == true)) {
				int random = (int)(Math.random() * 2 + 1);
				if(random == 1) { //go up
					if((xHoehe-2) < 0) {
						break;
					}
					feld[xHoehe-1][yBreite] = ".";
					feld[xHoehe-2][yBreite] = ".";
					xHoehe = xHoehe - 2;
					
				}
				if(random == 2) { //go right
					feld[xHoehe][yBreite+1] = ".";
					feld[xHoehe][yBreite+2] = ".";
					yBreite = yBreite + 2;
				}
			} //if end
			
		rechtsFrei = false;
		obenFrei = false;
			
		}
		/////////////////////////generate path end	
		
		//print field
		for (int i = 0; i < hoehe; i++) {
			
			for (int j = 0; j < breite; j++) {
				System.out.print(feld[i][j]);
			}
			System.out.println();
			
		}
		

		
		
		
		
		
		

		

	}

}
