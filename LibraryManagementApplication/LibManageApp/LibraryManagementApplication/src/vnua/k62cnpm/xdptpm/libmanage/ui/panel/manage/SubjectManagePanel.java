package vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Location;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Subject;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.JDBCConnection;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;

public class SubjectManagePanel extends BasePanel {
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private JTextField idTextField, tfName, tfNote;
	private JLabel lblId, lblName, lblNote;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable2;
	private keeptoo.KButton btnAdd;
	private keeptoo.KButton btnUpgrade;
	private keeptoo.KButton btnDelete;
	private keeptoo.KButton btnRefresh;
	private RoundJTextField searchTextField;
	private DefaultTableModel defaultTableModel;
	private DaoService subjectService;
	Subject sub;
	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		subjectService = new DaoService();
		sub = new Subject();
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
		Color greenLabel = new Color(204, 255, 0);
		Color backColor = new Color(42, 46, 55);
		Color tfColor = Color.white;
		Font fTf = new Font("Candara", 0, 20);
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
		tfNote = new javax.swing.JTextField();
		btnUpgrade = new keeptoo.KButton();
		btnDelete = new keeptoo.KButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();
		lblId = new JLabel();
		btnRefresh = new keeptoo.KButton();
		lblId = new JLabel();
		idTextField = new javax.swing.JTextField();
		tfName = new javax.swing.JTextField();
		lblName = new JLabel();

		searchTextField = new RoundJTextField(20);
		searchTextField.setFont(f4);
		searchTextField.setForeground(new Color(42, 46, 55));
		searchTextField.setSize(285, 40);
		searchTextField.setBackground(Color.white);
		searchTextField.setLocation(68, 155);
		searchTextField.setText("Subject's name");
		searchTextField.setCaretColor(Color.black);
		searchTextField.setFocusable(false);
		add(searchTextField);
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from TheLoai where TenTheLoai like ?";
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
		lblId.setText("Subject's ID (*)");
		lblId.setSize(250, 30);
		lblId.setLocation(40, 280);
		lblId.setFont(f1);
		add(lblId);

