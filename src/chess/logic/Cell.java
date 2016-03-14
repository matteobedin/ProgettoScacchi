package chess.logic;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * La classe Casella estende JButton e costituisce una casella
 * della scacchiera. E' dotata di un colore, un'eventuale pedina
 * corredata da immagine, e puo' essere selezionata al clic
 * del mouse. Ogni casella, come prevede il gioco degli scacchi,
 * può essere minacciata dalle pedine dei giocatori.
 */

public class Cell extends JButton {
	private static final long serialVersionUID = -4925019709314475589L;

	private int row; // riga
	private int column; // colonna

	private int whiteAlert; // 0 se la casella non e' minacciata, 1 in caso
							// contrario
	private int blackAlert; // 0 se la casella non e' minacciata, 1 in caso
							// contrario

	

	private String pedina; // nome della pedina
	private int colorP; // colore della pedina

	private int isSelected = 0; // 0 se questa casella non è selezionata, 1 se
								// e' selezionata
	private static int selected = 0; // 0 se non c'e' alcuna casella selezionata
										// nella scacchiera, 1 in caso contrario

	private ImageIcon image; // immagine della pedina
	private int color; // colore della casella

	public Cell(int x, int y, int color) {
		this.row = x;
		this.column = y;
		this.color = color;
	}

	public int getWhiteAlert() {
		return whiteAlert;
	}


	public void setWhiteAlert(int whiteAlert) {
		this.whiteAlert = whiteAlert;
	}


	public int getBlackAlert() {
		return blackAlert;
	}


	public void setBlackAlert(int blackAlert) {
		this.blackAlert = blackAlert;
	}


	public int getRow() {
		return row;
	}


	public int getColumn() {
		return column;
	}


	public ImageIcon getImage() {
		return image;
	}
	
	public boolean selected() {
		return selected == 1;
	}

	public ImageIcon getImageP() {
		return image;
	}

	public boolean thisSelected() {
		
		return isSelected == 1;
	}

	public int getColorP() {
		return colorP;
	}

	/*
	 * Ritorna vero e setta lo sfondo di colore rosso se nella casella e'
	 * presente un re bianco e la casella e' minacciata dai neri, altrimenti
	 * ritorna falso
	 */
	public boolean scaccoBianchi() {
		if ((pedina.equals("Re") && colorP == 0 && blackAlert == 1)) {
			this.setBackground(Color.RED);
			return true;
		} else if (isSelected == 1)
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
		if ((pedina.equals("Re") && colorP == 1 && whiteAlert == 1)) {
			this.setBackground(Color.RED);
			return true;
		} else if (isSelected == 1)
			this.setBackground(Color.GREEN);
		else
			this.setBackground(color == 0 ? Color.WHITE : Color.LIGHT_GRAY);
		return false;
	}

	public boolean isEmpty() {
		if (pedina.equals(""))
			return true;
		else
			return false;
	}

	// Ripristina i colori di sfondo una volta deselezionata la casella
	public void thisDeSelect() {
		isSelected = 0;
		this.setBackground(color == 0 ? Color.WHITE : Color.LIGHT_GRAY);
	}

	// Imposta lo sfondo di colore verde per le caselle di destinazione legali
	public void select() {
		selected = 1;
		isSelected = 1;
		this.setBackground(Color.GREEN);
	}

	// Deseleziona la casella e azzera selected
	public void deSelect() {
		selected = 0;
		isSelected = 0;
		this.setBackground(color == 0 ? Color.WHITE : Color.LIGHT_GRAY);
	}

	public int getColor() {
		return color;
	}

	public int getSelected() {
		return selected;
	}

	public String getPedina() {
		return pedina;
	}

	public void setPedina(String pedina, int colorePedina, ImageIcon image) {
		this.pedina = pedina;
		this.colorP = colorePedina;
		this.image = image;
		setIcon(image);
	}
}