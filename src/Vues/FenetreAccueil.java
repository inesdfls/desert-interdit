package Vues;

import Contrôleurs.Start;

import java.awt.*;
import javax.swing.*;

public class FenetreAccueil extends JPanel {
    private Start jouer;
    private JButton quitter;
    private JLabel nomduJeu;

    public FenetreAccueil(JPanel Panneau) {
        this.jouer = new Start(Panneau);
        this.jouer.setPreferredSize(new Dimension(100, 50)); // personnalisé la taille de jouer
        this.quitter = new JButton("Exit");
        this.quitter.setPreferredSize(new Dimension(100, 50)); // personnalisé la taille de quitter

        this.jouer = new Start(Panneau);
        this.quitter = new JButton("Exit");
        this.quitter.setFont(new Font("Times New Roman", Font.BOLD, 15));
        this.nomduJeu = new JLabel("LE DESERT INTERDIT");
        this.nomduJeu.setFont(new Font("Times New Roman", Font.BOLD, 40));
        this.nomduJeu.setHorizontalAlignment(JLabel.CENTER);

        JPanel bouton = new JPanel(new FlowLayout());
        bouton.setBackground(new Color(255, 228, 181));
        bouton.add(this.jouer);
        bouton.add(this.quitter);

        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(255, 228, 181));
        p.add(this.nomduJeu, BorderLayout.NORTH);
        p.add(bouton, BorderLayout.CENTER);
        this.add(p);
        this.setBackground(new Color(255, 228, 181));
    }

}
