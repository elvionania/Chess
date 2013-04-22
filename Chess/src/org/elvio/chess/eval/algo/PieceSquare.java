package org.elvio.chess.eval.algo;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Cavalier;
import org.elvio.chess.elements.Dame;
import org.elvio.chess.elements.Fou;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.elements.Pion;
import org.elvio.chess.elements.Tour;
import org.elvio.chess.util.BoardUtils;

public class PieceSquare implements FonctionEvaluation{
	
	private final static int[] valeursPionsBlancs = {	100,105,105,100,105,110,150,100,
														100,110,95,100,105,110,150,100,
														100,110,90,100,110,120,150,100,
														100,80,100,120,125,130,150,100,
														100,80,100,120,125,130,150,100,
														100,110,90,100,110,120,150,100,
														100,110,95,100,105,110,150,100,
														100,105,105,100,105,110,150,100};	
	private final static int[] valeursPionsNoirs = {	-100,-150,-110,-105,-100,-105,-105,-100,
														-100,-150,-110,-105,-100,-95,-110,-100,
														-100,-150,-120,-110,-100,-90,-110,-100,
														-100,-150,-130,-125,-120,-100,-80,-100,
														-100,-150,-130,-125,-120,-100,-80,-100,
														-100,-150,-120,-110,-100,-90,-110,-100,
														-100,-150,-110,-105,-100,-95,-110,-100,
														-100,-150,-110,-105,-100,-105,-105,-100};	
	private final static int[] valeursCavaliersBlancs = {270,280,290,290,290,290,280,270,
														280,300,325,320,325,320,300,280,
														290,320,330,335,335,330,320,290,
														290,325,335,340,340,335,320,290,
														290,325,335,340,340,335,320,290,
														290,320,330,335,335,330,320,290,
														280,300,325,320,325,320,300,280,
														270,280,290,290,290,290,280,270};	
	private final static int[] valeursCavaliersNoirs = {-270,-280,-290,-290,-290,-290,-280,-270,
														-280,-300,-325,-320,-325,-320,-300,-280,
														-290,-320,-330,-335,-335,-330,-320,-290,
														-290,-325,-335,-340,-340,-335,-320,-290,
														-290,-325,-335,-340,-340,-335,-320,-290,
														-290,-320,-330,-335,-335,-330,-320,-290,
														-280,-300,-325,-320,-325,-320,-300,-280,
														-270,-280,-290,-290,-290,-290,-280,-270};	
		private final static int[] valeursFousBlancs = {310,320,320,320,320,320,320,310,
														320,335,340,330,335,330,330,320,
														320,330,340,340,335,335,330,320,
														320,330,340,340,340,340,330,320,
														320,330,340,340,340,340,330,320,
														320,330,340,340,335,335,330,320,
														320,335,340,330,335,330,330,320,
														310,320,320,320,320,320,320,310};	
	private final static int[] valeursFousNoirs = {		-330,-320,-320,-320,-320,-320,-320,-310,
														-320,-330,-330,-335,-330,-340,-335,-320,
														-320,-330,-335,-335,-340,-340,-330,-320,
														-320,-330,-340,-340,-340,-340,-330,-320,
														-320,-330,-340,-340,-340,-340,-330,-320,
														-320,-330,-335,-335,-340,-340,-330,-320,
														-320,-330,-330,-335,-330,-340,-335,-320,
														-330,-320,-320,-320,-320,-320,-320,-310};	
	private final static int[] valeursToursBlanches = {	500,495,495,495,495,495,505,500,
														500,500,500,500,500,500,510,500,
														500,500,500,500,500,500,510,500,
														505,500,500,500,500,500,510,500,
														505,500,500,500,500,500,510,500,
														500,500,500,500,500,500,510,500,
														500,500,500,500,500,500,510,500,
														500,495,495,495,495,495,505,500};	
	private final static int[] valeursToursNoires = {	-500,-495,-495,-495,-495,-495,-505,-500,
														-500,-500,-500,-500,-500,-500,-510,-500,
														-500,-500,-500,-500,-500,-500,-510,-500,
														-505,-500,-500,-500,-500,-500,-510,-500,
														-505,-500,-500,-500,-500,-500,-510,-500,
														-500,-500,-500,-500,-500,-500,-510,-500,
														-500,-500,-500,-500,-500,-500,-510,-500,
														-500,-495,-495,-495,-495,-495,-505,-500};	
	private final static int[] valeursReineBlanche = {	880,890,890,900,895,890,890,880,
														890,900,905,900,900,900,900,890,
														890,905,905,905,905,905,900,890,
														895,900,905,905,905,905,900,895,
														895,900,905,905,905,905,900,895,
														890,905,905,905,905,905,900,890,
														890,900,900,900,900,900,900,890,
														880,890,890,895,895,890,890,880};	
	private final static int[] valeursReineNoire = {	-880,-890,-890,-900,-895,-890,-890,-880,
														-890,-900,-905,-900,-900,-900,-900,-890,
														-890,-905,-905,-905,-905,-905,-900,-890,
														-895,-900,-905,-905,-905,-905,-900,-895,
														-895,-900,-905,-905,-905,-905,-900,-895,
														-890,-905,-905,-905,-905,-905,-900,-890,
														-890,-900,-900,-900,-900,-900,-900,-890,
														-880,-890,-890,-895,-895,-890,-890,-880};	
	private final static int[] valeursRoiBlanc1 = {		1000020,1000020,999990,999980,999970,999970,999970,999970,
														1000030,1000020,999980,999970,999960,999960,999960,999960,
														1000010,1000000,999980,999970,999960,999960,999960,999960,
														1000000,1000000,999980,999960,999950,999950,999950,999950,
														1000000,1000000,999980,999960,999950,999950,999950,999950,
														1000010,1000000,999980,999970,999960,999960,999960,999960,
														1000030,1000020,999980,999970,999960,999960,999960,999960,
														1000020,1000020,999990,999980,999970,999970,999970,999970};
	private final static int[] valeursRoiNoir1 = {		-1000020,-1000020,-999990,-999980,-999970,-999970,-999970,-999970,
														-1000030,-1000020,-999980,-999970,-999960,-999960,-999960,-999960,
														-1000010,-1000000,-999980,-999970,-999960,-999960,-999960,-999960,
														-1000000,-1000000,-999980,-999960,-999950,-999950,-999950,-999950,
														-1000000,-1000000,-999980,-999960,-999950,-999950,-999950,-999950,
														-1000010,-1000000,-999980,-999970,-999960,-999960,-999960,-999960,
														-1000030,-1000020,-999980,-999970,-999960,-999960,-999960,-999960,
														-1000020,-1000020,-999990,-999980,-999970,-999970,-999970,-999970};
	private final static int[] valeursRoiBlanc2 = {		999950,999970,999970,999970,999970,999970,999970,999950,
														999970,999970,999990,999990,999990,999990,999980,999960,
														999970,1000000,1000020,1000030,1000030,1000020,999990,999970,
														999970,1000000,1000030,1000040,1000040,1000030,1000000,999980,
														999970,1000000,1000030,1000040,1000040,1000030,1000000,999980,
														999970,1000000,1000020,1000030,1000030,1000020,999990,999970,
														999970,999970,999990,999990,999990,999990,999980,999960,
														999950,999970,999970,999970,999970,999970,999970,999950};
	private final static int[] valeursRoiNoir2 = {		-999950,-999970,-999970,-999970,-999970,-999970,-999970,-999950,
														-999970,-999970,-999990,-999990,-999990,-999990,-999980,-999960,
														-999970,-1000000,-1000020,-1000030,-1000030,-1000020,-999990,-999970,
														-999970,-1000000,-1000030,-1000040,-1000040,-1000030,-1000000,-999980,
														-999970,-1000000,-1000030,-1000040,-1000040,-1000030,-1000000,-999980,
														-999970,-1000000,-1000020,-1000030,-1000030,-1000020,-999990,-999970,
														-999970,-999970,-999990,-999990,-999990,-999990,-999980,-999960,
														-999950,-999970,-999970,-999970,-999970,-999970,-999970,-999950};
	
	
	public final int getEval(Board board, int cpt){		
		int score = 0;
		Byte piece;
			
		for(byte position = 0 ; position < BoardUtils.NBRE_CASES_BOARD ; position++){
			if((piece = board.get(position)) != null){
				score += getEval(piece, position, cpt);
			}
		}
		return score;
	}
	
