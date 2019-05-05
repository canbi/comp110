import java.util.ArrayList;

/**
 *
 * @author Can Bi
 * @since 19.04.2019
 * 
 * 1D Polynomial Class extends Polynomial
 * 
 */

public class Polynomial1D extends Polynomial {

	private double a;
	private double b;

	/**
	 * 1D Polynomial Constructor
	 * 
	 * @param a x^0 coefficient
	 * @param b x^1 coefficient
	 */
	public Polynomial1D(double a, double b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public double evaluate(double x) {

		double y = a + b * x;
		return y;

	}

	@Override
	public String toString() {
		// Function: (0.150)x^2+(-0.500)x^1+(-7.000)
		// Points with zero derivatives:
		// 1. x: 1.67, y: -7.42
		ArrayList<Double> zeroDer = this.derivative(-10, 10);

		String allOfThem;

		String function = String.format("Function: (%5.3f)x+(%5.3f)\n", this.b, this.a);
		String points = "Points with zero derivatives:\n";

		allOfThem = function + points;

		String zero;
		int numberOfZero = 1;
		if (zeroDer.size() == 0) {
			zero = "There are no points with zero derivative in the range.\n";
			allOfThem = allOfThem + zero;
		} else {

			for (Double point : zeroDer) {
				zero = String.format("%d. x:%5.2f, y:%5.2f\n", numberOfZero, point, this.evaluate(point));
				allOfThem = allOfThem + zero;
				numberOfZero++;
			}
		}

		return allOfThem;
	}

}
