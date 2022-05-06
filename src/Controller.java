import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.TimerTask;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 
 * @author Malik
 *
 */
public class Controller extends View implements MouseListener, MouseMotionListener {

	private Graphics g;
	private static Color drawColor;
	private JLabel[] buttonLabels = getLabelSet();
	private String[] labelNameStrings = getLabelNameArray();
	private final int ERASER = 0, CUTTER = 1, PENCIL = 2, COLOR_CHOOSER = 3, COLOR_PICKER = 4, TRANSFORM = 5,
			PICTURE = 6, COLOR_SLIDER = 7;
	private final Color defaultColor = new Color(0, 0, 0);
	private static boolean colorChanged = false;
	private String currentTool = "pencil";
	private JSlider rSlider = super.getRSlider(), gSlider = super.getGSlider(), bSlider = super.getBSlider();
	private JPanel colPreview = super.getColPrevLab();
	private JLabel imgLb;

	public Controller(View v, Model m) {
		System.out.println("inside controller");
		v.setupDisplay();
		setLabelVisibility(buttonLabels);
		View.panel.addMouseListener(this);
		View.panel.addMouseMotionListener(this);

		for (int i = 0; i < buttonLabels.length; i++)
			buttonLabels[i].addMouseListener(this);
	}

	public Controller() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		for (int i = 0; i < buttonLabels.length; i++) {
			if (e.getSource() == buttonLabels[i]) {
				System.out.printf("You clicked: %s\n", labelNameStrings[i]);
			}

			if (e.getSource() == buttonLabels[COLOR_CHOOSER]) {
				// g.setColor();
				drawColor = JColorChooser.showDialog(this, "Select a color", Color.RED);
				colorChanged = true;
				break;
			}

			if (e.getSource() == buttonLabels[ERASER]) {
				currentTool = "eraser";
			}

			if (e.getSource() == buttonLabels[PENCIL]) {
				currentTool = "pencil";
			}

			if (e.getSource() == buttonLabels[COLOR_SLIDER]) {
				super.displayColorSliderDialogue();
				currentTool = "Color Slider";
				break;
			}

		}

		// View.panel.repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == View.panel && currentTool == "pencil") {
			g = View.panel.getGraphics();
			if (colorChanged == false)
				g.setColor(defaultColor);
			else
				g.setColor(drawColor);

			g.fillOval(e.getX() - 15, e.getY() - 20, 30, 30);
			System.out.println("Test");
			mouseDragged(e);

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < buttonLabels.length; i++) {
			buttonLabels[i].setOpaque(true);
			if (e.getSource() == buttonLabels[i]) {
				buttonLabels[i].setBackground(Color.GRAY);
			}
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < buttonLabels.length; i++) {
			if (e.getSource() == buttonLabels[i] && buttonLabels[i].getBackground() == Color.GRAY)
				buttonLabels[i].setBackground(new JButton().getBackground());
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getSource() == View.panel && currentTool == "pencil") {
			g = View.panel.getGraphics();
			if (colorChanged == false)
				g.setColor(defaultColor);
			else
				g.setColor(drawColor);
			g.fillOval(e.getX() - 15, e.getY() - 20, 30, 30);
			System.out.println("Test");
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private void setLabelVisibility(JLabel[] labelArray) {
		for (int i = 0; i < labelArray.length; i++)
			labelArray[i].setOpaque(true);
	}

	public static void setColor(Color c) {
		colorChanged = true;
		drawColor = c;
	}

	public void setupColorChangeDialogAction(JSlider r, JSlider g, JSlider b, JButton confirmBtn, JPanel colPreview) {
		r.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				colPreview.setBackground(new Color(r.getValue(), g.getValue(), b.getValue()));
			}
		});
		g.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				colPreview.setBackground(new Color(r.getValue(), g.getValue(), b.getValue()));
			}
		});
		b.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				colPreview.setBackground(new Color(r.getValue(), g.getValue(), b.getValue()));
			}
		});
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colPreview.setBackground(new Color(r.getValue(), g.getValue(), b.getValue()));
				Controller.setColor(new Color(r.getValue(), g.getValue(), b.getValue()));
				dispose();
			}

		});
	}

	public void setupPicImportMenuAction(JMenuItem picImportButton, JPanel panel, JLabel imgLbl) {
		picImportButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter pngOnly = new FileNameExtensionFilter(".png", "png");
				FileNameExtensionFilter jpegOnly = new FileNameExtensionFilter(".jpg", "jpg");
				jfc.addChoosableFileFilter(pngOnly);
				jfc.addChoosableFileFilter(jpegOnly);

				jfc.setAcceptAllFileFilterUsed(false);
				int response = jfc.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {

					try {
						BufferedImage ii = ImageIO.read(new FileInputStream(jfc.getSelectedFile().getAbsolutePath()));
						addImageToCanvas(panel, ii, imgLbl);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(jfc.getSelectedFile().getAbsolutePath());

				}
			}

		});
	}

	protected void addImageToCanvas(JPanel panel, BufferedImage bi, JLabel label) {
		label = new JLabel(new ImageIcon(bi));
		imgLabel = label;
		panel.add(label);
		panel.revalidate();
		label.setOpaque(true);
	}

	/**
	public void paintComponent(Graphics g, JPanel p) {
		JPanel temp;
		temp = new JPanel(true) {
			private static final long serialVersionUID = 1L;
			@Override
			 public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // do painting
            }

		};
	}
	**/

	private class drag {

	}

	private class drop {

	}
}
