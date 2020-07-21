/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.k62cnpm.xdptpm.libmanage.ui.panel.beginning;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import keeptoo.KButton;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.MaterialComboBoxUI;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;

/**
 *
 * @author Minh
 */

public class SearchContentPanel extends BasePanel{
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private keeptoo.KButton btnSearch, btnBorrow;
	private RoundJTextField searchTextField;
	private javax.swing.JScrollPane jScrollPane;
	private javax.swing.JTable jTable;

    @Override
    public void init() {
        setBackground(new Color(42, 46, 55));
        setLayout(null);
    }

    @Override
    public void addEvent() {
    	
    }

    @Override
    public void addComp() {
    	
    	new ReaderPanel().createNewFont();
    	Font f1 = new Font("Exo 2", 0, 18); 
    	Font f5 = new Font("Blackjack", 0, 20);
		Font f2 = new Font("Blackjack", Font.PLAIN, 40);
		Font f4 = new Font("Blackjack", Font.PLAIN, 45);
		Font f3 = new Font("Exo 2", Font.PLAIN, 40);
		
		searchTextField = new RoundJTextField(20);
		searchTextField.setFont(f1);
		searchTextField.setForeground(new Color(42, 46, 55));
		searchTextField.setSize(460, 40);
		searchTextField.setBackground(Color.white);
		searchTextField.setLocation(125, 15);
		searchTextField.setText("");
		searchTextField.setCaretColor(Color.black);
		searchTextField.setFocusable(true);
		add(searchTextField);
		
		searchTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			
			}
			@Override
			public void mousePressed(MouseEvent e) {
			
			}
			@Override
			public void mouseExited(MouseEvent e) {
			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
		});
		
		btnSearch = new KButton();
    	btnSearch.setForeground(Color.black);
        btnSearch.setText("Search");
        btnSearch.setBorderPainted(false);
        btnSearch.setkAllowGradient(false);
        btnSearch.setkBackGroundColor(new java.awt.Color(117, 209, 151));
        btnSearch.setkForeGround(new Color(42, 46, 55));
        btnSearch.setkHoverColor(new java.awt.Color(20, 123, 182));
        btnSearch.setkHoverForeGround(new Color(255, 255, 141));
        btnSearch.setSize(125, 38);
        btnSearch.setkBorderRadius(25);  
        btnSearch.setLocation(623, 15);
        btnSearch.setFont(f1);
        add(btnSearch);
        
        btnBorrow = new KButton();
    	btnBorrow.setForeground(Color.black);
        btnBorrow.setText("Borrow");
        btnBorrow.setBorderPainted(false);
        btnBorrow.setkAllowGradient(false);
        btnBorrow.setkBackGroundColor(new java.awt.Color(248, 123, 94));
        btnBorrow.setkForeGround(new Color(42, 46, 55));
        btnBorrow.setkHoverColor(new java.awt.Color(145, 96, 74));
        btnBorrow.setkHoverForeGround(new Color(255, 255, 141));
        btnBorrow.setSize(125, 38);
        btnBorrow.setkBorderRadius(25);  
        btnBorrow.setLocation(784, 15);
        btnBorrow.setFont(f1);
        add(btnBorrow);
        
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
		
		deploySearchContent();
		
//		jScrollPane = new javax.swing.JScrollPane();
//		jTable = new javax.swing.JTable();
//		
//		jTable.setBackground(new java.awt.Color(60, 63, 65));
//		jTable.setForeground(new java.awt.Color(204, 255, 102));
//		jTable.setModel(new javax.swing.table.DefaultTableModel(
//				new Object[][] {{ null, null, null, null }},
//				new String[] { "Reader's ID"}));
//		jTable.setFillsViewportHeight(true);
//		setModelTable();
//		jTable.setGridColor(new java.awt.Color(51, 51, 255));
//		jTable.setInheritsPopupMenu(true);
//		jScrollPane.setViewportView(jTable);
//		jScrollPane.setSize(600, 550);
//		jScrollPane.setLocation(580, 160);
////		setTableData(readerService.getAllReader());
//		add(jScrollPane);
    }

	private void setModelTable() {
		
	}

	private void deploySearchContent() {
		
	}

	@Override
    public void paint(Graphics g) {
    	// TODO Auto-generated method stub
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D) g;
    	try {
			Image imgIcon = ImageIO.read(getClass().getResource("/resources/images/icon_48.png"));
			g2d.drawImage(imgIcon, 10, 10, SearchContentPanel.this);

			Image searchIcon = ImageIO.read(getClass().getResource("/resources/images/search_ic_32.png"));
			g2d.drawImage(searchIcon, 80, 20, SearchContentPanel.this);
		
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_1.png"));
			g2d.drawImage(imgButtonReturn, -20, 570, SearchContentPanel.this);
			
			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_1.png"));
			g2d.drawImage(imgButtonTurnOff, 820, 560, SearchContentPanel.this);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	@Override
	protected void doClick(String name) {
		if(name.equals(BTN_RETURN)) {
			repaint();
			SearchContentPanel.this.setVisible(false);
			MainBeginningReaderPanel mbrp = (MainBeginningReaderPanel) SearchContentPanel.this.getParent();
			mbrp.showBeginningSearchReaderPanel();
		} else if(name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(SearchContentPanel.this, "Do you actually want to exit?","Alert", JOptionPane.YES_NO_OPTION);
			if(rs==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	public void sendUpdateSearchTF(String text) {
		if(text!=null && !text.equals("")) {
			searchTextField.setText(text);
		}
	}
	
}
