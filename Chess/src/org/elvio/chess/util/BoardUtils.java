package org.elvio.chess.util;

import java.util.ArrayList;
import java.util.List;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.pieces.Cavalier;
import org.elvio.chess.elements.util.CoupARetenir;
import org.elvio.chess.elements.pieces.Dame;
import org.elvio.chess.process.EtatDUnBoard;
import org.elvio.chess.elements.pieces.Fou;
import org.elvio.chess.elements.pieces.Piece;
import org.elvio.chess.elements.pieces.Pion;
import org.elvio.chess.elements.pieces.Roi;
import org.elvio.chess.elements.pieces.Tour;

public class BoardUtils {

	public static final int NBRE_CASES_COLONNE = 8;
	public static final int NBRE_CASES_BOARD = 64;
	private static final int ETAT_BASE = BoardUtils.GetRepresentationByte("00000000");
	private static final int MASQUE_MODULO_8 = BoardUtils.GetRepresentationByte("00000111");
	
	public static final int A1 = BoardUtils.GetRepresentationByte("00000000");
	public static final int A2 = BoardUtils.GetRepresentationByte("00000001");	
	public static final int A3 = BoardUtils.GetRepresentationByte("00000010");
	public static final int A4 = BoardUtils.GetRepresentationByte("00000011");
	public static final int A5 = BoardUtils.GetRepresentationByte("00000100");
	public static final int A6 = BoardUtils.GetRepresentationByte("00000101");
	public static final int A7 = BoardUtils.GetRepresentationByte("00000110");
	public static final int A8 = BoardUtils.GetRepresentationByte("00000111");
	public static final int B1 = BoardUtils.GetRepresentationByte("00001000");
	public static final int B2 = BoardUtils.GetRepresentationByte("00001001");
	public static final int B3 = BoardUtils.GetRepresentationByte("00001010");
	public static final int B4 = BoardUtils.GetRepresentationByte("00001011");
	public static final int B5 = BoardUtils.GetRepresentationByte("00001100");
	public static final int B6 = BoardUtils.GetRepresentationByte("00001101");
	public static final int B7 = BoardUtils.GetRepresentationByte("00001110");
	public static final int B8 = BoardUtils.GetRepresentationByte("00001111");
	public static final int C1 = BoardUtils.GetRepresentationByte("00010000");
	public static final int C2 = BoardUtils.GetRepresentationByte("00010001");
	public static final int C3 = BoardUtils.GetRepresentationByte("00010010");
	public static final int C4 = BoardUtils.GetRepresentationByte("00010011");
	public static final int C5 = BoardUtils.GetRepresentationByte("00010100");
	public static final int C6 = BoardUtils.GetRepresentationByte("00010101");
	public static final int C7 = BoardUtils.GetRepresentationByte("00010110");
	public static final int C8 = BoardUtils.GetRepresentationByte("00010111");
	public static final int D1 = BoardUtils.GetRepresentationByte("00011000");
	public static final int D2 = BoardUtils.GetRepresentationByte("00011001");
	public static final int D3 = BoardUtils.GetRepresentationByte("00011010");
	public static final int D4 = BoardUtils.GetRepresentationByte("00011011");
	public static final int D5 = BoardUtils.GetRepresentationByte("00011100");
	public static final int D6 = BoardUtils.GetRepresentationByte("00011101");
	public static final int D7 = BoardUtils.GetRepresentationByte("00011110");
	public static final int D8 = BoardUtils.GetRepresentationByte("00011111");
	public static final int E1 = BoardUtils.GetRepresentationByte("00100000");
	public static final int E2 = BoardUtils.GetRepresentationByte("00100001");
	public static final int E3 = BoardUtils.GetRepresentationByte("00100010");
	public static final int E4 = BoardUtils.GetRepresentationByte("00100011");
	public static final int E5 = BoardUtils.GetRepresentationByte("00100100");
	public static final int E6 = BoardUtils.GetRepresentationByte("00100101");
	public static final int E7 = BoardUtils.GetRepresentationByte("00100110");
	public static final int E8 = BoardUtils.GetRepresentationByte("00100111");
	public static final int F1 = BoardUtils.GetRepresentationByte("00101000");
	public static final int F2 = BoardUtils.GetRepresentationByte("00101001");
	public static final int F3 = BoardUtils.GetRepresentationByte("00101010");
	public static final int F4 = BoardUtils.GetRepresentationByte("00101011");
	public static final int F5 = BoardUtils.GetRepresentationByte("00101100");
	public static final int F6 = BoardUtils.GetRepresentationByte("00101101");
	public static final int F7 = BoardUtils.GetRepresentationByte("00101110");
	public static final int F8 = BoardUtils.GetRepresentationByte("00101111");
	public static final int G1 = BoardUtils.GetRepresentationByte("00110000");
	public static final int G2 = BoardUtils.GetRepresentationByte("00110001");
	public static final int G3 = BoardUtils.GetRepresentationByte("00110010");
	public static final int G4 = BoardUtils.GetRepresentationByte("00110011");
	public static final int G5 = BoardUtils.GetRepresentationByte("00110100");
	public static final int G6 = BoardUtils.GetRepresentationByte("00110101");
	public static final int G7 = BoardUtils.GetRepresentationByte("00110110");
	public static final int G8 = BoardUtils.GetRepresentationByte("00110111");
	public static final int H1 = BoardUtils.GetRepresentationByte("00111000");
	public static final int H2 = BoardUtils.GetRepresentationByte("00111001");
	public static final int H3 = BoardUtils.GetRepresentationByte("00111010");
	public static final int H4 = BoardUtils.GetRepresentationByte("00111011");
	public static final int H5 = BoardUtils.GetRepresentationByte("00111100");
	public static final int H6 = BoardUtils.GetRepresentationByte("00111101");
	public static final int H7 = BoardUtils.GetRepresentationByte("00111110");
	public static final int H8 = BoardUtils.GetRepresentationByte("00111111");

