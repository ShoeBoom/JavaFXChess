package chess.pieces;

public class King extends Piece {


	public King(int x, int y, char side) {
		super(x, y, side);
	}

	@Override
	public char toChar() {
		return 'k';
	}

	@Override
	boolean isMoveAllowed(Piece moveToPiece, Piece[][] board) {

		int displaceY = Math.abs(moveToPiece.getY() - this.getY());
		int displaceX = Math.abs(moveToPiece.getX() - this.getX());

		return displaceX <= 1 && displaceY <= 1;
	}

	public boolean isCastlingValid(Piece moveToPiece, Piece[][] board) {
		if (moveToPiece instanceof Rook
				&& moveToPiece.getY() == this.getY()
				&& !moveToPiece.hasMoved() && !this.hasMoved()
				&& this.getSide().equals(moveToPiece.getSide())) {

			int x1 = moveToPiece.getX();

			int directionX;
			if (x1 < this.getX()) {
				directionX = -1; // it is going left
			} else {
				directionX = 1; // it is going right
			}

			int x = this.getX() + directionX;

			while (x != x1) {
				if (!(board[x][this.getY()] instanceof BlankPiece)) {
					return false;
				}
				x += directionX;
			}

			return true;

		} else {
			return false;
		}
	}
}
