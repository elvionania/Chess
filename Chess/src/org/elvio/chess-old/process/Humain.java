package org.elvio.chess.process;


import java.util.List;
import java.util.Scanner;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.EtatDUnBoard;
import org.elvio.chess.elements.Joueur;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.time.Temps;
import org.elvio.chess.util.BoardUtils;

public class Humain extends Joueur {

	Board boardUneFoisJoue;
	private int positionInitiale;
	private int positionFinale;
	private Scanner scanner = new Scanner(System.in);
	
	@Override
	public Board jouer(Board board, int cpt, Temps temps) {
		
		BoardUtils.montrerLeBoard(board);
		
		while(true){
			if(valider(saisirDonnees(), board)){
				// voir si  plus d un board
				EtatDUnBoard boardsPossibles = BoardUtils.getBoardApresUnCoup(positionInitiale, board.get(positionInitiale), positionFinale, board, null); 
				if(boardsPossibles.size() == 1){
					boardUneFoisJoue = boardsPossibles.get(0);
					break;
				}else{
					boardUneFoisJoue = demanderQuellePromotionLeJoueurVeut(boardsPossibles.getBoards());// si oui demander quelle piece promouvoire
					break;
				}
			}
		}
		BoardUtils.montrerLeBoard(boardUneFoisJoue);
		return boardUneFoisJoue;
	}
	
	
	private Board demanderQuellePromotionLeJoueurVeut(
			List<Board> boardsPossibles) {
		while(true){
			//Les boards de la promotion sont crees dans l ordre cavalier, fou, tour, dame
			char promotionSaisie = saisirLaPromotion();
			
			if(promotionSaisie == 'C' || promotionSaisie == 'c'){
				return boardsPossibles.get(0);
			}
			if(promotionSaisie == 'F' || promotionSaisie == 'f'){
				return boardsPossibles.get(1);
			}
			if(promotionSaisie == 'T' || promotionSaisie == 't'){
				return boardsPossibles.get(2);
			}
			if(promotionSaisie == 'D' || promotionSaisie == 'd'){
				return boardsPossibles.get(3);
			}
		}
	}
	
	private char saisirLaPromotion(){
		Scanner scanner = null;
		System.out.println("jouez votre coup");
		scanner = new Scanner(System.in);
		String demande = scanner.next();
		scanner.close();
		return demande.charAt(0);
	}


	private boolean valider(Byte[] donneesJouees, Board board) {
		positionInitiale = donneesJouees[0];
		positionFinale = donneesJouees[1];
		Byte pieceJouee = board.get(positionInitiale);
		if(pieceJouee == null){
			System.out.println("pas valide, la piece est pas la");
			return false;
		}else{
			System.out.println("la piece est la");
			List<Integer> positionJ = Piece.getPositionsJouables(positionInitiale, board.get(positionInitiale), board);
			boolean coupJouable = positionJ.contains(positionFinale);
			System.out.println("est le coup est jouable "+coupJouable);
			return coupJouable;
		}
	}


	private Byte[] saisirDonnees(){

		System.out.println("entree une donnee");
		String positionDepart = scanner.nextLine();
		System.out.println("entree 1 " +positionDepart);
		String positionFin = scanner.nextLine();
		System.out.println("entree 2 " +positionFin);
		
		return BoardUtils.traduirePositionHumaineEnPositionSysteme(positionDepart, positionFin);
		
	}

}
