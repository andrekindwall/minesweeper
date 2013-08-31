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
			button.setClicked();
		}
	}
	
	private void createButtons(){
		mButtons = new ArrayList<>();
		Set<Integer> mineButtons = generateMines();
		
		for(int row=0; row<mRows; row++){
			for(int column=0; column<mColumns; column++){
				MineButton button = new MineButton(row, column);
				button.setIsMine(mineButtons.contains(row*mColumns + column));
				
				mButtons.add(button);
				add(button);
			}
		}
		
		countMineSiblings();
	}
	
	private void countMineSiblings(){
		MineButton button;
		ArrayList<Integer> siblings = new ArrayList<Integer>();
		boolean atTop;
		boolean atLeft;
		boolean atRight;
		boolean atBottom;
		for(int pos=0; pos<mButtons.size(); pos++){
			button = mButtons.get(pos);
			if(button.isMine()){
				continue;
			}
			atTop = button.getRow() == 0;
			atLeft = button.getColumn() == 0;
			atRight = button.getColumn() == mColumns-1;
			atBottom = button.getRow() == mRows-1;
			
			siblings.clear();

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
			
			int mineSiblings = 0;
			for(int siblingPos : siblings){
				if(mButtons.get(siblingPos).isMine()){
					mineSiblings++;
				}
			}
			button.setMineSiblings(mineSiblings);
		}
	}
	
	private Set<Integer> generateMines(){
		Set<Integer> minePositions = new HashSet<Integer>();
		Random rand = new Random();
		
		int nbrOfButtons = mRows * mColumns;
		while (minePositions.size() < mMines) {
			minePositions.add(rand.nextInt(nbrOfButtons));
		}
		
		return minePositions;
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
	}
	

}
