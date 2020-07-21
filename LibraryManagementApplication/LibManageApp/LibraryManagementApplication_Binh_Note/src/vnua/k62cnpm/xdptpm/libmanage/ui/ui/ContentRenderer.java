package vnua.k62cnpm.xdptpm.libmanage.ui.ui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import vnua.k62cnpm.xdptpm.libmanage.doc.ContentList;

public class ContentRenderer extends JLabel implements ListCellRenderer<ContentList> {

    public ContentRenderer() {
        setOpaque(false);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ContentList> list, ContentList value, int index,
            boolean isSelected, boolean cellHasFocus) {
        String id = value.getId();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/images/content" + id + ".png"));

        setIcon(imageIcon);
//        setText(value.getName()); 
        return this;
    }

}
