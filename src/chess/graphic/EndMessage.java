package chess.graphic;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/*
 * EndMessage mostra il messaggio di fine partita e propone ai giocatori
 * se iniziare o meno una nuova partita.
 */
public class EndMessage extends JDialog{
	private static final long serialVersionUID = 1L;
	private final JPanel end = new JPanel(new BorderLayout(3,1));
	private final JPanel northPanel = new JPanel(new BorderLayout());
	private final JPanel centerPanel = new JPanel(new BorderLayout());
	private final JPanel southPanel = new JPanel(new BorderLayout(1,2));
	private final JButton nuovoGioco = new JButton("Nuovo Gioco");
	private final JButton chiudi = new JButton("Chiudi");
	private final JLabel message1, message2;
	private final static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private final static int HEIGHT = 250;
	private final static int WIDTH = 150;
	
	public EndMessage (int colore, ChessboardFrame frame){
		setSize(HEIGHT, WIDTH);
		if(colore == 0){
			message1 = new JLabel("\n VITTORIA DEI NERI!!\n");
			message2 = new JLabel("\n Clicca Nuovo Gioco per rigiocare!");}
		else{
			message1 = new JLabel("\n VITTORIA DEI BIANCHI!!.\n");
			message2 = new JLabel("\n Clicca Nuovo Gioco per rigiocare!");}
		
		setLocation( (dim.width - HEIGHT) / 2, (dim.height - WIDTH) / 2 );
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		
		end.setBorder(new EmptyBorder(15,15,15,15));
		northPanel.add(message1);
		centerPanel.add(message2);
		southPanel.add(nuovoGioco, BorderLayout.WEST);
		southPanel.add(chiudi, BorderLayout.EAST);
		end.add(northPanel, BorderLayout.NORTH);
		end.add(centerPanel, BorderLayout.CENTER);
		end.add(southPanel, BorderLayout.SOUTH);
		
		nuovoGioco.addActionListener
		(event -> { frame.cleanGame(); dispose();});
		
		chiudi.addActionListener
		(event -> { dispose();});
		
		add(end, BorderLayout.CENTER);
		setVisible(true);
		pack();
	}
	
}