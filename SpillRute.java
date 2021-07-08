import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SpillRute extends JButton{
    protected String farge; // Fargen ruten skal ha i GUI'et 
    protected Spillbrett spillbrett;
    protected int kol;
    protected int rad;
    protected InfoPanel infoPanel;
    protected boolean aktivRute = false;

    public SpillRute(String farge, int kol, int rad, Spillbrett spillbrett) {
        super(); // Sender ikke noe tekst til JButton 
        this.farge = farge; // Har foelgende mulige farger: hvit, sort, roed, blaa
        this.kol = kol;
        this.rad = rad;
        this.spillbrett = spillbrett;
        this.infoPanel = spillbrett.hentInfoPanel();
    

        System.out.println("Rute opprettet paa kol: " + kol + " rad: " + rad);
    }
    
    public void initGui() {
        // Fjerner selve knappen utseende - slik at vi bare ser bakgrunnsfargen. 
        setOpaque(true);
        // Setter utgangsfargen til ruten
        if (this.farge.equals("hvit")) setBackground(GUISettings.Hvit());
        if (this.farge.equals("sort")) setBackground(GUISettings.Sort());
        if (this.farge.equals("roed")) setBackground(GUISettings.Roed());
        if (this.farge.equals("blaa")) setBackground(GUISettings.Blaa());
    }

    class Klikk implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Printer ut rute koordinater
            System.out.println("Klikk: kol: " + kol + " rad: " + rad);
            infoPanel.endreStartRuteTekst("Start rute (kol, rad): (" + kol + ", " + rad + ")");
            spillbrett.finnUtveierFra(kol, rad, hentDenne());
        }
    }
    public SpillRute hentDenne() {
        return this;
    }

    // For aa faa til en "hover" effekt
    class Hover implements MouseListener {
        @Override
        public void mouseEntered(MouseEvent e) {
            if (!aktivRute) {
                if (farge.equals("hvit")) setBackground(GUISettings.HvitHover());
                if (farge.equals("sort")) setBackground(GUISettings.SortHover());
            }
        }
        @Override
        public void mouseExited(MouseEvent e) {
            if (!aktivRute) {
                if (farge.equals("hvit")) setBackground(GUISettings.Hvit());
                if (farge.equals("sort")) setBackground(GUISettings.Sort());
            }
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            return;
        }
        @Override
        public void mousePressed(MouseEvent e) {
            return;
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            
        }
    }
    public void addAction() {
        addActionListener(new Klikk());
        addMouseListener(new Hover());
    }
    
    public void settOpprinneligFarge(String farge) {
        this.farge = farge;
        if (this.farge.equals("hvit")) setBackground(GUISettings.Hvit());
        if (this.farge.equals("sort")) setBackground(GUISettings.Sort());
    }

    public void deactivate() {
        aktivRute = false;
        if (this.farge.equals("hvit")) setBackground(GUISettings.Hvit());
        if (this.farge.equals("sort")) setBackground(GUISettings.Sort());
    }

    public void activeKlikk() {
        aktivRute = true;
        setBackground(GUISettings.Roed());
    }
    public void activeUtvei() {
        aktivRute = true;
        setBackground(GUISettings.Blaa());
    }

    public int hentRad() {
        return rad;
    }
    public int hentKolonne() {
        return kol;
    }

}