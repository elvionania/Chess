package org.elvio.chess.elements;

import java.util.Arrays;
import java.util.HashMap;

import org.elvio.chess.util.BoardEvalue;

public class HashBoard {

	private HashMap<String, Integer> boards;	
	private final static long MAX_BOARD = 1000000;
	private long compteur;
	
	public HashBoard(){
		boards = new HashMap<>();
		compteur = 0;
	}
	
	public Integer getScore(Board board){
		Integer score = getScore(board.toLong());
		return score;
	}
	
	private Integer getScore(long[] board){
		return boards.get(Arrays.toString(board));
	}
	
	public void putScore(BoardEvalue board){
		if(compteur < MAX_BOARD){
			boards.put(board.getBoard().toString(), board.getScore());
			compteur++;
		}
	}
	
}
