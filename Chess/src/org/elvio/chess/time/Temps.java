package org.elvio.chess.time;


public class Temps {

	private long tempsEnSecondeDeLaPartie;
	private long tempsCourantEnSeconde;
	private long dateInitialeDuCoup;
	private long dateCouranteDuCoup;
	private int nbreDeCoup;
	private int score;
	private byte couleur;
	
	public Temps (int tempsEnMinute){
		tempsEnSecondeDeLaPartie = tempsEnMinute*60;
		tempsCourantEnSeconde = tempsEnMinute*60;
		nbreDeCoup = 0;
		score = 0;
	}
		
	public void setTempsCourant(long milliSeconde){
		tempsCourantEnSeconde = milliSeconde/1000;
	}
	
	public void setDateInitialeDuCoup(long dateInitiale) {
		this.dateInitialeDuCoup = dateInitiale/1000;
	}

	public void setDateCouranteDuCoup(long dateCourante) {
		this.dateCouranteDuCoup = dateCourante/1000;
	}

	public long getTempsEnSecondeDeLaPartie() {
		return tempsEnSecondeDeLaPartie;
	}

	public void setTempsEnSecondeDeLaPartie(long tempsEnSecondeDeLaPartie) {
		this.tempsEnSecondeDeLaPartie = tempsEnSecondeDeLaPartie;
	}

	public long getTempsCourantEnSeconde() {
		return tempsCourantEnSeconde;
	}

	public void setTempsCourantEnSeconde(long tempsCourantEnSeconde) {
		this.tempsCourantEnSeconde = tempsCourantEnSeconde;
	}

	public int getNbreDeCoup() {
		return nbreDeCoup;
	}

	public void setNbreDeCoup(int nbreDeCoup) {
		this.nbreDeCoup = nbreDeCoup;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public byte getCouleur() {
		return couleur;
	}

	public void setCouleur(byte couleur) {
		this.couleur = couleur;
	}

	public long getDateInitialeDuCoup() {
		return dateInitialeDuCoup;
	}

	public long getDateCouranteDuCoup() {
		return dateCouranteDuCoup;
	}	
	
}
