/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.elvio.chess.time;

import org.elvio.chess.util.Outils;

/**
 *
 * @author Elvio
 */
public class GestionDuTemps {
    private long tempsFinal;
    private long tempsInitial;
    private long duree;
    
    public boolean cEstLHeure(Temps temps){
        tempsFinal = Outils.getTime();
	duree = (tempsFinal-tempsInitial) / 1000;
	
        if(temps.getParcelDeTemps() < (duree*20)){
		return true;
	}
        
        return false;
    }
    
}
