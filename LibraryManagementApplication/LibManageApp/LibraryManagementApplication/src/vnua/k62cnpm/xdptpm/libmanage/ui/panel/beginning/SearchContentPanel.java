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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import keeptoo.KButton;
import net.proteanit.sql.DbUtils;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Author;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.frame.DeDocSFRFrame;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.AuthorDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.DocumentDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.JDBCConnection;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.ReaderDao;
import vnua.k62cnpm.xdptpm.libmanage.logic.AppConst;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.RoundJTextField;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.ToastMessage;

/**
 *
 * @author Minh
 */

public class SearchContentPanel extends BasePanel {
	private int[] statusDoc = new int[] {};
	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private keeptoo.KButton btnSearch;
	private RoundJTextField searchTextField;
	private JFXPanel fxPanel;
	private static ObservableList<Document> data;
	private static ListView<Document> listView;
	private DocumentDao docService;
	private AuthorDao authService;
	private String subSearch;
	private boolean titleOrNot;

	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
	}

	@Override
	public void addEvent() {
	}

	@Override
	public void addComp() {
		docService = new DocumentDao();
		authService = new AuthorDao();

		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo 2", 0, 18);
		Font f5 = new Font("Blackjack", 0, 20);
		Font f2 = new Font("Blackjack", Font.ITALIC, 40);
		Font f4 = new Font("Blackjack", Font.ITALIC, 45);
		Font f3 = new Font("Exo 2", Font.ITALIC, 40);

		searchTextField = new RoundJTextField(20);
		searchTextField.setFont(f1);
		searchTextField.setForeground(new Color(42, 46, 55));
		searchTextField.setSize(460, 40);
		searchTextField.setBackground(Color.white);
		searchTextField.setLocation(125, 15);
		searchTextField.setText("");
		searchTextField.setCaretColor(Color.black);
		searchTextField.setFocusable(true);
		add(searchTextField);

		searchTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

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
		btnSearch.setLocation(623, 15);
		btnSearch.setFont(f1);
		add(btnSearch);

		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ToastMessage tm = new ToastMessage("Searching again ... Wait a second!", 600, 400, 400, 2000);
				tm.showtoast();
				updateListView();
			}
		});
		
		btnReturn = createJButton("", f5, 300, -180, 575, new Color(23, 24, 25), Color.white, BTN_RETURN);
		btnReturn.setSize(300, 75);
		add(btnReturn);

		btnTurnOff = createJButton("", f5, 300, 820, 570, new Color(23, 24, 25), Color.white, BTN_TURN_OFF);
		btnTurnOff.setSize(300, 75);
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

		// FX Panel
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new JFXPanel(); // initializes JavaFX environment
				Platform.runLater(new Runnable() {
					public void run() {
						data = FXCollections.observableArrayList();

//						List<Document> listDoc = docService.getAllDocument();
//						for (Document doc : listDoc) {
//							data.add(doc);
//						}

						updateListView();

						List<Document> listDocRecIdle = docService.getRecentIdleDocs(),
								listDocRecBorrowed = docService.getRecentBorDocs();

						String docID = "", docName = "";
						int iSD = 0;
						for (Document doc : data) {
							docID = doc.getMatl().toString().trim();
							docName = doc.getTentl().toString().trim();
						}

						for (Document docI : listDocRecIdle) {
							if (docID.equals(docI.getMatl().toString().trim())
									&& docName.equals(docI.getTentl().toString().trim())) {
								System.out.println("Trung");
							}
						}

						for (Document docB : listDocRecBorrowed) {
							if (docID.equals(docB.getMatl().toString().trim())
									&& docName.equals(docB.getTentl().toString().trim())) {
								System.out.println("OK done");
							}
						}

						listView = new ListView<Document>(data);
						listView.setCellFactory(new Callback<ListView<Document>, ListCell<Document>>() {

							public ListCell<Document> call(ListView<Document> listView) {
								return new CustomListCell();
							}

						});

						// Focus
						listView.getFocusModel().focus(1);
						listView.getSelectionModel().selectIndices(1);
						listView.getStylesheets().add("/resources/css/list-view-search-content.css");

						StackPane root = new StackPane();
						root.getStylesheets().add("/resources/css/list-view-search-content.css");

						root.getChildren().add(listView);
						Scene scene = new Scene(root, 670, 550);

						scene.getStylesheets().add("/resources/css/list-view-search-content.css");

						fxPanel = new JFXPanel();
						fxPanel.setScene(scene);
						fxPanel.setSize(670, 550);
						fxPanel.setLocation(140, 90);
						add(fxPanel);

						// handle onClick for listView cells
						listView.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

							@Override
							public void handle(javafx.scene.input.MouseEvent event) {
								System.out.println(
										"clicked on " + listView.getSelectionModel().getSelectedItem().getTentl());
							}
						});
					}
				});
			}
		});

		// handle onClick for listView cells
		listView = new ListView<Document>(data);
		listView.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

			@Override
			public void handle(javafx.scene.input.MouseEvent event) {
				System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem().getTentl());
			}
		});
	}

	private void updateListView() {
		// handle search for
		Connection connection = JDBCConnection.getJDBCConnection();
		try {
			ObservableList<Document> obDoc = FXCollections.observableArrayList();
			if(subSearch==null) {
				subSearch = new String("");
				System.out.println("NULL");
			}
			if (subSearch != null) {
				System.out.println(subSearch+" - "+searchTextField.getText().trim());
				String subS = subSearch.equals("Title") || subSearch=="Title" || titleOrNot==false ? "TenTaiLieu" : "TenTaiLieu";
				subS = subSearch.equals("Issued Year") || subSearch=="Issued Year" || titleOrNot==true ? "NamXB" : "TenTaiLieu";
				
				System.out.println(subS+" S");
				String query = "select * from TaiLieu where " + subS + " like ?";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, "%" + searchTextField.getText().trim() + "%");
				ResultSet resultS = pst.executeQuery();
				while (resultS.next()) {
					Document document = new Document();
					document.setMatl(resultS.getString("MaTaiLieu").trim());
					document.setTentl(resultS.getString("TenTaiLieu").trim());
					document.setMatheloai(resultS.getString("MaTheLoai").trim());
					document.setManxb(resultS.getString("MaNXB").trim());
					document.setMatg(resultS.getString("MaTacGia").trim());
					document.setNamxb(resultS.getString("NamXB").trim());
					document.setNoidung(resultS.getString("NoiDung").trim());
					document.setSotrang(resultS.getString("SoTrang").trim());
					document.setGiabia(resultS.getString("GiaBia").trim());
					document.setMavitri(resultS.getString("MaViTri").trim());
					document.setNgcapnhat(resultS.getDate("NgayCapNhat"));

					obDoc.add(document);
				}

				listView.setItems(obDoc);
				for(Document doc: obDoc) {
					System.out.println(doc.getMatl()+" 1");
				}
				
				pst.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image imgIcon = ImageIO.read(getClass().getResource("/resources/images/icon_48.png"));
			g2d.drawImage(imgIcon, 10, 10, SearchContentPanel.this);

			Image searchIcon = ImageIO.read(getClass().getResource("/resources/images/search_ic_32.png"));
			g2d.drawImage(searchIcon, 80, 20, SearchContentPanel.this);

			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_1.png"));
			g2d.drawImage(imgButtonReturn, -20, 570, SearchContentPanel.this);

			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_1.png"));
			g2d.drawImage(imgButtonTurnOff, 820, 560, SearchContentPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doClick(String name) {
		if (name.equals(BTN_RETURN)) {
			repaint();
			SearchContentPanel.this.setVisible(false);
			MainBeginningReaderPanel mbrp = (MainBeginningReaderPanel) SearchContentPanel.this.getParent();
			mbrp.showBeginningSearchReaderPanel();
		} else if (name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(SearchContentPanel.this, "Do you actually want to exit?", "Alert",
					JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	public void sendUpdateSearchTF(String text) {
		if (text != null && !text.equals("")) {
			searchTextField.setText(text);
		}
	}

	private class CustomListCell extends ListCell<Document> {
		private HBox content;
		private Text subName, stt, txtTitleDocName, txtTitleAuthName, txtTitlePubYear, txtGap, txtStatus;
		private ImageView imageViewDoc, imageViewStatus;
		private Button btnDetail, btnReview;
		private HBox hBoxStatus, hBoxButton;
		private VBox vBoxContent, vBoxImageView;
		private FileInputStream fisDoc, fisStatus;
		private String pathImageDoc = AppConst.PATH_IMAGE, pathImageStat = AppConst.PATH_IMAGE;
		private static final String pathDef = AppConst.PATH_IMAGE;
		private int count = pathImageDoc.length(), noteS = 0;
		private String docName, authName, pubYear, nop, language, status;

		public CustomListCell() {

			javafx.scene.text.Font fontOne = new javafx.scene.text.Font("Candara", 20);
			subName = new Text("  ");
			subName.setStyle("-fx-font-size: 20px;" + "-fx-font-family: Candara;" + "-fx-wrap-text: true;"
					+ "-fx-text-alignment: center;" + "-fx-alignment: top-center;" + "-fx-start-margin: 40");
			stt = new Text();
			stt.setStyle("-fx-font-size: 20px;" + "-fx-font-family: Candara;");

			// Create a image object
			fisDoc = null;
			fisStatus = null;
			pathImageDoc += "ic_journal_128.png";
			pathImageStat += "ic_green_stat_32.png";

			try {
				fisDoc = new FileInputStream(pathImageDoc);
				fisStatus = new FileInputStream(pathImageStat);
			} catch (Exception e) {
				e.printStackTrace();
			}

			javafx.scene.image.Image imageDoc = new javafx.scene.image.Image(fisDoc);

			// creating ImageView for adding image
			imageViewDoc = new ImageView();
			imageViewDoc.setImage(imageDoc);
			imageViewDoc.setPreserveRatio(true);
			imageViewDoc.setSmooth(true);

			txtGap = new Text("");
			vBoxImageView = new VBox(txtGap, imageViewDoc, subName);
			vBoxImageView.setSpacing(10);

			txtTitleDocName = new Text("Giáo trình nguyên lý kế toán");
			txtTitleDocName.setStyle("-fx-font-size: 25px;" + "-fx-text-fill: rgb(243, 105, 71);"
					+ "-fx-font-weight: bold;" + "-fx-wrap-text: true;");

			txtTitleAuthName = new Text("Đoàn Quang Thiệu.");
			txtTitleAuthName.setStyle("-fx-font-size: 25px;" + "-fx-wrap-text: true;");

			txtTitlePubYear = new Text("2015" + " - Luong Dinh Cua Library");
			txtTitlePubYear.setStyle("-fx-font-size: 22px;" + "-fx-wrap-text: true;");

			txtStatus = new Text("Idle Document");
			txtStatus.setStyle("-fx-font-size: 20px;" + "-fx-wrap-text: true;");

			javafx.scene.image.Image imageStat = new javafx.scene.image.Image(fisStatus);

			// creating ImageView for adding image
			imageViewStatus = new ImageView();
			imageViewStatus.setImage(imageStat);
			imageViewStatus.setPreserveRatio(true);
			imageViewStatus.setSmooth(true);
			imageViewStatus.setStyle("-fx-min-width: 20px;" + "-fx-min-height: 20px;" + "-fx-pref-width: 25px;"
					+ "-fx-pref-height: 25px;" + "-fx-max-width: 30px;" + "-fx-max-height: 30px;");

			hBoxStatus = new HBox(imageViewStatus, txtStatus);
			hBoxStatus.setSpacing(10);

			// HBox for Buttons
			btnDetail = new Button("Detail");
			btnReview = new Button("Review, vote and comment");
			btnDetail.getStylesheets().add("/resources/css/button-search-for-reader.css");
			btnReview.getStylesheets().add("/resources/css/button-search-for-reader.css");

			btnDetail.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

				@Override
				public void handle(javafx.scene.input.MouseEvent event) {
					createSFRFrame();
				}

			});

			hBoxButton = new HBox(btnDetail, btnReview);
			hBoxButton.setSpacing(10);

			vBoxContent = new VBox(txtTitleDocName, txtTitleAuthName, txtTitlePubYear, hBoxStatus, hBoxButton);
			vBoxContent.setSpacing(10);

			vBoxContent.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
					new EventHandler<javafx.scene.input.MouseEvent>() {

						@Override
						public void handle(javafx.scene.input.MouseEvent event) {
							event.consume();
						}
					});

			content = new HBox(stt, vBoxImageView, vBoxContent);
			content.setSpacing(10);
		}

		protected void createSFRFrame() {

		}

		@Override
		protected void updateItem(Document doc, boolean empty) {
			int sttInt = 0;
			if (doc != null && !empty) {
				String docID = doc.getMatl().toString().trim();
				sttInt = docID.equals("") == false
						? Integer.parseInt(docID.substring(docID.length() - 3, docID.length()))
						: 0;
			}
			String bookSub = "";
			switch (sttInt) {
			case 1:
				bookSub = "Journal";
				noteS = 1;
				break;
			case 2:
			case 4:
			case 5:
				bookSub = "Reference" + "\r\n" + "Book";
				noteS = 2;
				break;
			case 3:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
				bookSub = "Book";
				noteS = 3;
				break;
			default:
				bookSub = "OK";
				break;
			}

			String imgNameDoc = "", imgNameStat = "";

			if (doc != null && !empty) { // <== test for null item and empty parameter
				subName.setText("     " + bookSub);
				stt.setText(String.valueOf(sttInt));
				txtTitleAuthName.setText(doc.getMatg().toString().trim());
				txtTitleDocName.setText(doc.getTentl().toString().trim());
				txtTitlePubYear.setText(doc.getNamxb().toString().trim() + " - Luong Dinh Cua Library");

				if (noteS == 3) {
					imgNameDoc = "ic_book_128_0.png";
				} else if (noteS == 2) {
					imgNameDoc = "ic_ref_book_64.png";
				} else if (noteS == 1) {
					imgNameDoc = "ic_journal_64.png";
				}
				pathImageDoc = pathDef + imgNameDoc;
				pathImageStat = pathDef + imgNameStat;

//				try {
//					fisDoc = new FileInputStream(pathImageDoc);
//					fisStatus = new FileInputStream(pathImageStat);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//				javafx.scene.image.Image imageDoc = new javafx.scene.image.Image(fisDoc);
//
//				// creating ImageView for adding image
//				imageViewDoc = new ImageView();
//				imageViewDoc.setImage(imageDoc);
//				imageViewDoc.setFitWidth(50);
//				imageViewDoc.setPreserveRatio(true);
//				imageViewDoc.setSmooth(true);
//				imageViewDoc.setCache(true);
//				
//				javafx.scene.image.Image imageStat = new javafx.scene.image.Image(fisStatus);
//
//				// creating ImageView for adding image
//				imageViewStatus = new ImageView();
//				imageViewStatus.setImage(imageStat);
//				imageViewStatus.setFitWidth(50);
//				imageViewStatus.setPreserveRatio(true);
//				imageViewStatus.setSmooth(true);
//				imageViewStatus.setCache(true);

				// handle Author name
				List<Author> listAuth = authService.getAuthByDoc();
				for (int i = 0; i < listAuth.size(); i++) {
					if (i == sttInt - 1) {
						String authorName = listAuth.get(i).getTentg().toString().trim();
						txtTitleAuthName.setText(authorName + ".");
						this.authName = authorName;
					}
				}

				this.docName = doc.getTentl().toString().trim();
				this.pubYear = doc.getNamxb().toString().trim();
				this.nop = doc.getSotrang().toString().trim();
				this.language = language;
				this.status = status;

				setGraphic(content);
			} else {
				setGraphic(null);
			}
		}

	}

	public void sendUpdateSubSearch(String subChoosed) {
		this.subSearch = new String(subChoosed);
		updateListView();
	}

	public void updateBoolean(boolean b) {
		titleOrNot=b;
	}

}
