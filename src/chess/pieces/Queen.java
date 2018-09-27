package chess.pieces;

public class Queen extends Piece {
	public Queen(int x, int y, char side) {
		super(x, y, side);
	}

	@Override
	public char toChar() {
		return 'q';
	}

	@Override
	boolean isMoveAllowed(Piece moveToPiece, Piece[][] board) {
		int y1 = moveToPiece.getY();
		int displaceY = Math.abs(y1 - this.getY());
		int x1 = moveToPiece.getX();
		int displaceX = Math.abs(x1 - this.getX());

		int directionX;
		int directionY;

		if (displaceX == displaceY) { // if it is moving diagonally
			if (y1 > this.getY()) {
				directionY = 1; // up
			} else {
				directionY = -1; // down
			}

			if (x1 > this.getX()) {
				directionX = 1; // right
			} else {
				directionX = -1; // left
			}
		} else if (displaceX == 0) { // moving vertically
			directionX = 0;
			if (y1 > this.getY()) {
				directionY = 1; // down
			} else {
				directionY = -1; // up
			}
		} else if (displaceY == 0) { // moving horizontally
			directionY = 0;
			if (x1 > this.getX()) {
				directionX = 1; // right
			} else {
				directionX = -1; // left
			}
		} else {
			// it is moving neither diagonally nor straight
			// in this case we want the program to do nothing
			return false;
		}

		int x = this.getX() + directionX; // currentX + directionX
		int y = this.getY() + directionY; // currentY + directionY

		while (x != x1 || y != y1) {  // end if x==x1 && y==y1 (De Morgan's law)
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
