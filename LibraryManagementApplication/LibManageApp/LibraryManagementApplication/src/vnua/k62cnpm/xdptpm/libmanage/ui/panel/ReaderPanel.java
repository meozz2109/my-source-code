package vnua.k62cnpm.xdptpm.libmanage.ui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import keeptoo.KButton;
import net.proteanit.sql.DbUtils;
import vnua.k62cnpm.xdptpm.libmanage.aboutus.AboutUsFrame;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.DetaileDoc;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.User;
import vnua.k62cnpm.xdptpm.libmanage.datehandle.DateFormatParse;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.JDBCConnection;
import vnua.k62cnpm.xdptpm.libmanage.logic.AppConst;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage.DocumentManagePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage.StaticUserID;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.CalendarWindow;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundedBorder;

public class ReaderPanel extends BasePanel implements PropertyChangeListener {
	public static final String PATH_FONT = ""+AppConst.PATH_FONT;
	private JLabel lblReader, lblDoc, lblUser, lblSheet, lblStatReport, lblSearch, lblAboutUs;
	private JTextField idTextField, nameTextField, mailTextField, phoneTextField, userUpgradeIDTextField,
			accountIDTextField;
	private JFormattedTextField datePickerAddTextField, datePickerUpgradeTextField, datePickerDueTextField;
	private JLabel lblId, lblName, lblEmail, lblPhoneNumber, lblAccountID, lblAccountAddDate, lblAccountUpgradeDate,
			lblAccountDueDate, lblUserUpgradeID;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable2;
	private KButton btnAdd;
	private KButton btnUpgrade;
	private KButton btnDelete;
	private KButton btnRefresh;

