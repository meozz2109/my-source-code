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

import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.Supplier;
import vnua.k62cnpm.xdptpm.libmanage.ServiceDao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;

public class SupplierManagePanel extends BasePanel{
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private JTextField idTextField, nameTextField, tfAddress, tfPhone, tfEmail;
	private JLabel lblId, lblName, lblAddress, lblPhone, lblEmail;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable2;
	private keeptoo.KButton btnAdd;
	private keeptoo.KButton btnUpgrade;
	private keeptoo.KButton btnDelete;
	private keeptoo.KButton btnRefresh;
	private RoundJTextField searchTextField;
	private DefaultTableModel defaultTableModel;
	private DaoService supplierService;
	private Supplier sup;
	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		supplierService = new DaoService();
		sup = new Supplier();
	}

	@Override
	public void addEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComp() {
		Font f1 = new Font("Exo 2", Font.ITALIC + Font.BOLD, 20);
		Font f2 = new Font("Exo 2", Font.BOLD, 23);
		Font f3 = new Font("Candara", 0, 25);
		Color greenLabel = new Color(204, 255, 0);
        Color backColor = new Color(42, 46, 55);
        Color tfColor = Color.white;//204, 255, 102
        Font fTf = new Font("Candara", 0, 25);
        Font f4 = new Font("Candara", 0, 22);
		btnReturn = createJButton("", f1, 300, -180, 670, new Color(23, 24, 25), Color.white, BTN_RETURN);
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

		lblAddress = new JLabel();
		btnAdd = new keeptoo.KButton();
		tfAddress = new javax.swing.JTextField();
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
		searchTextField.setLocation(68, 155);
		searchTextField.setText("Supplier's name");
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
		lblId.setText("Supplier's ID (*)");
		lblId.setSize(250, 30);
		lblId.setLocation(45, 210);
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
		lblName.setText("Supplier's name  (*)");
		lblName.setSize(250, 30);
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

		lblAddress.setForeground(new java.awt.Color(204, 255, 0));
		lblAddress.setText("Address");
		lblAddress.setSize(150, 30);
		lblAddress.setLocation(lblId.getX(), nameTextField.getY() + nameTextField.getHeight() + 20);
		lblAddress.setFont(f1);
		add(lblAddress);

		tfAddress.setBackground(new java.awt.Color(42, 46, 55));
		tfAddress.setFont(fTf); // NOI18N
		tfAddress.setForeground(tfColor);
		tfAddress
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfAddress.setSize(250, 24);
		tfAddress.setLocation(lblAddress.getX(), lblAddress.getY() + lblAddress.getHeight() + 10);
		add(tfAddress);

		lblPhone = createLabel("Phone number", f1, tfAddress.getX(), tfAddress.getY()+tfAddress.getHeight()+10, greenLabel);
        lblPhone.setSize(150, 30);
        lblPhone.setBackground(backColor);
        add(lblPhone);
        
        tfPhone = createJTextField(f3, 200, lblPhone.getX(), lblPhone.getY()+lblPhone.getHeight()+10, tfColor);
        tfPhone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
        tfPhone.setBackground(backColor);
        tfPhone.setFont(fTf);
        tfPhone.setSize(idTextField.getWidth(), 24);
        add(tfPhone);
        
        lblEmail = createLabel("Email (*)", f1, tfPhone.getX(), tfPhone.getY()+tfPhone.getHeight()+10, greenLabel);
        lblEmail.setSize(150, 30);
        lblEmail.setBackground(backColor);
        add(lblEmail);
        
        tfEmail = createJTextField(f3, 200, lblEmail.getX(), lblEmail.getY()+lblEmail.getHeight()+10, tfColor);
        tfEmail.setFont(fTf);
        tfEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
        tfEmail.setBackground(backColor);
        tfEmail.setSize(idTextField.getWidth(), 24);
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
		btnAdd.setLocation(375, 270);
		btnAdd.setFont(f2);
		btnAdd.setkBorderRadius(25);
		add(btnAdd);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {

				List<Supplier> listSupplier = supplierService.getAllSupplier();
				for (int i = 0; i < listSupplier.size(); i++) {

					Supplier au = listSupplier.get(i);
					System.out.println(au.getMancc());
					if (idTextField.getText().trim().equals(au.getMancc().trim()) || idTextField.getText().equals("")
							|| nameTextField.getText().equals("") || tfAddress.getText().equals("")
							|| tfPhone.getText().equals("") || tfEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(SupplierManagePanel.this, "Invalid input supplier ID.");
						return;
					}

				}
				sup.setMancc(idTextField.getText());
				sup.setTenncc(nameTextField.getText());
				sup.setDiachi(tfAddress.getText());
				sup.setSdt(tfPhone.getText());
				sup.setEmail(tfEmail.getText());

				supplierService.addSupplier(sup);
				defaultTableModel.setRowCount(0);
				setTableData(supplierService.getAllSupplier());
				JOptionPane.showMessageDialog(SupplierManagePanel.this, "Add supplier successfully!  ");
				// reset tf
				idTextField.setText("");
				nameTextField.setText("");
				tfAddress.setText("");
				tfPhone.setText("");
				tfEmail.setText("");
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
				String supplierName = (String) jTable2.getValueAt(row, 1);
				if (row == -1) {
					JOptionPane.showMessageDialog(SupplierManagePanel.this, "Vui lòng chọn user trước", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else {

					int confirm = JOptionPane.showConfirmDialog(SupplierManagePanel.this,
							"Bạn có chắc chắn muốn xóa NhàCC: " + supplierName + " ko?", "Alert",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						String supplierID = (String) jTable2.getValueAt(row, 0);
						supplierService.deleteSupplier(supplierID);
						defaultTableModel.setRowCount(0);
						setTableData(supplierService.getAllSupplier());

					}

				}
			}

		});

		jTable2.setBackground(new java.awt.Color(60, 63, 65));
		jTable2.setForeground(new java.awt.Color(204, 255, 102));
		jTable2.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Supplier's ID", "Supplier's name", "Address", "Phone number", "Email" }));
		jTable2.setFillsViewportHeight(true);
		jTable2.setGridColor(new java.awt.Color(51, 51, 255));
		setModelTable2();
		jTable2.setInheritsPopupMenu(true);
		jScrollPane1.setViewportView(jTable2);
		jScrollPane1.setSize(600, 400);
		jScrollPane1.setLocation(568, 220);
		setTableData(supplierService.getAllSupplier());
		add(jScrollPane1);

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
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general_0.png"));
			g2d.drawImage(imgLabelGeneral, 0, 45, SupplierManagePanel.this);
			
			Image searchIcon = ImageIO.read(getClass().getResource("/resources/images/search_ic_32.png"));
			g2d.drawImage(searchIcon, 32, 160, SupplierManagePanel.this);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Supplier Management", 60, 105);
		
		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_0.png"));
			g2d.drawImage(imgButtonReturn, -20, 650, SupplierManagePanel.this);

			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_0.png"));
			g2d.drawImage(imgButtonTurnOff, 1062, 650, SupplierManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doClick(String name) {
		if(name.equals(BTN_RETURN)) {
			repaint();
			SupplierManagePanel.this.setVisible(false);
			MainPanel mp = (MainPanel) SupplierManagePanel.this.getParent();
			mp.showDocumentMenuPanel();
		} else if(name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(SupplierManagePanel.this, "Do you actually want to exit?","Alert", JOptionPane.YES_NO_OPTION);
			if(rs==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	private void setTableData(List<Supplier> suppliers) {
		for (Supplier supplier : suppliers) {
			defaultTableModel.addRow(new Object[] { supplier.getMancc(), supplier.getTenncc(), supplier.getDiachi(),
					supplier.getSdt(), supplier.getEmail() });
		}

	}

	private void setModelTable2() {
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};

		defaultTableModel.addColumn("Ma NCC");
		defaultTableModel.addColumn("Ten NCC");
		defaultTableModel.addColumn("Dia Chi");
		defaultTableModel.addColumn("Dien Thoai");
		defaultTableModel.addColumn("Email");

		jTable2.setModel(defaultTableModel);

	}
}
