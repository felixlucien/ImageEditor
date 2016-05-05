package com.gui.paintTools;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.gui.Frame;
import com.gui.ImageHandlerAll;
import com.gui.MenuBar;
import com.imageHandler.ImageHandler;
import com.toolkit.SnapToGrid;

public class PaintTools {

	public static int toolNum = 0;
	public static JDialog paintTools;
	public JPanel panel;
	JButton paintBrush, paintTool, pencilTool, eraser, penTools, lineTool, rectangleTool, circleTool;
	ImageHandlerAll imageHandler;
	
	public static final int PAINT_BRUSH = 0;
	
	public static final int PAINT_TOOL = 1;
	
	public static final int PENCIL_TOOL = 2;
	
	public static final int ERASER_TOOL = 3;
	
	public static final int PEN_TOOL = 4;
	
	public static final int LINE_TOOL = 5;
	
	public static final int RECTANGLE_TOOL = 6;
	
	public static final int CIRCLE_TOOL = 7;
	
	public PaintTools(ImageHandlerAll imageHandler) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		this.imageHandler = imageHandler;
		panel = new JPanel(null);
		paintTools = new JDialog(paintTools, "Tools");
		paintTools.setLocationRelativeTo(null);
		paintTools.setAlwaysOnTop(true);
		paintTools.setIconImage(null);
		paintTools.setResizable(false);
		paintTools.setLocationRelativeTo(Frame.guiWindow);
		paintTools.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		paintTools.setSize(25, 250);
		paintTools.add(panel);
		paintTools.setFocusableWindowState(false);
		paintTools.setLocation(0, 200);
		
		paintBrush = new JButton(new ImageIcon(loadImage("paintBrushTool.png", 50, 50)));
		paintTool = new JButton(new ImageIcon(loadImage("paintTool.png", 50, 50)));
		pencilTool = new JButton(new ImageIcon(loadImage("pencilTool.png", 50, 50)));
		eraser = new JButton(new ImageIcon(loadImage("eraser.png", 50, 50)));
		penTools = new JButton(new ImageIcon(loadImage("penTool.png", 50, 50)));
		lineTool = new JButton(new ImageIcon(loadImage("LineTools.png", 50, 50)));
		rectangleTool = new JButton(new ImageIcon(loadImage("rectangleTool.png", 50, 50)));
		circleTool = new JButton(new ImageIcon(loadImage("circleTool.png", 50, 50)));
		//rectangleTool = new JButton(new ImageIcon(loadImage("rectangleTool.png", 50, 50)));
		
		paintBrush.setSize(50, 50);
		paintBrush.setLocation(0, 0);
		paintBrush.setBackground(Color.WHITE);
		paintBrush.setFocusPainted(false);
		
		paintTool.setSize(50, 50);
		paintTool.setLocation(55, 0);
		paintTool.setBackground(Color.WHITE);
		paintTool.setFocusPainted(false);
		
		pencilTool.setSize(50, 50);
		pencilTool.setLocation(0, 55);
		pencilTool.setBackground(Color.WHITE);
		pencilTool.setFocusPainted(false);
		
		eraser.setSize(50, 50);
		eraser.setLocation(55, 55);
		eraser.setBackground(Color.WHITE);
		eraser.setFocusPainted(false);
		
		penTools.setSize(50, 50);
		penTools.setLocation(0, 110);
		penTools.setBackground(Color.WHITE);
		penTools.setFocusPainted(false);
		
		lineTool.setSize(50, 50);
		lineTool.setLocation(55, 110);
		lineTool.setBackground(Color.WHITE);
		lineTool.setFocusPainted(false);
		
		rectangleTool.setSize(50, 50);
		rectangleTool.setLocation(0, 165);
		rectangleTool.setBackground(Color.WHITE);
		rectangleTool.setFocusPainted(false);
		
		circleTool.setSize(50, 50);
		circleTool.setLocation(55, 165);
		circleTool.setBackground(Color.WHITE);
		circleTool.setFocusPainted(false);
		
		panel.add(paintBrush);
		panel.add(paintTool);
		panel.add(pencilTool);
		panel.add(eraser);
		panel.add(penTools);
		panel.add(lineTool);
		panel.add(rectangleTool);
		panel.add(circleTool);
		
		paintBrush.setVisible(true);
		paintTool.setVisible(true);
		pencilTool.setVisible(true);
		eraser.setVisible(true);
		
		paintBrush.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toolNum = 0;
			}
		});
		
		paintTool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toolNum = 1;
			}
		});
		
		pencilTool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toolNum = 2;
			}
		});
		
		eraser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toolNum = 3;
			}
		});
		
		penTools.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toolNum = 4;
			}
		});
		
		lineTool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toolNum = 5;
			}
		});
		
		rectangleTool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toolNum = 6;
			}
		});
		
		circleTool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toolNum = 7;
			}
		});
		paintTools.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				paintTools.setVisible(false);
				MenuBar.showPaintTools.setBorder(BorderFactory.createRaisedBevelBorder());
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

		paintTools.addMouseListener(new MouseListener() {

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
		
		paintTools.setVisible(true);
	}
	
	private BufferedImage loadImage(String name, int prefX, int prefY) {
		BufferedImage image = null;
		File file = null;
		URL url = null;
		url = (getClass().getResource(name));
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		if(file != null) {
			//System.out.println(file);
			//System.out.println(url);
			try {
				image = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println(image);
		}
		
		if(file == null || url == null) {
			System.err.println("FILE OR URL = NULL");
		}
		
		Image tmp = image.getScaledInstance(prefX, prefY, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(prefX, prefY, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    image = dimg;
	    dimg = null;
	    tmp = null;
	    return image;
	}
	
}
