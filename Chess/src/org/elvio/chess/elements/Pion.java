package org.elvio.chess.elements;

public class Pion extends Piece {
	
	static final String valeurBinaire = "01000000";
	
	public Pion(byte couleur) {
		setValue(couleur);
	}
	
}
