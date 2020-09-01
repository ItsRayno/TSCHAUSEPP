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
public class InvalidZahlSubmssionException extends Exception{
    
    private Karte.Zahl expected;
    private Karte.Zahl actual;

    public InvalidZahlSubmssionException(String message, Karte.Zahl actual, Karte.Zahl expected){
        this.actual = actual;
        this.expected = expected;
    }
}
    
