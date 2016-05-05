package com.main;

import com.gui.Frame;
import com.gui.GuiHandler;
import com.gui.Panel;
import com.toolkit.DetectRes;
import com.toolkit.SnapToGrid;

public class Main {
	
	public Main() {
		new DetectRes();
		GuiHandler guiHandler = new GuiHandler();
		new SnapToGrid();
		Frame.guiWindow.setVisible(true);
		Panel.mainLayeredPanel.repaint();
	}

	public static void main(String[] args) {
		new Main();
	}
}
