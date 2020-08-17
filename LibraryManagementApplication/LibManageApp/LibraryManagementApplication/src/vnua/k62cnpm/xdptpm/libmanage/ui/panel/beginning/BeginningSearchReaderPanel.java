/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.k62cnpm.xdptpm.libmanage.ui.panel.beginning;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import keeptoo.KButton;
import vnua.k62cnpm.xdptpm.libmanage.frame.RoleConfirmFrame;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.CustomBorder;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundedBorder;

/**
 *
 * @author Minh
 */
public class BeginningSearchReaderPanel extends BasePanel {
	private Thread th1;
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	protected static final Object TEXT_DEFAULT = "Enter the information of document you're searching for";
	private JButton btnReturn, btnTurnOff;
	private JLabel lblIntro, lblManual, lblPolicy;
	private keeptoo.KButton btnSearch;
	private RoundJTextField searchTextField;
	private JFXPanel fxPanel;
	private static ObservableList<String> data;
	private String curSubSearch;
	private ComboBox<String> comboBox;
	
	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);

		UIManager.put("TextField.background", Color.WHITE);
		UIManager.put("TextField.border",
				BorderFactory.createCompoundBorder(new CustomBorder(), new EmptyBorder(new Insets(4, 4, 4, 4))));
	}

	@Override
	public void addEvent() {
	}

	@Override
	public void addComp() {
		addFXPanel();

		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo 2", 0, 18);
		Font f2 = new Font("Blackjack", Font.PLAIN, 40);
		Font f4 = new Font("Blackjack", Font.PLAIN, 45);
		Font f3 = new Font("Exo 2", Font.PLAIN, 40);
		Font f5 = new Font("Blackjack", 0, 20);
		lblIntro = createLabel("Luong Dinh Cua LMS - VNUA", f2, 240, 80, new Color(255, 255, 141));
		lblIntro.setSize(700, lblIntro.getHeight());
		lblIntro.setBackground(new Color(42, 46, 55));
		add(lblIntro);

		searchTextField = new RoundJTextField(20);
		searchTextField.setFont(f1);
		searchTextField.setForeground(new Color(42, 46, 55));
		searchTextField.setSize(460, 40);
		searchTextField.setBackground(Color.white);
		searchTextField.setLocation(260, 210);
		searchTextField.setText("Enter the information of document you're searching for");
		searchTextField.setCaretColor(Color.black);
		searchTextField.setFocusable(false);
		add(searchTextField);

		searchTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTextField.setText("");
				searchTextField.setFocusable(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				searchTextField.setFocusable(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				searchTextField.setFocusable(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				searchTextField.setFocusable(true);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				searchTextField.setFocusable(true);
			}
		});

		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					String text = searchTextField.getText();
					if (text.equals("")) {
						JOptionPane.showMessageDialog(BeginningSearchReaderPanel.this, "Invalid input information!");
						return;
					} else if (text.equals(TEXT_DEFAULT)) {
						JOptionPane.showMessageDialog(BeginningSearchReaderPanel.this,
								"Please click on search field to search.");
						return;
					}
					getCurrentSubSearch();
					BeginningSearchReaderPanel.this.setVisible(false);
					MainBeginningReaderPanel mbrp = (MainBeginningReaderPanel) BeginningSearchReaderPanel.this
							.getParent();
					if(comboBox!=null) {
						mbrp.showSearchContentPanel(text, comboBox.getValue());	
					}
				}
			}
		});

		btnSearch = new KButton();
		btnSearch.setForeground(Color.black);
		btnSearch.setText("Search");
		btnSearch.setBorderPainted(false);
		btnSearch.setkAllowGradient(false);
		btnSearch.setkBackGroundColor(new java.awt.Color(117, 209, 151));
		btnSearch.setkForeGround(new Color(42, 46, 55));
		btnSearch.setkHoverColor(new java.awt.Color(20, 123, 182));
		btnSearch.setkHoverForeGround(new Color(255, 255, 141));
		btnSearch.setSize(125, 38);
		btnSearch.setkBorderRadius(25);
		btnSearch.setLocation(420, 310);
		btnSearch.setFont(f1);
		add(btnSearch);

		btnReturn = createJButton("", f5, 300, -180, 575, new Color(23, 24, 25), Color.white, BTN_RETURN);
		btnReturn.setSize(300, 75);
		add(btnReturn);

		btnTurnOff = createJButton("", f5, 300, 820, 570, new Color(23, 24, 25), Color.white, BTN_TURN_OFF);
		btnTurnOff.setSize(300, 75);
		add(btnTurnOff);

		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = searchTextField.getText();
				if (text.equals("")) {
					JOptionPane.showMessageDialog(BeginningSearchReaderPanel.this, "Invalid input information!");
					return;
				} else if (text.equals(TEXT_DEFAULT)) {
					JOptionPane.showMessageDialog(BeginningSearchReaderPanel.this,
							"Please click on search field to search.");
					return;
				}
				BeginningSearchReaderPanel.this.setVisible(false);
				MainBeginningReaderPanel mbrp = (MainBeginningReaderPanel) BeginningSearchReaderPanel.this.getParent();
				if(comboBox!=null) {
					mbrp.showSearchContentPanel(text, comboBox.getValue());
					if(!comboBox.getValue().equals("Title")) {
						mbrp.updateSCPBool(true);
						System.out.println("OK man.");
					}
				}
			}
		});

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

		addMenuInfor();
		addImageIconToMI();
		addMouseAdapterLabel();
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				data = FXCollections.observableArrayList("Title","Issued Year");
				ComboBox<String> comboBox = new ComboBox<String>(data);
				comboBox.setStyle("-fx-font-size: 16px;-fx-border-radius: 10 10 10 10;");

				comboBox.getSelectionModel().selectFirst(); // select the first element

				VBox vBox = new VBox();
				vBox.setPadding(new javafx.geometry.Insets(40, 40, 40, 40));
				vBox.getChildren().addAll(comboBox);

				StackPane root = new StackPane();
				root.setStyle("-fx-background-color: rgb(42, 46, 55);");
				root.getChildren().add(vBox);
				Scene scene = new Scene(root, 220, 45);
				fxPanel = new JFXPanel();
				fxPanel.setScene(scene);
				fxPanel.setSize(230, 45);
				fxPanel.setLocation(20, 208);
				add(fxPanel);

				comboBox.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
						new EventHandler<javafx.scene.input.MouseEvent>() {
							@Override
							public void handle(javafx.scene.input.MouseEvent e) {
								giveCbb(comboBox);
							}

						});
				
				
			}
		});
	}

	private void giveCbb(ComboBox<String> comboBox) {
		this.comboBox = comboBox;
		System.out.println(comboBox.getValue());
	}
	
	protected void getCurrentSubSearch() {

	}

	private void addFXPanel() {
		th1 = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel(); // initializes JavaFX environment
				Platform.runLater(new Runnable() {
					public void run() {
						data = FXCollections.observableArrayList("Title", "Author", "Subject", "Issued Year");
						comboBox = new ComboBox<String>(data);
						comboBox.setStyle("-fx-font-size: 16px;-fx-border-radius: 10 10 10 10;");

						comboBox.getSelectionModel().selectFirst(); // select the first element

						VBox vBox = new VBox();
						vBox.setPadding(new javafx.geometry.Insets(40, 40, 40, 40));
						vBox.getChildren().addAll(comboBox);

						StackPane root = new StackPane();
						root.setStyle("-fx-background-color: rgb(42, 46, 55);");
						root.getChildren().add(vBox);
						Scene scene = new Scene(root, 220, 45);
						fxPanel = new JFXPanel();
						fxPanel.setScene(scene);
						fxPanel.setSize(230, 45);
						fxPanel.setLocation(20, 208);
						add(fxPanel);

					}
				});
			}
		});
		th1.setDaemon(true);
		th1.start();
	}

	private void addMouseAdapterLabel() {
		lblManual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BeginningSearchReaderPanel.this.setVisible(false);
				MainBeginningReaderPanel mbrp = (MainBeginningReaderPanel) BeginningSearchReaderPanel.this.getParent();
				mbrp.showUserManualReaderPanel();
			}
		});

		lblPolicy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BeginningSearchReaderPanel.this.setVisible(false);
				MainBeginningReaderPanel mbrp = (MainBeginningReaderPanel) BeginningSearchReaderPanel.this.getParent();
				mbrp.showPolicyReaderPanel();
			}
		});
	}

	private void addImageIconToMI() {
		try {
			Image imgManual = ImageIO.read(getClass().getResource("/resources/images/manual.png"));
			lblManual.setIcon(new ImageIcon(imgManual));

			Image imgPolicy = ImageIO.read(getClass().getResource("/resources/images/policy.png"));
			lblPolicy.setIcon(new ImageIcon(imgPolicy));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addMenuInfor() {
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Blackjack", Font.PLAIN, 40);
		lblManual = createLabel("<html>&nbsp;Manual</html>", f1, 305, 435, Color.white);
		lblManual.setSize(150, 150);
		lblManual.setHorizontalTextPosition(JLabel.CENTER);
		lblManual.setVerticalTextPosition(JLabel.BOTTOM);
		lblManual.setBackground(new Color(233, 95, 61));
		lblManual.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblManual);

		lblPolicy = createLabel("<html>&nbsp;&nbsp;&nbsp;Policy</html>", f1, 505, 435, Color.white);
		lblPolicy.setSize(150, 150);
		lblPolicy.setHorizontalTextPosition(JLabel.CENTER);
		lblPolicy.setVerticalTextPosition(JLabel.BOTTOM);
		lblPolicy.setBackground(new Color(135, 63, 187));
		lblPolicy.setBorder(new RoundedBorder(10, new Color(42, 46, 55)));
		add(lblPolicy);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image imgIcon = ImageIO.read(getClass().getResource("/resources/images/icon_48.png"));
			g2d.drawImage(imgIcon, 180, 75, BeginningSearchReaderPanel.this);

			Image searchIcon = ImageIO.read(getClass().getResource("/resources/images/search_ic_32.png"));
			g2d.drawImage(searchIcon, 222, 214, BeginningSearchReaderPanel.this);

			Image imgBg = ImageIO.read(getClass().getResource("/resources/images/role_bg_blur_0.png"));
			g2d.drawImage(imgBg, 255, 405, BeginningSearchReaderPanel.this);

			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_1.png"));
			g2d.drawImage(imgButtonReturn, -20, 570, BeginningSearchReaderPanel.this);

			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_1.png"));
			g2d.drawImage(imgButtonTurnOff, 820, 560, BeginningSearchReaderPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doClick(String name) {
		if (name.equals(BTN_RETURN)) {
			repaint();
			((Window) getRootPane().getParent()).dispose();

			new RoleConfirmFrame().setVisible(true);
		} else if (name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(BeginningSearchReaderPanel.this, "Do you actually want to exit?",
					"Alert", JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}
