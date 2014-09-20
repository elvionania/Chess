package org.elvio.chess.process;

import java.util.List;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.pieces.Piece;
import org.elvio.chess.eval.algo.FonctionEvaluation;
import org.elvio.chess.time.Temps;
import org.elvio.chess.util.BoardEvalue;
import org.elvio.chess.util.Outils;

public class IntelligenceArtificielle extends Joueur {

	List<Board> boardAEvaluer;
	private final int profondeurMax;
	private final FonctionEvaluation algorythmeDEvaluation;
        private final IParcourir algorythmeDeParcours;
	public static long boardCalcule;
	private Integer scoreEncours = 0;
	
	public IntelligenceArtificielle(int profondeurDeCalculDuJoueur, 
                    FonctionEvaluation algorythmeDEvaluation,
                    IParcourir algorythmeDeParcours,
                    int temps, 
                    Byte couleur) {
            this.profondeurMax = profondeurDeCalculDuJoueur;
            this.algorythmeDEvaluation = algorythmeDEvaluation;
            this.temps = new Temps(temps);
            this.couleur = couleur;
            this.algorythmeDeParcours = algorythmeDeParcours;
	}

	@Override
	public Board jouer(Board board, int numeroDuCoup) {
		boardCalcule = 0;
		temps.initAvantDeJouer(scoreEncours, numeroDuCoup);
		BoardEvalue leMeilleurBoard = null;
                Board boardPremierCoupDuMeilleurBoard = null;
		long tempsInitial = Outils.getTime();
		long tempsFinal;
		long duree;
				
                //TODO le probleme de profondeur statique avec remise a zero du calcul                
                // on analyse a chaque profondeur max, tant que le temps nous le permet
                // si on a le temps on analyse le profondeur max suivante
		for(int profondeurCourante = 3 ; profondeurCourante <= profondeurMax ; profondeurCourante++){
			
			leMeilleurBoard = algorythmeDeParcours.getBoardEvalue(profondeurCourante, board, couleur, numeroDuCoup, algorythmeDEvaluation, null, false, temps);
			
			tempsFinal = Outils.getTime();
			duree = (tempsFinal-tempsInitial) / 1000;
                        
                        // la gestion du temps du joueur nous sort de la boucle dès qu'on dépasse le temps autorisé
			if(temps.getParcelDeTemps() < (duree*20)){
				break;
			}
		}
                
                // si le board est valide on cherche le score du meilleur score
                // et le premier coup de l'analyse amenant à ce score, qu'on transmet avec un premier coup null
                if(leMeilleurBoard != null){
                    scoreEncours = leMeilleurBoard.getScore();
                    boardPremierCoupDuMeilleurBoard = leMeilleurBoard.getBoard().getPremierCoupAJouer();
                    boardPremierCoupDuMeilleurBoard.setPremierCoupAJouer(null);
                }
                
                // certains scores ne peuvent être interpreté que par l'absence d'un roi
		if(scoreEncours > 100000 && Piece.isNoir(couleur) ||
                    scoreEncours < -100000 && Piece.isBlanc(couleur)){
			return null;
		}
                
		return boardPremierCoupDuMeilleurBoard;
	}
}
