package chess.pieces;

public class Rook extends Piece {

	public Rook(int x, int y, char side) {
		super(x, y, side);
	}

	@Override
	public char toChar() {
		return 'r';
	}

	@Override
	boolean isMoveAllowed(Piece moveToPiece, Piece[][] board) {
		int y1 = moveToPiece.getY();
		int displaceY = Math.abs(y1 - this.getY());
		int x1 = moveToPiece.getX();
		int displaceX = Math.abs(x1 - this.getX());

		int directionX;
		int directionY;

		if (displaceX == 0) { // moving vertically
			if (y1 > this.getY()) {
				directionY = 1; // down
			} else {
				directionY = -1; // up
			}
			directionX = 0;
		} else if (displaceY == 0) { // moving horizontally
			if (x1 > this.getX()) {
				directionX = 1; // right
			} else {
				directionX = -1; // left
			}
			directionY = 0;
		} else {
			return false; // if neither of the displacement are 0
		}

		int x = this.getX() + directionX; // currentX + directionX
		int y = this.getY() + directionY; // currentY + directionY

		while (x != x1 || y != y1) { // end if x==x1 && y==y1 (De Morgan's law)
			if (!(board[x][y] instanceof BlankPiece)) {
				// if non blank piece is found in the middle
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
