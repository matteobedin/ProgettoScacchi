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
		upper_panel.setLayout(new GridLayout(1, 1));
		JButton normal = new JButton();
		normal.setIcon(new ImageIcon(WindowStart.class
				.getResource("/images/normal/sfondo_iniziale.jpg")));
		upper_panel.add(normal);
		addActionListeners(normal, exit, laws);

		main_panel.add(upper_panel, BorderLayout.NORTH);
		main_panel.add(buttons_panel, BorderLayout.SOUTH);

		upper_panel.setPreferredSize(new Dimension(600, 400));
		setContentPane(main_panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		// pack() per impacchettare la finestra in base alla dimensione dei
		// componenti
		pack();

		// finestra al centro dello schermo
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int pos_x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int pos_y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(pos_x, pos_y);

	}

	private void addActionListeners(JButton normal, JButton exit, JButton laws) {

		normal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ScacchieraFrame();
				WindowStart.this.dispose();
			}
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
				// new LawsWindow();
			}
		});

	}

}