	private final static int getEval(Byte piece, int position, int cpt) {
		if(Piece.isComme(piece, Pion.getValueStatic())){
			return evalPion(position, piece);
		}else if(Piece.isComme(piece, Cavalier.getValueStatic())){
			return evalCavalier(position, piece);
		}else if(Piece.isComme(piece, Fou.getValueStatic())){
			return evalFou(position, piece);
		}else if(Piece.isComme(piece, Tour.getValueStatic())){
			return evalTour(position, piece);
		}else if(Piece.isComme(piece, Dame.getValueStatic())){
			return evalDame(position, piece);
		}else{
			if(cpt > 25){
				return evalRoiFinal(position, piece);
			}else{
				return evalRoi(position, piece);
			}
		}
	}

	private final static int evalRoi(int position, byte piece){
		if(Piece.isBlanc(piece)){
			return valeursRoiBlanc1[position];
		}else{
			return valeursRoiNoir1[position];
		}
	}
	
	private final static int evalRoiFinal(int position, byte piece){
		if(Piece.isBlanc(piece)){
			return valeursRoiBlanc2[position];
		}else{
			return valeursRoiNoir2[position];
		}
	}
	
	private final static int evalDame(int position, byte piece){
		if(Piece.isBlanc(piece)){
			return valeursReineBlanche[position];
		}else{
			return valeursReineNoire[position];
		}
	}
	
	private final static int evalTour(int position, byte piece){
		if(Piece.isBlanc(piece)){
			return valeursToursBlanches[position];
		}else{
			return valeursToursNoires[position];
		}
	}
	
	private final static int evalFou(int position, byte piece){
		if(Piece.isBlanc(piece)){
			return valeursFousBlancs[position];
		}else{
			return valeursFousNoirs[position];
		}
	}

	private final static int evalCavalier(int position, byte piece) {
		if(Piece.isBlanc(piece)){
			return valeursCavaliersBlancs[position];
		}else{
			return valeursCavaliersNoirs[position];
		}
	}

	private final static int evalPion(int position, byte piece) {
		if(Piece.isBlanc(piece)){
			return valeursPionsBlancs[position];
		}else{
			return valeursPionsNoirs[position];
		}
	}
	
}
