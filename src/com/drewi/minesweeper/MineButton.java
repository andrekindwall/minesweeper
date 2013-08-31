package com.drewi.minesweeper;

import java.awt.Color;
import java.awt.Graphics;

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
	private boolean mIsBomb;
	private int mBombNeighbours;

	private ImageProps mClickedImageProps;
	private ImageProps mUnclickedImageProps;
	
	private TranslucentView mHoverView;
	private int mFlagState = FLAG_NONE;
	
	public MineButton(int row, int column) {
		mRow = row;
		mColumn = column;
		
		mUnclickedImageProps = ImageProps.UNCLICKED;
		
		setBounds(mColumn*SIZE, mRow*SIZE, SIZE, SIZE);
		
		mHoverView = new TranslucentView();
		mHoverView.setBounds(0, 0, SIZE, SIZE);
		mHoverView.setVisible(false);
		add(mHoverView);
		
		setClicked(false);
	}
	
	public void setMineNeighbours(int mineNeighbours){
		mBombNeighbours = mineNeighbours;
		
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
		mHoverView.setVisible(false);
	}
	
	public boolean isClicked(){
		return mIsClicked;
	}
	
	public boolean isClickable(){
		return !mIsClicked && mFlagState != FLAG_FLAG;
	}
	
	public boolean isFlagged(){
		return mFlagState == FLAG_FLAG;
	}
	
	public void setHovering(boolean hovering){
		if(!mIsClicked){
			mHoverView.setVisible(hovering);
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
				break;
			case FLAG_FLAG:
				mUnclickedImageProps = ImageProps.FLAG;
				break;
			case FLAG_QUESTIONMARK:
				mUnclickedImageProps = ImageProps.QUESTIONMARK;
				break;
			}
			
			Icon icon = ImageHelper.getScaledIcon(getClass(), mUnclickedImageProps);
			setIcon(icon);
		}
	}
	
	public void setIsMine(boolean isMine) {
		mIsBomb = isMine;
		if(mIsBomb){
			mClickedImageProps = ImageProps.BOMB;
		}
	}
	
	public boolean isBomb() {
		return mIsBomb;
	}
	
	public int getBombNeighbours(){
		return mBombNeighbours;
	}
	
	public int getRow() {
		return mRow;
	}
	
	public int getColumn() {
		return mColumn;
	}
	
	public class TranslucentView extends JLabel{
		private static final long serialVersionUID = 2192667698664931117L;

		public TranslucentView(){
	        super();
	        setLayout(null);
	    }
	    
	    public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        Color ppColor = new Color(255, 255, 255, 70); //r,g,b,alpha
	        g.setColor(ppColor);
	        g.fillRect(0,0,SIZE,SIZE);
	    }    
	}
	
}
