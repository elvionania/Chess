package org.elvio.chess.elements;

import org.elvio.chess.time.Temps;

public abstract class Joueur {
	
	protected Byte couleur;
        protected Temps temps;
        protected long tempsAuCommencement;

	public void setCouleur(byte couleur) {
		this.couleur = couleur;		
	}
        
        public void setTempsCourant(long milliSeconde){	
            temps.setTempsCourant(milliSeconde - this.tempsAuCommencement);
	}
        
        public byte getCouleur() {
            return this.couleur;
        }
        
        public Temps getTemps(){
            return temps;
        }
	
	public abstract Board jouer(Board board, int cpt, Temps temps);

        public void setTempsAuCommencement(long tempsAuCommencement) {
            this.tempsAuCommencement = tempsAuCommencement;
        }

}
