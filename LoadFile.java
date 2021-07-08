/*
IKKE I BRUK!!!!!
*/


import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

class LoadFile extends JButton {
    File file;

    public LoadFile(String btnName) {
        super(btnName);
    }


    class Klikk implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser velger = new JFileChooser();
            int resultat = velger.showOpenDialog(null);
            if (resultat != JFileChooser.APPROVE_OPTION) {
                System.exit(1);
            }
            file = velger.getSelectedFile();
        }
    }
    public void addAction() {
        addActionListener(new Klikk());
    }



    public File getFile() {
        if (file != null) {
            return file;
        }
        return null;
    }
}