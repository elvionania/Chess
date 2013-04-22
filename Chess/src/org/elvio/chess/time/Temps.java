package org.elvio.chess.time;

public class Temps {

	private long tempsEnSecondeDeLaPartie;
	private long tempsCourantEnSeconde;
	private long dateInitiale;
	private long dateCourante;
	private int nbreDeCoup;
	private int premierCoupReflechi;
	private boolean minimum;
	private int score;
	
	private int parcelDeTemps;
	
	public Temps (int tempsEnMinute){
		tempsEnSecondeDeLaPartie = tempsEnMinute*60;
		tempsCourantEnSeconde = tempsEnMinute*60;
		nbreDeCoup = 0;
		minimum = false;
		score = 0;
	}
	
	public void vaJouer(int milliSeconde, int score, int nbreDeCoup, boolean premierCoupReflechi){
		tempsCourantEnSeconde = milliSeconde/1000;
		this.score = score;
		this.nbreDeCoup = nbreDeCoup;
		if(premierCoupReflechi){
			this.premierCoupReflechi = nbreDeCoup;
		}
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
			parcelDeTemps = 60;
		}
	}

	public void setDateInitiale(long dateInitiale) {
		this.dateInitiale = dateInitiale/1000;
	}

	public void setDateCourante(long dateCourante) {
		this.dateCourante = dateCourante/1000;
	}
	
	
}
