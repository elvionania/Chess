package org.elvio.chess.process;

import java.util.List;
import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.util.CoupARetenir;
import org.elvio.chess.elements.pieces.Piece;
import org.elvio.chess.eval.algo.FonctionEvaluation;
import org.elvio.chess.time.Temps;
import org.elvio.chess.util.BoardEvalue;
import org.elvio.chess.util.BoardUtils;


//TODO changer le nom de l interface de IEvaluer à IParcourir
public class NegaMax implements IEvaluer { 
    
    private static StringBuilder chemin = new StringBuilder();
    
	private static BoardEvalue getEvaluation(Board board, int numeroDuCoup, FonctionEvaluation algoDEvaluation){
			return new BoardEvalue(board, algoDEvaluation.getEval(board, numeroDuCoup));
	}

    /**
     *
     * parcours récursif de l'arbre des possibles et sélection du meilleur choix selon le principe du negamax
     * amélioration des performances via la mise en place de l'élagations des branches des possibles selon le pruning alpha béta
     * 
     * @param profondeur
     * @param board
     * @param couleur
     * @param numeroDuCoup
     * @param algoDEvaluation
     * @param maxParent
     * @param test
     * @param tempsDuJoueur
     * @return
     */
    @Override
    public BoardEvalue getBoardEvalue(int profondeur, Board board, Byte couleur, int numeroDuCoup, FonctionEvaluation algoDEvaluation, Integer maxParent, boolean test, Temps tempsDuJoueur){
		if (profondeur == 0){
			IntelligenceArtificielle.boardCalcule++;
			return getEvaluation(board, numeroDuCoup, algoDEvaluation);
		}
		
		BoardEvalue meilleurBoard = null;
		
		int facteur = -1;
		if(Piece.isBlanc(couleur)){
			facteur = 1;
		}
		
		int max = -1000000000;
		boolean brancheCoupee = false;
		Byte piece;
		EtatDUnBoard etat = null;
                boolean enTest;
                
		for(int position = 0 ; position < BoardUtils.NBRE_CASES_BOARD ; position++){
			if(Piece.isMemeCouleur((piece = board.get(position)), couleur)){
				board.setPositionsAttaquees(null);
				for(int coup : Piece.getPositionsJouables(position, piece, board)){
                                        enTest = (profondeur == 4 && coup == 14 && position == 10)||test;
                                            
                                        int max2 = -1000000000;
					etat = BoardUtils.getBoardApresUnCoup(position, piece, coup, board, etat);
                                        for(Board enfant : etat.getBoards()){
                                            if(etat.isRoiBlancDevore() || etat.isRoiNoirDevore()){
                                                BoardEvalue evaluation = getEvaluation(enfant, numeroDuCoup, algoDEvaluation).clone();
                                                Integer score = facteur * evaluation.getScore();

                                                if(score > max) {
                                                    max = score;
                                                    meilleurBoard = evaluation.clone();
                                                    meilleurBoard.addCoupARetenir(position, coup);
                                                }


                                            }else{
						enfant.creationPremierCoupAJouer(board);
						BoardEvalue negamax = getBoardEvalue((profondeur - 1), enfant, Piece.inverseCouleur(couleur), numeroDuCoup, algoDEvaluation, (meilleurBoard==null?null:(facteur*max)), enTest, tempsDuJoueur);
						Integer score;
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
                                                    meilleurBoard = negamax.clone();
                                                    meilleurBoard.addCoupARetenir(position, coup);
						}
						
                                            }
                                        }
										
					BoardUtils.revenirAuBoardInitial(board, etat);
					
					if(brancheCoupee){
						return null;
					}
                                        
                                        // comment recuperer le meilleur score du coup niv3....????
                                        if(enTest && profondeur > 1){
                                            System.out.println("profondeur "+profondeur+" coup "+BoardUtils.getLitteralPosition(position)+"-"+BoardUtils.getLitteralPosition(coup)+" score "+max2);
                                        }
				}
			}
		}
		return meilleurBoard;
	}

    private static String cheminement(List<CoupARetenir> coupsARetenir) {
        chemin.delete(0, chemin.length()-1);
        for(CoupARetenir coup : coupsARetenir){
            chemin.append(coup.getPositionCoupInitial());
            chemin.append(';');
            chemin.append(coup.getEtatCoupInitial());
            chemin.append(';');
            chemin.append(coup.getPositionCoupFinal());
            chemin.append(';');
            chemin.append(coup.getEtatCoupFinal());
            chemin.append(';');
        }
        return chemin.toString();
    }

