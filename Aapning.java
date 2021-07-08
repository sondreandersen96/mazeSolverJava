import java.util.ArrayList;

class Aapning extends HvitRute {
    
    // Konstruktoer
    public Aapning(int rad, int kolonne, Labyrint labyrint) {
        super(rad, kolonne, labyrint);
    }

    // Lager instanse av den abstrakte metoden i super klassen (Rute). 
    public char charTilTegn() {
        return '.';
    }

    public void gaa(Rute forrige, ArrayList<Tuppel> veiHit) {
        // Kopierer veien til denne ruten og legger til denne ruten. 
        ArrayList<Tuppel> denneRutensVei = new ArrayList<Tuppel>(veiHit);
        denneRutensVei.add(new Tuppel(this.kolonne, this.rad));

        // Lagrer denne utveien i Labyrint objektet. 
        labyrint.leggTilNyUtvei(denneRutensVei);


        // Leter etter evtuelle andre aapninger. 

        // Dette vil loese sykliske labyrinter ogsaa
        // Sjekker om vi tidligere har vaert i nord, syd, vest eller oest ruten (da vil vi unngaa StackOverFlowError ved sykliske labyrinter) 
        // En hvit rute kan ikke ha en null som nabo, saa trenger ikke sjekke det! 
        boolean vaertNord = false;
        boolean vaertSyd = false;
        boolean vaertVest = false;
        boolean vaertOest = false;
        for (Tuppel t : veiHit) {
            if (this.nord != null) {
                if (t.equals(new Tuppel(this.nord.kolonne, this.nord.rad))) {
                    vaertNord = true;
                }
            }
            if (this.syd != null) {
                if (t.equals(new Tuppel(this.syd.kolonne, this.syd.rad))) {
                    vaertSyd = true;
                }
            }
            if (this.vest != null) {
                if (t.equals(new Tuppel(this.vest.kolonne, this.vest.rad))) {
                    vaertVest = true;
                }
            }
            if (this.oest != null) {
                if (t.equals(new Tuppel(this.oest.kolonne, this.oest.rad))) {
                    vaertOest = true;
                }
            }
        }
        // Det kan vaere flere aapninger ved siden av hverandre. 
        if (!vaertNord && this.nord != null) this.nord.gaa(this, denneRutensVei);
        if (!vaertSyd && this.syd != null) this.syd.gaa(this, denneRutensVei);
        if (!vaertVest && this.vest != null) this.vest.gaa(this, denneRutensVei);
        if (!vaertOest && this.oest != null) this.oest.gaa(this, denneRutensVei);
    }
}
