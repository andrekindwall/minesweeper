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
		
		generateMines();
		
		setSize(calculateBoardSize());
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
		return new Dimension(width, height);
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
