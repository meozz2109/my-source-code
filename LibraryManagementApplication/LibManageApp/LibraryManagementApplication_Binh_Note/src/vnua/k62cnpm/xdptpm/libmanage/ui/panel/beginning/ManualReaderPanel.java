package vnua.k62cnpm.xdptpm.libmanage.ui.panel.beginning;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import vnua.k62cnpm.xdptpm.libmanage.doc.Document;
import vnua.k62cnpm.xdptpm.libmanage.frame.RoleConfirmFrame;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;

public class ManualReaderPanel extends BasePanel{
	private JLabel lblIntro;
	private JTextArea taManual;
	private JScrollPane scrollPaneManual;
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;

	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
	}

	@Override
	public void addEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComp() {
		Font f5 = new Font("Blackjack", 0, 20);
		Font f2 = new Font("Blackjack", Font.PLAIN, 40);
		Font f4 = new Font("Blackjack", Font.PLAIN, 45);
		Font f3 = new Font("Exo 2", Font.PLAIN, 40);
		
		lblIntro = createLabel("Luong Dinh Cua Library's Manual", f2, 70, 10, new Color(255,255, 141));
		lblIntro.setSize(700, lblIntro.getHeight()+10);
		lblIntro.setBackground(new Color(42, 46, 55));
		add(lblIntro);
		
		taManual = new JTextArea();
		taManual.setForeground(new Color(255, 255, 141));
		taManual.setBackground(new Color(42, 46, 55));
		taManual.setFont(new Font("Montserrat", Font.PLAIN, 20));
		taManual.setText(Document.MANUAL);
		scrollPaneManual = new JScrollPane(taManual);
		scrollPaneManual.setBackground(new Color(42, 46, 55));
		scrollPaneManual.setSize(710, 450);
		scrollPaneManual.setLocation(120, 80);
		add(scrollPaneManual);
		
		btnReturn = createJButton("", f5, 300, -180, 575, new Color(23, 24, 25), Color.white, BTN_RETURN);
		btnReturn.setSize(300, 75);
		add(btnReturn);
		
		btnTurnOff = createJButton("", f5, 300, 820, 570, new Color(23, 24, 25), Color.white, BTN_TURN_OFF);
		btnTurnOff.setSize(300, 75);
		add(btnTurnOff);
		
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				repaint();
			}
		});
		
		btnTurnOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				repaint();
//				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				repaint();
//				super.mouseExited(e);
			}
			
		});
		
	}
	

	@Override
    public void paint(Graphics g) {
    	// TODO Auto-generated method stub
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D) g;
    	try {
			Image imgIcon = ImageIO.read(getClass().getResource("/resources/images/icon_48.png"));
			g2d.drawImage(imgIcon, 10, 15, ManualReaderPanel.this);
			
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_1.png"));
			g2d.drawImage(imgButtonReturn, -20, 570, ManualReaderPanel.this);
			
			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_1.png"));
			g2d.drawImage(imgButtonTurnOff, 820, 560, ManualReaderPanel.this);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
	@Override
	protected void doClick(String name) {
		if(name.equals(BTN_RETURN)) {
			repaint();
			ManualReaderPanel.this.setVisible(false);
			MainBeginningReaderPanel mbrp = (MainBeginningReaderPanel) ManualReaderPanel.this.getParent();
			mbrp.showBeginningSearchReaderPanel();
		} else if(name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(ManualReaderPanel.this, "Do you actually want to exit?","Alert", JOptionPane.YES_NO_OPTION);
			if(rs==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}
