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

public class StatisticReportMenuPanel extends BasePanel{
	private JLabel lblReader, lblDoc, lblUser, lblSheet, lblStatReport, lblSearch, lblAboutUs, lblRecentDoc, lblBorrowedDoc, lblMostBorrowedDoc, lblOverdueReader;

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
		JLabel lblMain = createLabel("Statistic & Report", new Font("Allura", 1, 40), 200, 50, new Color(255, 255, 141));
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
		addMenuStatisticReport();
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
		    	StatisticReportMenuPanel.this.setVisible(false);
		    	Component c = (Component) StatisticReportMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) StatisticReportMenuPanel.this.getParent();
		    		mlp.showReaderPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) StatisticReportMenuPanel.this.getParent();
			    	mp.showReaderPanel();
		    	}
		    }
		});
		
		lblDoc.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	StatisticReportMenuPanel.this.setVisible(false);
		    	Component c = (Component) StatisticReportMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) StatisticReportMenuPanel.this.getParent();
			    	mp.showDocPanel();
		    	}
		    	
		    }
		});
		
		lblUser.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	StatisticReportMenuPanel.this.setVisible(false);
		    	Component c = (Component) StatisticReportMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) StatisticReportMenuPanel.this.getParent();
			    	mp.showUserPanel();
		    	}
		    }
		});
		
		lblSheet.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	StatisticReportMenuPanel.this.setVisible(false);
		    	Component c = (Component) StatisticReportMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) StatisticReportMenuPanel.this.getParent();
		    		mlp.showSheetPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) StatisticReportMenuPanel.this.getParent();
			    	mp.showSheetPanel();
		    	}
		    }
		});
		
		lblStatReport.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	
		    }
		});
		
		lblSearch.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	StatisticReportMenuPanel.this.setVisible(false);
		    	Component c = (Component) StatisticReportMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) StatisticReportMenuPanel.this.getParent();
		    		mlp.showSearchPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) StatisticReportMenuPanel.this.getParent();
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
		
		
		lblRecentDoc.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	StatisticReportMenuPanel.this.setVisible(false);
		    	Component c = (Component) StatisticReportMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) StatisticReportMenuPanel.this.getParent();
		    		mlp.showStatisticReportRecIdleDocPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) StatisticReportMenuPanel.this.getParent();
			    	mp.showStatisticReportRecIdleDocPanel();
		    	}
		    }
		});
		
		lblBorrowedDoc.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	StatisticReportMenuPanel.this.setVisible(false);
		    	Component c = (Component) StatisticReportMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) StatisticReportMenuPanel.this.getParent();
		    		mlp.showStatisticReportBorrowedDocPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) StatisticReportMenuPanel.this.getParent();
			    	mp.showStatisticReportBorrowedDocPanel();
		    	}
		    }
		});
		
		lblMostBorrowedDoc.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	StatisticReportMenuPanel.this.setVisible(false);
		    	Component c = (Component) StatisticReportMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) StatisticReportMenuPanel.this.getParent();
		    		mlp.showStatisticReportMostBrDocPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) StatisticReportMenuPanel.this.getParent();
			    	mp.showStatisticReportMostBrDocPanel();
		    	}
		    }
		});
		
		lblOverdueReader.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	StatisticReportMenuPanel.this.setVisible(false);
		    	Component c = (Component) StatisticReportMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) StatisticReportMenuPanel.this.getParent();
		    		mlp.showStatisticReportOverdueReaderPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) StatisticReportMenuPanel.this.getParent();
			    	mp.showStatisticReportOverdueReaderPanel();
		    	}
		    }
		});
	}

