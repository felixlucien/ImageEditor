package com.gui.paintTools;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.gui.Frame;
import com.gui.MenuBar;

public class RGBTools {
	JPanel panel;
	public static JDialog rgbTools;
	
	JSlider red, green, blue, alpha;
	
	public static int redI, greenI, blueI, alphaI;
	
	public RGBTools() {
		panel = new JPanel(null);
		rgbTools = new JDialog(rgbTools, "Scale RGB");
		rgbTools.setSize(300, 300);
		rgbTools.setLocationRelativeTo(null);
		rgbTools.setAlwaysOnTop (true);
		rgbTools.setIconImage(null);
		rgbTools.setResizable(false);
		rgbTools.setFocusable(false);
		rgbTools.setLocationRelativeTo(Frame.guiWindow);
		rgbTools.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		rgbTools.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				rgbTools.setVisible(false);
				MenuBar.showFilterTools.setBorder(BorderFactory.createRaisedBevelBorder());
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		rgbTools.add(panel);
		
		JLabel redLabel = new JLabel("Red: ");
		redLabel.setSize(50, 30);
		redLabel.setLocation(10, 15);
		
		JLabel greenLabel = new JLabel("Green: ");
		greenLabel.setSize(50, 30);
		greenLabel.setLocation(10, 85);
		
		JLabel blueLabel = new JLabel("Blue: ");
		blueLabel.setSize(50, 30);
		blueLabel.setLocation(10, 150);

		JLabel alphaLabel = new JLabel("Alpha: ");
		alphaLabel.setSize(50, 30);
		alphaLabel.setLocation(10, 225);
		
		panel.add(redLabel);
		panel.add(greenLabel);
		panel.add(blueLabel);
		panel.add(alphaLabel);
		
		red = new JSlider(0, 255);
		red.setValue(255);
		red.setSize(250, 50);
		red.setLocation(40, 10);
		red.setMajorTickSpacing(25);
		red.setPaintTicks(true);
		red.setPaintLabels(true);
		panel.add(red);
		
		green = new JSlider(0, 255);
		green.setValue(255);
		green.setSize(250, 60);
		green.setLocation(40, 70);
		green.setMajorTickSpacing(25);
		green.setPaintTicks(true);
		green.setPaintLabels(true);
		panel.add(green);
		
		blue = new JSlider(0, 255);
		blue.setValue(255);
		blue.setSize(250, 50);
		blue.setLocation(40, 140);
		blue.setMajorTickSpacing(25);
		blue.setPaintTicks(true);
		blue.setPaintLabels(true);
		panel.add(blue);
	
		alpha = new JSlider(0, 255);
		alpha.setValue(173);
		alpha.setSize(250, 60);
		alpha.setLocation(40, 210);
		alpha.setMajorTickSpacing(25);
		alpha.setPaintTicks(true);
		alpha.setPaintLabels(true);
		panel.add(alpha);
		
		rgbTools.setVisible(true);
	}
	
	public void updateColors() {
		redI = red.getValue();
		greenI = green.getValue();
		blueI = blue.getValue();
		alphaI = alpha.getValue();
	}
}
