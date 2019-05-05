import java.awt.Color;

/**
 * 
 * @author Can Bi
 * @since 28.03.2019
 *
 * Cat Class
 */
public class Cat {

	private int x;
	private int y;
	private Color color;
	private int foodCount;

	/**
	 * Constructor for the cat
	 * 
	 * @param x x location
	 * @param y y location
	 * @param c color of the cat
	 */
	Cat(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	/**
	 * Draw the cat on the canvas
	 */
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(x + 0.5, y + 0.5, 0.4);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getFoodCount() {
		return foodCount;
	}

	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
}