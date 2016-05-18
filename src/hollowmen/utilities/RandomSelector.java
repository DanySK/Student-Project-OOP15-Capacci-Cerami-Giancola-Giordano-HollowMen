package hollowmen.utilities;

public class RandomSelector {

	private static final float DIGIT = 10^5;
	
	public static <T> T getFrom(T[] vett) {
		int i = (int) (Math.random() * DIGIT) % vett.length;
		return vett[i];
	}
	
	
	
}
