package model;

import javafx.scene.chart.ScatterChart;
import org.omg.CORBA.INVALID_ACTIVITY;
import view.MenueGUI;
import view.SpielGUI;

import javax.swing.*;
import java.awt.*;
import java.security.spec.ECFieldF2m;
import java.util.Arrays;
import java.util.Timer;
import java.util.Vector;

public class Spiel{

    private int maximalPunktezahl = 10;
    public int aktuellerSpieler;
    private Vector<Spieler> alleSpieler;
    public KartenDeck kartenDeck;
    public AblageStapel ablageStapel;
    public  boolean istSpielbeendet;

    public Spiel(Vector<Spieler> alleSpieler) {
        this.alleSpieler = alleSpieler;
        kartenDeck = new KartenDeck();
        ablageStapel = new AblageStapel();
        istSpielbeendet = false;
    }

    public void rundeStarten() {
        kartenDeck.mischen();
        for (int i = 0; i < alleSpieler.size(); i++) {
            for (int j = 0; j < 7; j++) {
                alleSpieler.get(i).karteHinzufügen(kartenDeck.karteZiehen());
            }
        }
        ablageStapel.kartehinzufügen(kartenDeck.karteZiehen());
    }

    public boolean ZiehenBestätigen(Spieler spieler){
        boolean ziehen = true;
        for (int i = 0; i < spieler.getSpielerHand().size(); i++) {

            if(spieler.getSpielerHand().get(i).getSymbol() == ablageStapel.getObersteKarte().getSymbol() || spieler.getSpielerHand().get(i).getZahl() == ablageStapel.getObersteKarte().getZahl()){
                ziehen = false;
            }

            else if(spieler.getSpielerHand().get(i).getSymbol() == ablageStapel.getObersteKarte().getSymbol() && spieler.getSpielerHand().get(i).getZahl() == ablageStapel.getObersteKarte().getZahl()){
                ziehen = false;
            }

        }
        return ziehen;
    }
    
    public boolean istrundeBeendet() {
        boolean var =false;
        for (Spieler spieler : this.alleSpieler) {
            if (spieler.anzhalKarten() == 0) {
                if (spieler.hatTschau) {
                    if (spieler.hatSepp) {
                        var = true;
                    }
                }
            }
        }
        return var;
    }
    public void tschauSagen(Spieler spieler){
        spieler.hatTschau = true;
    }
    public void seppSagen(Spieler spieler){
        spieler.hatSepp = true;
    }

    public void rundebeenden(){
        kartenDeck.kartenDeckLeeren();
        for (int i = 0; i <alleSpieler.size(); i++) {
            alleSpieler.get(i).spielerKartenLöschen();
        }
        kartenDeck.zurücksetzten();
        rundeStarten();
    }

    public void setAktuellerSpieler(int aktuellerSpieler) {
        this.aktuellerSpieler = aktuellerSpieler;
    }

    public Spieler getAktuellerSpieler(){

        return this.alleSpieler.get(aktuellerSpieler);
    }
    public int getAktuellerSpielerNr(){

        return aktuellerSpieler;
    }

    public void kartenDeckauffüllen(){
        /*
        Hat leider nicht funktioniert :( Wollte das Kartendeck
        mit den schon gelegten Karten befüllen, sodass es nicht
        mehr Karten gibt.Habe leider keine Lösung gefunden.
        int p = ablageStapel.getSize()-1;
        for (int i = 0; i < p; i++) {
            System.out.println(ablageStapel.getKarte(0));

                deck.karteHinzufügen(ablageStapel.getKarte(0));
                ablageStapel.karteLöschen(0);
        }
        kartenDeck.setTop(kartenDeck.getKartenImDeck()-1);

         */
        kartenDeck.zurücksetzten();
        kartenDeck.mischen();
    }

    public boolean istKarteSpielbar(Karte karte) {
        boolean ok = false;
        if (karte.getZahl() == ablageStapel.getObersteKarte().getZahl() || karte.getSymbol() == ablageStapel.getObersteKarte().getSymbol()) {
            ok = true;
        } else if (karte.getZahl() == ablageStapel.getObersteKarte().getZahl() && karte.getSymbol() == ablageStapel.getObersteKarte().getSymbol()) {
             ok = true;
        }
        return ok;
    }

    public void spielbeenden(){
        JLabel message = new JLabel(alleSpieler.get(aktuellerSpieler).getName() + " hat das Spiel gewonnen!!");
        message.setFont(new Font("Arial", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, message);
        istSpielbeendet = true;

    }

    /**
     *
     * @param spieler
     * @param karte
     */
    public void spielerZugBestätigen (Spieler spieler, Karte karte,int index) throws InvalidSymbolSubmssionException, InvalidZahlSubmssionException {
        if (istKarteSpielbar(karte)==false) {
            if (karte.getSymbol() != ablageStapel.getObersteKarte().getSymbol()) {
                JLabel message = new JLabel("Ungültiger Zug, erwartetes Symbol: " + ablageStapel.getObersteKarte().getSymbol());
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
                throw new InvalidSymbolSubmssionException(message.getText(), karte.getSymbol(), ablageStapel.getObersteKarte().getSymbol());
            }
            if (karte.getZahl() != ablageStapel.getObersteKarte().getZahl()) {
                JLabel message = new JLabel("Ungültiger Zug, erwartete Zahl: " + ablageStapel.getObersteKarte().getZahl());
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
                throw new InvalidZahlSubmssionException(message.getText(), karte.getZahl(), ablageStapel.getObersteKarte().getZahl());
            }
        }else if (istKarteSpielbar(karte)==true) {
            spieler.karteEntfernen(index);
            ablageStapel.kartehinzufügen(karte);
            if (istrundeBeendet()) {
                Vector<Karte> allekarten = new Vector<Karte>();
                JLabel message = new JLabel(alleSpieler.get(aktuellerSpieler).getName() + " hat die Runde gewonnen!!");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
                for (int i = 0; i < alleSpieler.size(); i++) {
                    for (int j = 0; j < alleSpieler.get(i).getSpielerHand().size(); j++) {
                        allekarten.add(alleSpieler.get(i).getSpielerHand().get(j));
                    }
                }
                for (int i = 0; i < allekarten.size(); i++) {
                    spieler.punktieHinzufügen(allekarten.get(i).getPunkte());
                }

                if(maximalPunktezahl<= spieler.getPunktezahl()){
                    spielbeenden();
                }
                rundebeenden();
            } else if (spieler.getSpielerHand().size() == 1) {
                if (!spieler.hatTschau) {
                    if (kartenDeck.getKartenImDeck() <= 2) {
                        kartenDeckauffüllen();
                    }
                        spieler.karteHinzufügen(kartenDeck.karteZiehen());
                        spieler.karteHinzufügen(kartenDeck.karteZiehen());

                }
            } else if (spieler.getSpielerHand().size() == 0) {
                if (!spieler.hatSepp) {
                    if (kartenDeck.getKartenImDeck() <= 4) {
                        kartenDeckauffüllen();
                    }
                    for (int i = 0; i < 20; i++) {
                        spieler.karteHinzufügen(kartenDeck.karteZiehen());
                    }
                    spieler.hatTschau = false;
                   }
             }
         }
        if(aktuellerSpieler == 3){
            aktuellerSpieler = 0;
        }else{
            aktuellerSpieler++;
        }
    }
}



