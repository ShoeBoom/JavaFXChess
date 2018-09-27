package chess;

import chess.pieces.*;
import chess.utils.Side;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ChessGameController {
	@FXML
	private Label title;
	@FXML
	private GridPane gridPane;

	@FXML
	private Label whiteWinsCounter;
	@FXML
	private Label blackWinsCounter;

	private int whiteWins;
	private int blackWins;
	private Chess chess;

	@FXML
	private void initialize() {
		gridPane.maxWidthProperty().bind(gridPane.heightProperty());
		chess = new Chess(gridPane);

		setUpSquares();

	}

	@FXML
	private void reset() {
		blackWinsCounter.setText(String.valueOf(blackWins));
		whiteWinsCounter.setText(String.valueOf(whiteWins));

		title.setText("Chess");
		gridPane.getChildren().clear();
		chess.reset();
		setUpSquares();
	}

	/**
	 * setup the pieces on the board
	 */
	private void setUpSquares() {
		gridPane.getChildren().clear();
		for (int x = 0; x < 8; x++) {
			for (int y = 2; y < 6; y++) {
				addCell(new BlankPiece(x, y));
			}
		}

		addCell(new Rook(0, 7, 'w'));
		addCell(new Horse(1, 7, 'w'));
		addCell(new Bishop(2, 7, 'w'));
		addCell(new Queen(3, 7, 'w'));
		addCell(new King(4, 7, 'w'));
		addCell(new Bishop(5, 7, 'w'));
		addCell(new Horse(6, 7, 'w'));
		addCell(new Rook(7, 7, 'w'));

		for (int i = 0; i < 8; i++) {
			addCell(new Pawn(i, 6, 'w'));
		}

		for (int i = 0; i < 8; i++) {
			addCell(new Pawn(i, 1, 'b'));
		}

		addCell(new Rook(0, 0, 'b'));
		addCell(new Horse(1, 0, 'b'));
		addCell(new Bishop(2, 0, 'b'));
		addCell(new King(4, 0, 'b'));
		addCell(new Queen(3, 0, 'b'));
		addCell(new Bishop(5, 0, 'b'));
		addCell(new Horse(6, 0, 'b'));
		addCell(new Rook(7, 0, 'b'));
	}

	public void addCell(Piece piece) {
		setAction(piece);
		chess.addCell(piece);
	}

	private void setAction(Piece piece) {
		// if square is is a blank piece
		if (piece instanceof BlankPiece) {
			// set it to the blank piece action method
			piece.getGui().setOnMouseClicked(event -> chess.BlankPieceAction(piece));
		} else {
			piece.getGui().setOnMouseClicked(event -> {

				// run the piece action method
				chess.PieceAction(piece);

				// check if game is over and if it is over do the default action
				gameOverCheck();

				// if piece is a pawn and is at the end of the board
				if (chess.isPawnSwapValid(piece)) {

					// remove the pawn from the gui
					gridPane.getChildren().remove(piece.getGui());

					// add a queen with the arguments of the pawn.
					addCell(
							new Queen(piece.getX(),
									piece.getY(),
									piece.getSide().toChar())
					);
				}
			});
		}
	}


	private void gameOverCheck() {
		if (chess.isGameOver()) { // get game over state from the Chess class
			setGameOver(chess.getCurrentPlayer()); // run actions that need to be run when the game is over
		}
	}


	private void setGameOver(Side kingSide) {
		String winnerString = "Winner: "; // constant
		if (kingSide.equals('b')) { // if the king destroyed was black
			whiteWins++; // increment white wins
			winnerString += "White"; // add white to winner string
		} else { // if the king destroyed was black
			blackWins++; // increment black wins
			winnerString += "Black"; // add black to winner string
		}
		title.setText(winnerString); // set the title text
}
}
