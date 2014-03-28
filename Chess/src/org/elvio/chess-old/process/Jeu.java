package org.elvio.chess.process;

import java.util.GregorianCalendar;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Joueur;
import org.elvio.chess.elements.PartieNulle;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.elements.Pion;
import org.elvio.chess.eval.algo.PS2;
import org.elvio.chess.time.Temps;
import org.elvio.chess.util.BoardUtils;

public class Jeu {

	private Board jeuDEchec;
	private Joueur joueurBlanc;
	private Joueur joueurNoir;
	private boolean roiNoirMate;
	private boolean roiBlancMate;
	private boolean partieNulle;
	private PartieNulle evaluateurDePartieNulle;
	private Temps temps;
	
	public Jeu(int tempsEnMinute){
		jeuDEchec = new Board();
		temps = new Temps(tempsEnMinute);
		jeuDEchec.initialisation();
		evaluateurDePartieNulle = new PartieNulle();
	}
	
	//joueur1
	Joueur joueur1 = new IntelligenceArtificielle(10, new PS2(), temps);
	//joueur2
//	Joueur joueur2 = new IntelligenceArtificielle(5, new PieceSquare(), temps);
	Joueur joueur2 = new Humain();
	
	// determine qui est le blanc
	
	private void determineCouleurDesJoueurs() {
		
//		if(0.5 <= Math.random()){
			joueur1.setCouleur(Piece.BLANC);
			joueur2.setCouleur(Piece.NOIR);
			joueurBlanc = joueur1;
			joueurNoir = joueur2;
//			System.out.println("pc blanc");
//		}else{
//			joueur1.setCouleur(Piece.NOIR);
//			joueur2.setCouleur(Piece.BLANC);
//			joueurBlanc = joueur2;
//			joueurNoir = joueur1;
//			System.out.println("tu es blanc");
//		}
	}
	
	// commence la partie
	public void joue(){
		determineCouleurDesJoueurs();
		
		boolean pasTermine = true;
//		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");
			
		BoardUtils.montrerLeBoard(jeuDEchec);
		int cpt = 0;
		long t1;
		long t2 = 0l;
		double tVB = 0l;
		double tVN = 0l;
		double totalValue = 0d;
		double ratioValue = 0d;
		
		long t0 = GregorianCalendar.getInstance().getTime().getTime();
		while(pasTermine){
			System.out.println("blanc va joue");
			t1 = GregorianCalendar.getInstance().getTime().getTime();
			temps.setTempsCourant(t1-t0);
			reInitPriseEnPassant(Piece.BLANC);
			if(!joueBlanc(cpt,temps)){
				break;
			}
			t2 = GregorianCalendar.getInstance().getTime().getTime();
			tVB = (IntelligenceArtificielle.boardCalcule*1000/(t2-t1));
			System.out.println("blanc a joue board/s "+tVB);
			BoardUtils.montrerLeBoard(jeuDEchec);
			System.out.println("noir va joue");
			t1 = GregorianCalendar.getInstance().getTime().getTime();
			temps.setTempsCourant(t1-t0);
			
			reInitPriseEnPassant(Piece.NOIR);
			
			if(!joueNoir(cpt, temps)){
				break;
			}
			
			t2 = GregorianCalendar.getInstance().getTime().getTime();
			long totalTps = t2 - t1;
			if(totalTps == 0){
				totalTps = 1l;
			}
			tVN = (IntelligenceArtificielle.boardCalcule*1000/(totalTps));
			System.out.println("noir a joue board/s "+tVN);
			System.out.println("le coup numero "+cpt++);
			if(cpt == 14){
				System.out.println(jeuDEchec.get(53));
			}
			BoardUtils.montrerLeBoard(jeuDEchec);
			if(cpt > 1)	{
				totalValue += tVN;
				ratioValue = totalValue / (cpt-1);
			}
//			pasTermine = 2 > cpt++;
		}
		System.out.println("perf "+ratioValue);
		System.out.println(" temps "+(t2-t0)/1000);
//		while(true){
//			System.out.println(" ");
//		}
		
		if(roiBlancMate){
			System.out.println("Blancs sont mat");
		}
		
		if(roiNoirMate){
			System.out.println("Noirs sont mat");
		}
		
		if(partieNulle){
			System.out.println("La partie est nulle");
		}
		
	}

	private void reInitPriseEnPassant(byte couleur) {
		for(int i = 0 ; i < 64 ; i++){
			if(jeuDEchec.get(i)!= null && Piece.isMemeCouleur(couleur, jeuDEchec.get(i)) && Piece.isComme(jeuDEchec.get(i), Pion.getValueStatic())){
				jeuDEchec.put(i, (byte) (jeuDEchec.get(i)&Piece.NA_PLUS_AVANCER_DE_2));
			}
		}
	}

	private boolean joueNoir(int cpt, Temps temps) {
		Board board = joueurNoir.jouer(jeuDEchec, cpt, temps);
		
		if(board == null){
			roiNoirMate = true;
			return false;
		}
		
		if(board.getBoardAEvaluer()==null){
			System.out.println("p2");
		}
		jeuDEchec = board;
		
		if(BoardUtils.isMate(Piece.BLANC, jeuDEchec)){
			roiBlancMate = true;
			return false;
		}
		
		if(BoardUtils.isNulle(Piece.BLANC, jeuDEchec, evaluateurDePartieNulle)){
			partieNulle = true;
			return false;
		}
		
		return true;
	}

	private boolean joueBlanc(int cpt, Temps temps) {
		Board board = joueurBlanc.jouer(jeuDEchec, cpt, temps);
		
		if(board == null){
			roiBlancMate = true;
			return false;
		}
		
		jeuDEchec = board;
		
		if(BoardUtils.isMate(Piece.NOIR, jeuDEchec)){
			roiNoirMate = true;
			return false;
		}
		
		if(BoardUtils.isNulle(Piece.NOIR, jeuDEchec, evaluateurDePartieNulle)){
			partieNulle = true;
			return false;
		}
		
		return true;
	}
	
}
