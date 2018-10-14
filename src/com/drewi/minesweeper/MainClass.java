package com.drewi.minesweeper;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.drewi.minesweeper.Board.GameFinishedListener;

public class MainClass implements GameFinishedListener {
	
	private static final int ROWS = 10;
	private static final int COLUMNS = 10;
	private static final int MINES = 10;

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
		mBoard.setGameFinishedListener(this);
	}

	@Override
	public void gameOver() {
		mBoard.revealAllBombs();
		JOptionPane.showMessageDialog(null, "Game Over");
	}
	
}
