package vnua.k62cnpm.xdptpm.libmanage.frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vnua.k62cnpm.xdptpm.libmanage.ui.panel.beginning.MainBeginningReaderPanel;

public class BeginningReaderFrame extends JFrame{
	public static final int HEIGHT_FRAME = 700;
	public static final int WIDTH_FRAME = 950;
	public static final String TITLE = "Luong Dinh Cua Library Management System - Vietnam National University of Agriculture";
	private MainBeginningReaderPanel mbrp;

	public BeginningReaderFrame() {
		init();
		addComp();
		addEvent();
	}

	public void init() {
		setTitle(TITLE);
		setSize(WIDTH_FRAME, HEIGHT_FRAME);
		setLocation(300, 100);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.white);
		try {
            Image imgIcon = ImageIO.read(getClass().getResource("/resources/images/icon.png"));
            setIconImage(imgIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(new CardLayout());
	}

	public void addEvent() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int rs = JOptionPane.showConfirmDialog(BeginningReaderFrame.this, "Do you actually want to exit?", "Alert", JOptionPane.YES_NO_OPTION);
				if (rs == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
	}

	public void addComp() {
		mbrp = new MainBeginningReaderPanel();
		add(mbrp);
	}
}
