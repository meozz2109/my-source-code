package vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Author;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.DetaileDoc;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.ReceiptNote;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Subject;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Supplier;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.User;
import vnua.k62cnpm.xdptpm.libmanage.datehandle.DateFormatParse;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.JDBCConnection;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.CalendarWindow;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;

public class ReceiptNoteManagePanel extends BasePanel implements PropertyChangeListener{
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private JTextField idTextField, tfDocID, tfSupplID, tfUserID, tfQuantity;
	private JFormattedTextField tfIssueDate;
	private String userIDAutomation;
	private JLabel lblId, lblDocID, lblSupplID, lblIssueDate, lblUserID, lblQuantity;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable2;
	private keeptoo.KButton btnAdd;
	private keeptoo.KButton btnUpgrade;
	private keeptoo.KButton btnDelete;
	private keeptoo.KButton btnRefresh;
	private RoundJTextField searchTextField;
	private DefaultTableModel defaultTableModel;
    private DaoService receiptnoteService;
    private ReceiptNote receiptNote;
    private Document document;
    private Supplier sup;
	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		 receiptnoteService = new DaoService();
		 receiptNote = new ReceiptNote();
	}

	@Override
	public void addEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComp() {
		Font f1 = new Font("Exo 2", Font.ITALIC + Font.BOLD, 20);
		Font f2 = new Font("Exo 2", Font.BOLD, 23);
		Font f3 = new Font("Blackjack", 0, 20);
		Font f4 = new Font("Candara", 0, 22);
        Color greenLabel = new Color(204, 255, 0);
        Color backColor = new Color(42, 46, 55);
        Color tfColor = Color.white;
        Font fTf = new Font("Candara", 0, 25);
        
		btnReturn = createJButton("", f1, 300, -180, 685, new Color(23, 24, 25), Color.white, BTN_RETURN);
		btnReturn.setSize(300, 65);
		add(btnReturn);

		btnTurnOff = createJButton("", f1, 300, 1062, 680, new Color(23, 24, 25), Color.white, BTN_TURN_OFF);
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
			}

			@Override
			public void mouseExited(MouseEvent e) {
				repaint();
			}
		});

		lblSupplID = new JLabel();
		btnAdd = new keeptoo.KButton();
		tfSupplID = new javax.swing.JTextField();
		btnUpgrade = new keeptoo.KButton();
		btnDelete = new keeptoo.KButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();
		lblId = new JLabel();
		btnRefresh = new keeptoo.KButton();
		idTextField = new javax.swing.JTextField();
		tfDocID = new javax.swing.JTextField();
		lblDocID = new JLabel();

		searchTextField = new RoundJTextField(20);
		searchTextField.setFont(f4);
		searchTextField.setForeground(new Color(42, 46, 55));
		searchTextField.setSize(285, 40);
		searchTextField.setBackground(Color.white);
		searchTextField.setLocation(68, 155);
		searchTextField.setText("User ID");
		searchTextField.setCaretColor(Color.black);
		searchTextField.setFocusable(false);
		add(searchTextField);
		
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from PhieuNhap where MaNguoiDungNhap like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+searchTextField.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable2.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

		searchTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTextField.setText("");
				searchTextField.setFocusable(true);
			}
			
		});
		
		lblId.setForeground(new java.awt.Color(204, 255, 0));
		lblId.setText("Receipt Note's ID (*)");
		lblId.setSize(280, 30);
		lblId.setLocation(40, 230);
		lblId.setFont(f1);
		add(lblId);

		idTextField.setBackground(new java.awt.Color(42, 46, 55));
		idTextField.setFont(fTf); // NOI18N
		idTextField.setForeground(tfColor);
		idTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));

		idTextField.setSize(250, 24);
		idTextField.setLocation(lblId.getX(), lblId.getY() + lblId.getHeight() + 5);
		add(idTextField);

		lblDocID.setForeground(new java.awt.Color(204, 255, 0));
		lblDocID.setText("Document's ID (*)");
		lblDocID.setSize(250, 30);
		lblDocID.setLocation(lblId.getX(), idTextField.getY() + idTextField.getHeight() + 5);
		lblDocID.setFont(f1);
		add(lblDocID);

		tfDocID.setBackground(new java.awt.Color(42, 46, 55));
		tfDocID.setFont(fTf); // NOI18N
		tfDocID.setForeground(tfColor);
		tfDocID
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfDocID.setSize(250, 24);
		tfDocID.setLocation(lblDocID.getX(), lblDocID.getY() + lblDocID.getHeight() + 5);
		add(tfDocID);

		lblSupplID.setForeground(new java.awt.Color(204, 255, 0));
		lblSupplID.setText("Supplier's ID (*)");
		lblSupplID.setSize(175, 30);
		lblSupplID.setLocation(lblId.getX(), tfDocID.getY() + tfDocID.getHeight() + 5);
		lblSupplID.setFont(f1);
		add(lblSupplID);

		tfSupplID.setBackground(new java.awt.Color(42, 46, 55));
		tfSupplID.setFont(fTf); // NOI18N
		tfSupplID.setForeground(tfColor);
		tfSupplID
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfSupplID.setSize(250, 24);
		tfSupplID.setLocation(lblSupplID.getX(), lblSupplID.getY() + lblSupplID.getHeight() + 10);
		add(tfSupplID);

        lblIssueDate = createLabel("Issue Date (*)", f1, lblId.getX(), tfSupplID.getY()+tfSupplID.getHeight()+5, greenLabel);
        lblIssueDate.setSize(250, 30);
        lblIssueDate.setBackground(backColor);
        add(lblIssueDate);
        
        tfIssueDate = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MONTH_FIELD));
        tfIssueDate.setValue(new java.util.Date());
        tfIssueDate.setFont(f1);
        tfIssueDate.setBorder(new MatteBorder(new Insets(0, 0, 2, 0), new Color(255, 255, 141)));
        tfIssueDate.setBackground(new Color(42, 46, 55));
        tfIssueDate.setForeground(new Color(255, 255, 255));
        tfIssueDate.setSize(tfSupplID.getWidth(),  30);
        tfIssueDate.setLocation(lblIssueDate.getX(), lblIssueDate.getY()+lblIssueDate.getHeight()+2);
        add(tfIssueDate);
        
        CalendarWindow cw = new CalendarWindow();
        cw.setUndecorated(true);
        cw.addPropertyChangeListener(this);
        tfIssueDate.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		cw.setLocation(245, 540);
        		cw.setVisible(true);
        	}
		});
        
        lblUserID = createLabel("User's ID", f1, lblId.getX(), tfIssueDate.getY()+tfIssueDate.getHeight()+5, greenLabel);
        lblUserID.setSize(175, 30);
        lblUserID.setBackground(backColor);
        add(lblUserID);
        
        tfUserID = createJTextField(fTf, 150, lblUserID.getX(), lblUserID.getY()+lblUserID.getHeight()+5, tfColor);
        tfUserID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
        tfUserID.setBackground(backColor);