	private int noteDatePicker = 0;
	private RoundJTextField searchTextField;
//	private static final String BTN_RETURN = "BTN_RETURN";
//	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
//	private JButton btnReturn, btnTurnOff;
	private DefaultTableModel defaultTableModel;
	private DaoService readerService;
	private Reader reader;
	private List<User> listUser;
	

	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		readerService = new DaoService();
		reader = new Reader();
	}

	@Override
	public void addEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addComp() {
		addKButton();
		addImageIconToKB();
		Font f3 = new Font("Exo 2", Font.ITALIC + Font.BOLD, 20);
		Font fT = new Font("Tahoma", 0, 14);
		Font f1 = new Font("Candara", 0, 25);
		Font f4 = new Font("Candara", 0, 22);
		Font f2 = new Font("Exo 2", Font.BOLD, 23);
		lblEmail = new javax.swing.JLabel();
		btnAdd = new keeptoo.KButton();
		mailTextField = new javax.swing.JTextField();
		btnUpgrade = new keeptoo.KButton();
		phoneTextField = new javax.swing.JTextField();
		btnDelete = new keeptoo.KButton();
		lblPhoneNumber = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();
		lblId = new javax.swing.JLabel();
		btnRefresh = new keeptoo.KButton();
		lblId = new javax.swing.JLabel();
		idTextField = new javax.swing.JTextField();
		nameTextField = new javax.swing.JTextField();
		lblName = new javax.swing.JLabel();
		lblAccountAddDate = new JLabel();
		lblAccountID = new JLabel();
		lblAccountUpgradeDate = new JLabel();
		lblAccountDueDate = new JLabel();
		lblUserUpgradeID = new JLabel();
		accountIDTextField = new JTextField();
		userUpgradeIDTextField = new JTextField();
		// setBackground(new java.awt.Color(60, 63, 65));

		searchTextField = new RoundJTextField(20);
		searchTextField.setFont(f4);
		searchTextField.setForeground(new Color(42, 46, 55));
		searchTextField.setSize(250, 40);
		searchTextField.setBackground(Color.white);
		searchTextField.setLocation(850, 65);
		searchTextField.setText("Full name ");
		searchTextField.setCaretColor(Color.black);
		searchTextField.setFocusable(false);
		add(searchTextField);
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from DocGia where HoTen like ?";
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

			@Override
			public void mouseExited(MouseEvent e) {
				searchTextField.setText("");
				searchTextField.setFocusable(true);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				searchTextField.setText("");
				searchTextField.setFocusable(true);
			}
		});

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

		btnAdd.setSize(110, 36);
		btnAdd.setLocation(445, 270);
		btnAdd.setFont(f2);
		btnAdd.setkBorderRadius(25);
		add(btnAdd);
		btnAdd.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unlikely-arg-type")
			@Override
			public void mouseClicked(MouseEvent e) {
				// check valid
				List<Reader> readers = readerService.getAllReader();
				for (int i = 0; i < readers.size(); i++) {

					Reader reader = readers.get(i);
					// System.out.println(au.getMatg());
					if (idTextField.getText().trim().equals(reader.getID().trim()) || idTextField.getText().equals("")
							|| nameTextField.getText().equals("") || accountIDTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(ReaderPanel.this, "Invalid input reader ID.");
						return;
					}
				}
				
				// handle date format
				DateFormatParse dfp = new DateFormatParse();
				
				try {
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			        
					String dateFormatAdd = dfp.parseDate(datePickerAddTextField.getText().toString()).trim();
					String dateFormatUpgrade = dfp.parseDate(datePickerUpgradeTextField.getText().toString()).trim();
					String dateFormatDue = dfp.parseDate(datePickerDueTextField.getText().toString()).trim();
					
					java.util.Date parsedA = format.parse(dateFormatAdd);
					java.util.Date parsedU = format.parse(dateFormatUpgrade);
					java.util.Date parsedD = format.parse(dateFormatDue);
			        
			        java.sql.Date dateA = new java.sql.Date(parsedA.getTime());
			        java.sql.Date dateU = new java.sql.Date(parsedU.getTime());
			        java.sql.Date dateD = new java.sql.Date(parsedD.getTime());

					reader.setNgaycap(dateA);
					reader.setNgaygiahan(dateU);
					reader.setNgayhethan(dateD);
					
					

				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("OK");
				}
				
				reader.setID(idTextField.getText().toString().trim());
				reader.setName(nameTextField.getText().toString().trim());
				reader.setEmail(mailTextField.getText().toString().trim());
				reader.setSdt(phoneTextField.getText().toString().trim());
				reader.setMatk(accountIDTextField.getText().toString().trim());
				reader.setNgdungcapnhap(userUpgradeIDTextField.getText().toString().trim());

				for (int i = 0; i < readers.size(); i++) {

					Reader reader = readers.get(i);
					// System.out.println(au.getMatg());
					if (datePickerAddTextField.getText().equals(reader.getNgaycap()) == datePickerUpgradeTextField.getText().equals(reader.getNgaygiahan())
							&& datePickerDueTextField.getText().equals(reader.getNgayhethan()) == datePickerAddTextField.getText().equals(reader.getNgaycap()) 
							&& datePickerDueTextField.getText().equals(reader.getNgayhethan()) == datePickerUpgradeTextField.getText().equals(reader.getNgaygiahan()))
						 {
						JOptionPane.showMessageDialog(ReaderPanel.this, "The date entered does not overlap , Attention!");
						return;
					}
					else {
						System.out.println("Thất bại");
					}
					
				}
				readerService.addReader(reader);
				
				defaultTableModel.setRowCount(0);
				setTableData(readerService.getAllReader());
				JOptionPane.showMessageDialog(ReaderPanel.this, "Add reader successfully!  ");
				// reset tf
				idTextField.setText("");
				nameTextField.setText("");
				mailTextField.setText("");
				phoneTextField.setText("");
				accountIDTextField.setText("");
				datePickerAddTextField.setText("");
				datePickerDueTextField.setText("");
				userUpgradeIDTextField.setText("");
				datePickerUpgradeTextField.setText("");
			}
		});

		btnUpgrade.setForeground(Color.black);
		btnUpgrade.setText("Modify");
		btnUpgrade.setBorderPainted(false);
		btnUpgrade.setkAllowGradient(false);
		btnUpgrade.setkBackGroundColor(new java.awt.Color(117, 209, 151));
		btnUpgrade.setkForeGround(new Color(42, 46, 55));
		btnUpgrade.setkHoverColor(new java.awt.Color(20, 123, 182));
		btnUpgrade.setkHoverForeGround(new Color(255, 255, 141));
		btnUpgrade.setSize(110, 36);
		btnUpgrade.setkBorderRadius(25);
		btnUpgrade.setLocation(btnAdd.getX(), btnAdd.getY() + btnAdd.getHeight() + 30);
		btnUpgrade.setFont(f2);
		add(btnUpgrade);
		btnUpgrade.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unlikely-arg-type")
			@Override
			public void mouseClicked(MouseEvent e) {
				// check valid
				List<Reader> readers = readerService.getAllReader();
				for (int i = 0; i < readers.size(); i++) {

					Reader reader = readers.get(i);
					// System.out.println(au.getMatg());
					if (idTextField.getText().equals("") || nameTextField.getText().equals("") || accountIDTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(ReaderPanel.this, "Invalid input reader ID.");
						return;
					}
				}
				
				// handle date format
				DateFormatParse dfp = new DateFormatParse();
				
				try {
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			        
					String dateFormatAdd = dfp.parseDate(datePickerAddTextField.getText().toString()).trim();
					String dateFormatUpgrade = dfp.parseDate(datePickerUpgradeTextField.getText().toString()).trim();
					String dateFormatDue = dfp.parseDate(datePickerDueTextField.getText().toString()).trim();
					
					java.util.Date parsedA = format.parse(dateFormatAdd);
					java.util.Date parsedU = format.parse(dateFormatUpgrade);
					java.util.Date parsedD = format.parse(dateFormatDue);
			        
			        java.sql.Date dateA = new java.sql.Date(parsedA.getTime());
			        java.sql.Date dateU = new java.sql.Date(parsedU.getTime());
			        java.sql.Date dateD = new java.sql.Date(parsedD.getTime());

					reader.setNgaycap(dateA);
					reader.setNgaygiahan(dateU);
					reader.setNgayhethan(dateD);

				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("OK");
				}
				
				reader.setID(idTextField.getText().toString().trim());
				reader.setName(nameTextField.getText().toString().trim());
				reader.setEmail(mailTextField.getText().toString().trim());
				reader.setSdt(phoneTextField.getText().toString().trim());
				reader.setMatk(accountIDTextField.getText().toString().trim());
				reader.setNgdungcapnhap(userUpgradeIDTextField.getText().toString().trim());

				for (int i = 0; i < readers.size(); i++) {

					Reader reader = readers.get(i);
					// System.out.println(au.getMatg());
					if (datePickerAddTextField.getText().equals(reader.getNgaycap()) == datePickerUpgradeTextField.getText().equals(reader.getNgaygiahan())
							&& datePickerDueTextField.getText().equals(reader.getNgayhethan()) == datePickerAddTextField.getText().equals(reader.getNgaycap())
							&& datePickerDueTextField.getText().equals(reader.getNgayhethan()) == datePickerUpgradeTextField.getText().equals(reader.getNgaygiahan()))
						 {
						JOptionPane.showMessageDialog(ReaderPanel.this, "The date entered does not overlap , Attention!");
						return;
					}
					else {
						System.out.println("Thất bại");
					}
					
				}
				readerService.updateReader(reader);
				
				defaultTableModel.setRowCount(0);
				setTableData(readerService.getAllReader());
				JOptionPane.showMessageDialog(ReaderPanel.this, "Update reader successfully!  ");
				// reset tf
				idTextField.setText("");
				nameTextField.setText("");
				mailTextField.setText("");
				phoneTextField.setText("");
				accountIDTextField.setText("");
				datePickerAddTextField.setText("");
				datePickerDueTextField.setText("");
				userUpgradeIDTextField.setText("");
				datePickerUpgradeTextField.setText("");
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
		btnDelete.setLocation(btnAdd.getX(), btnUpgrade.getY() + btnUpgrade.getHeight() + 30);
		btnDelete.setFont(f2);
		add(btnDelete);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {

				int row = jTable2.getSelectedRow();
				
				if (row == -1) {
					JOptionPane.showMessageDialog(ReaderPanel.this, "Please select an ID first", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String readerName = (String) jTable2.getValueAt(row, 1);
					int confirm = JOptionPane.showConfirmDialog(ReaderPanel.this,
							"Are you sure you want to delete the reader " + readerName + "?", "Alert",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						String readerID = (String) jTable2.getValueAt(row, 0);
//						try {
//
//							List<DetaileDoc> detaileDocs = documentService.getAllDetaileDoc();
//							for (int i = 0; i < detaileDocs.size(); i++) {
//								DetaileDoc detaileDoc = detaileDocs.get(i);
//								if (documentID.toString().trim().equals(detaileDoc.getMatl().toString().trim())) {
//									JOptionPane.showMessageDialog(DocumentManagePanel.this,
//											" Attention please !You need to delete " + documentID.toString().trim()
//													+ " in the DetaileDoc table first.");
//
//								}
//							}
						readerService.deleteReader(readerID);
						defaultTableModel.setRowCount(0);
						setTableData(readerService.getAllReader());
//						} catch (Exception e) {
//							e.printStackTrace();
//
//						}
					}

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
		btnRefresh.setLocation(btnAdd.getX(), btnDelete.getY() + btnDelete.getHeight() + 30);
		btnRefresh.setFont(f2);
		add(btnRefresh);
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				setModelTable2();
				setTableData(readerService.getAllReader());
				idTextField.setEditable(true);
				accountIDTextField.setEditable(true);
				idTextField.setText("");
				nameTextField.setText("");
				mailTextField.setText("");
				phoneTextField.setText("");
				accountIDTextField.setText("");
				datePickerAddTextField.setText("");
				datePickerDueTextField.setText("");
				userUpgradeIDTextField.setText("");
				datePickerUpgradeTextField.setText("");
			}
		});

		jTable2.setBackground(new java.awt.Color(60, 63, 65));
		jTable2.setForeground(new java.awt.Color(204, 255, 102));
		jTable2.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null } },
				new String[] { "Reader's ID", "Name", "Email", "Phone Number", "Account's ID", "Account's Add Date",
						"Account's Upgrade Date", "Account's Due Date", "Upgrade Account User ID" }));
		jTable2.setFillsViewportHeight(true);
		jTable2.setFont(fT);
		setModelTable2();
		jTable2.setGridColor(new java.awt.Color(51, 51, 255));
		jTable2.setInheritsPopupMenu(true);
		jScrollPane1.setViewportView(jTable2);
		jScrollPane1.setSize(600, 550);
		jScrollPane1.setLocation(580, 160);
		setTableData(readerService.getAllReader());
		add(jScrollPane1);
		jTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = jTable2.getSelectedRow();
					// String authorName = (String) jTable2.getValueAt(row, 1);
					String readerID = (String) jTable2.getValueAt(row, 0);
					reader = readerService.getReaderById(readerID);
					try {
						idTextField.setText(reader.getID().trim());
						nameTextField.setText(reader.getName().trim());
						mailTextField.setText(reader.getEmail().trim());
						phoneTextField.setText(reader.getSdt().trim());
						accountIDTextField.setText(reader.getMatk().trim());
						datePickerAddTextField.setValue(reader.getNgaycap());
						datePickerUpgradeTextField.setValue(reader.getNgaygiahan());
						datePickerDueTextField.setValue(reader.getNgayhethan());
						userUpgradeIDTextField.setText(reader.getNgdungcapnhap());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					idTextField.setEditable(false);
					accountIDTextField.setEditable(false);
					datePickerAddTextField.setEditable(false);
					datePickerAddTextField.setFocusable(false);
					datePickerUpgradeTextField.setFocusable(false);
					datePickerDueTextField.setEditable(false);
				}

			}
		});

		lblId.setForeground(new java.awt.Color(204, 255, 0));
		lblId.setText("Reader ID (*)");
		lblId.setSize(200, 30);
		lblId.setLocation(160, 160);
		lblId.setFont(f3);
		add(lblId);

		idTextField.setBackground(new java.awt.Color(42, 46, 55));
		idTextField.setFont(f1); // NOI18N
		idTextField.setCaretColor(Color.white);
		idTextField.setForeground(Color.white);
		idTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));

		idTextField.setSize(250, 24);
		idTextField.setLocation(lblId.getX(), lblId.getY() + lblId.getHeight() + 5);
		add(idTextField);

		lblName.setForeground(new java.awt.Color(204, 255, 0));
		lblName.setText("Name");
		lblName.setSize(110, 30);
		lblName.setLocation(lblId.getX(), idTextField.getY() + idTextField.getHeight() + 5);
		lblName.setFont(f3);
		add(lblName);

		nameTextField.setBackground(new java.awt.Color(42, 46, 55));
		nameTextField.setFont(f1); // NOI18N
		nameTextField.setForeground(Color.white);
		nameTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		nameTextField.setSize(250, 24);
		nameTextField.setCaretColor(Color.white);
		nameTextField.setLocation(lblName.getX(), lblName.getY() + lblName.getHeight() + 5);
		add(nameTextField);

		lblEmail.setForeground(new java.awt.Color(204, 255, 0));
		lblEmail.setText("Email ");
		lblEmail.setSize(110, 30);
		lblEmail.setLocation(lblId.getX(), nameTextField.getY() + nameTextField.getHeight() + 5);
		lblEmail.setFont(f3);
		add(lblEmail);

		mailTextField.setBackground(new java.awt.Color(42, 46, 55));
		mailTextField.setFont(f1); // NOI18N
		mailTextField.setForeground(Color.white);
		mailTextField.setCaretPosition(0);
		mailTextField.setHorizontalAlignment(JTextField.LEFT);
		mailTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		mailTextField.setSize(250, 24);
		mailTextField.setCaretColor(Color.white);
		mailTextField.setLocation(lblEmail.getX(), lblEmail.getY() + lblEmail.getHeight() + 5);
		add(mailTextField);

		lblPhoneNumber.setForeground(new java.awt.Color(204, 255, 0));
		lblPhoneNumber.setText("Phone Number");
		lblPhoneNumber.setSize(140, 30);
		lblPhoneNumber.setLocation(lblId.getX(), mailTextField.getY() + mailTextField.getHeight() + 5);
		lblPhoneNumber.setFont(f3);
		add(lblPhoneNumber);

		phoneTextField.setBackground(new java.awt.Color(42, 46, 55));
		phoneTextField.setFont(f1); // NOI18N
		phoneTextField.setForeground(Color.white);
		phoneTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		phoneTextField.setSize(250, 24);
		phoneTextField.setCaretColor(Color.white);
		phoneTextField.setLocation(lblId.getX(), lblPhoneNumber.getY() + lblPhoneNumber.getHeight() + 5);
		add(phoneTextField);

		lblAccountID.setForeground(new java.awt.Color(204, 255, 0));
		lblAccountID.setText("Account's ID (*)");
		lblAccountID.setSize(250, 30);
		lblAccountID.setLocation(lblId.getX(), phoneTextField.getY() + phoneTextField.getHeight() + 5);
		lblAccountID.setFont(f3);
		add(lblAccountID);

		accountIDTextField.setBackground(new java.awt.Color(42, 46, 55));
		accountIDTextField.setFont(f1); // NOI18N
		accountIDTextField.setForeground(Color.white);
		accountIDTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		accountIDTextField.setSize(250, 24);
		accountIDTextField.setCaretColor(Color.white);
		accountIDTextField.setLocation(lblId.getX(), lblAccountID.getY() + lblAccountID.getHeight() + 5);
		add(accountIDTextField);

		lblAccountAddDate.setForeground(new java.awt.Color(204, 255, 0));
		lblAccountAddDate.setText("Account's Add Date (*)");
		lblAccountAddDate.setSize(260, 30);
		lblAccountAddDate.setLocation(accountIDTextField.getX(),
				accountIDTextField.getY() + accountIDTextField.getHeight() + 5);
		lblAccountAddDate.setFont(f3);
		add(lblAccountAddDate);

		// Customized Text Field Plus Date Picker
		datePickerAddTextField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MONTH_FIELD));
		datePickerAddTextField.setValue(new java.util.Date());
		datePickerAddTextField.setFont(f3);
		datePickerAddTextField.setBorder(new MatteBorder(new Insets(0, 0, 2, 0), new Color(255, 255, 141)));
		datePickerAddTextField.setBackground(new Color(42, 46, 55));
		datePickerAddTextField.setForeground(new Color(255, 255, 255));
		datePickerAddTextField.setSize(250, 24);
		datePickerAddTextField.setLocation(lblAccountAddDate.getX(),
				lblAccountAddDate.getY() + lblAccountAddDate.getHeight() + 2);
		add(datePickerAddTextField);

		CalendarWindow cw = new CalendarWindow();
		cw.setUndecorated(true);
		cw.addPropertyChangeListener(this);
		datePickerAddTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				noteDatePicker = 1;
				cw.setLocation(368, 585);
				cw.setVisible(true);
			}
		});

		lblAccountUpgradeDate.setForeground(new java.awt.Color(204, 255, 0));
		lblAccountUpgradeDate.setText("Account's Upgrade Date (*)");
		lblAccountUpgradeDate.setSize(265, 30);
		lblAccountUpgradeDate.setLocation(lblId.getX(),
				datePickerAddTextField.getY() + datePickerAddTextField.getHeight() + 5);
		lblAccountUpgradeDate.setFont(f3);
		add(lblAccountUpgradeDate);

		datePickerUpgradeTextField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MONTH_FIELD));
		datePickerUpgradeTextField.setValue(new java.util.Date());
		datePickerUpgradeTextField.setFont(f3);
		datePickerUpgradeTextField.setBorder(new MatteBorder(new Insets(0, 0, 2, 0), new Color(255, 255, 141)));
		datePickerUpgradeTextField.setBackground(new Color(42, 46, 55));
		datePickerUpgradeTextField.setForeground(new Color(255, 255, 255));
		datePickerUpgradeTextField.setSize(250, 24);
		datePickerUpgradeTextField.setLocation(lblAccountUpgradeDate.getX(),
				lblAccountUpgradeDate.getY() + lblAccountUpgradeDate.getHeight() + 2);
		add(datePickerUpgradeTextField);

		datePickerUpgradeTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				noteDatePicker = 2;
				cw.setLocation(368, 645);
				cw.setVisible(true);
			}
