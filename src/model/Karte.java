package model;


public class Karte
    {
        enum Symbol {
            Rosen,Schellen,Schilten,Eichel;

            private static final Symbol[] symbole = Symbol.values();
            public static  Symbol getSymbol(int i) {
                return Symbol.symbole[i];
            }

        }
        enum Zahl {
            Sechs,Sieben,Acht,Neun,Zehn,Under,Ober,König,Ass;

            private static final Zahl[] zahlen = Zahl.values();
            public static  Zahl getzahl(int i) {
                return Zahl.zahlen[i];
            }
        }

        private final Symbol symbol;
        private final Zahl zahl;
        public int punkte;
        

        public Karte(final Symbol symbol, final Zahl zahl){
            this.symbol = symbol;
            this.zahl = zahl;
            punkteverteilen(zahl);
        }
        
        public Symbol getSymbol(){
            return this.symbol;
        }
        public Zahl getZahl(){
            return this.zahl;
        }

        public void setPunkte(int punkte) {
            this.punkte = punkte;
        }

        public int getPunkte() {
            return punkte;
        }
        public void punkteverteilen(Zahl zahl){
            Zahl[] zahlen = Zahl.values();
            if(zahl.equals(zahlen[0])){
                setPunkte(6);
            }
            else if(zahl.equals(zahlen[1])){
                setPunkte(7);
            }
            else if(zahl.equals(zahlen[2])){
                setPunkte(8);
            }
            else if(zahl.equals(zahlen[3])){
                setPunkte(9);
            }
            else if(zahl.equals(zahlen[4])){
                setPunkte(10);
            }
            else if(zahl.equals(zahlen[5])){
                setPunkte(20);
            }
            else if(zahl.equals(zahlen[6])){
                setPunkte(3);
            }
            else if(zahl.equals(zahlen[7])){
                setPunkte(4);
            }
            else if(zahl.equals(zahlen[8])){
                setPunkte(11);
            }
        }

    }
