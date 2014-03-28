package org.elvio.chess.elements;

import org.elvio.chess.time.Temps;

public abstract class Joueur {
	
	protected Byte couleur;

	public void setCouleur(byte couleur) {
		this.couleur = couleur;		
	}
	
	public abstract Board jouer(Board board, int cpt, Temps temps);

}
