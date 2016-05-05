package com.toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.gui.Frame;
import com.gui.paintTools.ColorPickerGUI;
import com.gui.paintTools.LayerTools;
import com.gui.paintTools.PaintTools;
import com.gui.paintTools.RGBTools;

public class SnapToGrid {
	public static boolean lockedLeft;
	public static boolean lockedRight;
	boolean isSnapping = true;
	public SnapToGrid() {
		Timer timer = new Timer(60, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				snapToGrid(Frame.guiWindow, ColorPickerGUI.colorPickerGui, isSnapping);
				snapToGrid(Frame.guiWindow, PaintTools.paintTools, isSnapping);
				snapToGrid(Frame.guiWindow, RGBTools.rgbTools, isSnapping);
				//snapToGrid(Frame.guiWindow, LayerTools.layerTools, isSnapping);
			}
		});
		timer.start();
	}
	
	public void snapToGrid(JFrame parent, JDialog child, boolean snappingOn) {
		int parentXLeft = parent.getX();
		int parentXRight = parentXLeft + parent.getWidth();
		int childXLeft = child.getX();
		//int childXRight = child.getX() + child.getWidth();
		int leftX = childXLeft - parentXLeft;
		int rightX = leftX - parent.getWidth();
		//int rightXChildR = parentXRight + child.getWidth();
		int temp = parentXRight - child.getWidth();
		int temp2 = temp - child.getX();
		
		while(child.getY() + child.getHeight() > parent.getY() + parent.getHeight()) {
			child.setLocation(child.getX(), child.getY() - 1);
		}
		
		while(child.getY() < parent.getY()) {
			child.setLocation(child.getX(), child.getY() + 1);
		}
		
		while(child.getX() < parent.getX()) {
			child.setLocation(child.getX() + 1, child.getY());
			//child.setLocation(parentXLeft, child.getY());
		}
		
		while(child.getX() + child.getWidth() > parent.getX() + parent.getWidth()) {
			child.setLocation(child.getX() - 1, child.getY());
		}
		
		if(leftX < 30 && leftX > -30 && snappingOn) {
			child.setLocation(parentXLeft, child.getY());
		}
		
		if(temp2 < 30 && temp2 > -30 && snappingOn) {
			child.setLocation(temp, child.getY());	
		}
	}
	
	private void unconditionalSnap(JFrame parent, JDialog child) {
		int parentX = parent.getX();
		int childX = child.getX();
		int x = childX - parentX;
		child.setLocation(parentX, child.getY());	
	}
}
