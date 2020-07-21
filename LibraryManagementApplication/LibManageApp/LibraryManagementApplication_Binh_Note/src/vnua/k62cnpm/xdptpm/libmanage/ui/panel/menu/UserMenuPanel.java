package vnua.k62cnpm.xdptpm.libmanage.ui.panel.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import vnua.k62cnpm.xdptpm.libmanage.aboutus.AboutUsFrame;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainLibrarianPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundedBorder;

public class UserMenuPanel extends BasePanel{
	private JLabel lblReader, lblDoc, lblUser, lblSheet, lblStatReport, lblSearch, lblAboutUs, lblUserTwo, lblLIAccount;

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
		addKButton();
		addImageIconToKB();
		JLabel lblMain = createLabel("User Management", new Font("Allura", 1, 40), 200, 50, new Color(255, 255, 141));
		lblMain.setBackground(new Color(42, 46, 55));
		lblMain.setSize(400, 50);
		add(lblMain);
	}

	private void addKButton() {

		// ------NEW FONT-----------------------------NEW
		// FONT-----------------------------NEW FONT-----------------------NEW
		// FONT------------------------------------
		// ::::::::::::::::::::::NEW FONT::::::::::::::::::::::::::::::NEW
		// FONT:::::::::::::::::::::::::::::::NEW FONT::::::::::::::::::::::
		new ReaderPanel().createNewFont();
		createJLabel();
		addMenuUser();
		addMouseListenerForJLabel();
	}

	private void createJLabel() {
		Font f1 = new Font("Coquin", 0, 25);
		lblReader = createLabel("<html>&nbsp;&nbsp;Reader<p>Management</html>", f1, 0, 0, Color.white);
		lblReader.setSize(125, 110);
		lblReader.setHorizontalTextPosition(JLabel.CENTER);
		lblReader.setVerticalTextPosition(JLabel.BOTTOM);
		lblReader.setBackground(new Color(68, 71, 142));
		lblReader.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblReader);

		lblDoc = createLabel("<html>&nbsp;Document<p>Management</html>", f1, 0, 110, Color.white);
		lblDoc.setSize(125, 110);
		lblDoc.setHorizontalTextPosition(JLabel.CENTER);
		lblDoc.setVerticalTextPosition(JLabel.BOTTOM);
		lblDoc.setBackground(new Color(255, 143, 23));
		lblDoc.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblDoc);

		lblUser = createLabel("<html>&nbsp;&nbsp;User<p>Management</html>", f1, 0, 220, Color.white);
		lblUser.setSize(125, 110);
		lblUser.setHorizontalTextPosition(JLabel.CENTER);
		lblUser.setVerticalTextPosition(JLabel.BOTTOM);
		lblUser.setBackground(new Color(71, 202, 161));
		lblUser.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblUser);

		lblSheet = createLabel("<html>&nbsp&nbsp;Sheet<p>Management</html>", f1, 0, 330, Color.white);
		lblSheet.setSize(125, 110);
		lblSheet.setHorizontalTextPosition(JLabel.CENTER);
		lblSheet.setVerticalTextPosition(JLabel.BOTTOM);
		lblSheet.setBackground(new Color(143, 143, 133));
		lblSheet.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblSheet);

		lblStatReport = createLabel("<html>Statistic&nbsp;&&nbsp;<p>&nbsp;&nbsp;Report</html>", f1, 0,
				440, Color.white);
		lblStatReport.setSize(125, 110);
		lblStatReport.setHorizontalTextPosition(JLabel.CENTER);
		lblStatReport.setVerticalTextPosition(JLabel.BOTTOM);
		lblStatReport.setBackground(new Color(247, 65, 109));
		lblStatReport.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblStatReport);

		lblSearch = createLabel("<html>&nbsp;&nbsp;Search</html>", f1, 0, 550, Color.white);
		lblSearch.setSize(125, 110);
		lblSearch.setHorizontalTextPosition(JLabel.CENTER);
		lblSearch.setVerticalTextPosition(JLabel.BOTTOM);
		lblSearch.setBackground(new Color(102, 204, 255));//236, 226, 67
		lblSearch.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblSearch);

		lblAboutUs = createLabel("<html>&nbsp;About&nbsp;Us</html>", f1, 0, 660, Color.white);
		lblAboutUs.setSize(125, 110);
		lblAboutUs.setHorizontalTextPosition(JLabel.CENTER);
		lblAboutUs.setVerticalTextPosition(JLabel.BOTTOM);
		lblAboutUs.setBackground(new Color(203, 165, 114));
		lblAboutUs.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblAboutUs);
	}


	private void addMouseListenerForJLabel() {
		lblReader.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	UserMenuPanel.this.setVisible(false);
		    	Component c = (Component) UserMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) UserMenuPanel.this.getParent();
		    		mlp.showReaderPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) UserMenuPanel.this.getParent();
			    	mp.showReaderPanel();
		    	}
		    }
		});
		
		lblDoc.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	UserMenuPanel.this.setVisible(false);
		    	Component c = (Component) UserMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) UserMenuPanel.this.getParent();
			    	mp.showDocPanel();
		    	}
		    	
		    }
		});
		
		lblUser.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	
		    }
		});
		
		lblSheet.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	UserMenuPanel.this.setVisible(false);
		    	Component c = (Component) UserMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) UserMenuPanel.this.getParent();
		    		mlp.showSheetPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) UserMenuPanel.this.getParent();
			    	mp.showSheetPanel();
		    	}
		    }
		});
		
		lblStatReport.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	UserMenuPanel.this.setVisible(false);
		    	Component c = (Component) UserMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) UserMenuPanel.this.getParent();
		    		mlp.showStatisticReportPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) UserMenuPanel.this.getParent();
			    	mp.showStatisticReportPanel();
		    	}
		    }
		});
		
		lblSearch.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	UserMenuPanel.this.setVisible(false);
		    	Component c = (Component) UserMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) UserMenuPanel.this.getParent();
		    		mlp.showSearchPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) UserMenuPanel.this.getParent();
			    	mp.showSearchPanel();
		    	}
		    }
		});
		
		lblAboutUs.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	AboutUsFrame auf = new AboutUsFrame();
		    	auf.pack();
		    	auf.setLocationRelativeTo(null);
		    	auf.setVisible(true);
		    }
		});
		
		lblUserTwo.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	UserMenuPanel.this.setVisible(false);
		    	Component c = (Component) UserMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) UserMenuPanel.this.getParent();
			    	mp.showUserManagePanel();
		    	}
		    }
		});
		
		lblLIAccount.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	UserMenuPanel.this.setVisible(false);
		    	Component c = (Component) UserMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) UserMenuPanel.this.getParent();
			    	mp.showLogInAccountManagePanel();
		    	}
		    }
		});
	}

	private void addImageIconToKB() {
		try {
			Image imgReader = ImageIO.read(getClass().getResource("/resources/images/study.png"));
			lblReader.setIcon(new ImageIcon(imgReader));

			Image imgDoc = ImageIO.read(getClass().getResource("/resources/images/doc.png"));
			lblDoc.setIcon(new ImageIcon(imgDoc));

			Image imgUser = ImageIO.read(getClass().getResource("/resources/images/reader.png"));
			lblUser.setIcon(new ImageIcon(imgUser));
			lblUserTwo.setIcon(new ImageIcon(imgUser));

			Image imgSheet = ImageIO.read(getClass().getResource("/resources/images/file.png"));
			lblSheet.setIcon(new ImageIcon(imgSheet));

			Image imgStatRep = ImageIO.read(getClass().getResource("/resources/images/statistic_rep_1.png"));
			lblStatReport.setIcon(new ImageIcon(imgStatRep));

			Image imgSearch = ImageIO.read(getClass().getResource("/resources/images/search_ic_0.png"));
			lblSearch.setIcon(new ImageIcon(imgSearch));

			Image imgAboutUs = ImageIO.read(getClass().getResource("/resources/images/about_us.png"));
			lblAboutUs.setIcon(new ImageIcon(imgAboutUs));

			Image imgLIAccount = ImageIO.read(getClass().getResource("/resources/images/log_in_acc.png"));
			lblLIAccount.setIcon(new ImageIcon(imgLIAccount));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addMenuUser() {
		Font f1 = new Font("Coquin", 0, 30);
		lblUserTwo = createLabel("<html>&nbsp;&nbsp;&nbsp;User<p>&nbsp;Management</html>", f1, 280, 260, Color.white);
		lblUserTwo.setSize(200, 180);
		lblUserTwo.setHorizontalTextPosition(JLabel.CENTER);
		lblUserTwo.setVerticalTextPosition(JLabel.BOTTOM);
		lblUserTwo.setBackground(new Color(56, 193, 163));
		lblUserTwo.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblUserTwo);
		
		lblLIAccount = createLabel("<html>Log&nbsp;in&nbsp;Account<p>&nbsp;Management</html>", f1, 530, 260, Color.white);
		lblLIAccount.setSize(200, 180);
		lblLIAccount.setHorizontalTextPosition(JLabel.CENTER);
		lblLIAccount.setVerticalTextPosition(JLabel.BOTTOM);
		lblLIAccount.setBackground(new Color(221, 113, 90));
		lblLIAccount.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblLIAccount);
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general.png"));
			g2d.drawImage(imgLabelGeneral, 125, 45, UserMenuPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("User Management", 155, 105);
		
		try {
			Image imgBluredBg = ImageIO.read(getClass().getResource("/resources/images/bg_blur_4_0.png"));
			g2d.drawImage(imgBluredBg, 215,195, UserMenuPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
