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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Calendar;
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
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.JDBCConnection;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage.SOSManagePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.CalendarWindow;

public class SearchCheckOutSheetPanel extends BasePanel implements PropertyChangeListener{
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
    private keeptoo.KButton btnSearch, btnCancel;
    private javax.swing.JScrollPane jScrollPane;
	private javax.swing.JTable jTable;
	private DefaultTableModel defaultTableModel;
	private DaoService cosService;
	private JTextField idTextField, tfReaderID, tfDocID, tfBorType, tfUserID;
	private JFormattedTextField tfIssueDate;
	private JLabel lblId, lblDocID, lblReaderID, lblBorType, lblIssueDate, lblUserID;
	
	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		cosService = new DaoService();
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
		setTableData(cosService.getAllCheckoutSheets());
		
		Font fT = new Font("Tahoma", 0, 14);
		jTable.setFont(fT);
		
		add(jScrollPane);
		
		lblReaderID = new JLabel();
		tfReaderID = new javax.swing.JTextField();
		lblId = new JLabel();
		idTextField = new javax.swing.JTextField();
		tfDocID = new javax.swing.JTextField();
		lblDocID = new JLabel();
		
		lblId.setForeground(new java.awt.Color(204, 255, 0));
		lblId.setText("Check Out Sheet's ID : ");
		lblId.setSize(275, 30);
		lblId.setLocation(110, 150);
		lblId.setFont(f1);
		add(lblId);

		Color greenLabel = new Color(204, 255, 0);
		Color backColor = new Color(42, 46, 55);
		Color tfColor = Color.white;
		Font fTf = new Font("Candara", 0, 25);
		
