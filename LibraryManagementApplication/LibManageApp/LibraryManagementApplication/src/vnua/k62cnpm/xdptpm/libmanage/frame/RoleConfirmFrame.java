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
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.beginning.MainRoleConfirmPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.GUI;

public class RoleConfirmFrame extends JFrame{
	public static final int HEIGHT_FRAME = 450;
	public static final int WIDTH_FRAME = 650;
	public static final String TITLE = "Luong Dinh Cua Library Management System - Vietnam National University of Agriculture";
	private MainRoleConfirmPanel mrcp;

	public RoleConfirmFrame() {
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
				int rs = JOptionPane.showConfirmDialog(RoleConfirmFrame.this, "Do you actually want to exit?", "Alert", JOptionPane.YES_NO_OPTION);
				if (rs == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

	}

	public void addComp() {
		mrcp = new MainRoleConfirmPanel();
		add(mrcp);
	}
}
