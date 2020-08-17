package vnua.k62cnpm.xdptpm.libmanage.ui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import keeptoo.KButton;
import net.proteanit.sql.DbUtils;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.CheckoutSheets;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.JDBCConnection;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage.SOSManagePanel;

public class SearchDocumentPanel extends BasePanel{
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private keeptoo.KButton btnSearch, btnCancel;
    private javax.swing.JScrollPane jScrollPane;
	private javax.swing.JTable jTable;
	private DefaultTableModel defaultTableModel;
	private DaoService docService;
	private javax.swing.JTextField subIDTextField, tfSuppID, tfAuthID, tfIssueYear;
	private javax.swing.JLabel lblSubID, lblSuppID, lblAuthID, lblIssueYear;
	
	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		docService = new DaoService();
	}

	@Override
	public void addEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComp() {
		Font f1 = new Font("Candara", 0, 24);
		Font f3 = new Font("Candara", 0, 20);
		btnReturn = createJButton("", f3, 300, -180, 670, new Color(23, 24, 25), Color.white, BTN_RETURN);
		btnReturn.setSize(300, 65);
		add(btnReturn);
		
		btnTurnOff = createJButton("", f3, 300, 1062, 670, new Color(23, 24, 25), Color.white, BTN_TURN_OFF);
		btnTurnOff.setSize(300, 65);
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
		
		Font f2 = new Font("Candara", 0, 22);
		
		btnSearch = new KButton();
    	btnSearch.setForeground(Color.black);
        btnSearch.setText("Search");
        btnSearch.setBorderPainted(false);
        btnSearch.setkAllowGradient(false);
        btnSearch.setkBackGroundColor(new java.awt.Color(248, 123, 94));
        btnSearch.setkForeGround(new Color(42, 46, 55));
        btnSearch.setkHoverColor(new java.awt.Color(145, 96, 74));
        btnSearch.setkHoverForeGround(new Color(255, 255, 141));
        btnSearch.setSize(125, 38);
        btnSearch.setkBorderRadius(25);  
        btnSearch.setLocation(365, 320);
        btnSearch.setFont(f2);
        add(btnSearch);
        
        btnCancel = new KButton();
        btnCancel.setForeground(Color.black);
        btnCancel.setText("Cancel");
        btnCancel.setBorderPainted(false);
        btnCancel.setkAllowGradient(false);
        btnCancel.setkBackGroundColor(new java.awt.Color(117, 209, 151));
        btnCancel.setkForeGround(new Color(42, 46, 55));
        btnCancel.setkHoverColor(new java.awt.Color(20, 123, 182));
        btnCancel.setkHoverForeGround(new Color(255, 255, 141));
        btnCancel.setSize(125, 38);
        btnCancel.setkBorderRadius(25);  
        btnCancel.setLocation(680, 320);
        btnCancel.setFont(f2);
        add(btnCancel);
        
        jTable = new JTable();
        jScrollPane = new JScrollPane();
        jTable.setBackground(new java.awt.Color(60, 63, 65));
		jTable.setForeground(new java.awt.Color(204, 255, 102));
		jTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }
						, { null, null, null, null, null, null, null }},
				new String[] { "Check Out Sheet's ID", "Reader's ID", "Document's ID", "Borrow Type", "Issue Date",
						"Due Date", "User ID" }));
		jTable.setFillsViewportHeight(true);
		jTable.setGridColor(new java.awt.Color(51, 51, 255));
		setModelTable();
		jTable.setInheritsPopupMenu(true);
		jScrollPane.setViewportView(jTable);
		jScrollPane.setSize(900, 350);
		jScrollPane.setLocation(140, 380);
		setTableData(docService.getAllDocument());
		
		Font fT = new Font("Tahoma", 0, 14);
		jTable.setFont(fT);
		
		add(jScrollPane);
		
		lblSubID = new javax.swing.JLabel();
		subIDTextField = new javax.swing.JTextField();
		lblSuppID = new javax.swing.JLabel();
		tfSuppID = new javax.swing.JTextField();
		lblAuthID = new JLabel();
		tfAuthID = new JTextField();
		
		lblSubID.setForeground(new java.awt.Color(204, 255, 0));
		lblSubID.setText("Subject 's ID : ");
		lblSubID.setSize(275, 30);
		lblSubID.setLocation(250, 150);
		lblSubID.setFont(f1);
		add(lblSubID);

		Color greenLabel = new Color(204, 255, 0);
		Color backColor = new Color(42, 46, 55);
		Color tfColor = Color.white;
		Font fTf = new Font("Candara", 0, 25);
		
		subIDTextField.setBackground(new java.awt.Color(42, 46, 55));
		subIDTextField.setFont(fTf); // NOI18N
		subIDTextField.setForeground(tfColor);
		subIDTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));

		subIDTextField.setSize(250, 24);
		subIDTextField.setLocation(lblSubID.getX(), lblSubID.getY() + lblSubID.getHeight() + 10);
		add(subIDTextField);
		subIDTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from TaiLieu where MaTheLoai like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+subIDTextField.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

		lblSuppID.setForeground(new java.awt.Color(204, 255, 0));
		lblSuppID.setText("Supplier 's ID : ");
		lblSuppID.setSize(250, 30);
		lblSuppID.setLocation(lblSubID.getX(), subIDTextField.getY()+subIDTextField.getHeight()+10);
		lblSuppID.setFont(f1);
		add(lblSuppID);

		tfSuppID.setBackground(new java.awt.Color(42, 46, 55));
		tfSuppID.setFont(fTf); // NOI18N
		tfSuppID.setForeground(tfColor);
		tfSuppID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfSuppID.setSize(250, 24);
		tfSuppID.setLocation(lblSuppID.getX(), lblSuppID.getY() + lblSuppID.getHeight() + 10);
		add(tfSuppID);
		tfSuppID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from TaiLieu where NhaXuatBan like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+tfSuppID.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

		lblAuthID.setForeground(new java.awt.Color(204, 255, 0));
		lblAuthID.setText("Author 's ID :");
		lblAuthID.setSize(250, 30);
		lblAuthID.setLocation(lblSubID.getX()+lblSubID.getWidth()+150, lblSubID.getY());
		lblAuthID.setFont(f1);
		add(lblAuthID);

		tfAuthID.setBackground(new java.awt.Color(42, 46, 55));
		tfAuthID.setFont(fTf); 
		tfAuthID.setForeground(tfColor);
		tfAuthID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfAuthID.setSize(250, 24);
		tfAuthID.setLocation(lblAuthID.getX(), lblAuthID.getY() + lblAuthID.getHeight() + 10);
		add(tfAuthID);
		tfAuthID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from TaiLieu where MaTacGia like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+tfAuthID.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

		lblIssueYear = createLabel("Issue Year : ", f1, tfAuthID.getX(), tfAuthID.getY()+tfAuthID.getHeight()+10,
				greenLabel);
		lblIssueYear.setSize(175, 30);
		lblIssueYear.setBackground(backColor);
		add(lblIssueYear);

		tfIssueYear = createJTextField(fTf, 200, lblIssueYear.getX(), lblIssueYear.getY() + lblIssueYear.getHeight() + 10,
				tfColor);
		tfIssueYear.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfIssueYear.setBackground(backColor);
		tfIssueYear.setSize(250, 24);
		add(tfIssueYear);
		tfIssueYear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from TaiLieu where NamXB like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+tfIssueYear.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});
        
	}

	private void setTableData(List<Document> documents) {
		for (Document document : documents) {
			defaultTableModel.addRow(new Object[] { document.getMatl(), document.getTentl(), document.getMatheloai(),
					document.getManxb(), document.getMatg(), document.getNamxb(), document.getNoidung(),
					document.getSotrang(), document.getGiabia(), document.getMavitri(), document.getNgcapnhat() });
		}

	}

	private void setModelTable() {
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};

		defaultTableModel.addColumn("Doc ID");
		defaultTableModel.addColumn("Doc name");
		defaultTableModel.addColumn("Sub ID");
		defaultTableModel.addColumn("Sup ID");
		defaultTableModel.addColumn("Auth ID");
		defaultTableModel.addColumn("Pub year");
		defaultTableModel.addColumn("Pre Con");
		defaultTableModel.addColumn("NOP");
		defaultTableModel.addColumn("H price");
		defaultTableModel.addColumn("Loc ID");
		defaultTableModel.addColumn("Up Date");

		jTable.setModel(defaultTableModel);
	}


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general_0.png"));
			g2d.drawImage(imgLabelGeneral, 0, 45, SearchDocumentPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Search for Document", 60, 105);
		
		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_0.png"));
			g2d.drawImage(imgButtonReturn, -20, 650, SearchDocumentPanel.this);
			
			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_0.png"));
			g2d.drawImage(imgButtonTurnOff, 1062, 650, SearchDocumentPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		g2d.setColor(new Color());
		
	}
	
	@Override
	protected void doClick(String name) {
		if(name.equals(BTN_RETURN)) {
			repaint();
			SearchDocumentPanel.this.setVisible(false);
			Component c = (Component) SearchDocumentPanel.this.getParent();
	    	if(c.getName()=="LIB") {
	    		MainLibrarianPanel mlp = (MainLibrarianPanel) SearchDocumentPanel.this.getParent();
	    		mlp.showSearchMenuPanel();
	    	} else {
		    	MainPanel mp = (MainPanel) SearchDocumentPanel.this.getParent();
		    	mp.showSearchMenuPanel();
	    	}		
	    } else if(name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(SearchDocumentPanel.this, "Do you actually want to exit?","Alert", JOptionPane.YES_NO_OPTION);
			if(rs==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}
