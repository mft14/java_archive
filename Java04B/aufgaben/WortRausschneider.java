
public class WortRausschneider {

	public static void main(String[] args) {

		String test = "Beliebiger Text für das Nutzen einer Eingabe dies das";
		String test2 = "Das ist ein  weiterer String";
		String output = "";
		
		output = test.substring(0, test.indexOf(" "));
		
		
		
		System.out.println(output);
		System.out.println(test);
		
		
		
		
		
	}

}
