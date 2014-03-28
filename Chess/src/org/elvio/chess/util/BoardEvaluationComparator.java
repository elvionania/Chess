/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.elvio.chess.util;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author Elvio
 */
public class BoardEvaluationComparator implements Comparator{
    
 
    Map<String, Integer> map;
    int facteur;
 
    public BoardEvaluationComparator(Map map){
        this.map = map;
    }
            
    public BoardEvaluationComparator(){
    }

    public BoardEvaluationComparator(int facteur) {
        this.facteur = facteur;
    }
            
    public void setMap(Map map){
        this.map = map;
    }
    
    public void inverseComparaison(){
        this.facteur = -this.facteur;
    }
            
    @Override
    public int compare(Object keyA, Object keyB){

        Integer valueA = (map.get(keyA));
        Integer valueB = (map.get(keyB));

        if (facteur > 0){
            //ordre decroissant si les blancs, le meilleur en premier
            return valueA.compareTo(valueB);    
        }
        // ordre croissant si les noirs
        return valueB.compareTo(valueA);

    }

}
