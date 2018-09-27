package chess.pieces;

public class Horse extends Piece {

	public Horse(int x, int y, char side) {
		super(x, y, side);
	}

	@Override
	boolean isMoveAllowed(Piece moveToPiece, Piece[][] board) {
		int displaceY = Math.abs(moveToPiece.getY() - this.getY());
		int displaceX = Math.abs(moveToPiece.getX() - this.getX());

		// knight or horse must move in a 2 by 1 displacement
		return (displaceX == 2 && displaceY == 1)
				|| (displaceY == 2 && displaceX == 1);
	}

	@Override
	public char toChar() {
		return 'h';
	}
}
