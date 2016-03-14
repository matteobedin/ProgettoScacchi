package chess.logic;

import chess.graphic.ChessboardFrame;
import chess.graphic.EndMessage;

public class Controller {
	ChessboardFrame frame;
	private Cell[][] caselle = null;
	Cell start;
	int turn = 0;

	public Controller(Cell[][] caselle, ChessboardFrame frame) {
		this.frame = frame;
		this.caselle = caselle;
	}

	public void setCaselle(Cell[][] caselle) {
		this.caselle = caselle;
	}

	public void azzeraStart() {
		start = null;
	}

	public void azzeraTurn() {
		turn = 0;
	}

	private void unSetMinacce() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				caselle[i][j].setWhiteAlert(0);
				caselle[i][j].setBlackAlert(0);
			}
	}

	private void setAlerts() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (caselle[i][j].getPedina().equals("Pedone"))
					setAlertPedone(caselle[i][j]);
				if (caselle[i][j].getPedina().equals("Torre"))
					setAlertsTorre(caselle[i][j]);
				if (caselle[i][j].getPedina().equals("Alfiere"))
					setAlertsAlfiere(caselle[i][j]);
				if (caselle[i][j].getPedina().equals("Cavallo"))
					setAlertsCavallo(caselle[i][j]);
				if (caselle[i][j].getPedina().equals("Regina")) {
					setAlertsTorre(caselle[i][j]);
					setAlertsAlfiere(caselle[i][j]);
				}
				if (caselle[i][j].getPedina().equals("Re"))
					setMinacceRe(caselle[i][j]);
			}
	}

	/*
	 * Setta le minacce delle caselle di destinazione legali per il pedone
	 */
	private void setAlertPedone(Cell daControllare) {
		if (daControllare.getColorP() == 0 && daControllare.getRow() > 0) {
			if (daControllare.getColumn() > 0)
				if (caselle[daControllare.getRow() - 1][daControllare
						.getColumn() - 1].isEmpty()
						|| caselle[daControllare.getRow() - 1][daControllare
								.getColumn() - 1].getColorP() == daControllare
								.getColorP()
						|| (caselle[daControllare.getRow() - 1][daControllare
								.getColumn() - 1].getColorP() != daControllare
								.getColorP() && caselle[daControllare.getRow() - 1][daControllare
								.getColumn() - 1].getPedina().equals("Re")))
					caselle[daControllare.getRow() - 1][daControllare
							.getColumn() - 1].setWhiteAlert(1);
				else
					caselle[daControllare.getRow() - 1][daControllare
							.getColumn() - 1].setWhiteAlert(0);

			if (daControllare.getColumn() < 7)
				if (caselle[daControllare.getRow() - 1][daControllare
						.getColumn() + 1].isEmpty()
						|| caselle[daControllare.getRow() - 1][daControllare
								.getColumn() + 1].getColorP() == daControllare
								.getColorP()
						|| (caselle[daControllare.getRow() - 1][daControllare
								.getColumn() + 1].getColorP() != daControllare
								.getColorP() && caselle[daControllare.getRow() - 1][daControllare
								.getColumn() + 1].getPedina().equals("Re")))
					caselle[daControllare.getRow() - 1][daControllare
							.getColumn() + 1].setWhiteAlert(1);
				else
					caselle[daControllare.getRow() - 1][daControllare
							.getColumn() + 1].setWhiteAlert(0);
		}

		if (daControllare.getColorP() == 1 && daControllare.getRow() < 7) {

			if (daControllare.getColumn() < 7)
				if (caselle[daControllare.getRow() + 1][daControllare
						.getColumn() + 1].isEmpty()
						|| caselle[daControllare.getRow() + 1][daControllare
								.getColumn() + 1].getColorP() == daControllare
								.getColorP()
						|| (caselle[daControllare.getRow() + 1][daControllare
								.getColumn() + 1].getColorP() != daControllare
								.getColorP() && caselle[daControllare.getRow() + 1][daControllare
								.getColumn() + 1].getPedina().equals("Re")))
					caselle[daControllare.getRow() + 1][daControllare
							.getColumn() + 1].setBlackAlert(1);
				else
					caselle[daControllare.getRow() + 1][daControllare
							.getColumn() + 1].setBlackAlert(0);
			if (daControllare.getColumn() > 0)
				if (caselle[daControllare.getRow() + 1][daControllare
						.getColumn() - 1].isEmpty()
						|| caselle[daControllare.getRow() + 1][daControllare
								.getColumn() - 1].getColorP() == daControllare
								.getColorP()
						|| (caselle[daControllare.getRow() + 1][daControllare
								.getColumn() - 1].getColorP() != daControllare
								.getColorP() && caselle[daControllare.getRow() + 1][daControllare
								.getColumn() - 1].getPedina().equals("Re")))
					caselle[daControllare.getRow() + 1][daControllare
							.getColumn() - 1].setBlackAlert(1);
				else
					caselle[daControllare.getRow() + 1][daControllare
							.getColumn() - 1].setBlackAlert(0);
		}
	}

	private void setAlertsTorre(Cell daControllare) {
		int i = daControllare.getRow() - 1;
		int j = daControllare.getColumn();
		// while che controlla il movimento della torre verso su
		while (i >= 0) {
			if (caselle[i][j].isEmpty()) // se la casella e' vuota setto le
											// minacce e continuo a ciclare
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);
			// se la casella non contiene un re, setto la minaccia e esco
			// dal ciclo
			else if (!caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);
				break;
			}
			// se la casella contiene il re del colore opposto alla pedina di
			// partenza, setto la minaccia sulla casella del re e quella
			// successiva
			if (caselle[i][j].getColorP() != daControllare.getColorP()
					&& caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0) {
					caselle[i][j].setWhiteAlert(1);
					if (i - 1 > 0)
						caselle[i - 1][j].setWhiteAlert(1);
				} else {
					caselle[i][j].setBlackAlert(1);
					if (i - 1 > 0)
						caselle[i - 1][j].setBlackAlert(1);
				}

				break;
			}
			i--;
		}

		i = daControllare.getRow() + 1;
		j = daControllare.getColumn();
		// fa lo stesso lavoro del ciclo soprastante, ma vado verso destra
		while (i <= 7) {
			if (caselle[i][j].isEmpty())
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);

			else if (!caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);
				break;
			}
			if (caselle[i][j].getColorP() != daControllare.getColorP()
					&& caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0) {
					caselle[i][j].setWhiteAlert(1);
					if (i + 1 < 7)
						caselle[i + 1][j].setWhiteAlert(1);
				} else {
					caselle[i][j].setBlackAlert(1);
					if (i + 1 < 7)
						caselle[i + 1][j].setBlackAlert(1);
				}

				break;
			}
			i++;
		}

		i = daControllare.getRow();
		j = daControllare.getColumn() + 1;
		while (j <= 7) {
			if (caselle[i][j].isEmpty())
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);

			else if (!caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);
				break;
			}

			if (caselle[i][j].getColorP() != daControllare.getColorP()
					&& caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0) {
					caselle[i][j].setWhiteAlert(1);
					if (j + 1 < 7)
						caselle[i][j + 1].setWhiteAlert(1);
				} else {
					caselle[i][j].setBlackAlert(1);
					if (j + 1 < 7)
						caselle[i][j + 1].setBlackAlert(1);
				}

				break;
			}

			j++;
		}
		i = daControllare.getRow();
		j = daControllare.getColumn() - 1;
		
		while (j >= 0) {
			if (caselle[i][j].isEmpty())
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);

			else if (!caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);
				break;
			}

			if (caselle[i][j].getColorP() != daControllare.getColorP()
					&& caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0) {
					caselle[i][j].setWhiteAlert(1);
					if (j - 1 > 0)
						caselle[i][j - 1].setWhiteAlert(1);
				} else {
					caselle[i][j].setBlackAlert(1);
					if (j - 1 > 0)
						caselle[i][j - 1].setBlackAlert(1);
				}

				break;
			}
			j--;
		}

	}

	private void setAlertsAlfiere(Cell daControllare) {
		int i = daControllare.getRow() - 1;
		int j = daControllare.getColumn() + 1;

		while (i >= 0 && j <= 7) {
			if (caselle[i][j].isEmpty())
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);

			else if (!caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);
				break;
			}

			if (caselle[i][j].getColorP() != daControllare.getColorP()
					&& caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0) {
					caselle[i][j].setWhiteAlert(1);
					if (i - 1 > 0 && j + 1 < 7)
						caselle[i - 1][j + 1].setWhiteAlert(1);
				} else {
					caselle[i][j].setBlackAlert(1);
					if (i - 1 > 0 && j + 1 < 7)
						caselle[i - 1][j + 1].setBlackAlert(1);
				}

				break;
			}
			i--;
			j++;
		}

		i = daControllare.getRow() + 1;
		j = daControllare.getColumn() + 1;
		while (i <= 7 && j <= 7) {
			if (caselle[i][j].isEmpty())
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);

			else if (!caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);
				break;
			}
			if (caselle[i][j].getColorP() != daControllare.getColorP()
					&& caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0) {
					caselle[i][j].setWhiteAlert(1);
					if (i + 1 < 7 && j + 1 < 7)
						caselle[i + 1][j + 1].setWhiteAlert(1);
				} else {
					caselle[i][j].setBlackAlert(1);
					if (i + 1 < 7 && j + 1 < 7)
						caselle[i + 1][j + 1].setBlackAlert(1);
				}

				break;
			}
			i++;
			j++;
		}

		i = daControllare.getRow() + 1;
		j = daControllare.getColumn() - 1;
		while (j >= 0 && i <= 7) {
			if (caselle[i][j].isEmpty())
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);

			else if (!caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);
				break;
			}

			if (caselle[i][j].getColorP() != daControllare.getColorP()
					&& caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0) {
					caselle[i][j].setWhiteAlert(1);
					if (i + 1 < 7 && j - 1 > 0)
						caselle[i + 1][j - 1].setWhiteAlert(1);
				} else {
					caselle[i][j].setBlackAlert(1);
					if (i + 1 < 7 && j - 1 > 0)
						caselle[i + 1][j - 1].setBlackAlert(1);
				}

				break;
			}

			j--;
			i++;
		}

		i = daControllare.getRow() - 1;
		j = daControllare.getColumn() - 1;
		while (j >= 0 && i >= 0) {
			if (caselle[i][j].isEmpty())
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);

			else if (!caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0)
					caselle[i][j].setWhiteAlert(1);
				else
					caselle[i][j].setBlackAlert(1);
				break;
			}

			if (caselle[i][j].getColorP() != daControllare.getColorP()
					&& caselle[i][j].getPedina().equals("Re")) {
				if (daControllare.getColorP() == 0) {
					caselle[i][j].setWhiteAlert(1);
					if (i - 1 > 0 && j - 1 > 0)
						caselle[i - 1][j - 1].setWhiteAlert(1);
				} else {
					caselle[i][j].setBlackAlert(1);
					if (i - 1 > 0 && j - 1 > 0)
						caselle[i - 1][j - 1].setBlackAlert(1);
				}

				break;
			}
			j--;
			i--;
		}
	}

	private void setAlertsCavallo(Cell daControllare) {
		int i = daControllare.getRow();
		int j = daControllare.getColumn();

		if (i - 2 >= 0 && j + 1 <= 7)
			// se la casella su cui spostarsi e' vuota, oppure se contiene una
			// pedina dello stesso colore, oppure colore diverso ma e' un re
			if (caselle[i - 2][j + 1].isEmpty()
					|| caselle[i - 2][j + 1].getColorP() == daControllare
							.getColorP()
					|| (caselle[i - 2][j + 1].getColorP() != daControllare
							.getColorP() && caselle[i - 2][j + 1].getPedina()
							.equals("Re")))
				if (daControllare.getColorP() == 0)
					caselle[i - 2][j + 1].setWhiteAlert(1);
				else
					caselle[i - 2][j + 1].setBlackAlert(1);
		if (i - 1 >= 0 && j + 2 <= 7)
			if (caselle[i - 1][j + 2].isEmpty()
					|| caselle[i - 1][j + 2].getColorP() == daControllare
							.getColorP()
					|| (caselle[i - 1][j + 2].getColorP() != daControllare
							.getColorP() && caselle[i - 1][j + 2].getPedina()
							.equals("Re")))
				if (daControllare.getColorP() == 0)
					caselle[i - 1][j + 2].setWhiteAlert(1);
				else
					caselle[i - 1][j + 2].setBlackAlert(1);

		if (i + 1 <= 7 && j + 2 <= 7)
			if (caselle[i + 1][j + 2].isEmpty()
					|| caselle[i + 1][j + 2].getColorP() == daControllare
							.getColorP()
					|| (caselle[i + 1][j + 2].getColorP() != daControllare
							.getColorP() && caselle[i + 1][j + 2].getPedina()
							.equals("Re")))
				if (daControllare.getColorP() == 0)
					caselle[i + 1][j + 2].setWhiteAlert(1);
				else
					caselle[i + 1][j + 2].setBlackAlert(1);

		if (i + 2 <= 7 && j + 1 <= 7)
			if (caselle[i + 2][j + 1].isEmpty()
					|| caselle[i + 2][j + 1].getColorP() == daControllare
							.getColorP()
					|| (caselle[i + 2][j + 1].getColorP() != daControllare
							.getColorP() && caselle[i + 2][j + 1].getPedina()
							.equals("Re")))
				if (daControllare.getColorP() == 0)
					caselle[i + 2][j + 1].setWhiteAlert(1);
				else
					caselle[i + 2][j + 1].setBlackAlert(1);

		if (i + 2 <= 7 && j - 1 >= 0)
			if (caselle[i + 2][j - 1].isEmpty()
					|| caselle[i + 2][j - 1].getColorP() == daControllare
							.getColorP()
					|| (caselle[i + 2][j - 1].getColorP() != daControllare
							.getColorP() && caselle[i + 2][j - 1].getPedina()
							.equals("Re")))
				if (daControllare.getColorP() == 0)
					caselle[i + 2][j - 1].setWhiteAlert(1);
				else
					caselle[i + 2][j - 1].setBlackAlert(1);

		if (i + 1 <= 7 && j - 2 >= 0)
			if (caselle[i + 1][j - 2].isEmpty()
					|| caselle[i + 1][j - 2].getColorP() == daControllare
							.getColorP()
					|| (caselle[i + 1][j - 2].getColorP() != daControllare
							.getColorP() && caselle[i + 1][j - 2].getPedina()
							.equals("Re")))
				if (daControllare.getColorP() == 0)
					caselle[i + 1][j - 2].setWhiteAlert(1);
				else
					caselle[i + 1][j - 2].setBlackAlert(1);

		if (i - 1 >= 0 && j - 2 >= 0)
			if (caselle[i - 1][j - 2].isEmpty()
					|| caselle[i - 1][j - 2].getColorP() == daControllare
							.getColorP()
					|| (caselle[i - 1][j - 2].getColorP() != daControllare
							.getColorP() && caselle[i - 1][j - 2].getPedina()
							.equals("Re")))
				if (daControllare.getColorP() == 0)
					caselle[i - 1][j - 2].setWhiteAlert(1);
				else
					caselle[i - 1][j - 2].setBlackAlert(1);

		if (i - 2 >= 0 && j - 1 >= 0)
			if (caselle[i - 2][j - 1].isEmpty()
					|| caselle[i - 2][j - 1].getColorP() == daControllare
							.getColorP()
					|| (caselle[i - 2][j - 1].getColorP() != daControllare
							.getColorP() && caselle[i - 2][j - 1].getPedina()
							.equals("Re")))
				if (daControllare.getColorP() == 0)
					caselle[i - 2][j - 1].setWhiteAlert(1);
				else
					caselle[i - 2][j - 1].setBlackAlert(1);

	}

	private void setMinacceRe(Cell daControllare) {
		int i = daControllare.getRow();
		int j = daControllare.getColumn();

		if (i - 1 >= 0)// controllo sulla casella sopra quella del re
			// se la casella e' vuota oppure contiene una pedina dello stesso
			// colore
			if (caselle[i - 1][j].isEmpty()
					|| caselle[i - 1][j].getColorP() == daControllare
							.getColorP()) {
				if (daControllare.getColorP() == 0)
					caselle[i - 1][j].setWhiteAlert(1);
				if (daControllare.getColorP() == 1)
					caselle[i - 1][j].setBlackAlert(1);
			}

		if (i - 1 >= 0 && j + 1 <= 7)// controllo sulla casella sopra a destra
										// quella del re
			if (caselle[i - 1][j + 1].isEmpty()
					|| caselle[i - 1][j + 1].getColorP() == daControllare
							.getColorP()) {
				if (daControllare.getColorP() == 0)
					caselle[i - 1][j + 1].setWhiteAlert(1);
				if (daControllare.getColorP() == 1)
					caselle[i - 1][j + 1].setBlackAlert(1);
			}
		if (j + 1 <= 7)// controllo sulla casella sopra a destra di quella del re

			if (caselle[i][j + 1].isEmpty()
					|| caselle[i][j + 1].getColorP() == daControllare
							.getColorP()) {
				if (daControllare.getColorP() == 0)
					caselle[i][j + 1].setWhiteAlert(1);
				if (daControllare.getColorP() == 1)
					caselle[i][j + 1].setBlackAlert(1);
			}
		if (i + 1 <= 7 && j + 1 <= 7)// controllo sulla casella giù a destra di
										// quella del re
			if (caselle[i + 1][j + 1].isEmpty()
					|| caselle[i + 1][j + 1].getColorP() == daControllare
							.getColorP()) {
				if (daControllare.getColorP() == 0)
					caselle[i + 1][j + 1].setWhiteAlert(1);
				if (daControllare.getColorP() == 1)
					caselle[i + 1][j + 1].setBlackAlert(1);
			}
		if (i + 1 <= 7)// controllo sulla casella sotto quella del re
			if (caselle[i + 1][j].isEmpty()
					|| caselle[i + 1][j].getColorP() == daControllare
							.getColorP()) {
				if (daControllare.getColorP() == 0)
					caselle[i + 1][j].setWhiteAlert(1);
				if (daControllare.getColorP() == 1)
					caselle[i + 1][j].setBlackAlert(1);
			}
		if (i + 1 <= 7 && j - 1 >= 0)// controllo sulla casella giu' a sinistra
										// di quella del re
			if (caselle[i + 1][j - 1].isEmpty()
					|| caselle[i + 1][j - 1].getColorP() == daControllare
							.getColorP()) {
				if (daControllare.getColorP() == 0)
					caselle[i + 1][j - 1].setWhiteAlert(1);
				if (daControllare.getColorP() == 1)
					caselle[i + 1][j - 1].setBlackAlert(1);
			}
		if (j - 1 >= 0)// controllo sulla casella a sinistra di quella del re
			if (caselle[i][j - 1].isEmpty()
					|| caselle[i][j - 1].getColorP() == daControllare
							.getColorP()) {
				if (daControllare.getColorP() == 0)
					caselle[i][j - 1].setWhiteAlert(1);
				if (daControllare.getColorP() == 1)
					caselle[i][j - 1].setBlackAlert(1);
			}
		if (i - 1 >= 0 && j - 1 >= 0)// controllo sulla casella sopra a
										// sinistra di quella del re
			if (caselle[i - 1][j - 1].isEmpty()
					|| caselle[i - 1][j - 1].getColorP() == daControllare
							.getColorP()) {
				if (daControllare.getColorP() == 0)
					caselle[i - 1][j - 1].setWhiteAlert(1);
				if (daControllare.getColorP() == 1)
					caselle[i - 1][j - 1].setBlackAlert(1);
			}
	}

	/*
	 * funzione che parte dalla prima casella fino all'ultima e sposta le pedine
	 * in tutte le possibili caselle in cui possono andare, se dopo avere
	 * spostato la pedina in quella casella vi e' ancora scacco, allora quella
	 * caselle viene deselezionata in modo che non venga visualizzata verde
	 */
	private void deselectIfCheckAfterMove() {
		Cell temp = new Cell(1, 1, 1);

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (caselle[i][j].thisSelected() && caselle[i][j] != start) {
					// salvo la pedina della casella di destinazione
					temp.setPedina(caselle[i][j].getPedina(),
							caselle[i][j].getColorP(),
							caselle[i][j].getImageP());
					// sposto la pedina della casella di partenza in quella
					// di destinazione
					caselle[i][j].setPedina(start.getPedina(),
							start.getColorP(), start.getImageP());
					// lascio vuota la casella di partenza
					start.setPedina("", -1, null);

					unSetMinacce();
					setAlerts(); // aggiorno le minacce

					if ((turn == 0 && checkScaccoBianchi())
							|| (turn == 1 && checkScaccoNeri()))
						// se il giocatore di cui e' il turno e' sotto scacco
						// deseleziono la casella
						caselle[i][j].thisDeSelect();

					// rimetto le pedine al loro posto di prima
					start.setPedina(caselle[i][j].getPedina(),
							caselle[i][j].getColorP(),
							caselle[i][j].getImageP());
					caselle[i][j].setPedina(temp.getPedina(), temp.getColorP(),
							temp.getImageP());
				}
		unSetMinacce();
		setAlerts();
	}

	private boolean hasDestinations() {// funzione che controlla se rimangono
										// caselle selezionate
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (caselle[i][j].thisSelected() && caselle[i][j] != start)
					return true;
		return false;
	}

	public void onClick(Cell caselle) {// funzione che gestisce le azioni a
										// seconda che sia il primo click o il
										// secondo
		if (caselle.getSelected() != 1 && !caselle.isEmpty()) {// se e' il primo
																// click
			start = caselle;
			setDestinations(); 
					
			deselectIfCheckAfterMove(); 
			if (!hasDestinations()) // se non ci sono caselle "verdi" non
									// posso muovere la pedina, quindi
									// deselezioniamo
				deSelectAll();
		} else if (caselle.getColorP() == turn) {
			deSelectAll();
			if (caselle != start) {
				start = caselle;
				setDestinations(); // setto di verde le caselle disponibili
									// se ve ne sono
				deselectIfCheckAfterMove(); // deseleziono le caselle che
											// lasciano in situazione di scacco
				if (!hasDestinations()) // se non ci sono caselle "verdi" non
										// posso muovere la pedina, quindi
										// deseleziono
					deSelectAll();
					if(start.getPedina().equals("Re"))
						new EndMessage(start.getColorP(), frame);
			}
		} else if (caselle.thisSelected() && start != caselle) {// se e' il
																// secondo click
			if (!caselle.getPedina().equals("Re")) {// se la casella di
													// destinazione non e' un re
													// allora posso muovermi
				move(caselle); // funzione che applica il movimento
				start = null; // azzero la casella di partenza
				unSetMinacce();
				setAlerts(); // aggiorno le minacce dopo il movimento poiche'
								// la configurazione e' diversa
			}
			deSelectAll();

		} else
			// se non entro nelle condizioni sopra, deseleziono
			deSelectAll();
		if (turn == 0)// controllo se vi e' scacco ad ogni turno
			checkScaccoBianchi();
		else
			checkScaccoNeri();
	}

	private void setDestinations() {
		if (turn == start.getColorP()) {// se la pedina su cui ho cliccato
										// mi appartiene posso settare le
										// caselle di destinazione
			// di seguito le chiamate alle funzione che gestiscono le
			// destinazione dei vari pezzi
			if (start.getPedina().equals("Pedone"))
				setDestinationsPedone();
			if (start.getPedina().equals("Torre"))
				setDestinationsTorre();
			if (start.getPedina().equals("Alfiere"))
				setDestinationsAlfiere();
			if (start.getPedina().equals("Cavallo"))
				setDestinationsCavallo();
			if (start.getPedina().equals("Regina")) { 
				setDestinationsTorre();
				setDestinationsAlfiere();
			}
			if (start.getPedina().equals("Re"))
				setDestinationsRe();
		}
	}

	private void setDestinationsPedone() { // metodo che setta le caselle di
											// destinazione disponibili per il
											// pedone
		if (start.getColorP() == 0 && start.getRow() > 0) {// se il pedone e'
															// bianco e sono
															// nella penultima
															// riga andando
															// verso sù
			start.select(); // setto verde la casella
			if (caselle[start.getRow() - 1][start.getColumn()].isEmpty())
				caselle[start.getRow() - 1][start.getColumn()].select();

			if (start.getColumn() > 0)// se non sono sul bordo di sinistra
				// se la caselle sopra a sinistra ha una pedina di colore
				// opposto

				if (!caselle[start.getRow() - 1][start.getColumn() - 1]
						.isEmpty()
						&& caselle[start.getRow() - 1][start.getColumn() - 1]
								.getColorP() != start.getColorP())
					caselle[start.getRow() - 1][start.getColumn() - 1].select();

			if (start.getColumn() < 7)// se non sono sul bordo destro eseguo la
										// stessa operazione ma per la casella sopra
										// a destra
				if (!caselle[start.getRow() - 1][start.getColumn() + 1]
						.isEmpty()
						&& caselle[start.getRow() - 1][start.getColumn() + 1]
								.getColorP() != start.getColorP())
					caselle[start.getRow() - 1][start.getColumn() + 1].select();
		}
		// faccio gli stessi controlli per il pedone nero ma con le coordinate
		// rovesciate poiche' si sposta verso giù
		if (start.getColorP() == 1 && start.getRow() < 7) {
			start.select();
			if (caselle[start.getRow() + 1][start.getColumn()].isEmpty())
				caselle[start.getRow() + 1][start.getColumn()].select();

			if (start.getColumn() < 7)
				if (!caselle[start.getRow() + 1][start.getColumn() + 1]
						.isEmpty()
						&& caselle[start.getRow() + 1][start.getColumn() + 1]
								.getColorP() != start.getColorP())
					caselle[start.getRow() + 1][start.getColumn() + 1].select();

			if (start.getColumn() > 0)
				if (!caselle[start.getRow() + 1][start.getColumn() - 1]
						.isEmpty()
						&& caselle[start.getRow() + 1][start.getColumn() - 1]
								.getColorP() != start.getColorP())
					caselle[start.getRow() + 1][start.getColumn() - 1].select();
		}
	}

	private void setDestinationsTorre() {// fa lo stesso lavoro dei
											// checkAlertTorre ma setta le
											// caselle disponibili di verde
		int i = start.getRow() - 1;
		int j = start.getColumn();
		start.select();
		while (i >= 0
				&& (caselle[i][j].isEmpty() || caselle[i][j].getColorP() != start
						.getColorP())) {
			caselle[i][j].select();
			if (caselle[i][j].getColorP() != start.getColorP()
					&& caselle[i][j].getColorP() != -1)
				break;
			i--;
		}

		i = start.getRow() + 1;
		j = start.getColumn();

		while (i <= 7
				&& (caselle[i][j].isEmpty() || caselle[i][j].getColorP() != start
						.getColorP())) {
			caselle[i][j].select();
			if (caselle[i][j].getColorP() != start.getColorP()
					&& caselle[i][j].getColorP() != -1)
				break;
			i++;
		}

		i = start.getRow();
		j = start.getColumn() + 1;

		while (j <= 7
				&& (caselle[i][j].isEmpty() || caselle[i][j].getColorP() != start
						.getColorP())) {
			caselle[i][j].select();
			if (caselle[i][j].getColorP() != start.getColorP()
					&& caselle[i][j].getColorP() != -1)
				break;
			j++;
		}
		i = start.getRow();
		j = start.getColumn() - 1;

		while (j >= 0
				&& (caselle[i][j].isEmpty() || caselle[i][j].getColorP() != start
						.getColorP())) {
			caselle[i][j].select();
			if (caselle[i][j].getColorP() != start.getColorP()
					&& caselle[i][j].getColorP() != -1)
				break;
			j--;
		}

	}

	private void setDestinationsAlfiere() {
		int i = start.getRow() - 1;
		int j = start.getColumn() + 1;
		start.select();
		while ((i >= 0 && j <= 7)
				&& (caselle[i][j].isEmpty() || caselle[i][j].getColorP() != start
						.getColorP())) {
			caselle[i][j].select();
			if (caselle[i][j].getColorP() != start.getColorP()
					&& caselle[i][j].getColorP() != -1)
				break;
			i--;
			j++;
		}

		i = start.getRow() + 1;
		j = start.getColumn() - 1;
		while ((i <= 7 && j >= 0)
				&& (caselle[i][j].isEmpty() || caselle[i][j].getColorP() != start
						.getColorP())) {
			caselle[i][j].select();
			if (caselle[i][j].getColorP() != start.getColorP()
					&& caselle[i][j].getColorP() != -1)
				break;
			i++;
			j--;
		}

		i = start.getRow() - 1;
		j = start.getColumn() - 1;

		while ((i >= 0 && j >= 0)
				&& (caselle[i][j].isEmpty() || caselle[i][j].getColorP() != start
						.getColorP())) {
			caselle[i][j].select();
			if (caselle[i][j].getColorP() != start.getColorP()
					&& caselle[i][j].getColorP() != -1)
				break;
			i--;
			j--;
		}
		i = start.getRow() + 1;
		j = start.getColumn() + 1;

		while ((j <= 7 && i <= 7)
				&& (caselle[i][j].isEmpty() || caselle[i][j].getColorP() != start
						.getColorP())) {
			caselle[i][j].select();
			if (caselle[i][j].getColorP() != start.getColorP()
					&& caselle[i][j].getColorP() != -1)
				break;
			i++;
			j++;
		}
	}

	private void setDestinationsCavallo() {// fa lo stesso lavoro dei
											// checkAlertCavallo ma setta le
											// caselle disponibili di verde
		int i = start.getRow();
		int j = start.getColumn();
		start.select();
		if (i - 2 >= 0 && j + 1 <= 7)
			if ((caselle[i - 2][j + 1].isEmpty() || caselle[i - 2][j + 1]
					.getColorP() != start.getColorP()))
				caselle[i - 2][j + 1].select();

		if (i - 1 >= 0 && j + 2 <= 7)
			if ((caselle[i - 1][j + 2].isEmpty() || caselle[i - 1][j + 2]
					.getColorP() != start.getColorP()))
				caselle[i - 1][j + 2].select();

		if (i + 1 <= 7 && j + 2 <= 7)
			if ((caselle[i + 1][j + 2].isEmpty() || caselle[i + 1][j + 2]
					.getColorP() != start.getColorP()))
				caselle[i + 1][j + 2].select();

		if (i + 2 <= 7 && j + 1 <= 7)
			if ((caselle[i + 2][j + 1].isEmpty() || caselle[i + 2][j + 1]
					.getColorP() != start.getColorP()))
				caselle[i + 2][j + 1].select();

		if (i + 2 <= 7 && j - 1 >= 0)
			if ((caselle[i + 2][j - 1].isEmpty() || caselle[i + 2][j - 1]
					.getColorP() != start.getColorP()))
				caselle[i + 2][j - 1].select();

		if (i + 1 <= 7 && j - 2 >= 0)
			if ((caselle[i + 1][j - 2].isEmpty() || caselle[i + 1][j - 2]
					.getColorP() != start.getColorP()))
				caselle[i + 1][j - 2].select();

		if (i - 1 >= 0 && j - 2 >= 0)
			if ((caselle[i - 1][j - 2].isEmpty() || caselle[i - 1][j - 2]
					.getColorP() != start.getColorP()))
				caselle[i - 1][j - 2].select();

		if (i - 2 >= 0 && j - 1 >= 0)
			if ((caselle[i - 2][j - 1].isEmpty() || caselle[i - 2][j - 1]
					.getColorP() != start.getColorP()))
				caselle[i - 2][j - 1].select();

	}

	private void setDestinationsRe() {// fa lo stesso lavoro dei checkAlertRe
										// ma setta le caselle disponibili di
										// verde
		int i = start.getRow();
		int j = start.getColumn();
		start.select();

		if (i - 1 >= 0)
			if ((caselle[i - 1][j].isEmpty() && (start.getColorP() == 0 ? caselle[i - 1][j]
					.getBlackAlert() : caselle[i - 1][j].getWhiteAlert()) == 0)
					|| (caselle[i - 1][j].getColorP() != start.getColorP() && (start
							.getColorP() == 0 ? caselle[i - 1][j]
							.getBlackAlert() : caselle[i - 1][j]
							.getWhiteAlert()) == 0))
				caselle[i - 1][j].select();

		if (i - 1 >= 0 && j + 1 <= 7)
			if ((caselle[i - 1][j + 1].isEmpty() && (start.getColorP() == 0 ? caselle[i - 1][j + 1]
					.getBlackAlert() : caselle[i - 1][j + 1].getWhiteAlert()) == 0)
					|| (caselle[i - 1][j + 1].getColorP() != start.getColorP() && (start
							.getColorP() == 0 ? caselle[i - 1][j + 1]
							.getBlackAlert() : caselle[i - 1][j + 1]
							.getWhiteAlert()) == 0))
				caselle[i - 1][j + 1].select();

		if (j + 1 <= 7)
			if ((caselle[i][j + 1].isEmpty() && (start.getColorP() == 0 ? caselle[i][j + 1]
					.getBlackAlert() : caselle[i][j + 1].getWhiteAlert()) == 0)
					|| (caselle[i][j + 1].getColorP() != start.getColorP() && (start
							.getColorP() == 0 ? caselle[i][j + 1]
							.getBlackAlert() : caselle[i][j + 1]
							.getWhiteAlert()) == 0))
				caselle[i][j + 1].select();

		if (i + 1 <= 7 && j + 1 <= 7)
			if ((caselle[i + 1][j + 1].isEmpty() && (start.getColorP() == 0 ? caselle[i + 1][j + 1]
					.getBlackAlert() : caselle[i + 1][j + 1].getWhiteAlert()) == 0)
					|| (caselle[i + 1][j + 1].getColorP() != start.getColorP() && (start
							.getColorP() == 0 ? caselle[i + 1][j + 1]
							.getBlackAlert() : caselle[i + 1][j + 1]
							.getWhiteAlert()) == 0))
				caselle[i + 1][j + 1].select();

		if (i + 1 <= 7)
			if ((caselle[i + 1][j].isEmpty() && (start.getColorP() == 0 ? caselle[i + 1][j]
					.getBlackAlert() : caselle[i + 1][j].getWhiteAlert()) == 0)
					|| (caselle[i + 1][j].getColorP() != start.getColorP() && (start
							.getColorP() == 0 ? caselle[i + 1][j]
							.getBlackAlert() : caselle[i + 1][j]
							.getWhiteAlert()) == 0))
				caselle[i + 1][j].select();

		if (i + 1 <= 7 && j - 1 >= 0)
			if ((caselle[i + 1][j - 1].isEmpty() && (start.getColorP() == 0 ? caselle[i + 1][j - 1]
					.getBlackAlert() : caselle[i + 1][j - 1].getWhiteAlert()) == 0)
					|| (caselle[i + 1][j - 1].getColorP() != start.getColorP() && (start
							.getColorP() == 0 ? caselle[i + 1][j - 1]
							.getBlackAlert() : caselle[i + 1][j - 1]
							.getWhiteAlert()) == 0))
				caselle[i + 1][j - 1].select();

		if (j - 1 >= 0)
			if ((caselle[i][j - 1].isEmpty() && (start.getColorP() == 0 ? caselle[i][j - 1]
					.getBlackAlert() : caselle[i][j - 1].getWhiteAlert()) == 0)
					|| (caselle[i][j - 1].getColorP() != start.getColorP() && (start
							.getColorP() == 0 ? caselle[i][j - 1]
							.getBlackAlert() : caselle[i][j - 1]
							.getWhiteAlert()) == 0))
				caselle[i][j - 1].select();

		if (i - 1 >= 0 && j - 1 >= 0)
			if ((caselle[i - 1][j - 1].isEmpty() && (start.getColorP() == 0 ? caselle[i - 1][j - 1]
					.getBlackAlert() : caselle[i - 1][j - 1].getWhiteAlert()) == 0)
					|| (caselle[i - 1][j - 1].getColorP() != start.getColorP() && (start
							.getColorP() == 0 ? caselle[i - 1][j - 1]
							.getBlackAlert() : caselle[i - 1][j - 1]
							.getWhiteAlert()) == 0))
				caselle[i - 1][j - 1].select();
	}

	private void cambiaTurno() {
		if (turn == 0)
			turn = 1;
		else
			turn = 0;
	}

	private boolean checkScaccoMattoBianchi() {// metodo che controlla se vi e'
												// scacco matto per i bianchi
		if (checkScaccoBianchi()) {// se c'e' scacco per i bianchi
			for (int i = 0; i < 8; i++)
				// vado a controllare ogni casella
				for (int j = 0; j < 8; j++)
					if (caselle[i][j].getColorP() == 0) {// se la pedina e' bianca
						start = caselle[i][j];
						deSelectAll();
						setDestinations();// setto di verde le caselle dove
											// può andare
						deselectIfCheckAfterMove();// deseleziono le caselle
													// che dopo lo spostamento
													// portano a una situazione
													// di scacco
						if (hasDestinations())// se dopo tutto ciò ho
												// destinazioni disponibili,
												// allora non c'e' scacco matto
							return false;
					}
			deSelectAll();
			return true;
		}
		return false;
	}

	private boolean checkScaccoMattoNeri() {
		if (checkScaccoNeri()) {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					if (caselle[i][j].getColorP() == 1) {
						start = caselle[i][j];
						deSelectAll();
						setDestinations();

						deselectIfCheckAfterMove();

						if (hasDestinations())
							return false;
					}
			deSelectAll();
			return true;
		}
		return false;
	}

	public boolean isCheckmate() {
		return checkScaccoMattoBianchi() || checkScaccoMattoNeri();
	}

	private boolean checkScaccoBianchi() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (caselle[i][j].scaccoBianchi()) {
					return true;
				}
		return false;

	}

	private boolean checkScaccoNeri() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (caselle[i][j].scaccoNeri()) {
					return true;
				}
		return false;

	}

	private void pUpgrade(Cell casellaPromossa){
		deSelectAll();
		casellaPromossa.select();
		//TODO DA AGGIUNGERE FINESTRA SCELTA PEDINA
	}


	private void move(Cell destination) {
		Cell temp = new Cell(destination.getRow(), destination.getColumn(),
				destination.getColor());
		temp.setPedina(destination.getPedina(), destination.getColorP(),
				destination.getImageP());

		destination.setPedina(start.getPedina(), start.getColorP(),
				start.getImageP());
		start.setPedina("", -1, null);

		for(int j = 0; j < 8; j++){
			if(caselle[0][j].getPedina().equals("Pedone"))
				pUpgrade(caselle[0][j]);
			if(caselle[7][j].getPedina().equals("Pedone"))
				pUpgrade(caselle[7][j]);
		}
		
		unSetMinacce();
		setAlerts();
		cambiaTurno();
		if (checkScaccoMattoBianchi()) {
			checkScaccoBianchi();
			 new EndMessage(0, frame);

		}
		if (checkScaccoMattoNeri()) {
			checkScaccoNeri();
			 new EndMessage(1, frame);

		}
	}

	public void deSelectAll() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				caselle[i][j].deSelect();

	}
}