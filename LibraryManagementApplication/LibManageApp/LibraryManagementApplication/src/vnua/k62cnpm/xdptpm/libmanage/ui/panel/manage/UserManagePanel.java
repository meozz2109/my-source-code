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
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.CheckoutSheets;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.LogInAccount;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.User;
import vnua.k62cnpm.xdptpm.libmanage.datehandle.DateFormatParse;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.JDBCConnection;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.CalendarWindow;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;

public class UserManagePanel extends BasePanel implements PropertyChangeListener{
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private JTextField idTextField, tfUsername, nameTextField, tfGender, tfEmail, tfPhone;
	private JLabel lblId, lblName, lblUsername, lblDOB, lblGender, lblEmail, lblPhone;
	private JFormattedTextField tfDOB;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable2;
	private keeptoo.KButton btnAdd;
	private keeptoo.KButton btnUpgrade;
	private keeptoo.KButton btnDelete;
	private keeptoo.KButton btnRefresh;
	private RoundJTextField searchTextField;
	private DefaultTableModel defaultTableModel;
	private DaoService userService;
	private User user;

	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		userService = new DaoService();
		user = new User();
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
		btnReturn = createJButton("", f1, 300, -180, 682, new Color(23, 24, 25), Color.white, BTN_RETURN);
		btnReturn.setSize(300, 65);
		add(btnReturn);

		btnTurnOff = createJButton("", f1, 300, 1062, 670, new Color(23, 24, 25), Color.white, BTN_TURN_OFF);
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

		lblUsername = new JLabel();
		btnAdd = new keeptoo.KButton();
		tfUsername = new javax.swing.JTextField();
		btnUpgrade = new keeptoo.KButton();
		btnDelete = new keeptoo.KButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();
		lblId = new JLabel();
		btnRefresh = new keeptoo.KButton();
		lblId = new JLabel();
		idTextField = new javax.swing.JTextField();
		nameTextField = new javax.swing.JTextField();
		lblName = new JLabel();

		searchTextField = new RoundJTextField(20);
		searchTextField.setFont(f4);
		searchTextField.setForeground(new Color(42, 46, 55));
		searchTextField.setSize(285, 40);
		searchTextField.setBackground(Color.white);
		searchTextField.setLocation(800, 65);
		searchTextField.setText("User's name");
		searchTextField.setCaretColor(Color.black);
		searchTextField.setFocusable(false);
		add(searchTextField);
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from NguoiDung where HoTen like ?";
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
		lblId.setText("User's ID (*)");
		lblId.setSize(250, 30);
		lblId.setLocation(40, 135);
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

		lblName.setForeground(new java.awt.Color(204, 255, 0));
		lblName.setText("User's name");
		lblName.setSize(150, 30);
		lblName.setLocation(lblId.getX(), idTextField.getY() + idTextField.getHeight() + 20);
		lblName.setFont(f1);
		add(lblName);

		nameTextField.setBackground(new java.awt.Color(42, 46, 55));
		nameTextField.setFont(fTf); // NOI18N
		nameTextField.setForeground(tfColor);
		nameTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		nameTextField.setSize(250, 24);
		nameTextField.setLocation(lblName.getX(), lblName.getY() + lblName.getHeight() + 10);
		add(nameTextField);

		lblUsername.setForeground(new java.awt.Color(204, 255, 0));
		lblUsername.setText("Username (*)");
		lblUsername.setSize(150, 30);
		lblUsername.setLocation(lblId.getX(), nameTextField.getY() + nameTextField.getHeight() + 20);
		lblUsername.setFont(f1);
		add(lblUsername);

		tfUsername.setBackground(new java.awt.Color(42, 46, 55));
		tfUsername.setFont(fTf);
		tfUsername.setForeground(tfColor);
		tfUsername.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfUsername.setSize(250, 24);
		tfUsername.setLocation(lblUsername.getX(), lblUsername.getY() + lblUsername.getHeight() + 10);
		add(tfUsername);

