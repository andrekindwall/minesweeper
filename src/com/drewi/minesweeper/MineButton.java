package com.drewi.minesweeper;

import javax.swing.JButton;

public class MineButton extends JButton {

	private static final int SIZE = 20;
	
	private int mRow;
	private int mColumn;
	
	public MineButton(int row, int column) {
		mRow = row;
		mColumn = column;
		
		setBounds(mColumn*SIZE, mRow*SIZE, SIZE, SIZE);
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
