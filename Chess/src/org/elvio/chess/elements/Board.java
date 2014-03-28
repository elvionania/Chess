package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.elvio.chess.util.BoardUtils;

public class Board {

	private byte[] board;
	private List<Board> boardAEvaluer;
	private List<Integer> positionsAttaquees;
	
	private Board premierParent = null;
	
	public Board(){
		board = new byte[64];
	}
	
	public Board(byte[] board){
		this.board = board;
	}
	
	public void initialisation(){
		for(int i = 0 ; i < 64 ; i++){
			board[i] = 0;
		}
		BoardUtils.miseEnPlaceDesPiecesTest(this);
	}

	public final void put(int position, Byte etat){
		if(etat == null){
			board[position] = 0;
		}else{
			board[position] = etat;
		}
	}
	
	public final Byte get(int position){
		if(position == -1){
			return null;
		}
		if(board[position] == 0){
			return null;
		}
		return board[position];		
	}
	
	public final void remove(int position){
		board[position] = 0;
	}
	
	public final boolean contains(byte etat){
		for(Byte piece : board){
			if(Piece.isComme(piece, etat)){
				return true;
			}
		}
		return false;
	}
		
	public final Collection<Byte> getPositions(){
		ArrayList<Byte> liste = new ArrayList<>();
		for(byte i = 0 ; i < BoardUtils.NBRE_CASES_BOARD ; i++){
			if(board[i] != 0){
				liste.add(i);
			}
		}
		return liste;
	}
	
	public Board getPremierParent() {
			return premierParent;
	}

	public void setPremierParent(Board premierParent) {
			this.premierParent = premierParent;
	}
	
	public void setPremierParentPremiereFois(Board parent) {
		if(parent.getPremierParent() == null){
			this.premierParent = this.cloneSansParent();
		}else{
			this.premierParent = parent.getPremierParent().cloneSansParent();
		}
	}

	public List<Board> getBoardAEvaluer() {
		return boardAEvaluer;
	}

	public void setBoardAEvaluer(List<Board> boardAEvaluer) {
		this.boardAEvaluer = boardAEvaluer;
	}

	public void addBoards(List<Board> boards) {
		if(boardAEvaluer == null){
			boardAEvaluer = new ArrayList<>();
		}
		boardAEvaluer.addAll(boards);		
	}

	public int getBoardSize() {
		int size = 0;
		for(Byte piece : board){
			if(piece != 0){
				size++;
			}
		}
		return size;
	}
	
	public final Board clone(){
		Board clone = new Board(this.board.clone());
		
		if(this.getPremierParent() != null){
			clone.setPremierParent(this.getPremierParent().cloneSansParent());
		}
		
		return clone;
	}

	private final Board cloneSansParent() {
		return new Board(this.board.clone());
	}
	
	public List<Integer> getPositionsAttaquees() {
		return positionsAttaquees;
	}

	public void setPositionsAttaquees(List<Integer> positionsAttaquees) {
		this.positionsAttaquees = positionsAttaquees;
	}
	
}
