package com.drewi.minesweeper;

import javax.swing.Icon;
import javax.swing.JButton;

import com.drewi.minesweeper.util.ImageHelper;
import com.drewi.minesweeper.util.ImageHelper.ImageProps;

public class MineButton extends JButton {

	private static final long serialVersionUID = -4745396141963656398L;
	
	private static final int SIZE = 20;
	
	private int mRow;
	private int mColumn;
	
	private boolean mIsMine;
	
	public MineButton(int row, int column) {
		mRow = row;
		mColumn = column;
		
		setBounds(mColumn*SIZE, mRow*SIZE, SIZE, SIZE);
	}
	
	public void setIsMine(boolean isMine) {
		mIsMine = isMine;
		Icon image = ImageHelper.getScaledIcon(getClass(), isMine ? ImageProps.BOMB : ImageProps.ZERO);
		setIcon(image);
	}
	
	public int getRow() {
		return mRow;
	}
	
	public int getColumn() {
		return mColumn;
	}
	
	public static int getButtonSize(){
		return SIZE;
	}
	
}
