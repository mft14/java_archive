
public class Zeichenkettenbauer {

	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder("Das ist ein beliebiger Text, der rausgeschnitten werden soll");
		StringBuilder output = new StringBuilder("");
		
		System.out.println(sb+"\n");
		
		
		while (sb.indexOf(" ") >= 0) {
			output.append( sb.substring(0, sb.indexOf(" ")) );
			output.append(" ");
			sb.delete(0, sb.indexOf(" ") + 1);
			
			System.out.println(output);
		}
		
		
		
		
		
	}

}
