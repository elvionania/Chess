package org.elvio.chess.elements.pieces;

import java.util.ArrayList;
import java.util.List;
import org.elvio.chess.elements.Board;

import org.elvio.chess.util.BoardUtils;

public class Cavalier extends Piece {

	public static final String valeurBinaire = "01010000";
	static final byte valueStatic = (new Integer(Integer.parseInt(valeurBinaire,2))).byteValue();
	
	public Cavalier(byte couleur) {
		super(couleur);
	}
	
	public final static byte getValueStatic(){
		return valueStatic;
	}

	public static List<Integer> getPositionsJouables(int maPosition, Byte piece, Board board) {
		return getCheminsJouables(maPosition, piece, board);
	}
	
	public static int getMobilite(int maPosition, Byte piece, Board board) {
		int mobilite = 0;		

		if(Piece.isDifferenteCouleur(piece, board.get(BoardUtils.getPosition(maPosition, 1, 2)))){
			mobilite++;
		}
		if(Piece.isDifferenteCouleur(piece, board.get(BoardUtils.getPosition(maPosition, 2, 1)))){
			mobilite++;
		}
		if(Piece.isDifferenteCouleur(piece, board.get(BoardUtils.getPosition(maPosition, -1, 2)))){
			mobilite++;
		}
		if(Piece.isDifferenteCouleur(piece, board.get(BoardUtils.getPosition(maPosition, 2, -1)))){
			mobilite++;
		}
		
		if(Piece.isDifferenteCouleur(piece, board.get(BoardUtils.getPosition(maPosition, 1, -2)))){
			mobilite++;
		}		
		if(Piece.isDifferenteCouleur(piece, board.get(BoardUtils.getPosition(maPosition, -2, 1)))){
			mobilite++;
		} 
		
		if(Piece.isDifferenteCouleur(piece, board.get(BoardUtils.getPosition(maPosition, -1, -2)))){
			mobilite++;
		}
		if(Piece.isDifferenteCouleur(piece, board.get(BoardUtils.getPosition(maPosition, -2, -1)))){
			mobilite++;
		}
		
		return mobilite;
	}
	
	public static List<Integer> getPositionsAttaques(int maPosition, Board board) {
		List<Integer> resultat = new ArrayList<>();
		
		addElementAttaqueInResultat(resultat, board, BoardUtils.getPosition(maPosition, 1, 2));		
		addElementAttaqueInResultat(resultat, board, BoardUtils.getPosition(maPosition, 2, 1));		
		addElementAttaqueInResultat(resultat, board, BoardUtils.getPosition(maPosition, -1, 2));
		addElementAttaqueInResultat(resultat, board, BoardUtils.getPosition(maPosition, 2, -1));
		addElementAttaqueInResultat(resultat, board, BoardUtils.getPosition(maPosition, 1, -2));		
		addElementAttaqueInResultat(resultat, board, BoardUtils.getPosition(maPosition, -2, 1));		
		addElementAttaqueInResultat(resultat, board, BoardUtils.getPosition(maPosition, -1, -2));
		addElementAttaqueInResultat(resultat, board, BoardUtils.getPosition(maPosition, -2, -1));
				
		return resultat;
	}

	protected static List<Integer> getCheminsJouables(int maPosition, Byte piece, Board board) {
		List<Integer> resultat = new ArrayList<>();
			
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, 1, 2));		
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, 2, 1));		
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, -1, 2));
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, 2, -1));
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, 1, -2));		
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, -2, 1));		
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, -1, -2));
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, -2, -1));
				
		return resultat;
	}

	private static void addElementInResultat(List<Integer> resultat, Byte piece,
			Board board, int position){
		if(position != -1 && (board.get(position) == null || Piece.isDifferenteCouleur(piece, board.get(position)))){
			resultat.add(position);
		}
	}
	
	private static void addElementAttaqueInResultat(List<Integer> resultat,
			Board board, int position){
		if(position != -1){
			resultat.add(position);
		}
	}
        
        public final static boolean isComme(Byte etat) {
		return ((etat & valueStatic) == valueStatic);
	}

}
