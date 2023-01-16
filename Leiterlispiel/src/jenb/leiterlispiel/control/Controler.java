package jenb.leiterlispiel.control;


public class Controler {
	
	private int gewuerfelteZahl;
	private int posCir1 = 1;
	private int posCir2 = 1;
	
	public int getPosCir1() {
		return posCir1;
	}

	public void setPosCir1(int posCir1) {
		this.posCir1 = posCir1;
	}

	public int getPosCir2() {
		return posCir2;
	}

	public void setPosCir2(int posCir2) {
		this.posCir2 = posCir2;
	}

	public int getGewuerfelteZahl() {
		return gewuerfelteZahl;
	}

	public void setGewuerfelteZahl(int gewuerfelteZahl) {
		this.gewuerfelteZahl = gewuerfelteZahl;
	}
	
// verbindet das Spielbrett mit dem Spiel(logik) mittels getter und setter funktionen
}
