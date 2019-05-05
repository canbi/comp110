import java.util.Random;

/**
 * @author Can Bi
 * @since 17.03.2019
 * 
 * Draws number of circles which is given. 
 * If number of circles bigger and equals to 3,
 * it draws circles in bold which are the furthermost from each other
 * and draws a red line between the circles centers
 * 
 */
public class RemoteCircles {

	public static void main(String[] args) {

		// Canvas
		int canvas_width = 500;
		int canvas_height = 500;
		StdDraw.clear(StdDraw.WHITE); // set canvas background color
		StdDraw.setCanvasSize(canvas_width, canvas_height); // window size
		StdDraw.setXscale(-1, 1); // set x coordinates
		StdDraw.setYscale(-1, 1); // set y coordinates
		StdDraw.enableDoubleBuffering();

		int n = 100; // number of circles

		Circle[] circles = new Circle[n]; // circle array

		Random random = new Random(); // random object

		for (int i = 0; i < n; i++) {

			// getting random numbers for x and y
			double x = random.nextDouble() * 2 - 1;
			double y = random.nextDouble() * 2 - 1;

			// rule for random radius
			double getRandom;
			while (true) {
				getRandom = random.nextDouble() / 5; // dividing 5 to see circles clearly

				if ((1.0 - Math.abs(Math.abs(x) > Math.abs(y) ? x : y)) > getRandom) {
					break;
				}
			}
			double r = getRandom;

			Circle circle = new Circle(x, y, r); // creating a circle object
			circles[i] = circle; // storing the circle
			circle.draw(); // drawing with using circle object method draw()
		}

		double tempX1 = 0.0, tempY1 = 0.0, tempR1 = 0.0;
		double tempX2 = 0.0, tempY2 = 0.0, tempR2 = 0.0;

		// measuring distances between all circles
		double distance = 0.0;
		for (int i = 0; i < circles.length; i++) {
			for (int j = i + 1; j < circles.length - 1; j++) {

				if (circles[i].distance(circles[j]) > distance) {
					distance = circles[i].distance(circles[j]);
					tempX1 = circles[i].getX();
					tempY1 = circles[i].getY();
					tempX2 = circles[j].getX();
					tempY2 = circles[j].getY();
					tempR1 = circles[i].getR();
					tempR2 = circles[j].getR();

				}
			}
		}
		// Drawing circles which are the furthermost from each other
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.004);

		StdDraw.circle(tempX1, tempY1, tempR1);
		StdDraw.circle(tempX2, tempY2, tempR2);

		// Drawing red line between two circles' centers.
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius(0.004);
		StdDraw.line(tempX1, tempY1, tempX2, tempY2);
		StdDraw.show();

	}
}
