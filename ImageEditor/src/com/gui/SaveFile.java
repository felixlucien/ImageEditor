 package com.gui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SaveFile {

	boolean selected;
	File image;
	public SaveFile() {
		File saveFile = new File("image.png");
		try {
			ImageIO.write(ImageHandlerAll.finalImage, "png", saveFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JPanel fileChooserPanel = new JPanel(null);
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
		fileChooser.setSelectedFile(saveFile);
		fileChooser.showSaveDialog(fileChooserPanel);
		
		if(ImageHandlerAll.currentImage == null) {
			String message = "There is nothing to save";
			JOptionPane.showMessageDialog(new JFrame(), message, "", JOptionPane.ERROR_MESSAGE);
		}
		
		/*
		if(fileChooser.getCurrentDirectory() != null) {
			String message = "Are you sure you want to overwrite \n" + fileChooser.getSelectedFile() + "?";
			int n = JOptionPane.showConfirmDialog(null, message , "", JOptionPane.YES_NO_CANCEL_OPTION);
			
			if(n == JOptionPane.YES_OPTION) {
				
			}
			
			if(n == JOptionPane.NO_OPTION) {
				
			}
			
			if(n == JOptionPane.CANCEL_OPTION) {
				
			}
		}
		*/
	}
}
