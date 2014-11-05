/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.elvio.chess.time;

import org.elvio.chess.elements.pieces.Piece;


/**
 *
 * @author Elvio
 */
public class GestionDuTemps extends IGestionDuTemps{
   
	public GestionDuTemps(int temps) {
		super(temps);
	}

	@Override
	void manager() {
		temps.setTempsCourantEnSeconde(temps.getTempsEnSecondeDeLaPartie() + temps.getDateInitialeDuCoup() - temps.getDateCouranteDuCoup());
		if(temps.getTempsCourantEnSeconde() < 120){
			parcelDeTemps = 0;
		}else{
			parcelDeTemps = 10;
			if(Piece.isBlanc(temps.getCouleur()) && temps.getScore() < 0){
				parcelDeTemps *= 2;
			}
			if(!Piece.isBlanc(temps.getCouleur()) && temps.getScore() > 0){
				parcelDeTemps *= 2;
			}
			if(temps.getNbreDeCoup() < 12){
				parcelDeTemps *= 2;
			}
			
		}
	}
    
}
