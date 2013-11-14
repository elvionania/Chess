package org.elvio.chess.elements;

import java.util.ArrayList;
import java.util.List;

import org.elvio.chess.util.BoardUtils;
import org.elvio.chess.util.CoupsJouables;

public class Tour extends Piece {

	public Tour(byte couleur) {
		super(couleur);
	}

	public static final String valeurBinaire = "00110000";
	static final byte valueStatic = (new Integer(Integer.parseInt(valeurBinaire,2))).byteValue();
	
	public final static byte getValueStatic(){
		return valueStatic;
	}
	
	public static List<Integer> getPositionsAttaques(int maPosition, Byte piece, Board board) {
		List<Integer> coupsAttaques = new ArrayList<Integer>();
		int i = 1;
		int position;
		
		while((position = BoardUtils.getPosition(maPosition, i, 0)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsAttaques.add(position);
			}else{
				coupsAttaques.add(position);
				break;
			}
			i++;
		}
		
		i = -1;
		while((position = BoardUtils.getPosition(maPosition, i, 0)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsAttaques.add(position);
			}else{
				coupsAttaques.add(position);
				break;
			}
			i--;
		}
		
		int j = 1;
		while((position = BoardUtils.getPosition(maPosition, 0, j)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsAttaques.add(position);
			}else{
				coupsAttaques.add(position);
				break;
			}
			j++;
		}
		
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, 0, j)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsAttaques.add(position);
			}else{
				coupsAttaques.add(position);
				break;
			}
			j--;
		}
		
		return coupsAttaques;
	}
	
	public static List<Integer> getPositionsJouables(int maPosition, Byte piece, Board board) {
		List<Integer> coupsJouables = new ArrayList<Integer>();
		int i = 1;
		int position;
		CoupsJouables coup;
		
		while((position = BoardUtils.getPosition(maPosition, i, 0)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsJouables.add(position);
			}else if (coup == CoupsJouables.PRENABLE){
				coupsJouables.add(position);
				break;
			}else{
				break;
			}
			i++;
		}
		
		i = -1;
		while((position = BoardUtils.getPosition(maPosition, i, 0)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsJouables.add(position);
			}else if (coup == CoupsJouables.PRENABLE){
				coupsJouables.add(position);
				break;
			}else{
				break;
			}
			i--;
		}
		
		int j = 1;
		while((position = BoardUtils.getPosition(maPosition, 0, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsJouables.add(position);
			}else if (coup == CoupsJouables.PRENABLE){
				coupsJouables.add(position);
				break;
			}else{
				break;
			}
			j++;
		}
		
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, 0, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsJouables.add(position);
			}else if (coup == CoupsJouables.PRENABLE){
				coupsJouables.add(position);
				break;
			}else{
				break;
			}
			j--;
		}
		
		return coupsJouables;
	}
	
	public static int getMobilite(int maPosition, Byte piece, Board board) {
		int mobilite = 0;
		int i = 1;
		int position;
		CoupsJouables coup;
		
		while((position = BoardUtils.getPosition(maPosition, i, 0)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				mobilite++;
			}else if (coup == CoupsJouables.PRENABLE){
				mobilite++;
				break;
			}else{
				break;
			}
			i++;
		}
		
		i = -1;
		while((position = BoardUtils.getPosition(maPosition, i, 0)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				mobilite++;
			}else if (coup == CoupsJouables.PRENABLE){
				mobilite++;
				break;
			}else{
				break;
			}
			i--;
		}
		
		int j = 1;
		while((position = BoardUtils.getPosition(maPosition, 0, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				mobilite++;
			}else if (coup == CoupsJouables.PRENABLE){
				mobilite++;
				break;
			}else{
				break;
			}
			j++;
		}
		
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, 0, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				mobilite++;
			}else if (coup == CoupsJouables.PRENABLE){
				mobilite++;
				break;
			}else{
				break;
			}
			j--;
		}
		
		return mobilite;
	}

}
