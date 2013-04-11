package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

	protected static final String valeurBinaire = "00000000";
	
	public static final byte BLANC = (new Integer(Integer.parseInt("00000001",2))).byteValue();
	public static final byte NOIR = (new Integer(Integer.parseInt("00000000",2))).byteValue();
	public static final byte A_AVANCER_DE_2 = (new Integer(Integer.parseInt("00000100",2))).byteValue();
	public static final byte NA_PLUS_AVANCER_DE_2 = (new Integer(Integer.parseInt("11111011",2))).byteValue();
	public static final byte A_DEJA_JOUE = (new Integer(Integer.parseInt("00000010",2))).byteValue();
	public static final byte UN = 1;
	public static final byte DEUX = 2;
	public static final byte M_UN = -1;
	public static final byte M_DEUX = -2;
	public static final byte ZERO = 0;
	
	protected static Byte value = (new Integer(Integer.parseInt(valeurBinaire,2))).byteValue();
	
	public Integer propriete;
	
	public Piece(Byte couleur) {
		setValue(couleur);
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
			
	protected static List<Byte> getPositionsLibreSurLesChemins(Byte maPosition, Byte piece, List<Byte> positionsDUnChemin, Board board){
		ArrayList<Byte> positionsJouablesSurLeChemin = new ArrayList<Byte>();
		
		for(Byte position : positionsDUnChemin){
			if(board.get(position) == null){
				positionsJouablesSurLeChemin.add(position);
			}else if (!isMemeCouleur(piece, board.get(position)) && !isComme(piece, Pion.getValueStatic())){
				positionsJouablesSurLeChemin.add(position);
				break;
			}else{
				break;
			}
		}
		return positionsJouablesSurLeChemin;
	}
	
	public boolean isMemeCouleur(Piece piece){
		if((value & BLANC) == (piece.getValue() & BLANC)){
			return true;
		}
		return false;			
	}
		
	public final boolean isBlanc(){
		return (value & BLANC) == BLANC;
	}
	
	public final static byte getCouleur(byte etat){
		return (byte) (etat & BLANC);
	}

	public static boolean isBlanc(byte couleur){
		return (couleur & BLANC) == BLANC;
	}
	
	public Integer getPropriete() {
		return propriete;
	}

	public void setPropriete(Integer propriete) {
		this.propriete = propriete;
	}

	public final boolean isDernierMouvement(Integer vientDeAvanceDeDeux) {
		if(getPropriete() == vientDeAvanceDeDeux){
			return true;
		}
		return false;
	}
	
	public final static byte avancer(byte nombreDeCase, byte etat){
		if(isBlanc(etat)){
			return nombreDeCase;
		}else{
			return (byte) -nombreDeCase;
		}
	}

	public final static boolean isMemeCouleur(Byte moi, Byte autre) {
		if(autre == null || moi == null){
			return false;
		}
		if((moi & BLANC) == (autre & BLANC)){
			return true;
		}
		return false;
	}

	public final static boolean isComme(byte etat, byte pieceAComparer) {
		return ((etat & pieceAComparer) == pieceAComparer);
	}
	
	public final static Byte creation(byte couleur, Byte piece) {
		return (byte) (couleur | piece);
	}
	
	
	public static List<Byte> getPositionsAttaques(Byte caseCourante, Board board) {
		Byte piece = board.get(caseCourante);
		if(Piece.isComme(piece, Pion.getValueStatic())){
			return Pion.getPositionsAttaques(caseCourante, piece, board);
		}else if(Piece.isComme(piece, Cavalier.getValueStatic())){
			return Cavalier.getPositionsAttaques(caseCourante, piece, board);
		}else if(Piece.isComme(piece, Fou.getValueStatic())){
			return Fou.getPositionsAttaques(caseCourante, piece, board);
		}else if(Piece.isComme(piece, Tour.getValueStatic())){
			return Tour.getPositionsAttaques(caseCourante, piece, board);
		}else if(Piece.isComme(piece, Dame.getValueStatic())){
			return Dame.getPositionsAttaques(caseCourante, piece, board);
		}else if(Piece.isComme(piece, Roi.getValueStatic())){
			return Roi.getPositionsAttaques(caseCourante, piece, board);
		}
		return null;
	}

	public static List<Byte> getPositionsJouables(Byte caseCourante, Board board) {	
		Byte piece = board.get(caseCourante);
			if(Piece.isComme(piece, Pion.getValueStatic())){
				return Pion.getPositionsJouables(caseCourante, piece, board);
			}else if(Piece.isComme(piece, Cavalier.getValueStatic())){
				return Cavalier.getPositionsJouables(caseCourante, piece, board);
			}else if(Piece.isComme(piece, Fou.getValueStatic())){
				return Fou.getPositionsJouables(caseCourante, piece, board);
			}else if(Piece.isComme(piece, Tour.getValueStatic())){
				return Tour.getPositionsJouables(caseCourante, piece, board);
			}else if(Piece.isComme(piece, Dame.getValueStatic())){
				return Dame.getPositionsJouables(caseCourante, piece, board);
			}else if(Piece.isComme(piece, Roi.getValueStatic())){
				return Roi.getPositionsJouables(caseCourante, piece, board);
			}
		return null;
	}

	public final static Byte inverseCouleur(Byte couleur) {
		if(isBlanc(couleur)){
			return NOIR;
		}
		return BLANC;
	}

	public final static boolean neChangePasDeColonne(Byte position, Byte maPosition) {
		if(position == null || maPosition == null){
			return false;
		}
		return maPosition>>3 == position>>3;
	}
	
}