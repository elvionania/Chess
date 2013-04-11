package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.List;

import org.elvio.chess.util.BoardUtils;

public class Tour extends Piece {

	public Tour(byte couleur) {
		super(couleur);
	}

	public static final String valeurBinaire = "00110000";
	static final byte valueStatic = (new Integer(Integer.parseInt(valeurBinaire,2))).byteValue();
	
	public final static byte getValueStatic(){
		return valueStatic;
	}
	
	public static List<Byte> getPositionsAttaques(Byte maPosition, Byte piece, Board board) {
		return getPositionsJouables(maPosition, piece, board);
	}
	
	public static List<Byte> getPositionsJouables(Byte maPosition, Byte piece, Board board) {
		List<List<Byte>> chemins = getCheminsJouables(maPosition, board);
		List<Byte> coupsJouables = new ArrayList<Byte>();
		for(List<Byte> chemin : chemins){
			coupsJouables.addAll(getPositionsLibreSurLesChemins(maPosition, piece, chemin, board));
		}
		return coupsJouables;
	}

	public static List<List<Byte>> getCheminsJouables(Byte maPosition,
			Board board) {
		
		List<List<Byte>> chemins = new ArrayList<List<Byte>>();
		
		List<Byte> horizontalDroit = new ArrayList<Byte>();
		byte i = 1;
		Byte position;
		while((position = BoardUtils.getPosition(maPosition, i, Piece.ZERO)) != null){
			horizontalDroit.add(position);
			i++;
		}
		chemins.add(horizontalDroit);
		
		List<Byte> horizontalGauche = new ArrayList<Byte>();
		i = -1;
		while((position = BoardUtils.getPosition(maPosition, i, Piece.ZERO)) != null){
			horizontalGauche.add(position);
			i--;
		}
		chemins.add(horizontalGauche);
		
		List<Byte> verticalHaut = new ArrayList<Byte>();
		byte j = 1;
		while((position = BoardUtils.getPosition(maPosition, Piece.ZERO, j)) != null){
			verticalHaut.add(position);
			j++;
		}
		chemins.add(verticalHaut);
		
		List<Byte> verticalBas = new ArrayList<Byte>();
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, Piece.ZERO, j)) != null){
			verticalBas.add(position);
			j--;
		}
		chemins.add(verticalBas);
		
		return chemins;
	}
	
}
