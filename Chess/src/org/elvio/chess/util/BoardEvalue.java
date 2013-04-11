package org.elvio.chess.util;

import org.elvio.chess.elements.Board;

public class BoardEvalue {

	Board board;
	Double score;
	
	public BoardEvalue(Board board, Double score){
		this.board = board;
		this.score = score;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	public BoardEvalue clone(){
		Board clone = board.clone();
		this.board = clone;
		return this;
	}
	
}
