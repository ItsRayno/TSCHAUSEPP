package model;



import java.util.Vector;

public class AblageStapel {
    private Vector<Karte> kartenAblage;

    public AblageStapel(){
        kartenAblage = new Vector<Karte>();

    }
    public void kartehinzufügen(Karte karte){

        kartenAblage.add(karte);
    }

    public void entferneAlleKarten() {
        for (int i = 0; i < kartenAblage.size(); i++) {
            kartenAblage.remove(i);
        }
    }

    public Karte getObersteKarte(){
        return kartenAblage.get(kartenAblage.size()-1);
    }

    public int getSize(){
        return kartenAblage.size();
    }
    public Karte getKarte(int i){
        return kartenAblage.get(i);
    }
    public void karteLöschen(int i){
        kartenAblage.remove(i);
    }

    public void print(){
        for (Karte karte : kartenAblage)
            System.out.println("Ablagestapel: "+ karte.getSymbol()+" "+karte.getZahl());
        }

}
