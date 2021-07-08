import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;


class Main {
    public static void main(String[] args) {
        // Aapner filvelger 
        JFileChooser velger = new JFileChooser();
        int resultat = velger.showOpenDialog(null);
        if (resultat != JFileChooser.APPROVE_OPTION) System.exit(1); 
        
        // Lagerer filen 
        File fil = velger.getSelectedFile();


        // Oppretter ett vindu 
        JFrame vindu = new JFrame("Labyrint solver");

        // Forteller at vi skal lukke GUI-traaden naar vi lukker vinduet
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Lager et panel vi kan legge elementer paa
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        // Legger panelet vi laget til i vinduet
        vindu.add(panel);


        // ------ Funksjonalitet --------
        
        // Topp panel
        JPanel toppPanel = new JPanel();
        panel.add(toppPanel, BorderLayout.PAGE_START);
        

        JLabel fileNameHeader = new JLabel("Filnavn: " + fil.getName());
        toppPanel.add(fileNameHeader);
        
        // VenstrePanel
        InfoPanel venstrePanel = new InfoPanel();
        Border venstreBorder = BorderFactory.createTitledBorder("Informasjon");
        venstrePanel.setBorder(venstreBorder);
        panel.add(venstrePanel, BorderLayout.LINE_START);
        


        // HoeyrePanel
        Labyrint labyrint = null;
        Rute[][] labyrintArray = null;
        try {
            labyrint = new Labyrint(fil);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
        System.out.println(labyrint);
        labyrintArray = labyrint.hentLabyrintArray();


        JPanel hoeyrePanel = new JPanel();
        Border hoeyreBorder = BorderFactory.createTitledBorder("Labyrint");
        hoeyrePanel.setBorder(hoeyreBorder);
        hoeyrePanel.setLayout(new BoxLayout(hoeyrePanel, BoxLayout.Y_AXIS));
        panel.add(hoeyrePanel, BorderLayout.CENTER);
        
        // Legger til spillbrett 
        Spillbrett spillbrett = new Spillbrett(labyrint.hentAntallKolonner(), labyrint.hentAntallRader(), labyrintArray, venstrePanel, labyrint);
        spillbrett.initGui();
        venstrePanel.leggTilSpillbrett(spillbrett);
        hoeyrePanel.add(spillbrett);
        
        // Maa initGui paa venstre panel etter aa ha lagt til spillbrettet over 
        venstrePanel.initGui();

        
        // ------ Funksjonalitet slutt --------

        // Pakk sammen og gjoer alt synlig for bruker 
        vindu.pack();
        vindu.setVisible(true);
    }
}