package com.toolkit;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import com.gui.GuiHandler;

public class DetectRes {
	public DetectRes() {
		setRes();
	}
	
	private int[] getScreenRes() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		int[] res = {
			width, height	
		};
		return res;
	}
	
	private void setRes() {
		int[] res = getScreenRes();
		int width = res[0];
		int height = res[1];
		
		if(width >= 1280 && height >= 720) {
			GuiHandler.FRAME_WITDH = 1260;
			 GuiHandler.FRAME_HEIGHT = 700;
		}
		
		if(width >= 1920 && height >= 1080) {
			 GuiHandler.FRAME_WITDH = 1900;
			 GuiHandler.FRAME_HEIGHT = 1000;
		}
	
		if(width >= 2160 && height >= 1440) {
			 GuiHandler.FRAME_WITDH = 2100;
			 GuiHandler.FRAME_HEIGHT = 1360;
		}
		
		if(width >= 3840 && height >= 2160) {
			 GuiHandler.FRAME_WITDH = 3800;
			 GuiHandler.FRAME_HEIGHT = 2050;
		}
		System.out.println(width + " " + height);
	}
}
