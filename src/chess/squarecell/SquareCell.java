package chess.squarecell;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Custom component for chess pieces
 *
 * The class also stores the set piece as a character:
 * 'k' is king
 * 'q' is queen
 * 'p' is pawn
 * 'r' is rook
 * 'b' is bishop
 * 'h' is knight/horse
 */
public class SquareCell extends Pane {

	private static final String IMAGE_DIRECTORY = "res/";

	@FXML
	private ImageView imageView; // imageView for the piece image

	private char piece; // character to store the character indicating the piece

	public SquareCell() {
		// load FXML file
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SquareCell.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		// bind image view to always be a square
		imageView.fitHeightProperty().bind(this.heightProperty());
		imageView.fitWidthProperty().bind(this.widthProperty());
	}

	/**
	 * Used to turn on and off highlighting
	 *
	 * @param highlight if true the gui is highlighted. If false highlighting is turned off.
	 */
	public void setHighlight(boolean highlight) {
		if (highlight) {
			this.setStyle("-fx-background-color: rgba(255,255,0,0.42);"); // set the style using css
		} else {
			this.setStyle("-fx-background-color: transparent;"); // make the background transparent using css
		}
	}

	/**
	 * Used to set the image of the cell
	 *
	 * @param piece represents the first character of the pieces.
	 *
	 * @param side the side the piece is on. 'w' is white and 'b' is black
	 */
	public void setPiece(char piece, char side) {
		String colour; // string to store the colour in full text. Needed to navigate to image

		// set the colour string and check if the side entered is valid
		switch (side) {
			case 0:
				colour = "";
				break;
			case 'w':
				colour = "white";
				break;
			case 'b':
				colour = "black";
				break;
			default:
				throw new IllegalArgumentException("Player number must be either 'w' or 'b'");
		}

		// the image path can be obtained using  IMAGE_DIRECTORY + colour + "_{Piece type eg. "king", "pawn", etc. }.png"

		// set the image
		switch (piece) {
			case 0:
				changeImage((Image) null); // set imageView to null
				// for debugging
//				this.setStyle("-fx-background-color: rgba(255,6,0,0.42);");
				break;
			case 'k':
				changeImage(IMAGE_DIRECTORY + colour + "_king.png");
				break;
			case 'q':
				changeImage(IMAGE_DIRECTORY + colour + "_queen.png");
				break;
			case 'p':
				changeImage(IMAGE_DIRECTORY + colour + "_pawn.png");
				break;
			case 'r':
				changeImage(IMAGE_DIRECTORY + colour + "_rook.png");
				break;
			case 'b':
				changeImage(IMAGE_DIRECTORY + colour + "_bishop.png");
				break;
			case 'h':
				changeImage(IMAGE_DIRECTORY + colour + "_horse.png");
				break;
			default:
				// throw error since the character is invalid
				throw new IllegalArgumentException("Piece character must be k, q, p, r, b, or h");
		}
		this.piece = piece; // set the piece field
	}

	/**
	 * Resets the image
	 */
	public void reset() {
		setPiece((char) 0, (char) 0);
		setHighlight(false);
	}

	private void changeImage(Image image) {
		imageView.setImage(image);
	}

	/**
	 * sets the image to an image located at the path
	 *
	 * @param path path to the image
	 */
	private void changeImage(String path) {
		changeImage(new Image(getClass().getResource(path).toString(), true));
	}


	/**
	 * @return the character from the piece
	 */
	public char getPiece() {
		return piece;
	}
}
