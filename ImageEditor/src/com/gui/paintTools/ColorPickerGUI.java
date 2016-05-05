package com.gui.paintTools;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.gui.Frame;
import com.gui.GuiHandler;
import com.gui.MenuBar;
import com.imageHandler.ImageHandler;
import com.toolkit.SnapToGrid;

public class ColorPickerGUI {
	int mouseX;
	int mouseY;

	JLabel colorPickerWheel, currentColor;
	Frame frame;
	public static JDialog colorPickerGui;
	BufferedImage colorWheelImage;
	JPanel panel;
	public static Color currentColorC;
	
	public ColorPickerGUI(int width, int height, GuiHandler guiHandler, Frame frame) {
		this.frame = frame;
		createGui(width, height);
	}
	
	public void createGui(int width, int height) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		panel = new JPanel(null);
		colorPickerGui = new JDialog(colorPickerGui, "Color Picker");
		colorPickerGui.setAlwaysOnTop (true);
		colorPickerGui.setSize(width, height);
		colorPickerGui.setIconImage(null);
		colorPickerGui.setResizable(false);
		colorPickerGui.setLocationRelativeTo(Frame.guiWindow);
		colorPickerGui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		colorPickerGui.add(panel);
		colorWheelImage = loadWheel();
		handleWheel();
		colorPickerGui.setVisible(true);
		colorPickerGui.setFocusableWindowState(false);
		colorPickerGui.setLocation(0, 400);
		colorPickerGui.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				colorPickerGui.setVisible(false);
				MenuBar.showColorWheel.setBorder(BorderFactory.createRaisedBevelBorder());
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		colorPickerGui.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				SnapToGrid.lockedLeft = false;
				SnapToGrid.lockedRight = false;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}	
		});
	}
	
	private BufferedImage loadWheel() {
		BufferedImage image = null;
		File file = null;
		URL url = null;
		ImageHandler imageHandler = new ImageHandler();
		url = (getClass().getResource("colorWheel.png"));
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if(file != null) {
			image = imageHandler.loadImage(null , file, ImageHandler.LOAD_IMAGE_FROM_PATH);
		}
		
		Image tmp = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    image = dimg;
	    dimg = null;
	    return image;
	}
	
	private void handleWheel() {
		colorPickerWheel = new JLabel(new ImageIcon(colorWheelImage));
		colorPickerWheel.setSize(200, 200);
		colorPickerWheel.setLocation(0, 0);
		colorPickerWheel.setVisible(true);
		currentColorC = new Color(0, 0, 0);
		
		panel.add(colorPickerWheel);
		panel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				currentColorC = getColor();
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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private Color getColor() {
		Color color =  new Color(colorWheelImage.getRGB(mouseX, mouseY));
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		panel.setBackground(color);
		System.out.println("Red " + red + " Green " + green + " Blue " + blue);
		return color;
	}
	
	
}
