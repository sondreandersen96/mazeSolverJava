import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NesteLabKnapp extends JButton{
    private Spillbrett spillbrett;
    
    public NesteLabKnapp(Spillbrett spillbrett) {
        super("->");
        this.spillbrett = spillbrett;
    }

    class KlikkNeste implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            spillbrett.nesteUtvei();
        }
    }

    public void addAction() {
        addActionListener(new KlikkNeste());
    }
}
