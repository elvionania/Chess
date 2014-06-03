package org.elvio.chess.elements;

import org.elvio.chess.elements.pieces.Tour;
import org.elvio.chess.elements.pieces.Roi;
import org.elvio.chess.elements.pieces.Pion;
import org.elvio.chess.elements.pieces.Fou;
import org.elvio.chess.elements.pieces.Dame;
import org.elvio.chess.elements.pieces.Cavalier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.elvio.chess.util.BoardUtils;

public class Board extends IBoard{

    private final byte[] board;
    private List<Integer> positionsAttaquees;
    private Board premierCoupAJouer = null;
    private static ArrayList<Integer> listeDesPositionsAyantUnePiece = new ArrayList<>();
	
    /**
     * constructeur pour un nouveau board
     */
    public Board(){
            this.board = new byte[64];
    }

    /**
     * constructeur pour creer un board à partir d un board existant
     * @param board 
     */
    private Board(byte[] board){
            this.board = board;
    }

    /**
     * externalisation de la remise à zéro du board et de la mise en place des pieces
     */
    public void initialisation(){
            for(int i = 0 ; i < 64 ; i++){
                    board[i] = 0;
            }
            BoardUtils.miseEnPlaceDesPieces(this);
    }

    public final void put(int position, Byte etat){
            if(etat == null){
                    board[position] = 0;
            }else{
                    board[position] = etat;
            }
    }

    public final Byte get(int position){
            if(position == -1){
                    return null;
            }
            if(board[position] == 0){
                    return null;
            }
            return board[position];		
    }

    public final void remove(int position){
            board[position] = 0;
    }

    public final Collection<Integer> getPositionsDesPieces(){
            listeDesPositionsAyantUnePiece.clear();
            for(int i = 0 ; i < BoardUtils.NBRE_CASES_BOARD ; i++){
                    if(board[i] != 0){
                            listeDesPositionsAyantUnePiece.add(i);
                    }
            }
            return listeDesPositionsAyantUnePiece;
    }
    
    public Board getPremierCoupAJouer() {
                    return premierCoupAJouer;
    }

    public void setPremierCoupAJouer(Board premierCoupAJouer) {
                    this.premierCoupAJouer = premierCoupAJouer;
    }

    public void creationPremierCoupAJouer(Board parent) {
            if(parent.getPremierCoupAJouer() == null){
                    this.premierCoupAJouer = this.cloneSansPremierCoupAJouer();
            }else{
                    this.premierCoupAJouer = parent.getPremierCoupAJouer().cloneSansPremierCoupAJouer();
            }
    }

    public int getNombreDePiecesEnJeu() {
            int nbreDePiecesEnJeu = 0;
            for(Byte piece : board){
                    if(piece != 0){
                            nbreDePiecesEnJeu++;
                    }
            }
            return nbreDePiecesEnJeu;
    }

    public final Board clone(){
            Board clone = new Board(this.board.clone());

            if(this.getPremierCoupAJouer() != null){
                    clone.setPremierCoupAJouer(this.getPremierCoupAJouer().cloneSansPremierCoupAJouer());
            }

            return clone;
    }

    private Board cloneSansPremierCoupAJouer() {
        return new Board(this.board.clone());
    }

    public List<Integer> getPositionsAttaquees() {
            return positionsAttaquees;
    }
    
    public boolean isPositionAttaquee(int position){
        return positionsAttaquees.contains(position);
    }

    public void setPositionsAttaquees(List<Integer> positionsAttaquees) {
            this.positionsAttaquees = positionsAttaquees;
    }

    public void creationPieceTest(int position, byte etatDUnePiece){
        put(position, etatDUnePiece);
    }
    
    
    // TODO voir piece est important dans les getpositionsattaquees
    public List<Integer> getPositionsAttaques(int caseCourante) {
        Byte piece = this.get(caseCourante);
        if(Pion.isComme(piece)){
                return Pion.getPositionsAttaques(caseCourante, piece, this);
        }else if(Cavalier.isComme(piece)){
                return Cavalier.getPositionsAttaques(caseCourante, this);
        }else if(Fou.isComme(piece)){
                return Fou.getPositionsAttaques(caseCourante, piece, this);
        }else if(Tour.isComme(piece)){
                return Tour.getPositionsAttaques(caseCourante, piece, this);
        }else if(Roi.isComme(piece)){
                return Roi.getPositionsAttaques(caseCourante, piece, this);
        }else if(Dame.isComme(piece)){
                return Dame.getPositionsAttaques(caseCourante, piece, this);
        }
        return null;
    }
}
