package com.drewi.minesweeper.util;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.drewi.minesweeper.MineButton;

public class ImageHelper {
	
	private static final String RES_DIR = "images/";

	public enum ImageProps{

		UNCLICKED(RES_DIR + "unclicked.png", MineButton.SIZE, MineButton.SIZE),

		BOMB(RES_DIR + "bomb.png", MineButton.SIZE, MineButton.SIZE),

		FLAG(RES_DIR + "flag.png", MineButton.SIZE, MineButton.SIZE),

		FLAG_HOVER(RES_DIR + "flaghover.png", MineButton.SIZE, MineButton.SIZE),

		HOVER(RES_DIR + "hover.png", MineButton.SIZE, MineButton.SIZE),

		QUESTIONMARK(RES_DIR + "questionmark.png", MineButton.SIZE, MineButton.SIZE),
		
		QUESTIONMARK_HOVER(RES_DIR + "questionmarkhover.png", MineButton.SIZE, MineButton.SIZE),

		ZERO(RES_DIR + "zero.png", MineButton.SIZE, MineButton.SIZE),

		ONE(RES_DIR + "one.png", MineButton.SIZE, MineButton.SIZE),

		TWO(RES_DIR + "two.png", MineButton.SIZE, MineButton.SIZE),

		THREE(RES_DIR + "three.png", MineButton.SIZE, MineButton.SIZE),

		FOUR(RES_DIR + "four.png", MineButton.SIZE, MineButton.SIZE),

		FIVE(RES_DIR + "five.png", MineButton.SIZE, MineButton.SIZE),

		SIX(RES_DIR + "six.png", MineButton.SIZE, MineButton.SIZE),

		SEVEN(RES_DIR + "seven.png", MineButton.SIZE, MineButton.SIZE),

		EIGHT(RES_DIR + "eight.png", MineButton.SIZE, MineButton.SIZE);
		
		private final String mPath;
		private final int mWidth;
		private final int mHeight;
		
		private ImageProps(String path, int width, int height){
			mPath = path;
			mWidth = width;
			mHeight = height;
		}
		
		public int getHeight() {
			return mHeight;
		}
		
		public int getWidth() {
			return mWidth;
		}
		
		public String getPath() {
			return mPath;
		}
	}
	
	public static Icon getScaledIcon(Class<?> oclass, ImageProps imgProps){
		ImageIcon origIcon = new ImageIcon(oclass.getResource(imgProps.getPath()));
		Image img = origIcon.getImage();
		Image newimg = img.getScaledInstance(imgProps.getWidth(), imgProps.getHeight(), Image.SCALE_SMOOTH) ;  
		return new ImageIcon(newimg);
	}
	
}
