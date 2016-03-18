package chess.graphic;

import java.awt.BorderLayout;
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

import chess.logic.Cell;
import chess.logic.Controller;

@SuppressWarnings("serial")
public class ChessboardFrame extends JFrame {
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
	private Cell[][] caselle = new Cell[8][8];
	private final JPanel field = new JPanel(new GridLayout(8, 8));
	
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
	private final ImageIcon reBianco = new ImageIcon(
			ChessboardFrame.class.getResource("/images/other/re_bianco.png"));
	private final ImageIcon reNero = new ImageIcon(
			ChessboardFrame.class.getResource("/images/other/re_nero.png"));
	private final ImageIcon pedoneBianco = new ImageIcon(
			ChessboardFrame.class
					.getResource("/images/other/pedone_bianco.png"));
	private final ImageIcon pedoneNero = new ImageIcon(
			ChessboardFrame.class.getResource("/images/other/pedone_nero.png"));
	
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
	private final ImageIcon reBiancokids = new ImageIcon(
			ChessboardFrame.class.getResource("/images/kids/re_bianco.png"));
	private final ImageIcon reNerokids = new ImageIcon(
			ChessboardFrame.class.getResource("/images/kids/re_nero.png"));
	private final ImageIcon pedoneBiancokids = new ImageIcon(
			ChessboardFrame.class
					.getResource("/images/kids/pedone_bianco.png"));
	private final ImageIcon pedoneNerokids = new ImageIcon(
			ChessboardFrame.class.getResource("/images/kids/pedone_nero.png"));

	private JMenuBar jmb = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenuItem nuovogioco = new JMenuItem("Nuovo Gioco");
	private JMenuItem ricomincia = new JMenuItem("Ricomincia");
	private JMenuItem regole = new JMenuItem("Regole");
	private JMenuItem esci = new JMenuItem("Esci");

	private Controller controller = new Controller(caselle, this);

	private static int game;
	
	public int getGame() {
		return game;
	}

	public ChessboardFrame(int game) {
		this.game = game;
		setBounds(rect);
		setResizable(false);
		setTitle("SCACCHI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(myJMenuBar());

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// Crea la matrice di caselle
				caselle[i][j] = new Cell(i, j, (i + j) % 2 == 0 ? 0 : 1);
				// Imposta il colore di sfondo
				caselle[i][j]
						.setBackground(caselle[i][j].getColor() == 0 ? Cell.LIGHT : Cell.DARK);
				setActionListener(i, j);
				field.add(caselle[i][j]);
			}
		}

		cleanGame();
		gui.setBorder(new EmptyBorder(10, 10, 10, 10));
		scacchiera.add(field, BorderLayout.CENTER);
		gui.add(scacchiera, BorderLayout.CENTER);
		add(gui, BorderLayout.CENTER);

		setVisible(true);

	}

	public void setActionListener(int i, int j) {
		caselle[i][j].addActionListener(event -> {
			controller.onClick(caselle[i][j]);
		});
	}

	private void addActionListeners() {
		nuovogioco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					new WindowStart();
					ChessboardFrame.this.dispose();
			}
		});

		ricomincia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cleanGame();
			}
		});

		regole.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					new LawsWindow();
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

	public void cleanGame() {
		azzeraStatoController();
		pulisciCaselle();
		
		if(game == 0){
			for (int i = 0; i < 8; i++)
				
				switch (i) {
	
				case 0:
					caselle[i][0].setPedina("Torre", 1, torreNera);
					caselle[i][1].setPedina("Cavallo", 1, cavalloNero);
					caselle[i][2].setPedina("Alfiere", 1, alfiereNero);
					caselle[i][3].setPedina("Regina", 1, reginaNera);
					caselle[i][4].setPedina("Re", 1, reNero);
					caselle[i][5].setPedina("Alfiere", 1, alfiereNero);
					caselle[i][6].setPedina("Cavallo", 1, cavalloNero);
					caselle[i][7].setPedina("Torre", 1, torreNera);
					break;
				case 1:
					for (int j = 0; j < 8; j++)
						caselle[i][j].setPedina("Pedone", 1, pedoneNero);
					break;
				
				case 6:
					for (int j = 0; j < 8; j++)
						caselle[i][j].setPedina("Pedone", 0, pedoneBianco);
					break;
				case 7:
					caselle[i][0].setPedina("Torre", 0, torreBianca);
					caselle[i][1].setPedina("Cavallo", 0, cavalloBianco);
					caselle[i][2].setPedina("Alfiere", 0, alfiereBianco);
					caselle[i][3].setPedina("Regina", 0, reginaBianca);
					caselle[i][4].setPedina("Re", 0, reBianco);
					caselle[i][5].setPedina("Alfiere", 0, alfiereBianco);
					caselle[i][6].setPedina("Cavallo", 0, cavalloBianco);
					caselle[i][7].setPedina("Torre", 0, torreBianca);
					break;
					
				default:
					for (int j = 0; j < 8; j++)
						caselle[i][j].setPedina("", -1, null);
				}
		} else {
			for (int i = 0; i < 8; i++)
				
				switch (i) {
	
				case 0:
					caselle[i][0].setPedina("Torre", 1, torreNerakids);
					caselle[i][1].setPedina("Cavallo", 1, cavalloNerokids);
					caselle[i][2].setPedina("Alfiere", 1, alfiereNerokids);
					caselle[i][3].setPedina("Regina", 1, reginaNerakids);
					caselle[i][4].setPedina("Re", 1, reNerokids);
					caselle[i][5].setPedina("Alfiere", 1, alfiereNerokids);
					caselle[i][6].setPedina("Cavallo", 1, cavalloNerokids);
					caselle[i][7].setPedina("Torre", 1, torreNerakids);
					break;
				case 1:
					for (int j = 0; j < 8; j++)
						caselle[i][j].setPedina("Pedone", 1, pedoneNerokids);
					break;
				
				case 6:
					for (int j = 0; j < 8; j++)
						caselle[i][j].setPedina("Pedone", 0, pedoneBiancokids);
					break;
				case 7:
					caselle[i][0].setPedina("Torre", 0, torreBiancakids);
					caselle[i][1].setPedina("Cavallo", 0, cavalloBiancokids);
					caselle[i][2].setPedina("Alfiere", 0, alfiereBiancokids);
					caselle[i][3].setPedina("Regina", 0, reginaBiancakids);
					caselle[i][4].setPedina("Re", 0, reBiancokids);
					caselle[i][5].setPedina("Alfiere", 0, alfiereBiancokids);
					caselle[i][6].setPedina("Cavallo", 0, cavalloBiancokids);
					caselle[i][7].setPedina("Torre", 0, torreBiancakids);
					break;
					
				default:
					for (int j = 0; j < 8; j++)
						caselle[i][j].setPedina("", -1, null);
				}
		}

	}

	private void azzeraStatoController() {
		controller.azzeraTurn();
		controller.azzeraStart();
		controller.setCaselle(caselle);
		// TODO Auto-generated method stub
		
	}

	private void pulisciCaselle() {
		for(int i = 0; i < 8; i++)
			for( int j = 0; j < 8; j++)
				azzeraStatoCasella(caselle[i][j]);
		// TODO Auto-generated method stub
		
	}

	private void azzeraStatoCasella(Cell cell) {
		cell.setWhiteAlert(0);
		cell.setBlackAlert(0);
		cell.deSelect();
		// TODO Auto-generated method stub
		
	}

}