//	private void addImageIconToKB() {
//		try {
//			Image imgReader = ImageIO.read(getClass().getResource("/resources/images/study.png"));
//			lblReader.setIcon(new ImageIcon(imgReader));
//
//			Image imgDoc = ImageIO.read(getClass().getResource("/resources/images/doc.png"));
//			lblDoc.setIcon(new ImageIcon(imgDoc));
//
//			Image imgUser = ImageIO.read(getClass().getResource("/resources/images/reader.png"));
//			lblUser.setIcon(new ImageIcon(imgUser));
//
//			Image imgSheet = ImageIO.read(getClass().getResource("/resources/images/file.png"));
//			lblSheet.setIcon(new ImageIcon(imgSheet));
//
//			Image imgStatRep = ImageIO.read(getClass().getResource("/resources/images/statistic_rep_1.png"));
//			lblStatReport.setIcon(new ImageIcon(imgStatRep));
//
//			Image imgSearch = ImageIO.read(getClass().getResource("/resources/images/search_ic_0.png"));
//			lblSearch.setIcon(new ImageIcon(imgSearch));
//
//			Image imgAboutUs = ImageIO.read(getClass().getResource("/resources/images/about_us.png"));
//			lblAboutUs.setIcon(new ImageIcon(imgAboutUs));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	private void addImageIconToKB() {
		try {
			Image imgReader = ImageIO.read(getClass().getResource("/resources/images/study.png"));
			lblReader.setIcon(new ImageIcon(imgReader));

			Image imgDoc = ImageIO.read(getClass().getResource("/resources/images/doc.png"));
			lblDoc.setIcon(new ImageIcon(imgDoc));

			Image imgUser = ImageIO.read(getClass().getResource("/resources/images/reader.png"));
			lblUser.setIcon(new ImageIcon(imgUser));

			Image imgSheet = ImageIO.read(getClass().getResource("/resources/images/file.png"));
			lblSheet.setIcon(new ImageIcon(imgSheet));

			Image imgStatRep = ImageIO.read(getClass().getResource("/resources/images/statistic_rep_1.png"));
			lblStatReport.setIcon(new ImageIcon(imgStatRep));

			Image imgSearch = ImageIO.read(getClass().getResource("/resources/images/search_ic_0.png"));
			lblSearch.setIcon(new ImageIcon(imgSearch));

			Image imgAboutUs = ImageIO.read(getClass().getResource("/resources/images/about_us.png"));
			lblAboutUs.setIcon(new ImageIcon(imgAboutUs));
			
			Image imgRecentIdle = ImageIO.read(getClass().getResource("/resources/images/idle.png"));
			Image imgBorrowedDoc = ImageIO.read(getClass().getResource("/resources/images/borrowed.png"));
			Image imgMostBorrowedDoc = ImageIO.read(getClass().getResource("/resources/images/mostborrow.png"));
			Image imgOverdueReader = ImageIO.read(getClass().getResource("/resources/images/overdue.png"));
			
			lblRecentDoc.setIcon(new ImageIcon(imgRecentIdle));
			lblBorrowedDoc.setIcon(new ImageIcon(imgBorrowedDoc));
			lblMostBorrowedDoc.setIcon(new ImageIcon(imgMostBorrowedDoc));
			lblOverdueReader.setIcon(new ImageIcon(imgOverdueReader));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addMenuStatisticReport() {
		Font f1 = new Font("Coquin", 0, 30);
		lblRecentDoc = createLabel("<html>&nbsp;Recent&nbsp;Idle<p>&nbsp;&nbsp;Document</html>", f1, 260, 240, Color.white);
		lblRecentDoc.setSize(200, 200);
		lblRecentDoc.setHorizontalTextPosition(JLabel.CENTER);
		lblRecentDoc.setVerticalTextPosition(JLabel.BOTTOM);
		lblRecentDoc.setBackground(new Color(145, 85, 190));
		lblRecentDoc.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblRecentDoc);
		
		lblBorrowedDoc = createLabel("<html>&nbsp;&nbsp;Borrowed&nbsp;<p>&nbsp;&nbsp;Document</html>", f1, 510, 240, Color.white);
		lblBorrowedDoc.setSize(200, 200);
		lblBorrowedDoc.setHorizontalTextPosition(JLabel.CENTER);
		lblBorrowedDoc.setVerticalTextPosition(JLabel.BOTTOM);
		lblBorrowedDoc.setBackground(new Color(51, 182, 241));
		lblBorrowedDoc.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblBorrowedDoc);
		
		lblMostBorrowedDoc = createLabel("<html>&nbsp;Most&nbsp;Borrowed&nbsp;<p>&nbsp;&nbsp;Document</html>", f1, 760, 240, Color.white);
		lblMostBorrowedDoc.setSize(200, 200);
		lblMostBorrowedDoc.setHorizontalTextPosition(JLabel.CENTER);
		lblMostBorrowedDoc.setVerticalTextPosition(JLabel.BOTTOM);
		lblMostBorrowedDoc.setBackground(new Color(168, 96, 74));
		lblMostBorrowedDoc.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblMostBorrowedDoc);
		
		lblOverdueReader = createLabel("<html>&nbsp;&nbsp;Overdue<p>&nbsp;&nbsp;Reader</html>", f1, 260, 480, Color.white);
		lblOverdueReader.setSize(200, 200);
		lblOverdueReader.setHorizontalTextPosition(JLabel.CENTER);
		lblOverdueReader.setVerticalTextPosition(JLabel.BOTTOM);
		lblOverdueReader.setBackground(new Color(117, 133, 185));
		lblOverdueReader.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblOverdueReader);

		
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general.png"));
			g2d.drawImage(imgLabelGeneral, 125, 45, StatisticReportMenuPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Statistic & Report", 155, 105);
		
		try {
			Image imgBluredBg = ImageIO.read(getClass().getResource("/resources/images/bg_blur_6_0.png"));
			g2d.drawImage(imgBluredBg, 198,185, StatisticReportMenuPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
