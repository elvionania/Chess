package org.elvio.chess.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Cavalier;
import org.elvio.chess.elements.Dame;
import org.elvio.chess.elements.EtatDUnBoard;
import org.elvio.chess.elements.Fou;
import org.elvio.chess.elements.PartieNulle;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.elements.Pion;
import org.elvio.chess.elements.Roi;
import org.elvio.chess.elements.Tour;

public class BoardUtils {

	public static final byte NBRE_CASES_COLONNE = 8;
	public static final byte NBRE_CASES_BOARD = 64;
	private static final byte ETAT_BASE = BoardUtils.GetRepresentationByte("00000000");
	
	public static final Byte A1 = BoardUtils.GetRepresentationByte("00000000");
	public static final Byte A2 = BoardUtils.GetRepresentationByte("00000001");	
	public static final Byte A3 = BoardUtils.GetRepresentationByte("00000010");
	public static final Byte A4 = BoardUtils.GetRepresentationByte("00000011");
	public static final Byte A5 = BoardUtils.GetRepresentationByte("00000100");
	public static final Byte A6 = BoardUtils.GetRepresentationByte("00000101");
	public static final Byte A7 = BoardUtils.GetRepresentationByte("00000110");
	public static final Byte A8 = BoardUtils.GetRepresentationByte("00000111");
	public static final Byte B1 = BoardUtils.GetRepresentationByte("00001000");
	public static final Byte B2 = BoardUtils.GetRepresentationByte("00001001");
	public static final Byte B3 = BoardUtils.GetRepresentationByte("00001010");
	public static final Byte B4 = BoardUtils.GetRepresentationByte("00001011");
	public static final Byte B5 = BoardUtils.GetRepresentationByte("00001100");
	public static final Byte B6 = BoardUtils.GetRepresentationByte("00001101");
	public static final Byte B7 = BoardUtils.GetRepresentationByte("00001110");
	public static final Byte B8 = BoardUtils.GetRepresentationByte("00001111");
	public static final Byte C1 = BoardUtils.GetRepresentationByte("00010000");
	public static final Byte C2 = BoardUtils.GetRepresentationByte("00010001");
	public static final Byte C3 = BoardUtils.GetRepresentationByte("00010010");
	public static final Byte C4 = BoardUtils.GetRepresentationByte("00010011");
	public static final Byte C5 = BoardUtils.GetRepresentationByte("00010100");
	public static final Byte C6 = BoardUtils.GetRepresentationByte("00010101");
	public static final Byte C7 = BoardUtils.GetRepresentationByte("00010110");
	public static final Byte C8 = BoardUtils.GetRepresentationByte("00010111");
	public static final Byte D1 = BoardUtils.GetRepresentationByte("00011000");
	public static final Byte D2 = BoardUtils.GetRepresentationByte("00011001");
	public static final Byte D3 = BoardUtils.GetRepresentationByte("00011010");
	public static final Byte D4 = BoardUtils.GetRepresentationByte("00011011");
	public static final Byte D5 = BoardUtils.GetRepresentationByte("00011100");
	public static final Byte D6 = BoardUtils.GetRepresentationByte("00011101");
	public static final Byte D7 = BoardUtils.GetRepresentationByte("00011110");
	public static final Byte D8 = BoardUtils.GetRepresentationByte("00011111");
	public static final Byte E1 = BoardUtils.GetRepresentationByte("00100000");
	public static final Byte E2 = BoardUtils.GetRepresentationByte("00100001");
	public static final Byte E3 = BoardUtils.GetRepresentationByte("00100010");
	public static final Byte E4 = BoardUtils.GetRepresentationByte("00100011");
	public static final Byte E5 = BoardUtils.GetRepresentationByte("00100100");
	public static final Byte E6 = BoardUtils.GetRepresentationByte("00100101");
	public static final Byte E7 = BoardUtils.GetRepresentationByte("00100110");
	public static final Byte E8 = BoardUtils.GetRepresentationByte("00100111");
	public static final Byte F1 = BoardUtils.GetRepresentationByte("00101000");
	public static final Byte F2 = BoardUtils.GetRepresentationByte("00101001");
	public static final Byte F3 = BoardUtils.GetRepresentationByte("00101010");
	public static final Byte F4 = BoardUtils.GetRepresentationByte("00101011");
	public static final Byte F5 = BoardUtils.GetRepresentationByte("00101100");
	public static final Byte F6 = BoardUtils.GetRepresentationByte("00101101");
	public static final Byte F7 = BoardUtils.GetRepresentationByte("00101110");
	public static final Byte F8 = BoardUtils.GetRepresentationByte("00101111");
	public static final Byte G1 = BoardUtils.GetRepresentationByte("00110000");
	public static final Byte G2 = BoardUtils.GetRepresentationByte("00110001");
	public static final Byte G3 = BoardUtils.GetRepresentationByte("00110010");
	public static final Byte G4 = BoardUtils.GetRepresentationByte("00110011");
	public static final Byte G5 = BoardUtils.GetRepresentationByte("00110100");
	public static final Byte G6 = BoardUtils.GetRepresentationByte("00110101");
	public static final Byte G7 = BoardUtils.GetRepresentationByte("00110110");
	public static final Byte G8 = BoardUtils.GetRepresentationByte("00110111");
	public static final Byte H1 = BoardUtils.GetRepresentationByte("00111000");
	public static final Byte H2 = BoardUtils.GetRepresentationByte("00111001");
	public static final Byte H3 = BoardUtils.GetRepresentationByte("00111010");
	public static final Byte H4 = BoardUtils.GetRepresentationByte("00111011");
	public static final Byte H5 = BoardUtils.GetRepresentationByte("00111100");
	public static final Byte H6 = BoardUtils.GetRepresentationByte("00111101");
	public static final Byte H7 = BoardUtils.GetRepresentationByte("00111110");
	public static final Byte H8 = BoardUtils.GetRepresentationByte("00111111");

