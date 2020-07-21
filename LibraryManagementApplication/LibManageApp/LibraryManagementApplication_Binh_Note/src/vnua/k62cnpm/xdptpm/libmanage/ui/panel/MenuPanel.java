package vnua.k62cnpm.xdptpm.libmanage.ui.panel;

import java.awt.BorderLayout;
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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import vnua.k62cnpm.xdptpm.libmanage.aboutus.AboutUsFrame;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundedBorder;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundedBorderClone;

public class MenuPanel extends BasePanel {
	private JLabel lblReader, lblDoc, lblUser, lblSheet, lblStatReport, lblSearch, lblAboutUs;
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
//        addDropDownMenu();
	}
	
	private void addDropDownMenu() {
		JPopupMenu popupMenu = new JPopupMenu();
		 
		JMenuItem menuItemS = new JMenuItem("S");
		popupMenu.add(menuItemS);
		 
		JMenuItem menuItemModifyPassword = new JMenuItem("Modify Password");
		popupMenu.add(menuItemModifyPassword);
		 
		JMenuItem menuItemSignOut = new JMenuItem("Sign out");
		popupMenu.add(menuItemSignOut);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/pop_up_icon.png"));
		 
//		JButton dropDownButton = DropDownButtonFactory.createDropDownButton(icon, popupMenu);
//		dropDownButton.setLocation(1110, 5);
//		dropDownButton.setSize(50, 25);
//		add(dropDownButton);
	}

	private void addKButton() {
		
        //------NEW FONT-----------------------------NEW FONT-----------------------------NEW FONT-----------------------NEW FONT------------------------------------
        //::::::::::::::::::::::NEW FONT::::::::::::::::::::::::::::::NEW FONT:::::::::::::::::::::::::::::::NEW FONT::::::::::::::::::::::
        createNewFont();        
        createJLabel();
        addMouseListenerForJLabel();
	}

	private void createJLabel() {
		Font f1 = new Font("Blackjack", Font.BOLD, 24);
        lblReader = createLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;Reader<p>Management</html>", f1, 0, 0, Color.white);
        lblReader.setSize(125, 110);
        lblReader.setHorizontalTextPosition(JLabel.CENTER);
        lblReader.setVerticalTextPosition(JLabel.BOTTOM);
        lblReader.setBackground(new Color(68, 71, 142));
        lblReader.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
        add(lblReader);
        
        lblDoc = createLabel("<html>&nbsp;&nbsp;Document<p>Management</html>", f1, 0, 110, Color.white);
        lblDoc.setSize(125, 110);
        lblDoc.setHorizontalTextPosition(JLabel.CENTER);
        lblDoc.setVerticalTextPosition(JLabel.BOTTOM);
        lblDoc.setBackground(new Color(255, 143, 23));
        lblDoc.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
        add(lblDoc);
        
        lblUser = createLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;User<p>Management</html>", f1, 0, 220, Color.white);
        lblUser.setSize(125, 110);
        lblUser.setHorizontalTextPosition(JLabel.CENTER);
        lblUser.setVerticalTextPosition(JLabel.BOTTOM);
        lblUser.setBackground(new Color(71, 202, 161));
        lblUser.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
        add(lblUser);
        
        lblSheet = createLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;Sheet<p>Management</html>", f1, 0, 330, Color.white);
        lblSheet.setSize(125, 110);
        lblSheet.setHorizontalTextPosition(JLabel.CENTER);
        lblSheet.setVerticalTextPosition(JLabel.BOTTOM);
        lblSheet.setBackground(new Color(143, 143, 133));
        lblSheet.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
        add(lblSheet);
        
        lblStatReport = createLabel("<html>&nbsp;Statistic&nbsp;&&nbsp;<p>&nbsp;&nbsp;&nbsp;&nbsp;Report</html>", f1, 0, 440, Color.white);
        lblStatReport.setSize(125, 110);
        lblStatReport.setHorizontalTextPosition(JLabel.CENTER);
        lblStatReport.setVerticalTextPosition(JLabel.BOTTOM);
        lblStatReport.setBackground(new Color(247, 65, 109));
        lblStatReport.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
        add(lblStatReport);
        
        lblSearch = createLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;Search</html>", f1, 0, 550, Color.white);
        lblSearch.setSize(125, 110);
        lblSearch.setHorizontalTextPosition(JLabel.CENTER);
        lblSearch.setVerticalTextPosition(JLabel.BOTTOM);
        lblSearch.setBackground(new Color(236, 226, 67));
        lblSearch.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
        add(lblSearch);
        
        lblAboutUs = createLabel("<html>&nbsp;&nbsp;&nbsp;About&nbsp;Us</html>", f1, 0, 660, Color.white);
        lblAboutUs.setSize(125, 110);
        lblAboutUs.setHorizontalTextPosition(JLabel.CENTER);
        lblAboutUs.setVerticalTextPosition(JLabel.BOTTOM);
        lblAboutUs.setBackground(new Color(203, 165, 114));
        lblAboutUs.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
        add(lblAboutUs);
	}

	public void createNewFont() {
		try {
            //create the font to use. Specify the size!
            Font Superhero = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/Superhero.ttf"));
            Font Ntypes = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/Ntypes.otf"));
            Font Blackjack = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/blackjack.otf"));
            Font ConeriaScript = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/Demo_ConeriaScript.ttf"));
            Font Tusj = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/FFF_Tusj.ttf"));
            Font Allura = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/Allura.otf"));
            Font Montserrat = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/Montserrat.ttf"));
            Font Ancient = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/TheAncient.ttf"));
            Font Amsterdam = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/Amsterdam-VwYy.ttf"));
            Font BeautifulPeople = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/BeautifulPeopleTwo.ttf"));
            Font QuickKiss = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/QuickKissPersonalUse-PxlZ.ttf"));
            Font VeganStyle = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/VeganStylePersonalUse-5Y58.ttf"));
            Font SweetSensation = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/SweetSensationsPersonalUse-lRgq.ttf"));
            Font Remachine = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/RemachineScript_Personal_Use.ttf"));
            Font HistoricSeattle = Font.createFont(Font.TRUETYPE_FONT, new File("E:/LibraryManagementApp/src/resources/font/Historic Seattle_PersonaUseOnly.ttf"));
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(Superhero);
            ge.registerFont(Ntypes);
            ge.registerFont(Blackjack);
            ge.registerFont(ConeriaScript);
            ge.registerFont(Tusj);
            ge.registerFont(Allura);
            ge.registerFont(Montserrat);
            ge.registerFont(Ancient);
            ge.registerFont(Amsterdam);
            ge.registerFont(BeautifulPeople);
            ge.registerFont(QuickKiss);
            ge.registerFont(VeganStyle);
            ge.registerFont(SweetSensation);
            ge.registerFont(Remachine);
            ge.registerFont(HistoricSeattle);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	private void addMouseListenerForJLabel() {
		lblReader.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	MenuPanel.this.setVisible(false);
		    	Component c = (Component) MenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) MenuPanel.this.getParent();
		    		mlp.showReaderPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) MenuPanel.this.getParent();
			    	mp.showReaderPanel();
		    	}
		    }
		});
		
		lblDoc.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	MenuPanel.this.setVisible(false);
		    	Component c = (Component) MenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) MenuPanel.this.getParent();
			    	mp.showDocPanel();
		    	}
		    	
		    }
		});
		
		lblUser.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	MenuPanel.this.setVisible(false);
		    	Component c = (Component) MenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		
		    	} else {
			    	MainPanel mp = (MainPanel) MenuPanel.this.getParent();
			    	mp.showDocPanel();
		    	}
		    }
		});
		
		lblSheet.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	MenuPanel.this.setVisible(false);
		    	Component c = (Component) MenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) MenuPanel.this.getParent();
		    		mlp.showSheetPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) MenuPanel.this.getParent();
			    	mp.showSheetPanel();
		    	}
		    }
		});
		
		lblStatReport.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	MenuPanel.this.setVisible(false);
		    	Component c = (Component) MenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) MenuPanel.this.getParent();
		    		mlp.showStatisticReportPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) MenuPanel.this.getParent();
			    	mp.showStatisticReportPanel();
		    	}
		    }
		});
		
		lblSearch.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	MenuPanel.this.setVisible(false);
		    	Component c = (Component) MenuPanel.this.getParent();
		    	if(c.getName()=="LIB") {
		    		MainLibrarianPanel mlp = (MainLibrarianPanel) MenuPanel.this.getParent();
		    		mlp.showSearchPanel();
		    	} else {
			    	MainPanel mp = (MainPanel) MenuPanel.this.getParent();
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
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
//		Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(new Color(43, 11, 17));
//        g2d.fillRect(0, 0, 1200, 40);
//        
//        try {
//			Image imgAppIcon = ImageIO.read(getClass().getResource("/resources/images/icon_32.png"));
//			g2d.drawImage(imgAppIcon, 20, 5, MenuPanel.this);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        
//        Font f1 = new Font("Vegan Style Personal Use Regular", 0, 20);
//        Font f2 = new Font("Sweet Sensations Personal Use Regular", 0, 20);
//        Font f3 = new Font("Remachine Script Personal Use Regular", 0, 20);
//        Font f4 = new Font("Quick Kiss Personal Use Regular", 0, 20);
//        Font f5 = new Font("Historic Seattle_PersonalUseOnly Regular", 0, 20);
//        Font f6 = new Font("Beautiful People Personal Use Regular", 0, 20);
//        Font f7 = new Font("Montserrat", Font.PLAIN, 25);
//        g2d.setColor(new Color(255, 255, 141));
//        g2d.setFont(f7);
//        g2d.drawString("Luong Dinh Cua LMS", 60, 28);
//        
//        try {
//			Image imgAppIcon = ImageIO.read(getClass().getResource("/resources/images/pop_up_icon.png"));
//			g2d.drawImage(imgAppIcon, 1125, 5, MenuPanel.this);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
