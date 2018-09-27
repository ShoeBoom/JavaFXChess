package chess.pieces;

public class Bishop extends Piece {

	public Bishop(int x, int y, char side) {
		super(x, y, side);
	}

	@Override
	public char toChar() {
		return 'b';
	}

	@Override
	boolean isMoveAllowed(Piece moveToPiece, Piece[][] board) {

		int y1 = moveToPiece.getY(); // move to piece y
		int displaceY = Math.abs(y1 - this.getY());

		int x1 = moveToPiece.getX(); // move to piece x
		int displaceX = Math.abs(x1 - this.getX());

		if (displaceX != displaceY) {
			// if it is not moving diagonally we want the program to do nothing.
			return false;
		}

		int directionX;
		int directionY;

		if (y1 > this.getY()) {
			directionY = 1; // moving down
		} else {
			directionY = -1; // moving up
		}

		if (x1 > this.getX()) {
			directionX = 1; // moving right
		} else {
			directionX = -1; // moving left
		}

		int x = this.getX() + directionX; // currentX + directionX
		int y = this.getY() + directionY; // currentY + directionY

		while (x != x1 || y != y1) { // end if x==x1 && y==y1 (De Morgan's law)
			if (!(board[x][y] instanceof BlankPiece)) {
				// if non-blank piece is found in the middle we want it to return false
				return false;
			}

			// iterate direction
			x += directionX;
			y += directionY;
		}
		// if all pieces in the middle are blank
		return true;
	}
}
