package org.elvio.chess.process;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.EtatDUnBoard;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.eval.algo.FonctionEvaluation;
import org.elvio.chess.util.BoardEvalue;
import org.elvio.chess.util.BoardUtils;

public class Evaluer { 
	
	private static boolean voir = false;
	
	private static BoardEvalue getEvaluation(Board board, int cpt, FonctionEvaluation fe){
			return new BoardEvalue(board, fe.getEval(board, cpt));
	}
	
	
//	pourquoi le blanc a 3 de profondeur ne fait rien
	public static BoardEvalue negaMax(int profondeur, Board board, Byte couleur, int cpt, FonctionEvaluation fe){
		BoardEvalue meilleursBoard = null;
		board.setPositionsAttaquees(null);
		
		if (profondeur == 0){
			IntelligenceArtificielle.boardCalcule++;
			return getEvaluation(board, cpt, fe);
		}
		
		double facteur = 1;
		if(!Piece.isBlanc(couleur)){
			facteur = -1;
		}
		
		double max = -1000000000;
		
		Byte piece;
		for(byte position = 0 ; position < BoardUtils.NBRE_CASES_BOARD ; position++){
			piece = board.get(position);
			if(Piece.isMemeCouleur(piece, couleur)){
				for(Byte coup : Piece.getPositionsJouables(position, board)){
					
					EtatDUnBoard etat = BoardUtils.getBoardApresUnCoup(position, coup, board);
					
//					if(profondeur == 3){						
//						voir = (position == 9);
//						System.out.println(" voir "+voir+" position "+position);
//					}
					
					for(Board enfant : etat.getBoards()){
						enfant.setPremierParentPremiereFois(board);
						
						BoardEvalue negamax = negaMax((profondeur - 1), enfant, Piece.inverseCouleur(couleur), cpt, fe);
						double score = facteur * negamax.getScore();
//						if(voir){
//							System.out.println("profondeur "+profondeur+" pour le coup "+position+"-"+coup+" score "+score);
//						}
						if(score > max) {
							max = score;
							meilleursBoard = negamax.clone();
						}
					}
					BoardUtils.remonteLeCoup(board, etat);
				}
			}
		}
		return meilleursBoard;
	}

}
