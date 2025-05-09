package Contrôleurs;

import javax.swing.*;
import Modèles.*;
import Vues.FenetreJeu;
import Vues.VueDesert;
import Vues.VueJeu;
import Vues.VueJoueur;

import java.awt.*;
import java.util.Random;

public class FindeTour extends JButton {
    private Desert d;
    private JDialog finJeu;

    public FindeTour(Desert d, VueJeu vj, VueDesert vd, FenetreJeu f, VueJoueur vjo){
        this.d = d;
        this.finJeu = new JDialog();
        this.setText("Fin de tour");
        this.setPreferredSize(new Dimension(100, 50));
        BougerJoueur bougerJoueur = new BougerJoueur(d, vd, vjo);
        this.addKeyListener(bougerJoueur);
        this.addActionListener(e -> {
            boolean M = true;
            if(d.getPieceRecup().size()==4 && d.getCase(4,4).getJoueurs().size()==d.getJoueurs().size()) {
                JDialog finJeu = new JDialog(f, "Fin du Jeu");
                JLabel Gagne = new JLabel("Partie Gagnée :)");
                Gagne.setHorizontalAlignment(JLabel.CENTER);
                Gagne.setVerticalAlignment(JLabel.CENTER);
                Gagne.setFont(new Font("Times New Roman", Font.BOLD, 40));
                Gagne.setForeground(Color.WHITE);
                finJeu.setBackground(new Color(222, 180, 90));
                finJeu.setMinimumSize(new Dimension(600,600));
                finJeu.setContentPane(Gagne);
                finJeu.pack();
                finJeu.setVisible(true);
            }else if(this.d.getNiveauTempete() < 7 && this.d.totalSable() < 48){
                    int randomA = new Random().nextInt(31);
                    if (randomA < 3) {
                        d.dechaine();
                    }else if(randomA<15)  {
                        int randomDir = new Random().nextInt(4);
                        if (randomDir == 0) {
                            d.souffle(1, Desert.Direction.NORD);
                        } else if (randomDir == 1) {
                            d.souffle(1, Desert.Direction.SUD);
                        } else if (randomDir == 2) {
                            d.souffle(1, Desert.Direction.OUEST);
                        } else if (randomDir == 3) {
                            d.souffle(1, Desert.Direction.EST);
                        }
                    }else if(randomA<23)  {
                        int randomDir = new Random().nextInt(4);
                        if (randomDir == 0) {
                            d.souffle(2, Desert.Direction.NORD);
                        } else if (randomDir == 1) {
                            d.souffle(2, Desert.Direction.SUD);
                        } else if (randomDir == 2) {
                            d.souffle(2, Desert.Direction.OUEST);
                        } else if (randomDir == 3) {
                            d.souffle(2, Desert.Direction.EST);
                        }
                    }else if(randomA<27)  {
                        int randomDir = new Random().nextInt(4);
                        if (randomDir == 0) {
                            d.souffle(3, Desert.Direction.NORD);
                        } else if (randomDir == 1) {
                            d.souffle(3, Desert.Direction.SUD);
                        } else if (randomDir == 2) {
                            d.souffle(3, Desert.Direction.OUEST);
                        } else if (randomDir == 3) {
                            d.souffle(3, Desert.Direction.EST);
                        }
                    }else if(randomA<31)  {
                        M = d.vagueDeChaleur();
                    }
                }
            vj.update();
            vd.update();
            vjo.update();
            if (this.d.getNiveauTempete() >= 7 || this.d.totalSable() >= 43 || M==false) {
                JDialog finJeu = new JDialog(f, "Fin du Jeu");
                JLabel perdu;
                if(this.d.getNiveauTempete() >= 7) {
                    perdu = new JLabel("Partie Perdue :( (par tempête surpuissante)");
                }else if(this.d.totalSable() >= 43){
                    perdu = new JLabel("Partie Perdue :( (par ensablement)");
                }else{
                    perdu = new JLabel("Partie Perdue :( (par mort de soif)");
                }
                perdu.setHorizontalAlignment(JLabel.CENTER);
                perdu.setVerticalAlignment(JLabel.CENTER);
                perdu.setFont(new Font("Times New Roman", Font.BOLD, 40));
                perdu.setForeground(Color.WHITE);
                perdu.setBackground(new Color(200,100,100));

                finJeu.setBackground(new Color(222, 180, 90));
                finJeu.setMinimumSize(new Dimension(600,600));
                finJeu.setContentPane(perdu);
                finJeu.pack();
                finJeu.setVisible(true);
            }
        });
    }


}
