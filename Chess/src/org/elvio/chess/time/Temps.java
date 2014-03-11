package org.elvio.chess.time;

import org.elvio.chess.elements.Piece;

public class Temps {

	private long tempsEnSecondeDeLaPartie;
	private long tempsCourantEnSeconde;
	private long dateInitiale;
	private long dateCourante;
	private int nbreDeCoup;
	private int score;
	private byte couleur;
	
	private int parcelDeTemps;
	
	public Temps (int tempsEnMinute){
		tempsEnSecondeDeLaPartie = tempsEnMinute*60;
		tempsCourantEnSeconde = tempsEnMinute*60;
		nbreDeCoup = 0;
		score = 0;
	}
	
	public void initAvantDeJouer(int score, int nbreDeCoup){
		this.score = score;
		this.nbreDeCoup = nbreDeCoup;
	}
	
	public void setTempsCourant(long milliSeconde){
		tempsCourantEnSeconde = milliSeconde/1000;
	}
	
	public int getParcelDeTemps(){
		manager();
		return parcelDeTemps;
	}
	
	public void manager(){
		tempsCourantEnSeconde = tempsEnSecondeDeLaPartie + dateInitiale - dateCourante;
		if(tempsCourantEnSeconde < 120){
			parcelDeTemps = 0;
		}else{
			parcelDeTemps = 10;
			if(Piece.isBlanc(couleur) && score < 0){
				parcelDeTemps *= 2;
			}
			if(!Piece.isBlanc(couleur) && score > 0){
				parcelDeTemps *= 2;
			}
			if(nbreDeCoup < 12){
				parcelDeTemps *= 2;
			}
			
		}
	}

	public void setDateInitiale(long dateInitiale) {
		this.dateInitiale = dateInitiale/1000;
	}

	public void setDateCourante(long dateCourante) {
		this.dateCourante = dateCourante/1000;
	}
	
	
}
