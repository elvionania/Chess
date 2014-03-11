package org.elvio.chess.process;


import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Joueur;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.eval.algo.PS2;
import org.elvio.chess.util.BoardUtils;
import org.elvio.chess.util.Outils;
import org.elvio.chess.util.Regles;

public class Jeu {

        private int tempsEnMinute;
	private Board jeuDEchec;
	private Joueur joueurBlanc;
	private Joueur joueurNoir;
        
	public Jeu(int tempsEnMinute){
		jeuDEchec = new Board();
		jeuDEchec.initialisation();
                this.tempsEnMinute = tempsEnMinute;
	}
	
	// determine qui est le blanc
	
	private void initialisationDesJoueurs() {
		
//		if(0.5 <= Math.random()){
            joueurBlanc = new IntelligenceArtificielle(5, new PS2(), tempsEnMinute);
            joueurBlanc.setCouleur(Piece.BLANC);
            joueurNoir = new IntelligenceArtificielle(3, new PS2(), tempsEnMinute);
            joueurNoir.setCouleur(Piece.NOIR);
//		}else{
//			joueur1.setCouleur(Piece.NOIR);
//			joueur2.setCouleur(Piece.BLANC);
//			joueurBlanc = joueur2;
//			joueurNoir = joueur1;
//		}
	}
	
	// commence la partie
	public void partieCommence(){
		initialisationDesJoueurs();
		
		BoardUtils.montrerLeBoard(jeuDEchec);
                
                int etatDeLaPartie = Regles.PARTIE_NON_FINIE;
		int numeroDuCoup = 0;
                long tempsAuCommencement = Outils.getTime();			
                joueurBlanc.setTempsAuCommencement(tempsAuCommencement);
                joueurNoir.setTempsAuCommencement(tempsAuCommencement);
                
		while(etatDeLaPartie != Regles.PARTIE_NON_FINIE){
                    etatDeLaPartie = joueurJoue(numeroDuCoup, joueurBlanc);
                    if(etatDeLaPartie != Regles.PARTIE_NON_FINIE){
                        break;
                    }
                        
                    BoardUtils.montrerLeBoard(jeuDEchec);
                    
                    etatDeLaPartie = joueurJoue(numeroDuCoup, joueurBlanc);
                    if(etatDeLaPartie != Regles.PARTIE_NON_FINIE){
                        break;
                    }
                        
                    BoardUtils.montrerLeBoard(jeuDEchec);			
		}
                
            finalisation(etatDeLaPartie);
		
	}

        private int joueurJoue(int numeroDuCoup, Joueur joueur) {
            System.out.println((Piece.isBlanc(joueur.getCouleur())?"blanc ":"noir ") + "va joue");
            joueur.setTempsCourant(Outils.getTime());        
            Regles.initialisationDesPrisesEnPassant(joueur.getCouleur(), jeuDEchec);
            
            Board board = joueur.jouer(jeuDEchec, numeroDuCoup);

                // sortir qui est mate!!!
                if(board == null){
                    if(Piece.isBlanc(joueur.getCouleur())){
                        return Regles.PARTIE_GAGNEE_PAR_LES_NOIRS;
                    }else{
                        return Regles.PARTIE_GAGNEE_PAR_LES_BLANCS;
                    }
                }

                jeuDEchec = board;

                if(Regles.isMate(joueur.getCouleurDeLAutreJoueur(), jeuDEchec)){
                    if(Piece.isBlanc(joueur.getCouleur())){
                        return Regles.PARTIE_GAGNEE_PAR_LES_BLANCS;
                    }else{
                        return Regles.PARTIE_GAGNEE_PAR_LES_NOIRS;
                    }
                }
                
                if(Regles.isNulle(joueur.getCouleur(), jeuDEchec) ){
                    return Regles.PARTIE_NULLE;
                }

                return Regles.PARTIE_NON_FINIE;

        }
        
        private void finalisation(java.lang.Integer etatDeLaPartie) {
            switch(etatDeLaPartie){
                case Regles.PARTIE_NULLE: 
                    System.out.println("La partie est nulle");
                    break;
                case Regles.PARTIE_GAGNEE_PAR_LES_BLANCS:
                    System.out.println("Blancs sont mat");    
                    break;
                case Regles.PARTIE_GAGNEE_PAR_LES_NOIRS:
                    System.out.println("Noirs sont mat");    
                    break;
            }
        }

}