//        	@Override
//        	public void mouseExited(MouseEvent e) {
//        		noteDatePicker = 0;
//        	}
//        	@Override
//        	public void mouseReleased(MouseEvent e) {
//        		noteDatePicker = 0;
//        	}
		});
//
//        accountIDTextField.setBackground(new java.awt.Color(42, 46, 55));
//        accountIDTextField.setFont(new java.awt.Font("Montserrat", 0, 20)); // NOI18N
//        accountIDTextField.setForeground(new java.awt.Color(204, 255, 102));
//        accountIDTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
//        accountIDTextField.setSize(250, 16);
//        accountIDTextField.setLocation(lblId.getX(), lblAccountID.getY()+lblAccountID.getHeight()+10);
//        add(accountIDTextField);

		lblAccountDueDate.setForeground(new java.awt.Color(204, 255, 0));
		lblAccountDueDate.setText("Account's Due Date (*)");
		lblAccountDueDate.setSize(260, 30);
		lblAccountDueDate.setLocation(lblId.getX(),
				lblAccountUpgradeDate.getY() + lblAccountUpgradeDate.getHeight() + 15 + 16);
		lblAccountDueDate.setFont(f3);
		add(lblAccountDueDate);

		datePickerDueTextField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MONTH_FIELD));
		datePickerDueTextField.setValue(new java.util.Date());
		datePickerDueTextField.setFont(f3);
