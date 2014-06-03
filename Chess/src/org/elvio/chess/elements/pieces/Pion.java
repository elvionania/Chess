package org.elvio.chess.elements.pieces;

import org.elvio.chess.elements.pieces.Piece;
import java.util.ArrayList;
import java.util.List;
import org.elvio.chess.elements.Board;

import org.elvio.chess.util.BoardUtils;
import org.elvio.chess.util.CoupsJouables;

public class Pion extends Piece {
	
	public static final String valeurBinaire = "01100000";
	public static final String valeurBinaireBlanche = "01100001";
	static final byte valueStatic = (new Integer(Integer.parseInt(valeurBinaire,2))).byteValue();
	static final byte valueStaticBlanche = (new Integer(Integer.parseInt(valeurBinaireBlanche,2))).byteValue();
	
	public Pion(byte couleur) {
		super(couleur);
	}
	
	public final static byte getValueStatic(){
		return valueStatic;
	}
	
	public static List<Integer> getPositionsAttaques(int maPosition, Byte maPiece, Board board) {
		List<Integer> cheminsAttaquables = new ArrayList<>();
		int avance = avancer(1, maPiece);
		int positionAdverse = BoardUtils.getPosition(maPosition, 1, avance);
		cheminsAttaquables.add(positionAdverse);
		positionAdverse = BoardUtils.getPosition(maPosition, -1, avance);
		cheminsAttaquables.add(positionAdverse);
		
		return cheminsAttaquables;
	}

	public static List<Integer> getPositionsJouables(int maPosition, Byte piece, Board board) {
		List<Integer> cheminsJouables = getCheminsJouables(maPosition, piece, board);
		getPrisesNormales(maPosition, board, cheminsJouables);
		getPrisesEnPassant(maPosition, piece, board, cheminsJouables);
		return cheminsJouables;
	}

	private static void getPrisesNormales(int maPosition, Board board, List<Integer> cheminsJouables) {
		int avance = avancer(1, board.get(maPosition));
		int positionAdverse = BoardUtils.getPosition(maPosition, 1, avance);
		if(BoardUtils.isPieceAdverseAtPosition(positionAdverse, maPosition, board)){
			cheminsJouables.add(positionAdverse);
		}		
		positionAdverse = BoardUtils.getPosition(maPosition, -1, avance);
		if(BoardUtils.isPieceAdverseAtPosition(positionAdverse, maPosition, board)){
			cheminsJouables.add(positionAdverse);
		}
	}
	
	private static void getPrisesEnPassant(int maPosition, Byte maPiece, Board board, List<Integer> cheminsJouables) {
		if(isCePionEstUnePriseEnPassantPossible(maPosition, maPiece, board, -1)){
			cheminsJouables.add(BoardUtils.getPosition(maPosition, -1, avancer(1, maPiece)));
		}
		if(isCePionEstUnePriseEnPassantPossible(maPosition, maPiece, board, 1)){
			cheminsJouables.add(BoardUtils.getPosition(maPosition, 1, avancer(1, maPiece)));
		}
	} 
	
	private static boolean isCePionEstUnePriseEnPassantPossible(int maPosition, byte maPiece, Board board, int cote){
			int position = BoardUtils.getPosition(maPosition, cote, 0);
			
			if( position == -1 ){
				return false;
			}
			
			Byte piece = board.get(position);
			
			if ( piece == null ){
				return false;		
			}
			
			if(!Piece.isComme(piece, Pion.getValueStatic())){
				return false;
			}
			
			if(Piece.isMemeCouleur(maPiece, piece)){
				return false;
			}
			
			if(BoardUtils.memeEtat(piece, A_AVANCER_DE_2)){
				return true;
			}
			
			return false;
//		}
	}
	
	
	protected static List<Integer> getCheminsJouables(int maPosition, Byte etat, Board board) {
		List<Integer> cheminsJouables = new ArrayList<Integer>();
		
		//pion avance de un
		int nouvellePosition = 0;
		try{
			nouvellePosition = BoardUtils.getPosition(maPosition, Piece.ZERO, avancer(1, etat));
		}catch(Exception e){
			System.out.println("maposition "+maPosition);
			System.out.println("etat "+etat);
			System.out.println("avancer "+avancer(1, etat));
		}
		if(Piece.neChangePasDeColonne(maPosition, nouvellePosition) && getPositionsLibreSurLesChemins(nouvellePosition, board)==CoupsJouables.LIBRE){
			cheminsJouables.add(nouvellePosition);
			//pion avance de deux
			nouvellePosition += avancer(1, etat);
			if(BoardUtils.isEnPositionInitial(etat) && getPositionsLibreSurLesChemins(nouvellePosition, board)==CoupsJouables.LIBRE){
				cheminsJouables.add(nouvellePosition);
			}
		}
		
		return cheminsJouables;
	}
	
	protected static CoupsJouables getPositionsLibreSurLesChemins(int position, Board board){
		if(board.get(position) == null){
			return CoupsJouables.LIBRE;
		}else{
			return CoupsJouables.OCCUPE;
		}
	}

	public static byte getValueStaticBlanc() {
		return valueStaticBlanche;
	}
        
        public final static boolean isComme(Byte etat) {
		return ((etat & valueStatic) == valueStatic);
	}
	
}
