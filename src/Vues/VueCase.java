package Vues;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Modèles.*;

public class VueCase extends JPanel {
    private FenetreJeu f;
    private Case c;
    private JLabel label;

    public VueCase(FenetreJeu f, Case c, Desert d) {
        this.f = f;
        this.c = c;
        this.setPreferredSize(new Dimension(200, 200));
        this.label = new JLabel("");
        if (this.c.getTypeC() == Case.Type.OEIL) {
            this.label.setText("Oeil de la tempête");
        }
        this.add(this.label);
    }


    public void setCase(Case c) {
        this.c = c;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.c.getTypeC() == Case.Type.OEIL) {
            this.label.setText("");
            this.setBackground(Color.WHITE);
        } else if (this.c.getExplorer() && this.c.getTypeC() == Case.Type.MIRAGE) {
            this.label.setText(this.c.toString() + "  Mirage");
            this.setBackground(new Color(241, 182, 65));
        } else if ((this.c.getTypeC() == Case.Type.ENERGIE || this.c.getTypeC() == Case.Type.HELICE || this.c.getTypeC() == Case.Type.GOUVERNAIL || this.c.getTypeC() == Case.Type.MOTEUR) && this.c.getExplorer() == true) {
            if (this.c.getTypeC() == Case.Type.ENERGIE) {
                this.label.setText(this.c.toString() + " Pièce Energie");
            } else if (this.c.getTypeC() == Case.Type.HELICE) {
                this.label.setText(this.c.toString() + " Pièce Helice");
            } else if (this.c.getTypeC() == Case.Type.GOUVERNAIL) {
                this.label.setText(this.c.toString() + " Pièce Gouvernail");
            } else if (this.c.getTypeC() == Case.Type.MOTEUR) {
                this.label.setText(this.c.toString() + " Pièce Moteur");
            }
            this.setBackground(new Color(241, 182, 65));
            this.c.getTypeC().dessine(g);
        } else if (this.c.getExplorer() && this.c.getTypeC() == Case.Type.TUNNELS) {
            this.label.setText(this.c.toString() + "  Tunnels");
            this.setBackground(new Color(241, 182, 65));
        } else {
            this.label.setText(this.c.toString());
            this.setBackground(new Color(241, 182, 65));
        }
        switch (this.c.getTypeC()) {
            case OASIS:
            case MIRAGE:
                g.setColor(new Color(111, 154, 239, 255));
                g.fillRoundRect(80, 50, 40, 50, 60, 90);
                g.setColor(Color.BLACK);
                break;
            case CRASH:
                this.setBackground(new Color(146, 177, 234));
                drawJoueur(g, this.c.getJoueurs());
                break;
            case PISTE:
                this.setBackground(new Color(69, 103, 84));
                break;
        }
        drawJoueur(g, this.c.getJoueurs());

    }

    public void drawJoueur(Graphics g, ArrayList<Joueurs> joueurs) {
        for (int i = 2; i < joueurs.size() + 2; i++) {
            Joueurs j = joueurs.get(i - 2);
            if (j.getNum() == 1) {
                g.setColor(new Color(200, 100, 100));
            } else if (j.getNum() == 2) {
                g.setColor(new Color(200, 100, 200));
            } else if (j.getNum() == 3) {
                g.setColor(new Color(100, 50, 100));
            } else if (j.getNum() == 4) {
                g.setColor(new Color(100, 100, 200));
            } else if (j.getNum() == 5) {
                g.setColor(new Color(100, 200, 100));
            } else {
                g.setColor(new Color(0, 0, 0));
            }
            g.fillRect(i * 11, 100, 10, 10);
            g.setColor(new Color(0, 0, 0));
        }
    }

}