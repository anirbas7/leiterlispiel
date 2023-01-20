package jenb.leiterlispiel.Objekte;
import java.util.HashMap;
import java.util.Map;

//Schlagen und Leiter Definition
public class SchlangeLeiter {
	private Map<Integer, Integer> schlange = new HashMap<>();
	private Map<Integer, Integer> leiter = new HashMap<>();
	
	public SchlangeLeiter()
	{
		schlange.put(98, 79);
		schlange.put(92, 71);
		schlange.put(88, 67);
		schlange.put(65, 47);
		schlange.put(58, 26);
		schlange.put(41, 22);
		schlange.put(33, 8);

		leiter.put(2, 43);
		leiter.put(12, 51);
		leiter.put(25, 46);
		leiter.put(55, 86);
		leiter.put(57, 76);
		leiter.put(59, 80);
	}
	
	public int pruefen(int spielerPosition) {
		Integer ziel = schlange.get(spielerPosition);
		if (ziel != null){
			//System.out.println() ist gewollt eingestellt, da auf der Oberfläche nicht immer gut ersichtlich.
			System.out.println("Du wurdest von einer Schlange gebissen.");
			return ziel;
		}
		ziel = leiter.get(spielerPosition);
		if (ziel != null) {
			//System.out.println() ist gewollt eingestellt, da auf der Oberfläche nicht immer gut ersichtlich.
			System.out.println("Du kletterst eine Leiter hoch.");
			return ziel;
		}
		return spielerPosition;
	}
}
	