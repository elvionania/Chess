package org.elvio.chess.elements.pieces;

import java.util.ArrayList;
import java.util.List;
import org.elvio.chess.elements.Board;

import org.elvio.chess.util.BoardUtils;
import org.elvio.chess.util.CoupsJouables;

public class Dame extends Piece {

	
	public Dame(byte couleur) {
		super(couleur);
	}

	public static final String valeurBinaire = "00101000";
	static final byte valueStatic = (new Integer(Integer.parseInt(valeurBinaire,2))).byteValue();
	
	public final static byte getValueStatic(){
		return valueStatic;
	}
	
	public static List<Integer> getPositionsAttaques(int maPosition, Byte piece, Board board) {
		List<Integer> coupsDAttaque = new ArrayList<>();
		int position;
		int i = 1;
		int j = 1;
		
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsDAttaque.add(position);
			}else{
				coupsDAttaque.add(position);
				break;
			}
			i++;
			j++;
		}
		
		i = -1;
		j = 1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsDAttaque.add(position);
			}else{
				coupsDAttaque.add(position);
				break;
			}
			i--;
			j++;
		}
		
		i = 1;
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsDAttaque.add(position);
			}else{
				coupsDAttaque.add(position);
				break;
			}
			i++;
			j--;
		}
		
		i = -1;
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsDAttaque.add(position);
			}else{
				coupsDAttaque.add(position);
				break;
			}
			i--;
			j--;
		}
		
		i=1;
		while((position = BoardUtils.getPosition(maPosition, i, Piece.ZERO)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsDAttaque.add(position);
			}else{
				coupsDAttaque.add(position);
				break;
			}
			i++;
		}
		
		i = -1;
		while((position = BoardUtils.getPosition(maPosition, i, Piece.ZERO)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsDAttaque.add(position);
			}else{
				coupsDAttaque.add(position);
				break;
			}
			i--;
		}
		
		j = 1;
		while((position = BoardUtils.getPosition(maPosition, Piece.ZERO, j)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsDAttaque.add(position);
			}else{
				coupsDAttaque.add(position);
				break;
			}
			j++;
		}
		
		j = -1;
		while((position = BoardUtils.getPosition(maPosition, Piece.ZERO, j)) != -1){
			if((getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				coupsDAttaque.add(position);
			}else{
				coupsDAttaque.add(position);
				break;
			}
			j--;
		}
		
		return coupsDAttaque;
	}
	
	public static List<Integer> getPositionsJouables(int maPosition, Byte piece, Board board) {
		List<Integer> coupsJouables = new ArrayList<>();
		int position;
		int i = 1;
		int j = 1;
		CoupsJouables coup;
		
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
		
		i=1;
		while((position = BoardUtils.getPosition(maPosition, i, Piece.ZERO)) != -1){
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
		while((position = BoardUtils.getPosition(maPosition, i, Piece.ZERO)) != -1){
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
		
		j = 1;
		while((position = BoardUtils.getPosition(maPosition, Piece.ZERO, j)) != -1){
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
		while((position = BoardUtils.getPosition(maPosition, Piece.ZERO, j)) != -1){
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
		int position;
		int i = 1;
		int j = 1;
		CoupsJouables coup;
		
		while((position = BoardUtils.getPosition(maPosition, i, j)) != -1){
			if((coup = getPositionsLibreSurLesChemins(maPosition, piece, position, board)) == CoupsJouables.LIBRE){
				mobilite++;
			}else if (coup == CoupsJouables.PRENABLE){
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
			}else if (coup == CoupsJouables.PRENABLE){
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
			}else if (coup == CoupsJouables.PRENABLE){
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
			}else if (coup == CoupsJouables.PRENABLE){
				mobilite++;
				break;
			}else{
				break;
			}
			i--;
			j--;
		}
		
		while((position = BoardUtils.getPosition(maPosition, i, Piece.ZERO)) != -1){
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
		while((position = BoardUtils.getPosition(maPosition, i, Piece.ZERO)) != -1){
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
		
		j = 1;
		while((position = BoardUtils.getPosition(maPosition, Piece.ZERO, j)) != -1){
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
		while((position = BoardUtils.getPosition(maPosition, Piece.ZERO, j)) != -1){
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
        
        public final static boolean isComme(Byte etat) {
		return ((etat & valueStatic) == valueStatic);
	}

}
