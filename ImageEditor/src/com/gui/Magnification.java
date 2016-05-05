package com.gui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fileInput.IsParsable;


public class Magnification {
	public static int mag = 100;
	JTextField magInput;
	public Magnification() {
		JPanel panel = new JPanel(null);
		panel.setSize(200, 40);
		panel.setLocation(GuiHandler.FRAME_WITDH - 200, GuiHandler.FRAME_HEIGHT - 65);
		panel.setOpaque(true);
		panel.setBackground(new Color(219, 235, 255));
		Panel.mainLayeredPanel.add(panel, new Integer(2), 0);
		
		JLabel mag = new JLabel("Magnification");
		mag.setSize(70, 30);
		mag.setLocation(0, 0);
		mag.setHorizontalAlignment(JLabel.CENTER);
		mag.setVerticalAlignment(JLabel.CENTER);
		panel.add(mag);
		
		magInput = new JTextField("100%");
		magInput.setSize(60, 30);
		magInput.setLocation(70, 0);
		
		panel.add(magInput);
	}
	
	public void updateMag() {
		if(IsParsable.isParsable(magInput.getText())) {
			mag = Integer.parseInt(magInput.getText());
		}
	}
	
}
