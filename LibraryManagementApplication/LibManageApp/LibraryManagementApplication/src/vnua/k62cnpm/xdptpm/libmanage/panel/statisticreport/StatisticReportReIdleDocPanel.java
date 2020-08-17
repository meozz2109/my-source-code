package vnua.k62cnpm.xdptpm.libmanage.panel.statisticreport;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ScrollPane;
import keeptoo.KButton;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.DocumentDao;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainLibrarianPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.ReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.ui.ToastMessage;

public class StatisticReportReIdleDocPanel extends BasePanel {

	private static final String BTN_RETURN = "BTN_RETURN";
	private static final String BTN_TURN_OFF = "BTN_TURN_OFF";
	private JButton btnReturn, btnTurnOff;
	private KButton btnGenerateReport, btnRefresh;
	private JScrollPane scrollPane;
	private JTable tableStatRepIdleDoc;
	private final static ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
	private static PieChart pieChart;
	private JLabel lblPercent;
	private DefaultTableModel defaultTableModel;
	private DaoService documentService;
	private JFileChooser fileChooser;

	@Override
	public void init() {
		setBackground(new Color(42, 46, 55));
		setLayout(null);
	}

	@Override
	public void addEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addComp() {
		double theStatPercent = 25;
		double theLeftoverPercent = 75;

		Font f1 = new Font("Blackjack", 0, 24);
		Font f3 = new Font("Blackjack", 0, 20);
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

		Font f2 = new Font("Montserrat", 0, 22);
		btnGenerateReport = new KButton();
		btnGenerateReport.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 7, 7, 7));
		btnGenerateReport.setText("Generate Report");
		btnGenerateReport.setDoubleBuffered(true);
		btnGenerateReport.setFocusCycleRoot(true);
		btnGenerateReport.setFocusPainted(false);
		btnGenerateReport.setFont(new java.awt.Font("Lucida Sans", 1, 18));
		btnGenerateReport.setkAllowGradient(false);
		btnGenerateReport.setkBackGroundColor(new java.awt.Color(88, 88, 88));
		btnGenerateReport.setkBorderRadius(20);
		btnGenerateReport.setkFillButton(true);
		btnGenerateReport.setkHoverColor(new java.awt.Color(255, 255, 141));
		btnGenerateReport.setkHoverForeGround(new java.awt.Color(0, 0, 0));
		btnGenerateReport.setSize(170, 70);
		btnGenerateReport.setLocation(400, 670);
		add(btnGenerateReport);

		btnRefresh = new KButton();
		btnRefresh.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 7, 7, 7));
		btnRefresh.setText("Refresh");
		btnRefresh.setDoubleBuffered(true);
		btnRefresh.setFocusCycleRoot(true);
		btnRefresh.setFocusPainted(false);
		btnRefresh.setFont(new java.awt.Font("Lucida Sans", 1, 18));
		btnRefresh.setkAllowGradient(false);
		btnRefresh.setkBackGroundColor(new java.awt.Color(88, 88, 88));
		btnRefresh.setkBorderRadius(20);
		btnRefresh.setkFillButton(true);
		btnRefresh.setkHoverColor(new java.awt.Color(255, 255, 141));
		btnRefresh.setkHoverForeGround(new java.awt.Color(0, 0, 0));
		btnRefresh.setSize(170, 70);
		btnRefresh.setLocation(650, 670);
		add(btnRefresh);

		// Table and ScollPane
		tableStatRepIdleDoc = new JTable();
		scrollPane = new JScrollPane();
		documentService = new DaoService();
		tableStatRepIdleDoc.setBackground(new java.awt.Color(60, 63, 65));
		tableStatRepIdleDoc.setForeground(new java.awt.Color(204, 255, 102));
		tableStatRepIdleDoc.setModel(new javax.swing.table.DefaultTableModel(
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
				new String[] { "Doc ID", "Doc name", "Subject ID", "Publisher ID", "Author ID", "Publish year",
						"Pre Content", "Num of Pages", "Page Price", "Location ID", "Upgrade Date" }));

		setModelTable();
		tableStatRepIdleDoc.setFillsViewportHeight(true);
		tableStatRepIdleDoc.setGridColor(new java.awt.Color(51, 51, 255));
		tableStatRepIdleDoc.setInheritsPopupMenu(true);

		Font fT = new Font("Tahoma", 0, 14);
		tableStatRepIdleDoc.setFont(fT);
		
		scrollPane.setViewportView(tableStatRepIdleDoc);
		scrollPane.setSize(650, 450);
		scrollPane.setLocation(50, 170);

		// Set table data and count for pie chart percent as well
		double statPer = setTableData(documentService.getRecIdleDocs());
		double allPer = countAllPercent(documentService.getAllDocument());
		double percent = statPer / allPer * 100;

		add(scrollPane);

		// Pie Chart
		final JFXPanel dataPaneel = new JFXPanel();

		ScrollPane sp = new ScrollPane();

		// set percent before set for pie chart
		theStatPercent = percent;
		theLeftoverPercent = 100 - theStatPercent;
		String stringStatPer = String.format(" %.2f", theStatPercent);
		String stringLeftoverPer = String.format(" %.2f", theLeftoverPercent);

		details.addAll(new PieChart.Data("Recent Borrowed Documents", Double.parseDouble(stringLeftoverPer)),
				new PieChart.Data("Recent Idle Documents", Double.parseDouble(stringStatPer)));
		pieChart = new PieChart();
		pieChart.setData(details);
		pieChart.setTitle("Recent Idle Documents");
		pieChart.setLabelsVisible(false);
		pieChart.setLegendSide(Side.LEFT);

		// set color for pie chart
		int i = 0;
		String[] pieColors = new String[] { "#F36947", "#7585B9", "#EA8041", "#A29279", "#75D197", "#916060", "#70CEC7",
				"#E7DDB6" };
		for (PieChart.Data data : pieChart.getData()) {
			data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
			i++;
		}

		// Set style for pie chart
