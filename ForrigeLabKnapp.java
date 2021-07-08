import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ForrigeLabKnapp extends JButton{
    private Spillbrett spillbrett;
    
    public ForrigeLabKnapp(Spillbrett spillbrett) {
        super("<-");
        this.spillbrett = spillbrett;
    }

    class KlikkForrige implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            spillbrett.forrigeUtvei();
        }
    }

    public void addAction() {
        addActionListener(new KlikkForrige());
    }
}
