package chess.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Casella;

public class ScacchieraFrame extends JFrame {
	private final int SCREENW = (int) Toolkit.getDefaultToolkit()
			.getScreenSize().getWidth();
	private final int SCREENH = (int) Toolkit.getDefaultToolkit()
			.getScreenSize().getHeight();

	private final int FRAMEW = 660;
	private final int FRAMEH = 700;

	private final Rectangle rect = new Rectangle(SCREENW / 2 - FRAMEW / 2,
			SCREENH / 2 - FRAMEH / 2, FRAMEW, FRAMEH);

	private final JPanel gui = new JPanel(new BorderLayout(2, 1));
	private final JPanel scacchiera = new JPanel(new BorderLayout());
	private Casella[][] caselle = new Casella[8][8];
	private final JPanel field = new JPanel(new GridLayout(8, 8));
	private final ImageIcon torreBianca = new ImageIcon(
			"images/normal/torre_bianca.png");
	private final ImageIcon torreNera = new ImageIcon(
			"images/normal/torre_nera.png");
	private final ImageIcon cavalloBianco = new ImageIcon(
			"images/normal/cavallo_bianco.png");
	private final ImageIcon cavalloNero = new ImageIcon(
			"images/normal/cavallo_nero.png");
	private final ImageIcon alfiereBianco = new ImageIcon(
			"images/normal/alfiere_bianco.png");
	private final ImageIcon alfiereNero = new ImageIcon(
			"images/normal/alfiere_nero.png");
	private final ImageIcon reginaBianca = new ImageIcon(
			"images/normal/regina_bianca.png");
	private final ImageIcon reginaNera = new ImageIcon(
			"images/normal/regina_nera.png");
	private final ImageIcon reBianco = new ImageIcon(
			"images/normal/re_bianco.png");
	private final ImageIcon reNero = new ImageIcon("images/normal/re_nero.png");
	private final ImageIcon pedoneBianco = new ImageIcon(
			"images/normal/pedone_bianco.png");
	private final ImageIcon pedoneNero = new ImageIcon(
			"images/normal/pedone_nero.png");

	private JMenuBar jmb = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenuItem nuovogioco = new JMenuItem("Nuovo Gioco");
	private JMenuItem ricomincia = new JMenuItem("Ricomincia");
	private JMenuItem regole = new JMenuItem("Regole");
	private JMenuItem esci = new JMenuItem("Esci");

	public ScacchieraFrame() {
		setBounds(rect);
		setResizable(false);
		setTitle("SCACCHI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(myJMenuBar());

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// Crea la matrice di caselle
				caselle[i][j] = new Casella(i, j, (i + j) % 2 == 0 ? 0 : 1);
				// Imposta il colore di sfondo
				caselle[i][j]
						.setBackground(caselle[i][j].getColor() == 0 ? Color.WHITE
								: Color.LIGHT_GRAY);
				field.add(caselle[i][j]); // Aggiunge la casella alla
											// GridView campoDaGioco
			}
		}

		gui.setBorder(new EmptyBorder(10, 10, 10, 10));
		scacchiera.add(field, BorderLayout.CENTER);
		gui.add(scacchiera, BorderLayout.CENTER);
		add(gui, BorderLayout.CENTER);

		setVisible(true);

	}

	private void addActionListeners() {
		nuovogioco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowStart();
				ScacchieraFrame.this.dispose();
			}
		});

		ricomincia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		regole.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		esci.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	private JMenuBar myJMenuBar() {
		addActionListeners();
		file.add(nuovogioco);
		file.add(ricomincia);
		file.add(regole);
		file.add(esci);
		jmb.add(file);
		return jmb;
	}

	public void inizializza() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {

				if (i == 0 && (j == 0 || j == 7))
					caselle[i][j].setPedina("Torre", 1, torreNera);
				if (i == 0 && (j == 1 || j == 6))
					caselle[i][j].setPedina("Cavallo", 1, cavalloNero);
				if (i == 0 && (j == 2 || j == 5))
					caselle[i][j].setPedina("Alfiere", 1, alfiereNero);
				if (i == 0 && j == 3)
					caselle[i][j].setPedina("Regina", 1, reginaNera);
				if (i == 0 && j == 4)
					caselle[i][j].setPedina("Re", 1, reNero);
				if (i == 1)
					caselle[i][j].setPedina("Pedone", 1, pedoneNero);
				if (i == 7 && (j == 0 || j == 7))
					caselle[i][j].setPedina("Torre", 0, torreBianca);
				if (i == 7 && (j == 1 || j == 6))
					caselle[i][j].setPedina("Cavallo", 0, cavalloBianco);
				if (i == 7 && (j == 2 || j == 5))
					caselle[i][j].setPedina("Alfiere", 0, alfiereBianco);
				if (i == 7 && j == 3)
					caselle[i][j].setPedina("Regina", 0, reginaBianca);
				if (i == 7 && j == 4)
					caselle[i][j].setPedina("Re", 0, reBianco);
				if (i == 6)
					caselle[i][j].setPedina("Pedone", 0, pedoneBianco);
				if (i > 1 && i < 6)
					// Imposta le caselle vuote senza pedine
					caselle[i][j].setPedina("", -1, null);
			}
	}

}
