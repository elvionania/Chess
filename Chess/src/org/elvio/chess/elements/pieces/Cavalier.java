package org.elvio.chess.elements.pieces;

import java.util.ArrayList;
import java.util.List;
import org.elvio.chess.elements.Board;

import org.elvio.chess.util.BoardUtils;

/**
 * classe décrivant un cavalier et donnant ses actions et états spécifiques
 */
public class Cavalier extends Piece {

	private static byte valueStatic = 0b01010000;
	
	public Cavalier(byte couleur) {
		super(couleur);
	}

    /**
     * getter de la valeur binaire d'un cavalier
     * @return
     */
	public final static byte getValueStatic(){
		return valueStatic;
	}

    /**
     * retourne les positions jouables du cavalier
     * @param maPosition
     * @param piece
     * @param board
     * @return
     */
	public static List<Integer> getPositionsJouables(int maPosition, Byte piece, Board board) {
        List<Integer> resultat = new ArrayList<>();

        addPositionJouable(resultat, piece, board, BoardUtils.getPosition(maPosition, 1, 2));
        addPositionJouable(resultat, piece, board, BoardUtils.getPosition(maPosition, 2, 1));
        addPositionJouable(resultat, piece, board, BoardUtils.getPosition(maPosition, -1, 2));
        addPositionJouable(resultat, piece, board, BoardUtils.getPosition(maPosition, 2, -1));
        addPositionJouable(resultat, piece, board, BoardUtils.getPosition(maPosition, 1, -2));
        addPositionJouable(resultat, piece, board, BoardUtils.getPosition(maPosition, -2, 1));
        addPositionJouable(resultat, piece, board, BoardUtils.getPosition(maPosition, -1, -2));
        addPositionJouable(resultat, piece, board, BoardUtils.getPosition(maPosition, -2, -1));

        return resultat;
	}

    /**
     * donne la mobilite de la piece, necessaire pour l'evaluation
     * @param maPosition
     * @param piece
     * @param board
     * @return
     */
	public static int getMobilite(int maPosition, Byte piece, Board board) {
		int mobilite = isJouable(maPosition, piece, board, 1, 2);
        mobilite += isJouable(maPosition, piece, board, 2, 1);
        mobilite += isJouable(maPosition, piece, board, -1, 2);
        mobilite += isJouable(maPosition, piece, board, 2, -1);
        mobilite += isJouable(maPosition, piece, board, 1, -2);
        mobilite += isJouable(maPosition, piece, board, -2, 1);
        mobilite += isJouable(maPosition, piece, board, -1, -2);
        mobilite += isJouable(maPosition, piece, board, -2, -1);

		return mobilite;
	}

    /**
     * un increment de mobilité si on ne tombe pas sur une de nos pieces
     * @param maPosition
     * @param piece
     * @param board
     * @param col
     * @param ligne
     * @return
     */
    private static int isJouable(int maPosition, Byte piece, Board board, int col, int ligne){
        if(Piece.isDifferenteCouleur(piece, board.get(BoardUtils.getPosition(maPosition, col, ligne)))){
            return 1;
        }
        return 0;
    }

    /**
     * donne les positions que le cavalier attaquent
     * @param maPosition
     * @param board
     * @return
     */
	public static List<Integer> getPositionsAttaques(int maPosition, Board board) {
		List<Integer> resultat = new ArrayList<>();
		
		addPositionAttaque(resultat, board, BoardUtils.getPosition(maPosition, 1, 2));
		addPositionAttaque(resultat, board, BoardUtils.getPosition(maPosition, 2, 1));
		addPositionAttaque(resultat, board, BoardUtils.getPosition(maPosition, -1, 2));
		addPositionAttaque(resultat, board, BoardUtils.getPosition(maPosition, 2, -1));
		addPositionAttaque(resultat, board, BoardUtils.getPosition(maPosition, 1, -2));
		addPositionAttaque(resultat, board, BoardUtils.getPosition(maPosition, -2, 1));
		addPositionAttaque(resultat, board, BoardUtils.getPosition(maPosition, -1, -2));
		addPositionAttaque(resultat, board, BoardUtils.getPosition(maPosition, -2, -1));
				
		return resultat;
	}

    /**
     * ajoute une position jouable si la position existe et qu'elle est libre ou occupée par une pièce adverse
     * @param resultat
     * @param piece
     * @param board
     * @param position
     */
	private static void addPositionJouable(List<Integer> resultat, Byte piece,
                                           Board board, int position){
		if(position != -1 && (board.get(position) == null || Piece.isDifferenteCouleur(piece, board.get(position)))){
			resultat.add(position);
		}
	}

    /**
     * ajoute une position attaquée si la position existe
     * @param resultat
     * @param board
     * @param position
     */
	private static void addPositionAttaque(List<Integer> resultat,
                                           Board board, int position){
		if(position != -1){
			resultat.add(position);
		}
	}

    /**
     * retourne true si le paramètre est un Cavalier
     * @param etat
     * @return
     */
    public static boolean isComme(Byte etat) {
		return ((etat & valueStatic) == valueStatic);
	}

}
