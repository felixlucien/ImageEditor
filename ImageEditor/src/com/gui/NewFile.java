package com.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.fileInput.IsParsable;

public class NewFile {
	
	static boolean isFileCreatorEnabled;
	JDialog fileCreator;
	public int GUI_FILE_CREATOR_WIDTH;
	public int GUI_FILE_CREATOR_HEIGHT;
	JPanel fileCreatorPanel;
	JLabel imageWidthLabel, imageHeightLabel, titleLabel;
	JTextField imageWidth, imageHeight;
	int imageParameterConstantWidth = 100, imageParameterConstantHeight = 30,
	imageParameterConstantX = 40, imageParameterConstantY = 50;
	JButton create, cancel;
	String widthTF;
	String heightTF;
	GuiHandler guiHandler;
	int imgWidth, imgHeight;
	JScrollPane scrollPane;
	
	public NewFile(int width, int height, GuiHandler guiHandler) {
		int n = 1000;
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		if(ImageHandlerAll.currentImage != null) {
			String message = "Do you want to save untitled? \n if not it will be deleted";
			n = JOptionPane.showConfirmDialog(null, message , "", JOptionPane.YES_NO_CANCEL_OPTION);
			
			if(n == JOptionPane.YES_OPTION) {
				SaveFile save = new SaveFile();
				createFileCreator(width, height, guiHandler);
			}
			
			if(n == JOptionPane.NO_OPTION) {
				createFileCreator(width, height, guiHandler);
			}
			
			if(n == JOptionPane.CANCEL_OPTION) {
				
			}
		}
		
		if(n == 1000) {
			createFileCreator(width, height, guiHandler);
		}
	}
	
	private void createFileCreator(int width, int height, GuiHandler guiHandler) {
		this.guiHandler = guiHandler;
		isFileCreatorEnabled = true;
		GUI_FILE_CREATOR_WIDTH = width;
		GUI_FILE_CREATOR_HEIGHT = height;
		fileCreator = new JDialog(fileCreator, "Create Image");
		fileCreator.setSize(GUI_FILE_CREATOR_WIDTH, GUI_FILE_CREATOR_HEIGHT);
		fileCreator.setResizable(false);
		fileCreator.setVisible(true);
		fileCreator.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				fileCreator.dispose();
				isFileCreatorEnabled = false;
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
		
		
		
		fileCreatorPanel = new JPanel(null);
		fileCreatorPanel.setBackground(Color.WHITE);
		fileCreator.add(fileCreatorPanel);
		fileCreator.setLocationRelativeTo(null);
		fileCreator.setAlwaysOnTop(true);
		createSwingConstants();
		addActionListenersToButton();
		swingConstantHandler();
		addConstants();
	}
	
	private void createSwingConstants() {
		titleLabel = new JLabel("<html> <font Size=7> Create New Image</font> </html>");
		imageWidthLabel = new JLabel("Width: ");
		imageHeightLabel = new JLabel("Height: ");
		imageWidth = new JTextField("Width");
		imageHeight = new JTextField("Height");
		create = new JButton("Create");
		cancel = new JButton("Cancel");
	}
	
	private void swingConstantHandler() {
		titleLabel.setSize(400, 70);
		
		imageWidthLabel.setSize(imageParameterConstantWidth, imageParameterConstantHeight);
		imageHeightLabel.setSize(imageParameterConstantWidth, imageParameterConstantHeight);
		
		imageHeight.setSize(imageParameterConstantWidth, imageParameterConstantHeight);
		imageWidth.setSize(imageParameterConstantWidth, imageParameterConstantHeight);
		
		create.setSize(100, 30);
		cancel.setSize(100, 30);
		
		titleLabel.setLocation(fileCreator.getWidth() / 2 - 200, -10);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		imageWidthLabel.setLocation(imageParameterConstantX, imageParameterConstantY);
		imageHeightLabel.setLocation(imageParameterConstantX, imageParameterConstantY + imageParameterConstantHeight + 20);
		
		imageHeight.setLocation(imageParameterConstantX + 50, imageParameterConstantY + imageParameterConstantHeight + 20);
		imageWidth.setLocation(imageParameterConstantX + 50, imageParameterConstantY);
		
		create.setLocation(290, 340);
		create.setBackground(Color.WHITE);
		create.setFocusPainted(false);
		
		cancel.setLocation(190, 340);
		cancel.setBackground(Color.WHITE);
		cancel.setFocusPainted(false);
	}
	
	private void addActionListenersToButton() {
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createNewImage();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				killCreator();
			}
		});
	}
	
	private void killCreator() {
		fileCreator.dispose();
		isFileCreatorEnabled = false;
	}
	
	private void createNewImage() {
		checkInput();
		guiHandler.createImage(imgWidth, imgHeight);
		fileCreator.dispose();
		isFileCreatorEnabled = false;
	}
	
	private void checkInput() {
		boolean parsableWidth;
		boolean parsableHeight;
		int width = 0;
		int height = 0;
		
		widthTF = imageWidth.getText();
		heightTF = imageHeight.getText();
		
		parsableWidth = IsParsable.isParsable(widthTF);
		parsableHeight = IsParsable.isParsable(heightTF);
		
		if(parsableWidth) {
			width = Integer.parseInt(widthTF);
		}
		
		if(parsableHeight) {
			height = Integer.parseInt(heightTF);
		}
		
		if(!parsableWidth || !parsableHeight) {
			width = 300;
			height = 300;
		}
		
		imgWidth = width;
		imgHeight = height;
	}
	
	private void addConstants() {
		fileCreatorPanel.add(imageWidthLabel);
		fileCreatorPanel.add(imageHeightLabel);
		fileCreatorPanel.add(imageWidth);
		fileCreatorPanel.add(imageHeight);	
		fileCreatorPanel.add(titleLabel);
		fileCreatorPanel.add(create);
		fileCreatorPanel.add(cancel);
	}
	
	public void handleConstants() {
		swingConstantHandler();
	}
}
