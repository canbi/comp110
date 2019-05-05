import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Can Bi
 * @since 12.04.2019
 * 
 * This program reads text file which contains function coefficients
 * Draws axis and the function 
 * Calculates derivatives and draws zero derivative points approximately
 */

public class NumericalDiff {

	public static void main(String[] args) {

		String filename = "functions3.txt"; // text file name

		// opening file
		File myFile = new File(filename);
		Scanner myInput = null;
		try {
			myInput = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			System.out.println(filename + ": Input file can not be found!\nExiting program...");
			System.exit(1);
		}

		ArrayList<String> functions = new ArrayList<>(); // String line array which stores text file lines

		// storing text file lines into String ArrayList
		while (myInput.hasNext()) {
			String line = myInput.nextLine();
			functions.add(line);

		}

		ArrayList<Double> tempArr = new ArrayList<>();
		ArrayList<Polynomial> polynomials = new ArrayList<>(); // stores polynomial objects

		for (int i = 0; i < functions.size(); i++) {
			String[] degrees0 = functions.get(i).split(",");

			for (int j = 0; j < degrees0.length; j++) {
				tempArr.add(Double.parseDouble(degrees0[j].trim()));
			}

			if (tempArr.size() == 2) { // if it is 1D Polynomial
				Polynomial1D p = new Polynomial1D(tempArr.get(0), tempArr.get(1));
				polynomials.add(p);

			} else if (tempArr.size() == 3) { // if it is 2D Polynomial
				Polynomial2D p = new Polynomial2D(tempArr.get(0), tempArr.get(1), tempArr.get(2));
				polynomials.add(p);

			} else if (tempArr.size() == 4) { // if it is 3D Polynomial
				Polynomial3D p = new Polynomial3D(tempArr.get(0), tempArr.get(1), tempArr.get(2), tempArr.get(3));
				polynomials.add(p);
			}

			tempArr.clear();

		}

		// canvas
		int graphXNums = 10;
		int graphYNums = 10;
		int canvas_width = 1000;
		int canvas_height = 1000;
		drawCanvas(canvas_width, canvas_height, graphXNums, graphYNums);
		drawAxis(graphXNums, graphYNums);

		for (Polynomial p : polynomials) {

			p.draw(); // drawing polynomial

			ArrayList<Double> zeroDer = p.derivative(-10, 10); // zero derivative x axis points

			// drawing zero derivative points
			for (int i = 0; i < zeroDer.size(); i++) {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.setPenRadius(0.03);
				StdDraw.point(zeroDer.get(i), p.evaluate(zeroDer.get(i)));
			}

			System.out.println(p); // printing zero derivative points
			
		}
		StdDraw.show();
		StdDraw.save("sample.png");
	}

	/**
	 * Draws canvas
	 * 
	 * @param width      Canvas width
	 * @param height     Canvas height
	 * @param graphXNums x range for coordinate system
	 * @param graphYNums y range for coordinate system
	 */
	public static void drawCanvas(int width, int height, int graphXNums, int graphYNums) {

		StdDraw.clear(StdDraw.WHITE); // set canvas background color
		StdDraw.setCanvasSize(width, height); // window size
		StdDraw.setXscale(-graphXNums, graphXNums);
		StdDraw.setYscale(-graphYNums, graphYNums);
		StdDraw.enableDoubleBuffering();
	}

	/**
	 * Draws Axis
	 * 
	 * @param graphXNums x range for coordinate system
	 * @param graphYNums y range for coordinate system
	 */
	public static void drawAxis(int graphXNums, int graphYNums) {

		StdDraw.setPenRadius(0.003);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(0, -graphYNums, 0, graphYNums); // y axis
		StdDraw.line(-graphXNums, 0, graphXNums, 0); // x axis

		StdDraw.setPenRadius(0.01);
		for (int i = 0; i <= graphXNums; i++) { // x axis points
			StdDraw.point(i, 0);
			StdDraw.point(-i, 0);
		}

		for (int i = 0; i <= graphYNums; i++) { // y axis points
			StdDraw.point(0, i);
			StdDraw.point(0, -i);
		}
	}

}
