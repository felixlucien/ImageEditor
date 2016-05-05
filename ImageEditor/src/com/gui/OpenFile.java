package com.gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class OpenFile {
	boolean selected;
	File image;
	public OpenFile() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JPanel fileChooserPanel = new JPanel(null);
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooserPanel);
		if (result == JFileChooser.APPROVE_OPTION) {
		   File selectedFile = fileChooser.getSelectedFile();
		   image = selectedFile;
		   selected = true;
		}
	}
	
	protected File getFile() {
		return image;
	}
}
