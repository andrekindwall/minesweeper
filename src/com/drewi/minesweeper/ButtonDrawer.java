package com.drewi.minesweeper;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class ButtonDrawer extends Container implements MouseListener, MouseMotionListener {

	public interface MinesweeperMouseListener {
		public void onLeftClick(MineButton button);
		public void onRightClick(MineButton button);
		public void onDualClick(MineButton button);
	}

	private static final long serialVersionUID = -2380975115498957936L;

	private List<MineButton> mButtons;

	private MinesweeperMouseListener mListener;

	private int mColumns;
	
	private boolean mLeftButtonPressed;
	private boolean mRightButtonPressed;
	
	private int mHoverPosition = -1;

	public ButtonDrawer() {
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public List<MineButton> createButtons(int rows, int columns) {
		mColumns = columns;
		mButtons = new ArrayList<MineButton>();
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				MineButton button = new MineButton(row, column);

				mButtons.add(button);
				add(button);
			}
		}
		return mButtons;
	}

	public void setMinesweeperMouseListener(MinesweeperMouseListener listener) {
		mListener = listener;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		//nothing
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		//nothing
	}

	@Override
	public void mouseExited(MouseEvent event) {
		//nothing
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if(event.isMetaDown()){
			mRightButtonPressed = true;
			if(mListener != null && !mLeftButtonPressed) {
				mListener.onRightClick(mButtons.get(buttonIndexAtCoordinates(event.getX(), event.getY())));
			}
		} else {
			mLeftButtonPressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if (mListener != null) {
			try{
				if (mLeftButtonPressed && !mRightButtonPressed) {
					mListener.onLeftClick(mButtons.get(buttonIndexAtCoordinates(event.getX(), event.getY())));
				} if (mLeftButtonPressed && mRightButtonPressed) {
					mListener.onDualClick(mButtons.get(buttonIndexAtCoordinates(event.getX(), event.getY())));
				}
			} catch(IndexOutOfBoundsException e){
				System.out.println("released outside");
			}
		}

		if (event.isMetaDown()) {
			mRightButtonPressed = false;
		} else {
			mLeftButtonPressed = false;
		}
	}
	
	private int buttonIndexAtCoordinates(int x, int y){
		int column = (int) Math.floor(x / MineButton.SIZE);
		int row = (int) Math.floor(y / MineButton.SIZE);
		return row*mColumns + column;
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		mouseMoved(event);
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		int buttonPosition = buttonIndexAtCoordinates(event.getX(), event.getY());
		if(buttonPosition != mHoverPosition){
			mButtons.get(buttonPosition).setHovering(true);
			if(mHoverPosition != -1){
				mButtons.get(mHoverPosition).setHovering(false);
			}
			mHoverPosition = buttonPosition;
		}
	}

}
