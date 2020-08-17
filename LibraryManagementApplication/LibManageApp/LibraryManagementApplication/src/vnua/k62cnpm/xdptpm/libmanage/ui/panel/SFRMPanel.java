package vnua.k62cnpm.xdptpm.libmanage.ui.panel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;

public class SFRMPanel extends BasePanel {
	private SFRPanel spn;
	private String docName, authName, pubYear, nop, language, status, location;
	
	public SFRMPanel(String docName, String authName, String pubYear, String nop, String language, String status, String location) {
		this.docName = docName;
		this.authName = authName;
		this.pubYear = pubYear;
		this.nop = nop;
		this.language = language;
		this.status = status;
		this.location = location;
		init();
		addEvent();
		addComp();
	}
	
	@Override
	public void init() {
		setBackground(new Color(142, 146, 155));
		setLayout(new CardLayout());
	}

	@Override
	public void addEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComp() {
		spn = new SFRPanel(docName, authName, pubYear, nop, language, status, location);
		add(spn);
	}	
}