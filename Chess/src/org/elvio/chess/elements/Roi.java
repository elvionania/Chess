package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.List;

import org.elvio.chess.util.BoardUtils;
import org.elvio.chess.util.Regles;

public class Roi extends Piece {
	
	static final String valeurBinaire = "00011000";
	static final byte valueStatic = (new Integer(Integer.parseInt(valeurBinaire,2))).byteValue();
	
	public Roi(byte couleur) {
		super(couleur);
	}
	
	public final static byte getValueStatic(){
		return valueStatic;
	}
	
	public static List<Integer> getPositionsAttaques(int maPosition, Byte piece, Board board) {
		return getCheminsDAttaque(maPosition, piece, board);
	}

	public static List<Integer> getPositionsJouables(int maPosition, Byte piece, Board board) {
		
		List<Integer> listeDesPositionsJouables = getCheminsJouables(maPosition, piece, board);
		
		int petitRoque = getPetitRoque(maPosition, piece, board);
		int grandRoque = getGrandRoque(maPosition, piece, board);
		if(petitRoque != -1){
			listeDesPositionsJouables.add(petitRoque);
		}
		if(grandRoque != -1){
			listeDesPositionsJouables.add(grandRoque);
		}
		return listeDesPositionsJouables;
	}
	

	public static int getMobilite(int maPosition, Byte piece, Board board) {
		int mobilite = 0;
		if(isElementInResultat(piece, board, BoardUtils.getPosition(maPosition, Piece.UN, Piece.ZERO))){
			mobilite++;		
		}
		if(isElementInResultat(piece, board, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.ZERO))){
			mobilite++;
		}
		if(isElementInResultat(piece, board, BoardUtils.getPosition(maPosition, Piece.UN, Piece.M_UN))){
			mobilite++;
		}
		if(isElementInResultat(piece, board, BoardUtils.getPosition(maPosition, Piece.ZERO, Piece.M_UN))){
			mobilite++;
		}
		if(isElementInResultat(piece, board, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.M_UN))){
			mobilite++;
		}
		if(isElementInResultat(piece, board, BoardUtils.getPosition(maPosition, Piece.UN, Piece.UN))){
			mobilite++;
		}
		if(isElementInResultat(piece, board, BoardUtils.getPosition(maPosition, Piece.ZERO, Piece.UN))){
			mobilite++;
		}
		if(isElementInResultat(piece, board, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.UN))){
			mobilite++;
		}
		
		return mobilite;
	}

	protected static List<Integer> getCheminsJouables(int maPosition, Byte piece, Board board) {
		List<Integer> resultat = new ArrayList<Integer>();

		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.UN, Piece.ZERO));		
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.ZERO));
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.UN, Piece.M_UN));
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.ZERO, Piece.M_UN));		
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.M_UN));
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.UN, Piece.UN));		
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.ZERO, Piece.UN));
		addElementInResultat(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.UN));
				
		return resultat;
	}
	
	// pour attaque que ce soit pour le roi, la reine, le fou, la tour et le cavalier, ce sont autant les positions attaquees que defendues, donc pieces de meme couleur incluse
	protected static List<Integer> getCheminsDAttaque(int maPosition, Byte piece, Board board) {
		List<Integer> resultat = new ArrayList<Integer>();

		addElementInResultatEnAttaque(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.UN, Piece.ZERO));		
		addElementInResultatEnAttaque(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.ZERO));
		addElementInResultatEnAttaque(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.UN, Piece.M_UN));
		addElementInResultatEnAttaque(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.ZERO, Piece.M_UN));		
		addElementInResultatEnAttaque(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.M_UN));
		addElementInResultatEnAttaque(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.UN, Piece.UN));		
		addElementInResultatEnAttaque(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.ZERO, Piece.UN));
		addElementInResultatEnAttaque(resultat, piece, board, BoardUtils.getPosition(maPosition, Piece.M_UN, Piece.UN));
				
		return resultat;
	}

	// en faire un sans echec pour les positions d attaques 
	
	private static void addElementInResultatEnAttaque(List<Integer> resultat, Byte piece, Board board,
			int position){		
		if(position != -1 ){
			resultat.add(position);
		}
	}
	
	private static void addElementInResultat(List<Integer> resultat, Byte piece, Board board,
			int position){		
		if(position != -1 && (board.get(position) == null || Piece.isDifferenteCouleur(piece, board.get(position))) && !Regles.isCaseEnEchec(position, piece, board)){
			resultat.add(position);
		}
	}
	
	private static boolean isElementInResultat(Byte piece, Board board,	int position){		
		if(position != -1 && (board.get(position) == null || Piece.isDifferenteCouleur(piece, board.get(position))) && !Regles.isCaseEnEchec(position, piece, board)){
			return true;
		}
		return false;
	}
	
	private static int getPetitRoque(int maPosition, Byte piece, Board board){
		if(BoardUtils.memeEtat(piece, A_DEJA_JOUE)){
			return -1;
		}
		
		int positionInterstice2 = maPosition + 16;
		if(board.get(positionInterstice2)!=null){
			return -1;
		}
		
		int positionInterstice1 = maPosition + 8;
		if(board.get(positionInterstice1)!=null){
			return -1;
		}
		
		int positionDeLaTour = maPosition + 24;
		Byte tour = board.get(positionDeLaTour);
		if(tour == null){
			return -1;
		}
		if(BoardUtils.memeEtat(tour, A_DEJA_JOUE)){
			return -1;
		}
		
		if(Regles.isCaseEnEchec(positionInterstice2, piece, board)){
			return -1;
		}
		if(Regles.isCaseEnEchec(maPosition, piece, board)){
			return -1;
		}
		if(Regles.isCaseEnEchec(positionInterstice1, piece, board)){
			return -1;
		}
		
		return (16 + maPosition);
	}
	
	private static int getGrandRoque(int maPosition, Byte piece, Board board){

		if(BoardUtils.memeEtat(piece, A_DEJA_JOUE)){
			return -1;
		}
		
		int positionInterstice3 = maPosition - 24;
		if(board.get(positionInterstice3)!=null){
			return -1;
		}
		
		int positionInterstice2 = maPosition - 16;
		if(board.get(positionInterstice2)!=null){
			return -1;
		}
		
		int positionInterstice1 = maPosition - 8;
		if(board.get(positionInterstice1)!=null){
			return -1;
		}
		
		int positionDeLaTour = maPosition - 32;
		Byte tour = board.get(positionDeLaTour);
		if(tour == null){
			return -1;
		}
		if(BoardUtils.memeEtat(tour, A_DEJA_JOUE)){
			return -1;
		}
		
		if(Regles.isCaseEnEchec(positionInterstice1, piece, board)){
			return -1;
		}
		if(Regles.isCaseEnEchec(maPosition, piece, board)){
			return -1;
		}
		if(Regles.isCaseEnEchec(positionInterstice2, piece, board)){
			return -1;
		}
		if(Regles.isCaseEnEchec(positionInterstice3, piece, board)){
			return -1;
		}
		
		return maPosition - 16;
	}

}