//		pieChart.setStyle("*{-fx-font-size: 20px;}\r\n" + ".chart-title {\r\n" + "  -fx-text-fill: #47ABA1;\r\n"
//				+ "  -fx-font-size: 1.6em;\r\n" + "}\r\n" + " \r\n" + ".axis-label {\r\n"
//				+ "  -fx-text-fill: #47ABA1;\r\n" + "}\r\n" + " \r\n" + ".chart-legend {\r\n"
//				+ "   -fx-background-color:  transparent;\r\n" + "   -fx-padding: 20px;\r\n" + "}\r\n" + " \r\n"
//				+ ".chart-legend-item-symbol{\r\n" + "   -fx-background-radius: 0;\r\n" + "}\r\n" + " \r\n"
//				+ ".chart-legend-item{\r\n" + "    -fx-text-fill: #47ABA1;\r\n" + "}"
//				+ ".default-color0.chart-pie { -fx-pie-color: #ffd700; }\r\n"
//				+ ".default-color1.chart-pie { -fx-pie-color: #ffa500; }\r\n"
//				+ ".default-color2.chart-pie { -fx-pie-color: #860061; }\r\n"
//				+ ".default-color3.chart-pie { -fx-pie-color: #adff2f; }\r\n"
//				+ ".default-color4.chart-pie { -fx-pie-color: #ff5700; }");

		// Process the mouse event for pie chart

		lblPercent = createLabel("Demo %", f2, 0, 0, new Color(42, 46, 55));
		lblPercent.setVisible(false);
		add(lblPercent);

		for (final PieChart.Data data : pieChart.getData()) {
			data.getNode().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
					new EventHandler<javafx.scene.input.MouseEvent>() {
						@Override
						public void handle(javafx.scene.input.MouseEvent ev) {
							lblPercent.setText(String.valueOf(data.getPieValue()) + "%");

							lblPercent.setLocation((int) ev.getX() + 980, (int) ev.getY() + 395);
							lblPercent.setOpaque(false);
							lblPercent.setVisible(true);
						}
					});
		}

		sp.setFitToWidth(false);
		sp.setFitToHeight(true);
		sp.setContent(pieChart);
		sp.setStyle("-fx-background: rgb(42,46,55);" + "-fx-background-color: transparent;"
				+ "-fx-control-inner-background: transparent;" + "-fx-background-insets: 0;" + "-fx-padding: 0;"
				+ "-fx-text-fill:#47ABA1");

		Scene scene = new Scene(sp, 600, 500);

		scene.getStylesheets().add("/resources/css/pie-chart-rec-idle-doc-colors.css");

		dataPaneel.setScene(scene);
		dataPaneel.setSize(650, 450);
		dataPaneel.setLocation(700, 170);
		add(dataPaneel);
		
		// Handle on click for generate report button
		Random random = new Random();

		btnGenerateReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a folder to save report file (.xlsx)");
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);
				try {
					File path = showOpenDialogForChoosingPath();

					String pathString = path.getAbsolutePath() + "\\file-excel-report-recent-idle-doc-"
							+ String.valueOf((int) (1000 * random.nextDouble())) + ".xlsx";
					File excelFile = new File(pathString);
					if (excelFile.exists() && excelFile.isFile()) {
						System.out.println("File đã tồn tại");
						pathString = path.getAbsolutePath() + "\\file-excel-report-recent-idle-doc-"
								+ String.valueOf((int) (1000 * random.nextDouble())) + ".xlsx";
						excelFile = new File(pathString);
					} else {
						new File(excelFile.getParent()).mkdirs();
						System.out.println(pathString);
						try {
							boolean res = excelFile.createNewFile();
							String ms = res ? "Tạo file thành công" : "Tạo file không thành công";
							System.out.println(ms);
							if (res == false) {
								return;
							}
						} catch (Exception ex) {
							System.out.println("Lỗi: " + ex.getMessage());
						}
					}
					writeExcel(documentService.getRecIdleDocs(), excelFile.getAbsolutePath());
					JOptionPane.showMessageDialog(StatisticReportReIdleDocPanel.this,
							"Generates Recent Idle Documents reports successfully into file .xlsx! You can check it right away.",
							"Message", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshData();
			}
		});
	}

	protected void refreshData() {
		ToastMessage tm = new ToastMessage("Refreshing ... !", 750, 400, 200, 1500);
		tm.showtoast();
		
		setModelTable();
		setTableData(documentService.getRecIdleDocs());
	}

	protected File showOpenDialogForChoosingPath() {

		int userSelection = fileChooser.showSaveDialog(this);
		try {
			File fileToSave = new File("");
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				fileToSave = fileChooser.getSelectedFile();
				System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			}
			return fileToSave;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new File("");
	}

	private int countAllPercent(List<Document> allDocument) {
		return allDocument.size();
	}

	private int setTableData(List<Document> documents) {
		for (Document document : documents) {
			defaultTableModel.addRow(new Object[] { document.getMatl(), document.getTentl(), document.getMatheloai(),
					document.getManxb(), document.getMatg(), document.getNamxb(), document.getNoidung(),
					document.getSotrang(), document.getGiabia(), document.getMavitri(), document.getNgcapnhat() });

		}
		return documents.size();
	}

	private void setModelTable() {
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};

		defaultTableModel.addColumn("Doc ID");
		defaultTableModel.addColumn("Doc name");
		defaultTableModel.addColumn("Subject ID");
		defaultTableModel.addColumn("Publisher ID");
		defaultTableModel.addColumn("Author ID");
		defaultTableModel.addColumn("Publish year");
		defaultTableModel.addColumn("Pre Content");
		defaultTableModel.addColumn("Num of Pages");
		defaultTableModel.addColumn("Page Price");
		defaultTableModel.addColumn("Location ID");
		defaultTableModel.addColumn("Upgrade Date");

		tableStatRepIdleDoc.setModel(defaultTableModel);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image imgLabelGeneral = ImageIO.read(getClass().getResource("/resources/images/label_general_0.png"));
			g2d.drawImage(imgLabelGeneral, 0, 45, StatisticReportReIdleDocPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ReaderPanel().createNewFont();
		Font f1 = new Font("Exo", 1, 55);
		g2d.setFont(f1);
		g2d.setColor(new Color(255, 255, 141));
		g2d.drawString("Statistic Report For Recent Idle Documents", 20, 105);

		try {
			Image imgButtonReturn = ImageIO.read(getClass().getResource("/resources/images/btn_return_0.png"));
			g2d.drawImage(imgButtonReturn, -20, 650, StatisticReportReIdleDocPanel.this);

			Image imgButtonTurnOff = ImageIO.read(getClass().getResource("/resources/images/btn_turn_off_0.png"));
			g2d.drawImage(imgButtonTurnOff, 1062, 650, StatisticReportReIdleDocPanel.this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doClick(String name) {
		if (name.equals(BTN_RETURN)) {
			repaint();
			StatisticReportReIdleDocPanel.this.setVisible(false);
			Component c = (Component) StatisticReportReIdleDocPanel.this.getParent();
			if (c.getName() == "LIB") {
				MainLibrarianPanel mlp = (MainLibrarianPanel) StatisticReportReIdleDocPanel.this.getParent();
				mlp.showStatisticReportMenuPanel();
			} else {
				MainPanel mp = (MainPanel) StatisticReportReIdleDocPanel.this.getParent();
				mp.showStatisticReportMenuPanel();
			}
		} else if (name.equals(BTN_TURN_OFF)) {
			repaint();
			int rs = JOptionPane.showConfirmDialog(StatisticReportReIdleDocPanel.this, "Do you actually want to exit?",
					"Alert", JOptionPane.YES_NO_OPTION);
			if (rs == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	// Excel import and export
	public void writeExcel(List<Document> list, String excelFilePath) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		int rowCount = 7;

		CellStyle cellStyleBold = sheet.getWorkbook().createCellStyle(),
				cellStyleNor = sheet.getWorkbook().createCellStyle(),
				cellStyleLabel = sheet.getWorkbook().createCellStyle(),
				cellStylePreset = sheet.getWorkbook().createCellStyle();
		cellStyleBold.setAlignment(HorizontalAlignment.CENTER);
		cellStyleLabel.setAlignment(HorizontalAlignment.CENTER);
		cellStyleNor.setAlignment(HorizontalAlignment.CENTER);
		cellStylePreset.setAlignment(HorizontalAlignment.LEFT);
		org.apache.poi.ss.usermodel.Font fontB = sheet.getWorkbook().createFont(),
				fontN = sheet.getWorkbook().createFont(), fontS = sheet.getWorkbook().createFont(),
				fontH = sheet.getWorkbook().createFont();
		fontB.setBold(true);
		fontH.setBold(true);
		fontN.setFontName("Candara");
		fontS.setFontName("Candara");
		fontB.setFontName("Candara");
		fontH.setFontName("Candara");
		fontB.setFontHeightInPoints((short) 14);
		fontS.setFontHeightInPoints((short) 10);
		cellStyleBold.setFont(fontB);
		fontN.setFontHeightInPoints((short) 10);
		fontH.setFontHeightInPoints((short) 10);
		cellStyleNor.setFont(fontN);
		cellStylePreset.setFont(fontN);
		cellStyleNor.setWrapText(true);
		cellStylePreset.setWrapText(true);
		cellStyleBold.setWrapText(true);

		cellStyleNor.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		cellStyleNor.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStylePreset.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		cellStylePreset.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyleBold.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		cellStyleBold.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cellStyleNor.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.TOP);
		cellStyleBold.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.TOP);
		cellStyleLabel.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.TOP);
		cellStyleLabel.setFont(fontB);

		createPreSet(sheet, cellStylePreset);
		createReportLabel(sheet, cellStyleLabel);
		createHeaderRow(sheet, cellStyleBold, fontH);
		setBorderAll(sheet);

		for (Document doc : list) {
			Row row = sheet.createRow(++rowCount);
			cellStyleNor = updateBorderCell(cellStyleNor, fontS);
			cellStyleNor.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
			writeBook(doc, row, cellStyleNor);
		}

		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		}
	}

	private void setBorderAll(Sheet sheet) {
		CellRangeAddress region = new CellRangeAddress(1, 1, 1, 4);
		RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
		RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
		RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);

		CellRangeAddress regionA = new CellRangeAddress(1, 1, 9, 12);
		RegionUtil.setBorderBottom(BorderStyle.THIN, regionA, sheet);
		RegionUtil.setBorderTop(BorderStyle.THIN, regionA, sheet);
		RegionUtil.setBorderLeft(BorderStyle.THIN, regionA, sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, regionA, sheet);

		CellRangeAddress regionB = new CellRangeAddress(2, 2, 1, 4);
		RegionUtil.setBorderBottom(BorderStyle.THIN, regionB, sheet);
		RegionUtil.setBorderTop(BorderStyle.THIN, regionB, sheet);
		RegionUtil.setBorderLeft(BorderStyle.THIN, regionB, sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, regionB, sheet);

		CellRangeAddress regionC = new CellRangeAddress(3, 3, 1, 4);
		RegionUtil.setBorderBottom(BorderStyle.THIN, regionC, sheet);
		RegionUtil.setBorderTop(BorderStyle.THIN, regionC, sheet);
		RegionUtil.setBorderLeft(BorderStyle.THIN, regionC, sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, regionC, sheet);

	}

	private CellStyle updateBorderCell(CellStyle cellStyleNor, org.apache.poi.ss.usermodel.Font f) {
		if (f != null) {
			cellStyleNor.setFont(f);
		}

		cellStyleNor.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		cellStyleNor.setFillPattern(FillPatternType.NO_FILL);
		cellStyleNor.setBorderBottom(BorderStyle.THIN);
		cellStyleNor.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleNor.setBorderRight(BorderStyle.THIN);
		cellStyleNor.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleNor.setBorderTop(BorderStyle.THIN);
		cellStyleNor.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleNor.setBorderLeft(BorderStyle.THIN);
		cellStyleNor.setLeftBorderColor(IndexedColors.BLACK.getIndex());

		return cellStyleNor;
	}

	private void createPreSet(Sheet sheet, CellStyle cellStyleNor) {
		Row row = sheet.createRow(1);
		Cell cellStartDate = row.createCell(1);
		cellStartDate.setCellStyle(cellStyleNor);
		cellStartDate.setCellValue("Start Date:");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));

		Row rowA = sheet.createRow(2);
		Cell cellReportWriter = rowA.createCell(1);
		cellReportWriter.setCellStyle(cellStyleNor);
		cellReportWriter.setCellValue("Report Writer:");
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));

		Row rowB = sheet.createRow(3);
		Cell cellApprover = rowB.createCell(1);
		cellApprover.setCellStyle(cellStyleNor);
		cellApprover.setCellValue("Approver:");
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 4));

		Cell cellEndDate = row.createCell(9);
		cellEndDate.setCellStyle(cellStyleNor);
		cellEndDate.setCellValue("End Date:");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 9, 12));
	}

	private void createReportLabel(Sheet sheet, CellStyle cellStyleBold) {
		Row row = sheet.createRow(4);
		Cell cellLabel = row.createCell(6);
		cellLabel.setCellStyle(cellStyleBold);
		cellLabel.setCellValue("RECENT IDLE DOCUMENTS REPORT");
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 6, 9));
	}

	private void createHeaderRow(Sheet sheet, CellStyle cellStyle, org.apache.poi.ss.usermodel.Font f) {
		Row row = sheet.createRow(7);

		cellStyle.setFillBackgroundColor(IndexedColors.BLUE_GREY.getIndex());
		cellStyle.setFont(f);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

		Cell cellId = row.createCell(3);
		cellId.setCellStyle(cellStyle);
		cellId.setCellValue("Doc ID");

		Cell cellDocName = row.createCell(4);
		cellDocName.setCellStyle(cellStyle);
		cellDocName.setCellValue("Doc name");
		sheet.setColumnWidth(4, 25 * 256);

		Cell cellSubID = row.createCell(5);
		cellSubID.setCellStyle(cellStyle);
		cellSubID.setCellValue("Subject ID");

		Cell cellPubID = row.createCell(6);
		cellPubID.setCellStyle(cellStyle);
		cellPubID.setCellValue("Publisher ID");

		Cell cellAuthID = row.createCell(7);
		cellAuthID.setCellStyle(cellStyle);
		cellAuthID.setCellValue("Author ID");

		Cell cellPubDate = row.createCell(8);
		cellPubDate.setCellStyle(cellStyle);
		cellPubDate.setCellValue("Pub Date");

		Cell cellPreCon = row.createCell(9);
		cellPreCon.setCellStyle(cellStyle);
		cellPreCon.setCellValue("Pre Content");
		sheet.setColumnWidth(9, 50 * 256);

		Cell cellNOP = row.createCell(10);
		cellNOP.setCellStyle(cellStyle);
		cellNOP.setCellValue("N O Pages");

		Cell cellCoverPrices = row.createCell(11);
		cellCoverPrices.setCellStyle(cellStyle);
		cellCoverPrices.setCellValue("Cover Prices");

		Cell cellLocID = row.createCell(12);
		cellLocID.setCellStyle(cellStyle);
		cellLocID.setCellValue("Loc ID");

		Cell cellUpgDate = row.createCell(13);
		cellUpgDate.setCellStyle(cellStyle);
		cellUpgDate.setCellValue("Up Date");

	}

	private void writeBook(Document doc, Row row, CellStyle c) {

		Cell cell = row.createCell(3);
		cell.setCellStyle(c);
		cell.setCellValue(doc.getMatl());

		cell = row.createCell(4);
		cell.setCellStyle(c);
		cell.setCellValue(doc.getTentl());

		cell = row.createCell(5);
		cell.setCellStyle(c);
		cell.setCellValue(doc.getMatheloai());

		cell = row.createCell(6);
		cell.setCellStyle(c);
		cell.setCellValue(doc.getManxb());

		cell = row.createCell(7);
		cell.setCellStyle(c);
		cell.setCellValue(doc.getMatg());

		cell = row.createCell(8);
		cell.setCellStyle(c);
		cell.setCellValue(doc.getNamxb());

		cell = row.createCell(9);
		cell.setCellStyle(c);
		cell.setCellValue(doc.getNoidung());

		cell = row.createCell(10);
		cell.setCellStyle(c);
		cell.setCellValue(doc.getSotrang());

		cell = row.createCell(11);
		cell.setCellStyle(c);
		cell.setCellValue(doc.getGiabia());

		cell = row.createCell(12);
		cell.setCellStyle(c);
		cell.setCellValue(doc.getMavitri());

		cell = row.createCell(13);
		cell.setCellStyle(c);
		cell.setCellValue(doc.getNgcapnhat());

	}
}
