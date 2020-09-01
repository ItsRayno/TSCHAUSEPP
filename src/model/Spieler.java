package model;


import java.util.Vector;

public class Spieler {
    public String name;
    private Vector<Karte> SpielerHand;
    public int punktezahl = 0;
    public boolean hatTschau = false;
    public boolean hatSepp = false;



    public Spieler(String name){
        SpielerHand = new Vector<Karte>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int anzhalKarten(){
        return SpielerHand.size();
    }

    public boolean kannTschausagen(){
        boolean tschau = false;
        if(getSpielerHand().size()== 2){
            tschau = true;
        }
        return tschau;
    }
      public boolean kannSeppsagen(){
        boolean sepp = false;
        if(getSpielerHand().size()== 1){
            sepp = true;
        }
        return sepp;
    }

    public void karteHinzufügen(Karte karte) {
        SpielerHand.add(karte);
    }
    public void spielerKartenLöschen(){
        SpielerHand.removeAll(SpielerHand);
    }

    public void karteEntfernen(int i){
        SpielerHand.remove(i);
    }

    public int kartenZählen(){
        return SpielerHand.size();
    }

    public void punktieHinzufügen(int i){
        punktezahl = punktezahl +i;
    }

    public Vector<Karte> getSpielerHand(){
        return this.SpielerHand;
    }

    public int getPunktezahl() {
        return punktezahl;
    }

    public void print(){
        for (Karte karte : SpielerHand)
            System.out.println("SpielerHand: "+ karte.getSymbol()+" "+karte.getZahl());

        System.out.println("Name: "+ getName());
        System.out.println("Kartenzählen: "+ kartenZählen());
    }
}
