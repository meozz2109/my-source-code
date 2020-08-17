package vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage;

import java.awt.Color;
import java.awt.Component;
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
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.SignoutSheets;
import vnua.k62cnpm.xdptpm.libmanage.datehandle.DateFormatParse;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.DetaileDocDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.JDBCConnection;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.CheckoutSheets;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainLibrarianPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.menu.SearchMenuPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.CalendarWindow;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;

public class SOSManagePanel extends BasePanel implements PropertyChangeListener {
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private JTextField idTextField, tfReaderID, tfDocID, tfBorType, tfUserID;
	private JFormattedTextField tfGiveBackDate;
	private String userIDAutomation;
	private JLabel lblId, lblReaderID, lblDocID, lblBorType, lblUserID, lblGiveBackDate;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable2;
	private keeptoo.KButton btnAdd;
	private keeptoo.KButton btnUpgrade;
	private keeptoo.KButton btnDelete;
	private keeptoo.KButton btnRefresh;
	private RoundJTextField searchTextField;
	private DefaultTableModel defaultTableModel;
	private DaoService sosService;
	private DetaileDocDao dtddao;
	private SignoutSheets sos;
	private Document doc;
	private Reader reader;

	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		sosService = new DaoService();
		sos = new SignoutSheets();
		dtddao = new DetaileDocDao();
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
		Color greenLabel = new Color(204, 255, 0);
		Color backColor = new Color(42, 46, 55);
		Color tfColor = Color.white;
		Font fTf = new Font("Candara", 0, 25);
		Font f4 = new Font("Candara", 0, 22);

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

		lblReaderID = new JLabel();
		btnAdd = new keeptoo.KButton();
		tfReaderID = new javax.swing.JTextField();
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
		searchTextField.setLocation(738, 155);
		searchTextField.setText("Borrowed Type");
		searchTextField.setCaretColor(Color.black);
		searchTextField.setFocusable(false);
		add(searchTextField);
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from PhieuTra where KieuMuon like ?";
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
		lblId.setText("Check Out Sheet's ID (*)");
		lblId.setSize(275, 30);
		lblId.setLocation(40, 160);
		lblId.setFont(f1);
		add(lblId);

