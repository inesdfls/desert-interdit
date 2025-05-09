package Vues;

import Modèles.Desert;
import Modèles.Joueurs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueJoueur extends JPanel {
    private ArrayList<VueJoueurAux> allVueJoueur;
    private Desert desert;
    private boolean initJoueur;
    private FenetreJeu f;
    private JLabel Joueur;

    public VueJoueur(FenetreJeu f, Desert d) {
        this.desert = d;
        this.f = f;
        this.initJoueur = false;
        this.allVueJoueur = new ArrayList();
        this.setLayout(new GridLayout(6, 2, 2, 2));
        this.setPreferredSize(new Dimension(175, 10));
        this.Joueur = new JLabel("");
        this.add(this.Joueur);
    }

    public void update() {
        Joueurs srcEquipement;
        VueJoueurAux tmpVjx;
        //this.Joueur.setText("Tour de : " + this.desert.getJoueurTour().getNom());
        if (!this.initJoueur) {
            for (int i = 0; i < this.desert.getJoueurs().size(); i++) {
                srcEquipement = this.desert.getJoueurs().get(i);
                tmpVjx = new VueJoueurAux(srcEquipement, this);
                this.allVueJoueur.add(tmpVjx);
                this.add(tmpVjx);
            }

            this.initJoueur = true;
        }

        for (int i = 0; i < this.allVueJoueur.size(); i++) {
            VueJoueurAux vjx = this.allVueJoueur.get(i);
            Joueurs j = vjx.joueur;
            this.revalidate();
            this.repaint();
            return;
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Joueurs srcEquipement;
        VueJoueurAux tmpVjx;
        this.Joueur.setText("Tour de : " + this.desert.getJoueurTour().getNom());
        if(this.desert.getJoueurTour().getNum()==1) {
            this.Joueur.setForeground(new Color(200,100,100));
        }else if(this.desert.getJoueurTour().getNum()==2){
            this.Joueur.setForeground(new Color(200,100,200));
        }else if (this.desert.getJoueurTour().getNum()==3){
            this.Joueur.setForeground(new Color(100,50,100));
        }else if (this.desert.getJoueurTour().getNum()==4){
            this.Joueur.setForeground(new Color(100,100,200));
        }else if (this.desert.getJoueurTour().getNum()==5){
            this.Joueur.setForeground(new Color(100, 200, 100));
        }else{
            this.Joueur.setForeground(new Color(0, 0, 0));
        }
        if (!this.initJoueur) {
            for (int i = 0; i < this.desert.getJoueurs().size(); i++) {
                srcEquipement = this.desert.getJoueurs().get(i);
                tmpVjx = new VueJoueurAux(srcEquipement, this);
                this.allVueJoueur.add(tmpVjx);
                this.add(tmpVjx);
            }

            this.initJoueur = true;
        }

        for (int i = 0; i < this.allVueJoueur.size(); i++) {
            VueJoueurAux vjx = this.allVueJoueur.get(i);
            Joueurs j = vjx.joueur;
            this.revalidate();
            this.repaint();
            return;
        }
    }

    public class VueJoueurAux extends JPanel{
        private VueJoueur vueJoueurs;
        private Joueurs joueur;
        private JLabel nomJoueur;
        private JLabel actions;
        private JLabel niveauEau;
        private JLabel piece;


        public VueJoueurAux(Joueurs j, VueJoueur vj) {
            this.vueJoueurs = vj;
            this.joueur = j;
            this.setLayout(new BoxLayout(this, 1));
            this.nomJoueur = new JLabel("Joueur : " + j.getNom());

            this.actions = new JLabel("Actions : " + j.getActions());
            int nivEau = j.getNivEau();
            this.niveauEau = new JLabel("Niveau d'eau : " + nivEau);
            ArrayList<String> piece = j.getPiece();
            String text = "";
            for (String element : piece) {
                text += element + ", ";
            }
            this.piece = new JLabel("Pièces : " +text);

            if(this.joueur.getNum()==1) {
                this.setBorder(BorderFactory.createLineBorder(new Color(200,100,100), 5, true));
            }else if(this.joueur.getNum()==2){
                this.setBorder(BorderFactory.createLineBorder(new Color(200,100,200), 5, true));
            }else if (this.joueur.getNum()==3){
                this.setBorder(BorderFactory.createLineBorder(new Color(100,50,100), 5, true));
            }else if (this.joueur.getNum()==4){
                this.setBorder(BorderFactory.createLineBorder(new Color(100,100,200), 5, true));
            }else if (this.joueur.getNum()==5){
                this.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 100), 5, true));
            }else{
                this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 5, true));
            }
            this.add(this.nomJoueur);
            this.add(this.actions);
            this.add(this.niveauEau);
            this.add(this.piece);
        }
        public void update() {
            this.revalidate();
            this.repaint();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            JLabel nvEau1 = this.niveauEau;
            int nvEau2 = this.joueur.getNivEau();
            nvEau1.setText("Niveau d'eau : " + nvEau2 );
            this.actions.setText("Actions : " + this.joueur.getActions());
            ArrayList<String> piece = this.joueur.getPiece();
            String text = "";
            for (String element : piece) {
                text += element + ", ";
            }
            this.piece.setText("Pièces : " +text);
        }
    }
}
