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
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Author;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.DetaileDoc;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Subject;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Supplier;
import vnua.k62cnpm.xdptpm.libmanage.datehandle.DateFormatParse;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.JDBCConnection;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.CalendarWindow;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;

public class DocumentManagePanel extends BasePanel implements PropertyChangeListener {
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private javax.swing.JTextField docIDTextField, subIDTextField, docNameTextField, tfSuppID, tfAuthID, tfIssueYear,
			tfPreciseContent, tfNOPages, tfCPrices, tfLocID;
	private JFormattedTextField tfIssueDate;
	private javax.swing.JLabel lblId;
	private javax.swing.JLabel lblName;
	private javax.swing.JLabel lblSubID, lblSuppID, lblAuthID, lblIssueYear, lblPreciseContent, lblNOPages, lblHCPrices,
			lblIssueDate, lblLocID;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable2;
	private keeptoo.KButton btnAdd;
	private keeptoo.KButton btnUpgrade;
	private keeptoo.KButton btnDelete;
	private keeptoo.KButton btnRefresh;
	private RoundJTextField searchTextField;
	private DefaultTableModel defaultTableModel;
	private DaoService documentService;
	private Document document;
	private Subject sub;
	private Supplier supplier;
	private Author author;
	private DetaileDoc detaidoc;

	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
		documentService = new DaoService();
		document = new Document();
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
		Font f3 = new Font("Blackjack", 0, 20);
		Font f4 = new Font("Candara", 0, 22);
		Color greenLabel = new Color(204, 255, 0);
		Color backColor = new Color(42, 46, 55);
		Color tfColor = Color.white;
		Font fTf = new Font("Candara", 0, 25);

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

		lblSubID = new javax.swing.JLabel();
		btnAdd = new keeptoo.KButton();
		subIDTextField = new javax.swing.JTextField();
		btnUpgrade = new keeptoo.KButton();
		btnDelete = new keeptoo.KButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();
		lblId = new javax.swing.JLabel();
		btnRefresh = new keeptoo.KButton();
		lblId = new javax.swing.JLabel();
		docIDTextField = new javax.swing.JTextField();
		docNameTextField = new javax.swing.JTextField();
		lblName = new javax.swing.JLabel();

