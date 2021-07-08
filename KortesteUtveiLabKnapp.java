import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class KortesteUtveiLabKnapp extends JButton {
    private Spillbrett spillbrett;

    public KortesteUtveiLabKnapp(Spillbrett spillbrett) {
        super("Vis korteste utvei");
        this.spillbrett = spillbrett;
    }

    class Klikk implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            spillbrett.visKortesteUtvei();
        }
    }

    public void addAction() {
        addActionListener(new Klikk());
    }
}