	private BoardUtils(){}	
	
	public final static void miseEnPlaceDesPieces(Board board, String configuration, boolean configurationStatic) {
        if(configurationStatic) setBoard(board, creationDUnBoardConfigure());
        else{
            if(configuration == null)   miseEnPlaceDesPieces(board);
            else    setBoard(board, configuration);
        }
	}

    //todo mettre le get valuestatic dans la methode creation!!!
    //todo degager la reference inutile a boardutils on y est dedans!!!
    //todo faire une reference a blanc et noir dans une constante referencant Piece!!!
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

    /**
     * dans le cadre de test cette methode met en place les pieces
     */
	private final static String creationDUnBoardConfigure() {
        Board board = new Board();
        board.put(BoardUtils.C2, Piece.creation(Roi.getValueStatic(), Piece.BLANC, Piece.A_DEJA_JOUE));
        board.put(BoardUtils.G2, Piece.creation(Pion.getValueStatic(), Piece.BLANC));
        board.put(BoardUtils.A3, Piece.creation(Pion.getValueStatic(), Piece.BLANC, Piece.A_DEJA_JOUE));
        board.put(BoardUtils.B4, Piece.creation(Pion.getValueStatic(), Piece.BLANC, Piece.A_DEJA_JOUE));
        board.put(BoardUtils.C4, Piece.creation(Roi.getValueStatic(), Piece.NOIR, Piece.A_DEJA_JOUE));
        board.put(BoardUtils.F4, Piece.creation(Pion.getValueStatic(), Piece.BLANC, Piece.A_DEJA_JOUE));
        board.put(BoardUtils.H4, Piece.creation(Pion.getValueStatic(), Piece.BLANC, Piece.A_DEJA_JOUE));
        board.put(BoardUtils.F5, Piece.creation(Tour.getValueStatic(), Piece.BLANC, Piece.A_DEJA_JOUE));
        board.put(BoardUtils.H5, Piece.creation(Pion.getValueStatic(), Piece.NOIR, Piece.A_DEJA_JOUE));
        board.put(BoardUtils.H6, Piece.creation(Fou.getValueStatic(), Piece.BLANC, Piece.A_DEJA_JOUE));
        return getBoardConfiguration(board);
	}

	public final static boolean isPieceAdverseAtPosition(int positionAutre, int positionMoi, Board board){
		
		if(positionAutre == -1){
			return false;
		}
		Byte autre = board.get(positionAutre);
		Byte moi = board.get(positionMoi);

		if(autre == null || moi == null){
			return false;
		}
		
		return(!Piece.isMemeCouleur(moi, autre));
	}
	
	public final static boolean isEnPositionInitial(Byte monEtat) {
		return !comparerEtat(monEtat, Piece.A_DEJA_JOUE);
	}

	private static boolean comparerEtat(Byte monEtat, byte etat) {
		if(monEtat==null){
			return false;
		}
		return BoardUtils.memeEtat(monEtat, etat);		
	}