	private BoardUtils(){}	
	
	public final static void miseEnPlaceDesPieces(Board board) {
		board.put(BoardUtils.A2, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.B2, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.C2, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.D2, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.E2, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.F2, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.G2, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.H2, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.A1, Piece.creation(Piece.BLANC, Tour.getValueStatic()));
		board.put(BoardUtils.H1, Piece.creation(Piece.BLANC, Tour.getValueStatic()));
		board.put(BoardUtils.B1, Piece.creation(Piece.BLANC, Cavalier.getValueStatic()));
		board.put(BoardUtils.G1, Piece.creation(Piece.BLANC, Cavalier.getValueStatic()));
		board.put(BoardUtils.C1, Piece.creation(Piece.BLANC, Fou.getValueStatic()));
		board.put(BoardUtils.F1, Piece.creation(Piece.BLANC, Fou.getValueStatic()));
		board.put(BoardUtils.D1, Piece.creation(Piece.BLANC, Dame.getValueStatic()));
		board.put(BoardUtils.E1, Piece.creation(Piece.BLANC, Roi.getValueStatic()));
		
		board.put(BoardUtils.A7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.B7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.C7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.D7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.E7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.F7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.G7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.H7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.A8, Piece.creation(Piece.NOIR, Tour.getValueStatic()));
		board.put(BoardUtils.H8, Piece.creation(Piece.NOIR, Tour.getValueStatic()));
		board.put(BoardUtils.B8, Piece.creation(Piece.NOIR, Cavalier.getValueStatic()));
		board.put(BoardUtils.G8, Piece.creation(Piece.NOIR, Cavalier.getValueStatic()));
		board.put(BoardUtils.C8, Piece.creation(Piece.NOIR, Fou.getValueStatic()));
		board.put(BoardUtils.F8, Piece.creation(Piece.NOIR, Fou.getValueStatic()));
		board.put(BoardUtils.D8, Piece.creation(Piece.NOIR, Dame.getValueStatic()));
		board.put(BoardUtils.E8, Piece.creation(Piece.NOIR, Roi.getValueStatic()));
	}
	
