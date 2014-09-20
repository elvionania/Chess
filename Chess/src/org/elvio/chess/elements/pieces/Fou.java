package org.elvio.chess.elements.pieces;

import java.util.ArrayList;
import java.util.List;
import org.elvio.chess.elements.Board;

import org.elvio.chess.util.BoardUtils;
import org.elvio.chess.util.CoupsJouables;

public class Fou extends Piece {

	public Fou(byte couleur) {
		super(couleur);
	}

	static final byte valueStatic = 0b01001000;
	
	public final static byte getValueStatic(){
		return valueStatic;
	}
	
	public static List<Integer> getPositionsAttaques(int maPosition, Byte piece, Board board) {
		List<Integer> coupsAttaques = new ArrayList<>();
		int position;
		int i = 1;
		int j = 1;
		
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsAttaques.add(position);
			}else{
				coupsAttaques.add(position);
				break;
			}
			i++;
			j++;
		}
		
		i = -1;
		j = 1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsAttaques.add(position);
			}else{
				coupsAttaques.add(position);
				break;
			}
			i--;
			j++;
		}
		
		i = 1;
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsAttaques.add(position);
			}else{
				coupsAttaques.add(position);
				break;
			}
			i++;
			j--;
		}
		
		i = -1;
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsAttaques.add(position);
			}else{
				coupsAttaques.add(position);
				break;
			}
			i--;
			j--;
		}
		return coupsAttaques;
	}

	public static List<Integer> getPositionsJouables(int maPosition, Byte piece, Board board) {
		List<Integer> coupsJouables = new ArrayList<Integer>();
		CoupsJouables coup;
		int position;
		int i = 1;
		int j = 1;
		
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsJouables.add(position);
			}else if (coup == CoupsJouables.PRENABLE){
				coupsJouables.add(position);
				break;
			}else{
				break;
			}
			i++;
			j++;
		}
		
		i = -1;
		j = 1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsJouables.add(position);
			}else if (coup == CoupsJouables.PRENABLE){
				coupsJouables.add(position);
				break;
			}else{
				break;
			}
			i--;
			j++;
		}
		
		i = 1;
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsJouables.add(position);
			}else if (coup == CoupsJouables.PRENABLE){
				coupsJouables.add(position);
				break;
			}else{
				break;
			}
			i++;
			j--;
		}
		
		i = -1;
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsJouables.add(position);
			}else if (coup == CoupsJouables.PRENABLE){
				coupsJouables.add(position);
				break;
			}else{
				break;
			}
			i--;
			j--;
		}
		return coupsJouables;
	}
	
	
	
	public static int getMobilite(int maPosition, Byte piece, Board board) {
		int mobilite = 0;
		int position;
		CoupsJouables coup;
		int i = 1;
		int j = 1;
		
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				mobilite++;
			}else if (coup ==CoupsJouables.PRENABLE){
				mobilite++;
				break;
			}else{
				break;
			}
			i++;
			j++;
		}
		
		i = -1;
		j = 1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				mobilite++;
			}else if (coup ==CoupsJouables.PRENABLE){
				mobilite++;
				break;
			}else{
				break;
			}
			i--;
			j++;
		}
		
		i = 1;
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				mobilite++;
			}else if (coup ==CoupsJouables.PRENABLE){
				mobilite++;
				break;
			}else{
				break;
			}
			i++;
			j--;
		}
		
		i = -1;
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				mobilite++;
			}else if (coup ==CoupsJouables.PRENABLE){
				mobilite++;
				break;
			}else{
				break;
			}
			i--;
			j--;
		}
		return mobilite;
	}

    /**
     * retourne true si le paramètre est un Fou
     * @param etat
     * @return
     */
    public final static boolean isComme(Byte etat) {
		return ((etat & valueStatic) == valueStatic);
	}
	
}
