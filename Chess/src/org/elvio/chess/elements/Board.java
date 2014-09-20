package org.elvio.chess.elements;

import org.elvio.chess.elements.pieces.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.elvio.chess.util.BoardUtils;

/**
 * repésentation logique d'un board d'échec
 * s'appuyant sur une représentation en tableau de byte
 */
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
        miseAVideDuBoard();
        BoardUtils.miseEnPlaceDesPieces(this, null, false);
    }

    /**
     * externalisation de la remise à zéro du board et de la mise en place des pieces suivant une configuration
     * particuliere
     */
    public void initialisation(String configuration){
        miseAVideDuBoard();
        BoardUtils.miseEnPlaceDesPieces(this, configuration, false);
    }

    /**
     * externalisation de la remise à zéro du board et de la mise en place des pieces suivant une configuration
     * particuliere
     */
    public void initialisation(boolean configurationStatique){
        miseAVideDuBoard();
        BoardUtils.miseEnPlaceDesPieces(this, null, true);
    }

    /**
     * vide totalement un échèquier
     */
    private void miseAVideDuBoard() {
        for(int i = 0 ; i < 64 ; i++){
            board[i] = 0;
        }
    }

    /**
     * place une pièce sur l'échéquier
     * ou vide une case de l'échiquier
     * @param position
     * @param etat
     */
    public final void put(int position, Byte etat){
            if(etat == null){
                    board[position] = 0;
            }else{
                    board[position] = etat;
            }
    }

    /**
     * obtient le contenu d'une case de l'échiquier
     * retourne null en cas de position impossible ou de case vide
     *
     * @param position
     * @return
     */
    public final Byte get(int position){
            if(position == -1){
                    return null;
            }
            if(board[position] == 0){
                    return null;
            }
            return board[position];		
    }

    /**
     * vide une position
     * @param position
     */
    public final void remove(int position){
            board[position] = 0;
    }

    /**
     * retourne la liste des positions des pièces sur l'échéquier
     * @return
     */
    public final Collection<Integer> getPositionsDesPieces(){
        listeDesPositionsAyantUnePiece.clear();
        for(int i = 0 ; i < BoardUtils.NBRE_CASES_BOARD ; i++){
                if(board[i] != 0){
                        listeDesPositionsAyantUnePiece.add(i);
                }
        }
        return listeDesPositionsAyantUnePiece;
    }

    /**
     * retourne le premier à jouer sous forme du board d'alors
     * lors de la sélection de la meilleur variante, il s'agit du coup initiant cette variante
     * @return
     */
    public Board getPremierCoupAJouer() {
        return premierCoupAJouer;
    }

    /**
     * mémorise le premier coup à jouer sous forme du board d'alors
     * lors de la sélection de la meilleur variante, il s'agit du coup initiant cette variante
     * @param premierCoupAJouer
     */
    public void setPremierCoupAJouer(Board premierCoupAJouer) {
                    this.premierCoupAJouer = premierCoupAJouer;
    }

    /**
     * determine le le board du premier coup à jouer
     * lors de la sélection de la meilleur variante, il s'agit du coup initiant cette variante
     * @param parent
     */
    public void creationPremierCoupAJouer(Board parent) {
            if(parent.getPremierCoupAJouer() == null){
                    this.premierCoupAJouer = this.cloneSansPremierCoupAJouer();
            }else{
                    this.premierCoupAJouer = parent.getPremierCoupAJouer().cloneSansPremierCoupAJouer();
            }
    }

    /**
     * donne le nombre de pièces en jeu
     * @return
     */
    public int getNombreDePiecesEnJeu() {
            int nbreDePiecesEnJeu = 0;
            for(Byte piece : board){
                    if(piece != 0){
                            nbreDePiecesEnJeu++;
                    }
            }
            return nbreDePiecesEnJeu;
    }

    /**
     * duplication d'un board avec le souvenir du premier coup à jouer
     * sert pour l'évaluation
     * @return
     */
    public final Board clone(){
            Board clone = new Board(this.board.clone());

            if(this.getPremierCoupAJouer() != null){
                    clone.setPremierCoupAJouer(this.getPremierCoupAJouer().cloneSansPremierCoupAJouer());
            }

            return clone;
    }

    /**
     * duplication d'un board sans le souvenir du premier coup à jouer
     * sert dans la construction de l'arbre de parcours des possibilités
     * @return
     */
    private Board cloneSansPremierCoupAJouer() {
        return new Board(this.board.clone());
    }

    /**
     * retourne la liste des positions qui sont sous la possible occupation d'une pièce au prochain coup
     * @return
     */
    public List<Integer> getPositionsAttaquees() {
            return positionsAttaquees;
    }

    /**
     * indique si une position est sous la possible occupation d'une pièce au prochain coup
     * @param position
     * @return
     */
    public boolean isPositionAttaquee(int position){
        return positionsAttaquees.contains(position);
    }

    /**
     * enregistre la liste des positions qui sont sous la possible occupation d'une pièce au prochain coup
     * @param positionsAttaquees
     */
    public void setPositionsAttaquees(List<Integer> positionsAttaquees) {
            this.positionsAttaquees = positionsAttaquees;
    }

    /**
     * donne les positions attaquées par la pièce se trouvant à la position donnée
     * si la position ne contient pas de pièce, on retourne null
     * @param caseCourante
     * @return
     */
    public List<Integer> getPositionsAttaques(int caseCourante) {
        Byte piece = this.get(caseCourante);
        if(Pion.isComme(piece))             return Pion.getPositionsAttaques(caseCourante, piece, this);
        else if(Cavalier.isComme(piece))    return Cavalier.getPositionsAttaques(caseCourante, this);
        else if(Fou.isComme(piece))         return Fou.getPositionsAttaques(caseCourante, piece, this);
        else if(Tour.isComme(piece))        return Tour.getPositionsAttaques(caseCourante, piece, this);
        else if(Roi.isComme(piece))         return Roi.getPositionsAttaques(caseCourante, piece, this);
        else if(Dame.isComme(piece))        return Dame.getPositionsAttaques(caseCourante, piece, this);

        return null;
    }

    /**
     * indique si le roi est mangé
     * méthode pratique pour l'évaluation d'un mat
     * @param couleur
     * @return
     */
    public Boolean isRoiMort(byte couleur){
        for(int i = 0 ; i < BoardUtils.NBRE_CASES_BOARD ; i++){
            if(Roi.isComme(board[i]) && Piece.isMemeCouleur(couleur, board[i]))    return false;
        }
        return true;
    }
}
