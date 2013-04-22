package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<Byte> getPositionsAttaques(Byte maPosition, Byte piece, Board board) {
		return getPositionsJouables(maPosition, piece, board);
	}

	public static List<Byte> getPositionsJouables(Byte maPosition, Byte piece, Board board) {
		List<Byte> cheminsJouables = getPositionsLibreSurLesChemins(maPosition, piece, getCheminsJouables(maPosition, board).get(0), board);
		getPrisesNormales(maPosition, Piece.M_UN, board, cheminsJouables);
		getPrisesNormales(maPosition, Piece.UN, board, cheminsJouables);
		cheminsJouables.addAll(getPrisesEnPassant(maPosition, piece, board));
		return cheminsJouables;
	}
	
	public static int getMobilite(Byte caseCourante, Byte piece, Board board) {
		int mobilite = getPositionsLibreSurLesCheminsM(caseCourante, piece, getCheminsJouables(caseCourante, board).get(0), board);
		mobilite += getPrisesNormalesM(caseCourante, board);
		mobilite += getPrisesEnPassantM(caseCourante, piece, board);
		return mobilite;
	}
	
	private static int getPrisesEnPassantM(Byte maPosition, Byte maPiece, Board board) {
		int mobilite = 0;
		if(isCePionEstUnePriseEnPassantPossible(maPosition, board, Piece.M_UN)){
			mobilite++;
		}
		if(isCePionEstUnePriseEnPassantPossible(maPosition, board, Piece.UN)){
			mobilite++;
		}
		return mobilite;
	} 
	
	private static int getPrisesNormalesM(Byte maPosition, Board board) {
		if(BoardUtils.isPieceAdverseAtPosition(BoardUtils.getPosition(maPosition, Piece.M_UN, avancer(Piece.UN, board.get(maPosition))), maPosition, board)){
			if(BoardUtils.isPieceAdverseAtPosition(BoardUtils.getPosition(maPosition, Piece.UN, avancer(Piece.UN, board.get(maPosition))), maPosition, board)){
				return 2;
			}
			return 1;
		}else{
			if(BoardUtils.isPieceAdverseAtPosition(BoardUtils.getPosition(maPosition, Piece.UN, avancer(Piece.UN, board.get(maPosition))), maPosition, board)){
				return 1;
			}
		}
		return 0;
	}

	private static void getPrisesNormales(Byte maPosition, byte cote, Board board, List<Byte> cheminsJouables) {
		Byte positionAdverse = BoardUtils.getPosition(maPosition, cote, avancer(Piece.UN, board.get(maPosition)));
		if(BoardUtils.isPieceAdverseAtPosition(positionAdverse, maPosition, board)){
			cheminsJouables.add(positionAdverse);
		}		
	}
	
	private static List<Byte> getPrisesEnPassant(Byte maPosition, Byte maPiece, Board board) {
		List<Byte> prisesJouables = new ArrayList<Byte>();
		if(isCePionEstUnePriseEnPassantPossible(maPosition, board, Piece.M_UN)){
			prisesJouables.add(BoardUtils.getPosition(maPosition, Piece.M_UN, avancer(Piece.UN, maPiece)));
		}
		if(isCePionEstUnePriseEnPassantPossible(maPosition, board, Piece.UN)){
//			System.out.println("pep " + maPosition + "  " + BoardUtils.getPosition(maPosition, (byte) -1, Piece.ZERO));
			prisesJouables.add(BoardUtils.getPosition(maPosition, Piece.UN, avancer(Piece.UN, maPiece)));
		}
		return prisesJouables;
	} 
	
	private static boolean isCePionEstUnePriseEnPassantPossible(Byte maPosition, Board board, byte cote){
		Byte position = BoardUtils.getPosition(maPosition, cote, Piece.ZERO);
		
		if( position == null ){
			return false;
		}
		
		Byte piece = board.get(position);
		
		if ( piece == null ){
			return false;		
		}
		
		if(!Piece.isComme(piece, Pion.getValueStatic())){
			return false;
		}
		
		Byte maPiece = board.get(maPosition);
		
		if(Piece.isMemeCouleur(maPiece, piece)){
			return false;
		}
		
		if(aAvanceDeDeuxCases(piece)){
			return true;
		}
		
		return false;
	}
	
	
	private static boolean aAvanceDeDeuxCases(Byte pion) {
		if(pion != null){
			return BoardUtils.memeEtat(pion, A_AVANCER_DE_2);
		}
		return false;
	}

	protected static List<List<Byte>> getCheminsJouables(Byte maPosition, Board board) {
		List<List<Byte>> resultats = new ArrayList<List<Byte>>();
		List<Byte> cheminsJouables = new ArrayList<Byte>();
		
		byte etat = board.get(maPosition);
		
		//pion avance de un
		byte nouvellePosition = BoardUtils.getPosition(maPosition, Piece.ZERO, avancer(Piece.UN, etat));
		if(Piece.neChangePasDeColonne(maPosition, nouvellePosition)){
			cheminsJouables.add(nouvellePosition);
		}
		//pion avance de deux
		nouvellePosition++;
		if(BoardUtils.isEnPositionInitial(etat) && Piece.neChangePasDeColonne(maPosition, nouvellePosition)){
			cheminsJouables.add(nouvellePosition);
		}
		
		resultats.add(cheminsJouables);
		
		return resultats;
	}
	
	protected static CoupsJouables getPositionsLibreSurLesChemins2(Byte maPosition, Byte piece, byte position, Board board){
		if(board.get(position) == null){
			return CoupsJouables.LIBRE;
		}else{
			return CoupsJouables.OCCUPE;
		}
}

	public static boolean isUnPionEnMarcheEnAvant(Byte position, Byte maPosition,
			Byte maPiece, Board board) {
		if(Piece.isComme(maPiece, getValueStatic()) && Math.abs(maPosition-position) <= Piece.DEUX){
			return true;
		}
		return false;
	}

	public static byte getValueStaticBlanc() {
		return valueStaticBlanche;
	}

}
