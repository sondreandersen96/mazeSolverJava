import java.util.ArrayList;

class SortRute extends Rute{
    
    // Konstruktoer
    public SortRute(int rad, int kolonne, Labyrint labyrint) {
        super(rad, kolonne, labyrint);
    }

    // Lager instanse av den abstrakte metoden i super klassen (Rute). 
    public char charTilTegn() {
        return '#';
    }

    public void gaa(Rute forrige, ArrayList<Tuppel> veiHit) {
        // Hvis vi havner i en sort rute skal vi ikke gjoere noe mer. 
        return;
    }

}
