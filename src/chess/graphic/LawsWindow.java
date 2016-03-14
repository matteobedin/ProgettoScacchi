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
		description.setText("Testo da "
				+ "inserire");
		
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