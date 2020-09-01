/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Krist
 */
public class InvalidSymbolSubmssionException extends Exception{
 
    private Karte.Symbol expected;
    private Karte.Symbol actual;

    public InvalidSymbolSubmssionException(String message, Karte.Symbol actual, Karte.Symbol expected){
        this.actual = actual;
        this.expected = expected;
    }

}
