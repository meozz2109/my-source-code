/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.k62cnpm.xdptpm.libmanage.ui.panel.beginning;

import java.awt.CardLayout;
import java.awt.Color;

import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;

/**
 *
 * @author Minh
 */
public class MainBeginningReaderPanel extends BasePanel{

    private BeginningSearchReaderPanel ibp;
    private SearchContentPanel scp;
    private PolicyReaderPanel prp;
    private ManualReaderPanel umrp;
    
    @Override
    public void init() {
        setBackground(Color.red);
        setLayout(new CardLayout());

    }

    @Override
    public void addEvent() {
    }

    @Override
    public void addComp() {
        ibp = new BeginningSearchReaderPanel();
        add(ibp);
        
        scp = new SearchContentPanel();
        add(scp);
        
        prp = new PolicyReaderPanel();
        add(prp);
        
        umrp = new ManualReaderPanel();
        add(umrp);
    }
    
    public void showSearchContentPanel(String textSearch, String subChoosed) {
    	scp.setVisible(true);
    	scp.sendUpdateSearchTF(textSearch);
    	scp.sendUpdateSubSearch(subChoosed);
    }
    
    public void showPolicyReaderPanel() {
    	prp.setVisible(true);
    }
    
    public void showUserManualReaderPanel() {
    	umrp.setVisible(true);
    }
    
    public void showBeginningSearchReaderPanel() {
    	ibp.setVisible(true);
    }

	public void updateSCPBool(boolean b) {
		scp.setVisible(true);
		scp.updateBoolean(b);
	}
    
}