		lblDOB = createLabel("D.O.B", f1, lblId.getX(), tfUsername.getY() + tfUsername.getHeight() + 10, greenLabel);
		lblDOB.setSize(150, 30);
		lblDOB.setBackground(backColor);
		add(lblDOB);

		tfDOB = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MONTH_FIELD));
		tfDOB.setValue(new java.util.Date());
		tfDOB.setFont(fTf);
		tfDOB.setBorder(new MatteBorder(new Insets(0, 0, 2, 0), new Color(255, 255, 141)));
		tfDOB.setBackground(backColor);
		tfDOB.setForeground(tfColor);
		tfDOB.setSize(tfUsername.getWidth(), 24);
		tfDOB.setLocation(lblDOB.getX(), lblDOB.getY() + lblDOB.getHeight() + 10);
		add(tfDOB);
		
		CalendarWindow cw = new CalendarWindow();
		cw.setUndecorated(true);
		cw.addPropertyChangeListener(this);
		tfDOB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cw.setLocation(185, 500);
				cw.setVisible(true);
			}
		});
		
		lblGender = createLabel("Gender (M-0, F-1)", f1, lblId.getX(), tfDOB.getY() + tfDOB.getHeight() + 10,
				greenLabel);
		lblGender.setSize(250, 30);
		lblGender.setBackground(backColor);
		add(lblGender);

		tfGender = createJTextField(fTf, 200, lblGender.getX(), lblGender.getY() + lblGender.getHeight() + 10, tfColor);
		tfGender.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfGender.setBackground(backColor);
		tfGender.setFont(fTf);
		tfGender.setSize(tfUsername.getWidth(), 24);
		add(tfGender);

		lblPhone = createLabel("Phone number", f1, lblId.getX(), tfGender.getY() + tfGender.getHeight() + 10,
				greenLabel);
		lblPhone.setSize(150, 30);
		lblPhone.setBackground(backColor);
		add(lblPhone);

		tfPhone = createJTextField(fTf, 200, lblPhone.getX(), lblPhone.getY() + lblPhone.getHeight() + 10, tfColor);
		tfPhone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfPhone.setBackground(backColor);
		tfPhone.setFont(fTf);
		tfPhone.setSize(tfUsername.getWidth(), 24);
		add(tfPhone);

		lblEmail = createLabel("Email", f1, lblId.getX(), tfPhone.getY() + tfPhone.getHeight() + 10, greenLabel);
		lblEmail.setSize(150, 30);
		lblEmail.setBackground(backColor);
		add(lblEmail);

		tfEmail = createJTextField(fTf, 200, lblEmail.getX(), lblEmail.getY() + lblEmail.getHeight() + 10, tfColor);
		tfEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfEmail.setBackground(backColor);
		tfEmail.setFont(fTf);
		tfEmail.setSize(tfUsername.getWidth(), 24);
		add(tfEmail);

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
		btnAdd.setLocation(345, 280);
		btnAdd.setFont(f2);
		btnAdd.setkBorderRadius(25);
		add(btnAdd);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// check valid
				List<User> user1 = userService.getAllUser();
				for (int i = 0; i < user1.size(); i++) {

					User user2 = user1.get(i);
					// System.out.println(au.getMatg());
					if (idTextField.getText().trim().equals(user2.getUsername().trim())
							|| idTextField.getText().equals("") || nameTextField.getText().equals("") || tfUsername.getText().equals("")) {
						JOptionPane.showMessageDialog(UserManagePanel.this, "Invalid input user ID.");
						return;
					}
				}

				// handle date format
				DateFormatParse dfp = new DateFormatParse();

				try {

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String dateFormatAdd = dfp.parseDate(tfDOB.getText().toString()).trim();
					java.util.Date parsedA = format.parse(dateFormatAdd);
					java.sql.Date dateA = new java.sql.Date(parsedA.getTime());					
					user.setNamsinh(dateA);
				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("OK");
				}

				user.setUsername(idTextField.getText().toString().trim());
				user.setHoten(nameTextField.getText().toString().trim());
				user.setGioitinh(tfGender.getText().toString().trim());
				user.setEmail(tfEmail.getText().toString().trim());
				user.setDienthoai(tfPhone.getText().toString().trim());
				user.setMangdung(tfUsername.getText().toString().trim());

				userService.addUser(user);

				defaultTableModel.setRowCount(0);
				setTableData(userService.getAllUser());
				JOptionPane.showMessageDialog(UserManagePanel.this, "Add user successfully!  ");
				
				//ddtdao.updateBorrowedStatus(tfReaderID.getText().toString().trim());
				
				// reset tf
				idTextField.setText("");
				nameTextField.setText("");
				tfDOB.setText("");
				tfGender.setText("");
				tfEmail.setText("");
				tfPhone.setText("");
				tfUsername.setText("");
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
			@Override
			public void mouseClicked(MouseEvent e) {
				// check valid
				List<User> user1 = userService.getAllUser();				
					if ( idTextField.getText().equals("") || nameTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(UserManagePanel.this, "Invalid input user ID.");
						return;
					}

				DateFormatParse dfp = new DateFormatParse();

				try {

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String dateFormatAdd = dfp.parseDate(tfDOB.getText().toString()).trim();
					java.util.Date parsedA = format.parse(dateFormatAdd);
					java.sql.Date dateA = new java.sql.Date(parsedA.getTime());					
					user.setNamsinh(dateA);
				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("OK");
				}

				user.setUsername(idTextField.getText().toString().trim());
				user.setHoten(nameTextField.getText().toString().trim());
				user.setGioitinh(tfGender.getText().toString().trim());
				user.setEmail(tfEmail.getText().toString().trim());
				user.setDienthoai(tfPhone.getText().toString().trim());
				user.setMangdung(tfUsername.getText().toString().trim());

				userService.updateUser(user);

				defaultTableModel.setRowCount(0);
				setTableData(userService.getAllUser());
				JOptionPane.showMessageDialog(UserManagePanel.this, "Add user successfully!  ");
				
				//ddtdao.updateBorrowedStatus(tfReaderID.getText().toString().trim());
				
				// reset tf
				idTextField.setText("");
				nameTextField.setText("");
				tfDOB.setText("");
				tfGender.setText("");
				tfEmail.setText("");
				tfPhone.setText("");
				tfUsername.setText("");
			}

			
		});

		btnDelete.setForeground(Color.black);
		btnDelete.setText("Delete");
		btnDelete.setBorderPainted(false);
		btnDelete.setkAllowGradient(false);
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
					JOptionPane.showMessageDialog(UserManagePanel.this, "Please select an ID first", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String userName = (String) jTable2.getValueAt(row, 1);
					int confirm = JOptionPane.showConfirmDialog(UserManagePanel.this,
							"Are you sure you want to delete the user : " + userName + " ?", "Alert",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						String userID = (String) jTable2.getValueAt(row, 0);
						try {
							
							List<LogInAccount> logInAccounts = userService.getAllLogInAccount();
							for (int i = 0; i < logInAccounts.size(); i++) {
								LogInAccount login = logInAccounts.get(i);
								if (userID.toString().trim().equals(login.getUsername().toString().trim())) {
									JOptionPane.showMessageDialog(UserManagePanel.this,
											" Attention please !You need to delete " + userID.toString().trim()
													+ " in the Loginaccount table first.");

								}
							}
							userService.deleteUser(userID);
							defaultTableModel.setRowCount(0);
							setTableData(userService.getAllUser());
						}catch (Exception e) {
								e.printStackTrace();
							}
						

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
				setTableData(userService.getAllUser());
				idTextField.setEditable(true);
				tfUsername.setEditable(true);
				idTextField.setText("");
				nameTextField.setText("");
				tfDOB.setText("");
				tfGender.setText("");
				tfEmail.setText("");
				tfPhone.setText("");
				tfUsername.setText("");
			}
		});

		jTable2.setBackground(new java.awt.Color(60, 63, 65));
		jTable2.setForeground(new java.awt.Color(204, 255, 102));
		jTable2.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null } },
				new String[] { "User's ID", "User's name", "Username", "D.O.B", "Gender", "Phone", "Email" }));
		jTable2.setFillsViewportHeight(true);
		setModelTable2();
		jTable2.setGridColor(new java.awt.Color(51, 51, 255));
		jTable2.setInheritsPopupMenu(true);
		jScrollPane1.setViewportView(jTable2);
		jScrollPane1.setSize(680, 480);
		setTableData(userService.getAllUser());
		jScrollPane1.setLocation(500, lblId.getY());
		add(jScrollPane1);
		
		Font fT = new Font("Tahoma", 0, 14);
		jTable2.setFont(fT);
		
		jTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = jTable2.getSelectedRow();
					// String authorName = (String) jTable2.getValueAt(row, 1);
					String userID = (String) jTable2.getValueAt(row, 0);
					user = userService.getUserById(userID);
					try {
						idTextField.setText(user.getUsername().trim());
						nameTextField.setText(user.getHoten().trim());
						tfDOB.setValue(user.getNamsinh());
						tfGender.setText(user.getGioitinh().trim());
						tfEmail.setText(user.getEmail().trim());
						tfPhone.setText(user.getDienthoai().trim());
						tfUsername.setText(user.getMangdung().trim());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					idTextField.setEditable(false);
					tfUsername.setEditable(false);
				}

			}
		});
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
					tfGender.requestFocusInWindow();
				}
			}
		});
		tfGender.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfEmail.requestFocusInWindow();
				}
			}
		});
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfPhone.requestFocusInWindow();
				}
			}
		});
		tfPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfUsername.requestFocusInWindow();
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
			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general.png"));
			g2d.drawImage(imgLabelGeneral, 0, 45, UserManagePanel.this);

			Image searchIcon = ImageIO.read(getClass().getResource("/resources/images/search_ic_32.png"));
			g2d.drawImage(searchIcon, 763, 70, UserManagePanel.this);

		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("User Management", 30, 95);

		g2d.setColor(new Color(255, 255, 51));
		g2d.drawLine(37, 662, 155, 662);

		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return.png"));
			g2d.drawImage(imgButtonReturn, -20, 678, UserManagePanel.this);

			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off.png"));
			g2d.drawImage(imgButtonTurnOff, 1052, 650, UserManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doClick(String name) {
		if (name.equals(BTN_RETURN)) {
			repaint();
			UserManagePanel.this.setVisible(false);
			MainPanel mp = (MainPanel) UserManagePanel.this.getParent();
			mp.showUserMenuPanel();
		} else if (name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(UserManagePanel.this, "Do you actually want to exit?", "Alert",
					JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	private void setTableData(List<User> user) {
		for (User users : user) {
			defaultTableModel.addRow(new Object[] { users.getUsername(), users.getHoten(), users.getNamsinh(),
					users.getGioitinh(), users.getEmail(), users.getDienthoai(), users.getMangdung() });
		}

	}

	private void setModelTable2() {
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};

		defaultTableModel.addColumn("User ID");
		defaultTableModel.addColumn("Full name");
		defaultTableModel.addColumn("D.O.B");
		defaultTableModel.addColumn("Gender");
		defaultTableModel.addColumn("Email");
		defaultTableModel.addColumn("PhoneNumber");
		defaultTableModel.addColumn("Username");

		jTable2.setModel(defaultTableModel);

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("selectedDate")) {
			Calendar cal = (Calendar) evt.getNewValue();
			java.util.Date selDate = cal.getTime();
			tfDOB.setValue(selDate);
		}
	}
}
