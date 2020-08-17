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

public class DocumentMenuPanel extends BasePanel{
	private JLabel lblReader, lblDoc, lblUser, lblSheet, lblStatReport, lblSearch, lblAboutUs, lblDocTwo, lblPublisher, lblSubject, lblAuthor, lblLocation, lblReceipt, lblSupplier, lblDetailedDoc;

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
		JLabel lblMain = createLabel("Document Management", new Font("Allura", 1, 40), 200, 50, new Color(255, 255, 141));
		lblMain.setBackground(new Color(42, 46, 55));
		lblMain.setSize(400, 50);
		add(lblMain);
	}

	private void addMenuDocument() {
		Font f1 = new Font("Coquin", 0, 30);
		lblDocTwo = createLabel("<html>&nbsp;Document<p>Management</html>", f1, 280, 260, Color.white);
		lblDocTwo.setSize(150, 150);
		lblDocTwo.setHorizontalTextPosition(JLabel.CENTER);
		lblDocTwo.setVerticalTextPosition(JLabel.BOTTOM);
		lblDocTwo.setBackground(new Color(20, 123, 182));
		lblDocTwo.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblDocTwo);
		
		lblPublisher = createLabel("<html>&nbsp;Publisher<p>Management</html>", f1, 480, 260, Color.white);
		lblPublisher.setSize(150, 150);
		lblPublisher.setHorizontalTextPosition(JLabel.CENTER);
		lblPublisher.setVerticalTextPosition(JLabel.BOTTOM);
		lblPublisher.setBackground(new Color(145, 85, 190));
		lblPublisher.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblPublisher);
		
		lblSubject = createLabel("<html>&nbsp;&nbsp;Subject<p>Management</html>", f1, 680, 260, Color.white);
		lblSubject.setSize(150, 150);
		lblSubject.setHorizontalTextPosition(JLabel.CENTER);
		lblSubject.setVerticalTextPosition(JLabel.BOTTOM);
		lblSubject.setBackground(new Color(119, 172, 159));
		lblSubject.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblSubject);
		
		lblAuthor = createLabel("<html>&nbsp;&nbsp;Author<p>Management</html>", f1, 880, 260, Color.white);
		lblAuthor.setSize(150, 150);
		lblAuthor.setHorizontalTextPosition(JLabel.CENTER);
		lblAuthor.setVerticalTextPosition(JLabel.BOTTOM);
		lblAuthor.setBackground(new Color(243, 105, 71));
		lblAuthor.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblAuthor);
		
		lblLocation = createLabel("<html>&nbsp;Location<p>Management</html>", f1, 280, 480, Color.white);
		lblLocation.setSize(150, 150);
		lblLocation.setHorizontalTextPosition(JLabel.CENTER);
		lblLocation.setVerticalTextPosition(JLabel.BOTTOM);
		lblLocation.setBackground(new Color(110, 28, 43));
		lblLocation.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblLocation);
		
		lblReceipt = createLabel("<html>Receipt&nbsp;Note<p>Management</html>", f1, 480, 480, Color.white);
		lblReceipt.setSize(150, 150);
		lblReceipt.setHorizontalTextPosition(JLabel.CENTER);
		lblReceipt.setVerticalTextPosition(JLabel.BOTTOM);
		lblReceipt.setBackground(new Color(150, 198, 104));
		lblReceipt.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblReceipt);
		
		lblSupplier = createLabel("<html>&nbsp;Supplier<p>Management</html>", f1, 680, 480, Color.white);
		lblSupplier.setSize(150, 150);
		lblSupplier.setHorizontalTextPosition(JLabel.CENTER);
		lblSupplier.setVerticalTextPosition(JLabel.BOTTOM);
		lblSupplier.setBackground(new Color(67, 106, 119));
		lblSupplier.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblSupplier);
		
		lblDetailedDoc = createLabel("<html>Detailed-Doc<p>Management</html>", f1, 880, 480, Color.white);
		lblDetailedDoc.setSize(150, 150);
		lblDetailedDoc.setHorizontalTextPosition(JLabel.CENTER);
		lblDetailedDoc.setVerticalTextPosition(JLabel.BOTTOM);
		lblDetailedDoc.setBackground(new Color(135, 155, 203));
		lblDetailedDoc.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblDetailedDoc);
	}

	private void addKButton() {

		// ------NEW FONT-----------------------------NEW
		// FONT-----------------------------NEW FONT-----------------------NEW
		// FONT------------------------------------
		// ::::::::::::::::::::::NEW FONT::::::::::::::::::::::::::::::NEW
		// FONT:::::::::::::::::::::::::::::::NEW FONT::::::::::::::::::::::
		new ReaderPanel().createNewFont();
		createJLabel();
		addMenuDocument();
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
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) DocumentMenuPanel.this.getParent();
		    		mlp.showReaderPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
			    	mp.showReaderPanel();
		    	}
		    }
		});
		
		lblDoc.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	
		    }
		});
		
		lblUser.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
			    	mp.showUserPanel();
		    	}
		    }
		});
		
		lblSheet.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) DocumentMenuPanel.this.getParent();
		    		mlp.showSheetPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
			    	mp.showSheetPanel();
		    	}
		    }
		});
		
		lblStatReport.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) DocumentMenuPanel.this.getParent();
		    		mlp.showStatisticReportPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
			    	mp.showStatisticReportPanel();
		    	}
		    }
		});
		
		lblSearch.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) DocumentMenuPanel.this.getParent();
		    		mlp.showSearchPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
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
		
		lblDocTwo.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
			    	mp.showDocumentManagePanel();
		    	}
		    }
		});
		
		lblPublisher.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
			    	mp.showPublisherManagePanel();
		    	}
		    }
		});
		
		lblSubject.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
			    	mp.showSubjectManagePanel();
		    	}
		    }
		});
		
		lblAuthor.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
			    	mp.showAuthorManagePanel();
		    	}
		    }
		});
		
		lblLocation.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
			    	mp.showLocationManagePanel();
		    	}
		    }
		});

		lblReceipt.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
			    	mp.showReceiptManagePanel();
		    	}
		    }
		});

		lblSupplier.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
			    	mp.showSupplierManagePanel();
		    	}
		    }
		});

		lblDetailedDoc.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	DocumentMenuPanel.this.setVisible(false);
		    	Component c = (Component) DocumentMenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) DocumentMenuPanel.this.getParent();
			    	mp.showDetailedDocManagePanel();
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

			Image imgSheet = ImageIO.read(getClass().getResource("/resources/images/file.png"));
			lblSheet.setIcon(new ImageIcon(imgSheet));

			Image imgStatRep = ImageIO.read(getClass().getResource("/resources/images/statistic_rep_1.png"));
			lblStatReport.setIcon(new ImageIcon(imgStatRep));

			Image imgSearch = ImageIO.read(getClass().getResource("/resources/images/search_ic_0.png"));
			lblSearch.setIcon(new ImageIcon(imgSearch));

			Image imgAboutUs = ImageIO.read(getClass().getResource("/resources/images/about_us.png"));
			lblAboutUs.setIcon(new ImageIcon(imgAboutUs));

			Image imgDocTwo = ImageIO.read(getClass().getResource("/resources/images/doc.png"));
			lblDocTwo.setIcon(new ImageIcon(imgDocTwo));

			Image imgPublisher = ImageIO.read(getClass().getResource("/resources/images/pub1.png"));
			lblPublisher.setIcon(new ImageIcon(imgPublisher));

			Image imgSubject = ImageIO.read(getClass().getResource("/resources/images/translate.png"));
			lblSubject.setIcon(new ImageIcon(imgSubject));

			Image imgAuthor = ImageIO.read(getClass().getResource("/resources/images/writer.png"));
			lblAuthor.setIcon(new ImageIcon(imgAuthor));

			Image imgLocation = ImageIO.read(getClass().getResource("/resources/images/travel.png"));
			lblLocation.setIcon(new ImageIcon(imgLocation));

			Image imgReceipt = ImageIO.read(getClass().getResource("/resources/images/contract.png"));
			lblReceipt.setIcon(new ImageIcon(imgReceipt));

			Image imgSupplier = ImageIO.read(getClass().getResource("/resources/images/inventory.png"));
			lblSupplier.setIcon(new ImageIcon(imgSupplier));

			Image imgDetailedDoc = ImageIO.read(getClass().getResource("/resources/images/detailed_doc.png"));
			lblDetailedDoc.setIcon(new ImageIcon(imgDetailedDoc));

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
			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general_0.png"));
			g2d.drawImage(imgLabelGeneral, 125, 45, DocumentMenuPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Document Management", 180, 105);
		
//		g2d.setColor(new Color(105, 145, 197));
//		g2d.drawRoundRect(205, 200, 900, 500, 100, 100);
//		
		try {
			Image imgBluredBg = ImageIO.read(getClass().getResource("/resources/images/bg_blur_2_0.png"));
			g2d.drawImage(imgBluredBg, 215,195, DocumentMenuPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
