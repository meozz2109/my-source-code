package vnua.k62cnpm.xdptpm.libmanage.ui.panel;

import java.awt.CardLayout;
import java.awt.Color;

import vnua.k62cnpm.xdptpm.libmanage.panel.statisticreport.StatisticReportBorrowedDocPanel;
import vnua.k62cnpm.xdptpm.libmanage.panel.statisticreport.StatisticReportMostBrDocPanel;
import vnua.k62cnpm.xdptpm.libmanage.panel.statisticreport.StatisticReportOverdueReaderPanel;
import vnua.k62cnpm.xdptpm.libmanage.panel.statisticreport.StatisticReportReIdleDocPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage.COSManagePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage.SOSManagePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.menu.DocumentMenuPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.menu.SearchMenuPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.menu.SheetMenuPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.menu.StatisticReportMenuPanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.menu.UserMenuPanel;

public class MainLibrarianPanel extends BasePanel{
	public static final String NAME = "LIB";
	private MenuPanel mp;
	private SearchMenuPanel sp;
	private ReaderPanel rp;
	private DocumentMenuPanel dp;
	private SheetMenuPanel shp;
	private StatisticReportMenuPanel srp;
	private UserMenuPanel up;
	private COSManagePanel cosmp;
	private SOSManagePanel sosmp;
	private SearchDocumentPanel sdp;
	private SearchDetailedDocPanel sddp;
	private SearchCheckOutSheetPanel scosp;
	private SearchSignOutSheetPanel ssosp;
	private SearchReaderPanel serp;
	private StatisticReportReIdleDocPanel srridp;
	private StatisticReportBorrowedDocPanel srbdp;
	private StatisticReportMostBrDocPanel srmbdp;
	private StatisticReportOverdueReaderPanel srorp;
	
	@Override
	public void init() {
		setBackground(Color.blue);
		setLayout(new CardLayout());
		setName(NAME);
	}

	@Override
	public void addEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComp() {
		rp = new ReaderPanel();
		add(rp);
		
		mp = new MenuPanel();
		add(mp);
		
		sp = new SearchMenuPanel();
		add(sp);
		
		shp = new SheetMenuPanel();
		add(shp);
		
		srp = new StatisticReportMenuPanel();
		add(srp);
		
		cosmp = new COSManagePanel();
		add(cosmp);
		
		sosmp = new SOSManagePanel();
		add(sosmp);
		
		sdp = new SearchDocumentPanel();
		add(sdp);
		
		sddp = new SearchDetailedDocPanel();
		add(sddp);
		
		scosp = new SearchCheckOutSheetPanel();
		add(scosp);
		
		ssosp = new SearchSignOutSheetPanel();
		add(ssosp);
		
		serp = new SearchReaderPanel();
		add(serp);
		
		srridp = new StatisticReportReIdleDocPanel();
		add(srridp);
		
		srbdp = new StatisticReportBorrowedDocPanel();
		add(srbdp);
		
		srmbdp = new StatisticReportMostBrDocPanel();
		add(srmbdp);
		
		srorp = new StatisticReportOverdueReaderPanel();
		add(srorp);
	}
	
	public void showSearchPanel(){
		sp.setVisible(true);
	}
	
	public void showReaderPanel(){
		rp.setVisible(true);
	}
	
	public void showSheetPanel(){
		shp.setVisible(true);
	}
	
	public void showStatisticReportPanel(){
		srp.setVisible(true);
	}
	
	public void showCheckoutSheetsManagePanel() {
		cosmp.setVisible(true);
	}
	
	public void showSignOutSheetsManagePanel() {
		sosmp.setVisible(true);
	}
	
	public void showSearchSignOutSheetPanel() {
		ssosp.setVisible(true);
	}
	
	public void showSearchDocumentPanel() {
		sdp.setVisible(true);
	}
	
	public void showSearchDetailedDocPanel() {
		sddp.setVisible(true);
	}
	
	public void showSearchCheckOutSheetPanel() {
		scosp.setVisible(true);
	}
	
	public void showSearchReaderPanel() {
		serp.setVisible(true);
	}

	public void showSheetMenuPanel() {
		shp.setVisible(true);
	}

	public void showSearchMenuPanel() {
		sp.setVisible(true);
	}

	public void showStatisticReportRecIdleDocPanel() {
		srridp.setVisible(true);
	}
	
	public void showStatisticReportBorrowedDocPanel() {
		srbdp.setVisible(true);
	}
	
	public void showStatisticReportMostBrDocPanel() {
		srmbdp.setVisible(true);
	}
	
	public void showStatisticReportOverdueReaderPanel() {
		srorp.setVisible(true);
	}

	public void showStatisticReportMenuPanel() {
		srp.setVisible(true);
	}
}
