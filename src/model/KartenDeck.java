package model;


import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import javax.swing.ImageIcon;

public class KartenDeck {
    private Vector<Karte> karten;
    private int kartenImDeck;

    public void setTop(int top) {
        this.top = top;
    }

    public int top;

    public KartenDeck() {
        karten = new Vector<Karte>(72);
        zurücksetzten();
    }

    public int getTop() {
        return top;
    }

    public Vector<Karte> getKarten() {
        return karten;
    }

    //Das Kartdeck wird aufgefüllt/zurückgesetzt
    public void zurücksetzten() {
        Karte.Symbol[] symbole = Karte.Symbol.values();
        kartenImDeck = 0;
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < symbole.length; i++) {

                Karte.Symbol symbol = symbole[i];

                for (int j = 0; j < 9; j++) {
                    Karte karte1 = new Karte(symbol, Karte.Zahl.getzahl(j));
                    karten.add(karte1);
                    kartenImDeck++;
                }
            }
        }
                top = karten.size() - 1;
                assert karten.get(top) != null;
    }
    public void karteHinzufügen(Karte karte){
        karten.add(karte);
    }

    public int getKartenImDeck() {
        return kartenImDeck;
    }
    public ImageIcon getZiehIcon() throws IllegalArgumentException {
        Karte karte = karten.get(top);
        karten.remove(top--);
        return new ImageIcon(karte.toString() + ".gif");
    }
    

    //Das Deck wird gemischt
    public void mischen() {
        Collections.shuffle(karten);
    }


    //Eine Karte wird vom Deck gezogen
    public Karte karteZiehen() {
        Karte gezogeneKarte = karten.get(top);
        karten.remove(top);
        top--;
        kartenImDeck--;
        return gezogeneKarte;
    }
    //Es wird überprüft ob das Deck leer ist
    public boolean ObLeer() {
        return top < 0;
    }

    public void kartenDeckLeeren(){
     karten.removeAll(karten);
    }
    //Gibt die aktuellen Karten im Deck aus.
    public void print() {
        if (ObLeer()) {
            System.out.println("Der Stapel ist leer");
            return;
        }
        System.out.println("Das jetztige Deck");
        for (Karte karte : karten)
            System.out.println("Karte :" + karte.getSymbol()+" "+karte.getZahl());
    }
}


