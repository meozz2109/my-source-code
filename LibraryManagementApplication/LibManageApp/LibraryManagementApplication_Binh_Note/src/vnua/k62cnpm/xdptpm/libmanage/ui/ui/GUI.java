package vnua.k62cnpm.xdptpm.libmanage.ui.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI extends JFrame{
	public static final int HEIGHT_FRAME = 800;
	public static final int WIDTH_FRAME = 1000;
	public static final String TITLE = "Graphics UI One";

	public GUI() {
		init();
		addComp();
		addEvent();
	}

	public void init() {
		setTitle(TITLE);
		setSize(WIDTH_FRAME, HEIGHT_FRAME);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.white);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(new CardLayout());
	}

	public void addEvent() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int rs = JOptionPane.showConfirmDialog(GUI.this, "OK", "Alert", JOptionPane.YES_NO_OPTION);
				if (rs == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

	}

	public void addComp() {
//		mp = new MainPanel();
//		add(mp);
	}
}
