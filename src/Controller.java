import java.awt.event.MouseEvent;
import java.util.TimerTask;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
public class Controller implements MouseListener, MouseMotionListener {
	int mouseX, mouseY;
	boolean isPressed;
	MouseEvent mE;
	public Controller(View v) {
		System.out.println("inside controller");
		v.setupDisplay();
		View.panel.addMouseListener(this);
		View.panel.addMouseMotionListener(this);
	}
	
	public Controller() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseDragged(e);
		//View.panel.repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isPressed = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawOval(200,200,30,30);
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		
		Graphics g = View.panel.getGraphics();
		g.setColor(Color.RED);
		isPressed = true;
		g.fillOval(e.getX() - 10, e.getY() - 20,30,30);
		System.out.println("Test");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
}
