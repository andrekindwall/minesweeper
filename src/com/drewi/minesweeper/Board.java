package com.drewi.minesweeper;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;

public class Board extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 9148170605818301711L;
	
	private ArrayList<MineButton> mButtons;

	private int mRows;
	private int mColumns;
	private int mMines;
	
	private boolean mIsBoardGenerated;
	
	public Board(int rows, int columns, int mines) {
		super("Minesweeper");
		setLayout(null);
		
		mRows = rows;
		mColumns = columns;
		mMines = mines;

		createButtons();
		
		setSize(calculateBoardSize());
		
		//temp dev code
		for(MineButton button : mButtons){
			button.setClicked(false);
		}
	}
	
	/**
	 * Generates the board, placing all the mines.
	 * @param exclude This position will not be a mine.
	 */
	private void generateBoard(int exclude){
		Set<Integer> mineButtons = generateMines(exclude);
		
		for(int i=0; i<mButtons.size(); i++){
			MineButton button = mButtons.get(i);
			button.setIsMine(mineButtons.contains(i));
		}
		countMineSiblings();
		mIsBoardGenerated = true;
	}
	
	private void createButtons(){
		mButtons = new ArrayList<>();
		
		for(int row=0; row<mRows; row++){
			for(int column=0; column<mColumns; column++){
				MineButton button = new MineButton(row, column);
				button.addActionListener(this);
				
				mButtons.add(button);
				add(button);
			}
		}
		
		
	}
	
	private void countMineSiblings(){
		for(MineButton button : mButtons){
			if(button.isMine()){
				continue;
			}
			
			ArrayList<Integer> siblings = getSiblings(button);
			
			int mineSiblings = 0;
			for(int siblingPos : siblings){
				if(mButtons.get(siblingPos).isMine()){
					mineSiblings++;
				}
			}
			button.setMineSiblings(mineSiblings);
		}
	}
	
	/**
	 * Generates mine positions.
	 * @param exclude This position will not be a mine.
	 */
	private Set<Integer> generateMines(int exclude){
		Set<Integer> minePositions = new HashSet<Integer>();
		Random rand = new Random();
		
		int nbrOfButtons = mRows * mColumns;
		while (minePositions.size() < mMines) {
			int randPos = rand.nextInt(nbrOfButtons);
			if(randPos != exclude){
				minePositions.add(randPos);
			}
		}
		
		return minePositions;
	}
	
	private void clickedMineButton(MineButton button){
		int position = button.getRow()*mColumns + button.getColumn();
		Set<Integer> checkedPositions = new HashSet<Integer>();
		checkedPositions.add(position);
		
		if(!button.isMine() && button.getMineSiblings() == 0){
			chainMineButtonClick(position, checkedPositions);
		}
		
		for(int checkedPosition : checkedPositions){
			mButtons.get(checkedPosition).setClicked(true);
		}
	}
	
	private void chainMineButtonClick(int position, Set<Integer> checkedPositions){
		MineButton button = mButtons.get(position);
		ArrayList<Integer> siblings = getSiblings(button);
		for(int siblingPos : siblings){
			if(checkedPositions.contains(siblingPos)){
				continue;
			}
			checkedPositions.add(siblingPos);
			MineButton siblingButton = mButtons.get(siblingPos);
			
			if(siblingButton.getMineSiblings() == 0){
				chainMineButtonClick(siblingPos, checkedPositions);
			}
			
		}
	}
	
	
	
	private ArrayList<Integer> getSiblings(MineButton button){
		int row = button.getRow();
		int column = button.getColumn();
		int pos = row*mColumns + column;
		boolean atTop = row == 0;
		boolean atLeft = column == 0;
		boolean atRight = column == mColumns-1;
		boolean atBottom = row == mRows-1;
		
		ArrayList<Integer> siblings = new ArrayList<Integer>();
		
		if(!atTop && !atLeft)
			siblings.add(pos-mColumns-1);
		if(!atTop)
			siblings.add(pos-mColumns);
		if(!atTop && !atRight)
			siblings.add(pos-mColumns+1);
		if(!atLeft)
			siblings.add(pos-1);
		if(!atRight)
			siblings.add(pos+1);
		if(!atBottom && !atLeft)
			siblings.add(pos+mColumns-1);
		if(!atBottom)
			siblings.add(pos+mColumns);
		if(!atBottom && !atRight)
			siblings.add(pos+mColumns+1);
		
		return siblings;
	}
	
	private Dimension calculateBoardSize() {
		int buttonSize = MineButton.getButtonSize();
		int width = mColumns*buttonSize;
		int height = mRows*buttonSize;
		return new Dimension(width - 10, height - 10);
	}
	
	@Override
	public void setSize(Dimension dimension) {
		getContentPane().setPreferredSize(dimension);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		MineButton button = (MineButton) event.getSource();
		if(!mIsBoardGenerated){
			generateBoard(button.getRow()*mColumns + button.getColumn());
		}
		clickedMineButton(button);
	}
	

}
