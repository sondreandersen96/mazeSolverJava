import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
Denne klassen bestaar utelukkende av statiske "stil" variabler som 
skal gjelde for hele gui'et. 
*/
class GUISettings {
    public static Color Hvit() {
        return new Color(255,255,255);
    }
    public static Color Sort() {
        return new Color(70,70,70);
    }
    public static Color Roed() {
        return new Color(255,0,0);
    }
    public static Color Blaa() {
        return new Color(0,0,255);
    }
    public static Color SortHover() {
        return new Color(30,30,30);
    }
    public static Color HvitHover() {
        return new Color(190,190,190);
    }
    public static Color SpillbrettBakgrunn() {
        return new Color(10,10,10); // Graa 
    }
    public static int SpillRuteStoerrelse() {
        return 18;
    }
    public static int SpillRuteHorizontalGap() {
        return 1;
    }
    public static int SpillRuteVerticalGap() {
        return 1;
    }
}
