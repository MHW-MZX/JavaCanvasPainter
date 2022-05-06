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
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.PanelUI;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.net.URL;
import java.nio.channels.NonReadableChannelException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.kordamp.ikonli.swing.FontIcon;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;

public class View extends JFrame implements MouseListener{
	private static int imgNum = new File("src//imgs").listFiles().length;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private double width = screenSize.getWidth(), height = screenSize.getHeight();
	public static JPanel panel = new JPanel();
	private JPanel buttonsPanel = new JPanel(), colPre = new JPanel();
	private static JLabel[] toolsButtonSetIcons = new JLabel[imgNum];
	private JMenuBar menuBar = new JMenuBar();
	private ImageIcon[] icons = new ImageIcon[imgNum];
	private URL[] iconURLs = new URL[imgNum];
	private JMenu menu = new JMenu("Menu");
	protected JMenuItem picImport = new JMenuItem("Import Picture");
	private GridBagConstraints gbc = new GridBagConstraints();
	private JSlider redSlider,greenSlider,blueSlider;
	private static String[] toolNameStrings = { "Eraser", "Cutter", "Pencil", "Color Chooser", "Color Picker",
			"Transform", "Picture", "Color Slider" };
	JLabel imgLabel;

	public View() {

	}

	public void setupDisplay() {
		this.setSize(new Dimension((int) width - 20, (int) height - 60));
		this.setTitle("Canvas Painter");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(panel);
		this.add(buttonsPanel, BorderLayout.WEST);
		this.setJMenuBar(menuBar);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu.add(picImport);
		menuBar.add(menu);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		setupToolButtonSet();
		new Controller().setupPicImportMenuAction(picImport, panel,imgLabel);
		System.out.printf("JFrame width: %d and height: %d ", this.getWidth(), this.getHeight());
		this.setVisible(true);
	}

	private void setupToolButtonSet() {
		buttonsPanel.setPreferredSize(new Dimension(150, 100));

		System.out.println(imgNum);
		for (int i = 0; i < imgNum; i++) {
			// buttonsPanel.add(Box.createRigidArea(new Dimension(5, 35)));
			iconURLs[i] = getClass().getResource(String.format("./imgs/icon%d.png", i));
			System.out.println(String.format("./imgs/icon%d.png", i));
			icons[i] = new ImageIcon(
					new ImageIcon(iconURLs[i]).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
			toolsButtonSetIcons[i] = new JLabel(icons[i]);
			toolsButtonSetIcons[i].setToolTipText(toolNameStrings[i]);
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

	protected void displayColorSliderDialogue() {
		JPanel colorDialogPanel = new JPanel(), westColorDialoguePanel = new JPanel(), colorPreviewPanel = new JPanel();
		JButton colorDialogValidateButton = new JButton("Ok");
		JDialog colorDialog = new JDialog(this, "Color Customization");
		JSlider rSlider = new JSlider(0, 255), gSlider = new JSlider(0, 255), bSlider = new JSlider(0, 255);
		colorDialog.setSize(new Dimension(400, 240));
		colorDialog.add(westColorDialoguePanel, BorderLayout.WEST);
		colorDialog.add(colorPreviewPanel, BorderLayout.CENTER);
		colorDialog.add(colorDialogPanel, BorderLayout.SOUTH);
		westColorDialoguePanel.add(Box.createRigidArea(new Dimension(5, 5)));
		westColorDialoguePanel.setLayout(new BoxLayout(westColorDialoguePanel, BoxLayout.Y_AXIS));
		westColorDialoguePanel.add(new JLabel("R"));
		westColorDialoguePanel.add(rSlider);
		westColorDialoguePanel.add(Box.createRigidArea(new Dimension(5, 20)));
		westColorDialoguePanel.add(new JLabel("G"));
		westColorDialoguePanel.add(gSlider);
		westColorDialoguePanel.add(Box.createRigidArea(new Dimension(5, 20)));
		westColorDialoguePanel.add(new JLabel("B"));
		westColorDialoguePanel.add(bSlider);
		colorDialogPanel.add(colorDialogValidateButton);
		colorPreviewPanel.setBackground(new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue()));
		new Controller().setupColorChangeDialogAction(rSlider,gSlider,bSlider,colorDialogValidateButton,colorPreviewPanel);
		colorDialog.setLocationRelativeTo(null);
		colorDialog.setVisible(true);
		colorDialog.setResizable(false);
		colorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	protected JSlider getRSlider() {return redSlider;}
	protected JSlider getGSlider() {return greenSlider;}
	protected JSlider getBSlider() {return blueSlider;}
	protected JPanel getColPrevLab() {return colPre;}
	
}