		idTextField.setBackground(new java.awt.Color(42, 46, 55));
		idTextField.setFont(fTf); // NOI18N
		idTextField.setForeground(tfColor);
		idTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));

		idTextField.setSize(250, 24);
		idTextField.setLocation(lblId.getX(), lblId.getY() + lblId.getHeight() + 10);
		add(idTextField);

		lblDocID.setForeground(new java.awt.Color(204, 255, 0));
		lblDocID.setText("Document's ID (*)");
		lblDocID.setSize(250, 30);
		lblDocID.setLocation(lblId.getX(), idTextField.getY() + idTextField.getHeight() + 20);
		lblDocID.setFont(f1);
		add(lblDocID);

		tfDocID.setBackground(new java.awt.Color(42, 46, 55));
		tfDocID.setFont(fTf); // NOI18N
		tfDocID.setForeground(tfColor);
		tfDocID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfDocID.setSize(250, 24);
		tfDocID.setLocation(lblDocID.getX(), lblDocID.getY() + lblDocID.getHeight() + 10);
		add(tfDocID);

		lblReaderID.setForeground(new java.awt.Color(204, 255, 0));
		lblReaderID.setText("Reader's ID (*)");
		lblReaderID.setSize(250, 30);
		lblReaderID.setLocation(lblId.getX(), tfDocID.getY() + tfDocID.getHeight() + 20);
		lblReaderID.setFont(f1);
		add(lblReaderID);

		tfReaderID.setBackground(new java.awt.Color(42, 46, 55));
		tfReaderID.setFont(fTf); // NOI18N
		tfReaderID.setForeground(tfColor);
		tfReaderID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfReaderID.setSize(250, 24);
		tfReaderID.setLocation(lblReaderID.getX(), lblReaderID.getY() + lblReaderID.getHeight() + 10);
		add(tfReaderID);

		lblBorType = createLabel("Borrow Type", f1, lblId.getX(), tfReaderID.getY() + tfReaderID.getHeight() + 10,
				greenLabel);
		lblBorType.setSize(175, 30);
		lblBorType.setBackground(backColor);
		add(lblBorType);

		tfBorType = createJTextField(fTf, 200, lblBorType.getX(), lblBorType.getY() + lblBorType.getHeight() + 10,
				tfColor);
		tfBorType.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfBorType.setBackground(backColor);
		tfBorType.setSize(tfReaderID.getWidth(), 24);
		add(tfBorType);

		lblGiveBackDate = createLabel("Give Back Date (*)", f1, lblId.getX(),
				tfBorType.getY() + tfBorType.getHeight() + 5, greenLabel);
		lblGiveBackDate.setSize(250, 30);
		lblGiveBackDate.setBackground(backColor);
		add(lblGiveBackDate);

		tfGiveBackDate = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MONTH_FIELD));
		tfGiveBackDate.setValue(new java.util.Date());
		tfGiveBackDate.setFont(f1);
		tfGiveBackDate.setBorder(new MatteBorder(new Insets(0, 0, 2, 0), new Color(255, 255, 141)));
		tfGiveBackDate.setBackground(new Color(42, 46, 55));
		tfGiveBackDate.setForeground(new Color(255, 255, 255));
		tfGiveBackDate.setSize(tfBorType.getWidth(), 30);
		tfGiveBackDate.setLocation(lblGiveBackDate.getX(), lblGiveBackDate.getY() + lblGiveBackDate.getHeight() + 2);
		add(tfGiveBackDate);

		CalendarWindow cw = new CalendarWindow();
		cw.setUndecorated(true);
		cw.addPropertyChangeListener(this);
		tfGiveBackDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cw.setLocation(245, 585);
				cw.setVisible(true);
			}
		});

		lblUserID = createLabel("User's ID", f1, lblId.getX(), tfGiveBackDate.getY() + tfGiveBackDate.getHeight() + 5,
				greenLabel);
		lblUserID.setSize(250, 30);
		lblUserID.setBackground(backColor);
		add(lblUserID);

		tfUserID = createJTextField(fTf, 200, lblUserID.getX(), lblUserID.getY() + lblUserID.getHeight() + 5, tfColor);
		tfUserID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfUserID.setBackground(backColor);
//		tfUserID.setText("" + userIDAutomation);
		// tfUserID.setEditable(true);
		tfUserID.setSize(tfBorType.getWidth(), 24);
		add(tfUserID);

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
		btnAdd.setLocation(375, 270);
		btnAdd.setFont(f2);
		btnAdd.setkBorderRadius(25);
		add(btnAdd);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// check valid
				List<SignoutSheets> soss = sosService.getAllSignoutSheets();
				for (int i = 0; i < soss.size(); i++) {

					SignoutSheets sos1 = soss.get(i);
					// System.out.println(au.getMatg());
					if (idTextField.getText().trim().equals(sos1.getMaphieutra().trim())
							|| idTextField.getText().equals("") || tfUserID.getText().equals("") || tfGiveBackDate.getText().equals("")) {
						JOptionPane.showMessageDialog(SOSManagePanel.this, "Invalid input Sign out Sheet 's ID.");
						return;
					}
					
				}
