/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.elvio.chess.util;

import java.util.HashMap;
import java.util.List;
import org.elvio.chess.elements.Board;
import org.elvio.chess.process.EtatDUnBoard;
import org.elvio.chess.elements.pieces.Piece;
import org.elvio.chess.elements.pieces.Pion;
import org.elvio.chess.elements.pieces.Roi;
import org.elvio.chess.eval.algo.PieceSquare;
import org.elvio.chess.process.NegaMax;

/**
 *
 * @author Elvio
 */
public class Regles {
    
    // les regles evalue si une partie est nulle, on a une classe les donnees dynamiques 
    // aucours de la partie
    public static PartieNulle evaluateurDePartieNulle = new PartieNulle();

    /**
     * valide si une position est attaquee par l adversaire
     * @param position de la piece
     * @param couleur de la piece
     * @param board
     * @return 
     */
    public static final boolean isCaseEnEchec(int position, Byte couleur, Board board) {
        List<Integer> positionsAttaquees = null;
        
        if (board.getPositionsAttaquees() == null) {
            trouverLesPositionsAttaquees(board, couleur, positionsAttaquees);
            board.setPositionsAttaquees(positionsAttaquees);
            
            return (positionsAttaquees != null && positionsAttaquees.contains(position));            
        } else return (board.isPositionAttaquee(position));
    }

    private static void trouverLesPositionsAttaquees(Board board, Byte couleur, List<Integer> positionsAttaquees) {
        for (int  positionCourante = 0; positionCourante < 64; positionCourante++) {
            if (Piece.isDifferenteCouleur(board.get(positionCourante), couleur)) {
                if (positionsAttaquees == null) {
                    positionsAttaquees = board.getPositionsAttaques(positionCourante);
                } else {
                    positionsAttaquees.addAll(board.getPositionsAttaques(positionCourante));
                }
            }
        }
    }

    
    // partie nulle a reinitialiser 
    private static boolean isNullePar50CoupsOu3PositionsIdentiques(Board board) {
        
        StringBuilder representationDunEchiquier = new StringBuilder();
        if (board.getNombreDePiecesEnJeu() != evaluateurDePartieNulle.getNbreDePieceSurLeGame()) {
            evaluateurDePartieNulle.setNbreDePieceSurLeGame(board.getNombreDePiecesEnJeu());
            evaluateurDePartieNulle.setCoupJouePourAnnulerEn50Coups(0);
            evaluateurDePartieNulle.setNbrePositionsIdentiques(new HashMap<String, Byte>());
        } else {
            for (int position : board.getPositionsDesPieces()) {
                representationDunEchiquier.append(position).append(board.get(position));
            }
            evaluateurDePartieNulle.incrementCoupJouePourAnnulerEn50Coups();
        }
        if (evaluateurDePartieNulle.getCoupJouePourAnnulerEn50Coups() > 49) {
            System.out.println("50 coups");
            return true;
        }
        if (evaluateurDePartieNulle.getNbrePositionsIdentiques(representationDunEchiquier.toString()) == null) {
            evaluateurDePartieNulle.putNbrePositionsIdentiques(representationDunEchiquier.toString(), (byte)1);
        }else{
            evaluateurDePartieNulle.putNbrePositionsIdentiques(representationDunEchiquier.toString(), (byte) (evaluateurDePartieNulle.getNbrePositionsIdentiques(representationDunEchiquier.toString()) + 1));
        }
        if (evaluateurDePartieNulle.getNbrePositionsIdentiques().values().contains(3)) {
            System.out.println("3 repetitions");
            return true;
        }
        return false;
    }

