package org.elvio.chess.process;

import org.elvio.chess.elements.Board;
import org.elvio.chess.elements.pieces.Piece;
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
        
        public boolean isBlanc() {
            return Piece.isBlanc(couleur);
        }
        
        public boolean isNoir() {
            return !Piece.isBlanc(couleur);
        }
        
        public Temps getTemps(){
            return temps;
        }
	
	public abstract Board jouer(Board board, int numeroDuCoup);

        public void setTempsAuCommencement(long tempsAuCommencement) {
            this.tempsAuCommencement = tempsAuCommencement;
        }
        
        public Byte getCouleurDeLAutreJoueur(){
            return this.getCouleur() == Piece.BLANC ? Piece.NOIR : Piece.BLANC;
        }

}
