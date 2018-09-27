package chess;

import chess.pieces.BlankPiece;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.utils.Side;
import javafx.scene.layout.GridPane;

public class Chess {
	private GridPane gridPane;
	private Piece[][] grid; // grid[row][column];
	private boolean selected;
	private Piece selectedPiece;
	private Side currentSide;
	private boolean isGameOver;

	public Chess(GridPane gridPane) {
		this.gridPane = gridPane;
		this.gridPane.maxWidthProperty().bind(this.gridPane.heightProperty());
		this.grid = new Piece[8][8];
		this.currentSide = new Side('w');
	}

	Side getCurrentPlayer() {
		return currentSide;
	}

	boolean isGameOver() {
		return isGameOver;
	}

	private boolean isMoveValid(Piece piece) {
		return selectedPiece.isMoveValid(piece, grid);
	}


	private void printBoard() {
		for (int j = 0; j < 8; j++) {
			System.out.print("\n|");
			for (int i = 0; i < 8; i++) {
				System.out.print(grid[i][j] + "\t|");
			}
		}
		System.out.println();
	}

	void reset() {
		this.grid = new Piece[8][8];
		selected = false;
		Piece selectedPiece = null;
		isGameOver = false;
		currentSide = new Side('w');
	}

	void addCell(Piece piece) {
		grid[piece.getX()][piece.getY()] = piece;
		gridPane.add(piece.getGui(), piece.getX(), piece.getY());
	}

	private void movePiece(Piece pieceMoveTo) {
		movePiece(selectedPiece, pieceMoveTo);
	}

	private void movePiece(Piece pieceMoveFrom, Piece pieceMoveTo) {
		if (pieceMoveTo instanceof King) {
			isGameOver = true;
		}

		pieceMoveFrom.setHasMoved(true);

		// remove from GUI
		gridPane.getChildren().remove(pieceMoveFrom.getGui());

		// add black cell the the origin
		BlankPiece blankPiece = new BlankPiece(
				pieceMoveFrom.getX(),
				pieceMoveFrom.getY());
		blankPiece.getGui().setOnMouseClicked(event -> BlankPieceAction(blankPiece));
		addCell(blankPiece);

		pieceMoveFrom.setLocation(pieceMoveTo.getLocation());

		gridPane.getChildren().remove(pieceMoveTo.getGui());

		addCell(pieceMoveFrom);
	}

	private void Castling(Piece king, Piece rook) {
		king.setHasMoved(true);
		rook.setHasMoved(true);

		// remove from GUI
		gridPane.getChildren().remove(king.getGui());

		BlankPiece blankPiece = new BlankPiece(king.getX(), king.getY());
		blankPiece.getGui().setOnMouseClicked(event -> BlankPieceAction(blankPiece));
		addCell(blankPiece);

		gridPane.getChildren().remove(rook.getGui());
		BlankPiece blankPiece1 = new BlankPiece(rook.getX(), rook.getY());
		blankPiece1.getGui().setOnMouseClicked(event -> BlankPieceAction(blankPiece1));
		addCell(blankPiece1);

		if (king.getX() > rook.getX()) {
			king.setX(king.getX() - 2);
			rook.setX(king.getX() + 1);
		} else {
			king.setX(king.getX() + 2);
			rook.setX(king.getX() - 1);
		}

		gridPane.getChildren().remove(grid[king.getX()][king.getY()].getGui());
		gridPane.getChildren().remove(grid[rook.getX()][rook.getY()].getGui());

		addCell(rook);
		addCell(king);
	}


	void PieceAction(Piece piece) {
		if (selected && isCastlingValid(piece) && piece.getSide().equals(currentSide)) {
			if (piece instanceof King) { // if piece is king
				Castling(piece, selectedPiece); // king is the first param
			} else {
				Castling(selectedPiece, piece); // if selected piece is king
			}
			printBoard();
			// switch sides and deselect the piece
			switchPlayer();
			deselectPiece();
		} else if (!selected && piece.getSide().equals(currentSide)) {
			// if nothing is selected and the correct side is clicked
			selectPiece(piece);
		} else if (piece.getSide().equals(currentSide)) {
			// if the selected piece and clicked piece are on the same side

			// deselect and select current piece
			deselectPiece();
			selectPiece(piece);
		} else if (selected && isMoveValid(piece)) { // if it is a different side and the move is valid
			movePiece(piece);
			printBoard();
			switchPlayer();
			deselectPiece();
		} else {
			System.out.println("Invalid input");
		}
	}

	private boolean isCastlingValid(Piece piece) {
		return (selectedPiece instanceof King && ((King) selectedPiece).isCastlingValid(piece, grid))
				|| (piece instanceof King && ((King) piece).isCastlingValid(selectedPiece, grid));
	}

	void BlankPieceAction(Piece piece) {
		if (selected && isMoveValid(piece)) {
			movePiece(piece);
			printBoard();
			switchPlayer();
			deselectPiece();
		} else {
			System.out.println("Invalid input");
		}
	}

	Piece getSelectedPiece() {
		return selectedPiece;
	}

	static boolean isPawnSwapValid(Piece piece) {
		return piece instanceof Pawn && (piece.getY() == 0 || piece.getY() == 7);
	}


	private void selectPiece(Piece piece) {
		if (!isGameOver) {
			piece.getGui().setHighlight(true);
			selectedPiece = piece;
			selected = true;
		}
	}

	private void deselectPiece() {
		selectedPiece.getGui().setHighlight(false);
		selected = false;
		selectedPiece = null;
	}

	private void switchPlayer() {
		if (currentSide.equals('w')) {
			currentSide = new Side('b');
		} else if (currentSide.equals('b')) {
			currentSide = new Side('w');
		}
	}
}
