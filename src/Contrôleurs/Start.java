package Contrôleurs;

import Modèles.Desert;
import Modèles.Joueurs;
import Vues.FenetreJeu;
import Vues.VueJeu;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Start extends JButton {
    private JPanel ecran;

    public Start(JPanel ecran) {
        this.ecran = ecran;
        this.setText("Start");
        this.setFont(new Font("Times New Roman", Font.BOLD, 15));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // création de la fenêtre pour choisir le nombre de joueurs
                FenetreJeu fenetreDeJeu = new FenetreJeu();
                JPanel ecran2 = new JPanel(new BorderLayout());

                // création du slider pour choisir le nombre de joueurs
                JSlider slider = new JSlider(0, 2, 5, 2);
                JLabel label = new JLabel("Nombre de joueurs : ");
                label.setFont(new Font("Times New Roman", Font.BOLD, 30));
                label.setHorizontalAlignment(JLabel.CENTER);
                slider.setPreferredSize(new Dimension(100, 50));
                slider.setMajorTickSpacing(1);
                slider.setMinorTickSpacing(1);
                slider.setPaintTicks(true);
                slider.setPaintLabels(true);
                ecran2.add(label, BorderLayout.NORTH);
                ecran2.add(slider, BorderLayout.CENTER);
                ecran2.setBackground(new Color(255, 228, 181));

                // création du bouton pour passer à la fenêtre de choix des noms
                JButton suivant = new JButton("Suivant");
                suivant.setFont(new Font("Times New Roman", Font.BOLD, 15));
                suivant.setPreferredSize(new Dimension(100, 30));
                suivant.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // création de la vue pour choisir les noms des joueurs
                        int nbJoueurs = slider.getValue();
                        Desert desert = new Desert();
                        VueJeu vueNoms = new VueJeu(fenetreDeJeu, desert);
                        ArrayList<String> nomsJoueurs = new ArrayList<String>();
                        for (int i = 0; i < nbJoueurs; i++) {
                            JTextField nomJoueur = new JTextField(5);
                            Object[] message = { "Nom du joueur " + (i+1) + " :", nomJoueur };
                            int option = JOptionPane.showConfirmDialog(null, message, "Entrez le nom du joueur", JOptionPane.OK_CANCEL_OPTION);
                            if (option == JOptionPane.OK_OPTION) {
                                String nom = nomJoueur.getText();
                                if (nom.trim().length() > 0) {
                                    nomsJoueurs.add(nom.trim());
                                    desert.getCase(0, 0).addJoueurs(new Joueurs(nom),desert);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Le nom du joueur ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                                    i--;
                                }
                            }else{
                                return;
                            }
                            }

                        ecran.removeAll();
                        ecran.add(vueNoms);
                        ecran.revalidate();
                        ecran.repaint();
                    }
                });
                ecran2.add(suivant, BorderLayout.SOUTH);


                // affichage de la fenêtre pour choisir le nombre de joueurs
                ecran.removeAll();
                ecran.add(ecran2);
                ecran.revalidate();
                ecran.repaint();
                ecran.setBackground(new Color(255, 228, 181));

            }
        });

    }
}