//				List<Reader> readers = sosService.getAllReader();
//				for (int i = 0; i < readers.size(); i++) {
//					Reader reader = readers.get(i);
//					System.out.println(tfReaderID.getText().trim());
//					System.out.println(reader.getID().trim());
//					// System.out.println(au.getMatg());
//					if (!tfReaderID.getText().toString().trim().equals(reader.getID().toString().trim())) {
//						JOptionPane.showMessageDialog(SOSManagePanel.this, "The reader code you have enter does not exist, Attention");
//					}
//					
//				}
//				
//				List<Document> documents = sosService.getAllDocument();
//				for (int i = 0; i < documents.size(); i++) {
//					Document document = documents.get(i);
//					// System.out.println(au.getMatg());
//					if (!tfDocID.getText().toString().trim().equals(document.getMatl().toString().trim())) {
//						JOptionPane.showMessageDialog(SOSManagePanel.this, "The document code you have enter does not exist, Attention");
//						
//					}
//					return;
//				}

				// handle date format
				DateFormatParse dfp = new DateFormatParse();

				try {

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String dateFormatAdd = dfp.parseDate(tfGiveBackDate.getText().toString()).trim();
					java.util.Date parsedA = format.parse(dateFormatAdd);
					java.sql.Date dateA = new java.sql.Date(parsedA.getTime());
					sos.setNgaytra(dateA);

				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("OK");
				}

				sos.setMaphieutra(idTextField.getText().toString().trim());
				sos.setMadocgia(tfReaderID.getText().toString().trim());
				sos.setMatl(tfDocID.getText().toString().trim());
				sos.setKieumuon(tfBorType.getText().toString().trim());
				sos.setMangdung(tfUserID.getText().toString().trim());

				sosService.addSignoutSheets(sos);

				defaultTableModel.setRowCount(0);
				setTableData(sosService.getAllSignoutSheets());
				JOptionPane.showMessageDialog(SOSManagePanel.this, "Add new Sign out Sheet successfully!  ");
				dtddao.updateIdleStatus(tfReaderID.getText().toString().trim());

				idTextField.setText("");
				tfReaderID.setText("");
				tfDocID.setText("");
				tfBorType.setText("");
				tfGiveBackDate.setText("");
				tfUserID.setText("");
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
				List<SignoutSheets> soss = sosService.getAllSignoutSheets();
				if (idTextField.getText().equals("") || tfUserID.getText().equals("")) {
					JOptionPane.showMessageDialog(SOSManagePanel.this, "Invalid input Sign out Sheet 's ID.");
					return;
				}
				// handle date format
				DateFormatParse dfp = new DateFormatParse();

				try {

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String dateFormatAdd = dfp.parseDate(tfGiveBackDate.getText().toString()).trim();
					java.util.Date parsedA = format.parse(dateFormatAdd);
					java.sql.Date dateA = new java.sql.Date(parsedA.getTime());
					sos.setNgaytra(dateA);

				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("OK");
				}

				sos.setMaphieutra(idTextField.getText().toString().trim());
				sos.setMadocgia(tfDocID.getText().toString().trim());
				sos.setMatl(tfReaderID.getText().toString().trim());
				sos.setKieumuon(tfBorType.getText().toString().trim());
				sos.setMangdung(tfUserID.getText().toString().trim());

				sosService.updateSignoutSheets(sos);

				defaultTableModel.setRowCount(0);
				setTableData(sosService.getAllSignoutSheets());
				JOptionPane.showMessageDialog(SOSManagePanel.this, "Update the database successfully!  ");
				// reset tf
				idTextField.setEditable(true);
				tfDocID.setEditable(true);
				tfReaderID.setEditable(true);
				idTextField.setText("");
				tfReaderID.setText("");
				tfDocID.setText("");
				tfBorType.setText("");
				tfGiveBackDate.setText("");
				tfUserID.setText("");
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
					JOptionPane.showMessageDialog(SOSManagePanel.this, "Please select an ID first", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String sosName = (String) jTable2.getValueAt(row, 0);
					int confirm = JOptionPane.showConfirmDialog(SOSManagePanel.this,
							"Are you sure you want to delete the signoutSheet " + sosName.trim() + "?", "Alert",
							JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						String sosID = (String) jTable2.getValueAt(row, 0);
						sosService.deleteSos(sosID);
						defaultTableModel.setRowCount(0);
						setTableData(sosService.getAllSignoutSheets());
					}
				}
			}

		});

		jTable2.setBackground(new java.awt.Color(60, 63, 65));
		jTable2.setForeground(new java.awt.Color(204, 255, 102));
		jTable2.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null } },
				new String[] { "ID", "Reader ID", "Doc ID", "Bor Type", "Give Back Date", "User ID" }));
		jTable2.setFillsViewportHeight(true);
		jTable2.setGridColor(new java.awt.Color(51, 51, 255));
		setModelTable2();
		jTable2.setInheritsPopupMenu(true);
		jScrollPane1.setViewportView(jTable2);
		jScrollPane1.setSize(600, 350);
		jScrollPane1.setLocation(568, 220);
		setTableData(sosService.getAllSignoutSheets());
		add(jScrollPane1);

		Font fT = new Font("Tahoma", 0, 14);
		jTable2.setFont(fT);

		jTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = jTable2.getSelectedRow();
					String signoutID = (String) jTable2.getValueAt(row, 0);
					sos = sosService.getSignoutById(signoutID);
					try {
						idTextField.setText(sos.getMaphieutra().trim());
						tfDocID.setText(sos.getMadocgia().trim());
						tfReaderID.setText(sos.getMatl().trim());
						tfBorType.setText(sos.getKieumuon());
						tfGiveBackDate.setValue(sos.getNgaytra());
						tfUserID.setText(sos.getMangdung().trim());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					idTextField.setEditable(false);
					tfDocID.setEditable(false);
					tfReaderID.setEditable(false);
				}

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
				setTableData(sosService.getAllSignoutSheets());
				idTextField.setEditable(true);
				tfDocID.setEditable(true);
				tfReaderID.setEditable(true);
				idTextField.setText("");
				tfReaderID.setText("");
				tfDocID.setText("");
				tfBorType.setText("");
				tfGiveBackDate.setText("");
				tfUserID.setText("");
			}
		});
		idTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfReaderID.requestFocusInWindow();
				}
			}
		});

		tfReaderID.addKeyListener(new KeyAdapter() {
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
					tfBorType.requestFocusInWindow();
				}
			}
		});
		tfBorType.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfUserID.requestFocusInWindow();
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
			g2d.drawImage(imgLabelGeneral, 0, 45, SOSManagePanel.this);

			Image searchIcon = ImageIO.read(getClass().getResource("/resources/images/search_ic_32.png"));
			g2d.drawImage(searchIcon, 698, 160, SOSManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Sign out Sheets Management", 30, 95);

		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_1.png"));
			g2d.drawImage(imgButtonReturn, -20, 672, SOSManagePanel.this);

			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_1.png"));
			g2d.drawImage(imgButtonTurnOff, 1058, 660, SOSManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doClick(String name) {
		if (name.equals(BTN_RETURN)) {
			repaint();
			SOSManagePanel.this.setVisible(false);
			Component c = (Component) SOSManagePanel.this.getParent();
			if (c.getName() == "LIB") {
				MainLibrarianPanel mlp = (MainLibrarianPanel) SOSManagePanel.this.getParent();
				mlp.showSheetMenuPanel();
			} else {
				MainPanel mp = (MainPanel) SOSManagePanel.this.getParent();
				mp.showSheetMenuPanel();
			}
		} else if (name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(SOSManagePanel.this, "Do you actually want to exit?", "Alert",
					JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Calendar cal = (Calendar) evt.getNewValue();
		java.util.Date selDate = cal.getTime();
		tfGiveBackDate.setValue(selDate);
	}

	private void setTableData(List<SignoutSheets> signoutSheetses) {
		for (SignoutSheets signoutSheets1 : signoutSheetses) {
			defaultTableModel.addRow(new Object[] { signoutSheets1.getMaphieutra(), signoutSheets1.getMadocgia(),
					signoutSheets1.getMatl(), signoutSheets1.getKieumuon(), signoutSheets1.getNgaytra(),
					signoutSheets1.getMangdung() });
		}

	}

	private void setModelTable2() {
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};

		defaultTableModel.addColumn("SOS ID");
		defaultTableModel.addColumn("Reader ID");
		defaultTableModel.addColumn("Doc ID");
		defaultTableModel.addColumn("Type");
		defaultTableModel.addColumn("GB Date");
		defaultTableModel.addColumn("User ID");

		jTable2.setModel(defaultTableModel);

	}
}
