package chess.graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WindowStart extends JFrame {

	public WindowStart() {
		super("GIOCO DEGLI SCACCHI");
		setVisible(true);

		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout());

		JButton exit = new JButton("ESCI");
		JButton laws = new JButton("REGOLE");

		// pannello sud con i bottoni
		JPanel buttons_panel = new JPanel();
		buttons_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttons_panel.add(laws);
		buttons_panel.add(exit);

		// pannello centrale bottoni gioco
		JPanel upper_panel = new JPanel();
		upper_panel.setLayout(new GridLayout(1, 2));
		JButton normal = new JButton();
		JButton kids = new JButton("KIDS");
		normal.setIcon(new ImageIcon(WindowStart.class
				.getResource("/images/other/sfondo_iniziale.jpg")));
		kids.setIcon(new ImageIcon(WindowStart.class
				.getResource("/images/kids/kids_sfondo.jpg")));
		upper_panel.add(normal);
		upper_panel.add(kids);
		addActionListeners(normal, kids, exit, laws);

		main_panel.add(upper_panel, BorderLayout.NORTH);
		main_panel.add(buttons_panel, BorderLayout.SOUTH);

		upper_panel.setPreferredSize(new Dimension(600, 400));
		setContentPane(main_panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();

		// finestra al centro dello schermo
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int pos_x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int pos_y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(pos_x, pos_y);

	}

	private void addActionListeners(JButton normal, JButton kids, JButton exit, JButton laws) {

		normal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChessboardFrame(0);
				WindowStart.this.dispose();
			}
		});
		
		kids.addActionListener(event -> {
			new ChessboardFrame(1);
			WindowStart.this.dispose();
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		laws.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LawsWindow();
			}
		});

	}

}
