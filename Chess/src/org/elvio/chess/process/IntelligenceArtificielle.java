package org.elvio.chess.process;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Joueur;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.eval.algo.FonctionEvaluation;
import org.elvio.chess.time.Temps;
import org.elvio.chess.util.BoardEvalue;
import org.elvio.chess.util.BoardUtils;

public class IntelligenceArtificielle extends Joueur {

	List<Board> boardAEvaluer;
	private int profondeur = 5;
	private FonctionEvaluation fe;
	public static long boardCalcule;
	private int scoreEncours = 0;
	
	public IntelligenceArtificielle(int i, FonctionEvaluation fe, Temps temps) {
		this.profondeur = i;
		this.fe = fe;
                this.temps = temps;
	}

	@Override
	public Board jouer(Board board, int cpt, Temps temps) {
		boardCalcule = 0;
		temps.vaJouer(scoreEncours, cpt, couleur);
		BoardEvalue be = null;
		Calendar calendar = GregorianCalendar.getInstance();
		long t0 = calendar.getTimeInMillis();
		long t1 = 0l;
		long duree = 0l;
		
		
		
		
		for(int p = 3 ; p <= profondeur ; p++){
			
			be = Evaluer.negaMax(p, board, couleur, cpt, fe, null);
			
			for(int i = 0 ; i < be.getCoupARetenir().size() ; i++){
				System.out.println("coup a retenir "+BoardUtils.getLitteralPosition(be.getCoupARetenir().get(i))+"-"+BoardUtils.getLitteralPosition(be.getCoupARetenir().get(++i)));
			}
			
			
			
			t1 = GregorianCalendar.getInstance().getTimeInMillis();
			duree = (t1-t0) / 1000;
			System.out.println("profondeur "+p+" parceldetemps "+temps.getParcelDeTemps()+" duree "+duree+" score "+be.getScore());
			if(temps.getParcelDeTemps() < (duree*20)){
				break;
			}
		}
		scoreEncours = be.getScore();
		if(scoreEncours > 100000 && !Piece.isBlanc(couleur) ||
				scoreEncours < -100000 && Piece.isBlanc(couleur)){
			return null;
		}
		Board meilleurBoard = be.getBoard();
		Board boardPremierCoupDuMeilleurBoard = meilleurBoard.getPremierParent();
		boardPremierCoupDuMeilleurBoard.setPremierParent(null);
		boardPremierCoupDuMeilleurBoard.setBoardAEvaluer(null);

		return boardPremierCoupDuMeilleurBoard;
	}
}
