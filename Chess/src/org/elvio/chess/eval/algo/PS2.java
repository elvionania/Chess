package org.elvio.chess.eval.algo;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Cavalier;
import org.elvio.chess.elements.Dame;
import org.elvio.chess.elements.Fou;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.elements.Pion;
import org.elvio.chess.elements.Tour;
import org.elvio.chess.util.BoardUtils;

public class PS2  implements FonctionEvaluation {
	
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
	
	private final static long[] cases = {1l<<0,1l<<1,1l<<2,1l<<3,1l<<4,1l<<5,1l<<6,1l<<7,
											1l<<8,1l<<9,1l<<10,1l<<11,1l<<12,1l<<13,1l<<14,1l<<15,
											1l<<16,1l<<17,1l<<18,1l<<19,1l<<20,1l<<21,1l<<22,1l<<23,
											1l<<24,1l<<25,1l<<26,1l<<27,1l<<28,1l<<29,1l<<30,1l<<31,
											1l<<32,1l<<33,1l<<34,1l<<35,1l<<36,1l<<37,1l<<38,1l<<39,
											1l<<40,1l<<41,1l<<42,1l<<43,1l<<44,1l<<45,1l<<46,1l<<47,
											1l<<48,1l<<49,1l<<50,1l<<51,1l<<52,1l<<53,1l<<54,1l<<55,
											1l<<56,1l<<57,1l<<58,1l<<59,1l<<60,1l<<61,1l<<62,1l<<63};
	
	private final static long[] casesM8 = { 0l,0l,0l,0l,0l,0l,0l,0l,
											1l<<0,1l<<1,1l<<2,1l<<3,1l<<4,1l<<5,1l<<6,1l<<7,
											1l<<8,1l<<9,1l<<10,1l<<11,1l<<12,1l<<13,1l<<14,1l<<15,
											1l<<16,1l<<17,1l<<18,1l<<19,1l<<20,1l<<21,1l<<22,1l<<23,
											1l<<24,1l<<25,1l<<26,1l<<27,1l<<28,1l<<29,1l<<30,1l<<31,
											1l<<32,1l<<33,1l<<34,1l<<35,1l<<36,1l<<37,1l<<38,1l<<39,
											1l<<40,1l<<41,1l<<42,1l<<43,1l<<44,1l<<45,1l<<46,1l<<47,
											1l<<48,1l<<49,1l<<50,1l<<51,1l<<52,1l<<53,1l<<54,1l<<55};
	
	private final static long[] casesP8 = {	1l<<8,1l<<9,1l<<10,1l<<11,1l<<12,1l<<13,1l<<14,1l<<15,
											1l<<16,1l<<17,1l<<18,1l<<19,1l<<20,1l<<21,1l<<22,1l<<23,
											1l<<24,1l<<25,1l<<26,1l<<27,1l<<28,1l<<29,1l<<30,1l<<31,
											1l<<32,1l<<33,1l<<34,1l<<35,1l<<36,1l<<37,1l<<38,1l<<39,
											1l<<40,1l<<41,1l<<42,1l<<43,1l<<44,1l<<45,1l<<46,1l<<47,
											1l<<48,1l<<49,1l<<50,1l<<51,1l<<52,1l<<53,1l<<54,1l<<55,
											1l<<56,1l<<57,1l<<58,1l<<59,1l<<60,1l<<61,1l<<62,1l<<63,
											 0l,0l,0l,0l,0l,0l,0l,0l,};
	
	private final static int[] casesCB = {7,7,7,7,7,7,7,7,
											15,15,15,15,15,15,15,15,
											23,23,23,23,23,23,23,23,
											31,31,31,31,31,31,31,31,
											39,39,39,39,39,39,39,39,
											47,47,47,47,47,47,47,47,
											55,55,55,55,55,55,55,55,
											63,63,63,63,63,63,63,63};
	
