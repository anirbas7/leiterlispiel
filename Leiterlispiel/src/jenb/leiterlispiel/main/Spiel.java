package jenb.leiterlispiel.main;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import jenb.leiterlispiel.Objekte.SchlangeLeiter;
import jenb.leiterlispiel.Objekte.Spieler;
import jenb.leiterlispiel.Objekte.Wuerfel;

//Spiellogik/Ablauf
public class Spiel {
	public boolean sechsGewuerfelt = false;
	public boolean jemandHatGewonnen = false;
	public final int WINPOINT = 100;
	public Queue<Spieler> spielerWarteschlange = new ConcurrentLinkedQueue<>();
	private Wuerfel wuerfel = new Wuerfel();
	private SchlangeLeiter schlangeLeiter = new SchlangeLeiter();
	public int punkte = 0;


	public Spieler getNextSpieler() {
		return spielerWarteschlange.peek();
	}
		
	public void addPlayer(String name) {
		spielerWarteschlange.add(new Spieler(name));
	}

	public void spielen() {
		Spieler spieler = spielerWarteschlange.peek();
		int wurf = wuerfel.wuerfeln();
		
		if (sechsGewuerfelt) {
			punkte = punkte + wurf;
		} else {
			punkte = wurf;
		}
		
		if (wurf == 6) {
			sechsGewuerfelt = true;
			spieler.setAnzahlSechserGewuerfelt(spieler.getAnzahlSechserGewuerfelt()+1);
			
			if (spieler.getAnzahlSechserGewuerfelt() == 3) {
				System.out.println("3x ein 6er hinter einander gewürfelt. Pech gehabt!");
				spieler.setAnzahlSechserGewuerfelt(0);
				spielerWarteschlange.add(spielerWarteschlange.poll());
				punkte = 0;
			} 
			
		}else {
			sechsGewuerfelt = false;
			
			spieler.setAnzahlSechserGewuerfelt(0);
			ziehen(spieler, wurf, punkte);
			spieler.setSpielerPosition(schlangeLeiter.pruefen(spieler.getSpielerPosition()));
			spielerWarteschlange.add(spielerWarteschlange.poll());
		}

		if (spieler.getSpielerPosition() >= WINPOINT) {
			jemandHatGewonnen = true;
			System.out.println(spieler.getName() + " Du hast Gewonnen!!");
		} 
		//System.out.println(spieler.getName() + ": " + spieler.getSpielerPosition());
	}
	
	public void ziehen(Spieler spieler, int wurf, int punkte) {
		if (punkte > 6) {
			//System.out.println("Du hast eine " + punkte + " gewürfelt.");
			spieler.setSpielerPosition(spieler.getSpielerPosition()+ punkte);
		}else {
			//System.out.println("Du hast eine " + wurf + " gewürfelt.");
			spieler.setSpielerPosition(spieler.getSpielerPosition()+ wurf);
		}
	}
}
