package com.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fileInput.IsParsable;
import com.gui.paintTools.ColorPickerGUI;
import com.gui.paintTools.PaintTools;
import com.gui.paintTools.RGBTools;
import com.imageHandler.ImageHandler;

public class MenuBar {
	JButton file, edit, save, open, newFile;
	public static JButton showColorWheel, showPaintTools, showFilterTools;
	int buttonWidth = 100, buttonHeight = 30;
	int frameWidth = GuiHandler.FRAME_WITDH;
	int frameHeight = GuiHandler.FRAME_HEIGHT;
	NewFile newFile1;
	JPanel menuPanel;
	GuiHandler guiHandler;
	JTextField radius;
	JLabel radiusLabel;
	
	protected void updateRadius() {
		if(IsParsable.isParsable(radius.getText())) {
			ImageHandlerAll.radius = Integer.parseInt(radius.getText());
		}
	}
	
	public MenuBar(int x, int y, GuiHandler guiHandler) {
		this.guiHandler = guiHandler;
		menuPanel = new JPanel(null);
		menuPanel.setBackground(Color.WHITE);
		menuPanel.setSize(GuiHandler.FRAME_WITDH, 70);
		menuPanel.grabFocus();
		
		radiusLabel = new JLabel("Drawing Radius: ");
		radius = new JTextField("radius");
		
		file = new JButton("File");
		edit = new JButton("Edit");
		save = new JButton("Save");
		open = new JButton("Open");
		newFile = new JButton("New");
		
		file.setFocusable(false);
		edit.setFocusable(false);
		save.setFocusable(false);
		open.setFocusable(false);
		newFile.setFocusable(false);
		
		showColorWheel = new JButton(new ImageIcon(loadImage("colorWheel.png", 20, 20)));
		showPaintTools = new JButton(new ImageIcon(loadImage("paintbrushTool.png", 20, 20)));
		showFilterTools = new JButton(new ImageIcon(loadImage("filter.jpg", 20, 20)));
		
		
		showColorWheel.setFocusPainted(false);
		showPaintTools.setFocusPainted(false);
		showPaintTools.setBackground(Color.WHITE);
		showFilterTools.setFocusPainted(false);
		showFilterTools.setFocusPainted(false);
		
		
		showColorWheel.setBorder(BorderFactory.createLoweredBevelBorder());
		showPaintTools.setBorder(BorderFactory.createLoweredBevelBorder());
		showFilterTools.setBorder(BorderFactory.createLoweredBevelBorder());
		
		file.setBackground(Color.WHITE);
		file.setFocusPainted(false);

		edit.setBackground(Color.WHITE);
		edit.setFocusPainted(false);

		save.setBackground(Color.WHITE);
		save.setFocusPainted(false);

		open.setBackground(Color.WHITE);
		open.setFocusPainted(false);

		newFile.setBackground(Color.WHITE);
		newFile.setFocusPainted(false);

		handleButtons(x, y);
		
		

		menuPanel.add(file);
		menuPanel.add(edit);
		menuPanel.add(save);
		menuPanel.add(open);
		menuPanel.add(newFile);
		menuPanel.add(showColorWheel);
		menuPanel.add(showPaintTools);
		menuPanel.add(showFilterTools);
		menuPanel.add(radius);
		menuPanel.add(radiusLabel);
		
		addActionListeners();
		Panel.mainLayeredPanel.add(menuPanel, new Integer(1), 0);
	}

	public void handleButtons(int x, int y) {
		showFilterTools.setSize(20, 20);
		radiusLabel.setSize(100, 30);
		radius.setSize(55, 30);
		showColorWheel.setSize(20, 20);
		showPaintTools.setSize(20, 20);
		file.setSize(buttonWidth, buttonHeight);
		edit.setSize(buttonWidth, buttonHeight);
		save.setSize(buttonWidth, buttonHeight);
		open.setSize(buttonWidth, buttonHeight);
		newFile.setSize(buttonWidth, buttonHeight);
		
		radiusLabel.setLocation(0 + 510, 0);
		radius.setLocation(600, 0);
		showPaintTools.setLocation(Frame.guiWindow.getWidth() - 30, 30);
		showColorWheel.setLocation(Frame.guiWindow.getWidth() - 30, 0);
		showFilterTools.setLocation(Frame.guiWindow.getWidth() - 60, 0);
		file.setLocation(x, y);
		edit.setLocation(x + buttonWidth, y);
		save.setLocation(x + buttonWidth * 2, y);
		open.setLocation(x + buttonWidth * 3, y);
		newFile.setLocation(x + buttonWidth * 4, y);
	}
	
	public void addActionListeners() {
		newFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!NewFile.isFileCreatorEnabled) {
					newFile1 = new NewFile(400, 400, guiHandler);
				}
			}
		});
		
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guiHandler.loadImage();
			}
		});
		
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveFile save = new SaveFile();
			}
		});
		
		showColorWheel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleBorder(showColorWheel);	
			}
		});
		
		showPaintTools.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleBorder(showPaintTools);
			}
		});
		
		showFilterTools.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleBorder(showFilterTools);
			}
		});
	 }
	
	public BufferedImage loadImage(String name, int prefX, int prefY) {
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
	
	private void toggleBorder(JButton button) {
		if(button.getBorder() == BorderFactory.createRaisedBevelBorder()) {
			if(button == showPaintTools) {
				PaintTools.paintTools.setVisible(true);
			}
			
			if(button == showColorWheel) {
				ColorPickerGUI.colorPickerGui.setVisible(true);
			}
			
			if(button == showFilterTools) {
				RGBTools.rgbTools.setVisible(true);
			}
			
			button.setBorder(BorderFactory.createLoweredBevelBorder());
		} else if(button.getBorder() == BorderFactory.createLoweredBevelBorder()) {
			if(button == showPaintTools) {
				PaintTools.paintTools.setVisible(false);
			}
			
			if(button == showColorWheel) {
				ColorPickerGUI.colorPickerGui.setVisible(false);
			}
			
			if(button == showFilterTools) {
				RGBTools.rgbTools.setVisible(false);
			}
			
			button.setBorder(BorderFactory.createRaisedBevelBorder());
		}
		
	}
}
