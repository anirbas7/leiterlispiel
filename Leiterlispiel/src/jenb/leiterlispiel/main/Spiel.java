package jenb.leiterlispiel.main;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import jenb.leiterlispiel.Objekte.SchlangeLeiter;
import jenb.leiterlispiel.Objekte.SpielZug;
import jenb.leiterlispiel.Objekte.Spieler;
//import jenb.leiterlispiel.Objekte.Wuerfel;
import jenb.leiterlispiel.ansicht.SpielBrett;
import jenb.leiterlispiel.control.Controler;

public class Spiel {
	private final int WINPOINT = 100;
	private SpielZug spielZug = new SpielZug();
	private boolean jemandHatGewonnen = false;
	private Queue<Spieler> spielerWarteschlange = new ConcurrentLinkedQueue<>();
	//private Wuerfel wuerfel = new Wuerfel();
	private SchlangeLeiter schlangeLeiter = new SchlangeLeiter();
	private SpielBrett spielBrett = new SpielBrett();
	private Scanner s = new Scanner(System.in);
	private Controler controler = new Controler();

	public Spiel() {
		//System.out.println("Willkommen zum Leiterlispiel");
		//spielerErstellen();
		while (jemandHatGewonnen == false) {
			spielen(spielerWarteschlange.poll());
		}
		s.close();
	}

/*	private void spielerErstellen() {
		int i = 1;
		String name = "";
		while (true){
			System.out.println("Bitte gebt den Namen " + i++ + ". Spielers ein. Nichts eingeben und Enter drücken, wenn alle Spieler erfassr sind. ");
			name = s.nextLine();
			
			if (name.equals("")) {						
				break;
			}else {
				spielerWarteschlange.add(new Spieler(name));
			}
		}
	}*/

	private void spielen(Spieler spieler) {
		System.out.println(spielBrett.getSpielerWarteschlange(spieler.getName()) + " du bist an der Reihe. Drücke Enter um zu Würfeln.");
		s.nextLine();
		
		int wurf = controler.getGewuerfelteZahl();
		int punkte = wurf;
		if (wurf == 6) {
			spieler.setAnzahlSechserGewuerfelt(spieler.getAnzahlSechserGewuerfelt()+1);
			System.out.println("Du hast ein 6er gewürfelt. Würfle nochmals.");
			s.nextLine();
			spielBrett.wuerfelButton.onActionProperty();
			//controler.setGewuerfelteZahl(wuerfel.wuerfeln());
			wurf = controler.getGewuerfelteZahl();
			punkte = punkte + wurf;
			if (wurf == 6) {
				spieler.setAnzahlSechserGewuerfelt(spieler.getAnzahlSechserGewuerfelt()+1);
				System.out.println("Du hast einen weiteren 6er gewürfelt. Würfle nochmals.");
				s.nextLine();
				spielBrett.wuerfelButton.onActionProperty();
				//controler.setGewuerfelteZahl(wuerfel.wuerfeln());
				wurf = controler.getGewuerfelteZahl();
				punkte = punkte + wurf;
				if (wurf == 6) {
					spieler.setAnzahlSechserGewuerfelt(spieler.getAnzahlSechserGewuerfelt()+1);
					if (spieler.getAnzahlSechserGewuerfelt() == 3) {
						System.out.println("3x ein 6er hinter einander gewürfelt. Pech gehabt!");
						spieler.setAnzahlSechserGewuerfelt(0);
						spielerWarteschlange.add(spieler);
					} else {
						spieler.setAnzahlSechserGewuerfelt(0);
						spielZug.ziehen(spieler, wurf, punkte);
						spieler.setSpielerPosition(schlangeLeiter.pruefen(spieler.getSpielerPosition()));
					}
				}else {
					spieler.setAnzahlSechserGewuerfelt(0);
					spielZug.ziehen(spieler, wurf, punkte);
					spieler.setSpielerPosition(schlangeLeiter.pruefen(spieler.getSpielerPosition()));
				}
			}else {
				spieler.setAnzahlSechserGewuerfelt(0);
				spielZug.ziehen(spieler, wurf, punkte);
				spieler.setSpielerPosition(schlangeLeiter.pruefen(spieler.getSpielerPosition()));
			}
		}else {
			spieler.setAnzahlSechserGewuerfelt(0);
			spielZug.ziehen(spieler, wurf, punkte);
			spieler.setSpielerPosition(schlangeLeiter.pruefen(spieler.getSpielerPosition()));
		}

		if (spieler.getSpielerPosition() >= WINPOINT) {
			jemandHatGewonnen = true;
			System.out.println(spieler.getName() + " Du hast Gewonnen!!");
		} else {
			spielerWarteschlange.add(spieler);
		}
		System.out.println(spieler.getName() + ": " + spieler.getSpielerPosition());
	}
}
