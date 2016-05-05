package com.gui;

import java.awt.Color;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Panel {
	public static JPanel mainPanel;
	public static JLayeredPane mainLayeredPanel;
	public Panel() {
		mainLayeredPanel = new JLayeredPane();
		mainPanel = new JPanel();
		mainPanel.setSize(GuiHandler.FRAME_WITDH, GuiHandler.FRAME_HEIGHT);
		mainPanel.setBackground(new Color(219, 235, 255));
		mainLayeredPanel.add(mainPanel, new Integer(0), 0);
	}
}