	public final static void miseEnPlaceDesPiecesTest(Board board) {
		board.put(BoardUtils.D4, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.E2, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.F2, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.G2, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.H2, Piece.creation(Piece.BLANC, Pion.getValueStatic()));
		board.put(BoardUtils.E1, Piece.creation(Piece.BLANC, Roi.getValueStatic()));
		
		board.put(BoardUtils.A7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.B7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.C7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.D7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.E6, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.F7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.G7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.H7, Piece.creation(Piece.NOIR, Pion.getValueStatic()));
		board.put(BoardUtils.E8, Piece.creation(Piece.NOIR, Roi.getValueStatic()));
	}
	
	public final static boolean isPieceAdverseAtPosition(Byte positionAutre, Byte positionMoi, Board board){
		Byte autre = board.get(positionAutre);
		Byte moi = board.get(positionMoi);

		if(autre == null || moi == null){
			return false;
		}
		
		if(!Piece.isMemeCouleur(moi, autre)){
			return true;
		}
		
		return false;
	}
	
	public final static boolean isPieceAdverse(Byte autre, Byte moi){

		if(autre == null || moi == null){
			return false;
		}
		
		if(!Piece.isMemeCouleur(moi, autre)){
			return true;
		}
		
		return false;
	}

	public final static boolean isEnPositionInitial(Byte monEtat) {
		return !comparerEtat(monEtat, Piece.A_DEJA_JOUE);
	}

	private final static boolean comparerEtat(Byte monEtat, byte etat) {
		if(monEtat==null){
			return false;
		}
		return BoardUtils.memeEtat(monEtat, etat);		
	}

	public final static Byte getPosition(byte maPosition, byte colonne, byte ligne) {
		byte colonneMaPosition = (byte) (maPosition>>3);
		byte ligneMapPosition = (byte) (maPosition%8);
		
		if(colonneMaPosition+colonne > 7 || colonneMaPosition+colonne < 0)
			return null;
		if(ligneMapPosition+ligne > 7 || ligneMapPosition+ligne < 0)
			return null;

		return (byte) (maPosition + (BoardUtils.NBRE_CASES_COLONNE * colonne) + ligne);
	}
	
	public final static boolean isPositionValide(Byte maPosition){		
		if( maPosition >= 0 && maPosition < BoardUtils.NBRE_CASES_BOARD ){
			return true;
		}
		return false;
	}
	
	public final static boolean isCaseEnEchec(Byte position, Byte couleur, Board board) {
		List<Byte> positionsAttaquees = null;
		if(board.getPositionsAttaquees() == null){
			positionsAttaquees = new ArrayList<Byte>();
			for(byte i = 0 ; i < BoardUtils.NBRE_CASES_BOARD ; i++){
				if(board.get(i) != null && !Piece.isMemeCouleur(board.get(i),couleur)){
					Byte positionCourante = i;
					positionsAttaquees.addAll(Piece.getPositionsAttaques(positionCourante, board));
				}
			}
			
			board.setPositionsAttaquees(positionsAttaquees);
			
			if(positionsAttaquees.contains(position)){
				return true;
			}
		}else{
			if(board.getPositionsAttaquees().contains(position)){
				return true;
			}
		}
		
		return false;
	}
	
	public final static List<Byte> getPiecesDUnJoueur(byte couleur, Board board){
		ArrayList<Byte> piecesDUnJoueur = new ArrayList<Byte>();
		Byte piece;
		for(byte position = 0 ; position < BoardUtils.NBRE_CASES_BOARD ; position++){
			piece = board.get(position);
			if(Piece.isMemeCouleur(piece, couleur)){
					piecesDUnJoueur.add(position);
			}
		}
		return piecesDUnJoueur;
	}
	
	public final static byte GetRepresentationByte(String valeurAConvertir){
		return (new Integer(Integer.parseInt(valeurAConvertir,2))).byteValue();
	}
	
	public final static boolean memeEtat(Byte etat, Byte... aComparerACesEtats){
		if(etat == null){
			return false;
		}
		
		byte aComparerACetEtat = ETAT_BASE;	
		
		for(byte etatAComparer : aComparerACesEtats){
			aComparerACetEtat |= etatAComparer;
		}
		return (etat & aComparerACetEtat) == aComparerACetEtat;
	}
	
	public final static boolean isPetitRoque(Byte positionInitiale, Byte positionFinale){
		return (positionFinale - positionInitiale == 2 * BoardUtils.NBRE_CASES_COLONNE);		
	}
	
