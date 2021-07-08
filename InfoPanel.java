import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

class InfoPanel extends JPanel {
    protected JPanel infoTekst;

    protected JLabel startRute;
    protected JLabel antLoesninger;
    protected JLabel loesningNr;
    protected JLabel antallSteg;
    protected KortesteUtveiLabKnapp visKortesteUtvei;
    protected ForrigeLabKnapp forrigeUtvei;
    protected NesteLabKnapp nesteUtvei;
    protected Spillbrett spillbrett;

    

    public InfoPanel() {
        
    }

    public void initGui() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Lager et eget panel for teksten i dette panelet. 
        infoTekst = new JPanel();
        Border border = infoTekst.getBorder();
        Border margin = new EmptyBorder(20,10,10,50); // Top, left, bottom, right
        infoTekst.setBorder(new CompoundBorder(border, margin));
        infoTekst.setLayout(new BoxLayout(infoTekst, BoxLayout.Y_AXIS));
        add(infoTekst);


        startRute = new JLabel("Start rute (kol, rad):          ");
        antLoesninger = new JLabel("Antall loesninger:   ");
        
        loesningNr = new JLabel("Loesning nr:   ");
        antallSteg = new JLabel("Antall steg: ");
        visKortesteUtvei = new KortesteUtveiLabKnapp(spillbrett);

        forrigeUtvei = new ForrigeLabKnapp(spillbrett);
        nesteUtvei = new NesteLabKnapp(spillbrett);

        visKortesteUtvei.addAction();
        forrigeUtvei.addAction();
        nesteUtvei.addAction();


        // Infotekst 
        infoTekst.add(startRute);
        infoTekst.add(antLoesninger);
        
        infoTekst.add(loesningNr);
        infoTekst.add(antallSteg);



        add(visKortesteUtvei);
        
        add(forrigeUtvei);
        add(nesteUtvei);
    }

    public void endreStartRuteTekst(String nyTekst) {
        startRute.setText(nyTekst);
    }
    public void endreAntLoesningerTekst(String nyTekst) {
        antLoesninger.setText(nyTekst);
    }
    public void endreLoesningNr(int nr) {
        loesningNr.setText("Loesning nr: " + (nr + 1));
    }
    public void endreAntallSteg(int antall) {
        antallSteg.setText("Antall steg: " + antall);
    }

    public void leggTilSpillbrett(Spillbrett spillbrett) {
        this.spillbrett = spillbrett;
    }

}
