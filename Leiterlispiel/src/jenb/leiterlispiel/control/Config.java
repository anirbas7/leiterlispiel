package jenb.leiterlispiel.control;

public class Config {

	public static final boolean CONSOLE = true;
	
	public void config(arg) {
		
	//damit alle System.out.println funktionen auf einmal auf ein oder aus geschalltet werden können.
	if (Config.CONSOLE == true) {
		System.out.println();
	}
}}
