package org.elvio.chess.util;

import org.elvio.chess.elements.Board;

public class BoardEvalue {

	Board board;
	int score;
	
	public BoardEvalue(Board board, int score){
		this.board = board;
		this.score = score;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public BoardEvalue clone(){
		Board clone = board.clone();
		this.board = clone;
		return this;
	}
	
}
