package com.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import com.gui.paintTools.ColorPickerGUI;
import com.gui.paintTools.PaintTools;
import com.gui.paintTools.RGBTools;
import com.imageHandler.ImageHandler;

public class ImageHandlerAll{
	public static int radius = 5;
	public static BufferedImage currentImage;
	BufferedImage layer1;
	boolean[] layersVisible = {false, false, false, false, false,
								false, false, false, false, false};
	BufferedImage layer2;
	BufferedImage layer3;
	BufferedImage layer4;
	BufferedImage layer5;
	BufferedImage layer6;
	BufferedImage layer7;
	BufferedImage layer8;
	BufferedImage layer9;
	BufferedImage layer10;
	
	BufferedImage colorMask = loadImage("default.png", 2000, 2000);
	public ImageHandler imageHandler;
	BufferedImage colorMaskImage = colorMask;
	public JPanel graphicsPanel;
	boolean isPressed = false, isDrawingShape;
	boolean isRendering;
	int mouseX = 0;
	int mouseY = 0;
	int oldX, oldY;
	Graphics2D g;
	static BufferedImage finalImage;
	public static int COLOR_MASK_OPACITY = 127;
	Color mask = new Color(0, 0, 0, 127);
	int shapeX, shapeY;
	int shapeXNew, shapeYNew;
	int paintTolarence = 1;
	JScrollPane scrollPane;
	int panelWidth, panelHeight;
	int imageWidth, imageHeight;
	Timer timer;
	public ImageHandlerAll() {
		imageHandler = new ImageHandler();	
		timer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setRgb();
				updateFinalImage();
				//updateSize();
			}
		});
	}
	
	private void updateSize() {
		//panelWidth = 1000; //currentImage.getWidth() / (Magnification.mag * 10);
		///panelHeight = 900; // currentImage.getHeight() / (Magnification.mag * 10);
		//if(graphicsPanel != null) {
			//graphicsPanel.setSize(panelWidth, panelHeight);
		//}
		
		System.out.println("df");
		
		imageWidth = (finalImage.getWidth() / 100) * Magnification.mag;
		imageHeight = (finalImage.getHeight() / 100) * Magnification.mag;
		
		
		
		finalImage = scaleImage(finalImage, imageWidth, imageHeight);
		graphicsPanel.setSize(imageWidth, imageHeight);
		
		if(graphicsPanel != null) {
			if(graphicsPanel.getHeight() < 870) {
				graphicsPanel.setLocation(GuiHandler.FRAME_WITDH / 2 - graphicsPanel.getWidth() / 2, GuiHandler.FRAME_HEIGHT / 2 - graphicsPanel.getHeight() / 2);
			} else {
				graphicsPanel.setLocation(GuiHandler.FRAME_WITDH / 2 - graphicsPanel.getWidth() / 2, 100);
			}
			graphicsPanel.setVisible(true);
		}
		
	}
	
	private void updateFinalImage() {
		Graphics2D g = finalImage.createGraphics();
		g.drawImage(currentImage, 0, 0, null);
		g.setColor(mask);
		g.fillRect(0, 0, graphicsPanel.getWidth(), graphicsPanel.getHeight());		
		g.dispose();
	}
	
	public void setRgb() {
		int iAlpha = 0;
	    int iRed = 0;
	    int iGreen = 0;
	    int iBlue = 0;

	    for(int i = 0; i < currentImage.getWidth(); i++) {
	        for(int j = 0; j < currentImage.getHeight(); j++) {
	        	int rgb = colorMask.getRGB(i, j); 
	        	int rgbC = currentImage.getRGB(i, j); 
	        	iAlpha = rgb >> 24 & 0xff;
	        	iRed = rgb >> 16 & RGBTools.redI;
	        	iGreen = rgb >>8 & RGBTools.greenI;
	        	iBlue = rgb >>8 & RGBTools.blueI;
	        	rgb = iAlpha << 24 | iRed<<16 | iGreen<<8 | iBlue;
	        	
	        	mask = new Color(iRed, iGreen, iBlue, RGBTools.alphaI);
	        	
	        	//System.out.println(RGBTools.redI + " " + RGBTools.greenI + " " + RGBTools.blueI);
	        	
	        	//currentImage.setRGB(i, j, rgb);
	        }
	    }

	}
	
	protected void createImage(int width, int height) {
		currentImage = loadImage("default.png", width, height);
		onCreate();
		createPanel(currentImage.getWidth(), currentImage.getHeight());
	}
	
	protected void loadImageFromPath(String path) {
		currentImage = imageHandler.loadImage(path, null, ImageHandler.LOAD_IMAGE_FROM_PATH);
		createPanel(currentImage.getWidth(), currentImage.getHeight());
	}
	
	protected void loadImageFromFile(File file) {
		currentImage = imageHandler.loadImage(null, file, ImageHandler.LOAD_IMAGE_FROM_FILE);
		createPanel(currentImage.getWidth(), currentImage.getHeight());
	}
	
	private void createPanel(int width, int height) {
		finalImage = loadImage("default.png", width, height);
		graphicsPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			 @Override
	            protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(finalImage, 0, 0, null);
					
					
					//g.drawImage(currentImage, 0, 0, null);
					//g.setColor(mask);
					//g.fillRect(0, 0, graphicsPanel.getWidth(), graphicsPanel.getHeight());
					((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            }
		};
		graphicsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		graphicsPanel.setSize(currentImage.getWidth(), currentImage.getHeight());
		if(graphicsPanel.getHeight() < 870) {
			graphicsPanel.setLocation(GuiHandler.FRAME_WITDH / 2 - graphicsPanel.getWidth() / 2, GuiHandler.FRAME_HEIGHT / 2 - graphicsPanel.getHeight() / 2);
		} else {
			graphicsPanel.setLocation(GuiHandler.FRAME_WITDH / 2 - graphicsPanel.getWidth() / 2, 100	);
		}
		
		
		//JScrollPane scrollPane = new JScrollPane(graphicsPanel);
		//scrollPane.setLocation(70, 100);
		//scrollPane.setSize(1800, 800);
		Panel.mainLayeredPanel.add(graphicsPanel, new Integer(2), 0);
		
		graphicsPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				isDrawingShape = true;
				isPressed = true;
				oldX = e.getX();
				oldY = e.getY();
				
				shapeXNew = e.getX();
				shapeYNew = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				isDrawingShape = false;
				isPressed = false;
				shapeX = e.getX();
				shapeY = e.getY();
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

		graphicsPanel.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();	
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();	
				

			}
			
		});

		timer.start();
		
		graphicsPanel.setVisible(true);
	}
	
	protected void deleteImage() {
		
	}
	
	protected void onCreate() {
		
		g = (Graphics2D) currentImage.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, currentImage.getWidth(), currentImage.getWidth());
	}
	
	protected void setCursor() {
		if(graphicsPanel != null) {
			graphicsPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		
	}
	
	protected void updateImage() {
		while(isPressed && !isRendering) {
			
			isRendering = true;
			//currentImage = (BufferedImage) currentImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
			g = (Graphics2D) currentImage.getGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(ColorPickerGUI.currentColorC);
			
			if(PaintTools.toolNum == PaintTools.PAINT_BRUSH) {
				Stroke stroke = new BasicStroke(radius, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0,
				        new float[] { 3, 1 }, 0);
				g.setStroke(stroke);
				g.drawLine(oldX, oldY, mouseX, mouseY);
			}
			
			if(PaintTools.toolNum == PaintTools.PAINT_TOOL) {
				
				isPressed = false;
			}
			
			if(PaintTools.toolNum == PaintTools.PENCIL_TOOL) {
				Stroke stroke = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0,
				        new float[] { 3, 1 }, 0);
				g.setStroke(stroke);
				g.drawLine(oldX, oldY, mouseX, mouseY);
			}
			
			if(PaintTools.toolNum == PaintTools.ERASER_TOOL) {
				g.setColor(Color.WHITE);
				Stroke stroke = new BasicStroke(radius, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0,
				        new float[] { 3, 1 }, 0);
				g.setStroke(stroke);
				if(oldX == 0 || oldY == 0) {
					oldX = mouseX;
					oldY = mouseY;
				} 
				
				g.drawLine(oldX, oldY, mouseX, mouseY);
			}
			
			if(PaintTools.toolNum == PaintTools.RECTANGLE_TOOL) {
				Stroke stroke = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0,
				        new float[] { 3, 1 }, 0);
				g.setStroke(stroke);
				//g.drawLine(oldX, oldY, shapeX, shapeY);
				if(isDrawingShape) {
					g.drawRect(mouseX, mouseY, shapeX, shapeY);	
				}
				isDrawingShape = false;
			}
			
			if(PaintTools.toolNum == PaintTools.PEN_TOOL) {
				Stroke stroke = new BasicStroke(radius, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0,
				        new float[] { 3, 1 }, 0);
				g.setStroke(stroke);
				g.drawLine(oldX, oldY, shapeX, shapeY);
			}
			
			
			
			
			g.dispose();
			
			oldX = mouseX;
			oldY = mouseY;
		}
		isRendering = false;
	}
	
	private BufferedImage loadImage(String name, int prefX, int prefY) {
		BufferedImage image = null;
		File file = null;
		URL url = null;
		url = (getClass().getResource(name));	
		ImageHandler imageHandler = new ImageHandler();
		try {
				file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			if(file == null) {
				System.out.println("FIlE IS NULL");
			}
		}
		if(file != null) {
			image = imageHandler.loadImage(null, file, ImageHandler.LOAD_IMAGE_FROM_FILE);
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
	
	private BufferedImage scaleImage(BufferedImage image, int width, int height) {
		Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    return dimg;
	}
	
	private void paintArea(int mouseX, int mouseY) {
		
		
		
	}
	
	private void checkColorMatch(int mouseX, int mouseY) {
		boolean isRed, isGreen, isBlue;
		Color color = new Color(currentImage.getRGB(mouseX, mouseY));
		isRed = checkRed(color);
		isGreen = checkGreen(color);
		isBlue = checkBlue(color);
		
	}
	
	private boolean checkRed(Color color) {
		boolean isRedMatch = false;
		for(int i = 0; i < paintTolarence; i++) {
			if(ColorPickerGUI.currentColorC.getRed() == color.getRed()) {
				isRedMatch = true;
			}
		}
		return isRedMatch;
	}
	
	private boolean checkGreen(Color color) {
		boolean isGreenMatch = false;
		for(int i = 0; i < paintTolarence; i++) {
			if(ColorPickerGUI.currentColorC.getGreen() == color.getGreen()) {
				isGreenMatch = true;
			}
		}
		return isGreenMatch;
	}
	
	private boolean checkBlue(Color color) {
		boolean isBlueMatch = false;
		for(int i = 0; i < paintTolarence; i++) {
			if(ColorPickerGUI.currentColorC.getBlue() == color.getBlue()) {
				isBlueMatch = true;
			}
		}
		return isBlueMatch;
	}
}

