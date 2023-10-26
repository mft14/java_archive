/*####################################w#####################
  
Einsendeaufgabe 2.4
  
##########################################################*/

public class Java02b_einsend04 {

	public static void main(String[] args)

	{
		//KM = Kilometer, M = Meter, CM = Centimeter
		int KM = 1; //Hier beliebige Kilometer eingeben
		final int KMzuM = KM * 1000;
		final int KMzuCM = KM * 1000000;
		
		System.out.println(KM + " Kilometer entspricht " + KMzuM + " Metern.");

		//KILOMETER = 1000 * 100;
		System.out.println(KM + " Kilometer entspricht " + KMzuCM + " Zentimetern.");

	}
}