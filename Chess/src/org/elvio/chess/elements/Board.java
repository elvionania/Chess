package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.elvio.chess.util.BoardUtils;

public class Board {

	private byte[] board;
	private List<Board> boardAEvaluer;
	private List<Byte> positionsAttaquees;
	
	private Board premierParent = null;
	
	public Board(){
		board = new byte[64];
	}
	
	public void initialisation(){
		for(byte i = 0 ; i < 64 ; i++){
			board[i] = 0;
		}
		BoardUtils.miseEnPlaceDesPieces(this);
	}

	public final void put(Byte position, Byte etat){
		if(etat == null){
			board[position] = 0;
		}else{
			board[position] = etat;
		}
	}
	
	public final Byte get(Byte position){
		if(position == null){
			return null;
		}
		if(board[position] == 0){
			return null;
		}
		return board[position];		
	}
	
	public final Byte get2(int position){
		if(board[position] == 0){
			return null;
		}
		return board[position];		
	}
	
	public final void remove(Byte position){
		board[position] = 0;
	}
		
	public final Collection<Byte> getPieces(){
		ArrayList<Byte> liste = new ArrayList<>();
		for(Byte piece : board){
			if(piece != 0){
				liste.add(piece);
			}
		}
		return liste;
	}
	
	public final boolean isEnFinale(){
		
		byte damev = Dame.getValueStatic();
		byte tourv = Tour.getValueStatic();
		byte fouv = Fou.getValueStatic();
		byte cavalierv = Cavalier.getValueStatic();
		
		boolean dame = false;
		boolean tour = false;
		boolean cavalier = false; 
		boolean fou = false;
		
		for(byte piece : board){
			if(Piece.isComme(piece, damev)){
				dame = true;
			}else if(Piece.isComme(piece, tourv)){
				tour = true;
			}else if(Piece.isComme(piece, fouv)){
				fou = true;
			}else if(Piece.isComme(piece, cavalierv)){
				cavalier = true;
			}
		}
		return (dame && cavalier && !fou && !tour)||(dame && !cavalier && fou && !tour)||(!dame);
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
	
	public final void reInitBoardAVisualiser() {
		boardAEvaluer = new ArrayList<>();
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
		return getPieces().size();
	}
	
	public final Board clone(){
		Board clone = new Board();
		clone.board = this.board.clone();
		if(this.getPremierParent() != null){
			clone.setPremierParent(this.getPremierParent().cloneSansParent());
		}
		return clone;
	}

	private final Board cloneSansParent() {
		Board clone = new Board();
		clone.board = this.board.clone();
		return clone;
	}
	
	public String toString(){
		return Arrays.toString(board);
	}
	
	public List<Byte> getPositionsAttaquees() {
		return positionsAttaquees;
	}

	public void setPositionsAttaquees(List<Byte> positionsAttaquees) {
		this.positionsAttaquees = positionsAttaquees;
	}

	public long[] toLong(){
		long[] resultat = new long[5];
		long piece1 = 0;
		long piece2 = 0;
		int correcteur2 = 16; 
		long piece3 = 0;
		int correcteur3 = 32; 
		long piece4 = 0;
		int correcteur4 = 48; 
		long positions = 0;
		
		for(int position = 0 ; position < 64 ; position++){
			if(board[position] != 0){
				if(position < 16){
					piece1 += ((1 << position) * board[position]); 
				}else if (position > 15 && position <32){
					piece2 += ((1 << (position-correcteur2)) * board[position]);
				}else if (position > 31 && position < 48){
					piece3 += ((1 << (position-correcteur3)) * board[position]);
				}else{
					piece4 += ((1 << (position-correcteur4)) * board[position]);
				}
				positions += (1 << position);
			}
		}
		
		resultat[0] = piece1;
		resultat[1] = piece2;
		resultat[2] = piece3;
		resultat[3] = piece4;
		resultat[4] = positions;
		
		return resultat;
	}
	
}
