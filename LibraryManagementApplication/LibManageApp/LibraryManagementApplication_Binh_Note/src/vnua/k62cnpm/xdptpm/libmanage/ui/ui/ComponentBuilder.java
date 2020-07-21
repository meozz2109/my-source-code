package vnua.k62cnpm.xdptpm.libmanage.ui.ui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.border.Border;

public abstract class ComponentBuilder<B extends ComponentBuilder<B, T>, T extends JComponent> {
	public static final String BORDER = "border";
	public static final String FOREGROUND = "foreground";
	public static final String BACKGROUND = "background";

	private Map<String, Object> properties = new HashMap<>();

	protected abstract B self();

	protected void put(String key, Object value) {
		properties.put(key, value);
	}

	public B withBorder(Border border) {
		put(BORDER, border);
		return self();
	}

	public B withForeground(Color color) {
		put(FOREGROUND, color);
		return self();
	}

	public B withBackground(Color color) {
		put(BACKGROUND, color);
		return self();
	}

	public abstract T build();

	public <O> O get(String key, Class<O> type, O defaultValue) {
		Object value = properties.get(key);
		if (value == null) {
			return defaultValue;
		} else if (value.getClass().isAssignableFrom(type)) {
			return (O) value;
		}
		return defaultValue;
	}

	protected Border getBorder() {
		return get(BORDER, Border.class, null);
	}

	protected int getInt(String key, int defaultValue) {
		return get(key, int.class, defaultValue);
	}

	protected Color getColor(String key, Color defaultValue) {
		return get(key, Color.class, defaultValue);
	}

	protected Color getForeground() {
		return getColor(FOREGROUND, null);
	}

	protected Color getBackground() {
		return getColor(BACKGROUND, null);
	}

}
