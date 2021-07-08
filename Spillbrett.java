import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

class Spillbrett extends JPanel{
    protected int kolonner, rader;
    protected ArrayList<SpillRute> ruter = new ArrayList<>();
    protected Rute[][] labyrintArray;
    protected InfoPanel infoPanel;
    protected Labyrint labyrint; 
    protected ArrayList<ArrayList<Tuppel>> utveier;
    protected int utveiNrVises = 0;
    protected SpillRute aktivSpillRute;

    public Spillbrett(int kolonner, int rader, Rute[][] labyrintArray, InfoPanel infoPanel, Labyrint labyrint) {
        this.kolonner = kolonner;
        this.rader = rader;
        this.labyrintArray = labyrintArray;
        this.infoPanel = infoPanel;
        this.labyrint = labyrint; 
    }

    public void visUtvei(int nr, SpillRute startRute) {
        // Reset labyrint GUI
        resetLabGUI();
        // Fargelegg utvei blaa
        ArrayList<Tuppel> utvei = utveier.get(nr);
        // Gaar igjennom alle spillruter og fargelegger de som er en del av en utvei blaa.
        for (Tuppel t : utvei) {
            for (SpillRute r : ruter) {
                if (t.hentX() == r.hentKolonne() && t.hentY() == r.hentRad()) {
                    r.activeUtvei();
                }
            }
        }
        infoPanel.endreAntallSteg(utvei.size());
        startRute.activeKlikk();
        infoPanel.endreLoesningNr(utveiNrVises);
        return;
    }

    public void visKortesteUtvei() {
        // Finner den korteste utveien
        ArrayList<Tuppel> kortesteUtvei = utveier.get(0);
        int utveiNr = 0;
        int kortesteUtveiNr = 0;
        for (ArrayList<Tuppel> utvei : utveier) {
            if (utvei.size() < kortesteUtvei.size()) {
                kortesteUtvei = utvei;
                kortesteUtveiNr = utveiNr; 
            }
            utveiNr++;
        }
        this.utveiNrVises = kortesteUtveiNr;
        visUtvei(kortesteUtveiNr, aktivSpillRute);
    }

    private void resetLabGUI() {
        int kol = 0;
        int rad = 0;
        int n = 0; // Rute nr i Spillbrett variabelen ArrayList<ruter>
        String ruteTegn;
        SpillRute rute; 

        for (int i = 0; i < (rader * kolonner); i++) {
            ruteTegn = Character.toString(labyrintArray[rad][kol].charTilTegn());
            rute = ruter.get(n);
            rute.deactivate();
            if (ruteTegn.equals(".")) {
                rute.settOpprinneligFarge("hvit");
            } else if (ruteTegn.equals("#")) {
                rute.settOpprinneligFarge("sort");
            }
            n++;
            kol++;
            if (kol >= kolonner) {
                kol = 0;
                rad++;
            }
        }
    }

    public void finnUtveierFra(int kol, int rad, SpillRute spillRute) {
        // Resetter
        resetLabGUI();
        utveiNrVises = 0;

        // Finner utveier med rekursiv metode fra oblig 6
        utveier = labyrint.finnUtveiFra(kol, rad);
        // Setter nye infopanel variabler 
        infoPanel.endreAntLoesningerTekst("Antall loesninger: " + utveier.size());

        spillRute.activeKlikk();

        // Fargelegg utveien blaa
        if (utveier.size() > 0) {
            visUtvei(utveiNrVises, spillRute);
            infoPanel.endreLoesningNr(utveiNrVises);
            aktivSpillRute = spillRute; // Lagrer utgangspunkt ruten slik at neste og forrige knappen kan bruke de 
        } else {
            infoPanel.endreAntLoesningerTekst("Antall loesninger: Ingen!");
            infoPanel.endreAntallSteg(0);
            infoPanel.endreLoesningNr(-1); // Fordi funksjonen plusser paa en for aa faa en mer menneskelig indeks i GUIet (dvs. en som ikke starter paa null)
        }
    }

    public void nesteUtvei() {
        if (utveiNrVises < utveier.size()-1) {
            utveiNrVises++;
            visUtvei(utveiNrVises, this.aktivSpillRute);
        }
    }
    public void forrigeUtvei() {
        if (utveiNrVises > 0) {
            utveiNrVises--;
            visUtvei(utveiNrVises, this.aktivSpillRute);
        }
    }

    public void initGui() {
        // Lager et rows X cols rutenett
        JPanel rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(rader, kolonner, GUISettings.SpillRuteHorizontalGap(),GUISettings.SpillRuteVerticalGap())); // Rader, kolonner, hgap, vgap
        rutenett.setBackground(GUISettings.SpillbrettBakgrunn());

        int kol = 0;
        int rad = 0;
        String ruteTegn;
        SpillRute rute;
        for (int i = 0; i < (rader * kolonner); i++) {
            ruteTegn = Character.toString(labyrintArray[rad][kol].charTilTegn());

            if (ruteTegn.equals(".")) {
                rute = new SpillRute("hvit", kol, rad, this); 
            } else {
                rute = new SpillRute("sort", kol, rad, this); 
            }
        
            //SpillRute rute = new SpillRute("blaa", kol, rad, this);
            rute.initGui();
            rute.setPreferredSize(new Dimension(GUISettings.SpillRuteStoerrelse(),GUISettings.SpillRuteStoerrelse()));
            rute.setBorderPainted(false);
            rute.addAction(); // Oppretter en event listener paa knappen 
            rutenett.add(rute);

            ruter.add(rute);
            
            kol++;
            if (kol >= kolonner) {
                kol = 0;
                rad++;
            }
        }
        // Legger rutenettet til i panelet vi faar i konstruktoeren 
        add(rutenett);
    }
    public InfoPanel hentInfoPanel() {
        return infoPanel;
    }
}