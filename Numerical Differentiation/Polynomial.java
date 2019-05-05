import java.util.ArrayList;

/**
 *
 * @author Can Bi
 * @since 19.04.2019
 * 
 * Polynomial Class Superclass
 */
public class Polynomial {
	private ArrayList<Double> x;
	private ArrayList<Double> y;
	private double h = 0.0001;

	public Polynomial() {
		x = new ArrayList<>();
		y = new ArrayList<>();
	}

	/**
	 * Calculates y value of x coordinate
	 * 
	 * @param x x coordinate
	 * @return y value
	 */
	public double evaluate(double x) {
		return 0.0;
	}

	/**
	 * Draws the polynomial
	 * 
	 */
	public void draw() {

		for (double i = -10; i <= 10; i = i + 0.01) {
			StdDraw.setPenRadius(0.01);
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			double x = i;
			double y = this.evaluate(x);
			this.getX().add(i);
			this.getY().add(y);
			StdDraw.point(x, y);
		}

	}

	/**
	 * Calculates derivative of the polynomial Finds 0 derivative points
	 * (approximately)
	 * 
	 * @param xmin minumum value of the range
	 * @param xmax maximum value of the range
	 * @return returns an ArrayList that contains zero derivate points' x coords
	 */
	public ArrayList<Double> derivative(double xmin, double xmax) {

		ArrayList<Double> xCoords = new ArrayList<>();

		for (double i = xmin; i <= xmax; i = i + 0.001) {

			double slope = (evaluate(i + this.h) - evaluate(i)) / this.h;

			double secondX = 0.0;

			if (Math.abs(slope) <= 0.01) {

				// finds second slope which is the same with the first one
				// Rolle's theorem
				for (double j = i; j <= i + 1; j = j + 0.01) {

					double slope2 = (evaluate(j + this.h) - evaluate(j)) / this.h;
					if (Math.abs(slope) - Math.abs(slope2) < 0.0000001) {
						secondX = j;
					}
				}

				double min = Math.abs(slope);
				double coordX = i;

				// tries to find the smallest slope of the range
				// Rolle's theorem
				for (double k = i; k <= secondX; k = k + 0.01) {

					double slope2 = (evaluate(k + this.h) - evaluate(k)) / this.h;
					if (Math.abs(slope2) < min) {
						min = Math.abs(slope2);
						coordX = k;
					}

				}

				xCoords.add(coordX); // adds the x coordinate of the smallest slope.
				i = secondX;
			}

		}

		return xCoords;

	}

	public double getH() {
		return h;
	}

	public ArrayList<Double> getX() {
		return x;
	}

	public ArrayList<Double> getY() {
		return y;
	}

}
