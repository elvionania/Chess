package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.List;

import org.elvio.chess.util.BoardUtils;

public class Roi extends Piece {
	
	static final String valeurBinaire = "00011000";
	static final byte valueStatic = (new Integer(Integer.parseInt(valeurBinaire,2))).byteValue();
	
	public Roi(byte couleur) {
		super(couleur);
	}
	
	public final static byte getValueStatic(){
		return valueStatic;
	}
	
	public static List<Byte> getPositionsAttaques(Byte maPosition, Byte piece, Board board) {
		List<Byte> listeDesPositionsJouables = new ArrayList<Byte>();		
		List<List<Byte>> chemins = getCheminsJouables(maPosition, board);
		
		for(List<Byte> chemin : chemins){
			listeDesPositionsJouables.addAll(getPositionsLibreSurLesChemins(maPosition, piece, chemin, board));
		}
		
		return listeDesPositionsJouables;
	}

	public static List<Byte> getPositionsJouables(Byte maPosition, Byte piece, Board board) {
		
		List<Byte> listeDesPositionsJouables = getPositionsAttaques(maPosition, board);
		
		Byte petitRoque = getPetitRoque(maPosition, piece, board);
		Byte grandRoque = getGrandRoque(maPosition, piece, board);
		if(petitRoque != null){
			listeDesPositionsJouables.add(petitRoque);
		}
		if(grandRoque != null){
			listeDesPositionsJouables.add(grandRoque);
		}
		return listeDesPositionsJouables;
	}

	protected static List<List<Byte>> getCheminsJouables(Byte maPosition, Board board) {
		List<List<Byte>> resultat = new ArrayList<List<Byte>>();

		addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.UN, Piece.ZERO));		
		addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.ZERO));
		
		if(maPosition%8 != 0){
			addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.UN, Piece.M_UN));
			addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.ZERO, Piece.M_UN));		
			addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.M_UN));
		}
		
		if(maPosition%8 != 7){
			addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.UN, Piece.UN));		
			addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.ZERO, Piece.UN));
			addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.UN));
		}
				
		return resultat;
	}

	private static void addElementInResultat(List<List<Byte>> resultat,
			Byte position){
		if(position != null){
			List<Byte> listeDesPositionsJouables = new ArrayList<Byte>();
			listeDesPositionsJouables.add(position);
			resultat.add(listeDesPositionsJouables);
		}
	}
	
	private static Byte getPetitRoque(Byte maPosition, Byte piece, Board board){
		if(BoardUtils.memeEtat(piece, A_DEJA_JOUE)){
			return null;
		}
		
		Byte positionInterstice2 = (byte) (maPosition + 2 * BoardUtils.NBRE_CASES_COLONNE);
		if(board.get(positionInterstice2)!=null){
			return null;
		}
		
		Byte positionInterstice1 = (byte) (maPosition + 1 * BoardUtils.NBRE_CASES_COLONNE);
		if(board.get(positionInterstice1)!=null){
			return null;
		}
		
		Byte positionDeLaTour = (byte) (maPosition + 3 * BoardUtils.NBRE_CASES_COLONNE);
		Byte tour = board.get(positionDeLaTour);
		if(tour == null){
			return null;
		}
		if(BoardUtils.memeEtat(tour, A_DEJA_JOUE)){
			return null;
		}
		
		Byte couleur = getCouleur(piece);
		if(BoardUtils.isCaseEnEchec(positionInterstice2, couleur, board)){
			return null;
		}
		if(BoardUtils.isCaseEnEchec(maPosition, couleur, board)){
			return null;
		}
		if(BoardUtils.isCaseEnEchec(positionInterstice1, couleur, board)){
			return null;
		}
		
		return (byte) (2*BoardUtils.NBRE_CASES_COLONNE + maPosition);
	}
	
	private static Byte getGrandRoque(Byte maPosition, Byte piece, Board board){

		if(BoardUtils.memeEtat(piece, A_DEJA_JOUE)){
			return null;
		}
		
		Byte positionInterstice3 = (byte) (maPosition - 3 * BoardUtils.NBRE_CASES_COLONNE);
		if(board.get(positionInterstice3)!=null){
			return null;
		}
		
		Byte positionInterstice2 = (byte) (maPosition - 2 * BoardUtils.NBRE_CASES_COLONNE);
		if(board.get(positionInterstice2)!=null){
			return null;
		}
		
		Byte positionInterstice1 = (byte) (maPosition - BoardUtils.NBRE_CASES_COLONNE);
		if(board.get(positionInterstice1)!=null){
			return null;
		}
		
		Byte positionDeLaTour = (byte) (maPosition - 4 * BoardUtils.NBRE_CASES_COLONNE);
		Byte tour = board.get(positionDeLaTour);
		if(tour == null){
			return null;
		}
		if(BoardUtils.memeEtat(tour, A_DEJA_JOUE)){
			return null;
		}
		
		Byte couleur = getCouleur(piece);
		if(BoardUtils.isCaseEnEchec(positionInterstice1, couleur, board)){
			return null;
		}
		if(BoardUtils.isCaseEnEchec(maPosition, couleur, board)){
			return null;
		}
		if(BoardUtils.isCaseEnEchec(positionInterstice2, couleur, board)){
			return null;
		}
		if(BoardUtils.isCaseEnEchec(positionInterstice3, couleur, board)){
			return null;
		}
		
		return (byte) (maPosition - 2 * BoardUtils.NBRE_CASES_COLONNE);
	}

}
