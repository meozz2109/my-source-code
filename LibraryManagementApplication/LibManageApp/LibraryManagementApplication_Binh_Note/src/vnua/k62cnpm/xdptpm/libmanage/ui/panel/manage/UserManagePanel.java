package vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.User;
import vnua.k62cnpm.xdptpm.libmanage.ServiceDao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;

public class UserManagePanel extends BasePanel {
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private JTextField idTextField, tfUsername, nameTextField, tfDOB, tfGender, tfEmail, tfPhone;
	private JLabel lblId, lblName, lblUsername, lblDOB, lblGender, lblEmail, lblPhone;
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
		lblName.setText("User's name ");
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
		lblUsername.setText("Username");
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

		tfDOB = createJTextField(fTf, 200, lblDOB.getX(), lblDOB.getY() + lblDOB.getHeight() + 10, tfColor);
		tfDOB.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfDOB.setBackground(backColor);
		tfDOB.setFont(fTf);
		tfDOB.setSize(tfUsername.getWidth(), 24);
		add(tfDOB);

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
				String userName = (String) jTable2.getValueAt(row, 1);
				if (row == -1) {
					JOptionPane.showMessageDialog(UserManagePanel.this, "Vui lòng trọn user trước ", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else {

					int confirm = JOptionPane.showConfirmDialog(UserManagePanel.this,
							"Bạn có chắc chắn muốn xóa người dùng : " + userName + " ko?", "Alert",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						String userID = (String) jTable2.getValueAt(row, 0);
						userService.deleteUser(userID);
						defaultTableModel.setRowCount(0);
						setTableData(userService.getAllUser());

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

		defaultTableModel.addColumn("User name");
		defaultTableModel.addColumn("Ho Ten");
		defaultTableModel.addColumn("Nam sinh");
		defaultTableModel.addColumn("Gioi tinh");
		defaultTableModel.addColumn("Email");
		defaultTableModel.addColumn("So dt");
		defaultTableModel.addColumn("Ma nguoi dung");

		jTable2.setModel(defaultTableModel);

	}
}
