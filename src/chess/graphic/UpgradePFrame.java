package chess.graphic;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import chess.logic.Cell;

/*
 *  PromotionFrame: finestra di dialogo che appare alla promozione di un pedone.
 *  Visualizza 4 pulsanti contenenti la pedina da scegliere in sostituzione
 */
public class UpgradePFrame extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JToolBar tools = new JToolBar();
	private final JPanel choosePanel = new JPanel();
	private final JPanel panel = new JPanel(new FlowLayout());

	private final JButton Torre = new JButton();
	private final JButton Alfiere = new JButton();
	private final JButton Cavallo = new JButton();
	private final JButton Regina = new JButton();

	private final ImageIcon torreBianca = new ImageIcon(
			ChessboardFrame.class.getResource("/images/other/torre_bianca.png"));
	private final ImageIcon torreNera = new ImageIcon(
			ChessboardFrame.class.getResource("/images/other/torre_nera.png"));
	private final ImageIcon cavalloBianco = new ImageIcon(
			ChessboardFrame.class
					.getResource("/images/other/cavallo_bianco.png"));
	private final ImageIcon cavalloNero = new ImageIcon(
			ChessboardFrame.class.getResource("/images/other/cavallo_nero.png"));
	private final ImageIcon alfiereBianco = new ImageIcon(
			ChessboardFrame.class
					.getResource("/images/other/alfiere_bianco.png"));
	private final ImageIcon alfiereNero = new ImageIcon(
			ChessboardFrame.class.getResource("/images/other/alfiere_nero.png"));
	private final ImageIcon reginaBianca = new ImageIcon(
			ChessboardFrame.class
					.getResource("/images/other/regina_bianca.png"));
	private final ImageIcon reginaNera = new ImageIcon(
			ChessboardFrame.class.getResource("/images/other/regina_nera.png"));
	
	private final ImageIcon torreBiancakids = new ImageIcon(
			ChessboardFrame.class.getResource("/images/kids/torre_bianca.png"));
	private final ImageIcon torreNerakids = new ImageIcon(
			ChessboardFrame.class.getResource("/images/kids/torre_nera.png"));
	private final ImageIcon cavalloBiancokids = new ImageIcon(
			ChessboardFrame.class
					.getResource("/images/kids/cavallo_bianco.png"));
	private final ImageIcon cavalloNerokids = new ImageIcon(
			ChessboardFrame.class.getResource("/images/kids/cavallo_nero.png"));
	private final ImageIcon alfiereBiancokids = new ImageIcon(
			ChessboardFrame.class
					.getResource("/images/kids/alfiere_bianco.png"));
	private final ImageIcon alfiereNerokids = new ImageIcon(
			ChessboardFrame.class.getResource("/images/kids/alfiere_nero.png"));
	private final ImageIcon reginaBiancakids = new ImageIcon(
			ChessboardFrame.class
					.getResource("/images/kids/regina_bianca.png"));
	private final ImageIcon reginaNerakids = new ImageIcon(
			ChessboardFrame.class.getResource("/images/kids/regina_nera.png"));

	private final static int HEIGHT = 280;
	private final static int WIDTH = 150;
	private final static Dimension dim = Toolkit.getDefaultToolkit()
			.getScreenSize();

	public UpgradePFrame(ChessboardFrame frame, Cell casella) {
		setSize(HEIGHT, WIDTH);
		setModal(true); // Resta in primo piano
		setResizable(false); // Dimensione fissa
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation((dim.width - HEIGHT) / 2, (dim.height - WIDTH) / 2);

		Torre.setPreferredSize(new Dimension(56, 56));
		Alfiere.setPreferredSize(new Dimension(56, 56));
		Cavallo.setPreferredSize(new Dimension(56, 56));
		Regina.setPreferredSize(new Dimension(56, 56));

		// Sostituisce il pedone con il pezzo selezionato (colore bianco o nero)
		// e chiude la finestra di dialogo
		if (casella.getColorP() == 0) { // Colore bianco
			if (frame.getGame() == 0) {
				Torre.setBackground(Cell.DARK);
				Torre.setIcon(torreBianca);
				Alfiere.setBackground(Cell.DARK);
				Alfiere.setIcon(alfiereBianco);
				Cavallo.setBackground(Cell.DARK);
				Cavallo.setIcon(cavalloBianco);
				Regina.setBackground(Cell.DARK);
				Regina.setIcon(reginaBianca);
				Torre.addActionListener(event -> {
					casella.setPedina("Torre", casella.getColorP(), torreBianca);
					dispose();
				});
				Alfiere.addActionListener(event -> {
					casella.setPedina("Alfiere", casella.getColorP(),
							alfiereBianco);
					dispose();
				});
				Cavallo.addActionListener(event -> {
					casella.setPedina("Cavallo", casella.getColorP(),
							cavalloBianco);
					dispose();
				});
				Regina.addActionListener(event -> {
					casella.setPedina("Regina", casella.getColorP(),
							reginaBianca);
					dispose();
				});
			} else {
				Torre.setBackground(Cell.DARK);
				Torre.setIcon(torreBiancakids);
				Alfiere.setBackground(Cell.DARK);
				Alfiere.setIcon(alfiereBiancokids);
				Cavallo.setBackground(Cell.DARK);
				Cavallo.setIcon(cavalloBiancokids);
				Regina.setBackground(Cell.DARK);
				Regina.setIcon(reginaBiancakids);
				Torre.addActionListener(event -> {
					casella.setPedina("Torre", casella.getColorP(),
							torreBiancakids);
					dispose();
				});
				Alfiere.addActionListener(event -> {
					casella.setPedina("Alfiere", casella.getColorP(),
							alfiereBiancokids);
					dispose();
				});
				Cavallo.addActionListener(event -> {
					casella.setPedina("Cavallo", casella.getColorP(),
							cavalloBiancokids);
					dispose();
				});
				Regina.addActionListener(event -> {
					casella.setPedina("Regina", casella.getColorP(),
							reginaBiancakids);
					dispose();
				});
			}
		} else { // Colore nero
			if (frame.getGame() == 0) {
				Torre.setBackground(Cell.LIGHT);
				Torre.setIcon(torreNera);
				Alfiere.setBackground(Cell.LIGHT);
				Alfiere.setIcon(alfiereNero);
				Cavallo.setBackground(Cell.LIGHT);
				Cavallo.setIcon(cavalloNero);
				Regina.setBackground(Cell.LIGHT);
				Regina.setIcon(reginaNera);
				Torre.addActionListener(event -> {
					casella.setPedina("Torre", casella.getColorP(), torreNera);
					dispose();
				});
				Alfiere.addActionListener(event -> {
					casella.setPedina("Alfiere", casella.getColorP(),
							alfiereNero);
					dispose();
				});
				Cavallo.addActionListener(event -> {
					casella.setPedina("Cavallo", casella.getColorP(),
							cavalloNero);
					dispose();
				});
				Regina.addActionListener(event -> {
					casella.setPedina("Regina", casella.getColorP(), reginaNera);
					dispose();
				});
			} else {
				Torre.setBackground(Cell.LIGHT);
				Torre.setIcon(torreNerakids);
				Alfiere.setBackground(Cell.LIGHT);
				Alfiere.setIcon(alfiereNerokids);
				Cavallo.setBackground(Cell.LIGHT);
				Cavallo.setIcon(cavalloNerokids);
				Regina.setBackground(Cell.LIGHT);
				Regina.setIcon(reginaNerakids);
				Torre.addActionListener(event -> {
					casella.setPedina("Torre", casella.getColorP(), torreNerakids);
					dispose();
				});
				Alfiere.addActionListener(event -> {
					casella.setPedina("Alfiere", casella.getColorP(),
							alfiereNerokids);
					dispose();
				});
				Cavallo.addActionListener(event -> {
					casella.setPedina("Cavallo", casella.getColorP(),
							cavalloNerokids);
					dispose();
				});
				Regina.addActionListener(event -> {
					casella.setPedina("Regina", casella.getColorP(), reginaNerakids);
					dispose();
				});
			}
		}

		tools.add(new JLabel("Scegli tra le sequenti pedine!"));

		choosePanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		choosePanel.add(tools);
		panel.add(Torre);
		panel.add(Alfiere);
		panel.add(Cavallo);
		panel.add(Regina);
		choosePanel.add(panel);

		add(choosePanel);
		setVisible(true);
		pack();
	}
}