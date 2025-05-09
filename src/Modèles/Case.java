package Modèles;

import java.awt.*;
import java.util.ArrayList;

public class Case {
    private int niveauSable;
    private Coordonnées coord;
    private Desert d;
    private Type typeC ;
    private boolean Explorer;

    public ArrayList<Joueurs> joueurs;

    public Case(int niveauSable, Type typeC, Desert d,int x, int y){
        this.niveauSable = niveauSable;
        this.typeC = typeC;
        this.d = d;
        this.joueurs = new ArrayList();
        this.Explorer = false;
        this.setCoord(x,y);
    }

    public void setCoord(int x, int y) {
        this.coord = new Coordonnées(x, y);
    }
    public Coordonnées getCoord(){
        return this.coord;
    }
    public boolean getExplorer(){ return this.Explorer;}
    public void setExplorer(boolean b){ this.Explorer=b;}

    public boolean Explorer(){
        Joueurs joueursact = this.d.getJoueurTour();
        if(joueursact.getPosition() == this){
            if(this.getExplorer() == false){
                this.setExplorer(true);
            }
            return this.getExplorer() == true;
        }
        return this.getExplorer() == false;
    }


   public int getNiveauSable(){ return this.niveauSable;}

    public void augNivSable() {
        niveauSable++;
    }
    public void decNiveauSable(){ niveauSable--;}

    public Type getTypeC(){ return this.typeC; }

    public void setType(Type o) { this.typeC = o; }
    public ArrayList<Joueurs> getJoueurs(){
        return this.joueurs;
    }

    public void removeJoueursC(Joueurs j, Desert d){
        this.joueurs.remove(j);
    }
    public void addJoueurs(Joueurs j, Desert d){
        this.joueurs.add(j);
        d.addJoueur(j);
        j.setPosition(this);
    }
    public void addJoueursC(Joueurs j, Desert d){
        this.joueurs.add(j);
        j.setPosition(this);
    }

    public String toString() {
        String res = "Sable: " + this.getNiveauSable();
        return res;
    }



    public static enum Type{
        OEIL ("Oeil"),
        QUELCONQUE("Quelconque"),
        CRASH ("Crash"),

        PISTE("Piste atterissage"),
        OASIS ("Oasis"),

        MIRAGE ("MIRAGE"),
        TUNNELS("Tunnels"),

        MOTEUR("Moteur"),
        GOUVERNAIL("Gouvernail"),
        ENERGIE("Energie"),
        HELICE("Helice");


        private String type;
        private Type(String type){ this.type = type ;}
        public String getType(){ return this.type; }
        public void dessine(Graphics g) {
            switch (this) {
                case MOTEUR:
                    g.setColor(new Color(112, 112, 73));
                    g.fillRoundRect(80, 50, 40, 50, 60, 90);
                    g.setColor(Color.BLACK);
                    break;
                case GOUVERNAIL:
                    g.setColor(new Color(187, 99, 116));
                    g.fillRoundRect(80, 50, 40, 50, 60, 90);
                    g.setColor(Color.BLACK);
                    break;
                case HELICE:
                    g.setColor(new Color(50, 134, 90));
                    g.fillRoundRect(80, 50, 40, 50, 60, 90);
                    g.setColor(Color.BLACK);
                    break;
                case ENERGIE:
                    g.setColor(new Color(66, 122, 124));
                    g.fillRoundRect(80, 50, 40, 50, 60, 90);
                    g.setColor(Color.BLACK);
                    break;
            }
        }
    }
}
