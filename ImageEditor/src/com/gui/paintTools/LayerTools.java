package com.gui.paintTools;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.gui.Frame;

public class LayerTools {

	public static JDialog layerTools;
	public LayerTools() {
		JLayeredPane panel = new JLayeredPane();
		layerTools = new JDialog(layerTools, "Layer tools");
		layerTools.setLocationRelativeTo(null);
		layerTools.setAlwaysOnTop(true);
		layerTools.setIconImage(null);
		layerTools.setResizable(false);
		layerTools.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				layerTools.setVisible(false);
				
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
		layerTools.setLocationRelativeTo(Frame.guiWindow);
		layerTools.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		layerTools.setSize(300, 230);
		layerTools.add(panel);
		layerTools.setFocusableWindowState(false);
		layerTools.setVisible(true);
		
		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);
		
		
		JPanel layer1 = new JPanel(null);
		layer1.setSize(300, 50);
		createPanelConstants(layer1, 1);
		
		JPanel layer2 = new JPanel(null);
		layer2.setSize(300, 50);
		layer2.setLocation(0, 50);
		createPanelConstants(layer2, 2);
		
		JPanel layer3 = new JPanel(null);
		layer3.setSize(300, 50);
		layer3.setLocation(0, 100);
		createPanelConstants(layer3, 3);
		
		JPanel layer4 = new JPanel(null);
		layer4.setSize(300, 50);
		layer4.setLocation(0, 150);
		createPanelConstants(layer4, 4);
		
		JPanel layer5 = new JPanel(null);
		JPanel layer6 = new JPanel(null);
		JPanel layer7 = new JPanel(null);
		JPanel layer8 = new JPanel(null);
		JPanel layer9 = new JPanel(null);
		JPanel layer10 = new JPanel(null);
		
		panel.add(layer1, new Integer(0), 0);
		panel.add(layer2, new Integer(1), 0);
		panel.add(layer3, new Integer(2), 0);
		panel.add(layer4, new Integer(3), 0);
		
		
	}
	
	private void createPanelConstants(JPanel parent, int num) {
		JCheckBox isVisible = new JCheckBox();
	//	isVisible.setd
		isVisible.setLocation(50, 10);
		isVisible.setSize(20, 20);
		parent.add(isVisible);
		
		JLabel label = new JLabel("Layer " + num);
		label.setSize(80, 30);
		label.setLocation(5, 5);
		parent.add(label);
		
	}
}
