
public class Autoklasse {

	String autoname;
	int geschwindigkeit;
	
	public Autoklasse (String autoname, int geschwindigkeit) {
		this.autoname = autoname;
		this.geschwindigkeit = geschwindigkeit;
		System.out.println("Die Geschwindigkeit beträgt vom "+autoname+" nun " + this.geschwindigkeit + " km/h");
	}
	
	void bremsen(int aenderung) {
		if ((geschwindigkeit - aenderung)<0) {
			geschwindigkeit = 0;
		} else {
			geschwindigkeit = geschwindigkeit - aenderung;
		}
	}
	
	void beschleunigen(int geschwindigkeit) {
		this.geschwindigkeit = geschwindigkeit;
	}



/////// GETTERS SETTERS
public String getAutoname() {
	return autoname;
}

public void setAutoname(String autoname) {
	this.autoname = autoname;
}

public int getGeschwindigkeit() {
	return geschwindigkeit;
}

public void setGeschwindigkeit(int geschwindigkeit) {
	this.geschwindigkeit = geschwindigkeit;
}


}