package vnua.k62cnpm.xdptpm.libmanage.ui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import keeptoo.KButton;
import net.proteanit.sql.DbUtils;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.JDBCConnection;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage.SOSManagePanel;

public class SearchReaderPanel extends BasePanel {
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private keeptoo.KButton btnSearch, btnCancel;
	private javax.swing.JScrollPane jScrollPane;
	private javax.swing.JTable jTable;
	private DefaultTableModel defaultTableModel;
	private DaoService readerService;
	private javax.swing.JTextField tfReaderID, tfName, tfPhoneNum, tfUserUpID;
	private javax.swing.JLabel lblReaderID, lblName, lblPhoneNum, lblUserUpID;

	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		readerService = new DaoService();
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
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null } },
				new String[] { "Reader's ID", "Name", "Email", "Phone Number", "Account's ID", "Account's Add Date",
						"Account's Upgrade Date", "Account's Due Date", "Upgrade Account User ID" }));
		jTable.setFillsViewportHeight(true);
		jTable.setGridColor(new java.awt.Color(51, 51, 255));
		setModelTable();
		jTable.setInheritsPopupMenu(true);
		jScrollPane.setViewportView(jTable);
		jScrollPane.setSize(900, 350);
		jScrollPane.setLocation(140, 380);
		setTableData(readerService.getAllReader());

		Font fT = new Font("Tahoma", 0, 14);
		jTable.setFont(fT);

		add(jScrollPane);

		lblReaderID = new javax.swing.JLabel();
		tfReaderID = new javax.swing.JTextField();
		lblName = new javax.swing.JLabel();
		tfName = new javax.swing.JTextField();
		lblPhoneNum = new JLabel();
		tfPhoneNum = new JTextField();

		lblReaderID.setForeground(new java.awt.Color(204, 255, 0));
		lblReaderID.setText("Reader 's ID : ");
		lblReaderID.setSize(275, 30);
		lblReaderID.setLocation(250, 150);
		lblReaderID.setFont(f1);
		add(lblReaderID);

		Color greenLabel = new Color(204, 255, 0);
		Color backColor = new Color(42, 46, 55);
		Color tfColor = Color.white;
		Font fTf = new Font("Candara", 0, 25);

		tfReaderID.setBackground(new java.awt.Color(42, 46, 55));
		tfReaderID.setFont(fTf); // NOI18N
		tfReaderID.setForeground(tfColor);
		tfReaderID
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));

		tfReaderID.setSize(250, 24);
		tfReaderID.setLocation(lblReaderID.getX(), lblReaderID.getY() + lblReaderID.getHeight() + 10);
		add(tfReaderID);
		tfReaderID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from DocGia where MaDocGia like ?";
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

		lblName.setForeground(new java.awt.Color(204, 255, 0));
		lblName.setText("Full Name : ");
		lblName.setSize(250, 30);
		lblName.setLocation(lblReaderID.getX(), tfReaderID.getY() + tfReaderID.getHeight() + 10);
		lblName.setFont(f1);
		add(lblName);

		tfName.setBackground(new java.awt.Color(42, 46, 55));
		tfName.setFont(fTf); // NOI18N
		tfName.setForeground(tfColor);
		tfName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfName.setSize(250, 24);
		tfName.setLocation(lblName.getX(), lblName.getY() + lblName.getHeight() + 10);
		add(tfName);
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from DocGia where HoTen like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+tfName.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

		lblPhoneNum.setForeground(new java.awt.Color(204, 255, 0));
		lblPhoneNum.setText("Phone Number :");
		lblPhoneNum.setSize(250, 30);
		lblPhoneNum.setLocation(lblReaderID.getX() + lblReaderID.getWidth() + 150, lblReaderID.getY());
		lblPhoneNum.setFont(f1);
		add(lblPhoneNum);

		tfPhoneNum.setBackground(new java.awt.Color(42, 46, 55));
		tfPhoneNum.setFont(fTf);
		tfPhoneNum.setForeground(tfColor);
		tfPhoneNum.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfPhoneNum.setSize(250, 24);
		tfPhoneNum.setLocation(lblPhoneNum.getX(), lblPhoneNum.getY() + lblPhoneNum.getHeight() + 10);
		add(tfPhoneNum);
		tfPhoneNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from DocGia where SoDienThoai like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+tfPhoneNum.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

		lblUserUpID = createLabel("Upgrade Acc User ID :", f1, tfPhoneNum.getX(), tfPhoneNum.getY() + tfPhoneNum.getHeight() + 10,
				greenLabel);
		lblUserUpID.setSize(265, 30);
		lblUserUpID.setBackground(backColor);
		add(lblUserUpID);

		tfUserUpID = createJTextField(fTf, 200, lblUserUpID.getX(),
				lblUserUpID.getY() + lblUserUpID.getHeight() + 10, tfColor);
		tfUserUpID
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfUserUpID.setBackground(backColor);
		tfUserUpID.setSize(250, 24);
		add(tfUserUpID);
		tfUserUpID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from DocGia where MaNguoiDungCapNhat like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+tfUserUpID.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

	}

	private void setTableData(List<Reader> reader) {
		for (Reader reade : reader) {
			defaultTableModel.addRow(new Object[] { reade.getID(), reade.getName(), reade.getEmail(), reade.getSdt(),
					reade.getMatk(), reade.getNgaycap(), reade.getNgayhethan(), reade.getNgdungcapnhap(),
					reade.getNgaygiahan() });
		}
	}

	private void setModelTable() {
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

		jTable.setModel(defaultTableModel);

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general_0.png"));
			g2d.drawImage(imgLabelGeneral, 0, 45, SearchReaderPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Search for Reader", 80, 105);

		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_0.png"));
			g2d.drawImage(imgButtonReturn, -20, 650, SearchReaderPanel.this);

			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_0.png"));
			g2d.drawImage(imgButtonTurnOff, 1062, 650, SearchReaderPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		g2d.setColor(new Color());

	}

	@Override
	protected void doClick(String name) {
		if (name.equals(BTN_RETURN)) {
			repaint();
			SearchReaderPanel.this.setVisible(false);
			Component c = (Component) SearchReaderPanel.this.getParent();
			if (c.getName() == "LIB") {
				MainLibrarianPanel mlp = (MainLibrarianPanel) SearchReaderPanel.this.getParent();
				mlp.showSearchMenuPanel();
			} else {
				MainPanel mp = (MainPanel) SearchReaderPanel.this.getParent();
				mp.showSearchMenuPanel();
			}
		} else if (name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(SearchReaderPanel.this, "Do you actually want to exit?", "Alert",
					JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}