//        datePickerDueTextField.setAlignmentX(CENTER_ALIGNMENT);
//        datePickerDueTextField.setAlignmentY(CENTER_ALIGNMENT);
		datePickerDueTextField.setBorder(new MatteBorder(new Insets(0, 0, 2, 0), new Color(255, 255, 141)));
		datePickerDueTextField.setBackground(new Color(42, 46, 55));
		datePickerDueTextField.setForeground(new Color(255, 255, 255));
		datePickerDueTextField.setSize(250, 24);
		datePickerDueTextField.setLocation(lblAccountDueDate.getX(),
				lblAccountDueDate.getY() + lblAccountDueDate.getHeight() + 2);
		add(datePickerDueTextField);

		datePickerDueTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				noteDatePicker = 3;
				cw.setLocation(368, 705);
				cw.setVisible(true);
			}
//        	@Override
//        	public void mouseExited(MouseEvent e) {
//        		noteDatePicker = 0;
//        	}
//        	@Override
//        	public void mouseReleased(MouseEvent e) {
//        		noteDatePicker = 0;
//        	}
		});

		lblUserUpgradeID.setForeground(new java.awt.Color(204, 255, 0));
		lblUserUpgradeID.setText("Upgrade Account User ID (*)");
		lblUserUpgradeID.setSize(265, 30);
		lblUserUpgradeID.setLocation(lblId.getX(),
				datePickerDueTextField.getY() + datePickerDueTextField.getHeight() + 5);
		lblUserUpgradeID.setFont(f3);
		add(lblUserUpgradeID);

		userUpgradeIDTextField.setBackground(new java.awt.Color(42, 46, 55));
		userUpgradeIDTextField.setFont(f1); // NOI18N
		userUpgradeIDTextField.setForeground(Color.white);
		userUpgradeIDTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		userUpgradeIDTextField.setSize(250, 24);
		userUpgradeIDTextField.setCaretColor(Color.white);
		userUpgradeIDTextField.setLocation(lblId.getX(), lblUserUpgradeID.getY() + lblUserUpgradeID.getHeight() + 5);
		add(userUpgradeIDTextField);
		
		// Update static User ID

		idTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					nameTextField.requestFocusInWindow();
				}
			}
		});

		nameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					mailTextField.requestFocusInWindow();
				}
			}
		});

		mailTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					phoneTextField.requestFocusInWindow();
				}
			}
		});

		phoneTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					accountIDTextField.requestFocusInWindow();
				}
			}
		});

		accountIDTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					userUpgradeIDTextField.requestFocusInWindow();
				}
			}
		});
