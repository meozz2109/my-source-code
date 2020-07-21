package vnua.k62cnpm.xdptpm.libmanage.ui.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Arc2D;

import javax.swing.JComponent;

public class CircleProgressBar extends JComponent{
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D)g;
	    g2.translate(250, 250);
	    g2.rotate(Math.toRadians(330));
	    Arc2D arc = new Arc2D.Float(Arc2D.CHORD);
	    arc.setFrameFromCenter(new Point(250, 250), new Point(400, 400));
	    arc.setAngleStart(1);
	    arc.setAngleExtent(-100*3.6);
	    g2.setColor(Color.red);
	    g2.draw(arc);
	    g2.fill(arc);
	}
}
