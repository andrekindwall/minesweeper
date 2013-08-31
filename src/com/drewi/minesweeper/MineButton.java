package com.drewi.minesweeper;

import javax.swing.Icon;
import javax.swing.JLabel;

import com.drewi.minesweeper.util.ImageHelper;
import com.drewi.minesweeper.util.ImageHelper.ImageProps;

public class MineButton extends JLabel {
	
	private static final long serialVersionUID = -4745396141963656398L;

	public static final int SIZE = 20;
	
	public static final int FLAG_NONE = 0;
	public static final int FLAG_FLAG = 1;
	public static final int FLAG_QUESTIONMARK = 2;
	
	private int mRow;
	private int mColumn;

	private boolean mIsClicked;
	private boolean mIsMine;
	private int mMineNeighbours;

	private ImageProps mClickedImageProps;
	private ImageProps mUnclickedImageProps;
	private ImageProps mHoverImageProps;
	
	private int mFlagState = FLAG_NONE;
	
	public MineButton(int row, int column) {
		mRow = row;
		mColumn = column;
		
		mUnclickedImageProps = ImageProps.UNCLICKED;
		mHoverImageProps = ImageProps.HOVER;
		
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
		Icon icon = ImageHelper.getScaledIcon(getClass(), mIsClicked ? mClickedImageProps : mUnclickedImageProps);
		setIcon(icon);
	}
	
	public boolean isClicked(){
		return mIsClicked;
	}
	
	public boolean isClickable(){
		return !mIsClicked && mFlagState != FLAG_FLAG;
	}
	
	public void setHovering(boolean hovering){
		if(!mIsClicked){
			Icon icon = ImageHelper.getScaledIcon(getClass(), hovering ? mHoverImageProps : mUnclickedImageProps);
			setIcon(icon);
		}
	}
	
	public void toggleFlag(){
		if(!mIsClicked){
			mFlagState++;
			if(mFlagState > FLAG_QUESTIONMARK){
				mFlagState = FLAG_NONE;
			}
			
			switch(mFlagState){
			case FLAG_NONE:
				mUnclickedImageProps = ImageProps.UNCLICKED;
				mHoverImageProps = ImageProps.HOVER;
				break;
			case FLAG_FLAG:
				mUnclickedImageProps = ImageProps.FLAG;
				mHoverImageProps = ImageProps.FLAG_HOVER;
				break;
			case FLAG_QUESTIONMARK:
				mUnclickedImageProps = ImageProps.QUESTIONMARK;
				mHoverImageProps = ImageProps.QUESTIONMARK_HOVER;
				break;
			}
			
			Icon icon = ImageHelper.getScaledIcon(getClass(), mUnclickedImageProps);
			setIcon(icon);
		}
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
	
}
