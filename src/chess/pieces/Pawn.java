package chess.pieces;

public class Pawn extends Piece {

	public Pawn(int x, int y, char side) {
		super(x, y, side);
	}

	@Override
	public char toChar() {
		return 'p';
	}

	private static boolean isOneStepMoveValid(Piece moveToPiece, int displaceX, int displaceY) {
		return moveToPiece instanceof BlankPiece
				&& displaceY == 1
				&& displaceX == 0;
	}

	private static boolean isCounterValid(Piece moveToPiece, int displaceX, int displaceY) {
		return !(moveToPiece instanceof BlankPiece)
				&& displaceX == 1
				&& displaceY == 1;
	}

	private boolean isTwoStepMoveValid(Piece moveToPiece, Piece[][] board, int displaceX, int displaceY) {
		int direction;

		if (this.isWhite()) {
			direction = -1;
		} else {
			direction = 1;
		}

		return moveToPiece instanceof BlankPiece
				&& board[this.getX()][this.getY() + direction] instanceof BlankPiece
				&& displaceY == 2
				&& displaceX == 0
				&& !hasMoved();
	}

	@Override
	boolean isMoveAllowed(Piece moveToPiece, Piece[][] board) {
		if (isMovingForward(moveToPiece)) {
			int displaceY = Math.abs(moveToPiece.getY() - this.getY());
			int displaceX = Math.abs(moveToPiece.getX() - this.getX());

			return isCounterValid(moveToPiece, displaceX, displaceY)
					|| isOneStepMoveValid(moveToPiece, displaceX, displaceY)
					|| isTwoStepMoveValid(moveToPiece, board, displaceX, displaceY);
		}
		return false;
	}

	private boolean isMovingForward(Piece moveToPiece) {
		return this.isWhite() && moveToPiece.getY() < this.getY() // if it is white the new y must be lower
				|| this.isBlack() && moveToPiece.getY() > this.getY(); // if it is black the new y must be higher
	}


}
