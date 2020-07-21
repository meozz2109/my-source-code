package vnua.k62cnpm.xdptpm.libmanage.ui.ui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicCheckBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class MaterialComboBoxUI extends BasicComboBoxUI {
	public static ComponentUI createUI(JComponent c) {
        return new MaterialComboBoxUI();
    }
 
    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
 
        JComboBox<String> comboBox = (JComboBox<String>) c;
        comboBox.setFont(UIManager.getFont("ComboBox.font"));
//        comboBox.setBackground(UIManager.getColor("ComboBox.background"));
//        comboBox.setForeground(UIManager.getColor("ComboBox.foreground"));
//        comboBox.setBorder(UIManager.getBorder("ComboBox.border"));
//        comboBox.setLightWeightPopupEnabled(false);
        comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
 
 
    public void update(Graphics2D g, JComponent c) {
    	g.setColor(c.getBackground());
        g.fillRoundRect(70, 215, c.getWidth(), c.getHeight(),12, 12);
        paint(g, c);
    }
 
    public void paint(Graphics2D g, JComponent c) {
    	Graphics g1 = (Graphics) g;
        super.paint(g1, c);
    }
 
   
}
