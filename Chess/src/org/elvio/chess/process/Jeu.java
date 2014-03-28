package org.elvio.chess.process;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Joueur;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.eval.algo.PS2;
import org.elvio.chess.util.BoardUtils;
import org.elvio.chess.util.Outils;
import org.elvio.chess.util.Regles;
import org.elvio.chess.util.StatutPartie;

public class Jeu {

    private final int tempsEnMinute;
    private Board board;
    private Joueur joueurBlanc;
    private Joueur joueurNoir;
    private StatutPartie etatDeLaPartie;
    private int numeroDuCoup;
    private long tempsAuCommencement;

    public Jeu(int tempsEnMinute){
        board = new Board();
        board.initialisation();
        this.tempsEnMinute = tempsEnMinute;
    }

    /**
     * creation des joueurs, on leur donne leur temps de jeu
     * et leurs couleurs
     */
    private void initialisationDeLaPartieEtDesJoueurs() {
        
        etatDeLaPartie = StatutPartie.NON_FINIE;
        numeroDuCoup = 0;
        tempsAuCommencement = Outils.getTime();
        joueurBlanc = new IntelligenceArtificielle(5, new PS2(), tempsEnMinute, Piece.BLANC);
        joueurNoir = new IntelligenceArtificielle(3, new PS2(), tempsEnMinute, Piece.NOIR);
        joueurBlanc.setTempsAuCommencement(tempsAuCommencement);
        joueurNoir.setTempsAuCommencement(tempsAuCommencement);
        
        BoardUtils.montrerLeBoard(board);
    }

    // commence la partie
    public void partieCommence(){
        // decouper cette methode 
        // globaliser les variables
        initialisationDeLaPartieEtDesJoueurs();

        while(etatDeLaPartie == StatutPartie.NON_FINIE){
            joueurJoue(joueurBlanc);
            if(etatDeLaPartie != StatutPartie.NON_FINIE) break;

            joueurJoue(joueurNoir);
            if(etatDeLaPartie != StatutPartie.NON_FINIE) break;
        }

        finalisationDeLaPartie(etatDeLaPartie);
    }
    
    //compartimenter en 3 methodes cette methode
    private void joueurJoue(Joueur joueur) {
        // preparation du jeu et du joueur avant son coup
        initialisationAvantDeJouerLeCoup(joueur);
        // on joue le coup
        board = joueur.jouer(board, numeroDuCoup);
        BoardUtils.montrerLeBoard(board);	
        // on definit l'état de la partie après le coup
        etatDeLaPartie = finalisationApresAvoirJouerLecoup(joueur);
    }
    
    private void initialisationAvantDeJouerLeCoup(Joueur joueur){
        System.out.println((Piece.isBlanc(joueur.getCouleur())?"blanc ":"noir ") + "va joue");
        // on renseigne le joueur de l'heure
        joueur.setTempsCourant(Outils.getTime());  
        // les pions ayant avancé de deux cases étaient soumis à une prise en passant, 
        // au nouveau tour de jeu ils ne sont plus soumis à cette règle
        Regles.initialisationDesPrisesEnPassant(joueur.getCouleur(), board);
    }
    
    private StatutPartie finalisationApresAvoirJouerLecoup(Joueur joueur){
        // si le board retourné est null, c'est que le joueur n'est plus en jeu vu son score impliquant une absence de roi
        if(board == null)                                           return savoirQuiAGagne(joueur, true);  
        // l'adversaire est il mat?
        if(Regles.isMate(joueur.getCouleurDeLAutreJoueur(), board)) return savoirQuiAGagne(joueur, false);
        // la partie est nulle?
        if(Regles.isNulle(joueur.getCouleur(), board) )             return StatutPartie.NULLE;
        // alors la partie continue!                                                               
        return StatutPartie.NON_FINIE;
    }
        
    /**
     * on affiche les resultats de la partie
     * @param etatDeLaPartie 
     */
    private void finalisationDeLaPartie(StatutPartie etatDeLaPartie) {
        switch(etatDeLaPartie){
            case NULLE: 
                System.out.println("La partie est nulle");
                break;
            case GAGNEE_PAR_LES_BLANCS:
                System.out.println("Noirs sont mat");    
                break;
            case GAGNEE_PAR_LES_NOIRS:
                System.out.println("Blancs sont mat");    
                break;
        }
    }

    // faire un isblanc et isnoir sur le joueur
    private StatutPartie savoirQuiAGagne(Joueur joueur, boolean estMate) {
        if(Piece.isBlanc(joueur.getCouleur()) && estMate){
            return StatutPartie.GAGNEE_PAR_LES_NOIRS;
        }else{
            return StatutPartie.GAGNEE_PAR_LES_BLANCS;
        }
    }
}