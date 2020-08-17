package vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage;

import java.awt.Color;
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
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.CheckoutSheets;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.DetaileDoc;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Location;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.SignoutSheets;
import vnua.k62cnpm.xdptpm.libmanage.datehandle.DateFormatParse;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.JDBCConnection;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.CalendarWindow;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;

public class DetailedDocManagePanel extends BasePanel implements PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private JTextField tfDocBarCode, tfDocID, tfIssueDate, tfHandleNote, tfUserID;
	private JFormattedTextField tfStatus;
	private String userIDAutomation;
	private JLabel lblDocBarCode, lblDocID, lblStatus, lblIssueDate, lblUserID, lblHandleNote;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable2;
	private keeptoo.KButton btnAdd;
	private keeptoo.KButton btnUpgrade;
	private keeptoo.KButton btnDelete;
	private keeptoo.KButton btnRefresh;
	private RoundJTextField searchTextField;
	private DefaultTableModel defaultTableModel;
	private DaoService detailedService;
	private DetaileDoc detailedoc;
	private Document doc;
	private CheckoutSheets cos;

	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		detailedService = new DaoService();
		detailedoc = new DetaileDoc();
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

		lblStatus = new JLabel();
		btnAdd = new keeptoo.KButton();
		tfIssueDate = new javax.swing.JTextField();
		btnUpgrade = new keeptoo.KButton();
		btnDelete = new keeptoo.KButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();
		lblDocBarCode = new JLabel();
		btnRefresh = new keeptoo.KButton();
		tfDocBarCode = new javax.swing.JTextField();
		tfDocID = new javax.swing.JTextField();
		lblDocID = new JLabel();

		searchTextField = new RoundJTextField(20);
		searchTextField.setFont(f4);
		searchTextField.setForeground(new Color(42, 46, 55));
		searchTextField.setSize(285, 40);
		searchTextField.setBackground(Color.white);
		searchTextField.setLocation(738, 155);
		searchTextField.setText("Status");
		searchTextField.setCaretColor(Color.black);
		searchTextField.setFocusable(false);
		add(searchTextField);
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from TaiLieuChiTiet where TinhTrang like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, "%" + searchTextField.getText().trim() + "%");
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

		lblDocBarCode.setForeground(new java.awt.Color(204, 255, 0));
		lblDocBarCode.setText("Document Bar Code");
		lblDocBarCode.setSize(250, 30);
		lblDocBarCode.setLocation(40, 160);
		lblDocBarCode.setFont(f1);
		add(lblDocBarCode);

		tfDocBarCode.setBackground(new java.awt.Color(42, 46, 55));
		tfDocBarCode.setFont(fTf); // NOI18N
		tfDocBarCode.setForeground(tfColor);
		tfDocBarCode
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));

		tfDocBarCode.setSize(250, 24);
		tfDocBarCode.setLocation(lblDocBarCode.getX(), lblDocBarCode.getY() + lblDocBarCode.getHeight() + 10);
		add(tfDocBarCode);

		lblDocID.setForeground(new java.awt.Color(204, 255, 0));
		lblDocID.setText("Document's ID (*)");
		lblDocID.setSize(250, 30);
		lblDocID.setLocation(lblDocBarCode.getX(), tfDocBarCode.getY() + tfDocBarCode.getHeight() + 20);
		lblDocID.setFont(f1);
		add(lblDocID);

		tfDocID.setBackground(new java.awt.Color(42, 46, 55));
		tfDocID.setFont(fTf); // NOI18N
		tfDocID.setForeground(tfColor);
		tfDocID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfDocID.setSize(250, 24);
		tfDocID.setLocation(lblDocID.getX(), lblDocID.getY() + lblDocID.getHeight() + 10);
		add(tfDocID);

		lblStatus.setForeground(new java.awt.Color(204, 255, 0));
		lblStatus.setText("Issue Date (*)");
		lblStatus.setSize(175, 30);
		lblStatus.setLocation(lblDocBarCode.getX(), tfDocID.getY() + tfDocID.getHeight() + 20);
		lblStatus.setFont(f1);
		add(lblStatus);

		tfStatus = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MONTH_FIELD));
		tfStatus.setValue(new java.util.Date());
		tfStatus.setBackground(new java.awt.Color(42, 46, 55));
		tfStatus.setFont(fTf);
		tfStatus.setForeground(tfColor);
		tfStatus.setBorder(new MatteBorder(new Insets(0, 0, 2, 0), new Color(255, 255, 141)));
		tfStatus.setSize(250, 24);
		tfStatus.setLocation(lblStatus.getX(), lblStatus.getY() + lblStatus.getHeight() + 10);
		add(tfStatus);

		CalendarWindow cw = new CalendarWindow();
		cw.setUndecorated(true);
		cw.addPropertyChangeListener(this);
		tfStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cw.setLocation(245, 385);
				cw.setVisible(true);
			}
		});

		lblHandleNote = createLabel("Handle Note", f1, lblDocBarCode.getX(),
				tfStatus.getY() + tfStatus.getHeight() + 10, greenLabel);
		lblHandleNote.setSize(175, 30);
		lblHandleNote.setBackground(backColor);
		add(lblHandleNote);

		tfHandleNote = createJTextField(fTf, 200, lblHandleNote.getX(),
				lblHandleNote.getY() + lblHandleNote.getHeight() + 10, tfColor);
		tfHandleNote
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfHandleNote.setBackground(backColor);
		tfHandleNote.setSize(lblDocBarCode.getWidth(), 24);
		add(tfHandleNote);

		lblIssueDate = createLabel("Status", f1, lblDocBarCode.getX(),
				tfHandleNote.getY() + tfHandleNote.getHeight() + 5, greenLabel);
		lblIssueDate.setSize(250, 30);
		lblIssueDate.setBackground(backColor);
		add(lblIssueDate);
