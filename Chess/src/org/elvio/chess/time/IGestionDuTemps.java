package org.elvio.chess.time;

import org.elvio.chess.util.Outils;


public abstract class IGestionDuTemps {

	protected Temps temps;
	protected int parcelDeTemps;
	private long tempsFinal;
    private long duree;
	
	public IGestionDuTemps(int temps){
		this.temps = new Temps(temps);
	}
	
	public void setTempsCourant(long milliSeconde){
		temps.setTempsCourantEnSeconde(milliSeconde/1000);
	}
	
	abstract void manager();
		
	public boolean cEstLHeure(byte couleur, int score, int nbreDeCoup, long tempsInitial){
		temps.setCouleur(couleur);
		temps.setScore(score);
		temps.setNbreDeCoup(nbreDeCoup);
		manager();
        tempsFinal = Outils.getTime();
        duree = (tempsFinal-tempsInitial) ;
	
        if(parcelDeTemps < (duree*20)){
        	return true;
        }
        
        return false;
	}
	
}
