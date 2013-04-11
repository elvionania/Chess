package org.elvio.chess.process;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.util.BoardUtils;

public class Visualiser {

	private Board board;
	private Byte couleur;
	private int profondeur;
	
	public Visualiser(Board board, Byte couleur) {
		super();
		this.board = board;
		this.couleur = couleur;
	}
	
//	public void genererLesPossibilites(int profondeur){
//		board.reInitBoardAVisualiser();
//		this.profondeur = profondeur;
//		parcourirLesCoupPossibles(board, couleur, 1);
//		
//	}
//	
//	private void parcourirLesCoupPossibles(Board board, Byte couleur, int profondeur) {
//		for(Byte pieceDUnJoueur : BoardUtils.getPiecesDUnJoueur(couleur, board)){
//			for(Byte coup : Piece.getPositionsJouables(pieceDUnJoueur, board)){
//				BoardUtils.addBoardAEvaluer(BoardUtils.getBoardApresUnCoup(pieceDUnJoueur, coup, board), board);
//			}
//		}
//		
//		if(profondeur < this.profondeur){
//			for(Board enfant : board.getBoardAEvaluer()){
//				parcourirLesCoupPossibles(enfant, Piece.inverseCouleur(couleur), (profondeur+1));
//			}
//		}
//	}
}