//
//		tfIssueDate = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MONTH_FIELD));
//		tfIssueDate.setValue(new java.util.Date());
		tfIssueDate.setFont(f1);
		tfIssueDate
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfIssueDate.setBackground(new Color(42, 46, 55));
		tfIssueDate.setForeground(new Color(255, 255, 255));
		tfIssueDate.setSize(lblDocBarCode.getWidth(), 30);
		tfIssueDate.setLocation(lblIssueDate.getX(), lblIssueDate.getY() + lblIssueDate.getHeight() + 2);
		add(tfIssueDate);

		lblUserID = createLabel("User's ID", f1, lblDocBarCode.getX(), tfIssueDate.getY() + tfIssueDate.getHeight() + 5,
				greenLabel);
		lblUserID.setSize(175, 30);
		lblUserID.setBackground(backColor);
		add(lblUserID);

		tfUserID = createJTextField(fTf, 200, lblUserID.getX(), lblUserID.getY() + lblUserID.getHeight() + 5, tfColor);
		tfUserID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfUserID.setBackground(backColor);
//		tfUserID.setText("" + userIDAutomation);
//		tfUserID.setEditable(false);
		tfUserID.setSize(lblDocBarCode.getWidth(), 24);
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
				List<DetaileDoc> detaileDocs = detailedService.getAllDetaileDoc();
				for (int i = 0; i < detaileDocs.size(); i++) {
					DetaileDoc doc = detaileDocs.get(i);
					// System.out.println(au.getMatg());
					if (tfDocBarCode.getText().trim().equals(doc.getMavachsach().trim())
							|| tfDocBarCode.getText().equals("") || tfUserID.getText().equals("")) {
						JOptionPane.showMessageDialog(DetailedDocManagePanel.this, "Invalid input detailedoc ID.");
						return;
					}
				}
				List<Document> documents = detailedService.getAllDocument();
				for (int i = 0; i < documents.size(); i++) {
					Document doc = documents.get(i);
					// System.out.println(au.getMatg());
					if (tfDocID.getText().trim() != doc.getMatl().trim()) {
						JOptionPane.showMessageDialog(DetailedDocManagePanel.this,
								"The document code you entered does not exist, Attention");
						return;
					}
				}

				// handle date format
				DateFormatParse dfp = new DateFormatParse();

				try {

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String dateFormatAdd = dfp.parseDate(tfStatus.getText().toString()).trim();
					java.util.Date parsedA = format.parse(dateFormatAdd);
					java.sql.Date dateA = new java.sql.Date(parsedA.getTime());
					detailedoc.setNgaycapnhat(dateA);

				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("OK");
				}

				detailedoc.setMavachsach(tfDocBarCode.getText().toString().trim());
				detailedoc.setMatl(tfDocID.getText().toString().trim());
				detailedoc.setMangdung(tfHandleNote.getText().toString().trim());
				detailedoc.setTinhtrang(tfIssueDate.getText().toString().trim());
				detailedoc.setDanhdauxuly(tfUserID.getText().toString().trim());

				detailedService.addDetaileDoc(detailedoc);

				defaultTableModel.setRowCount(0);
				setTableData(detailedService.getAllDetaileDoc());
				JOptionPane.showMessageDialog(DetailedDocManagePanel.this, "Add detailedoc successfully!  ");

				// reset tf
				tfDocBarCode.setText("");
				tfDocID.setText("");
				tfStatus.setText("");
				tfUserID.setText("");
				tfIssueDate.setText("");
				tfHandleNote.setText("");
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
				List<DetaileDoc> detaileDocs = detailedService.getAllDetaileDoc();
				for (int i = 0; i < detaileDocs.size(); i++) {

					DetaileDoc doc = detaileDocs.get(i);
					// System.out.println(au.getMatg());
					if (tfDocBarCode.getText().equals("") || tfUserID.getText().equals("")) {
						JOptionPane.showMessageDialog(DetailedDocManagePanel.this, "Invalid input detailedoc ID.");
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
					detailedoc.setNgaycapnhat(dateA);

				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("OK");
				}

				detailedoc.setMavachsach(tfDocBarCode.getText().toString().trim());
				detailedoc.setMatl(tfDocID.getText().toString().trim());
				detailedoc.setMangdung(tfUserID.getText().toString().trim());
				detailedoc.setTinhtrang(tfStatus.getText().toString().trim());
				detailedoc.setDanhdauxuly(tfHandleNote.getText().toString().trim());

				detailedService.updateDetaileDoc(detailedoc);

				defaultTableModel.setRowCount(0);
				setTableData(detailedService.getAllDetaileDoc());
				JOptionPane.showMessageDialog(DetailedDocManagePanel.this, "Update detailedoc successfully!  ");
				// reset tf
				tfDocBarCode.setText("");
				tfDocID.setText("");
				tfIssueDate.setText("");
				tfUserID.setText("");
				tfStatus.setText("");
				tfHandleNote.setText("");
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
					JOptionPane.showMessageDialog(DetailedDocManagePanel.this, "Please select an User first", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String detailedName = (String) jTable2.getValueAt(row, 0);
					int confirm = JOptionPane.showConfirmDialog(DetailedDocManagePanel.this,
							"Are you sure you want to delete the barcode book" + detailedName.trim() + "?", "Alert",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						
							String detailedID = (String) jTable2.getValueAt(row, 0);
							try {
								String cosID = (String) jTable2.getValueAt(row, 1);
								List<CheckoutSheets> checkoutSheets = detailedService.getAllCheckoutSheets();
								for (int i = 0; i < checkoutSheets.size(); i++) {
									CheckoutSheets detaileDoc = checkoutSheets.get(i);
									if (cosID.toString().trim().equals(detaileDoc.getMatl().toString().trim())) {
										JOptionPane.showMessageDialog(DetailedDocManagePanel.this,
												" Attention please !You need to delete " + cosID.toString().trim()
														+ " in the CheckoutSheet table first.");

									}
								}
								detailedService.deleteDetailed(detailedID);;
								defaultTableModel.setRowCount(0);
								setTableData(detailedService.getAllDetaileDoc());
							}catch (Exception e) {
									e.printStackTrace();
								}
					}
								
				}
			}

		});

		jTable2.setBackground(new java.awt.Color(60, 63, 65));
		jTable2.setForeground(new java.awt.Color(204, 255, 102));
		jTable2.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null } },
				new String[] { "Doc Bar Code", "Document's ID", "Status", "Handle Note", "Issue Date", "User ID" }));
		jTable2.setFillsViewportHeight(true);
		jTable2.setGridColor(new java.awt.Color(51, 51, 255));
		setModelTable2();
		jTable2.setInheritsPopupMenu(true);
		jScrollPane1.setViewportView(jTable2);
		jScrollPane1.setSize(600, 400);
		jScrollPane1.setLocation(568, 220);
		setTableData(detailedService.getAllDetaileDoc());
		add(jScrollPane1);

		Font fT = new Font("Tahoma", 0, 14);
		jTable2.setFont(fT);

		jTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = jTable2.getSelectedRow();
					// String authorName = (String) jTable2.getValueAt(row, 1);

					try {
						String detaile = (String) jTable2.getValueAt(row, 0);
						detailedoc = detailedService.getDetaileDocById(detaile);
						tfDocBarCode.setText(detailedoc.getMavachsach().trim());
						tfDocID.setText(detailedoc.getMatl().trim());
						tfStatus.setValue(detailedoc.getNgaycapnhat());
						tfUserID.setText(detailedoc.getMangdung().trim());
						tfIssueDate.setText(detailedoc.getTinhtrang().trim());
						tfHandleNote.setText(detailedoc.getDanhdauxuly());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					tfDocBarCode.setEditable(false);
					tfDocID.setEditable(false);
					tfUserID.setEditable(false);
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
				setTableData(detailedService.getAllDetaileDoc());
				tfDocBarCode.setEditable(true);
				tfDocID.setEditable(true);
				tfUserID.setEditable(true);
				tfDocBarCode.setText("");
				tfDocID.setText("");
				tfIssueDate.setText("");
				tfUserID.setText("");
				tfStatus.setText("");
				tfHandleNote.setText("");
			}
		});

		tfDocBarCode.addKeyListener(new KeyAdapter() {
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
					tfHandleNote.requestFocusInWindow();
				}
			}
		});

		tfHandleNote.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfIssueDate.requestFocusInWindow();
				}
			}
		});
		tfIssueDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfUserID.requestFocusInWindow();
				}
			}
		});

	}

	protected void btnAddKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void btnAddActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general_1.png"));
			g2d.drawImage(imgLabelGeneral, 0, 45, DetailedDocManagePanel.this);

			Image searchIcon = ImageIO.read(getClass().getResource("/resources/images/search_ic_32.png"));
			g2d.drawImage(searchIcon, 698, 160, DetailedDocManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Detailed Document Management", 45, 105);

		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_0.png"));
			g2d.drawImage(imgButtonReturn, -20, 650, DetailedDocManagePanel.this);

			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_0.png"));
			g2d.drawImage(imgButtonTurnOff, 1062, 650, DetailedDocManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doClick(String name) {
		if (name.equals(BTN_RETURN)) {
			repaint();
			DetailedDocManagePanel.this.setVisible(false);
			MainPanel mp = (MainPanel) DetailedDocManagePanel.this.getParent();
			mp.showDocumentMenuPanel();
		} else if (name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(DetailedDocManagePanel.this, "Do you actually want to exit?",
					"Alert", JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Calendar cal = (Calendar) evt.getNewValue();
		java.util.Date selDate = cal.getTime();
		tfStatus.setValue(selDate);
	}

	private void setTableData(List<DetaileDoc> detaileDocs) {
		for (DetaileDoc detaileDoc : detaileDocs) {
			defaultTableModel
					.addRow(new Object[] { detaileDoc.getMavachsach(), detaileDoc.getMatl(), detaileDoc.getTinhtrang(),
							detaileDoc.getDanhdauxuly(), detaileDoc.getNgaycapnhat(), detaileDoc.getMangdung() });
		}

	}

	private void setModelTable2() {
		defaultTableModel = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};

		defaultTableModel.addColumn("Barcode");
		defaultTableModel.addColumn("Doc ID");
		defaultTableModel.addColumn("Status");
		defaultTableModel.addColumn("H Note");
		defaultTableModel.addColumn("Issue Date");
		defaultTableModel.addColumn("User ID");
		jTable2.setModel(defaultTableModel);

	}
}
