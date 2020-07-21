package vnua.k62cnpm.xdptpm.libmanage.ui.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class ToastMessage extends JFrame {
	private int mlSecond;
	
	// JWindow
	private JWindow w;

	public ToastMessage(String s, int x, int y, int width, int mlsecond) {
		w = new JWindow();
		this.mlSecond = mlsecond;
		
		// make the background transparent
		w.setBackground(new Color(0, 0, 0, 0));

		// create a panel
		JPanel p = new JPanel() {
			public void paintComponent(Graphics g) {
				int wid = width;
				int hei = g.getFontMetrics().getHeight();

				// draw the boundary of the toast and fill it
				g.setColor(Color.black);
				g.fillRoundRect(30, 30, wid + 90, hei + 20, 20, 20);
				g.setColor(Color.black);
				g.drawRoundRect(30, 30, wid + 90, hei + 20, 20, 20);

				// set the color of text
				Font font = new Font("Candara", 0, 25);
				g.setFont(font);
				g.setColor(new Color(255, 255, 255, 240));
				g.drawString(s, 40, 57);
				int t = 250;

				// draw the shadow of the toast
				for (int i = 0; i < 4; i++) {
					t -= 60;
					g.setColor(new Color(0, 0, 0, t));
					g.drawRoundRect(30 - i, 30 - i, wid + 90 + i * 2, hei + 20 + i * 2, 20, 20);
				}
			}
		};

		w.add(p);
		w.setSize(width, 100);
		w.setLocation(x, y);
	}

	// function to pop up the toast
	public void showtoast() {
		try {
			w.setOpacity(1);
			w.setVisible(true);

			// wait for some time
			Thread.sleep(mlSecond);

			// make the message disappear slowly
			for (double d = 1.0; d > 0.2; d -= 0.1) {
				Thread.sleep(100);
				w.setOpacity((float) d);
			}

			// set the visibility to false
			w.setVisible(false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void disposeToast() {
		dispose();
	}
}
