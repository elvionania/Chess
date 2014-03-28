package org.elvio.chess.process;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.EtatDUnBoard;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.eval.algo.FonctionEvaluation;
import org.elvio.chess.util.BoardEvalue;
import org.elvio.chess.util.BoardUtils;

public class Evaluer { 
	
	private static BoardEvalue getEvaluation(Board board, int cpt, FonctionEvaluation fe){
			return new BoardEvalue(board, fe.getEval(board, cpt));
	}
	
	public static BoardEvalue negaMax(int profondeur, Board board, Byte couleur, int cpt, FonctionEvaluation fe, Integer maxParent){
		if (profondeur == 0){
			IntelligenceArtificielle.boardCalcule++;
			return getEvaluation(board, cpt, fe);
		}
		
		BoardEvalue meilleursBoard = null;
		
		int facteur = 1;
		if(!Piece.isBlanc(couleur)){
			facteur = -1;
		}
		
		int max = -1000000000;
		boolean brancheCoupee = false;
		Byte piece = null;
		EtatDUnBoard etat = null;
		for(int position = 0 ; position < BoardUtils.NBRE_CASES_BOARD ; position++){
			if(Piece.isMemeCouleur((piece = board.get(position)), couleur)){
				board.setPositionsAttaquees(null);
				for(int coup : Piece.getPositionsJouables(position, piece, board)){
					etat = BoardUtils.getBoardApresUnCoup(position, piece, coup, board, etat);
					
					if(etat.isRoiBlancDevore() || etat.isRoiNoirDevore()){
						meilleursBoard = getEvaluation(board, cpt, fe).clone();
						meilleursBoard.addCoupARetenir(position, coup);
					}else{
						for(Board enfant : etat.getBoards()){
							enfant.setPremierParentPremiereFois(board);
							BoardEvalue negamax = negaMax((profondeur - 1), enfant, Piece.inverseCouleur(couleur), cpt, fe, (meilleursBoard==null?null:(facteur*max)));
							Integer score = null;
							if(negamax != null){
								score = facteur * negamax.getScore();
							}else{
								continue;
							}
							
							if (brancheCoupee = (maxParent != null && score > (facteur*maxParent))){
								break;
							}
							
							if(score > max) {
								max = score;
								meilleursBoard = negamax.clone();
								meilleursBoard.addCoupARetenir(position, coup);
							}
						}
					}
										
					BoardUtils.remonteLeCoup(board, etat);
					
					if(brancheCoupee){
						return null;
					}
				}
			}
		}
		return meilleursBoard;
	}


}
