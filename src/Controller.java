import java.awt.event.MouseEvent;
import java.util.TimerTask;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.Color;
import java.awt.event.MouseMotionListener;
public class Controller extends View implements MouseListener, MouseMotionListener {
	int mouseX, mouseY;
	Graphics g;
	JLabel[] buttonLabels = getLabelSet();
	String[] labelNameStrings = getLabelNameArray();
	public Controller(View v) {
		System.out.println("inside controller");
		v.setupDisplay();
		setLabelVisibility(buttonLabels);
		View.panel.addMouseListener(this);
		View.panel.addMouseMotionListener(this);
		for(int i = 0; i < buttonLabels.length; i++)
			buttonLabels[i].addMouseListener(this);
	}
	
	public Controller() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < buttonLabels.length; i++) {
			if(e.getSource() == buttonLabels[i]) {
				System.out.printf("You clicked: %s\n", labelNameStrings[i]);
			}
		}
		//View.panel.repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == View.panel) {
		g = View.panel.getGraphics();
		g.setColor(Color.RED);
		g.fillOval(e.getX() - 15, e.getY() - 20,30,30);
		System.out.println("Test");
		mouseDragged(e);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for(int i = 0; i < buttonLabels.length; i++) {
			buttonLabels[i].setOpaque(true);
			if(e.getSource() == buttonLabels[i]) {
				buttonLabels[i].setBackground(Color.GRAY);
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for(int i = 0; i < buttonLabels.length; i++) {
			if(e.getSource() == buttonLabels[i] && buttonLabels[i].getBackground() == Color.GRAY) 
				buttonLabels[i].setBackground(new JButton().getBackground());
		}
		
	}
	


	@Override
	public void mouseDragged(MouseEvent e) {
		g = View.panel.getGraphics();
		g.setColor(Color.RED);
		g.fillOval(e.getX() - 15, e.getY() - 20,30,30);
		System.out.println("Test");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	
	private void setLabelVisibility(JLabel[] labelArray) {
		for(int i = 0; i < labelArray.length; i++) 
			labelArray[i].setOpaque(true);
	}
}
