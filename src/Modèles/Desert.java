package Modèles;

import java.util.ArrayList;

public class Desert {
    private Case[][] grille = new Case[5][5];
    private Coordonnées Oeil = new Coordonnées(2,2);
    private double niveauTempête;
    private Direction vent;
    private ArrayList<Case> cases =  new ArrayList<>();
    private ArrayList<Joueurs> ensJoueurs;
    private Case.Type  avantOeil;
    private int joueurTour;
    private  ArrayList<String> pieceRecup;


    public Desert(){
        this.pieceRecup = new ArrayList<>();
        this.niveauTempête = 2.0;
        this.vent = Direction.NORD;
        this.avantOeil = Case.Type.QUELCONQUE;
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.grille[i][j] = new Case(0, Case.Type.QUELCONQUE, this, j, i);
            }
        }
        this.grille[2][2] = new Case(0, Case.Type.OEIL,this,2,2);
        //EAUX
        this.grille[2][0] = new Case(0, Case.Type.OASIS,this,0,2); //aug du nivEau de 2
        this.grille[4][3] = new Case(0, Case.Type.OASIS,this,3,4);
        this.grille[0][2] = new Case(0, Case.Type.MIRAGE,this,2,0); //pas d'eau

        //DEPART ET ARRIVEE
        this.grille[4][4] = new Case(0, Case.Type.PISTE,this,4,4); //pas d'eau
        this.grille[0][0] = new Case(0, Case.Type.CRASH, this,0,0);

        //PIECES
        this.grille[1][0] = new Case(0, Case.Type.HELICE,this,0,1);
        this.grille[1][3] = new Case(0, Case.Type.GOUVERNAIL,this,3,1);
        this.grille[2][1] = new Case(0, Case.Type.ENERGIE, this,1,2);
        this.grille[0][4] = new Case(0, Case.Type.MOTEUR, this,4,0);

        //TUNNELS
        this.grille[4][0] = new Case(0, Case.Type.TUNNELS,this,0,4);
        this.grille[3][3] = new Case(0, Case.Type.TUNNELS,this,3,3);

        this.grille[0][2].augNivSable();
        this.grille[1][1].augNivSable();
        this.grille[1][3].augNivSable();
        this.grille[2][0].augNivSable();
        this.grille[2][4].augNivSable();
        this.grille[3][1].augNivSable();
        this.grille[3][3].augNivSable();
        this.grille[4][2].augNivSable();
        this.ensJoueurs = getJoueurs();
        this.joueurTour = 1;
    }

    public void addPieceRecup(String s){
        this.pieceRecup.add(s);
    }
    public ArrayList<String> getPieceRecup(){
        return this.pieceRecup;
    }
    public Case getCase(int col, int lig){
        return grille[lig][col];
    }
    public void setCase(Case c, int x, int y){
        this.grille[y][x]=c;
        this.cases.add(c);
        c.setCoord(x,y);
    }
    public ArrayList<Joueurs> getJoueurs(){
        ArrayList<Joueurs> A = new ArrayList<>();
        ArrayList<Joueurs> B = new ArrayList<>();
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                B =this.grille[i][j].getJoueurs();
                for(int k = 0; k<B.size();k++){
                    A.add(B.get(k));
                }
            }
        }
        return A;
    }
    public Joueurs getJoueurTour() {
        for (int i = 0; i < this.ensJoueurs.size(); i++) {
            if (this.ensJoueurs.get(i).getNum() == this.joueurTour) {
                return this.ensJoueurs.get(i);
            }
        }
        return null;
    }
    public void setJoueurTour(int i){
        this.joueurTour = i;
    }
    public void addJoueur(Joueurs j){
        this.ensJoueurs.add(j);
        this.ensJoueurs.get(this.ensJoueurs.size()-1).setNum(this.ensJoueurs.size());

    }
    public void removeJoueur(Joueurs j){
        this.ensJoueurs.remove(j);
    }
    public void dechaine(){
        if(this.niveauTempête < 8){
            this.niveauTempête+=0.5;
        }
        this.changeJoueurTour();
    }



    public int totalSable(){
        int n=0;
        for(int i =0; i<5; i++){
            for(int j=0; j<5; j++ ){
                if(grille[i][j]!=null) {
                    n = n + grille[i][j].getNiveauSable();
                }
            }
        }
        return n;
    }

    public void souffle(int force, Direction dv){
        int x = this.Oeil.getCol();
        int y = this.Oeil.getLig();
        if(dv==Direction.OUEST){
            for(int i=0; i<force; i++){
                if(y-i>=0) {
                    this.grille[y - i][x].augNivSable();
                }
            }
            if(y-force>=0) {
                this.grille[y][x].setType(this.avantOeil);
                this.avantOeil = this.grille[y-force][x].getTypeC();
                this.grille[y-force][x].setType(Case.Type.OEIL);
                this.Oeil = new Coordonnées(x, y-force);

            } else if (y-(force-1)>=0) {
                this.grille[y][x].setType(this.avantOeil);
                this.avantOeil = this.grille[y-(force-1)][x].getTypeC();
                this.grille[y-(force-1)][x].setType(Case.Type.OEIL);
                this.Oeil = new Coordonnées(x, y-(force-1));
            }else if (y-(force-2)>=0) {
                this.grille[y][x].setType(this.avantOeil);
                this.avantOeil = this.grille[y-(force-2)][x].getTypeC();
                this.grille[y - (force - 2)][x].setType(Case.Type.OEIL);
                this.Oeil = new Coordonnées(x, y - (force - 2));
            }
        } else if (dv==Direction.EST) {
            for(int i=0; i<force; i++){
                if(y+i<=4) {
                    this.grille[y + i][x].augNivSable();
                }
            }
            if(y+force<=4) {
                this.grille[y][x].setType(this.avantOeil);
                this.avantOeil = this.grille[y+force][x].getTypeC();
                this.grille[y+force][x].setType(Case.Type.OEIL);
                this.Oeil = new Coordonnées(x, y+force);
            } else if (y+(force-1)<=4) {
                this.grille[y][x].setType(this.avantOeil);
                this.avantOeil = this.grille[y+(force-1)][x].getTypeC();
                this.grille[y+(force-1)][x].setType(Case.Type.OEIL);
                this.Oeil = new Coordonnées(x, y+(force-1));
            }else if (y+(force-2)<=4) {
                this.grille[y][x].setType(this.avantOeil);
                this.avantOeil = this.grille[y+(force-2)][x].getTypeC();
                this.grille[y + (force - 2)][x].setType(Case.Type.OEIL);
                this.Oeil = new Coordonnées(x, y +(force - 2));
            }
        } else if (dv==Direction.SUD) {
            for(int i=0; i<force; i++){
                if(x+i<=4) {
                    this.grille[y][x + i].augNivSable();
                }
            }
            if(x+force<=4) {
                this.grille[y][x].setType(this.avantOeil);
                this.avantOeil = this.grille[y][x+force].getTypeC();
                this.grille[y][x+force].setType(Case.Type.OEIL);
                this.Oeil = new Coordonnées(x+force, y);
            } else if (x+(force-1)<=4) {
                this.grille[y][x].setType(this.avantOeil);
                this.avantOeil = this.grille[y][x+(force-1)].getTypeC();
                this.grille[y][x+(force-1)].setType(Case.Type.OEIL);
                this.Oeil = new Coordonnées(x+(force-1), y);
            }else if (x+(force-2)<=4) {
                this.grille[y][x].setType(this.avantOeil);
                this.avantOeil = this.grille[y][x+(force-2)].getTypeC();
                this.grille[y][x + (force - 2)].setType(Case.Type.OEIL);
                this.Oeil = new Coordonnées(x + (force - 2), y);
            }
        } else if (dv==Direction.NORD) {
            for(int i=0; i<force; i++){
                if(x-i>=0) {
                    this.grille[y][x - i].augNivSable();
                }
            }
            if(x-force>=0) {
                this.grille[y][x].setType(this.avantOeil);
                this.avantOeil = this.grille[y][x-force].getTypeC();
                this.grille[y][x-force].setType(Case.Type.OEIL);
                this.Oeil = new Coordonnées(x-force, y);
            } else if (x-(force-1)>=0) {
                this.grille[y][x].setType(this.avantOeil);
                this.avantOeil = this.grille[y][x-(force-1)].getTypeC();
                this.grille[y][x-(force-1)].setType(Case.Type.OEIL);
                this.Oeil = new Coordonnées(x-(force-1), y);
            }else if (x-(force-2)>=0) {
                this.grille[y][x].setType(this.avantOeil);
                this.avantOeil = this.grille[y][x-(force-2)].getTypeC();
                this.grille[y][x - (force - 2)].setType(Case.Type.OEIL);
                this.Oeil = new Coordonnées(x - (force - 2), y);
            }
        }
        this.changeJoueurTour();
    }


    public double getNiveauTempete(){ return this.niveauTempête; }
    public void changeJoueurTour() {
        this.getJoueurTour().resetActions();
        if (this.joueurTour == this.ensJoueurs.size()) {
            this.joueurTour = 1;
        } else {
            ++this.joueurTour;
        }

    }

    public boolean vagueDeChaleur(){
        this.changeJoueurTour();
        for(int i=0; i<this.ensJoueurs.size(); i++){
            this.ensJoueurs.get(i).bois();
            if((this.ensJoueurs.get(i).getNivEau()==0) ){
                return false;
            }
            if((this.ensJoueurs.get(i).getPosition().getTypeC() == Case.Type.TUNNELS)){
                this.ensJoueurs.get(i).getNivEau();
            }
            this.ensJoueurs.get(i).bois();
            if(this.ensJoueurs.get(i).getNivEau()==0){
                return false;
            }
        }
        return true;
    }


    public static enum Direction{
        NORD ("Nord"),
        SUD("Sud"),
        OUEST("Ouest"),
        EST("Est");
        private String direction;
        private Direction(String direction){ this.direction = direction ;}
        public String getDirection(){ return this.direction; }

    }


}
