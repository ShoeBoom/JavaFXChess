package chess.utils;

public class Location {
	private int x; // store x coordinate
	private int y; // store y coordinate

	/**
	 * Constructor
	 *
	 * @param x: x coordinate of the location
	 * @param y: y coordinate of the location
	 */
	public Location(int x, int y) {
		// check if the coordinate is valid
		if (y < 0 || y > 7 || x < 0 || x > 7) {
			throw new RuntimeException("Invalid Location");
		}

		this.x = x; // set x
		this.y = y; // set y
	}

	/**
	 * @return [{x}, {y}]
	 */
	@Override
	public String toString() {
		return "[" + x + ", " + y + ']';
	}

	/**
	 * @return x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x: new x coordinate of the location
	 */
	public void setX(int x) {
		// check if the coordinate is valid
		if (x < 0 || x > 7) {
			throw new RuntimeException("Invalid Location");
		}
		this.x = x; // set x
	}

	/**
	 * @return y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y: new y coordinate of the location
	 */
	public void setY(int y) {
		// check if the coordinate is valid
		if (y < 0 || y > 7) {
			throw new RuntimeException("Invalid Location");
		}
		this.y = y; // set y
	}
}
