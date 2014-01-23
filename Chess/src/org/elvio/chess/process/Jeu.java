package org.elvio.chess.process;

import java.util.GregorianCalendar;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Joueur;
import org.elvio.chess.elements.PartieNulle;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.elements.Pion;
import org.elvio.chess.eval.algo.PS2;
import org.elvio.chess.time.Temps;
import org.elvio.chess.util.BoardUtils;

public class Jeu {

	private Board jeuDEchec;
	private Joueur joueurBlanc;
	private Joueur joueurNoir;
        
        //hashMap couleur,roiMate
	private Boolean roiNoirMate;
	private Boolean roiBlancMate;
	private boolean partieNulle;
	private final PartieNulle evaluateurDePartieNulle;
	private final Temps tempsJoueur1;
        private final Temps tempsJoueur2;
        private final Joueur joueur1;
        private final Joueur joueur2;
	
	public Jeu(int tempsEnMinute){
		jeuDEchec = new Board();
		jeuDEchec.initialisation();
                
                tempsJoueur1 = new Temps(tempsEnMinute);
                tempsJoueur2 = new Temps(tempsEnMinute);
		
		evaluateurDePartieNulle = new PartieNulle();                
                //joueur1
                joueur1 = new IntelligenceArtificielle(4, new PS2(), tempsJoueur1);
                //joueur2
                joueur2 = new IntelligenceArtificielle(3, new PS2(), tempsJoueur2);
                //	Joueur joueur2 = new Humain();
	}
	
	// determine qui est le blanc
	
	private void determineCouleurDesJoueurs() {
		
//		if(0.5 <= Math.random()){
			joueur1.setCouleur(Piece.BLANC);
			joueur2.setCouleur(Piece.NOIR);
			joueurBlanc = joueur1;                     
			joueurNoir = joueur2;
//			System.out.println("pc blanc");
//		}else{
//			joueur1.setCouleur(Piece.NOIR);
//			joueur2.setCouleur(Piece.BLANC);
//			joueurBlanc = joueur2;
//			joueurNoir = joueur1;
//			System.out.println("tu es blanc");
//		}
	}
	
	// commence la partie
	public void joue(){
		determineCouleurDesJoueurs();
		
		BoardUtils.montrerLeBoard(jeuDEchec);
                
                boolean partieToujoursEnCours = true;
		int numeroDuCoup = 0;
                long tempsAuCommencement = getTime();
						
                joueurBlanc.setTempsAuCommencement(tempsAuCommencement);
                joueurNoir.setTempsAuCommencement(tempsAuCommencement);
                
		while(partieToujoursEnCours){
                    if (joueurJoue(numeroDuCoup, joueurBlanc)) {
                        break;
                    }		
                        
                    if (joueurJoue(numeroDuCoup, joueurNoir)) {
                        break;
                    }	
                        
                    BoardUtils.montrerLeBoard(jeuDEchec);			
		}
		
		if(roiBlancMate){
			System.out.println("Blancs sont mat");
		}
		
		if(roiNoirMate){
			System.out.println("Noirs sont mat");
		}
		
		if(partieNulle){
			System.out.println("La partie est nulle");
		}
		
	}

    // trop de decouplage inutile, defaire joueurJoue
    // utiliser Joueur.getCouleur
        
    private boolean joueurJoue(int numeroDuCoup, Joueur joueur) {
        long tempsInitial = getTime();
        System.out.println("blanc va joue");
        joueur.setTempsCourant(tempsInitial);        
        reInitPriseEnPassant(joueur.getCouleur());
        
        if (!joue(numeroDuCoup, joueur.getTemps(), joueur)) {
            return true;
        }
        
        afficherPuissanceDeCalcul(getTime(), tempsInitial);
        return false;
    }

    private void afficherPuissanceDeCalcul(long tempsFinal, long tempsInitial) {
        double nbreDePositionsCalculesPourLeBlanc;
        long denominateur = (tempsFinal-tempsInitial);
        nbreDePositionsCalculesPourLeBlanc = (IntelligenceArtificielle.boardCalcule*1000/denominateur!=0?denominateur:1l);
        System.out.println("a calcule "+nbreDePositionsCalculesPourLeBlanc + " board par seconde");
    }

	private void reInitPriseEnPassant(byte couleur) {
		for(int i = 0 ; i < 64 ; i++){
			if(jeuDEchec.get(i)!= null && Piece.isMemeCouleur(couleur, jeuDEchec.get(i)) && Piece.isComme(jeuDEchec.get(i), Pion.getValueStatic())){
				jeuDEchec.put(i, (byte) (jeuDEchec.get(i)&Piece.NA_PLUS_AVANCER_DE_2));
			}
		}
	}

	private boolean joue(int cpt, Temps temps, Joueur joueur) {
		Board board = joueur.jouer(jeuDEchec, cpt, temps);
                
		if(board == null){
                    if(joueur.getCouleur() == Piece.NOIR){
			roiNoirMate = true;
                    }else{
                        roiBlancMate = true;
                    }
                    return false;
		}
				
		jeuDEchec = board;
		
		if(BoardUtils.isMate(Piece.BLANC, jeuDEchec)){
			roiBlancMate = true;
			return false;
		}else if(BoardUtils.isMate(Piece.NOIR, jeuDEchec)){
                        roiNoirMate = true;
			return false;
                }
		
		if(BoardUtils.isNulle(joueur.getCouleur(), jeuDEchec, evaluateurDePartieNulle) ){
                    partieNulle = true;
                    return false;
                }
		
		return true;
	}

    private long getTime() {
        return GregorianCalendar.getInstance().getTime().getTime();
    }
	
}