		idTextField.setBackground(new java.awt.Color(42, 46, 55));
		idTextField.setFont(f3); // NOI18N
		idTextField.setForeground(tfColor);
		idTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));

		idTextField.setSize(250, 24);
		idTextField.setLocation(lblId.getX(), lblId.getY() + lblId.getHeight() + 10);
		add(idTextField);

		lblName.setForeground(new java.awt.Color(204, 255, 0));
		lblName.setText("Subject's name (*)");
		lblName.setSize(250, 30);
		lblName.setLocation(lblId.getX(), idTextField.getY() + idTextField.getHeight() + 20);
		lblName.setFont(f1);
		add(lblName);

		tfName.setBackground(new java.awt.Color(42, 46, 55));
		tfName.setFont(f3); // NOI18N
		tfName.setForeground(tfColor);
		tfName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfName.setSize(250, 24);
		tfName.setLocation(lblName.getX(), lblName.getY() + lblName.getHeight() + 10);
		add(tfName);

		lblNote = createLabel("Note", f1, tfName.getX(), tfName.getY() + tfName.getHeight() + 10, greenLabel);
		lblNote.setSize(150, 30);
		lblNote.setBackground(backColor);
		add(lblNote);

		tfNote = createJTextField(f3, 200, lblNote.getX(), lblNote.getY() + lblNote.getHeight() + 10, tfColor);
		tfNote.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfNote.setBackground(backColor);
		tfNote.setSize(idTextField.getWidth(), 24);
		add(tfNote);

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
				List<Subject> subjects = subjectService.getAllSubject();
				for (int i = 0; i < subjects.size(); i++) {
					Subject sub = subjects.get(i);
					//System.out.println(lo.getMavitri());
					if (idTextField.getText().trim().equals(sub.getMatheloai().trim()) && tfName.getText().trim().equals(sub.getTentheloai().trim()) 
							|| idTextField.getText().equals("") || tfName.getText().equals("")) {
							JOptionPane.showMessageDialog(SubjectManagePanel.this, "Invalid input subject ID.");
							return;			
					}	
				}
				
				sub.setMatheloai(idTextField.getText());
				sub.setTentheloai(tfName.getText());
				sub.setGhichu(tfNote.getText());

				subjectService.addSubject(sub);
				
				defaultTableModel.setRowCount(0);
				setTableData(subjectService.getAllSubject());
				JOptionPane.showMessageDialog(SubjectManagePanel.this, "Add subject successfully! ");
				
				idTextField.setText("");
				tfName.setText("");
				tfNote.setText("");
			
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
				List<Subject> subjects = subjectService.getAllSubject();
					if ( idTextField.getText().equals("") || tfName.getText().equals("")) {
							JOptionPane.showMessageDialog(SubjectManagePanel.this, "Invalid input subject ID.");
							return;			
					}					
				sub.setMatheloai(idTextField.getText());
				sub.setTentheloai(tfName.getText());
				sub.setGhichu(tfNote.getText());

				subjectService.updateSubject(sub);
				
				defaultTableModel.setRowCount(0);
				setTableData(subjectService.getAllSubject());
				JOptionPane.showMessageDialog(SubjectManagePanel.this, "Update subject successfully! ");
				
				idTextField.setText("");
				tfName.setText("");
				tfNote.setText("");
			
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
					JOptionPane.showMessageDialog(SubjectManagePanel.this, "Please select an ID first", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String subjectName = (String) jTable2.getValueAt(row, 0);
					int confirm = JOptionPane.showConfirmDialog(SubjectManagePanel.this,
							"Are you sure you want to delete the subject " + subjectName.trim() + "?", "Alert",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						String subjectID = (String) jTable2.getValueAt(row, 0);
						subjectService.deleteSubject(subjectID);
						defaultTableModel.setRowCount(0);
						setTableData(subjectService.getAllSubject());

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
						new String[] { "Subject's ID", "Subject's name", "Note"}));
		jTable2.setFillsViewportHeight(true);
		jTable2.setGridColor(new java.awt.Color(51, 51, 255));
		setModelTable2();
		jTable2.setInheritsPopupMenu(true);
		jScrollPane1.setViewportView(jTable2);
		jScrollPane1.setSize(600, 350);
		jScrollPane1.setLocation(568, 220);
		setTableData(subjectService.getAllSubject());
		add(jScrollPane1);
		
		Font fT = new Font("Tahoma", 0, 14);
		jTable2.setFont(fT);
		
		jTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = jTable2.getSelectedRow();
					String subID = (String) jTable2.getValueAt(row, 0);
					sub = subjectService.getSubjectById(subID);

					idTextField.setText(sub.getMatheloai().trim());
					tfName.setText(sub.getTentheloai().trim());
					tfNote.setText(sub.getGhichu().trim());

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
				setTableData(subjectService.getAllSubject());
				idTextField.setEditable(true);
				idTextField.setText("");
				tfName.setText("");
				tfNote.setText("");
			}
		});
		idTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfName.requestFocusInWindow();
				}
			}
		});

		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfNote.requestFocusInWindow();
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
			g2d.drawImage(imgLabelGeneral, 0, 45, SubjectManagePanel.this);
			
			Image searchIcon = ImageIO.read(getClass().getResource("/resources/images/search_ic_32.png"));
			g2d.drawImage(searchIcon, 32, 160, SubjectManagePanel.this);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Subject Management", 60, 105);

		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_0.png"));
			g2d.drawImage(imgButtonReturn, -20, 650, SubjectManagePanel.this);

			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_0.png"));
			g2d.drawImage(imgButtonTurnOff, 1062, 650, SubjectManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doClick(String name) {
		if (name.equals(BTN_RETURN)) {
			repaint();
			SubjectManagePanel.this.setVisible(false);
			MainPanel mp = (MainPanel) SubjectManagePanel.this.getParent();
			mp.showDocumentMenuPanel();
		} else if (name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(SubjectManagePanel.this, "Do you actually want to exit?", "Alert",
					JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
	private void setTableData(List<Subject> subjects) {
		for (Subject subject : subjects) {
			defaultTableModel
					.addRow(new Object[] { subject.getMatheloai(), subject.getTentheloai(), subject.getGhichu() });
		}

	}

	private void setModelTable2() {
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};

		defaultTableModel.addColumn("Sub ID");
		defaultTableModel.addColumn("Sub name");
		defaultTableModel.addColumn("note");

		jTable2.setModel(defaultTableModel);

	}
}
