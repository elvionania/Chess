package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.List;

import org.elvio.chess.util.BoardUtils;

public class Dame extends Piece {

	
	public Dame(byte couleur) {
		super(couleur);
	}

	public static final String valeurBinaire = "00101000";
	static final byte valueStatic = (new Integer(Integer.parseInt(valeurBinaire,2))).byteValue();
	
	public final static byte getValueStatic(){
		return valueStatic;
	}
	
	public static List<Byte> getPositionsAttaques(Byte maPosition, Byte piece, Board board) {
		return getPositionsJouables(maPosition, piece, board);
	}
	
	public static List<Byte> getPositionsJouables(Byte maPosition, Byte piece, Board board) {
		List<List<Byte>> chemins = getCheminsJouables(maPosition, board);
		List<Byte> coupsJouables = null;
		for(List<Byte> chemin : chemins){
			if(coupsJouables == null){
				coupsJouables = getPositionsLibreSurLesChemins(maPosition, piece, chemin, board);
			}else{
				coupsJouables.addAll(getPositionsLibreSurLesChemins(maPosition, piece, chemin, board));
			}
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
		
		List<Byte> horizontalDroit = new ArrayList<Byte>();
		i = 1;
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
		j = 1;
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
