package org.elvio.chess.elements;

public class Board {

	private Piece[][] board;
	
	public static final byte[] A1 = {0,0};
	public static final byte[] A2 = {0,1};
	public static final byte[] A3 = {0,2};
	public static final byte[] A4 = {0,3};
	public static final byte[] A5 = {0,4};
	public static final byte[] A6 = {0,5};
	public static final byte[] A7 = {0,6};
	public static final byte[] A8 = {0,7};
	public static final byte[] B1 = {1,0};
	public static final byte[] B2 = {1,1};
	public static final byte[] B3 = {1,2};
	public static final byte[] B4 = {1,3};
	public static final byte[] B5 = {1,4};
	public static final byte[] B6 = {1,5};
	public static final byte[] B7 = {1,6};
	public static final byte[] B8 = {1,7};
	public static final byte[] C1 = {2,0};
	public static final byte[] C2 = {2,1};
	public static final byte[] C3 = {2,2};
	public static final byte[] C4 = {2,3};
	public static final byte[] C5 = {2,4};
	public static final byte[] C6 = {2,5};
	public static final byte[] C7 = {2,6};
	public static final byte[] C8 = {2,7};
	public static final byte[] D1 = {3,0};
	public static final byte[] D2 = {3,1};
	public static final byte[] D3 = {3,2};
	public static final byte[] D4 = {3,3};
	public static final byte[] D5 = {3,4};
	public static final byte[] D6 = {3,5};
	public static final byte[] D7 = {3,6};
	public static final byte[] D8 = {3,7};
	public static final byte[] E1 = {4,0};
	public static final byte[] E2 = {4,1};
	public static final byte[] E3 = {4,2};
	public static final byte[] E4 = {4,3};
	public static final byte[] E5 = {4,4};
	public static final byte[] E6 = {4,5};
	public static final byte[] E7 = {4,6};
	public static final byte[] E8 = {4,7};
	public static final byte[] F1 = {5,0};
	public static final byte[] F2 = {5,1};
	public static final byte[] F3 = {5,2};
	public static final byte[] F4 = {5,3};
	public static final byte[] F5 = {5,4};
	public static final byte[] F6 = {5,5};
	public static final byte[] F7 = {5,6};
	public static final byte[] F8 = {5,7};
	public static final byte[] G1 = {6,0};
	public static final byte[] G2 = {6,1};
	public static final byte[] G3 = {6,2};
	public static final byte[] G4 = {6,3};
	public static final byte[] G5 = {6,4};
	public static final byte[] G6 = {6,5};
	public static final byte[] G7 = {6,6};
	public static final byte[] G8 = {6,7};
	public static final byte[] H1 = {7,0};
	public static final byte[] H2 = {7,1};
	public static final byte[] H3 = {7,2};
	public static final byte[] H4 = {7,3};
	public static final byte[] H5 = {7,4};
	public static final byte[] H6 = {7,5};
	public static final byte[] H7 = {7,6};
	public static final byte[] H8 = {7,7};
	
	public Board(){		
	}
	
	public void initialisation(){
		board = new Piece[8][8];
		miseEnPlaceDesPieces();
	}

	private void miseEnPlaceDesPieces() {
		positionnerUnePieceSurLEchiquier(new Pion(Piece.BLANC), A2);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.BLANC), B2);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.BLANC), C2);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.BLANC), D2);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.BLANC), E2);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.BLANC), F2);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.BLANC), G2);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.BLANC), H2);
		positionnerUnePieceSurLEchiquier(new Tour(Piece.BLANC), A1);
		positionnerUnePieceSurLEchiquier(new Tour(Piece.BLANC), H1);
		positionnerUnePieceSurLEchiquier(new Cavalier(Piece.BLANC), B1);
		positionnerUnePieceSurLEchiquier(new Cavalier(Piece.BLANC), G1);
		positionnerUnePieceSurLEchiquier(new Fou(Piece.BLANC), C1);
		positionnerUnePieceSurLEchiquier(new Fou(Piece.BLANC), F1);
		positionnerUnePieceSurLEchiquier(new Dame(Piece.BLANC), D1);
		positionnerUnePieceSurLEchiquier(new Roi(Piece.BLANC), E1);
		
		positionnerUnePieceSurLEchiquier(new Pion(Piece.NOIR), A7);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.NOIR), B7);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.NOIR), C7);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.NOIR), D7);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.NOIR), E7);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.NOIR), F7);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.NOIR), G7);
		positionnerUnePieceSurLEchiquier(new Pion(Piece.NOIR), H7);
		positionnerUnePieceSurLEchiquier(new Tour(Piece.NOIR), A8);
		positionnerUnePieceSurLEchiquier(new Tour(Piece.NOIR), H8);
		positionnerUnePieceSurLEchiquier(new Cavalier(Piece.NOIR), B8);
		positionnerUnePieceSurLEchiquier(new Cavalier(Piece.NOIR), G8);
		positionnerUnePieceSurLEchiquier(new Fou(Piece.NOIR), C8);
		positionnerUnePieceSurLEchiquier(new Fou(Piece.NOIR), F8);
		positionnerUnePieceSurLEchiquier(new Dame(Piece.NOIR), D8);
		positionnerUnePieceSurLEchiquier(new Roi(Piece.NOIR), E8);
	}

	private void positionnerUnePieceSurLEchiquier(Piece piece, byte[] position) {
		board[position[0]][position[1]] = piece;
	}
	
}