	private final static int[] casesCN = {0,0,0,0,0,0,0,0,
											8,8,8,8,8,8,8,8,
											16,16,16,16,16,16,16,16,
											24,24,24,24,24,24,24,24,
											32,32,32,32,32,32,32,32,
											40,40,40,40,40,40,40,40,
											48,48,48,48,48,48,48,48,
											56,56,56,56,56,56,56,56};
	private final static int[] pasEnAvantBlancPourLInfluence = {};
	private final static int[] pasEnAvantNoirPourLInfluence = {};
	
	private final static int[] poidColonne = {1,9,17,25,33,41,49,57};
	private final static int[] poidFinColonne = {7,15,23,31,39,47,55,63};
	
	private static long masqueBlanc = 0l;
	private static long masquePositionsSousInfluenceBlanche = 0l;
	private static long masqueNoir = 0l;
	private static long masquePositionsSousInfluenceNoire = 0l;
	private static Byte piece;
	private static int cb[] = new int[8];
	private static int cn[] = new int[8];
	
	public final double getEval(Board board, int cpt){		
		int score = 0;
		masqueBlanc = 0l;
		masquePositionsSousInfluenceBlanche = 0l;
		masqueNoir = 0l;
		masquePositionsSousInfluenceNoire = 0l;
			
		for(int position = 0 ; position < BoardUtils.NBRE_CASES_BOARD ; position++){
			if((piece = board.get2(position)) != null){
				score += getEval(piece, position, cpt, cb, cn);
			}
		}
		
		score += evalPionPasse(board);
		
		return score;
	}
	
	private final static int getEval(Byte piece, int position, int cpt, int[] cb, int[] cn) {
		if(Piece.isComme(piece, Pion.getValueStatic())){
			return evalPion(position, piece, cb, cn);
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

	
	private final static int evalPion(int position, byte piece, int[] cb, int[] cn) {
		if(Piece.isBlanc(piece)){
			
			masqueBlanc |= cases[position];
			for(int position2 = position+1 ; position2 < casesCB[position] ; position2++){
				masquePositionsSousInfluenceBlanche |= casesP8[position2];
				masquePositionsSousInfluenceBlanche |= cases[position2];
				masquePositionsSousInfluenceBlanche |= casesM8[position2];
			}
			
			return valeursPionsBlancs[position];
		}else{
			
			masqueNoir |= cases[position];
			for(int position2 = position-1 ; position2 > casesCN[position] ; position2--){
				masquePositionsSousInfluenceNoire |= casesP8[position2];
				masquePositionsSousInfluenceNoire |= cases[position2];
				masquePositionsSousInfluenceNoire |= casesM8[position2];
			}
			
			return valeursPionsNoirs[position];
		}
	}
	
	
	private static long pionsPassesNoirs;
	private static long pionsPassesBlancs;
	
	private final static int evalPionPasse(Board board){
		pionsPassesNoirs = (masqueNoir & (~masquePositionsSousInfluenceBlanche));
		pionsPassesBlancs = (masqueBlanc & (~masquePositionsSousInfluenceNoire));
		int valeurPD = 0;
		int valeurPP = 0;
		boolean pionBE = false;
		boolean pionNE = false;
		long value;
		
		for(int colonne = 0 ; colonne < 8 ; colonne++){
			for(int casesPourUneColonne = poidColonne[colonne] ; casesPourUneColonne < poidFinColonne[colonne] ; casesPourUneColonne++){
				value = cases[casesPourUneColonne];
				
				if (pionsPassesBlancs != 0l && (pionsPassesBlancs & value) == value){
					valeurPP++;
				}
				
				if (pionsPassesNoirs != 0l && (pionsPassesNoirs & value) == value){
					valeurPP--;
				}
				
				if ((masqueBlanc & value) == value) {
					if(pionBE){
						valeurPD++;
					}else{
						pionBE = true;
					}
				}
				if ((masqueNoir & value) == value) {
					if(pionNE){
						valeurPD--;
					}else{
						pionNE = true;
					}
				}
			}
		}
		
		return (valeurPP)*100-(valeurPD)*40;
	}
	
}