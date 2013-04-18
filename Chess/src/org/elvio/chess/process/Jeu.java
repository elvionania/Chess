package org.elvio.chess.process;

import java.util.GregorianCalendar;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Joueur;
import org.elvio.chess.elements.PartieNulle;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.eval.algo.PS2;
import org.elvio.chess.eval.algo.PieceSquare;
import org.elvio.chess.util.BoardUtils;

public class Jeu {

	private Board jeuDEchec;
	private Joueur joueurBlanc;
	private Joueur joueurNoir;
	private boolean roiNoirMate;
	private boolean roiBlancMate;
	private boolean partieNulle;
	private PartieNulle evaluateurDePartieNulle;
	
	public Jeu(){
		jeuDEchec = new Board();
		jeuDEchec.initialisation();
		evaluateurDePartieNulle = new PartieNulle();
	}
	
	//joueur1
	Joueur joueur1 = new IntelligenceArtificielle(4, new PS2());
	//joueur2
	Joueur joueur2 = new IntelligenceArtificielle(4, new PieceSquare());
	
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
		long t2;
		double tVB = 0l;
		double tVN = 0l;
		double totalValue = 0d;
		double ratioValue = 0d;
		
		while(pasTermine){
			System.out.println("blanc va joue");
			t1 = GregorianCalendar.getInstance().getTime().getTime();
			pasTermine = joueBlanc(cpt);
			t2 = GregorianCalendar.getInstance().getTime().getTime();
			tVB = (IntelligenceArtificielle.boardCalcule*1000/(t2-t1));
			System.out.println("blanc a joue board/s "+tVB);
			BoardUtils.montrerLeBoard(jeuDEchec);
			System.out.println("noir va joue");
			t1 = GregorianCalendar.getInstance().getTime().getTime();
			pasTermine = joueNoir(cpt);
			t2 = GregorianCalendar.getInstance().getTime().getTime();
			tVN = (IntelligenceArtificielle.boardCalcule*1000/(t2-t1));
			System.out.println("noir a joue board/s "+tVN);
			BoardUtils.montrerLeBoard(jeuDEchec);
			if(cpt > 1)	{
				totalValue += (tVB/tVN)*100;
				ratioValue = totalValue / (cpt-1);
			}
			pasTermine = 12 > cpt++;
		}
		System.out.println("perf Blanc "+ratioValue);
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

	private boolean joueNoir(int cpt) {
		jeuDEchec = joueurNoir.jouer(jeuDEchec, cpt);
		
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

	private boolean joueBlanc(int cpt) {
		jeuDEchec = joueurBlanc.jouer(jeuDEchec, cpt);
		
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
