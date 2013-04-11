package org.elvio.chess.process;

import java.util.List;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Joueur;
import org.elvio.chess.eval.algo.FonctionEvaluation;

public class IntelligenceArtificielle extends Joueur {

	List<Board> boardAEvaluer;
	private int profondeur = 5;
	private FonctionEvaluation fe;
	public static long boardCalcule;
	
	public IntelligenceArtificielle(int i, FonctionEvaluation fe) {
		this.profondeur = i;
		this.fe = fe;
	}

	@Override
	public Board jouer(Board board, int cpt) {
//		Visualiser observer = new Visualiser(board, couleur);
		
//		observer.genererLesPossibilites(profondeur);
		boardCalcule = 0;
		Board meilleurBoard = Evaluer.negaMax(profondeur, board, couleur, cpt, fe).getBoard();
		Board boardPremierCoupDuMeilleurBoard = meilleurBoard.getPremierParent();
		boardPremierCoupDuMeilleurBoard.setPremierParent(null);
		boardPremierCoupDuMeilleurBoard.setBoardAEvaluer(null);
		board = null;		

		return boardPremierCoupDuMeilleurBoard;
	}
}
