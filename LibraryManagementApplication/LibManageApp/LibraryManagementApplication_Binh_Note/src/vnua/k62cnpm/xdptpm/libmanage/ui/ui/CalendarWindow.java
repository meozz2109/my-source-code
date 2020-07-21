package vnua.k62cnpm.xdptpm.libmanage.ui.ui;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.ThemeType;

public class CalendarWindow extends JFrame{
	Calendar calendar = new Calendar();
	private java.util.Calendar selectedDate = java.util.Calendar.getInstance();
	protected PropertyChangeSupport changeSupport;
	
	public CalendarWindow() {
		setSize(255, 240);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		calendar.setTheme(ThemeType.Windows2003);
		calendar.setFont(new Font("Exo 2", 0, 20));
		Container ct = getContentPane();
		ct.setLayout(new BorderLayout());
		ct.add(calendar, BorderLayout.CENTER);
		
		changeSupport = new PropertyChangeSupport(this);
		
		calendar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 || e.getClickCount() == 1) {
					calendar.getSelection().reset();
					
					DateTime pointedDate = calendar.getDateAt(e.getX(), e.getY());
					
					java.util.Calendar cal = java.util.Calendar.getInstance();
					cal.set(pointedDate.getYear(), pointedDate.getMonth() - 1, pointedDate.getDay());
					setSelectedDate(cal);
					
					dispose();
				}
			}
		});
		
	}
	
	public java.util.Calendar getSelectedDate() {
		return selectedDate;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}
	
	public void setSelectedDate(java.util.Calendar newDate){
		java.util.Calendar oldDate = (java.util.Calendar) selectedDate.clone();
		selectedDate = newDate;
		
		changeSupport.firePropertyChange("selectedDate", oldDate, selectedDate);
	}
}
