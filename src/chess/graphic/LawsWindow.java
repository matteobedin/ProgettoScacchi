package chess.graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class LawsWindow extends JFrame{
	
	protected LawsWindow(){
		
		setTitle("Regole del gioco - SCACCHI");
		
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout());
		
		JButton ok = new JButton("CHIUDI");
		
		JPanel buttons_panel = new JPanel();
		buttons_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttons_panel.add(ok);
		
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				dealloca la finestra alla chiusura
				dispose();
			}
		});
		
		JPanel description_panel = new JPanel();
		description_panel.setLayout(new BorderLayout());
		
		JTextPane description = new JTextPane();
		description.setText("Ogni giocatore dispone di un insieme di 16 pezzi, ciascuno composto di\n"
							+"sei tipi diversi di pezzi. In ordine crescente di importanza sono: \n"
							+ "pedone, cavallo, alfiere, torre, donna, re.\n"
							+"Ciascun pezzo degli scacchi si muove con precise modalità. Nessun pezzo\n"
							+ "può andare a occupare una casa in cui è presente un altro pezzo dello stesso\n"
							+ "schieramento; può invece muoversi su una casa occupata da un pezzo avversario,\n"
							+ "effettuando in tal caso una cattura, cioè eliminando dalla scacchiera il pezzo\n"
							+ "nemico e prendendo il suo posto. Si dice che un pezzo minaccia una casa se esso \n"
							+ "può muoversi su di essa.\n"
							+ "  - L'alfiere può muoversi su una qualunque casa della stessa diagonale rispetto\n"
							+ "    a quella in cui si trova, purché per raggiungerla non debba attraversare case \n"
							+ "    occupate da pezzi e purché la casa d'arrivo non sia occupata da un pezzo amico.\n"
							+ "  - La torre può muoversi su una qualunque casa della stessa traversa o della \n"
							+ "    stessa colonna rispetto a quella in cui si trova, purché per raggiungerla non debba\n"
							+ "    attraversare case occupate da pezzi (amici o avversari) e purché la casa d'arrivo\n"
							+ "    non sia occupata da un pezzo amico.\n"
							+ "  - La donna, il pezzo più potente di tutti, può scegliere ad ogni mossa se muoversi\n"
							+ "    come un alfiere o come una torre.\n"
							+ "  - Il cavallo può muoversi su una delle case a lui più vicine che non appartengono \n"
							+ "  - alla traversa, alla colonna e alle diagonali passanti per la sua casa di partenza\n"
							+ "    Il movimento del cavallo può essere immaginato come la somma di uno spostamento \n"
							+ "    orizzontale di una casa e di uno verticale di due (o viceversa), disegnando una L.\n"
							+ "  - Il pedone segue regole di movimento leggermente più complesse:\n"
							+ "    Il pedone è il solo pezzo che cattura in maniera differente da come muove. Può \n"
							+ "    catturare un pezzo nemico solo se si trova su una delle due case poste diagonalmente\n"
							+ "    in avanti rispetto alla sua casa di partenza.\n"
							+ "    Se un pedone riesce ad avanzare fino all'ottava traversa, viene promosso, ossia\n"
							+ "    sostituito con un pezzo dello stesso colore (donna, torre, alfiere o cavallo, a scelta\n"
							+ "    del giocatore), indipendentemente dai pezzi già presenti sulla scacchiera.\n"
							+ "  - Il re si può muovere in una delle case adiacenti (anche diagonalmente) a quella\n"
							+ "    occupata, purché questa non sia controllata da un pezzo avversario.\n "
							+ "Il re è l'unico pezzo che non viene mai catturato, ma solo minacciato. Quando il re di uno\n "
							+ "dei due giocatori è minacciato (ovvero, come si dice, è sotto scacco) non è consentita\n"
							+ "alcuna mossa che lasci il proprio re in tale condizione: deve cioè essere effettuata una\n"
							+ "mossa che pari la minaccia e cioè, nell'ordine:\n"
							+ "1  Muovere il Re in una delle case adiacenti a patto che queste non siano sotto il \n"
							+ "   controllo di un altro pezzo avversario.\n"
							+ "2  Mangiare, con il re o con altro pezzo o pedone, il pezzo avversario che minaccia\n"
							+ "   la presa e dà origine allo scacco.\n"
							+ "3  Frapporre tra sé e il pezzo che minaccia scacco uno qualunque dei propri pezzi o\n"
							+ "    pedoni in modo che sia quest'ultimo a venire mangiato in vece del Re.\n"
							+ "Se il giocatore non può sottrarre in alcun modo il Re dallo scacco, si tratta \n"
							+ "di scacco matto e la partita termina con la vittoria dell'avversario.\n"
							);
		
		description.setEditable(false);
		
		description_panel.add(description);
		
		main_panel.add(description_panel, BorderLayout.NORTH);
		main_panel.add(buttons_panel, BorderLayout.SOUTH);
		
		setContentPane(main_panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int pos_x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int pos_y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(pos_x, pos_y);
		
		setVisible(true);
		
	}

}