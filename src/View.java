import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
public class View extends JFrame {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	public static JPanel panel = new JPanel();
	JPanel buttonsPanel = new JPanel();
	
	public View() {	
	}
	
	public void setupDisplay() {
		this.setSize(new Dimension((int) width / 2, (int) height / 2));
		this.setTitle("Canvas Painter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(panel);
		this.add(buttonsPanel,BorderLayout.SOUTH);
		buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.add(new JButton());
		System.out.printf("JFrame width: %d and height: %d ", this.getWidth(), this.getHeight());
	}
	
	
}
