import java.util.ArrayList;

class HvitRute extends Rute{
    
    // Konstruktoer
    public HvitRute(int rad, int kolonne, Labyrint labyrint) {
        super(rad, kolonne, labyrint);
    }

    // Lager instanse av den abstrakte metoden i super klassen (Rute). 
    public char charTilTegn() {
        return '.';
    }

    public void gaa(Rute forrige, ArrayList<Tuppel> veiHit) {
        //System.out.println("Rute: Rad: " + this.rad + " Kolonne: " + this.kolonne);
        // Kopi av veien hit. 
        ArrayList<Tuppel> denneRutensVei = new ArrayList<Tuppel>(veiHit);
        denneRutensVei.add(new Tuppel(this.kolonne, this.rad));

    
        // Hvis vi er i en hvit rute skal vi bare spoerre videre, men ikke tilbake. 

        // Dette vil loese sykliske labyrinter ogsaa
        // Sjekker om vi tidligere har vaert i nord, syd, vest eller oest ruten (da vil vi unngaa StackOverFlowError ved sykliske labyrinter) 
        // En hvit rute kan ikke ha en null som nabo, saa trenger ikke sjekke det! 
        boolean vaertNord = false;
        boolean vaertSyd = false;
        boolean vaertVest = false;
        boolean vaertOest = false;
        for (Tuppel t : veiHit) {
            if (t.equals(new Tuppel(this.nord.kolonne, this.nord.rad))) {
                vaertNord = true;
            }
            if (t.equals(new Tuppel(this.syd.kolonne, this.syd.rad))) {
                vaertSyd = true;
            }
            if (t.equals(new Tuppel(this.vest.kolonne, this.vest.rad))) {
                vaertVest = true;
            }
            if (t.equals(new Tuppel(this.oest.kolonne, this.oest.rad))) {
                vaertOest = true;
            }
        }
        if (!vaertNord) this.nord.gaa(this, denneRutensVei);
        if (!vaertSyd) this.syd.gaa(this, denneRutensVei);
        if (!vaertVest) this.vest.gaa(this, denneRutensVei);
        if (!vaertOest) this.oest.gaa(this, denneRutensVei);
    }
}
