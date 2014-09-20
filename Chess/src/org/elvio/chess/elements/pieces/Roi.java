package org.elvio.chess.elements.pieces;

import org.elvio.chess.elements.pieces.Piece;
import java.util.ArrayList;
import java.util.List;
import org.elvio.chess.elements.Board;

import org.elvio.chess.util.BoardUtils;
import org.elvio.chess.util.Regles;

public class Roi extends Piece {
	
	static final byte valueStatic = 0b00011000;
	
	public Roi(byte couleur) {
		super(couleur);
	}
	
	public final static byte getValueStatic(){
		return valueStatic;
	}
	
	public static List<Integer> getPositionsAttaques(int maPosition, Byte piece, Board board) {
		return getCheminsDAttaque(maPosition, piece, board);
	}

    /**
     * donne les coups jouables par le roi
     * @param maPosition
     * @param piece
     * @param board
     * @return
     */
	public static List<Integer> getPositionsJouables(int maPosition, Byte piece, Board board) {
		
		List<Integer> listeDesDeplacementsPossibles = getDeplacementsPossibles(maPosition, piece, board);
        List<Integer> resultat = new ArrayList<>();

        for(int position : listeDesDeplacementsPossibles){
            if (!Regles.isCaseEnEchec(position, piece, board)){
                resultat.add(position);
            }
        }

		return resultat;
	}

    /**
     * donne les chemins possibles (pas forcement legaux) enrichis des possibilités de roque
     * @param maPosition
     * @param piece
     * @param board
     * @return
     */
    private static List<Integer> getDeplacementsPossibles(int maPosition, Byte piece, Board board){
        List<Integer> listeDesDeplacementsPossibles = getCheminsJouables(maPosition, piece, board);

        int petitRoque = getPetitRoque(maPosition, piece, board);
        int grandRoque = getGrandRoque(maPosition, piece, board);

        if(petitRoque != -1){
            listeDesDeplacementsPossibles.add(petitRoque);
        }
        if(grandRoque != -1){
            listeDesDeplacementsPossibles.add(grandRoque);
        }

        return listeDesDeplacementsPossibles;
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
		List<Integer> resultat = new ArrayList<>();

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
		List<Integer> resultat = new ArrayList<>();

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

    /**
     * donne la position du roi en cas de grand roque possible, sinon -1
     * @param maPosition
     * @param piece
     * @param board
     * @return
     */
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

    /**
     * retourne true si le paramètre est un Roi
     * @param etat
     * @return
     */
     public final static boolean isComme(Byte etat) {
		return ((etat & valueStatic) == valueStatic);
	 }

}
