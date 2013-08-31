package com.drewi.minesweeper;

import javax.swing.JFrame;

public class MainClass {

	private Board mBoard;
	
	public static void main(String[] args) {
		MainClass mainClass = new MainClass();
		mainClass.initGui();
	}
	
	private void initGui() {
		mBoard = new Board();
		mBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mBoard.setSize(600, 450);
		mBoard.setVisible(true);
	}
	
}
