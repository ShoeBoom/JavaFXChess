package chess.pieces;

import chess.squarecell.SquareCell;
import chess.utils.Location;
import chess.utils.Side;

/**
 * Abstract implementation for a chess game piece.
 * <p>
 * Piece character is stored as:
 * 'k' is king
 * 'q' is queen
 * 'p' is pawn
 * 'r' is rook
 * 'b' is bishop
 * 'h' is knight/horse
 * <p>
 * Side is stored as:
 * 'b' is black
 * 'w' is white
 */
public abstract class Piece {
	private boolean hasMoved;   // set whether the piece has moved previously.
	// This is stored for pawns since they can move 2 steps on the first turn

	private SquareCell guiCell; // store the gui component of the piece
	private Location location; // set the location of the piece
	private Side side; // set the side of the piece

	public Piece(int x, int y, char side) {
		this.guiCell = new SquareCell(); // set the gui
		this.location = new Location(x, y); // set the location
		this.side = new Side(side); // set the side

		guiCell.setPiece(toChar(), this.side.toChar());
	}

	/**
	 * @return get char of the piece. Eg. 'h', 'k', etc.
	 */
	public abstract char toChar();

	/**
	 * implementation for the rules of a specific piece
	 *
	 * @param moveToPiece the piece it is trying to move to
	 * @param board       the board with all the pieces
	 * @return true if the move is valid
	 */
	abstract boolean isMoveAllowed(Piece moveToPiece, Piece[][] board);

	/**
	 * @param moveToPiece the piece it is trying to move to
	 * @param board       the board with all the pieces
	 * @return true if the move is valid
	 */
	public boolean isMoveValid(Piece moveToPiece, Piece[][] board) {
		if (moveToPiece.side.equals(side)) {
			return false; // if the pieces are on the same side do nothing
		}
		return isMoveAllowed(moveToPiece, board); // check piece specific rules
	}

	/**
	 * @return true if the piece has moved
	 */
	boolean hasMoved() {
		return hasMoved;
	}

	/**
	 * @return {side character}{piece character}
	 * eg. "bp" is black pawn
	 */
	@Override
	public String toString() {
		return String.valueOf(side.toChar()) + toChar(); // returns the string
	}

	/**
	 * @return side of the piece
	 */
	public Side getSide() {
		return side;
	}

	/**
	 * @return gui component of the piece
	 */
	public SquareCell getGui() {
		return guiCell;
	}

	/**
	 * @return the x coordinate of the piece
	 */
	public int getX() {
		return location.getX();
	}

	/**
	 * @param x the new x coordinate of the piece
	 */
	public void setX(int x) {
		location.setX(x);
	}

	/**
	 * @return the y coordinate of the piece
	 */
	public int getY() { // get the y coordinate of the piece
		return location.getY();
	}

	/**
	 * @param y the new x coordinate of the piece
	 */
	public void setY(int y) {
		location.setY(y);
	}

	/**
	 * @return true if the piece is black
	 */
	boolean isBlack() {
		return side.isBlack();
	}

	/**
	 * @return true if the piece is white
	 */
	boolean isWhite() {
		return side.isWhite();
	}

	/**
	 * @return location of the piece
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location new location to be assigned to piece
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * set to true if the piece has moved.
	 */
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
}
