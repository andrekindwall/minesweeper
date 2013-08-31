package com.drewi.minesweeper;

import javax.swing.JFrame;

public class MainClass {
	
	private static final int ROWS = 16;
	private static final int COLUMNS = 30;
	private static final int MINES = 99;

	private Board mBoard;
	
	public static void main(String[] args) {
		MainClass mainClass = new MainClass();
		mainClass.initGui();
	}
	
	private void initGui() {
		mBoard = new Board(ROWS, COLUMNS, MINES);
		mBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mBoard.setResizable(false);
		mBoard.setVisible(true);
	}
	
}