//        -- probleme :
//        1/ nous allons cloner au prealable les boards
//        2/ a terme il ne faudra garder que le chemin!!
//        3/ ou faire directement 2/
//        
//        public static BoardEvalue negaMax2(int profondeur, Board board, Byte couleur, int numeroDuCoup, FonctionEvaluation algoDEvaluation, Integer maxParent, boolean test, Temps tempsDuJoueur){
//            
//            int facteur;
//            
//            if(Piece.isBlanc(couleur)){
//                facteur = 1;
//            }else{
//                facteur = -1;
//            }
//            
//            BoardEvaluationComparator bec = new BoardEvaluationComparator(facteur);
//            final TreeMap<String, Integer> tubeDEvaluation = new TreeMap<>(bec);
//            
//            //TODO prise en compte du facteur!!!!!!!!!!!!!!!!!!! :'p
//            firstFillTube(board, couleur, numeroDuCoup, algoDEvaluation, tubeDEvaluation);
//            
//            String elementCourant;
//            
//            // boucler sur le premier element du tube
//            Map.Entry<String, Integer> element;
//
//            GestionDuTemps gestionDuTemps = new GestionDuTemps();
//            
//            while(!gestionDuTemps.cEstLHeure(tempsDuJoueur)){
//                //stocker en dehors du tube le premier element dans 'currentElement' et le supprimer du tube
//                element = tubeDEvaluation.firstEntry();
//                elementCourant = element.getKey();
//                tubeDEvaluation.remove(elementCourant);
//                fillTube(elementCourant, couleur, numeroDuCoup, algoDEvaluation, facteur);                  
//            }
//            
//            element = tubeDEvaluation.firstEntry();
//            return element.getValue();
//	}
//
////        -- probleme avec le facteur pour l'ordre ... peut etre 2 tubes ????
//        
//    private static void firstFillTube(Board board, Byte couleur, int numeroDuCoup, FonctionEvaluation algoDEvaluation, TreeMap<String, Integer> tubeDEvaluation) {
//        Byte piece;
//        EtatDUnBoard etat = null;
//        //TODO ne pas faire un parcour recursif direct mais par preference
//        // etape 1 si le tube est vide, chercher les enfants du board originel
//        for(int position : board.getPositionsDesPieces()){
//            if(Piece.isMemeCouleur((piece = board.get(position)), couleur)){
//                board.setPositionsAttaquees(null);
//                for(int coup : Piece.getPositionsJouables(position, piece, board)){
//                    etat = BoardUtils.getBoardApresUnCoup(position, piece, coup, board, etat);
//                    
//                    for(Board enfant : etat.getBoards()){
//                        BoardEvalue evaluation = getEvaluation(enfant, numeroDuCoup, algoDEvaluation).clone();
//                        tubeDEvaluation.put(cheminement(etat.getCoupARetenir()), evaluation.getScore());
//                    }
//                    
//                    BoardUtils.revenirAuBoardInitial(board, etat);                    
//                }
//            }
//        }
//    }
//       
//    
//    // faire deux boucles
//    // 1 ere boucle pour determiner le coup noir
//    // 2ieme boucle pour determiner le coup des blancs
//    // evaluer le coup des blancs, et donner le meilleur par coup noir
//    // determiner quel coup noir est le plus faible
//    
//    // probleme a resoudre pour la profondeur et le sacrifice!!!
//    
//     private static TreeMap<Board, BoardEvalue> fillTube(String board, Byte couleur, int numeroDuCoup, FonctionEvaluation algoDEvaluation, int facteur) {
//        Byte piece;
//        EtatDUnBoard etat = null;
//        
//        BoardEvaluationComparator bec = new BoardEvaluationComparator(facteur);
//        TreeMap<Board, BoardEvalue> tubeDEvaluationCourant = new TreeMap<>(bec);
//        bec.inverseComparaison();
//        
//        //TODO ne pas faire un parcour recursif direct mais par preference
//        // etape 1 si le tube est vide, chercher les enfants du board originel
//        for(int position : board.getPositions()){
//            if(Piece.isDifferenteCouleur((piece = board.get(position)), couleur)){
//                board.setPositionsAttaquees(null);
//                for(int coup : Piece.getPositionsJouables(position, piece, board)){
//                    etat = BoardUtils.getBoardApresUnCoup(position, piece, coup, board, etat);
//                    
//                    for(Board enfant : etat.getBoards()){
//                        BoardEvalue evaluation = getEvaluation(enfant, numeroDuCoup, algoDEvaluation).clone();
//                        tubeDEvaluationCourant.put(enfant, evaluation);
//                    }
//                    
//                }
//            }
//        }
//        
//        bec.inverseComparaison();
//        Board boardCourant;
//        
//        for(Map.Entry<Board, BoardEvalue> entry : tubeDEvaluationCourant.entrySet()){
//            
//            boardCourant = entry.getKey();
//            tubeDEvaluationCourant.remove(boardCourant);
//            
//            for(int position : boardCourant.getPositionsDesPieces()){
//                if(Piece.isMemeCouleur((piece = boardCourant.get(position)), couleur)){
//                    boardCourant.setPositionsAttaquees(null);
//                    for(int coup : Piece.getPositionsJouables(position, piece, boardCourant)){
//                        etat = BoardUtils.getBoardApresUnCoup(position, piece, coup, boardCourant, etat);
//
//                        for(Board enfant : etat.getBoards()){
//                            BoardEvalue evaluation = getEvaluation(enfant, numeroDuCoup, algoDEvaluation).clone();
//                            tubeDEvaluationCourant.put(enfant, evaluation);
//                        }
//
//                    }
//                }
//            }
//        }
//        
//        return tubeDEvaluationCourant;
//    }

}
