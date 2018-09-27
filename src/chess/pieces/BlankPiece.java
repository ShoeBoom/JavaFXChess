package chess.pieces;

public class BlankPiece extends Piece {

	public BlankPiece(int x, int y) {
		super(x, y, (char) 0);
	}

	@Override
	public String toString() {
		return " ";
	}

	@Override
	public char toChar() {
		return 0;
	}

	@Override
	boolean isMoveAllowed(Piece moveToPiece, Piece[][] board) {
		return false;
	}
}
