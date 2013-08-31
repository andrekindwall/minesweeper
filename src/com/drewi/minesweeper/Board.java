package com.drewi.minesweeper;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;

import com.drewi.minesweeper.ButtonDrawer.MinesweeperMouseListener;

public class Board extends JFrame implements MinesweeperMouseListener{
	
	public interface GameFinishedListener{
		public void gameOver();
	}
	
	private static final long serialVersionUID = 9148170605818301711L;
	
	private List<MineButton> mButtons;

	private int mRows;
	private int mColumns;
	private int mMines;
	
	private boolean mIsBoardGenerated;
	private boolean mGameOver;
	
	private GameFinishedListener mListener;
	
	public Board(int rows, int columns, int mines) {
		super("Minesweeper");
		setLayout(null);
		
		mRows = rows;
		mColumns = columns;
		mMines = mines;

		ButtonDrawer drawer = new ButtonDrawer();
		mButtons = drawer.createButtons(mRows, mColumns);
		drawer.setMinesweeperMouseListener(this);
		add(drawer);
		
		setSize(calculateBoardSize(-10));
		drawer.setSize(calculateBoardSize(0));
	}
	
	public void setGameFinishedListener(GameFinishedListener listener){
		mListener = listener;
	}
	
	/**
	 * Generates the board, placing all the mines.
	 * @param exclude This position or any of its neighbours will not be a mine.
	 */
	private void generateBoard(int exclude){
		List<Integer> excludes = getNeighbours(mButtons.get(exclude));
		excludes.add(exclude);
		Set<Integer> mineButtons = generateMines(excludes);
		
		for(int i=0; i<mButtons.size(); i++){
			MineButton button = mButtons.get(i);
			button.setIsMine(mineButtons.contains(i));
		}
		countButtonsMineNeighbours();
		mIsBoardGenerated = true;
	}
	
	private void countButtonsMineNeighbours(){
		for(MineButton button : mButtons){
			if(button.isMine()){
				continue;
			}
			
			List<Integer> neighbors = getNeighbours(button);
			
			int mineNeighbours = 0;
			for(int neighbourPos : neighbors){
				if(mButtons.get(neighbourPos).isMine()){
					mineNeighbours++;
				}
			}
			button.setMineNeighbours(mineNeighbours);
		}
	}
	
	/**
	 * Generates mine positions.
	 * @param excludes These positions will not be a mine.
	 */
	private Set<Integer> generateMines(List<Integer> excludes){
		Set<Integer> minePositions = new HashSet<Integer>();
		Random rand = new Random();
		
		int nbrOfButtons = mRows * mColumns;
		while (minePositions.size() < mMines) {
			int randPos = rand.nextInt(nbrOfButtons);
			if(excludes == null || !excludes.contains(randPos)){
				minePositions.add(randPos);
			}
		}
		
		return minePositions;
	}
	
	private void clickedMineButton(MineButton button){
		int position = button.getRow()*mColumns + button.getColumn();
		Set<Integer> checkedPositions = new HashSet<Integer>();
		checkedPositions.add(position);
		
		if(!button.isMine() && button.getMineNeighbours() == 0){
			chainMineButtonClick(position, checkedPositions);
		}
		
		for(int checkedPosition : checkedPositions){
			mButtons.get(checkedPosition).setClicked(true);
		}
		
		if(mListener != null && button.isMine()){
			mListener.gameOver();
			mGameOver = true;
		}
	}
	
	private void chainMineButtonClick(int position, Set<Integer> checkedPositions){
		MineButton button = mButtons.get(position);
		List<Integer> neighbours = getNeighbours(button);
		for(int neighbourPos : neighbours){
			if(checkedPositions.contains(neighbourPos)){
				continue;
			}
			checkedPositions.add(neighbourPos);
			MineButton neighbourButton = mButtons.get(neighbourPos);
			
			if(neighbourButton.getMineNeighbours() == 0){
				chainMineButtonClick(neighbourPos, checkedPositions);
			}
			
		}
	}
	
	private List<Integer> getNeighbours(MineButton button){
		int row = button.getRow();
		int column = button.getColumn();
		int pos = row*mColumns + column;
		boolean atTop = row == 0;
		boolean atLeft = column == 0;
		boolean atRight = column == mColumns-1;
		boolean atBottom = row == mRows-1;
		
		ArrayList<Integer> neighbours = new ArrayList<Integer>();
		
		if(!atTop && !atLeft)
			neighbours.add(pos-mColumns-1);
		if(!atTop)
			neighbours.add(pos-mColumns);
		if(!atTop && !atRight)
			neighbours.add(pos-mColumns+1);
		if(!atLeft)
			neighbours.add(pos-1);
		if(!atRight)
			neighbours.add(pos+1);
		if(!atBottom && !atLeft)
			neighbours.add(pos+mColumns-1);
		if(!atBottom)
			neighbours.add(pos+mColumns);
		if(!atBottom && !atRight)
			neighbours.add(pos+mColumns+1);
		
		return neighbours;
	}
	
	private Dimension calculateBoardSize(int offset) {
		int width = mColumns*MineButton.SIZE;
		int height = mRows*MineButton.SIZE;
		return new Dimension(width + offset, height + offset);
	}
	
	@Override
	public void setSize(Dimension dimension) {
		getContentPane().setPreferredSize(dimension);
		pack();
	}

	@Override
	public void onLeftClick(MineButton button) {
		if(!mIsBoardGenerated){
			generateBoard(button.getRow()*mColumns + button.getColumn());
		}
		if(!mGameOver && button.isClickable()){
			clickedMineButton(button);
		}
	}

	@Override
	public void onRightClick(MineButton button) {
		if(!mGameOver && !button.isClicked()){
			button.toggleFlag();
		}
	}

	@Override
	public void onDualClick(MineButton button) {
		// TODO Auto-generated method stub
		
	}
	
}
