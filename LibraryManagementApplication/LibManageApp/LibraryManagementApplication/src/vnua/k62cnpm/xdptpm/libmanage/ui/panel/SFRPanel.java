package vnua.k62cnpm.xdptpm.libmanage.ui.panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class SFRPanel extends BasePanel {
	private JLabel lblTitle, lblDocName, lblAuthor, lblPubYear, lblNOPages, lblLanguage, lblStatus, lblLocation, lblInputDocName,
			lblInputAuthor, lblInputPubYear, lblInputNOPages, lblInputLanguage, lblInputStatus, lblInputLocation;

	public SFRPanel(String docName, String authName, String pubYear, String nop, String language, String status, String location) {
		lblInputDocName.setText(docName);
		lblInputAuthor.setText(authName);
		lblInputPubYear.setText(pubYear);
		lblInputNOPages.setText(nop);
		lblInputLanguage.setText(language);
		lblInputStatus.setText(status);
		lblInputLocation.setText(location);
		init();
		addEvent();
		addComp();
	}
	
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
		Font f1 = new Font("Tahoma", 0, 40);
		Font f2 = new Font("Tahoma", 0, 25);

		lblTitle = createLabel("Detail Document", f1, 270, 40, new Color(23, 231, 161));
		lblTitle.setSize(400, 40);
		
		lblTitle.setBackground(new Color(42, 46, 55));
		add(lblTitle);

		lblDocName = createLabel("Title: ", f2, 80, 90, new Color(139, 204, 196));
		lblDocName.setSize(70, 40);
		lblDocName.setBackground(new Color(42, 46, 55));
		add(lblDocName);

		lblAuthor = createLabel("Author: ", f2, 80, 140, new Color(139, 204, 196));
		lblAuthor.setSize(110, 40);
		lblAuthor.setBackground(new Color(42, 46, 55));
		add(lblAuthor);

		lblPubYear = createLabel("Published Year: ", f2, 80, 190, new Color(139, 204, 196));
		lblPubYear.setSize(190, 40);
		lblPubYear.setBackground(new Color(42, 46, 55));
		add(lblPubYear);

		lblNOPages = createLabel("Number of pages: ", f2, 80, 240, new Color(139, 204, 196));
		lblNOPages.setSize(210, 40);
		lblNOPages.setBackground(new Color(42, 46, 55));
		add(lblNOPages);
		
		lblLanguage = createLabel("Language: ", f2, 80, 290, new Color(139, 204, 196));
		lblLanguage.setSize(140, 40);
		lblLanguage.setBackground(new Color(42, 46, 55));
		add(lblLanguage);
		
		lblStatus = createLabel("Status: ", f2, 80, 340, new Color(139, 204, 196));
		lblStatus.setSize(100, 40);
		lblStatus.setBackground(new Color(42, 46, 55));
		add(lblStatus);
		
		lblLocation = createLabel("Location: ", f2, 80, 390, new Color(139, 204, 196));
		lblLocation.setSize(150, 40);
		lblLocation.setBackground(new Color(42, 46, 55));
		add(lblLocation);
		
		lblInputDocName = createLabel("Doc name", f2, 150, 90, new Color(139, 204, 196));
		lblInputDocName.setSize(400, 40);
		lblInputDocName.setBackground(new Color(42, 46, 55));
		add(lblInputDocName);

		lblInputAuthor = createLabel("Author name", f2, 190, 140, new Color(139, 204, 196));
		lblInputAuthor.setSize(400, 40);
		lblInputAuthor.setBackground(new Color(42, 46, 55));
		add(lblInputAuthor);
 
		lblInputPubYear = createLabel("Doc name", f2, 270, 190, new Color(139, 204, 196));
		lblInputPubYear.setSize(400, 40);
		lblInputPubYear.setBackground(new Color(42, 46, 55));
		add(lblInputPubYear);
 
		lblInputNOPages = createLabel("Doc name", f2, 290, 240, new Color(139, 204, 196));
		lblInputNOPages.setSize(400, 40);
		lblInputNOPages.setBackground(new Color(42, 46, 55));
		add(lblInputNOPages);
 
		lblInputLanguage = createLabel("Doc name", f2, 220, 290, new Color(139, 204, 196));
		lblInputLanguage.setSize(400, 40);
		lblInputLanguage.setBackground(new Color(42, 46, 55));
		add(lblInputLanguage);
 
		lblInputStatus = createLabel("Doc name", f2, 180, 340, new Color(139, 204, 196));
		lblInputStatus.setSize(400, 40);
		lblInputStatus.setBackground(new Color(42, 46, 55));
		add(lblInputStatus);
		
		lblInputLocation = createLabel("Location", f2, 230, 390, new Color(139, 204, 196));
		lblInputLocation.setSize(400, 40);
		lblInputLocation.setBackground(new Color(42, 46, 55));
		add(lblInputLocation);
 
	}
}
