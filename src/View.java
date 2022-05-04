import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.plaf.PanelUI;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.kordamp.ikonli.swing.FontIcon;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;

public class View extends JFrame implements MouseListener {
	private static int imgNum = new File("src//imgs").listFiles().length;
	public static JPanel panel = new JPanel();
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private double width = screenSize.getWidth();
	private double height = screenSize.getHeight();
	private JPanel buttonsPanel = new JPanel();
	private static JLabel [] toolsButtonSetIcons = new JLabel[imgNum];
	private JMenuBar menuBar = new JMenuBar();
	private ImageIcon[] icons = new ImageIcon[imgNum];
	private URL [] iconURLs = new URL[imgNum];
	private JMenu menu = new JMenu("Menu");
	private GridBagConstraints gbc = new GridBagConstraints();
	private static String[] toolNameStrings = {"Eraser","Cutter","Pencil","Color Chooser","Color Picker","Transform","Picture"};
	
	
	
	public View() {	
		
	}
	
	public void setupDisplay() {
		this.setSize(new Dimension((int) width - 20, (int) height - 60 ));
		this.setTitle("Canvas Painter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(panel);
		this.add(buttonsPanel,BorderLayout.WEST);
		this.setJMenuBar(menuBar);
		menuBar.add(menu);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		setupToolButtonSet();
		System.out.printf("JFrame width: %d and height: %d ", this.getWidth(), this.getHeight());
		this.setVisible(true);
	}
	
	private void setupToolButtonSet() {
		buttonsPanel.setPreferredSize(new Dimension(150,100));
		
		System.out.println(imgNum);
		for(int i = 0 ; i < imgNum; i++) {
			//buttonsPanel.add(Box.createRigidArea(new Dimension(5, 35)));
			iconURLs[i] = getClass().getResource(String.format("./imgs/icon%d.png",i));
			System.out.println(String.format("./imgs/icon%d.png",i));
			icons[i] = new ImageIcon(new ImageIcon(iconURLs[i]).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
			toolsButtonSetIcons[i] = new JLabel(icons[i]);
			buttonsPanel.add(toolsButtonSetIcons[i]);

			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	protected static JLabel[] getLabelSet() {
		return toolsButtonSetIcons;
	}
	
	protected static String[] getLabelNameArray() {
		return toolNameStrings;
	}
	
}

