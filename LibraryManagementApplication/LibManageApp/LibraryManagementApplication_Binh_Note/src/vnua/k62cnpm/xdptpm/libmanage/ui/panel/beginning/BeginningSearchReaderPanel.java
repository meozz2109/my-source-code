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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.util.Callback;
import keeptoo.KButton;
import vnua.k62cnpm.xdptpm.libmanage.frame.BeginningReaderFrame;
import vnua.k62cnpm.xdptpm.libmanage.frame.RoleConfirmFrame;
import vnua.k62cnpm.xdptpm.libmanage.test.application.Person;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.ComboBoxRenderer;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.CustomBorder;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.MaterialComboBoxUI;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.MyComboBoxEditor;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundedBorder;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.ToastMessage;

/**
 *
 * @author Minh
 */
public class BeginningSearchReaderPanel extends BasePanel {
	private Thread th;
	private static final String TEXT_DEFAULT = "Enter the information of document you're searching for";
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private static JFXPanel cbbFXPanel;
	private JButton btnReturn, btnTurnOff;
	private MaterialComboBoxUI mcbui;
	private MyComboBoxEditor mcce;
	private ComboBoxRenderer cbbr;
	private JComboBox<String> cbbSearchAttributes;
	private JLabel lblIntro, lblManual, lblPolicy;
	private keeptoo.KButton btnSearch;
	private RoundJTextField searchTextField;
	private ToastMessage tm;
	private Thread th1;

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

		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo 2", 0, 18);
		Font f2 = new Font("Blackjack", Font.PLAIN, 40);
		Font f6 = new Font("Blackjack", Font.PLAIN, 40);
		Font f4 = new Font("Blackjack", Font.PLAIN, 45);
		Font f3 = new Font("Exo 2", Font.PLAIN, 40);
		Font f5 = new Font("Blackjack", 0, 20);
		lblIntro = createLabel("Luong Dinh Cua LMS - VNUA", f2, 240, 80, new Color(255, 255, 141));
		lblIntro.setSize(700, lblIntro.getHeight());
		lblIntro.setBackground(new Color(42, 46, 55));
		add(lblIntro);

		// Combo Box Panel of JFXPanel
		cbbFXPanel = new JFXPanel();
		Scene newScene = createScene();
		System.out.println("OK");

		cbbFXPanel.setScene(newScene);

		System.out.println("OK");
		cbbFXPanel.setSize(130, 42); // 115, 40
		cbbFXPanel.setLocation(60, 210);
		add(cbbFXPanel);

		// Search tf
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
							"Please click on search field to search!");
					return;
				}
				BeginningSearchReaderPanel.this.setVisible(false);
				MainBeginningReaderPanel mbrp = (MainBeginningReaderPanel) BeginningSearchReaderPanel.this.getParent();
				mbrp.showSearchContentPanel(text);
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
		th1 = new Thread(new Runnable() {

			@Override
			public void run() {
				if (th1.isInterrupted()) {
					return;
				}
				try {
					Thread.sleep(500);
					tm = new ToastMessage(
							"[IMPORTANT!!!!] Dear all readers, please check out our policy and read the manual before searching documents !!!!!",
							BeginningReaderFrame.WIDTH_FRAME / 2 - 300, BeginningReaderFrame.HEIGHT_FRAME / 2, 1250,
							6000);
					tm.showtoast();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		th1.setDaemon(true);
		th1.start();
	}

	private void addMouseAdapterLabel() {
		lblManual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				th1.interrupt();
				BeginningSearchReaderPanel.this.setVisible(false);
				MainBeginningReaderPanel mbrp = (MainBeginningReaderPanel) BeginningSearchReaderPanel.this.getParent();
				mbrp.showUserManualReaderPanel();
			}
		});

		lblPolicy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				th1.interrupt();
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
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
//    		mcbui = new MaterialComboBoxUI();
//    		mcbui.update(g2d, cbbSearchAttributes);
//    		g2d.setColor(cbbSearchAttributes.getBackground());
//    		g2d.fillRoundRect(75, 215, cbbSearchAttributes.getWidth(), cbbSearchAttributes.getHeight(),12, 12);
//            BasicComboBoxUI bcbui = new BasicComboBoxUI();
//            bcbui.update(g2d, cbbSearchAttributes);
//            bcbui.paint(g2d, cbbSearchAttributes);
//    		
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
			tm.disposeToast();
			th1.interrupt();
			((Window) getRootPane().getParent()).dispose();
			System.out.println(th1.isInterrupted());
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

	private Scene createScene() {
		ComboBox<String> comboBox = new ComboBox<String>(
				FXCollections.observableArrayList("Title", "Author name", "Subject", "Issued Year"));
		comboBox.getSelectionModel().select(0);
		comboBox.setStyle("-fx-font: 19px \"Candara\"; ");
		comboBox.setPrefSize(130, 42);

		// handle click on item and change color for each item
		comboBox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String string_before, String string_after) {
				System.out.println(string_before);
			}

		});

		comboBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

			@Override
			public ListCell<String> call(ListView<String> arg0) {
				ListCell<String> cell = new ListCell<String>() {

					@Override
					public void updateItem(String string, boolean empty) {
						super.updateItem(string, empty);
						if (string != null) {
							setText(string);
							if (string.equals("Author name")) {
								setTextFill(javafx.scene.paint.Color.BLUEVIOLET);
							} else if (string.equals("Title")) {
								setTextFill(javafx.scene.paint.Color.BROWN);
							} else if (string.equals("Subject")) {
								setTextFill(javafx.scene.paint.Color.BLACK);
							} else if (string.equals("Issued Year")) {
								setTextFill(javafx.scene.paint.Color.CADETBLUE);
							}
						} else {
							setText(null);
						}
					}

				};
				return cell;
			}
		});

		VBox root = new VBox();
//		root.setStyle("-fx-background-color: rgb(\" + 42 + \",\" + 46 + \", \" + 55 + \");");
		root.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.rgb(42, 46, 55),
				CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
		root.getChildren().add(comboBox);
		Scene scene = new Scene(root, 300, 600);
		return scene;
	}

}
