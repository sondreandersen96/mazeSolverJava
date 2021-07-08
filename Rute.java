import java.util.ArrayList;

abstract class Rute {
    protected int rad;
    protected int kolonne;
    protected Labyrint labyrint;

    // Nabo-ruter
    protected Rute nord;
    protected Rute syd;
    protected Rute vest;
    protected Rute oest;

    // Konstruktoer
    public Rute(int rad, int kolonne, Labyrint labyrint) {
        this.rad = rad;
        this.kolonne = kolonne;
        this.labyrint = labyrint;
        this.nord = null;
        this.syd = null;
        this.vest = null;
        this.oest = null;
    }

    public void settNord(Rute rute) {
        this.nord = rute;
    }
    public void settSyd(Rute rute) {
        this.syd = rute;
    }
    public void settVest(Rute rute) {
        this.vest = rute;
    }
    public void settOest(Rute rute) {
        this.oest = rute;
    }

    public void finnUtvei() {
        // Dette skal kalles paa gaa. 
        this.gaa(this, new ArrayList<Tuppel>());
    }



    abstract public char charTilTegn();
    abstract public void gaa(Rute forrige, ArrayList<Tuppel> veiHit);

}
