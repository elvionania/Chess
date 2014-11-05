/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.elvio.chess.process;

import org.elvio.chess.elements.Board;
import org.elvio.chess.eval.algo.FonctionEvaluation;
import org.elvio.chess.time.IGestionDuTemps;
import org.elvio.chess.util.BoardEvalue;

/**
 *
 * @author elvionania
 */
interface IParcourir {

    BoardEvalue getBoardEvalue(int profondeur, Board board, Byte couleur, int numeroDuCoup, FonctionEvaluation algoDEvaluation, Integer maxParent, boolean test, IGestionDuTemps tempsDuJoueur);
}
