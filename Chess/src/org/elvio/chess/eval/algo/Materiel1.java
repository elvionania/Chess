package org.elvio.chess.eval.algo;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Cavalier;
import org.elvio.chess.elements.Dame;
import org.elvio.chess.elements.Fou;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.elements.Pion;
import org.elvio.chess.elements.Roi;
import org.elvio.chess.elements.Tour;
import org.elvio.chess.util.BoardUtils;


public class Materiel1  implements FonctionEvaluation {

	private final static int roi = 1000000;
	private final static int dame = 900;
	private final static int tour = 500;
	private final static int fou = 300;
	private final static int cavalier = 300;
	private final static int pion = 100;
	
	private final static int roi_n = -1000000;
	private final static int dame_n = -900;
	private final static int tour_n = -500;
	private final static int fou_n = -300;
	private final static int cavalier_n = -300;
	private final static int pion_n = -100;
	
	public final int getEval(Board board, int cpt){
		int resultat = 0;
		for(int position = 0 ; position < BoardUtils.NBRE_CASES_BOARD ; position++){
			resultat += eval(board.get2(position));
		}
		return resultat;
	}

	private final static int eval(Byte etat) {
		if(etat != null ){
			if(Piece.isBlanc(etat)){
				return 1;
			}else{
				return Piece.M_UN;
			}
		}
		return 0;
	}
	
	
}
