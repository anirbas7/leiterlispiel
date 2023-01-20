package jenb.leiterlispiel.Objekte;
//definiert die Klasse spieler
public class Spieler {
	private int spielerPosition = 1;
	private final String name;
	private int anzahlSechserGewuerfelt = 0;
	
    public int getAnzahlSechserGewuerfelt() {
		return anzahlSechserGewuerfelt;
	}

	public void setAnzahlSechserGewuerfelt(int anzahlSechserGewuerfelt) {
		this.anzahlSechserGewuerfelt = anzahlSechserGewuerfelt;
	}

	public Spieler(String name) {
		this.name = name;
    }

	public int getSpielerPosition() {
		return spielerPosition;
	}

	public void setSpielerPosition(int spielerPosition) {
		this.spielerPosition = spielerPosition;
	}
	
	public String getName() {
		return name;
	}   
}