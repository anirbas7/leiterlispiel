package jenb.leiterlispiel.Objekte;

public class SpielZug {
	public void ziehen(Spieler spieler, int wurf, int punkte) {
		if (punkte > 6) {
			System.out.println("Du hast eine " + punkte + " gewürfelt.");
			spieler.setSpielerPosition(spieler.getSpielerPosition()+ punkte);
		}else {
			System.out.println("Du hast eine " + wurf + " gewürfelt.");
			spieler.setSpielerPosition(spieler.getSpielerPosition()+ wurf);
		}
	}
}
