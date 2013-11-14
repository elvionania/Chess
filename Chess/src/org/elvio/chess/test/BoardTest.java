package org.elvio.chess.test;

import java.util.ArrayList;
import java.util.List;

import org.elvio.chess.elements.Board;
import org.elvio.chess.util.BoardUtils;

public class BoardTest {

	static Board board;
	
	public void test(){
		
		
		testIsPieceAdverseAtPosition();
		
		
	}

	private static void testIsPieceAdverseAtPosition() {

		board = new Board();
		board.initialisation();
		
		List<int[]> datas = new ArrayList<>();
		int[] data1 = {BoardUtils.A2, BoardUtils.A7};
		int[] data2 = {BoardUtils.F2, BoardUtils.H2};
		int[] data3 = {BoardUtils.A7, BoardUtils.A2};
		int[] data4 = {BoardUtils.E1, BoardUtils.E8};
		int[] data5 = {BoardUtils.A2, BoardUtils.B2};
		int[] data6 = {BoardUtils.A7, BoardUtils.C6};
		
		datas.add(data1);
		datas.add(data2);
		datas.add(data3);
		datas.add(data4);
		datas.add(data5);
		datas.add(data6);
		

		if(BoardUtils.isPieceAdverseAtPosition(data1[0], data1[1], board) == true){
			System.out.println("ok1");
		}else{
			System.out.println("ko1");
		}
		if(BoardUtils.isPieceAdverseAtPosition(data2[0], data2[1], board) == false){
			System.out.println("ok2");
		}else{
			System.out.println("ko2");
		}
		if(BoardUtils.isPieceAdverseAtPosition(data3[0], data3[1], board) == true){
			System.out.println("ok3");
		}else{
			System.out.println("ko3");
		}
		if(BoardUtils.isPieceAdverseAtPosition(data4[0], data4[1], board) == true){
			System.out.println("ok4");
		}else{
			System.out.println("ko4");
		}
		if(BoardUtils.isPieceAdverseAtPosition(data5[0], data5[1], board) == false){
			System.out.println("ok5");
		}else{
			System.out.println("ko5");
		}
		if(BoardUtils.isPieceAdverseAtPosition(data6[0], board.get(data6[1]), board) == false){
			System.out.println("ok6");
		}else{
			System.out.println("ko6");
		}
		
	}
	
	public static void testIsEnPositionInitial() {
		board = new Board();
		board.initialisation();
		if(BoardUtils.isEnPositionInitial(board.get(BoardUtils.A2))){
			System.out.println("ok1");
		}else{
			System.out.println("ko1");			
		}
		
		if(BoardUtils.isEnPositionInitial(board.get(BoardUtils.B2))){
			System.out.println("ok2");
		}else{
			System.out.println("ko2");			
		}
		
		if(BoardUtils.isEnPositionInitial(board.get(BoardUtils.E1))){
			System.out.println("ok3");
		}else{
			System.out.println("ko3");			
		}
		
		BoardUtils.bouger(BoardUtils.A2, board.get(BoardUtils.A2), BoardUtils.A4, board.get(BoardUtils.A4), board);
		if(BoardUtils.isEnPositionInitial(board.get(BoardUtils.A4))){
			System.out.println("ko4");
		}else{
			System.out.println("ok4");			
		}
		
		BoardUtils.bouger(BoardUtils.E1, board.get(BoardUtils.E1), BoardUtils.E2, board.get(BoardUtils.E2), board);
		if(BoardUtils.isEnPositionInitial(board.get(BoardUtils.E2))){
			System.out.println("ko5");
		}else{
			System.out.println("ok5");			
		}
	}
	
	public static void main(String[] args){
		testIsEnPositionInitial();
	}
	
	
}
