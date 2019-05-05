/**
 * @author Can Bi
 * @since 17.03.2019
 * 
 * Circle Class which its objects have x, y coordinates and radius
 * 
 */
public class Circle {

	private double x;
	private double y;
	private double r;

	/**
	 * Constructor for Circle class
	 * 
	 * @param x double, x coordinate of circle object
	 * @param y double, y coordinate of circle object
	 * @param r double, radius of circle object
	 */
	public Circle(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	/**
	 * Draws circle to canvas.
	 */
	public void draw() {

		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.002);
		StdDraw.circle(this.getX(), this.getY(), this.getR()); // Drawing circle

	}

	/**
	 * Measures distance between two circles.
	 * 
	 * @param inputCircle circle object input
	 * @return returns distance between the circle and given input circle
	 */
	public double distance(Circle inputCircle) {

		double distance = Math.sqrt(
				Math.pow((inputCircle.getX() - this.getX()), 2) + Math.pow((inputCircle.getY() - this.getY()), 2));

		return distance;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getR() {
		return r;
	}

}