	public final static boolean isGrandRoque(Byte positionInitiale, Byte positionFinale){
		return positionFinale - positionInitiale == - 2 * BoardUtils.NBRE_CASES_COLONNE;
	}
	
	public final static void setAJoue(Byte position, Board board){
		board.put(position, (byte)(board.get(position)|Piece.A_DEJA_JOUE));
	}

	public final static List<List<List<Byte>>> PrendrePionEnPassant(Byte positionFinale, Byte positionInitiale, Board board) {
		Byte pionMange = (byte) (positionFinale - Piece.avancer((byte)1, board.get(positionInitiale)));
		List<List<List<Byte>>> coupARetenir = retenirBoard(pionMange, pionMange, board);
		board.remove(pionMange);	
		return coupARetenir;
	}

	public final static Byte[] traduirePositionHumaineEnPositionSysteme(
			String positionDepart, String positionFin) {
		Byte[] resultat = new Byte[2];
		if(positionDepart == null || positionDepart.length() != 2){
			return null;
		}
		if(positionFin == null || positionFin.length() != 2){
			return null;
		}
		
		char colonneDepart = positionDepart.charAt(0);
		char ligneDepart = positionDepart.charAt(1);
		char colonneFin = positionFin.charAt(0);
		char ligneFin = positionFin.charAt(1);
		
		String colonnesValides = "abcdefghABCDEFGH";
		String lignesValides = "12345678";
		
		byte positionColonneDepart = (byte) colonnesValides.indexOf(colonneDepart);
		byte positionLigneDepart = (byte) lignesValides.indexOf(ligneDepart);
		byte positionColonneFin = (byte) colonnesValides.indexOf(colonneFin);
		byte positionLigneFin = (byte) lignesValides.indexOf(ligneFin);
		
		if(positionColonneDepart > 7){
			positionColonneDepart = (byte) (positionColonneDepart - 8);			
		}
		
		if(positionColonneFin > 7){
			positionColonneFin = (byte) (positionColonneFin - 8);
		}
		
		if(colonnesValides.indexOf(colonneDepart) == -1 
				|| colonnesValides.indexOf(colonneFin) == -1 
				|| lignesValides.indexOf(ligneDepart) == -1
				|| lignesValides.indexOf(ligneFin) == -1){
			return null;
		}
		
		byte positionBoardDepart = (byte) (positionColonneDepart * 8 + positionLigneDepart);
		byte positionBoardArrivee = (byte) (positionColonneFin * 8 + positionLigneFin);
		
		resultat[0] = positionBoardDepart;
		resultat[1] = positionBoardArrivee;
		
		return resultat;
	}
	
	public final static void montrerLeBoard(Board board){
		String[][] table = new String[8][8];
		String representation = "";
		for(byte i = 0 ; i < 64 ; i++){
			if(board.get(i)==null){
				representation = "  ";
			}else if(memeEtat(board.get(i), Pion.getValueStatic(), Piece.BLANC)){
				representation = "Pb";
			}else if(memeEtat(board.get(i), Cavalier.getValueStatic(), Piece.BLANC)){
				representation = "Cb";
			}else if(memeEtat(board.get(i), Fou.getValueStatic(), Piece.BLANC)){
				representation = "Fb";
			}else if(memeEtat(board.get(i), Tour.getValueStatic(), Piece.BLANC)){
				representation = "Tb";
			}else if(memeEtat(board.get(i), Dame.getValueStatic(), Piece.BLANC)){
				representation = "Db";
			}else if(memeEtat(board.get(i), Roi.getValueStatic(), Piece.BLANC)){
				representation = "Rb";
			}else if(memeEtat(board.get(i), Pion.getValueStatic(), Piece.NOIR)){
				representation = "Pn";
			}else if(memeEtat(board.get(i), Cavalier.getValueStatic(), Piece.NOIR)){
				representation = "Cn";
			}else if(memeEtat(board.get(i), Fou.getValueStatic(), Piece.NOIR)){
				representation = "Fn";
			}else if(memeEtat(board.get(i), Tour.getValueStatic(), Piece.NOIR)){
				representation = "Tn";
			}else if(memeEtat(board.get(i), Dame.getValueStatic(), Piece.NOIR)){
				representation = "Dn";
			}else if(memeEtat(board.get(i), Roi.getValueStatic(), Piece.NOIR)){
				representation = "Rn";
			}
			
			table[i>>3][i%8] = representation;
		}
		
		String ligne = "";
		for(int i = 0 ; i < 8 ; i++){
			ligne="";
			for(int j = 0 ; j < 8 ; j++){
				ligne+=table[j][7-i];
			}
			System.out.println(ligne);
		}
			
	}
	
