package org.elvio.chess.elements.pieces;

import java.util.List;
import org.elvio.chess.elements.Board;

import org.elvio.chess.util.CoupsJouables;

public abstract class Piece {

	protected static final String valeurBinaire = "00000000";
	
	public static final byte BLANC = 0b00000001;//(new Integer(Integer.parseInt("00000001",2))).byteValue();
	public static final byte NOIR = (new Integer(Integer.parseInt("00000000",2))).byteValue();
	public static final byte A_AVANCER_DE_2 = (new Integer(Integer.parseInt("00000100",2))).byteValue();
	public static final byte NE_VIENT_PAS_D_AVANCER_DE_2_CASES = (new Integer(Integer.parseInt("11111011",2))).byteValue();
	public static final byte A_DEJA_JOUE = (new Integer(Integer.parseInt("00000010",2))).byteValue();
	public static final byte UN = 1;
	public static final byte DEUX = 2;
	public static final byte M_UN = -1;
	public static final byte M_DEUX = -2;
	public static final byte ZERO = 0;
	public static final byte SEPT = 7;
	public static final byte HUIT = 8;
	public static final byte TROIS = 3;
	public static final byte CINQUANTE_SIX = 56;
	
	protected static Byte value = (new Integer(Integer.parseInt(valeurBinaire,2))).byteValue();
	
	public Piece(Byte couleur) {
		value = (byte) (value & couleur);
	}

	public byte getValue() {
		return value;
	}
	
	public void setValue(Byte couleur){
		value = (byte) (value & couleur);
	}

	public Piece() {
		super();
	}
			
	protected static CoupsJouables getPositionsLibreSurLesChemins(int maPosition, Byte piece, int position, Board board){
			Byte couleur;
			if((couleur = board.get(position)) == null){
				return CoupsJouables.LIBRE;
			}else if (isDifferenteCouleur(piece, couleur)){
				return CoupsJouables.PRENABLE;
			}else{
				return CoupsJouables.OCCUPE;
			}
	}
	
	public static boolean isBlanc(byte couleur){
		return (couleur & BLANC) == BLANC;
	}
        
        public static boolean isNoir(byte couleur){
		return (couleur & BLANC) == NOIR;
	}
	
	public final static int avancer(int nombreDeCase, byte etat){
		if(isBlanc(etat)){
			return nombreDeCase;
		}else{
			return -nombreDeCase;
		}
	}
	
	public final static boolean isMemeCouleur(Byte moi, Byte autre) {
		if(autre == null || moi == null){
			return false;
		}
		return ((moi & BLANC) == (autre & BLANC));
	}
	
	public final static boolean isDifferenteCouleur(Byte moi, Byte autre) {
		if(autre == null || moi == null){
			return false;
		}
		return ((moi & BLANC) != (autre & BLANC));
	}

	public final static boolean isComme(byte etat, byte pieceAComparer) {
		return ((etat & pieceAComparer) == pieceAComparer);
	}
        
        public final static boolean isComme(byte etat, byte pieceAComparer, byte couleur) {
            int etatComparable = (pieceAComparer|couleur);
		return ((etat & etatComparable) == etatComparable);
	}
	
	public static boolean isComme(Byte etat, byte pieceAComparer) {
		return ((etat & pieceAComparer) == pieceAComparer);
	}
	
	public final static Byte creation(byte couleur, Byte piece) {
		return (byte) (couleur | piece);
	}
	
//	public static List<Integer> getPositionsAttaques(int caseCourante, Board board) {
//		Byte piece = board.get(caseCourante);
//		if(Piece.isComme(piece, Pion.getValueStatic())){
//			return Pion.getPositionsAttaques(caseCourante, piece, board);
//		}else if(Piece.isComme(piece, Cavalier.getValueStatic())){
//			return Cavalier.getPositionsAttaques(caseCourante, board);
//		}else if(Piece.isComme(piece, Fou.getValueStatic())){
//			return Fou.getPositionsAttaques(caseCourante, piece, board);
//		}else if(Piece.isComme(piece, Tour.getValueStatic())){
//			return Tour.getPositionsAttaques(caseCourante, piece, board);
//		}else if(Piece.isComme(piece, Roi.getValueStatic())){
//			return Roi.getPositionsAttaques(caseCourante, piece, board);
//		}else if(Piece.isComme(piece, Dame.getValueStatic())){
//			return Dame.getPositionsAttaques(caseCourante, piece, board);
//		}
//		return null;
//	}

	public static List<Integer> getPositionsJouables(int caseCourante, Byte piece, Board board) {	
			if(Piece.isComme(piece, Pion.getValueStatic())){
				return Pion.getPositionsJouables(caseCourante, piece, board);
			}else if(Piece.isComme(piece, Cavalier.getValueStatic())){
				return Cavalier.getPositionsJouables(caseCourante, piece, board);
			}else if(Piece.isComme(piece, Fou.getValueStatic())){
				return Fou.getPositionsJouables(caseCourante, piece, board);
			}else if(Piece.isComme(piece, Tour.getValueStatic())){
				return Tour.getPositionsJouables(caseCourante, piece, board);
			}else if(Piece.isComme(piece, Roi.getValueStatic())){
				return Roi.getPositionsJouables(caseCourante, piece, board);
			}else if(Piece.isComme(piece, Dame.getValueStatic())){
				return Dame.getPositionsJouables(caseCourante, piece, board);
			}
		return null;
	}
	
	

	public final static Byte inverseCouleur(Byte couleur) {
		if(isBlanc(couleur)){
			return NOIR;
		}
		return BLANC;
	}

	public final static boolean neChangePasDeColonne(int position, int maPosition) {
		return maPosition>>3 == position>>3;
	}
        
        public final static Byte nEstPasUnePriseEnPassant(Byte piece){
            return (byte) (piece & NE_VIENT_PAS_D_AVANCER_DE_2_CASES);
        }
	
}