//        tfUserID.setText(""+userIDAutomation);
//        tfUserID.setEditable(false);
        tfUserID.setSize(tfSupplID.getWidth(), 24);
        add(tfUserID);
        
        lblQuantity = createLabel("Quantity", f1, lblUserID.getX(), tfUserID.getY()+tfUserID.getHeight()+5, greenLabel);
        lblQuantity.setSize(175, 30);
        lblQuantity.setBackground(backColor);
        add(lblQuantity);
        
        tfQuantity = createJTextField(fTf, 150, lblQuantity.getX(), lblQuantity.getY()+lblQuantity.getHeight()+5, tfColor);
        tfQuantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
        tfQuantity.setBackground(backColor);
        tfQuantity.setEditable(false);
        tfQuantity.setSize(tfUserID.getWidth(), 24);
        add(tfQuantity);
        
		btnAdd.setForeground(Color.black);
		btnAdd.setText("Add");
		btnAdd.setActionCommand("Exo 2");
		btnAdd.setBorderPainted(false);
		btnAdd.setkAllowGradient(false);
		btnAdd.setkBackGroundColor(new java.awt.Color(117, 209, 151));
		btnAdd.setkForeGround(new Color(42, 46, 55));
		btnAdd.setkFillButton(true);
		btnAdd.setkHoverColor(new java.awt.Color(20, 123, 182));
		btnAdd.setkHoverForeGround(new java.awt.Color(255, 255, 141));
		btnAdd.setkBorderRadius(25);
		btnAdd.setSize(110, 36);
		btnAdd.setLocation(375, 280);
		btnAdd.setFont(f2);
		btnAdd.setkBorderRadius(25);
		add(btnAdd);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// check valid
				List<ReceiptNote> receiptnote = receiptnoteService.getAllReceiptNote();
				for (int i = 0; i < receiptnote.size(); i++) {
					ReceiptNote receipt = receiptnote.get(i);
					// System.out.println(au.getMatg());
					if (idTextField.getText().trim().equals(receipt.getMaphieunhap().trim())
							|| idTextField.getText().equals("") || tfDocID.getText().equals("") || tfSupplID.getText().equals("") || tfIssueDate.getValue().equals("")) {
						JOptionPane.showMessageDialog(ReceiptNoteManagePanel.this, "Invalid input receiptNote ID.");
						return;
					}
				}
				List<Document> documents = receiptnoteService.getAllDocument();
				for (int i = 0; i < documents.size(); i++) {
					Document doc = documents.get(i);
					// System.out.println(au.getMatg());
					if (tfDocID.getText().trim() != doc.getMatl().trim()) {
						JOptionPane.showMessageDialog(ReceiptNoteManagePanel.this, "The document code you entered does not exist, Attention");
						return;
					}
				}
				List<Supplier> supList = receiptnoteService.getAllSupplier();
				for (int i = 0; i < supList.size(); i++) {
					Supplier supplier = supList.get(i);
					// System.out.println(au.getMatg());
					if (tfSupplID.getText().trim() != supplier.getMancc().trim()) {
						JOptionPane.showMessageDialog(ReceiptNoteManagePanel.this, "The provider code you have enter does not exist, Attention");
						return;
					}
				}
				// handle date format
				DateFormatParse dfp = new DateFormatParse();
				try {

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String dateFormatAdd = dfp.parseDate(tfIssueDate.getText().toString()).trim();
					java.util.Date parsedA = format.parse(dateFormatAdd);
					java.sql.Date dateA = new java.sql.Date(parsedA.getTime());
					receiptNote.setNgaynhap(dateA);

				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("OK");
				}
								receiptNote.setMaphieunhap(idTextField.getText().toString().trim());
				receiptNote.setMatl(tfDocID.getText().toString().trim());
				receiptNote.setMancc(tfSupplID.getText().toString().trim());
				receiptNote.setMangdung(tfUserID.getText().toString().trim());
				receiptNote.setSoluong(tfQuantity.getText().toString().trim());
			
				receiptnoteService.addReceiptNote(receiptNote);
				defaultTableModel.setRowCount(0);
				setTableData(receiptnoteService.getAllReceiptNote());
				JOptionPane.showMessageDialog(ReceiptNoteManagePanel.this, "Add receiptNote successfully!  ");
				// reset tf
				idTextField.setText("");
				tfDocID.setText("");
				tfSupplID.setText("");
				tfIssueDate.setText("");
				tfUserID.setText("");
				tfQuantity.setText("");				
			}
		});

		btnUpgrade.setForeground(Color.black);
		btnUpgrade.setText("Modify");
		btnUpgrade.setBorderPainted(false);
		btnUpgrade.setFont(new java.awt.Font("Exo 2", 1, 16)); // NOI18N
		btnUpgrade.setkAllowGradient(false);
		btnUpgrade.setkBackGroundColor(new java.awt.Color(117, 209, 151));
		btnUpgrade.setkForeGround(new Color(42, 46, 55));
		btnUpgrade.setkHoverColor(new java.awt.Color(20, 123, 182));
		btnUpgrade.setkHoverForeGround(new Color(255, 255, 141));
		btnUpgrade.setSize(110, 36);
		btnUpgrade.setkBorderRadius(25);
		btnUpgrade.setLocation(375, btnAdd.getY() + btnAdd.getHeight() + 30);
		btnUpgrade.setFont(f2);
		add(btnUpgrade);
		btnUpgrade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// check valid
				List<ReceiptNote> receiptnote = receiptnoteService.getAllReceiptNote();
					if ( idTextField.getText().equals("") || tfDocID.getText().equals("") || tfSupplID.getText().equals("")) {
						JOptionPane.showMessageDialog(ReceiptNoteManagePanel.this, "Invalid input receiptNote ID.");
						return;
					}
			
				// handle date format
				DateFormatParse dfp = new DateFormatParse();
				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String dateFormatAdd = dfp.parseDate(tfIssueDate.getText().toString()).trim();
					java.util.Date parsedA = format.parse(dateFormatAdd);
					java.sql.Date dateA = new java.sql.Date(parsedA.getTime());
					receiptNote.setNgaynhap(dateA);

				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("OK");
				}				
				receiptNote.setMaphieunhap(idTextField.getText().toString().trim());
				receiptNote.setMatl(tfDocID.getText().toString().trim());
				receiptNote.setMancc(tfSupplID.getText().toString().trim());
				receiptNote.setMangdung(tfUserID.getText().toString().trim());
				receiptNote.setSoluong(tfQuantity.getText().toString().trim());
			
				receiptnoteService.updatReceiptNote(receiptNote);
				defaultTableModel.setRowCount(0);
				setTableData(receiptnoteService.getAllReceiptNote());
				JOptionPane.showMessageDialog(ReceiptNoteManagePanel.this, "Update receiptNote successfully!  ");
				// reset tf
				idTextField.setText("");
				tfDocID.setText("");
				tfSupplID.setText("");
				tfIssueDate.setText("");
				tfUserID.setText("");
				tfQuantity.setText("");				
			}
		});

		btnDelete.setForeground(Color.black);
		btnDelete.setText("Delete");
		btnDelete.setBorderPainted(false);
		btnDelete.setkAllowGradient(false);
		btnDelete.setkAllowTab(true);
		btnDelete.setkBackGroundColor(new java.awt.Color(117, 209, 151));
		btnDelete.setkForeGround(new Color(42, 46, 55));
		btnDelete.setkHoverColor(new java.awt.Color(20, 123, 182));
		btnDelete.setkHoverForeGround(new java.awt.Color(255, 255, 141));
		btnDelete.setkBorderRadius(25);
		btnDelete.setSize(110, 36);
		btnDelete.setLocation(375, btnUpgrade.getY() + btnUpgrade.getHeight() + 30);
		btnDelete.setFont(f2);
		add(btnDelete);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				int row = jTable2.getSelectedRow();			
				if (row == -1) {
					JOptionPane.showMessageDialog(ReceiptNoteManagePanel.this, "Please select an ID first", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String receipt1 = (String) jTable2.getValueAt(row, 0);
					int confirm = JOptionPane.showConfirmDialog(ReceiptNoteManagePanel.this, "Are you sure you want to delete the receiptNote " + receipt1.trim() + "?", "Alert", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						String receiptnoteID = (String) jTable2.getValueAt(row, 0);
//						try {
//
//							List<DetaileDoc> detaileDocs = receiptnoteService.getAllDetaileDoc();
//							for (int i = 0; i < detaileDocs.size(); i++) {
//								String docID = (String) jTable2.getValueAt(row, 1);
//								DetaileDoc detaileDoc = detaileDocs.get(i);
//								if (docID.toString().trim().equals(detaileDoc.getMatl().toString().trim())) {
//									JOptionPane.showMessageDialog(ReceiptNoteManagePanel.this,
//											" Attention please !You need to delete " + docID.toString().trim()
//													+ " in the DetaileDoc table first.");
//
//								}
//							}
//							List<User> users = receiptnoteService.getAllUser();
//							for (int i = 0; i < users.size(); i++) {
//								String user = (String) jTable2.getValueAt(row, 3);
//								User user2 = users.get(i);
//								// System.out.println(au.getMatg());
//								if (user.toString().trim().equals(user2.getMangdung().toString().trim())) {
//									JOptionPane.showMessageDialog(ReceiptNoteManagePanel.this,
//											"Attention please !You need to delete " + user.toString().trim() + " in the User table first, Attention");
//								}
//							}
//							List<Supplier> suppliers = receiptnoteService.getAllSupplier();
//							for (int i = 0; i < suppliers.size(); i++) {
//								String supID = (String) jTable2.getValueAt(row, 2);
//								Supplier superID = suppliers.get(i);
//								// System.out.println(au.getMatg());
//								if (supID.toString().trim().equals(superID.getMancc().toString().trim())) {
//									JOptionPane.showMessageDialog(ReceiptNoteManagePanel.this,
//											"Attention please !You need to delete " + supID.toString().trim() + " in the Supplier table first, Attention");
//								}
//							}
						receiptnoteService.deleteReceiptnote(receiptnoteID);
						defaultTableModel.setRowCount(0);
						setTableData(receiptnoteService.getAllReceiptNote());
//						} catch (Exception e) {
//							e.printStackTrace();
//
//						}
					}
				}
			}

		});

		jTable2.setBackground(new java.awt.Color(60, 63, 65));
		jTable2.setForeground(new java.awt.Color(204, 255, 102));
		jTable2.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null}, { null, null, null, null},
								{ null, null, null, null}, { null, null, null, null}},
						new String[] { "ID", "Supplier's ID", "Document's ID", "Issue Date"}));
		jTable2.setFillsViewportHeight(true);
		setModelTable2();
		jTable2.setGridColor(new java.awt.Color(51, 51, 255));
		jTable2.setInheritsPopupMenu(true);
		jScrollPane1.setViewportView(jTable2);
		jScrollPane1.setSize(600, 350);
		jScrollPane1.setLocation(568, 220);
		setTableData(receiptnoteService.getAllReceiptNote());
		add(jScrollPane1);
		
		Font fT = new Font("Tahoma", 0, 14);
		jTable2.setFont(fT);
		
		jTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = jTable2.getSelectedRow();
					// String authorName = (String) jTable2.getValueAt(row, 1);
					String repString = (String) jTable2.getValueAt(row, 0);
					receiptNote = receiptnoteService.getReceiptNoteById(repString);
					try {
						idTextField.setText(receiptNote.getMaphieunhap().trim());
						tfDocID.setText(receiptNote.getMatl().trim());
						tfSupplID.setText(receiptNote.getMancc().trim());
						tfIssueDate.setValue(receiptNote.getNgaynhap());
						tfUserID.setText(receiptNote.getMangdung().trim());
						tfQuantity.setText(receiptNote.getSoluong().trim());
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				idTextField.setEditable(false);
			}
		});

		btnRefresh.setForeground(Color.black);
		btnRefresh.setText("Refresh");
		btnRefresh.setBorderPainted(false);
		btnRefresh.setkAllowGradient(false);
		btnRefresh.setkBackGroundColor(new java.awt.Color(117, 209, 151));
		btnRefresh.setkForeGround(new Color(42, 46, 55));
		btnRefresh.setkHoverColor(new java.awt.Color(20, 123, 182));
		btnRefresh.setkHoverForeGround(new java.awt.Color(255, 255, 141));
		btnRefresh.setkBorderRadius(25);
		btnRefresh.setSize(110, 36);
		btnRefresh.setLocation(375, btnDelete.getY() + btnDelete.getHeight() + 30);
		btnRefresh.setFont(f2);
		add(btnRefresh);
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setModelTable2();
				setTableData(receiptnoteService.getAllReceiptNote());
				idTextField.setEditable(false);
				idTextField.setText("");
				tfDocID.setText("");
				tfSupplID.setText("");
				tfIssueDate.setText("");
				tfUserID.setText("");
				tfQuantity.setText("");
			}
		});
		idTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfDocID.requestFocusInWindow();
				}
			}
		});

		tfDocID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfSupplID.requestFocusInWindow();
				}
			}
		});

		tfSupplID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfUserID.requestFocusInWindow();
				}
			}
		});

		tfUserID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfQuantity.requestFocusInWindow();
				}
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general_0.png"));
			g2d.drawImage(imgLabelGeneral, 0, 45, ReceiptNoteManagePanel.this);
			
			Image searchIcon = ImageIO.read(getClass().getResource("/resources/images/search_ic_32.png"));
			g2d.drawImage(searchIcon, 32, 160, ReceiptNoteManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Receipt Note Management", 25, 105);
		
		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_0.png"));
			g2d.drawImage(imgButtonReturn, -20, 650, ReceiptNoteManagePanel.this);
			
			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_0.png"));
			g2d.drawImage(imgButtonTurnOff, 1062, 650, ReceiptNoteManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doClick(String name) {
		if(name.equals(BTN_RETURN)) {
			repaint();
			ReceiptNoteManagePanel.this.setVisible(false);
			MainPanel mp = (MainPanel) ReceiptNoteManagePanel.this.getParent();
			mp.showDocumentMenuPanel();
		} else if(name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(ReceiptNoteManagePanel.this, "Do you actually want to exit?","Alert", JOptionPane.YES_NO_OPTION);
			if(rs==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Calendar cal = (Calendar) evt.getNewValue();
		java.util.Date selDate = cal.getTime();
		tfIssueDate.setValue(selDate);
	}
	  private void setTableData(List<ReceiptNote> receiptNotes) {
	        for (ReceiptNote receiptNote : receiptNotes) {
	            defaultTableModel.addRow(new Object[]{receiptNote.getMaphieunhap(),receiptNote.getMatl(),receiptNote.getMancc(),receiptNote.getMangdung(),
	            receiptNote.getNgaynhap(),receiptNote.getSoluong()});
	        }

	    }

	    private void setModelTable2() {
	        defaultTableModel = new DefaultTableModel() {
	            @Override
	            public boolean isCellEditable(int i, int i1) {
	                return false;
	            }
	        };
	       
	        defaultTableModel.addColumn("RC ID");
	        defaultTableModel.addColumn("Doc ID");
	        defaultTableModel.addColumn("Sup ID");
	        defaultTableModel.addColumn("User ID");
	        defaultTableModel.addColumn("Init Date");
	        defaultTableModel.addColumn("Quantity");
	       
	        
	        
	        jTable2.setModel(defaultTableModel);
	        
	    }
}
