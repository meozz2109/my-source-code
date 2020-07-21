package vnua.k62cnpm.xdptpm.libmanage.ui.panel.beginning;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import vnua.k62cnpm.xdptpm.libmanage.frame.BeginningReaderFrame;
import vnua.k62cnpm.xdptpm.libmanage.frame.RoleConfirmFrame;
import vnua.k62cnpm.xdptpm.libmanage.ui.login.LogInFrame;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainLibrarianPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.menu.SheetMenuPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundedBorder;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.ToastMessage;

public class MainRoleConfirmPanel extends BasePanel{
	private JLabel lblIntro, lblChoose, lblReader, lblLibAndMan;
	private ToastMessage tm;
	private Thread th1;
	
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
		addMenuRoleOption();
		addImageIconToKB();
		addLabelMouseAdapter();
		th1 = new Thread(new Runnable() {

			@Override
			public void run() {
				if(th1.isInterrupted()) {
					return;
				}
				try {
					Thread.sleep(500);
					tm = new ToastMessage("Welcome to VNUA - Luong Dinh Cua Library Management System, pal!", 
							RoleConfirmFrame.WIDTH_FRAME/2+80,
							RoleConfirmFrame.HEIGHT_FRAME/2+150,
							780,
							3500);
					tm.showtoast();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		th1.setDaemon(true);
		th1.start();
	}
	private void addLabelMouseAdapter() {
		lblReader.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {
		    	tm.disposeToast();
		    	th1.interrupt();
		    	((Window) getRootPane().getParent()).dispose();
		    	new BeginningReaderFrame().setVisible(true);
		    }
		});
		
		lblLibAndMan.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	tm.disposeToast();
		    	th1.interrupt();
		    	((Window) getRootPane().getParent()).dispose();
		    	new LogInFrame().setVisible(true);
		    }
		});
	}

	private void addMenuRoleOption() {
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Candara", Font.ITALIC, 32);
		Font f7 = new Font("Blackjack", Font.PLAIN, 32);
		Font f2 = new Font("Blackjack", Font.ITALIC, 40);
		Font f5 = new Font("Blackjack", Font.PLAIN, 40);
		Font f4 = new Font("Blackjack", Font.ITALIC, 45);
		Font f6 = new Font("Blackjack", Font.PLAIN, 45);
		Font f3 = new Font("Candara", Font.BOLD, 40);
		
		lblIntro = createLabel("Luong Dinh Cua LMS - VNUA", f5, 100, 5, new Color(255,255, 141));
		lblIntro.setSize(700, lblIntro.getHeight());
		lblIntro.setBackground(new Color(42, 46, 55));
		add(lblIntro);
		
		lblChoose = createLabel("Please choose your role:", f3, 110, 95, new Color(223, 223, 223));
		lblChoose.setBackground(new Color(42, 46, 55));
		lblChoose.setSize(500, lblChoose.getHeight());
		add(lblChoose);
		
		lblReader = createLabel("<html>&nbsp;&nbsp;Reader</html>", f6, 140, 180, Color.white);
		lblReader.setSize(150, 150);
		lblReader.setHorizontalTextPosition(JLabel.CENTER);
		lblReader.setVerticalTextPosition(JLabel.BOTTOM);
		lblReader.setBackground(new Color(15, 154, 107));
		lblReader.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblReader);
		
		lblLibAndMan = createLabel("<html>Librarian&nbsp;&<p>&nbsp;&nbsp;Manager</html>", f7, 355, 180, Color.white);
		lblLibAndMan.setSize(150, 150);
		lblLibAndMan.setHorizontalTextPosition(JLabel.CENTER);
		lblLibAndMan.setVerticalTextPosition(JLabel.BOTTOM);
		lblLibAndMan.setBackground(new Color(85, 118, 161));
		lblLibAndMan.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblLibAndMan);
		
	}

	private void addImageIconToKB() {
		try {
			Image imgReader = ImageIO.read(getClass().getResource("/resources/images/reader_1.png"));
			lblReader.setIcon(new ImageIcon(imgReader));

			Image imgUser = ImageIO.read(getClass().getResource("/resources/images/reader.png"));
			lblLibAndMan.setIcon(new ImageIcon(imgUser));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		try {
			Image imgIcon = ImageIO.read(getClass().getResource("/resources/images/icon_48.png"));
			g2d.drawImage(imgIcon, 50, 5, MainRoleConfirmPanel.this);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		Font f1 = new Font("Montserrat", Font.PLAIN, 32);
//		Font f2 = new Font("Highland", 0, 50);
//		g2d.setFont(f1);
//		g2d.setColor(new Color(223, 223, 223));
//		g2d.drawString("Please choose your role: ", 120, 130);

		g2d.setColor(new Color(234, 83, 65));
		try {
			Image imgBg = ImageIO.read(getClass().getResource("/resources/images/role_bg_blur.png"));
			g2d.drawImage(imgBg, 67, 50, MainRoleConfirmPanel.this);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
