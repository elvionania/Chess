package org.elvio.chess.util;

import java.util.ArrayList;
import java.util.List;

import org.elvio.chess.elements.Board;

public class BoardEvalue {

	Board board;
	int score;
	private List<Integer> coupARetenir;
	
	public BoardEvalue(Board board, int score){
		this.board = board;
		this.score = score;
		this.coupARetenir = new ArrayList<>();
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

	public List<Integer> getCoupARetenir() {
		return coupARetenir;
	}

	public void addCoupARetenir(int position, int coup) {
		coupARetenir.add(position);
		coupARetenir.add(coup);
	}
	
	public boolean isRoiMort(byte couleur){
        return board.isRoiMort(couleur);
    }
	
}
