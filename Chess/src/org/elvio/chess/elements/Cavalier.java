package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.List;

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

	public static List<Byte> getPositionsJouables(Byte maPosition, Byte piece, Board board) {
		List<Byte> listeDesPositionsJouables = new ArrayList<Byte>();		
		List<List<Byte>> chemins = getCheminsJouables(maPosition, board);
		
		for(List<Byte> chemin : chemins){
			listeDesPositionsJouables.addAll(getPositionsLibreSurLesChemins(maPosition,piece, chemin, board));
		}		
		
		return listeDesPositionsJouables;
	}
	
	public static int getMobilite(Byte maPosition, Byte piece, Board board) {
		int mobilite = 0;		

		if(Piece.isDifferenteCouleur(piece, BoardUtils.getPosition(maPosition, Piece.UN, Piece.DEUX))){
			mobilite++;
		}
		if(Piece.isDifferenteCouleur(piece, BoardUtils.getPosition(maPosition, Piece.DEUX, Piece.UN))){
			mobilite++;
		}
		if(Piece.isDifferenteCouleur(piece, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.DEUX))){
			mobilite++;
		}
		if(Piece.isDifferenteCouleur(piece, BoardUtils.getPosition(maPosition, Piece.DEUX, Piece.M_UN))){
			mobilite++;
		}
		
		if(Piece.isDifferenteCouleur(piece, BoardUtils.getPosition(maPosition, Piece.UN, Piece.M_DEUX))){
			mobilite++;
		}		
		if(Piece.isDifferenteCouleur(piece, BoardUtils.getPosition(maPosition, Piece.M_DEUX, Piece.UN))){
			mobilite++;
		} 
		
		if(Piece.isDifferenteCouleur(piece, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.M_DEUX))){
			mobilite++;
		}
		if(Piece.isDifferenteCouleur(piece, BoardUtils.getPosition(maPosition, Piece.M_DEUX, Piece.M_UN))){
			mobilite++;
		}
		
		return mobilite;
	}
	
	public static List<Byte> getPositionsAttaques(Byte maPosition, Byte piece, Board board) {
		return getPositionsJouables(maPosition, piece, board);
	}

	protected static List<List<Byte>> getCheminsJouables(Byte maPosition, Board board) {
		List<List<Byte>> resultat = new ArrayList<List<Byte>>();

		addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.UN, Piece.DEUX));		
		addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.DEUX, Piece.UN));		
		addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.DEUX));
		addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.DEUX, Piece.M_UN));
		addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.UN, Piece.M_DEUX));		
		addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.M_DEUX, Piece.UN));		
		addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.M_DEUX));
		addElementInResultat(resultat, BoardUtils.getPosition(maPosition, Piece.M_DEUX, Piece.M_UN));
				
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

}
