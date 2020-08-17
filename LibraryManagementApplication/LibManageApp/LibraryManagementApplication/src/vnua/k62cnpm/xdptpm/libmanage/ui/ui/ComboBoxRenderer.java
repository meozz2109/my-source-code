package vnua.k62cnpm.xdptpm.libmanage.ui.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class ComboBoxRenderer extends BasicComboBoxRenderer {
	private JLabel labelItem = new JLabel();

	public ComboBoxRenderer() {
		super();
		setOpaque(false);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 2.0;
		constraints.insets = new Insets(2, 2, 2, 2);
		labelItem.setFont(new Font("Exo 2", 0, 18));
		labelItem.setOpaque(false);
		labelItem.setHorizontalAlignment(JLabel.CENTER);

		add(labelItem, constraints);
//      setBackground(Color.LIGHT_GRAY);
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		setFont(new Font("Exo 2", 0, 18));
		setText(value.toString());
		if (isSelected) {
			labelItem.setBackground(new Color(42, 46, 55));
			labelItem.setForeground(new Color(240, 240, 240));
			setBackground(new Color(45, 46, 45));
			setForeground(new Color(255, 255, 255));
		} else {
			labelItem.setBackground(new Color(42, 46, 55));
			labelItem.setForeground(new Color(160, 160, 160));
			setBackground(new Color(42, 46, 55));
			setForeground(new Color(135, 135, 135));
		}

		return this;
	}
}
