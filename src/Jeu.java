import Modèles.*;
import Vues.*;

import javax.swing.*;
import java.awt.*;

public class Jeu extends JFrame {
    public Jeu(){}
    private static Desert creationPanneauJeu(FenetreJeu f, JPanel DesertI) {
        Desert d = new Desert();
        VueJeu vj = new VueJeu(f, d);
        DesertI.add(vj, "vue_jeu");
        return d;
    }

    public static void main(String[] args) {
        FenetreJeu DesertInterdit = new FenetreJeu();
        JPanel Jeu = new JPanel(new CardLayout());
        Desert d = new Desert();
        FenetreAccueil a = new FenetreAccueil(Jeu);
        Jeu.add(a, "fenetre_accueil");
        d = creationPanneauJeu(DesertInterdit, Jeu);
        DesertInterdit.ajouteElement(Jeu);
        DesertInterdit.drawFenetre();
    }
}