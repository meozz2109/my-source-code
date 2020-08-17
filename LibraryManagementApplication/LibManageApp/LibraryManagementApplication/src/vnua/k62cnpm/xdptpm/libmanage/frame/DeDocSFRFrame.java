package vnua.k62cnpm.xdptpm.libmanage.frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import vnua.k62cnpm.xdptpm.libmanage.ui.panel.BasePanel;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.MainFrame;
import vnua.k62cnpm.xdptpm.libmanage.ui.panel.SFRMPanel;

public class DeDocSFRFrame extends JFrame{
	public static final int HEIGHT_FRAME = 500;
	public static final int WIDTH_FRAME = 800;
	public static final String TITLE = "Luong Dinh Cua Library Management System - Vietnam National University of Agriculture";
	private String docName, authName, pubYear, nop, language, status, location;
	private SFRMPanel sp;
	
	public DeDocSFRFrame(String docName, String authName, String pubYear, String nop, String language, String status, String location) {
		this.docName = docName;
		this.authName = authName;
		this.pubYear = pubYear;
		this.nop = nop;
		this.language = language;
		this.status = status;
		this.location = location;
		
		init();
		addComp();
		addEvent();
	}

	public void init() {
		setTitle(TITLE);
		setSize(WIDTH_FRAME, HEIGHT_FRAME);
		setLocation(300, 100);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.black);
		try {
            Image imgIcon = ImageIO.read(getClass().getResource("/resources/images/icon.png"));
            setIconImage(imgIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(new CardLayout());
	}

	public void addEvent() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int rs = JOptionPane.showConfirmDialog(DeDocSFRFrame.this, "Do you actually want to exit?", "Alert", JOptionPane.YES_NO_OPTION);
				if (rs == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
	}

	public void addComp() {
		sp = new SFRMPanel(docName, authName, pubYear, nop, language, status, location);
		add(sp);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(255, 255, 143));
		g2d.drawRoundRect(50, 60, 700, 400, 25, 25);
		
		g2d.setColor(new Color(73, 77, 74, 90));
		g2d.fillRoundRect(51, 61, 699, 399, 25, 25);
		
	}
	   public static void main(String args[]) {
	        /* Set the Nimbus look and feel */
	        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
	        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
	         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
	         */
	        try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (Exception ex) {
	            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } 
	        /* Create and display the form */
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new DeDocSFRFrame(null, null, null, null, null, null, null).setVisible(true);
	            }
	        });
	    }
}
