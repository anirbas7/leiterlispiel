package jenb.leiterlispiel.Objekte;

import java.util.Random;


public class Wuerfel {
		//Diese Methode generiert zufällige Zahlen zwischen 1-6
	public int wuerfeln() {
		Random r = new Random();
		int n = r.nextInt(6);
		return n = n + 1;
	}
}