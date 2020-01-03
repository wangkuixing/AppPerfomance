package Common;

public class Commons {
	public static double streamDouble(double pixRate) {
		double newPixRate = (double)Math.round(pixRate * 100) / 100;
		return newPixRate;
	}
}