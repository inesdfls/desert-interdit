package Vues;

import javax.swing.*;
import java.awt.*;
import Modèles.*;
public class VueDesert extends JPanel {

    private Desert d;
    private VueCase[][] vueDesert;

    public VueDesert(FenetreJeu f, Desert d, int hauteur, int largeur) {
        this.d = d;
        this.vueDesert = new VueCase[5][5];
        this.setLayout(new GridLayout(hauteur, largeur, 2, 2));
        this.setPreferredSize(new Dimension(500, 500));
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                VueCase vc = new VueCase(f, d.getCase(i, j ), d);
                this.vueDesert[i][j] = vc;
                this.add(vc);
            }
        }
    }
    public void update() {
        for(int i = 0; i < 5; ++i) {
            for(int j = 0; j < 5; ++j) {
                this.vueDesert[i][j].setCase(this.d.getCase(i, j));
            }
        }
        this.revalidate();
        this.repaint();
    }
}