    public static boolean isRoiEnPat(Board board, byte couleur) {
        Byte piece;
        for (int position : board.getPositionsDesPieces()) {
            if (Piece.isMemeCouleur(board.get(position), couleur)) {
                piece = board.get(position);
                for (int coup : Piece.getPositionsJouables(position, piece, board)) {
                    EtatDUnBoard etatBoard = BoardUtils.getBoardApresUnCoup(position, piece, coup, board, null);
                    for (Board newBoard : etatBoard.getBoards()) {
                        for (int nbPosition : newBoard.getPositionsDesPieces()) {
                            byte pieceANbPosition = newBoard.get(nbPosition);
                            if (Roi.isComme(pieceANbPosition, Roi.getValueStatic(), couleur)) {
                                if (!Regles.isCaseEnEchec(nbPosition, newBoard.get(nbPosition), newBoard)) {
                                    BoardUtils.revenirAuBoardInitial(board, etatBoard);
                                    return false;
                                }
                            }
                        }
                    }
                    BoardUtils.revenirAuBoardInitial(board, etatBoard);
                }
            }
        }
        return true;
    }

    public static boolean isRoiEnEchec(Board board, byte couleur) {
        for (int position : board.getPositionsDesPieces()) {
            byte piece = board.get(position);
            if (Roi.isComme(piece, Roi.getValueStatic()) && Piece.isMemeCouleur(piece, couleur)) {
                if (Regles.isCaseEnEchec(position, couleur, board)) {
                    return true;
                }
            }
        }
        return false;
    }

    //TODO nulle si aucune pièce ne peut bouger sans mettre le roi en echec
    // ou si le roi bougeant se met en echec
    // pos = position du roi de la couleur voulue
    // // le roi est deja en echec
    // si (echec(pos))
    //      return false
    // for (toutes les pieces de la couleur voulue)
    //      piece <--
    //      for(tout mouvement de la piece)
    //              // on trouve un mouvement ne provoquant pas la mise en echec du roi
    //          si (board apres ce mouvement != echec(pos))
    //              return false
    // //on n a pas trouve de coup ne mettant pas en echecx le roi
    // return true
    public static final boolean isNulleParPat(byte couleur, Board board) {
        if (isRoiEnEchec(board, couleur)) {
            return false;
        }
        if (isRoiEnPat(board, couleur)) {
            board.setPositionsAttaquees(null);
            System.out.println("pat");
            return true;
        }
        return false;
    }

    public static final boolean isNulle(byte couleur, Board board) {
        return isNullePar50CoupsOu3PositionsIdentiques(board) || isNulleParPat(couleur, board);
    }

    public static final boolean isMate(Byte couleur, Board board) {
        board.setPositionsAttaquees(null);
        for (int position = 0; position < 64; position++) {
            if (board.get(position) != null && Piece.isComme(board.get(position), Roi.getValueStatic()) && Piece.isMemeCouleur(board.get(position), couleur)) {
                if (!Regles.isCaseEnEchec(position, couleur, board)) {
                    System.out.println("le roi "+(Piece.isBlanc(couleur)?"blanc":"noir") + " n'est pas en echec");
                    return false;
                }
            }
        }
        BoardEvalue evaluation = NegaMax.getBoardEvalue(2, board, couleur, 0, new PieceSquare(), null, false, null);
        System.out.println("eval mate " + evaluation.getScore());
        if (evaluation.getScore() < -100000.0) {
            System.out.println("pas bon pour le blanc");
        } else {
            System.out.println("correct blanc");
        }
        if (evaluation.getScore() > 100000.0) {
            System.out.println("pas bon pour le noir");
        } else {
            System.out.println("correct noir");
        }
        return Piece.isBlanc(couleur) && (evaluation.getScore() < -100000.0) || !Piece.isBlanc(couleur) && (evaluation.getScore() > 100000.0);
    }
    
    public static void initialisationDesPrisesEnPassant(byte couleur, Board board) {
            Byte piece;
            for(int i = 0 ; i < 64 ; i++){
                piece = board.get(i);
                if(Piece.isMemeCouleur(couleur, piece) && Pion.isComme(piece)){
                    board.put(i, Piece.nEstPasUnePriseEnPassant(piece));
                }
            }
        }
    
}
