package org.elvio.chess.util;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.Piece;
import org.elvio.chess.elements.Pion;

public class Comparaison {

	
	
	public static void compare (Board board1, Board boardf){
		Byte positionI = 0;
		Byte positionF = 0;
		boolean manger = false;
		
		for(Byte position : board1.getPositions()){
			if(board1.get(position) != null){
				if(boardf.get(position) == null){
					positionI = position;
				}else if(boardf.get(position).byteValue() != board1.get(position).byteValue()){
					manger = true;
					positionF = position;
				}
			}
		}
		
		for(byte position : boardf.getPositions()){
			if(boardf.get(position) != null){
				if(board1.get(position) == null){
					positionF = position;
				}
			}
		}
		
		if(Piece.isComme(boardf.get(positionF), Pion.getValueStatic())){
			System.out.println("pion");
			if(Math.abs(positionF - positionI) == 2){
				System.out.println("pion avance de deux");
			}
		}
		
		System.out.println("position i "+positionI+" position f "+positionF+" mange "+manger);
	}
}
