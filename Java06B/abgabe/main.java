
public class main {

	public static void main(String[] args) {

		Insekt i = new Insekt(10, 50);
		Biene b = new Biene(6, 50, 25);
		i.ausgabe();
		b.ausgabe();
		
		System.out.println(b.toString());
		
	}
}


