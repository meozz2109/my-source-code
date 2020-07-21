package vnua.k62cnpm.xdptpm.libmanage.ui.ui;

import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.sun.beans.infos.ComponentBeanInfo;

public class TextAreaBuilder extends ComponentBuilder<TextAreaBuilder, JTextArea>{
	public static final String ROWS = "rows";
    public static final String COLUMNS = "columns";
    
	@Override
	protected TextAreaBuilder self() {
		// TODO Auto-generated method stub
		return this;
	}
	
	public TextAreaBuilder withRows(int rows) {
        put(ROWS, rows);
        return self();
    }

    public TextAreaBuilder withColumns(int cols) {
        put(COLUMNS, cols);
        return self();
    }

    protected int getRows(int defaultValue) {
        return getInt(ROWS, defaultValue);
    }

    protected int getColumns(int defaultValue) {
        return getInt(COLUMNS, defaultValue);
    }
	
	@Override
	public JTextArea build() {
		JTextArea ta = new JTextArea();
        ta.setColumns(getColumns(0));
        ta.setRows(getRows(0));
        ta.setBorder(getBorder());
        ta.setForeground(getForeground());
        ta.setBackground(getBackground());
        return ta;
	}

	public JTextArea newTextArea(int rows, int cols, Border border) {
	    JTextArea ta = new JTextArea(rows, cols);
	    ta.setBorder(border);
	    return ta;
	}
}
