package org.elvio.chess.elements;

public class Piece {

	protected static final String valeurBinaire = "";
	
	public static final byte BLANC = (new Integer(Integer.parseInt("10000000",2))).byteValue();
	public static final byte NOIR = (new Integer(Integer.parseInt("00000000",2))).byteValue();
	
	protected byte value = (new Integer(Integer.parseInt(valeurBinaire,2))).byteValue();
	
	public Piece(byte couleur) {
		setValue(couleur);
	}

	public byte getValue() {
		return value;
	}
	
	public void setValue(byte couleur){
		value = (byte) (value & couleur);
	}

	public Piece() {
		super();
	}

}