package Vues;

import Modèles.Desert;

import javax.swing.*;
import java.awt.*;

public class FenetreJeu extends JFrame{

    private JPanel e;

    private JFrame frame;


    public FenetreJeu(){
        this.frame = new JFrame("Le Désert Interdit");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800,680));
        this.e = new JPanel(new BorderLayout());
        e.setBackground(new Color(255, 228, 181));
        this.add(this.e);

    }

    public void ajouteElement(JComponent element) {
        this.e.add(element);
    }

    public void drawFenetre() {
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(3);

    }
}


