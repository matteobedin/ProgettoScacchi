package logic;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * La classe Casella estende JButton e costituisce una casella
 * della scacchiera. E' dotata di un colore, un'eventuale pedina
 * corredata da immagine, e puo' essere selezionata al clic
 * del mouse. Ogni casella, come prevede il gioco degli scacchi,
 * pu� essere minacciata dalle pedine dei giocatori.
 */

public class Casella extends JButton {
	private int row; // riga
	private int column; // colonna
	private int color; // colore della casella
	private ImageIcon image; // immagine della pedina
	private String namep; // nome della pedina
	private int colorp; // colore della pedina
	private boolean isSelected = false; // false se questa casella non �
										// selezionata, true se e' selezionata
	private static boolean selection = false; // false se non c'e' alcuna
												// casella selezionata nella
												// scacchiera, true in caso
												// contrario

	private boolean whiteAlert; // false se la casella non e' minacciata, true
								// in caso contrario
	private boolean blackAlert; // false se la casella non e' minacciata, true
								// in caso contrario

	public Casella(int r, int c, int color) {
		this.row = r;
		this.column = c;
		this.color = color;
	}

	public void setWhiteAlert(boolean alert) {
		whiteAlert = alert;
	}

	public void setBlackAlert(boolean alert) {
		blackAlert = alert;
	}

	public boolean isWhiteAlert() {
		return whiteAlert;
	}

	public boolean isBlackAlert() {
		return blackAlert;
	}

	public int getColorP() {
		return colorp;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public boolean selected() {
		if (selection)
			return true;
		return false;
	}

	public ImageIcon getImagePedina() {
		return image;
	}

	public boolean thisSelected() {
		if (isSelected)
			return true;
		return false;
	}

	/*
	 * Ritorna vero e setta lo sfondo di colore rosso se nella casella e'
	 * presente un re bianco e la casella e' minacciata dai neri, altrimenti
	 * ritorna falso
	 */
	public boolean scaccoBianchi() {
		if ((namep.equals("Re") && colorp == 0 && blackAlert)) {
			this.setBackground(Color.RED);
			return true;
		} else if (isSelected)
			this.setBackground(Color.GREEN);
		else
			this.setBackground(color == 0 ? Color.WHITE : Color.LIGHT_GRAY);
		return false;
	}

	/*
	 * Ritorna vero e setta lo sfondo di colore rosso se nella casella e'
	 * presente un re nero e la casella e' minacciata dai bianchi, altrimenti
	 * ritorna falso
	 */
	public boolean scaccoNeri() {
		if ((namep.equals("Re") && colorp == 1 && whiteAlert)) {
			this.setBackground(Color.RED);
			return true;
		} else if (isSelected)
			this.setBackground(Color.GREEN);
		else
			this.setBackground(color == 0 ? Color.WHITE : Color.LIGHT_GRAY);
		return false;
	}

	public boolean isEmpty() {
		if (namep.equals(""))
			return true;
		else
			return false;
	}

	// Ripristina i colori di sfondo una volta deselezionata la casella
	public void thisDeSelect() {
		isSelected = false;
		this.setBackground(color == 0 ? Color.WHITE : Color.LIGHT_GRAY);
	}

	// Imposta lo sfondo di colore verde per le caselle di destinazione legali
	public void select() {
		selection = true;
		isSelected = true;
		this.setBackground(Color.GREEN);
	}

	// Deseleziona la casella e azzera selected
	public void deSelect() {
		selection = false;
		isSelected = false;
		this.setBackground(color == 0 ? Color.WHITE : Color.LIGHT_GRAY);
	}

	public int getColor() {
		return color;
	}

	public boolean isSelected() {
		return selection;
	}

	public String getPedina() {
		return namep;
	}

	public void setPedina(String pedina, int colorePedina, ImageIcon image) {
		this.namep = pedina;
		this.colorp = colorePedina;
		this.image = image;
		setIcon(image);
	}
}