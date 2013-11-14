package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.List;

public class EtatDUnBoard {

	private List<Board> boards;
	private List<CoupARetenir> coupARetenir;
	private Board premierParent;
	private boolean roiBlancDevore;
	private boolean roiNoirDevore;
	
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
	
	public void addBoards(Board[] boards) {
		for(Board board : boards){
			this.boards.add(board);
		}
	}
	
	public List<CoupARetenir> getCoupARetenir() {
		return coupARetenir;
	}
	
	public void setCoupARetenir(List<CoupARetenir> coupARetenir) {
		this.coupARetenir = coupARetenir;
	}

	public void addCoupARetenir(CoupARetenir coupARetenir) {
		if(this.coupARetenir == null){
			this.coupARetenir = new ArrayList<>();
			this.coupARetenir.add(coupARetenir);
		}else{
			this.coupARetenir.add(coupARetenir);
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
		boards.clear();
		coupARetenir = null;	
		roiBlancDevore = false;
		roiNoirDevore = false;
	}

	public boolean isRoiBlancDevore() {
		return roiBlancDevore;
	}

	public void setRoiBlancDevore(boolean roiBlancDevore) {
		this.roiBlancDevore = roiBlancDevore;
	}

	public boolean isRoiNoirDevore() {
		return roiNoirDevore;
	}

	public void setRoiNoirDevore(boolean roiNoirDevore) {
		this.roiNoirDevore = roiNoirDevore;
	}
	
}