	public final static boolean isMate(Byte couleur, Board board){
		
		byte leRoi = (byte) (Roi.getValueStatic() | couleur);
				
		if(board.getBoardAEvaluer() == null || board.getBoardAEvaluer().size()==0){
			return false;
		}
		
		for(Board board1 : board.getBoardAEvaluer()){
			if(board1.getBoardAEvaluer() == null || board1.getBoardAEvaluer().size() == 0){
				return false;
			}
			for(Board board2 : board1.getBoardAEvaluer()){
				for(Byte value : board2.getPieces()){
					if(BoardUtils.memeEtat(value, leRoi)){
						return false;
					}					
				}				
			}
		}
		return true;
	}
	
	public final static boolean isNulle(byte couleur, Board board, PartieNulle evaluateurDePartieNulle){
		if(isNullePar50CoupsOu3PositionsIdentiques(board, evaluateurDePartieNulle) || isNulleParPat(couleur, board)){
			return true;
		}
		return false;
	}
	
	private final static boolean isNullePar50CoupsOu3PositionsIdentiques(Board board, PartieNulle evaluateurDePartieNulle){
		StringBuilder representationDunEchiquier = new StringBuilder();
		if(board.getBoardSize()!=evaluateurDePartieNulle.getNbreDePieceSurLeGame()){
			evaluateurDePartieNulle.setNbreDePieceSurLeGame(board.getBoardSize());
			evaluateurDePartieNulle.setCoupJouePourAnnulerEn50Coups(0);
			evaluateurDePartieNulle.setNbrePositionsIdentiques(new HashMap<String, Byte>());
		}else{
			TreeSet<Byte> positionOrdonnees = new TreeSet<Byte>(board.getPositions());
			for(Byte position: positionOrdonnees) {
				representationDunEchiquier.append(position).append(board.get(position));
			}
			evaluateurDePartieNulle.incrementCoupJouePourAnnulerEn50Coups();
		}
		
		if(evaluateurDePartieNulle.getCoupJouePourAnnulerEn50Coups() > 49){
			System.out.println("50 coups");
			return true;
		}
		
		if(evaluateurDePartieNulle.getNbrePositionsIdentiques(representationDunEchiquier.toString())==null){
			evaluateurDePartieNulle.putNbrePositionsIdentiques(representationDunEchiquier.toString(), (byte)1);
		}else{
			evaluateurDePartieNulle.putNbrePositionsIdentiques(representationDunEchiquier.toString(), (byte)(evaluateurDePartieNulle.getNbrePositionsIdentiques(representationDunEchiquier.toString())+1));
		}
		
		if(evaluateurDePartieNulle.getNbrePositionsIdentiques().values().contains((byte)3)){
			System.out.println("3 repetitions");
			return true;
		}
		return false;
	}
	
	public final static boolean isNulleParPat(byte couleur, Board board){
		for(Byte position : board.getPositions()){
			if(Roi.isComme(position, Roi.getValueStatic()) && Piece.isMemeCouleur(board.get(position), couleur) && !isCaseEnEchec(position, couleur, board)){
				for(Board board1 :board.getBoardAEvaluer()){
					for(Board board2 : board1.getBoardAEvaluer()){
						if(board2.getPieces().contains(couleur|Roi.getValueStatic())|| board2.getPieces().contains(couleur|Roi.getValueStatic()|Piece.A_DEJA_JOUE)){
							return false;
						}
					}
				}
			}else{
				return false;
			}
		}
		System.out.println("pat");
		return true;
	}
	
