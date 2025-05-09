package Vues;

import javax.swing.*;
import java.awt.*;

import Modèles.Case;

import Modèles.Desert;
import Contrôleurs.BougerJoueur;
import Contrôleurs.FindeTour;

public class VueJeu extends JPanel{

    private Desert d;
    private FenetreJeu f;
    private JLabel totalSableLabel;
    private JLabel niveauTempeteLabel;


    public VueJeu(FenetreJeu f, Desert desert) {
        this.d = desert;
        this.f = f;

        totalSableLabel = new JLabel("Total Sable: " + desert.totalSable());
        niveauTempeteLabel = new JLabel("Niveau de la Tempête: " + desert.getNiveauTempete());
        totalSableLabel.setFont(new Font("", Font.BOLD, 12));
        niveauTempeteLabel.setFont(new Font("", Font.BOLD, 12));
        totalSableLabel.setHorizontalAlignment(JLabel.CENTER);
        niveauTempeteLabel.setHorizontalAlignment(JLabel.CENTER);



        JPanel infoPanneau = new JPanel(new GridLayout(2, 1));
        JLabel regle = new JLabel("Pour creuser, appuyez sur C | Pour Explorer, appuyez sur E | Pour entrer dans un tunnel, appuyez sur T");
        regle.setFont(new Font("", Font.BOLD, 12));
        regle.setHorizontalAlignment(JLabel.CENTER);
        infoPanneau.add(totalSableLabel);
        infoPanneau.add(niveauTempeteLabel);;
        infoPanneau.add(regle);


        VueDesert vueDesert = new VueDesert(f, d, 5, 5);
        VueJoueur vueJoueurs = new VueJoueur(f,d);

        setLayout(new BorderLayout());
        add(infoPanneau, BorderLayout.NORTH);
        add(vueDesert, BorderLayout.CENTER);
        add(vueJoueurs, BorderLayout.EAST);



        FindeTour boutonFindeTour = new FindeTour(d,this, vueDesert, this.f, vueJoueurs);
        boutonFindeTour.setPreferredSize(new Dimension(5,3));
        BougerJoueur bougerJoueur = new BougerJoueur(d,vueDesert,vueJoueurs);
        infoPanneau.add(boutonFindeTour);

        this.addKeyListener(bougerJoueur);
        this.setFocusable(true);
        this.requestFocusInWindow();


    }

    public void update() {
        totalSableLabel.setText("Total Sable: " + d.totalSable());
        niveauTempeteLabel.setText("Niveau de la Tempête: " + d.getNiveauTempete());

    }

}
