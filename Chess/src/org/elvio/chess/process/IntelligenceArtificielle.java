package org.elvio.chess.process;

import java.util.List;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Joueur;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.eval.algo.FonctionEvaluation;
import org.elvio.chess.time.Temps;
import org.elvio.chess.util.BoardEvalue;
import org.elvio.chess.util.Outils;

public class IntelligenceArtificielle extends Joueur {

	List<Board> boardAEvaluer;
	private int profondeurMax = 5;
	private final FonctionEvaluation algoDEvaluation;
	public static long boardCalcule;
	private Integer scoreEncours = 0;
	
	public IntelligenceArtificielle(int profondeurDeCalculDuJoueur, 
                    FonctionEvaluation algoDEvaluation, 
                    int temps, 
                    Byte couleur) {
		this.profondeurMax = profondeurDeCalculDuJoueur;
		this.algoDEvaluation = algoDEvaluation;
                this.temps = new Temps(temps);
                this.couleur = couleur;
	}

	@Override
	public Board jouer(Board board, int numeroDuCoup) {
		boardCalcule = 0;
		temps.initAvantDeJouer(scoreEncours, numeroDuCoup);
		BoardEvalue leMeilleurBoard = null;
		long tempsInitial = Outils.getTime();
		long tempsFinal;
		long duree;
				
		for(int profondeurCourante = 3 ; profondeurCourante <= profondeurMax ; profondeurCourante++){
			
			leMeilleurBoard = Evaluer.negaMax(profondeurCourante, board, couleur, numeroDuCoup, algoDEvaluation, null, false, temps);
			
//			for(int i = 0 ; i < leMeilleurBoard.getCoupARetenir().size() ; i++){
//				System.out.println("coup a retenir "+BoardUtils.getLitteralPosition(leMeilleurBoard.getCoupARetenir().get(i))+"-"+BoardUtils.getLitteralPosition(leMeilleurBoard.getCoupARetenir().get(++i)));
//			}
			
			tempsFinal = Outils.getTime();
			duree = (tempsFinal-tempsInitial) / 1000;
//			System.out.println("profondeur "+profondeurCourante+" parceldetemps "+temps.getParcelDeTemps()+" duree "+duree+" score "+leMeilleurBoard.getScore());
			if(temps.getParcelDeTemps() < (duree*20)){
				break;
			}
		}
                
		scoreEncours = leMeilleurBoard.getScore();
		if(scoreEncours > 100000 && Piece.isNoir(couleur) ||
				scoreEncours < -100000 && Piece.isBlanc(couleur)){
			return null;
		}
		Board meilleurBoard = leMeilleurBoard.getBoard();
		Board boardPremierCoupDuMeilleurBoard = meilleurBoard.getPremierCoupAJouer();
		boardPremierCoupDuMeilleurBoard.setPremierCoupAJouer(null);

//                System.out.println(BoardUtils.getBoardConfiguration(boardPremierCoupDuMeilleurBoard));
                
		return boardPremierCoupDuMeilleurBoard;
	}
}