	public final static EtatDUnBoard getBoardApresUnCoup(Byte positionInitiale, Byte positionFinale, Board board){
		EtatDUnBoard etatDUnBoard = creationEtatBoard(board);
		
		Byte enPositionInitial = board.get(positionInitiale);
		if(Roi.isComme(enPositionInitial, Roi.getValueStatic())){
			
			iRoiT(etatDUnBoard, positionInitiale, positionFinale, board);
			
		}else if(Pion.isComme(enPositionInitial, Pion.getValueStatic())){
			// si le pion va dans une case vide, c est qu il avance ou fait une prise en passant, si la distance 
			// parcouru sur le byte de position est plus grande que 2 cases, cest qu il change de colonne donc qu il 
			// s agit d une prise en passant 
			iPionT(etatDUnBoard, positionInitiale, positionFinale, board);
			
		}else{
			iNormalT(etatDUnBoard, positionInitiale, positionFinale, board);
		}
		
		return etatDUnBoard;
	}
	
	private static EtatDUnBoard creationEtatBoard(Board board) {
		return new EtatDUnBoard(board.getPremierParent());
	}

	private static void iNormalT(EtatDUnBoard etatDUnBoard,
			Byte positionInitiale, Byte positionFinale, Board board) {
		addCoup(etatDUnBoard, bouger(positionInitiale, positionFinale, board));
		addBoard(etatDUnBoard, board);
	}

	private static void addBoard(EtatDUnBoard etatDUnBoard, Board board) {
		etatDUnBoard.addBoard(board);
	}

	private static void addCoup(EtatDUnBoard etatDUnBoard,
			List<List<List<Byte>>> bouger) {
		etatDUnBoard.addCoupARetenir(bouger);
	}

	private static void iPionT(EtatDUnBoard etatDUnBoard,
			Byte positionInitiale, Byte positionFinale, Board board) {
		if(board.get(positionFinale) == null && (positionFinale > positionInitiale+2 || positionFinale < positionInitiale-2)){
			etatDUnBoard.addCoupARetenir(BoardUtils.PrendrePionEnPassant(positionFinale, positionInitiale, board));
		}
		
		etatDUnBoard.addCoupARetenir(bouger(positionInitiale, positionFinale, board));
		
		if(positionFinale == BoardUtils.A1 || positionFinale == BoardUtils.B1 || positionFinale == BoardUtils.C1 || positionFinale == BoardUtils.D1 || positionFinale == BoardUtils.E1 || positionFinale == BoardUtils.F1 || positionFinale == BoardUtils.G1 || positionFinale == BoardUtils.H1){
			etatDUnBoard.addBoards(promotion(positionFinale, Piece.NOIR, board));
		}else if(positionFinale == BoardUtils.A8 || positionFinale == BoardUtils.B8 || positionFinale == BoardUtils.C8 || positionFinale == BoardUtils.D8 || positionFinale == BoardUtils.E8 || positionFinale == BoardUtils.F8 || positionFinale == BoardUtils.G8 || positionFinale == BoardUtils.H8){
			etatDUnBoard.addBoards(promotion(positionFinale, Piece.BLANC, board));
		}else{
			etatDUnBoard.addBoard(board);
		}
	}

	private static void iRoiT(EtatDUnBoard etatDUnBoard, Byte positionInitiale,
			Byte positionFinale, Board board) {
		etatDUnBoard.addCoupARetenir(bouger(positionInitiale, positionFinale, board));
		if(BoardUtils.isPetitRoque(positionInitiale, positionFinale)){
			etatDUnBoard.addCoupARetenir(bouger((byte) (positionFinale+BoardUtils.NBRE_CASES_COLONNE), (byte) (positionFinale-BoardUtils.NBRE_CASES_COLONNE), board));
		}
		if(BoardUtils.isGrandRoque(positionInitiale, positionFinale)){
			etatDUnBoard.addCoupARetenir(bouger((byte) (positionFinale-2*BoardUtils.NBRE_CASES_COLONNE), (byte) (positionFinale+BoardUtils.NBRE_CASES_COLONNE), board));
		}
		etatDUnBoard.addBoard(board);
		
	}

