import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Labyrint {
    // Instansvariabler
    private int antRader;
    private int antKolonner;
    private Rute[][] labyrintArr;
    private ArrayList<ArrayList <Tuppel>> utveier = new ArrayList<>();

    /** Konstruktor  
    @param filnavn navn paa filen der labyriten som skal opprettes ligger. 
    */
    public Labyrint(File fil) throws FileNotFoundException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Kunne ikke finne fil med navn: " + fil.getName());
            throw new FileNotFoundException("Kunne ikke opprette labyrint objekt.");
        }

        // Leser inn antall rader og kolonner 
        String foersteRad = scanner.nextLine();
        antRader = Integer.parseInt(foersteRad.split(" ")[0]);
        antKolonner = Integer.parseInt(foersteRad.split(" ")[1]);
        
        // Oppretter 2D-array med stoerrelsen som er gitt oeverst i filen.
        labyrintArr = new Rute[antRader][antKolonner];

        // Variabler for aa holde orden paa hvor i den todimmensjonale arrayen vi er. 
        int rad = 0;
        int kol = 0;
        while (scanner.hasNextLine()) {
            String linje = scanner.nextLine();
            // Looper igjennom hver karakter i linjen. 
            kol = 0;
            for (int i = 0; i < linje.length(); i++) {
                char c = linje.charAt(i);
                // Oppretter et Rute objekt 
                Rute rute;
                if (c == '#') {
                    rute = new SortRute(rad, kol, this);
                } else {
                    // Sjekk om det er en aapningsrute
                    if ((rad == 0) || (rad == antRader -1) || (kol == 0) || (kol == antKolonner -1)) {
                        rute = new Aapning(rad, kol, this);
                    } else {
                        // Hvis ikke er det en hvit rute
                        rute = new HvitRute(rad, kol, this);
                    }
                }
                // Legger rute inn i array 
                labyrintArr[rad][kol] = rute;

                kol++;
            }
            rad++;
        }
        // Lukker scanneren. 
        scanner.close();

        // Finner naboer
        Rute naavaerendeRute;
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                naavaerendeRute = labyrintArr[i][j];
                // Sjekker om det finnes en nabo paa hver side 
                // Er nabo over (nord)
                if (i > 0) naavaerendeRute.settNord(labyrintArr[i-1][j]);
                // Er nabo under (syd)
                if (i < antRader -1) naavaerendeRute.settSyd(labyrintArr[i+1][j]);
                // Er nabo til venstre (vest)
                if (j > 0) naavaerendeRute.settVest(labyrintArr[i][j-1]);
                // Er nabo til hoeyre (oest)
                if (j < antKolonner -1) naavaerendeRute.settOest(labyrintArr[i][j+1]); 
            }
        }    
    }


    public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int kol, int rad) {
        // Sletter utveier vi evt. har funnet fra andre koordinater 
        utveier = new ArrayList<>();

        // Lager en rute startRute og starter et rekursivt kall paa denne for aa finne en utvei. 
        Rute startRute = labyrintArr[rad][kol];
        startRute.finnUtvei();
        return utveier;
    }

    public void leggTilNyUtvei(ArrayList<Tuppel> utvei) {
        utveier.add(utvei);
    }

    public ArrayList<ArrayList<Tuppel>> hentUtveier() {
        return utveier;
    }

    public Rute[][] hentLabyrintArray() {
        return labyrintArr;
    }

    public int hentAntallKolonner() {
        return antKolonner;
    }

    public int hentAntallRader() {
        return antRader;
    }

    @Override
    public String toString() {
        // Skriver ut labyrinten. 
        String lab = "";

        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                lab += labyrintArr[i][j].charTilTegn();
            }
            lab += "\n";
        }        
        return lab;
    }

}
