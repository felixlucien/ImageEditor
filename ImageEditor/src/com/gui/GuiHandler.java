package com.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Timer;

import com.gui.paintTools.ColorPickerGUI;
import com.imageHandler.ImageHandler;

public class GuiHandler implements ActionListener {
	
	public static int FRAME_WITDH; //= 1280; //1920;
	public static int FRAME_HEIGHT;// = 720; //1040;

	public static int isImage;	
	
	public MenuBar menuBar;
	public Frame frame;
	public Panel panel;
	ImageHandlerAll imageHandlerAll;
	Timer update;
	Magnification mag;
	public GuiHandler() {
		update = new Timer(30, this);
		frame = new Frame(FRAME_WITDH, FRAME_HEIGHT, "Image Editor", this);
		panel = new Panel();
		menuBar = new MenuBar(0, 0, this);
		Frame.guiWindow.add(Panel.mainLayeredPanel);
		mag = new Magnification();
	}
	
	protected void createImage(int width, int height) {
		imageHandlerAll = new ImageHandlerAll();
		imageHandlerAll.createImage(width, height);
		update.start();
	}
	
	protected void loadImage() {
		imageHandlerAll = new ImageHandlerAll();
		OpenFile openFile = new OpenFile();
		if(openFile.selected) {
			loadImage(null, openFile.getFile(), ImageHandler.LOAD_IMAGE_FROM_FILE);
			openFile.selected = false;
		}
		
	}
	
	private void loadImage(String path, File file, int type) {
		if(type == ImageHandler.LOAD_IMAGE_FROM_PATH) {
			imageHandlerAll.loadImageFromPath(path);
		}
		
		if(type == ImageHandler.LOAD_IMAGE_FROM_FILE) {
			imageHandlerAll.loadImageFromFile(file);
		}
		update.start();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(frame != null) {
			Frame.guiWindow.repaint();
			frame.rgbTools.updateColors();
			ColorPickerGUI.colorPickerGui.repaint();
		}
		
		//System.out.println(RGBTools.redI + " " + RGBTools.greenI + " " + RGBTools.blueI);
		
		
		menuBar.handleButtons(0, 0);
		menuBar.updateRadius();
		
		if(menuBar.newFile1 != null) {
			menuBar.newFile1.handleConstants();
		}
		
		if(mag != null) {
			mag.updateMag();
		}
		
		if(imageHandlerAll != null && imageHandlerAll.graphicsPanel != null) {
			imageHandlerAll.graphicsPanel.repaint();
		}
		
		if(!imageHandlerAll.isRendering && imageHandlerAll != null && imageHandlerAll.currentImage != null) {
			imageHandlerAll.updateImage();
			imageHandlerAll.setCursor();
		}
	}
}