		searchTextField = new RoundJTextField(20);
		searchTextField.setFont(f4);
		searchTextField.setForeground(new Color(42, 46, 55));
		searchTextField.setSize(285, 40);
		searchTextField.setBackground(Color.white);
		searchTextField.setLocation(705, 145);
		searchTextField.setText("Document's name");
		searchTextField.setCaretColor(Color.black);
		searchTextField.setFocusable(false);
		add(searchTextField);
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connection connection = JDBCConnection.getJDBCConnection();
				try {
					String query = "select * from TaiLieu where TenTaiLieu like ?";
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

		lblId.setForeground(new java.awt.Color(204, 255, 0));
		lblId.setText("Document's ID (*)");
		lblId.setSize(250, 30);
		lblId.setLocation(25, 200);
		lblId.setFont(f1);
		add(lblId);

		docIDTextField.setBackground(new java.awt.Color(42, 46, 55));
		docIDTextField.setFont(fTf); // NOI18N
		docIDTextField.setForeground(tfColor);
		docIDTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		docIDTextField.setSize(170, 24);
		docIDTextField.setLocation(lblId.getX(), lblId.getY() + lblId.getHeight() + 10);
		add(docIDTextField);

		lblName.setForeground(new java.awt.Color(204, 255, 0));
		lblName.setText("Document's name (*)");
		lblName.setSize(250, 30);
		lblName.setLocation(lblId.getX(), docIDTextField.getY() + docIDTextField.getHeight() + 10);
		lblName.setFont(f1);
		add(lblName);

		docNameTextField.setBackground(new java.awt.Color(42, 46, 55));
		docNameTextField.setFont(fTf); // NOI18N
		docNameTextField.setForeground(tfColor);
		docNameTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		docNameTextField.setSize(docIDTextField.getWidth(), 24);
		docNameTextField.setLocation(lblName.getX(), lblName.getY() + lblName.getHeight() + 10);
		add(docNameTextField);

		lblSubID.setForeground(new java.awt.Color(204, 255, 0));
		lblSubID.setText("Subject's ID (*)");
		lblSubID.setSize(250, 30);
		lblSubID.setLocation(lblId.getX(), docNameTextField.getY() + docNameTextField.getHeight() + 10);
		lblSubID.setFont(f1);
		add(lblSubID);

		subIDTextField.setBackground(new java.awt.Color(42, 46, 55));
		subIDTextField.setFont(fTf);
		subIDTextField.setForeground(tfColor);
		subIDTextField
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		subIDTextField.setSize(docIDTextField.getWidth(), 24);
		subIDTextField.setLocation(lblSubID.getX(), lblSubID.getY() + lblSubID.getHeight() + 10);
		add(subIDTextField);

		lblSuppID = createLabel("Supplier's ID (*)", f1, lblId.getX(),
				subIDTextField.getY() + subIDTextField.getHeight() + 10, greenLabel);
		lblSuppID.setSize(200, 30);
		lblSuppID.setBackground(backColor);
		add(lblSuppID);

		tfSuppID = createJTextField(fTf, 200, lblSuppID.getX(), lblSuppID.getY() + lblSuppID.getHeight() + 10, tfColor);
		tfSuppID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfSuppID.setBackground(backColor);
		tfSuppID.setSize(docIDTextField.getWidth(), 24);
		add(tfSuppID);

		lblAuthID = createLabel("Author's ID (*)", f1, lblId.getX(), tfSuppID.getY() + tfSuppID.getHeight() + 10,
				greenLabel);
		lblAuthID.setSize(200, 30);
		lblAuthID.setBackground(backColor);
		add(lblAuthID);

		tfAuthID = createJTextField(fTf, 200, lblAuthID.getX(), lblAuthID.getY() + lblAuthID.getHeight() + 10, tfColor);
		tfAuthID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfAuthID.setBackground(backColor);
		tfAuthID.setSize(docIDTextField.getWidth(), 24);
		add(tfAuthID);

		lblIssueYear = createLabel("Issue Year", f1, lblId.getX(), tfAuthID.getY() + tfAuthID.getHeight() + 10,
				greenLabel);
		lblIssueYear.setSize(150, 30);
		lblIssueYear.setBackground(backColor);
		add(lblIssueYear);

		tfIssueYear = createJTextField(fTf, 200, lblIssueYear.getX(),
				lblIssueYear.getY() + lblIssueYear.getHeight() + 10, tfColor);
		tfIssueYear
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfIssueYear.setBackground(backColor);
		tfIssueYear.setSize(docIDTextField.getWidth(), 24);
		add(tfIssueYear);

		lblPreciseContent = createLabel("Precise Content", f1, lblId.getX() + 200, lblId.getY(), greenLabel);
		lblPreciseContent.setSize(150, 30);
		lblPreciseContent.setBackground(backColor);
		add(lblPreciseContent);

		tfPreciseContent = createJTextField(fTf, 200, lblPreciseContent.getX(),
				lblPreciseContent.getY() + lblPreciseContent.getHeight() + 10, tfColor);
		tfPreciseContent
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfPreciseContent.setBackground(backColor);
		tfPreciseContent.setSize(docIDTextField.getWidth(), 24);
		add(tfPreciseContent);

		lblNOPages = createLabel("Pages", f1, tfPreciseContent.getX(),
				tfPreciseContent.getY() + tfPreciseContent.getHeight() + 10, greenLabel);
		lblNOPages.setSize(150, 30);
		lblNOPages.setBackground(backColor);
		add(lblNOPages);

		tfNOPages = createJTextField(fTf, 200, lblNOPages.getX(), lblNOPages.getY() + lblNOPages.getHeight() + 10,
				tfColor);
		tfNOPages.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfNOPages.setBackground(backColor);
		tfNOPages.setSize(docIDTextField.getWidth(), 24);
		add(tfNOPages);

		lblHCPrices = createLabel("Cover Prices", f1, tfNOPages.getX(), tfNOPages.getY() + tfNOPages.getHeight() + 10,
				greenLabel);
		lblHCPrices.setSize(150, 30);
		lblHCPrices.setBackground(backColor);
		add(lblHCPrices);

		tfCPrices = createJTextField(fTf, 200, lblHCPrices.getX(), lblHCPrices.getY() + lblHCPrices.getHeight() + 10,
				tfColor);
		tfCPrices.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfCPrices.setBackground(backColor);
		tfCPrices.setSize(docIDTextField.getWidth(), 24);
		add(tfCPrices);

		lblLocID = createLabel("Location's ID (*)", f1, tfCPrices.getX(), tfCPrices.getY() + tfCPrices.getHeight() + 10,
				greenLabel);
		lblLocID.setSize(150, 30);
		lblLocID.setBackground(backColor);
		add(lblLocID);

		tfLocID = createJTextField(fTf, 200, lblLocID.getX(), lblLocID.getY() + lblLocID.getHeight() + 10, tfColor);
		tfLocID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 51)));
		tfLocID.setBackground(backColor);
		tfLocID.setSize(docIDTextField.getWidth(), 24);
		add(tfLocID);

		lblIssueDate = createLabel("Issue Date", f1, tfLocID.getX(), tfLocID.getY() + tfLocID.getHeight() + 10,
				greenLabel);
		lblIssueDate.setSize(150, 30);
		lblIssueDate.setBackground(backColor);
		add(lblIssueDate);

		tfIssueDate = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MONTH_FIELD));
		tfIssueDate.setValue(new java.util.Date());
		tfIssueDate.setFont(f1);
		tfIssueDate.setBorder(new MatteBorder(new Insets(0, 0, 2, 0), new Color(255, 255, 141)));
		tfIssueDate.setBackground(new Color(42, 46, 55));
		tfIssueDate.setForeground(new Color(255, 255, 255));
		tfIssueDate.setSize(docIDTextField.getWidth(), 30);
		tfIssueDate.setLocation(lblIssueDate.getX(), lblIssueDate.getY() + lblIssueDate.getHeight() + 2);
		add(tfIssueDate);

		CalendarWindow cw = new CalendarWindow();
		cw.setUndecorated(true);
		cw.addPropertyChangeListener(this);
		tfIssueDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cw.setLocation(430, 605);
				cw.setVisible(true);
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
		btnAdd.setkBorderRadius(25);
		btnAdd.setSize(110, 36);
		btnAdd.setLocation(428, 270);
		btnAdd.setFont(f2);
		btnAdd.setkBorderRadius(25);
		add(btnAdd);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// check valid
				List<Document> documents = documentService.getAllDocument();
				for (int i = 0; i < documents.size(); i++) {
					Document doc = documents.get(i);
					// System.out.println(au.getMatg());
					if (docIDTextField.getText().trim().equals(doc.getMatl().trim())
							&& docNameTextField.getText().trim().equals(doc.getTentl().trim())
							|| docIDTextField.getText().equals("") || docNameTextField.getText().equals("")
							|| subIDTextField.getText().equals("") || tfSuppID.getText().equals("")
							|| tfAuthID.getText().equals("")) {
						JOptionPane.showMessageDialog(DocumentManagePanel.this, "Invalid input document ID.");
						return;
					}
				}
				List<Subject> subjects = documentService.getAllSubject();
				for (int i = 0; i < subjects.size(); i++) {
					Subject subject = subjects.get(i);
					// System.out.println(au.getMatg());
					if (subIDTextField.getText().trim() != subject.getMatheloai().trim()) {
						JOptionPane.showMessageDialog(DocumentManagePanel.this,
								"The category code you have enter does not exist, Attention");
						return;
					}
				}
				List<Supplier> supList = documentService.getAllSupplier();
				for (int i = 0; i < supList.size(); i++) {
					Supplier supplier = supList.get(i);
					// System.out.println(au.getMatg());
					if (tfSuppID.getText().trim() != supplier.getMancc().trim()) {
						JOptionPane.showMessageDialog(DocumentManagePanel.this,
								"The provider code you have enter does not exist, Attention");
						return;
					}
				}
				List<Author> authors = documentService.getAllAuthor();
				for (int i = 0; i < authors.size(); i++) {
					Author author = authors.get(i);
					// System.out.println(au.getMatg());
					if (tfAuthID.getText().trim() != author.getMatg().trim()) {
						JOptionPane.showMessageDialog(DocumentManagePanel.this,
								"The author code you just enter does not exist, Attention");
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
					document.setNgcapnhat(dateA);

				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("OK");
				}

				document.setMatl(docIDTextField.getText().toString().trim());
				document.setTentl(docNameTextField.getText().toString().trim());
				document.setMatheloai(subIDTextField.getText().toString().trim());
				document.setManxb(tfSuppID.getText().toString().trim());
				document.setMatg(tfAuthID.getText().toString().trim());
				document.setNamxb(tfIssueYear.getText().toString().trim());
				document.setNoidung(tfPreciseContent.getText().toString().trim());
				document.setSotrang(tfNOPages.getText().toString().trim());
				document.setGiabia(tfCPrices.getText().toString().trim());
				document.setMavitri(tfLocID.getText().toString().trim());

				documentService.addDocument(document);

				defaultTableModel.setRowCount(0);
				setTableData(documentService.getAllDocument());
				JOptionPane.showMessageDialog(DocumentManagePanel.this, "Add documemt successfully!  ");

				// reset tf
				docIDTextField.setText("");
				docNameTextField.setText("");
				subIDTextField.setText("");
				tfSuppID.setText("");
				tfAuthID.setText("");
				tfIssueYear.setText("");
				tfPreciseContent.setText("");
				tfNOPages.setText("");
				tfCPrices.setText("");
				tfLocID.setText("");
				tfIssueDate.setText("");
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
				List<Document> documents = documentService.getAllDocument();
				for (int i = 0; i < documents.size(); i++) {
					Document doc = documents.get(i);
					// System.out.println(au.getMatg());
					if (docIDTextField.getText().equals("") || docNameTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(DocumentManagePanel.this, "Invalid input document ID.");
						return;
					}
				}

				// handle date format
				DateFormatParse dfp = new DateFormatParse();

				try {

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String dateFormatAdd = dfp.parseDate(tfIssueDate.getText().toString());
					java.util.Date parsedA = format.parse(dateFormatAdd);
					java.sql.Date dateA = new java.sql.Date(parsedA.getTime());
					document.setNgcapnhat(dateA);

				} catch (Exception e2) {
					e2.printStackTrace();
				}

				document.setMatl(docIDTextField.getText().toString().trim());
				document.setTentl(docNameTextField.getText().toString().trim());
				document.setMatheloai(subIDTextField.getText().toString().trim());
				document.setManxb(tfSuppID.getText().toString().trim());
				document.setMatg(tfAuthID.getText().toString().trim());
				document.setNamxb(tfIssueYear.getText().toString().trim());
				document.setNoidung(tfPreciseContent.getText().toString().trim());
				document.setSotrang(tfNOPages.getText().toString().trim());
				document.setGiabia(tfCPrices.getText().toString().trim());
				document.setMavitri(tfLocID.getText().toString().trim());

				documentService.updateDocument(document);

				defaultTableModel.setRowCount(0);
				setTableData(documentService.getAllDocument());
				JOptionPane.showMessageDialog(DocumentManagePanel.this, "Update document successfully!  ");
				// reset tf
				docIDTextField.setText("");
				docNameTextField.setText("");
				subIDTextField.setText("");
				tfSuppID.setText("");
				tfAuthID.setText("");
				tfIssueYear.setText("");
				tfPreciseContent.setText("");
				tfNOPages.setText("");
				tfCPrices.setText("");
				tfLocID.setText("");
				tfIssueDate.setText("");
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
					JOptionPane.showMessageDialog(DocumentManagePanel.this, "Please select an ID first", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String documentName = (String) jTable2.getValueAt(row, 1);
					int confirm = JOptionPane.showConfirmDialog(DocumentManagePanel.this,
							"Are you sure you want to delete Document " + documentName.trim() + "?", "Alert",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						String documentID = (String) jTable2.getValueAt(row, 0);
						try {

							List<DetaileDoc> detaileDocs = documentService.getAllDetaileDoc();
							for (int i = 0; i < detaileDocs.size(); i++) {
								DetaileDoc detaileDoc = detaileDocs.get(i);
								if (documentID.toString().trim().equals(detaileDoc.getMatl().toString().trim())) {
									JOptionPane.showMessageDialog(DocumentManagePanel.this,
											" Attention please !You need to delete " + documentID.toString().trim()
													+ " in the DetaileDoc table first.");

								}
								return;
							}
							List<Subject> subjects = documentService.getAllSubject();
							for (int i = 0; i < subjects.size(); i++) {
								String subID = (String) jTable2.getValueAt(row, 2);
								Subject subject = subjects.get(i);
								// System.out.println(au.getMatg());
								if (subID.toString().trim().equals(subject.getMatheloai().toString().trim())) {
									JOptionPane.showMessageDialog(DocumentManagePanel.this,
											"Attention please !You need to delete " + subID.toString().trim() + " in the Category table first, Attention");
								}
							}
							List<Supplier> suppliers = documentService.getAllSupplier();
							for (int i = 0; i < suppliers.size(); i++) {
								String supID = (String) jTable2.getValueAt(row, 2);
								Supplier superID = suppliers.get(i);
								// System.out.println(au.getMatg());
								if (supID.toString().trim().equals(superID.getMancc().toString().trim())) {
									JOptionPane.showMessageDialog(DocumentManagePanel.this,
											"Attention please !You need to delete " + supID.toString().trim() + " in the Category table first, Attention");
								}
							}
							List<Author> authors = documentService.getAllAuthor();
							for (int i = 0; i < authors.size(); i++) {
								String authID = (String) jTable2.getValueAt(row, 2);
								Author authorID = authors.get(i);
								// System.out.println(au.getMatg());
								if (authID.toString().trim().equals(authorID.getMatg().toString().trim())) {
									JOptionPane.showMessageDialog(DocumentManagePanel.this,
											"Attention please !You need to delete " + authID.toString().trim() + " in the Category table first, Attention");
								}
							}

							documentService.deleteDocument(documentID);
							defaultTableModel.setRowCount(0);
							setTableData(documentService.getAllDocument());

						} catch (Exception e) {
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
				setTableData(documentService.getAllDocument());
				docIDTextField.setEditable(true);
				docNameTextField.setEditable(true);
				subIDTextField.setEditable(true);
				tfSuppID.setEditable(true);
				tfAuthID.setEditable(true);
				docIDTextField.setText("");
				docNameTextField.setText("");
				subIDTextField.setText("");
				tfSuppID.setText("");
				tfAuthID.setText("");
				tfIssueYear.setText("");
				tfPreciseContent.setText("");
				tfNOPages.setText("");
				tfCPrices.setText("");
				tfLocID.setText("");
				tfIssueDate.setText("");
			}
		});

		jTable2.setBackground(new java.awt.Color(60, 63, 65));
		jTable2.setForeground(new java.awt.Color(204, 255, 102));
		jTable2.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "Doc ID", "Name", "Sub ID", "Supp ID", "Auth ID", "Iss Year", "Pre Con", "Pages",
						"CPrices", "Loc ID", "Iss Date" }));
		jTable2.setFillsViewportHeight(true);
		setModelTable2();
		jTable2.setGridColor(new java.awt.Color(51, 51, 255));
		jTable2.setInheritsPopupMenu(true);
		jScrollPane1.setViewportView(jTable2);
		jScrollPane1.setSize(600, 415);
		jScrollPane1.setLocation(568, lblId.getY());
		setTableData(documentService.getAllDocument());
		add(jScrollPane1);

		Font fT = new Font("Tahoma", 0, 14);
		jTable2.setFont(fT);

		jTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = jTable2.getSelectedRow();
					// String authorName = (String) jTable2.getValueAt(row, 1);
					String docString = (String) jTable2.getValueAt(row, 0);
					document = documentService.getDocumentById(docString);
					try {
						docIDTextField.setText(document.getMatl().trim());
						docNameTextField.setText(document.getTentl().trim());
						subIDTextField.setText(document.getMatheloai().trim());
						tfSuppID.setText(document.getManxb().trim());
						tfAuthID.setText(document.getMatg().trim());
						tfIssueYear.setText(document.getNamxb().trim());
						tfPreciseContent.setText(document.getNoidung().trim());
						tfNOPages.setText(document.getSotrang().trim());
						tfCPrices.setText(document.getGiabia().trim());
						tfLocID.setText(document.getMavitri().trim());
						tfIssueDate.setValue(document.getNgcapnhat());
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
				docIDTextField.setEditable(false);
				docNameTextField.setEditable(false);
				subIDTextField.setEditable(false);
				tfSuppID.setEditable(false);
				tfAuthID.setEditable(false);
			}
		});

		docIDTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					docNameTextField.requestFocusInWindow();
				}
			}
		});

		docNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					subIDTextField.requestFocusInWindow();
				}
			}
		});

		subIDTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfSuppID.requestFocusInWindow();
				}
			}
		});

		tfSuppID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfAuthID.requestFocusInWindow();
				}
			}
		});

		tfAuthID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfIssueYear.requestFocusInWindow();
				}
			}
		});

		tfIssueYear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfPreciseContent.requestFocusInWindow();
				}
			}
		});

		tfPreciseContent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfNOPages.requestFocusInWindow();
				}
			}
		});

		tfNOPages.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfCPrices.requestFocusInWindow();
				}
			}
		});

		tfCPrices.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					tfLocID.requestFocusInWindow();
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
			g2d.drawImage(imgLabelGeneral, 0, 45, DocumentManagePanel.this);

			Image searchIcon = ImageIO.read(getClass().getResource("/resources/images/search_ic_32.png"));
			g2d.drawImage(searchIcon, 662, 150, DocumentManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Document Management", 60, 105);

		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_0.png"));
			g2d.drawImage(imgButtonReturn, -20, 650, DocumentManagePanel.this);

			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_0.png"));
			g2d.drawImage(imgButtonTurnOff, 1062, 650, DocumentManagePanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doClick(String name) {
		if (name.equals(BTN_RETURN)) {
			repaint();
			DocumentManagePanel.this.setVisible(false);
			MainPanel mp = (MainPanel) DocumentManagePanel.this.getParent();
			mp.showDocumentMenuPanel();
		} else if (name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(DocumentManagePanel.this, "Do you actually want to exit?", "Alert",
					JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("selectedDate")) {
			Calendar cal = (Calendar) evt.getNewValue();
			java.util.Date selDate = cal.getTime();
			tfIssueDate.setValue(selDate);
		}
	}

	private void setTableData(List<Document> documents) {
		for (Document document : documents) {
			defaultTableModel.addRow(new Object[] { document.getMatl(), document.getTentl(), document.getMatheloai(),
					document.getManxb(), document.getMatg(), document.getNamxb(), document.getNoidung(),
					document.getSotrang(), document.getGiabia(), document.getMavitri(), document.getNgcapnhat() });
		}

	}

	@SuppressWarnings("serial")
	private void setModelTable2() {
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};

		defaultTableModel.addColumn("Doc ID");
		defaultTableModel.addColumn("Doc name");
		defaultTableModel.addColumn("Sub ID");
		defaultTableModel.addColumn("Sup ID");
		defaultTableModel.addColumn("Auth ID");
		defaultTableModel.addColumn("Pub year");
		defaultTableModel.addColumn("Pre Con");
		defaultTableModel.addColumn("NOP");
		defaultTableModel.addColumn("H price");
		defaultTableModel.addColumn("Loc ID");
		defaultTableModel.addColumn("Up Date");

		jTable2.setModel(defaultTableModel);

	}
}
