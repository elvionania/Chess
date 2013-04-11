package org.elvio.chess.elements;

import java.util.HashMap;

public class PartieNulle {

	private int coupJouePourAnnulerEn50Coups = 0;
	private HashMap<String, Byte> nbrePositionsIdentiques = new HashMap<String, Byte> ();
	private int nbreDePieceSurLeGame = 0;
	public int getCoupJouePourAnnulerEn50Coups() {
		return coupJouePourAnnulerEn50Coups;
	}
	public void setCoupJouePourAnnulerEn50Coups(int coupJouePourAnnulerEn50Coups) {
		this.coupJouePourAnnulerEn50Coups = coupJouePourAnnulerEn50Coups;
	}
	public HashMap<String, Byte> getNbrePositionsIdentiques() {
		return nbrePositionsIdentiques;
	}
	public void setNbrePositionsIdentiques(
			HashMap<String, Byte> nbrePositionsIdentiques) {
		this.nbrePositionsIdentiques = nbrePositionsIdentiques;
	}
	public int getNbreDePieceSurLeGame() {
		return nbreDePieceSurLeGame;
	}
	public void setNbreDePieceSurLeGame(int nbreDePieceSurLeGame) {
		this.nbreDePieceSurLeGame = nbreDePieceSurLeGame;
	}
	public void incrementCoupJouePourAnnulerEn50Coups() {
		this.coupJouePourAnnulerEn50Coups++;		
	}
	public Byte getNbrePositionsIdentiques(
			String representationDunEchiquier) {
		return this.nbrePositionsIdentiques.get(representationDunEchiquier);
	}
	public void putNbrePositionsIdentiques(String echequier, byte combien) {
		this.nbrePositionsIdentiques.put(echequier, combien);
	}
	
	
	
}