	public final static int getPosition(int maPosition, int colonne, int ligne) {
		
		int colonneMaPosition = maPosition>>3;
		int ligneMapPosition = modulo8(maPosition);
		
		if((colonneMaPosition+colonne) >> 3 != 0){			
			return -1;
		}
		if((ligneMapPosition+ligne) >> 3 != 0)
			return -1;

		return (maPosition + (colonne << 3) + ligne);
	}
	
	public final static int GetRepresentationByte(String valeurAConvertir){
		return (new Integer(Integer.parseInt(valeurAConvertir,2)));
	}
	
	public final static boolean memeEtat(Byte etat, Byte... aComparerACesEtats){
		if(etat == null){
			return false;
		}
		
		int aComparerACetEtat = ETAT_BASE;	
		
		for(byte etatAComparer : aComparerACesEtats){
			aComparerACetEtat |= etatAComparer;
		}
		return (etat & aComparerACetEtat) == aComparerACetEtat;
	}
	
	public final static boolean isPetitRoque(int differentiel){
		return differentiel == 16;		
	}
	
	public final static boolean isGrandRoque(int differentiel){
		return differentiel == -16;
	}

	public final static CoupARetenir PrendrePionEnPassant(int positionFinale, int positionInitiale, Byte enPositionInitiale, Board board) {
		int positionMange = positionFinale - Piece.avancer(1, enPositionInitiale);
		Byte pionMange = board.get(positionMange);
		CoupARetenir coupARetenir = memoriserLeBoard(positionMange, pionMange, positionMange, pionMange, board);
		board.remove(positionMange);	
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
		System.out.println("etape 1");
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
		
		System.out.println("etape 2");
		
		byte positionBoardDepart = (byte) (positionColonneDepart * 8 + positionLigneDepart);
		byte positionBoardArrivee = (byte) (positionColonneFin * 8 + positionLigneFin);
		
		resultat[0] = positionBoardDepart;
		resultat[1] = positionBoardArrivee;
		System.out.println("etape 3");
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
			
			table[i>>3][modulo8(i)] = representation;
		}
		
