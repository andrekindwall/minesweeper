package com.drewi.minesweeper;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Board extends JFrame {
	
	private ArrayList<MineButton> mButtons;
	
	public Board(int rows, int columns, int mines) {
		super("Minesweeper");
		
		createButtons(rows, columns);
	}
	
	@Override
	public void setSize(int width, int height) {
		getContentPane().setPreferredSize(new Dimension(width, height));
		pack();
	}
	
	private void createButtons(int rows, int columns){
		mButtons = new ArrayList<>();
		
		for(int row=0; row<rows; row++){
			for(int column=0; column<columns; column++){
				MineButton button = new MineButton(row, column);
				
				mButtons.add(button);
				add(button);
			}
		}
	}
	

}
