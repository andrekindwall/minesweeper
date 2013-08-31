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

	private boolean mIsClicked;
	private boolean mIsMine;
	private int mMineNeighbours;
	
	private ImageProps mClickedImageProps;
	
	public MineButton(int row, int column) {
		mRow = row;
		mColumn = column;
		
		setBounds(mColumn*SIZE, mRow*SIZE, SIZE, SIZE);
		
		setClicked(false);
	}
	
	public void setMineNeighbours(int mineNeighbours){
		mMineNeighbours = mineNeighbours;
		
		switch(mineNeighbours){
		case 0:
			mClickedImageProps = ImageProps.ZERO;
			break;
		case 1:
			mClickedImageProps = ImageProps.ONE;
			break;
		case 2:
			mClickedImageProps = ImageProps.TWO;
			break;
		case 3:
			mClickedImageProps = ImageProps.THREE;
			break;
		case 4:
			mClickedImageProps = ImageProps.FOUR;
			break;
		case 5:
			mClickedImageProps = ImageProps.FIVE;
			break;
		case 6:
			mClickedImageProps = ImageProps.SIX;
			break;
		case 7:
			mClickedImageProps = ImageProps.SEVEN;
			break;
		case 8:
			mClickedImageProps = ImageProps.EIGHT;
			break;
		default:
			mClickedImageProps = ImageProps.ZERO;
			break;
		}
	}
	
	public void setClicked(boolean clicked) {
		mIsClicked = clicked;
		Icon icon = ImageHelper.getScaledIcon(getClass(), mIsClicked ? mClickedImageProps : ImageProps.UNCLICKED);
		setIcon(icon);
	}
	
	public boolean isClicked(){
		return mIsClicked;
	}
	
	public void setIsMine(boolean isMine) {
		mIsMine = isMine;
		if(mIsMine){
			mClickedImageProps = ImageProps.BOMB;
		}
	}
	
	public boolean isMine() {
		return mIsMine;
	}
	
	public int getMineNeighbours(){
		return mMineNeighbours;
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