		String ligne;
		for(int i = 0 ; i < 8 ; i++){
			ligne="";
			for(int j = 0 ; j < 8 ; j++){
				ligne+=table[j][7-i];
			}
			System.out.println(ligne);
		}
			
	}
		
	public final static EtatDUnBoard getBoardApresUnCoup(int positionInitiale, Byte pieceEnPositionInitial, int positionFinale, Board board, EtatDUnBoard etat){
		EtatDUnBoard etatDUnBoard;
		if(etat == null){
			etatDUnBoard = creationEtatBoard(board);
		}else{
			etatDUnBoard = etat;
			etatDUnBoard.init();
		}
		
		Byte pieceEnPositionFinale = board.get(positionFinale);
		if(pieceEnPositionFinale != null && Piece.isComme(pieceEnPositionFinale, Roi.getValueStatic())){
			if(Piece.isBlanc(pieceEnPositionFinale)){
				etatDUnBoard.setRoiBlancDevore(true);
			}else{
				etatDUnBoard.setRoiNoirDevore(true);
			}
		}
		
		if(Pion.isComme(pieceEnPositionInitial, Pion.getValueStatic())){
			// si le pion va dans une case vide, c est qu il avance ou fait une prise en passant, si la distance 
			// parcouru sur le byte de position est plus grande que 2 cases, cest qu il change de colonne donc qu il 
			// s agit d une prise en passant 
			mouvementDePion(etatDUnBoard, positionInitiale, pieceEnPositionInitial, positionFinale, pieceEnPositionFinale, board);
			
		}else if(!Roi.isComme(pieceEnPositionInitial, Roi.getValueStatic())){
			mouvementDeNoblesse(etatDUnBoard, positionInitiale, pieceEnPositionInitial, positionFinale, pieceEnPositionFinale, board);
		}else{
			mouvementDuRoi(etatDUnBoard, positionInitiale, pieceEnPositionInitial, positionFinale, pieceEnPositionFinale, board);
		}
		
		return etatDUnBoard;
	}
	
	private static EtatDUnBoard creationEtatBoard(Board board) {
		return new EtatDUnBoard(board.getPremierCoupAJouer());
	}

	private static void mouvementDeNoblesse(EtatDUnBoard etatDUnBoard,
			int positionInitiale, Byte enPositionInitial, int positionFinale, Byte enPositionFinale, Board board) {
		addCoup(etatDUnBoard, bouger(positionInitiale, enPositionInitial, positionFinale, enPositionFinale, board));
		addBoard(etatDUnBoard, board);
	}

	private static void addBoard(EtatDUnBoard etatDUnBoard, Board board) {
		etatDUnBoard.addBoard(board);
	}

	private static void addCoup(EtatDUnBoard etatDUnBoard,
			CoupARetenir bouger) {
		etatDUnBoard.addCoupARetenir(bouger);
	}

	
	private static void mouvementDePion(EtatDUnBoard etatDUnBoard,
			int positionInitiale, Byte enPositionInitiale, int positionFinale, Byte enPositionFinale, Board board) {
		
		if(board.get(positionFinale) == null && (positionFinale >> 3) != (positionInitiale >> 3)){
			etatDUnBoard.addCoupARetenir(BoardUtils.PrendrePionEnPassant(positionFinale, positionInitiale, enPositionInitiale, board));
		}
		
		etatDUnBoard.addCoupARetenir(bouger(positionInitiale, enPositionInitiale, positionFinale, enPositionFinale, board));
		int ligne = modulo8(positionFinale);
		if(ligne == 0){
			etatDUnBoard.addBoards(promotion(positionFinale, Piece.NOIR, board));
		}else if(ligne == 7){
			etatDUnBoard.addBoards(promotion(positionFinale, Piece.BLANC, board));
		}else{
			etatDUnBoard.addBoard(board);
		}
	}
	
	private static void mouvementDuRoi(EtatDUnBoard etatDUnBoard, int positionInitiale, Byte enPositionInitial,
			int positionFinale, Byte enPositionFinal, Board board) {
		etatDUnBoard.addCoupARetenir(bouger(positionInitiale, enPositionInitial, positionFinale, enPositionFinal, board));
		int differentiel = positionFinale - positionInitiale;
		if(BoardUtils.isPetitRoque(differentiel)){
			int nouvellePositionInitiale = positionFinale + 8;
			int nouvellePositionFinale = positionFinale - 8;
			etatDUnBoard.addCoupARetenir(bouger(nouvellePositionInitiale, board.get(nouvellePositionInitiale), nouvellePositionFinale, board.get(nouvellePositionFinale), board));
		}else if(BoardUtils.isGrandRoque(differentiel)){
			int nouvellePositionInitiale = positionFinale - 16;
			int nouvellePositionFinale = positionFinale + 8;
			etatDUnBoard.addCoupARetenir(bouger(nouvellePositionInitiale, board.get(nouvellePositionInitiale), nouvellePositionFinale, board.get(nouvellePositionFinale), board));
		}
		etatDUnBoard.addBoard(board);
	}
	
	public final static Board[] promotion(int positionFinale, byte couleur, Board board) {
		Board[] resultat = new Board[4];

		resultat[0] = board.clone();
		resultat[0].put(positionFinale, Piece.creation(couleur, Cavalier.getValueStatic()));
		
		resultat[1] = board.clone();
		resultat[1].put(positionFinale, Piece.creation(couleur, Fou.getValueStatic()));
		
		resultat[2] = board.clone();
		resultat[2].put(positionFinale, Piece.creation(couleur, Tour.getValueStatic()));
		
		resultat[3] = board.clone();
		resultat[3].put(positionFinale, Piece.creation(couleur, Dame.getValueStatic()));
		
		return resultat;
	}

	public final static CoupARetenir bouger(int positionInitiale, Byte enPositionInitial, int positionFinale, Byte enPositionFinal, Board board) {
		CoupARetenir coupsARetenir = memoriserLeBoard(positionInitiale, enPositionInitial, positionFinale, enPositionFinal, board);
		byte etatUneFoisDeplace = enPositionInitial;
		etatUneFoisDeplace |= Piece.A_DEJA_JOUE;
		
		if(Piece.isComme(etatUneFoisDeplace, Pion.getValueStatic())){
			int differentiel = positionFinale-positionInitiale;
			if(differentiel  == 2 || differentiel == -2){
				etatUneFoisDeplace |= Piece.A_AVANCER_DE_2;
			}
		}		
		
		board.put(positionFinale, etatUneFoisDeplace);
		board.remove(positionInitiale);
		
		return coupsARetenir;
	}
	
	public final static CoupARetenir memoriserLeBoard(int positionInitiale, Byte pieceEnPositionInitiale, int positionFinale, Byte pieceEnPositionFinale, Board board){
		
		CoupARetenir coup = new CoupARetenir();
		coup.setPositionCoupInitial(positionInitiale);
		coup.setEtatCoupInitial(pieceEnPositionInitiale);
		coup.setPositionCoupFinal(positionFinale);
		coup.setEtatCoupFinal(pieceEnPositionFinale);
		
		return coup;
	}
	
	public final static Board revenirAuBoardInitial(Board board,			
			EtatDUnBoard etat) {
		for(CoupARetenir coupARetenir : etat.getCoupARetenir()){
			board.put(coupARetenir.getPositionCoupFinal(), coupARetenir.getEtatCoupFinal());
			board.put(coupARetenir.getPositionCoupInitial(), coupARetenir.getEtatCoupInitial());
		}
		board.setPremierCoupAJouer(etat.getPremierParent());
		
		return board;
	}
	
	
	public final static String getLitteralPosition(int position){
		switch(position){
			case 0 : return "A1";
			case 1 : return "A2";
			case 2 : return "A3";
			case 3 : return "A4";
			case 4 : return "A5";
			case 5 : return "A6";
			case 6 : return "A7";
			case 7 : return "A8";
			case 8 : return "B1";
			case 9 : return "B2";
			case 10 : return "B3";
			case 11 : return "B4";
			case 12 : return "B5";
			case 13 : return "B6";
			case 14 : return "B7";
			case 15 : return "B8";
			case 16 : return "C1";
			case 17 : return "C2";
			case 18 : return "C3";
			case 19 : return "C4";
			case 20 : return "C5";
			case 21 : return "C6";
			case 22 : return "C7";
			case 23 : return "C8";
			case 24 : return "D1";
			case 25 : return "D2";
			case 26 : return "D3";
			case 27 : return "D4";
			case 28 : return "D5";
			case 29 : return "D6";
			case 30 : return "D7";
			case 31 : return "D8";
			case 32 : return "E1";
			case 33 : return "E2";
			case 34 : return "E3";
			case 35 : return "E4";
			case 36 : return "E5";
			case 37 : return "E6";
			case 38 : return "E7";
			case 39 : return "E8";
			case 40 : return "F1";
			case 41 : return "F2";
			case 42 : return "F3";
			case 43 : return "F4";
			case 44 : return "F5";
			case 45 : return "F6";
			case 46 : return "F7";
			case 47 : return "F8";
			case 48 : return "G1";
			case 49 : return "G2";
			case 50 : return "G3";
			case 51 : return "G4";
			case 52 : return "G5";
			case 53 : return "G6";
			case 54 : return "G7";
			case 55 : return "G8";
			case 56 : return "H1";
			case 57 : return "H2";
			case 58 : return "H3";
			case 59 : return "H4";
			case 60 : return "H5";
			case 61 : return "H6";
			case 62 : return "H7";
			case 63 : return "H8";
			default : return "inconnue";
		}
	}
        
        public static void setBoard(Board board, String boardConfiguration){
            List<PieceConfiguration> configuration = lire(boardConfiguration);
            
            for(PieceConfiguration piece : configuration){
                board.put(piece.getPosition(), piece.getEtat());
            }
            
        }
        
        public static String getBoardConfiguration(Board board){
            Byte piece;
            StringBuilder chaine = new StringBuilder();
            for(int i = 0 ; i < 64 ; i++){
                piece = board.get(i);
                if(piece != null){
                   chaine.append(";");
                   chaine.append(i);                   
                   chaine.append(";");
                   chaine.append(piece);                   
                }
            }
            return chaine.toString();
        }

        private static List<PieceConfiguration> lire(String boardConfiguration) {
            String[] boardConfigurationSplit = boardConfiguration.split(";");
            PieceConfiguration piece = new PieceConfiguration();
            List<PieceConfiguration> configuration = new ArrayList<>();
            
            
            for(String splity : boardConfigurationSplit){
                if(splity.length()>0 && !piece.addConfiguration(splity)){
                    configuration.add(piece);
                    piece = new PieceConfiguration();
                    piece.addConfiguration(splity);
                }
            }
            
            return configuration;
        }
        
        private static class PieceConfiguration{
            private Integer position;
            private Byte etat;
            
            public boolean addConfiguration(String element){
                if(position == null){
                    position = Integer.valueOf(element);
                }else if(etat == null){
                    etat = Byte.valueOf(element);
                }else{
                    return false;
                }                
                return true;
            }

            /**
             * @return the position
             */
            public Integer getPosition() {
                return position;
            }

            /**
             * @param position the position to set
             */
            public void setPosition(Integer position) {
                this.position = position;
            }

            /**
             * @return the etat
             */
            public Byte getEtat() {
                return etat;
            }

            /**
             * @param etat the etat to set
             */
            public void setEtat(Byte etat) {
                this.etat = etat;
            }
            
            
        }
        
        public final static int modulo8(int valeur){
		return valeur & MASQUE_MODULO_8;
	}
	
}