//
//		datePickerAddTextField.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == e.VK_ENTER) {
//					datePickerUpgradeTextField.requestFocusInWindow();
//				}
//			}
//		});
//
//		datePickerUpgradeTextField.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == e.VK_ENTER) {
//					datePickerDueTextField.requestFocusInWindow();
//				}
//			}
//		});
//
//		datePickerDueTextField.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == e.VK_ENTER) {
//					userUpgradeIDTextField.requestFocusInWindow();
//				}
//			}
//		});
//		btnReturn = createJButton("", f1, 300, 1050, 40, new Color(23, 24, 25), Color.white, BTN_RETURN);
//		btnReturn.setSize(300, 65);
//		add(btnReturn);
//		
//		btnTurnOff = createJButton("", f1, 300, 1062, 670, new Color(23, 24, 25), Color.white, BTN_TURN_OFF);
//		btnTurnOff.setSize(300, 65);
//		add(btnTurnOff);
//		JLabel lblMain = createLabel("Reader Management", new Font("Allura", 1, 40), 200, 50, new Color(255, 255, 141));
//		lblMain.setSize(400, 50);
//		lblMain.setBackground(new Color(42, 46, 55));
//		add(lblMain);
	}

	protected void btnAddKeyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	protected void btnAddActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void addKButton() {

		// ------NEW FONT-----------------------------NEW
		// FONT-----------------------------NEW FONT-----------------------NEW
		// FONT------------------------------------
		// ::::::::::::::::::::::NEW FONT::::::::::::::::::::::::::::::NEW
		// FONT:::::::::::::::::::::::::::::::NEW FONT::::::::::::::::::::::
		createNewFont();
		createJLabel();
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

		lblStatReport = createLabel("<html>Statistic&nbsp;&&nbsp;<p>&nbsp;&nbsp;Report</html>", f1, 0, 440,
				Color.white);
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
		lblSearch.setBackground(new Color(102, 204, 255));// 236, 226, 67
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

	public void createNewFont() {
		try {
			// create the font to use. Specify the size!
			Font Coquin = Font.createFont(Font.TRUETYPE_FONT, new File(PATH_FONT + "Coquin.ttf"));
			Font Dalgona = Font.createFont(Font.TRUETYPE_FONT, new File(PATH_FONT + "Dalgona.ttf"));
			Font Exo2 = Font.createFont(Font.TRUETYPE_FONT, new File(PATH_FONT + "Exo2-SemiBoldItalic.ttf"));
			Font Blackjack = Font.createFont(Font.TRUETYPE_FONT, new File(PATH_FONT + "blackjack.otf"));
			Font Allura = Font.createFont(Font.TRUETYPE_FONT, new File(PATH_FONT + "Allura.otf"));
			Font Montserrat = Font.createFont(Font.TRUETYPE_FONT, new File(PATH_FONT + "Montserrat.ttf"));
			Font Amsterdam = Font.createFont(Font.TRUETYPE_FONT, new File(PATH_FONT + "Amsterdam-VwYy.ttf"));

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			// register the font
			ge.registerFont(Blackjack);
			ge.registerFont(Allura);
			ge.registerFont(Montserrat);
			ge.registerFont(Amsterdam);
			ge.registerFont(Exo2);
			ge.registerFont(Coquin);
			ge.registerFont(Dalgona);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void addMouseListenerForJLabel() {
		lblReader.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			}
		});

		lblDoc.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ReaderPanel.this.setVisible(false);
				Component c = (Component) ReaderPanel.this.getParent();
				if (c.getName() == "LIB") {

				} else {
					MainPanel mp = (MainPanel) ReaderPanel.this.getParent();
					mp.showDocPanel();
				}

			}
		});

		lblUser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ReaderPanel.this.setVisible(false);
				Component c = (Component) ReaderPanel.this.getParent();
				if (c.getName() == "LIB") {

				} else {
					MainPanel mp = (MainPanel) ReaderPanel.this.getParent();
					mp.showUserPanel();
				}
			}
		});

		lblSheet.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ReaderPanel.this.setVisible(false);
				Component c = (Component) ReaderPanel.this.getParent();
				if (c.getName() == "LIB") {
					MainLibrarianPanel mlp = (MainLibrarianPanel) ReaderPanel.this.getParent();
					mlp.showSheetPanel();
				} else {
					MainPanel mp = (MainPanel) ReaderPanel.this.getParent();
					mp.showSheetPanel();
				}
			}
		});

		lblStatReport.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ReaderPanel.this.setVisible(false);
				Component c = (Component) ReaderPanel.this.getParent();
				if (c.getName() == "LIB") {
					MainLibrarianPanel mlp = (MainLibrarianPanel) ReaderPanel.this.getParent();
					mlp.showStatisticReportPanel();
				} else {
					MainPanel mp = (MainPanel) ReaderPanel.this.getParent();
					mp.showStatisticReportPanel();
				}
			}
		});

		lblSearch.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ReaderPanel.this.setVisible(false);
				Component c = (Component) ReaderPanel.this.getParent();
				if (c.getName() == "LIB") {
					MainLibrarianPanel mlp = (MainLibrarianPanel) ReaderPanel.this.getParent();
					mlp.showSearchPanel();
				} else {
					MainPanel mp = (MainPanel) ReaderPanel.this.getParent();
					mp.showSearchPanel();
				}
			}
		});

		lblAboutUs.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {

			Image searchIcon = ImageIO.read(getClass().getResource("/resources/images/search_ic_32.png"));
			g2d.drawImage(searchIcon, 813, 70, ReaderPanel.this);

			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general.png"));
			g2d.drawImage(imgLabelGeneral, 125, 45, ReaderPanel.this);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Font f1 = new Font("Exo", 0, 55);
		Font f2 = new Font("Highland", 0, 50);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Reader Management", 140, 105);

		g2d.setColor(new Color(234, 83, 65));
//		g2d.drawRoundRect(147, 203, 312, 395, 45, 45);
//		g2d.drawRoundRect(655, 200, 525, 400, 45, 45);
		try {
//			Image imgBg0 = ImageIO.read(getClass().getResource("/resources/images/border_reader_1.png"));
//			g2d.drawImage(imgBg0, 138, 159, ReaderPanel.this);

//			Image imgBg1 = ImageIO.read(getClass().getResource("/resources/images/bg_read_panel_3.png"));
//			g2d.drawImage(imgBg1, 636, 192, ReaderPanel.this);

		} catch (Exception e) {
			e.printStackTrace();
		}

//		g2d.setColor(new Color(43, 45, 55));
//
//		g2d.drawLine(636, 190, 636, 196);
//		g2d.drawLine(637, 190, 637, 196);
//		g2d.drawLine(642, 611, 1488, 611);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("selectedDate")) {
			Calendar cal = (Calendar) evt.getNewValue();
			java.util.Date selDate = cal.getTime();
			if (noteDatePicker == 1) {
				datePickerAddTextField.setValue(selDate);
			} else if (noteDatePicker == 2) {
				datePickerUpgradeTextField.setValue(selDate);
			} else if (noteDatePicker == 3) {
				datePickerDueTextField.setValue(selDate);
			} else {
				System.out.println("OK note 0");
			}

		}
	}

	private void setTableData(List<Reader> reader) {
		for (Reader reade : reader) {
			defaultTableModel.addRow(new Object[] { reade.getID(), reade.getName(), reade.getEmail(), reade.getSdt(),
					reade.getMatk(), reade.getNgaycap(), reade.getNgayhethan(), reade.getNgdungcapnhap(),
					reade.getNgaygiahan() });
		}
	}

	private void setModelTable2() {
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};

		defaultTableModel.addColumn("Reader ID");
		defaultTableModel.addColumn("Full name");
		defaultTableModel.addColumn("Email");
		defaultTableModel.addColumn("Phone number");
		defaultTableModel.addColumn("Account ID");
		defaultTableModel.addColumn("Add Date");
		defaultTableModel.addColumn("Due Date");
		defaultTableModel.addColumn("Up User ID");
		defaultTableModel.addColumn("Up Date");
		

		jTable2.setModel(defaultTableModel);

	}

}


	