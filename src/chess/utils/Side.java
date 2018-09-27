package chess.utils;

public class Side {

	private final char side; // unchangeable side using char

	/**
	 * @param side: side of piece. Must be 0, 'b' or 'w'.
	 */
	public Side(char side) {
		if (side != 0 && side != 'b' && side != 'w') {
			throw new RuntimeException("Invalid Side Argument. Must be either 0, 'b' or 'w'");
		}

		this.side = side;

	}

	/**
	 * @return true if piece is on side black, false otherwise
	 */
	public boolean isBlack() {
		return side == 'b';
	}

	/**
	 * @return true if piece is on side white, false otherwise
	 */
	public boolean isWhite() {
		return side == 'w';
	}

	/**
	 * @param side: Side object to compare to
	 * @return if Side object is equal
	 */
	public boolean equals(Side side) {
		return this.toChar() == side.toChar();
	}

	/**
	 * @param side: char primitive to compare to
	 * @return if object is equal to char
	 */
	public boolean equals(char side) {
		return this.toChar() == side;
	}

	/**
	 * @return return the side as a char
	 *          'b' for black
	 *          'w' for white
	 *          0 is a placeholder
	 */
	public char toChar() {
		return side;
	}
}
