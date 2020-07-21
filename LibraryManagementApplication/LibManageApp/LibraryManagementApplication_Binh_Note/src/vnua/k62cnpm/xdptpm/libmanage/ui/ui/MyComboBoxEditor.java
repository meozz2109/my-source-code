package vnua.k62cnpm.xdptpm.libmanage.ui.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class MyComboBoxEditor extends BasicComboBoxEditor{
	private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private Object selectedItem;
     
    public MyComboBoxEditor() {
         
        label.setOpaque(false);
        label.setForeground(new Color(255, 255, 141));
        label.setFont(new Font("Exo 2", 0, 18));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
        label.setAlignmentX(60);
        label.setAlignmentY(150);
        panel.add(label);
        panel.setBackground(new Color(48, 48, 48));
    }
     
    public Component getEditorComponent() {
        return this.panel;
    }
     
    public Object getItem() {
        return  this.selectedItem.toString();
    }
     
    public void setItem(Object item) {
        this.selectedItem = item;
        label.setText(item.toString());
    }
}
