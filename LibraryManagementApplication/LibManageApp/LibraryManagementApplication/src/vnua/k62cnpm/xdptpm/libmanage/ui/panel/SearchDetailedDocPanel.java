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
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.DetaileDoc;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.JDBCConnection;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage.SOSManagePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.CalendarWindow;

public class SearchDetailedDocPanel extends BasePanel implements PropertyChangeListener{
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private keeptoo.KButton btnSearch, btnCancel;
	private javax.swing.JScrollPane jScrollPane;
	private javax.swing.JTable jTable;
	private DefaultTableModel defaultTableModel;
	private DaoService detailDocService;
	private javax.swing.JTextField tfDocID, tfStatus, tfUserID;
	private javax.swing.JLabel lblDocID, lblStatus, lblIssueDate, lblUserID;
	private JFormattedTextField tfIssueDate;
	
	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		detailDocService = new DaoService();
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
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null } },
				new String[] { "Doc Bar Code", "Document's ID", "Status", "Handle Note", "Issue Date" }));
		jTable.setFillsViewportHeight(true);
		jTable.setGridColor(new java.awt.Color(51, 51, 255));
		setModelTable();
		jTable.setInheritsPopupMenu(true);
		jScrollPane.setViewportView(jTable);
		jScrollPane.setSize(900, 350);
		jScrollPane.setLocation(140, 380);
		setTableData(detailDocService.getAllDetaileDoc());

		Font fT = new Font("Tahoma", 0, 14);
		jTable.setFont(fT);

		add(jScrollPane);

		lblDocID = new javax.swing.JLabel();
		tfDocID = new javax.swing.JTextField();
		lblStatus = new javax.swing.JLabel();
		tfStatus = new javax.swing.JTextField();
		lblIssueDate = new JLabel();

		lblDocID.setForeground(new java.awt.Color(204, 255, 0));
		lblDocID.setText("Document 's ID : ");
		lblDocID.setSize(275, 30);
		lblDocID.setLocation(250, 150);
		lblDocID.setFont(f1);
		add(lblDocID);

		Color greenLabel = new Color(204, 255, 0);
		Color backColor = new Color(42, 46, 55);
		Color tfColor = Color.white;
		Font fTf = new Font("Candara", 0, 25);

		tfDocID.setBackground(new java.awt.Color(42, 46, 55));
		tfDocID.setFont(fTf); // NOI18N
		tfDocID.setForeground(tfColor);
		tfDocID
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));

		tfDocID.setSize(250, 24);
		tfDocID.setLocation(lblDocID.getX(), lblDocID.getY() + lblDocID.getHeight() + 10);
		add(tfDocID);
		tfDocID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from TaiLieuChiTiet where MaTaiLieu like ?";
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

		lblStatus.setForeground(new java.awt.Color(204, 255, 0));
		lblStatus.setText("Status : ");
		lblStatus.setSize(250, 30);
		lblStatus.setLocation(lblDocID.getX(), tfDocID.getY() + tfDocID.getHeight() + 10);
		lblStatus.setFont(f1);
		add(lblStatus);

		tfStatus.setBackground(new java.awt.Color(42, 46, 55));
		tfStatus.setFont(fTf); // NOI18N
		tfStatus.setForeground(tfColor);
		tfStatus.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfStatus.setSize(250, 24);
		tfStatus.setLocation(lblStatus.getX(), lblStatus.getY() + lblStatus.getHeight() + 10);
		add(tfStatus);
		tfStatus.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from TaiLieuChiTiet where TinhTrang like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+tfStatus.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

		lblIssueDate.setForeground(new java.awt.Color(204, 255, 0));
		lblIssueDate.setText("Issue Date :");
		lblIssueDate.setSize(250, 30);
		lblIssueDate.setLocation(lblDocID.getX() + lblDocID.getWidth() + 150, lblDocID.getY());
		lblIssueDate.setFont(f1);
		add(lblIssueDate);

		tfIssueDate = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MONTH_FIELD));
		tfIssueDate.setBackground(new java.awt.Color(42, 46, 55));
		tfIssueDate.setFont(fTf);
		tfIssueDate.setForeground(tfColor);
		tfIssueDate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfIssueDate.setSize(250, 24);
		tfIssueDate.setLocation(lblIssueDate.getX(), lblIssueDate.getY() + lblIssueDate.getHeight() + 10);
		tfIssueDate.setValue(new java.util.Date());
		tfIssueDate.setLocation(lblIssueDate.getX(), lblIssueDate.getY() + lblIssueDate.getHeight() + 2);
		add(tfIssueDate);
		tfIssueDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from TaiLieuChiTiet where NgayCapNhat like ?";
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
				cw.setLocation(825, 255);
				cw.setVisible(true);
			}
		});
		
		lblUserID = createLabel("User 's ID : ", f1, tfIssueDate.getX(), tfIssueDate.getY() + tfIssueDate.getHeight() + 10,
				greenLabel);
		lblUserID.setSize(175, 30);
		lblUserID.setBackground(backColor);
		add(lblUserID);

		tfUserID = createJTextField(fTf, 200, lblUserID.getX(),
				lblUserID.getY() + lblUserID.getHeight() + 10, tfColor);
		tfUserID
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfUserID.setBackground(backColor);
		tfUserID.setSize(250, 24);
		add(tfUserID);
		tfUserID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from TaiLieuChiTiet where MaNguoiDungCapNhat like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,"%"+tfUserID.getText().trim()+"%");
					ResultSet resultSet = pst.executeQuery();
					jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}

		});

	}

	private void setTableData(List<DetaileDoc> detaileDocs) {
		for (DetaileDoc detaileDoc : detaileDocs) {
			defaultTableModel.addRow(
					new Object[] { detaileDoc.getMavachsach(), detaileDoc.getMatl(), detaileDoc.getNgaycapnhat(),
							detaileDoc.getMangdung(), detaileDoc.getTinhtrang(), detaileDoc.getDanhdauxuly() });
		}

	}

	private void setModelTable() {
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
		defaultTableModel.addColumn("Issue Date");
		defaultTableModel.addColumn("User ID");
		defaultTableModel.addColumn("Status");
		defaultTableModel.addColumn("H Note");

		jTable.setModel(defaultTableModel);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Calendar cal = (Calendar) evt.getNewValue();
		java.util.Date selDate = cal.getTime();
		tfIssueDate.setValue(selDate);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general_1.png"));
			g2d.drawImage(imgLabelGeneral, 0, 45, SearchDetailedDocPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Search for Detailed Document", 40, 105);

		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_0.png"));
			g2d.drawImage(imgButtonReturn, -20, 650, SearchDetailedDocPanel.this);

			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_0.png"));
			g2d.drawImage(imgButtonTurnOff, 1062, 650, SearchDetailedDocPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		g2d.setColor(new Color());

	}

	@Override
	protected void doClick(String name) {
		if (name.equals(BTN_RETURN)) {
			repaint();
			SearchDetailedDocPanel.this.setVisible(false);
			Component c = (Component) SearchDetailedDocPanel.this.getParent();
			if (c.getName() == "LIB") {
				MainLibrarianPanel mlp = (MainLibrarianPanel) SearchDetailedDocPanel.this.getParent();
				mlp.showSearchMenuPanel();
			} else {
				MainPanel mp = (MainPanel) SearchDetailedDocPanel.this.getParent();
				mp.showSearchMenuPanel();
			}
		} else if (name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(SearchDetailedDocPanel.this, "Do you actually want to exit?",
					"Alert", JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}
