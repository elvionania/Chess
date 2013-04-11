package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.List;

import org.elvio.chess.util.BoardUtils;

public class Fou extends Piece {

	public Fou(byte couleur) {
		super(couleur);
	}

	public static final String valeurBinaire = "01001000";
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
		
		List<Byte> diagHautDroit = new ArrayList<Byte>();
		byte i = 1;
		byte j = 1;
		Byte position;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != null){
			diagHautDroit.add(position);
			i++;
			j++;
		}
		chemins.add(diagHautDroit);
		
		List<Byte> diagHautGauche = new ArrayList<Byte>();
		i = -1;
		j = 1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != null){
			diagHautGauche.add(position);
			i--;
			j++;
		}
		chemins.add(diagHautGauche);
		
		List<Byte> diagBasDroit = new ArrayList<Byte>();
		i = 1;
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != null){
			diagBasDroit.add(position);
			i++;
			j--;
		}
		chemins.add(diagBasDroit);
		
		List<Byte> diagBasGauche = new ArrayList<Byte>();
		i = -1;
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != null){
			diagBasGauche.add(position);
			i--;
			j--;
		}
		chemins.add(diagBasGauche);
		
		return chemins;
	}
	
}
