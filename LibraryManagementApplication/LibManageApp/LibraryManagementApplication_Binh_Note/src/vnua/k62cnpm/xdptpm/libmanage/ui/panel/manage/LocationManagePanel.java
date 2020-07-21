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

import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.Location;
import vnua.k62cnpm.xdptpm.libmanage.ServiceDao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;

public class LocationManagePanel extends BasePanel {
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private JTextField idTextField, noteTextField, nameTextField;
	private JLabel lblId, lblName, lblNote;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable2;
	private keeptoo.KButton btnAdd;
	private keeptoo.KButton btnUpgrade;
	private keeptoo.KButton btnDelete;
	private keeptoo.KButton btnRefresh;
	private RoundJTextField searchTextField;
	private DefaultTableModel defaultTableModel;
	private DaoService locationService;
	private Location location;

	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		locationService = new DaoService();
		location = new Location();
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

		lblNote = new JLabel();
		btnAdd = new keeptoo.KButton();
		noteTextField = new javax.swing.JTextField();
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
		searchTextField.setText("Note");
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
		lblId.setText("Location's ID (*)");
		lblId.setSize(200, 30);
		lblId.setLocation(40, 280);
		lblId.setFont(f1);
		add(lblId);

		idTextField.setBackground(new java.awt.Color(42, 46, 55));
		idTextField.setFont(f3); // NOI18N
		idTextField.setForeground(Color.white);
		idTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));

		idTextField.setSize(250, 24);
		idTextField.setLocation(lblId.getX(), lblId.getY() + lblId.getHeight() + 10);
		add(idTextField);

		lblName.setForeground(new java.awt.Color(204, 255, 0));
		lblName.setText("Location's name (*)");
		lblName.setSize(205, 30);
		lblName.setLocation(lblId.getX(), idTextField.getY() + idTextField.getHeight() + 20);
		lblName.setFont(f1);
		add(lblName);

		nameTextField.setBackground(new java.awt.Color(42, 46, 55));
		nameTextField.setFont(f3); // NOI18N
		nameTextField.setForeground(Color.white);
		nameTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		nameTextField.setSize(250, 24);
		nameTextField.setLocation(lblName.getX(), lblName.getY() + lblName.getHeight() + 10);
		add(nameTextField);

		lblNote.setForeground(new java.awt.Color(204, 255, 0));
		lblNote.setText("Note");
		lblNote.setSize(150, 30);
		lblNote.setLocation(lblId.getX(), nameTextField.getY() + nameTextField.getHeight() + 20);
		lblNote.setFont(f1);
		add(lblNote);

		noteTextField.setBackground(new java.awt.Color(42, 46, 55));
		noteTextField.setFont(f3); // NOI18N
		noteTextField.setForeground(Color.white);
		noteTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		noteTextField.setSize(250, 24);
		noteTextField.setLocation(lblNote.getX(), lblNote.getY() + lblNote.getHeight() + 10);
		add(noteTextField);

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
				List<Location> listLocation = locationService.getAllLocation();
				for (int i = 0; i < listLocation.size(); i++) {
					Location lo = listLocation.get(i);
					// System.out.println(lo.getMavitri());
					if (idTextField.getText().trim().equals(lo.getMavitri().trim()) || idTextField.getText().equals("")
							|| nameTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(LocationManagePanel.this, "Invalid input location ID.");
						return;
					}
				}

				location.setMavitri(idTextField.getText());
				location.setTenvitri(nameTextField.getText());
				location.setMota(noteTextField.getText());

				locationService.addLocation(location);

				defaultTableModel.setRowCount(0);
				setTableData(locationService.getAllLocation());
				JOptionPane.showMessageDialog(LocationManagePanel.this, "Add location successfully! ");

				idTextField.setText("");
				nameTextField.setText("");
				noteTextField.setText("");

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
			public void mouseClicked(MouseEvent me) {
				// check valid
//                List<Author> listAuthor = authorService.getAllAuthor();
//                for(int i = 0; i < listAuthor.size();i++ ){
//                    
//                    Author au = listAuthor.get(i);
//                    
//                    if(idTextField.getText().trim().equals(au.getMatg().trim()) || nameTextField.getText().equals("") ){
//                        JOptionPane.showMessageDialog(AuthorManagePanel.this, "Invalid input author ID.");
//                        return;
//                    }
//                    
//                    
//                }
//                author.setMatg(idTextField.getText());
				location.setTenvitri(nameTextField.getText());
				location.setMota(noteTextField.getText());

				locationService.updateLocation(location);

				defaultTableModel.setRowCount(0);
				setTableData(locationService.getAllLocation());
				JOptionPane.showMessageDialog(LocationManagePanel.this, "Update author successfully!  ");
				// reset tf
				idTextField.setText("");
				nameTextField.setText("");
				noteTextField.setText("");
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
				String locationName = (String) jTable2.getValueAt(row, 1);
				if (row == -1) {
					JOptionPane.showMessageDialog(LocationManagePanel.this, "Vui lÃ²ng chá»�n ID trÆ°á»›c", "Lá»—i",
							JOptionPane.ERROR_MESSAGE);
				} else {

					int confirm = JOptionPane.showConfirmDialog(LocationManagePanel.this,
							"Báº¡n cÃ³ cháº¯c cháº¯n muá»‘n xÃ³a Vá»‹ TrÃ­ " + locationName.trim() + " ko?", "Alert",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						String locationID = (String) jTable2.getValueAt(row, 0);
						locationService.deleteLocation(locationID);
						defaultTableModel.setRowCount(0);
						setTableData(locationService.getAllLocation());

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
						new String[] { "Location's ID", "Location's name", "Note" }));
		jTable2.setFillsViewportHeight(true);
		setModelTable2();
		jTable2.setGridColor(new java.awt.Color(51, 51, 255));
		jTable2.setInheritsPopupMenu(true);
		jScrollPane1.setViewportView(jTable2);
		jScrollPane1.setSize(600, 350);
		jScrollPane1.setLocation(568, 220);
		setTableData(locationService.getAllLocation());
		add(jScrollPane1);
		
		jTable2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(e.getClickCount()==2) {
        			 int row =  jTable2.getSelectedRow();
                    // String authorName = (String) jTable2.getValueAt(row, 1);
        			 String locationID = (String) jTable2.getValueAt(row, 0);
        			 location = locationService.getLocationById(locationID);
        			 idTextField.setText(location.getMavitri().trim());
                     nameTextField.setText(location.getTenvitri().trim());
                     noteTextField.setText(location.getMota().trim());
                    	
                    
        		}
        	}
        } );

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
			g2d.drawImage(imgLabelGeneral, 0, 45, LocationManagePanel.this);

			Image searchIcon = ImageIO.read(getClass().getResource("/resources/images/search_ic_32.png"));
			g2d.drawImage(searchIcon, 32, 160, LocationManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Location Management", 75, 105);

		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_0.png"));
			g2d.drawImage(imgButtonReturn, -20, 650, LocationManagePanel.this);

			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_0.png"));
			g2d.drawImage(imgButtonTurnOff, 1062, 650, LocationManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doClick(String name) {
		if (name.equals(BTN_RETURN)) {
			repaint();
			LocationManagePanel.this.setVisible(false);
			MainPanel mp = (MainPanel) LocationManagePanel.this.getParent();
			mp.showDocumentMenuPanel();
		} else if (name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(LocationManagePanel.this, "Do you actually want to exit?", "Alert",
					JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	private void setTableData(List<Location> location) {
		for (Location locations : location) {
			defaultTableModel
					.addRow(new Object[] { locations.getMavitri(), locations.getTenvitri(), locations.getMota() });
		}

	}

	private void setModelTable2() {
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};

		defaultTableModel.addColumn("Ma Vi Tri");
		defaultTableModel.addColumn("Ten Vi Tri");
		defaultTableModel.addColumn("Mo Ta");

		jTable2.setModel(defaultTableModel);

	}
}