		idTextField.setBackground(new java.awt.Color(42, 46, 55));
		idTextField.setFont(fTf); // NOI18N
		idTextField.setForeground(tfColor);
		idTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));

		idTextField.setSize(250, 24);
		idTextField.setLocation(lblId.getX(), lblId.getY() + lblId.getHeight() + 10);
		add(idTextField);
		idTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from PhieuMuon where MaPhieuMuon like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+idTextField.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

		lblDocID.setForeground(new java.awt.Color(204, 255, 0));
		lblDocID.setText("Reader's ID : ");
		lblDocID.setSize(250, 30);
		lblDocID.setLocation(idTextField.getX(), idTextField.getY()+idTextField.getHeight()+10);
		lblDocID.setFont(f1);
		add(lblDocID);

		tfDocID.setBackground(new java.awt.Color(42, 46, 55));
		tfDocID.setFont(fTf); // NOI18N
		tfDocID.setForeground(tfColor);
		tfDocID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfDocID.setSize(250, 24);
		tfDocID.setLocation(lblDocID.getX(), lblDocID.getY() + lblDocID.getHeight() + 10);
		add(tfDocID);
		tfDocID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from PhieuMuon where MaTaiLieu like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+tfDocID.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

		lblReaderID.setForeground(new java.awt.Color(204, 255, 0));
		lblReaderID.setText("Document's ID :");
		lblReaderID.setSize(250, 30);
		lblReaderID.setLocation(tfDocID.getX()+tfDocID.getWidth()+100, lblId.getY());
		lblReaderID.setFont(f1);
		add(lblReaderID);

		tfReaderID.setBackground(new java.awt.Color(42, 46, 55));
		tfReaderID.setFont(fTf); 
		tfReaderID.setForeground(tfColor);
		tfReaderID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfReaderID.setSize(250, 24);
		tfReaderID.setLocation(lblReaderID.getX(), lblReaderID.getY() + lblReaderID.getHeight() + 10);
		add(tfReaderID);
		tfReaderID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from PhieuMuon where MaDocGia like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+tfReaderID.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

		lblBorType = createLabel("Borrow Type : ", f1, lblReaderID.getX(), tfReaderID.getY()+tfReaderID.getHeight()+10,
				greenLabel);
		lblBorType.setSize(175, 30);
		lblBorType.setBackground(backColor);
		add(lblBorType);

		tfBorType = createJTextField(fTf, 200, lblBorType.getX(), lblBorType.getY() + lblBorType.getHeight() + 10,
				tfColor);
		tfBorType.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfBorType.setBackground(backColor);
		tfBorType.setSize(lblReaderID.getWidth(), 24);
		add(tfBorType);
		tfBorType.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from PhieuMuon where KieuMuon like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+tfBorType.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

		lblIssueDate = createLabel("Issue Date : ", f1, tfBorType.getX()+tfBorType.getWidth()+100, lblReaderID.getY(),
				greenLabel);
		lblIssueDate.setSize(175, 30);
		lblIssueDate.setBackground(backColor);
		add(lblIssueDate);

		tfIssueDate = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MONTH_FIELD));
		tfIssueDate.setValue(new java.util.Date());
		tfIssueDate.setFont(f1);
		tfIssueDate.setBorder(new MatteBorder(new Insets(0, 0, 2, 0), new Color(255, 255, 141)));
		tfIssueDate.setBackground(new Color(42, 46, 55));
		tfIssueDate.setForeground(new Color(255, 255, 255));
		tfIssueDate.setSize(lblReaderID.getWidth(), 30);
		tfIssueDate.setLocation(lblIssueDate.getX(), lblIssueDate.getY() + lblIssueDate.getHeight() + 2);
		add(tfIssueDate);
		tfIssueDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from PhieuMuon where NgayMuon like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+tfIssueDate.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});
		
		CalendarWindow cw = new CalendarWindow();
		cw.setUndecorated(true);
		cw.addPropertyChangeListener(this);
		tfIssueDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cw.setLocation(955, 255);
				cw.setVisible(true);
			}
		});
		
		lblUserID = createLabel("User's ID : ", f1, lblIssueDate.getX(), tfIssueDate.getY() + tfIssueDate.getHeight() + 15,
				greenLabel);
		lblUserID.setSize(175, 30);
		lblUserID.setBackground(backColor);
		add(lblUserID);

		tfUserID = createJTextField(fTf, 200, lblUserID.getX(), lblUserID.getY() + lblUserID.getHeight() + 5, tfColor);
		tfUserID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfUserID.setBackground(backColor);
		// tfUserID.setText("" + userIDAutomation);
		// tfUserID.setEditable(true);
		tfUserID.setSize(lblReaderID.getWidth(), 24);
		add(tfUserID);
		tfUserID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from PhieuMuon where MaNguoiDung like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+idTextField.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});
	}

	private void setTableData(List<CheckoutSheets> allCheckoutSheets) {
		for (CheckoutSheets checkoutSheets2 : allCheckoutSheets) {
			defaultTableModel.addRow(new Object[] { checkoutSheets2.getMaphieumuon(), checkoutSheets2.getMadocgia(),
					checkoutSheets2.getMatl(), checkoutSheets2.getKieumuon(), checkoutSheets2.getNgaymuon(),
					checkoutSheets2.getHantra(), checkoutSheets2.getMangdung() });
		}

	}

	private void setModelTable() {
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};

		defaultTableModel.addColumn("COS ID");
		defaultTableModel.addColumn("Reader ID");
		defaultTableModel.addColumn("Doc ID");
		defaultTableModel.addColumn("Type");
		defaultTableModel.addColumn("B Date");
		defaultTableModel.addColumn("Due Date");
		defaultTableModel.addColumn("User ID");

		jTable.setModel(defaultTableModel);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general_0.png"));
			g2d.drawImage(imgLabelGeneral, 0, 45, SearchCheckOutSheetPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Search for Check out Sheets", 20, 105);
		
		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_0.png"));
			g2d.drawImage(imgButtonReturn, -20, 650, SearchCheckOutSheetPanel.this);
			
			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_0.png"));
			g2d.drawImage(imgButtonTurnOff, 1062, 650, SearchCheckOutSheetPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		g2d.setColor(new Color());
		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Calendar cal = (Calendar) evt.getNewValue();
		java.util.Date selDate = cal.getTime();
		tfIssueDate.setValue(selDate);
	}
	
	@Override
	protected void doClick(String name) {
		if(name.equals(BTN_RETURN)) {
			repaint();
			SearchCheckOutSheetPanel.this.setVisible(false);
			Component c = (Component) SearchCheckOutSheetPanel.this.getParent();
	    	if(c.getName()=="LIB") {
	    		MainLibrarianPanel mlp = (MainLibrarianPanel) SearchCheckOutSheetPanel.this.getParent();
	    		mlp.showSearchMenuPanel();
	    	} else {
		    	MainPanel mp = (MainPanel) SearchCheckOutSheetPanel.this.getParent();
		    	mp.showSearchMenuPanel();
	    	}
		} else if(name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(SearchCheckOutSheetPanel.this, "Do you actually want to exit?","Alert", JOptionPane.YES_NO_OPTION);
			if(rs==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}
