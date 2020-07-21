package vnua.k62cnpm.xdptpm.libmanage.ui.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ComboBoxTest extends JPanel implements ActionListener, Runnable {

	private final JComboBox comboDemo = new JComboBox(new String[] { "A", "B", "C"});
	private final JComboBox comboDemoTwo = new JComboBox();
	private ComboBoxModel[] models = new ComboBoxModel[3];
	
	public ComboBoxTest() {
		models[0] = new DefaultComboBoxModel();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
