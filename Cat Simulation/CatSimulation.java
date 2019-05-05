import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.awt.Font;

/**
 * 
 * @author Can Bi
 * @since 28.03.2019
 * 
 *        Draws a map according to given world type file. (World type file must
 *        have walls) An a circle orange cat moves randomly in the world
 *        Whenever it see a food in one cell distance Cat takes food Cat can not
 *        moves into sea and wall.
 */

public class CatSimulation {
	
	public static void main(String[] args){

		String filename = "world.txt"; // world file
		int iteration = 20000; // number of moves

		// opening file
		File myFile = new File(filename);
		Scanner myInput = null;
		try {
			myInput = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			System.out.println(filename + ": Input file can not be found!\nExiting program...");
			System.exit(1);
		}

		// getting row and column from the file
		String line = myInput.nextLine();
		String[] rowCollumn = line.split(" ");
		int column = Integer.parseInt(rowCollumn[0].trim());
		int row = Integer.parseInt(rowCollumn[1].trim());

		int[][] worldTypes = new int[row][column];

		// getting world types coordinate by coordinate
		int c = 1;
		while (myInput.hasNext()) {
			line = myInput.nextLine();
			String[] parts = line.split(";"); // split according to ";"

			for (int i = 0; i < parts.length; i++) {

				int worldType = Integer.parseInt(parts[i].trim());
				worldTypes[c - 1][i] = worldType; // assigning world types according to coordinates

			}
			c++;
		}

		// creating canvas
		int worldRowSize = row;
		int worldColumnSize = column;
		int canvasSizeMultiplier = 20; // Canvas Size Multiplier

		StdDraw.setCanvasSize(worldColumnSize * canvasSizeMultiplier, worldRowSize * canvasSizeMultiplier);
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, column);
		StdDraw.setYscale(row, 0);

		// background
		StdDraw.setPenColor(StdDraw.GRAY);
		int max = Math.max(row, column);
		StdDraw.filledSquare(column / 2 + 1, row / 2 + 1, max / 2 + 1);

		// coloring by world type
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {

				if (worldTypes[i][j] == 0) //space
					StdDraw.setPenColor(StdDraw.WHITE);
				else if (worldTypes[i][j] == 1) //wall
					StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				else if (worldTypes[i][j] == 2) //water
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				else if (worldTypes[i][j] == 3) //food
					StdDraw.setPenColor(StdDraw.BOOK_RED);

				StdDraw.filledSquare(j + 0.5, i + 0.5, 0.45000); // draws square
			}
		}

		// random initial position for cat
		Random random = new Random();
		int catXCoordinate;
		int catYCoordinate;
		while (true) {

			catXCoordinate = random.nextInt(column);
			catYCoordinate = random.nextInt(row);

			// initial position must be space
			if (worldTypes[catYCoordinate][catXCoordinate] == 0) {
				break;
			}

		}

		// creating cat object
		Cat cat = new Cat(catXCoordinate, catYCoordinate, StdDraw.PRINCETON_ORANGE);

		// cat moves
		for (int i = 0; i < iteration; i++) {

			// clearing current cat position
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledSquare(cat.getX() + 0.5, cat.getY() + 0.5, 0.43);

			// If there is a food in one cell distance
			// cat must get food
			if (worldTypes[cat.getY()][cat.getX() - 1] == 3) {

				worldTypes[cat.getY()][cat.getX() - 1] = 0;
				cat.setX(cat.getX() - 1);
				clearFood(cat); // clear the food after is taken
				cat.setFoodCount(cat.getFoodCount() + 1);

			}

			else if (worldTypes[cat.getY()][cat.getX() + 1] == 3) {

				worldTypes[cat.getY()][cat.getX() + 1] = 0;
				cat.setX(cat.getX() + 1);
				clearFood(cat); // clear the food after is taken
				cat.setFoodCount(cat.getFoodCount() + 1);
			}

			else if (worldTypes[cat.getY() + 1][cat.getX()] == 3) {

				worldTypes[cat.getY() + 1][cat.getX()] = 0;
				cat.setY(cat.getY() + 1);
				clearFood(cat); // clear the food after is taken
				cat.setFoodCount(cat.getFoodCount() + 1);
			}

			else if (worldTypes[cat.getY() - 1][cat.getX()] == 3) {

				worldTypes[cat.getY() - 1][cat.getX()] = 0;
				cat.setY(cat.getY() - 1);
				clearFood(cat); // clear the food after is taken
				cat.setFoodCount(cat.getFoodCount() + 1);
			}

			// If there is no food in one distance
			// cat moves another cell which must be space
			else {

				int direction = random.nextInt(4);

				if ((direction == 0) && (worldTypes[cat.getY()][cat.getX() - 1] == 0)) // left
					cat.setX(cat.getX() - 1);

				else if ((direction == 1) && (worldTypes[cat.getY()][cat.getX() + 1] == 0)) // right
					cat.setX(cat.getX() + 1);

				else if ((direction == 2) && (worldTypes[cat.getY() + 1][cat.getX()] == 0)) // down
					cat.setY(cat.getY() + 1);

				else if ((direction == 3) && (worldTypes[cat.getY() - 1][cat.getX()] == 0)) // up
					cat.setY(cat.getY() - 1);
			}

			// after moving 1 cell
			// drawing circle again
			cat.draw();
			StdDraw.show(); // show every move in canvas
		}

		// Writing result to the canvas
		Font font = new Font("Arial", Font.BOLD, (row + column) / 2);
		StdDraw.setFont(font);
		String message1 = String.valueOf(iteration) + " Moves";
		String message2 = "Cat has eaten " + String.valueOf(cat.getFoodCount()) + " foods";
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(column / 2, row / 2 + row / 10, message1);
		StdDraw.text(column / 2, row / 2, message2);
		StdDraw.show();

		StdDraw.save("sample.png"); // Saving last image of canvas

	}

	/**
	 * Clears food's square after the cat takes
	 * 
	 * @param cat Cat object
	 */
	public static void clearFood(Cat cat) {

		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledSquare(cat.getX() + 0.5, cat.getY() + 0.5, 0.455);

	}
}
