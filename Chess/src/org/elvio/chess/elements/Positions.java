package org.elvio.chess.elements;

import org.elvio.chess.util.BoardUtils;

public enum Positions {

	A1("A1", "00000000"), A2("A2", "00000001"), A3("A3", "00000010"), A4("A4", "00000011"), A5("A5", "00000100"), A6("A6","00000101"), A7("A7", "00000110"), A8("A8", "00000111"), 
	B1("B1", "00001000"), B2("B2","00001001"),B3("B3", "00001010"), B4("B4", "00001011"), B5("B5", "00001100"), B6("B6","00001101"), B7("B7", "00001110"), B8("B8", "00001111"),
	C1("C1", "00010000"), C2("C2","00010001"),C3("C3", "00010010"), C4("C4", "00010011"), C5("C5", "00010100"), C6("C6","00010101"), C7("C7", "00010110"), C8("C8", "00010111"),
	D1("D1", "00011000"), D2("D2","00011001"),D3("D3", "00011010"), D4("D4", "00011011"), D5("D5", "00011100"), D6("D6","00011101"), D7("D7", "00011110"), D8("D8", "00011111"),
	E1("E1", "00100000"), E2("E2","00100001"),E3("E3", "00100010"), E4("E4", "00100011"), E5("E5", "00100100"), E6("E6","00100101"), E7("E7", "00100110"), E8("E8", "00100111"),
	F1("F1", "00101000"), F2("F2","00101001"),F3("F3", "00101010"), F4("F4", "00101011"), F5("F5", "00101100"), F6("F6","00101101"), F7("F7", "00101110"), F8("F8", "00101111"),
	G1("G1", "00110000"), G2("G2","00110001"),G3("G3", "00110010"), G4("G4", "00110011"), G5("G5", "00110100"), G6("G6","00110101"), G7("G7", "00110110"), G8("G8", "00110111"),
	H1("H1", "00111000"), H2("H2","00111001"),H3("H3", "00111010"), H4("H4", "00111011"), H5("H5", "00111100"), H6("H6","00111101"), H7("H7", "00111110"), H8("H8", "00111111");
	
	private String nom;
	private Byte valeur;
	
	private Positions(String nom, String valeur){
		this.nom = nom;
		this.valeur = BoardUtils.GetRepresentationByte(valeur);
		
	}
	
	public String toString(){
		return nom;
	}
	
	public static String getNomPourUnePosition(Byte position){
		for(Positions iPosition : Positions.values()){
			if(iPosition.valeur == position){
				return iPosition.toString();
			}
		}
		return "position inconnue";
	}
}
