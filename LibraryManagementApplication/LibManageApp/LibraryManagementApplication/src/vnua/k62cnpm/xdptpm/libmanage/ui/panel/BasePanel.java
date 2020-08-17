package vnua.k62cnpm.xdptpm.libmanage.ui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.ICommon;


public abstract class BasePanel extends JPanel implements ICommon, ActionListener{
	public BasePanel() {
		init();
		addEvent();
		addComp();
	}
	
	protected JLabel createLabel(String text, Font f1, int x, int y, Color c) {
		JLabel lblTitle = new JLabel(text);

		// Sử dụng đối tượng FontMetrics để đo chi�?u rộng và chi�?u cao theo font chữ
		FontMetrics fontMetrics = getFontMetrics(f1);
		int wText = fontMetrics.stringWidth(lblTitle.getText());
		int hText = fontMetrics.getHeight();

		lblTitle.setSize(wText, hText);
		lblTitle.setForeground(c);

		lblTitle.setOpaque(true);

		lblTitle.setLocation(x, y);
		lblTitle.setFont(f1);
		return lblTitle;
	}
	
	protected JTextField createJTextField(Font f, int w, int x, int y, Color c) {
		JTextField tF = new JTextField();
		tF.setFont(f);
		FontMetrics fontMetrics = getFontMetrics(f);
		int hTfA = tF.getInsets().top*2 + fontMetrics.getHeight();
		tF.setSize(w, hTfA);
		tF.setForeground(c);
		tF.setOpaque(true);
		tF.setLocation(x, y);
		return tF;
	}
	
	protected JButton createJButton(String title, Font f1, int w, int x, int y, Color bc, Color fc, String name) {
		JButton btnSolve = new JButton(title);
		btnSolve.setFont(f1);
		FontMetrics fontMetrics = getFontMetrics(f1);
		int hTfA = btnSolve.getInsets().top*2 + fontMetrics.getHeight();
		btnSolve.setSize(w, hTfA);
		btnSolve.setLocation(x, y);
		btnSolve.setForeground(fc);
		btnSolve.setBackground(bc);
		btnSolve.addActionListener(this);
		btnSolve.setName(name);
		return btnSolve;
	}
	
	public void actionPerformed(ActionEvent e) {
		Component c = (Component) e.getSource();
		String name = c.getName();
		doClick(name);
	}

	protected void doClick(String name) {
	}
	
	public BufferedImage initImg(String name) {
		try {
			URL path = getClass().getResource("/resources/images/"+name);
			return ImageIO.read(path);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