	public final static List<Board> promotion(Byte positionFinale, byte couleur, Board board) {
		List<Board> resultat = new ArrayList<Board>();
		Board board1 = board.clone();
		board1.put(positionFinale, Piece.creation(couleur, Cavalier.getValueStatic()));
		Board board2 = board.clone();
		board2.put(positionFinale, Piece.creation(couleur, Fou.getValueStatic()));
		Board board3 = board.clone();
		board3.put(positionFinale, Piece.creation(couleur, Tour.getValueStatic()));
		Board board4 = board.clone();
		board4.put(positionFinale, Piece.creation(couleur, Dame.getValueStatic()));
		
		resultat.add(board1);
		resultat.add(board2);
		resultat.add(board3);
		resultat.add(board4);
		
		return resultat;
	}

	public final static List<List<List<Byte>>> bouger(byte positionInitiale, byte positionFinale, Board board) {
		List<List<List<Byte>>> coupsARetenir = retenirBoard(positionInitiale, positionFinale, board);
		byte etatUneFoisDeplace = board.get(positionInitiale);
		etatUneFoisDeplace |= Piece.A_DEJA_JOUE;
		
		if(Piece.isComme(etatUneFoisDeplace, Pion.getValueStatic())){
			if(Math.abs(positionFinale-positionInitiale) > 1){
				etatUneFoisDeplace |= Piece.A_AVANCER_DE_2;
			}else{
				etatUneFoisDeplace &= Piece.NA_PLUS_AVANCER_DE_2;
			}
		}		
		
		board.put(positionFinale, etatUneFoisDeplace);
		board.remove(positionInitiale);
		
		return coupsARetenir;
	}
	
	public final static List<List<List<Byte>>> retenirBoard(byte positionInitiale, byte positionFinale, Board board){
		
		Byte enPositionInitial = board.get(positionInitiale);
		Byte enPositionFinal = board.get(positionFinale);
		
		List<List<List<Byte>>> listeDeCoupARetenir = new ArrayList<>();
		List<List<Byte>> coup1 = new ArrayList<>();
		List<Byte> coupI = new ArrayList<>();
		List<Byte> coupF = new ArrayList<>();
		
		coupI.add(positionInitiale);
		coupI.add(enPositionInitial);
		coupF.add(positionFinale);
		if(enPositionFinal != null){
			coupF.add(enPositionFinal);
		}
				
		coup1.add(coupI);
		coup1.add(coupF);

		listeDeCoupARetenir.add(coup1);	
		return listeDeCoupARetenir;
	}
	
	public final static void addBoardAEvaluer(List<Board> boards, Board board){
		board.addBoards(boards);
		for(Board boardEnfant : boards){
			if(board.getPremierParent() == null){
				boardEnfant.setPremierParent(boardEnfant);
			}else{
				boardEnfant.setPremierParent(board.getPremierParent());
			}
		}
	}

	public final static Board remonteLeCoup(Board board,			
			EtatDUnBoard etat) {
		for(List<List<Byte>> coupARetenir : etat.getCoupARetenir()){
			List<Byte> coupI = coupARetenir.get(0);
			List<Byte> coupF = coupARetenir.get(1);
			if(coupF.size()>1){
				board.put(coupF.get(0), coupF.get(1));
			}else{
				board.put(coupF.get(0), null);
			}
			board.put(coupI.get(0), coupI.get(1));
		}
		board.setPremierParent(etat.getPremierParent());
		board.setPositionsAttaquees(null);
		
		return board;
	}
	
	public final static boolean isEnFinale(Board board){
		Collection<Byte> pieces = board.getPieces();
		
		boolean dame = false;
		boolean tour = false;
		boolean cavalier = false; 
		boolean fou = false;
		
		for(byte piece : pieces){
			if(Piece.isComme(piece, Dame.getValueStatic())){
				dame = true;
			}else if(Piece.isComme(piece, Tour.getValueStatic())){
				tour = true;
			}else if(Piece.isComme(piece, Fou.getValueStatic())){
				fou = true;
			}else if(Piece.isComme(piece, Cavalier.getValueStatic())){
				cavalier = true;
			}
		}
		return (dame && cavalier && !fou && !tour)||(dame && !cavalier && fou && !tour)||(!dame);
	}
	
}
