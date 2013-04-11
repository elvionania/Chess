package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.List;

public class EtatDUnBoard {

	private List<Board> boards;
	private List<List<List<Byte>>> coupARetenir;
	private Board premierParent;
	
	public EtatDUnBoard(){
		boards = new ArrayList<Board>();
	}
	
	public EtatDUnBoard(Board premierParent) {
		boards = new ArrayList<Board>();
		this.premierParent = premierParent;
	}

	public List<Board> getBoards() {
		return boards;
	}
	
	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}
	
	public void addBoard(Board board) {
		this.boards.add(board);
	}
	
	public void addBoards(List<Board> boards) {
		this.boards.addAll(boards);
	}
	
	public List<List<List<Byte>>> getCoupARetenir() {
		return coupARetenir;
	}
	
	public void setCoupARetenir(List<List<List<Byte>>> coupARetenir) {
		this.coupARetenir = coupARetenir;
	}

	public void addCoupARetenir(List<List<List<Byte>>> coupARetenir) {
		if(this.coupARetenir == null){
			this.coupARetenir = coupARetenir;
		}else{
			for(List<List<Byte>> coup : coupARetenir){
				this.coupARetenir.add(coup);
			}
		}
		
	}
	
	public int size() {
		return this.boards.size();
	}

	public Board get(int i) {
		return this.boards.get(i);
	}

	public Board getPremierParent() {
		return premierParent;
	}

	public void setPremierParent(Board premierParent) {
		this.premierParent = premierParent;
	}

	public void init() {
		boards = new ArrayList<Board>();
		coupARetenir = null;
		premierParent = null;		
	}
	
}
