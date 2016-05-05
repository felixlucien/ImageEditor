
package com.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.gui.paintTools.*;

public class Frame {
	public static JFrame guiWindow;
	public ColorPickerGUI colorPickerGui;
	public boolean isMinimized = true;
	public PaintTools paintTools;
	public RGBTools rgbTools;
	public LayerTools layerTools;
	
	public Frame(int initWidth, int initHeight, String title, GuiHandler guiHandler) {
		try {
			
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		guiWindow = new JFrame(title);
		guiWindow.setSize(initWidth, initHeight);
		guiWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiWindow.setIconImage(null);
		guiWindow.setResizable(false);
		guiWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		guiWindow.setLocationRelativeTo(null);
		guiWindow.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				changeBoolean();
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				changeBoolean();
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
		colorPickerGui = new ColorPickerGUI(207, 300, guiHandler, this);
		paintTools = new PaintTools(guiHandler.imageHandlerAll);
		rgbTools = new RGBTools();
		//layerTools = new LayerTools();
	}
	
	private void changeBoolean() {
		if(isMinimized) {
			isMinimized = false;
		} else if(!isMinimized) {
			isMinimized = true;
		}
		updateColorPickerVar();
	}

	private void updateColorPickerVar() {
		ColorPickerGUI.colorPickerGui.setVisible(isMinimized);
		PaintTools.paintTools.setVisible(isMinimized);
		RGBTools.rgbTools.setVisible(isMinimized);
	}
}
