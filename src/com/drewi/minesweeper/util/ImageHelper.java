package com.drewi.minesweeper.util;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.drewi.minesweeper.MineButton;

public class ImageHelper {
	
	private static final String RES_DIR = "images/";

	public enum ImageProps{

		UNCLICKED(RES_DIR + "unclicked.png", MineButton.getButtonSize(), MineButton.getButtonSize()),

		BOMB(RES_DIR + "bomb.png", MineButton.getButtonSize(), MineButton.getButtonSize()),

		ZERO(RES_DIR + "zero.png", MineButton.getButtonSize(), MineButton.getButtonSize()),

		ONE(RES_DIR + "one.png", MineButton.getButtonSize(), MineButton.getButtonSize()),

		TWO(RES_DIR + "two.png", MineButton.getButtonSize(), MineButton.getButtonSize()),

		THREE(RES_DIR + "three.png", MineButton.getButtonSize(), MineButton.getButtonSize()),

		FOUR(RES_DIR + "four.png", MineButton.getButtonSize(), MineButton.getButtonSize()),

		FIVE(RES_DIR + "five.png", MineButton.getButtonSize(), MineButton.getButtonSize()),

		SIX(RES_DIR + "six.png", MineButton.getButtonSize(), MineButton.getButtonSize()),

		SEVEN(RES_DIR + "seven.png", MineButton.getButtonSize(), MineButton.getButtonSize()),

		EIGHT(RES_DIR + "eight.png", MineButton.getButtonSize(), MineButton.getButtonSize()),

		NINE(RES_DIR + "nine.png", MineButton.getButtonSize(), MineButton.getButtonSize());
		
		private String mPath;
		private int mWidth;
		private int mHeight;
		
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
