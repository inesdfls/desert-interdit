package Modèles;

import java.util.ArrayList;

public class Joueurs {
    private int niveauEau;
    private String nomJ;
    private Case position;
    private int actions;
    private int num;
    private ArrayList<String> pieces;

    public Joueurs(String n) {
        this.actions = 4;
        this.nomJ = n;
        this.niveauEau = 5;
        this.num=0;
        this.pieces= new ArrayList();
    }
    
    public void decrActions(){
        this.actions--;
    }
    public void incrNivEau(){this.niveauEau++;}
    public void resetActions() {
        this.actions = 4;
    }
    public int getActions() {
        return this.actions;
    }
    public String getNom() {
        return this.nomJ;
    }
    public int getNivEau() {
        return this.niveauEau;
    }
    public void setPosition(Case c) {
        this.position = c;
    }
    public Case getPosition() {
        return this.position;
    }
    public void setNum(int i){
        this.num=i;
    }
    public int getNum(){
        return this.num;
    }
     public void addPiece(String s, Desert d ){
        this.pieces.add(s);
        d.addPieceRecup(s);

     }
     public ArrayList<String> getPiece(){
        return this.pieces;
     }

    public String toString() {
        return ("Joueur : (niveau d'eau= " + this.niveauEau
                + ", nom du joueur= " + this.nomJ
                + ", actions= " + this.actions + ")");
    }


    //peut-être à modifier
    public boolean depJoueur(Direction dir, Desert d){
         if(dir == Direction.GAUCHE && this.position.getCoord().getLig()==0){
             return false;
        }else if(dir == Direction.DROITE && this.position.getCoord().getLig()==4){
             return false;
        }else if(dir == Direction.HAUT && this.position.getCoord().getCol()==0){
             return false;
        }else if(dir == Direction.BAS && this.position.getCoord().getCol()==4){
             return false;
        }else{
            int x = this.position.getCoord().getCol();
            int y = this.position.getCoord().getLig();
            if(dir == Direction.HAUT) {
                if( d.getCase(this.position.getCoord().getCol() - 1,this.position.getCoord().getLig()).getNiveauSable()>=2
                        || d.getCase(this.position.getCoord().getCol()-1,this.position.getCoord().getLig()).getTypeC()== Case.Type.OEIL){
                    return false;
                }
                d.getCase(x, y).removeJoueursC(this,d);
                d.getCase(x - 1,y).addJoueursC(this,d);
                this.setPosition(d.getCase(x - 1,y));
            }else if(dir == Direction.BAS) {
                if(d.getCase(this.position.getCoord().getCol() + 1,this.position.getCoord().getLig()).getNiveauSable()>=2
                        || d.getCase(this.position.getCoord().getCol() + 1,this.position.getCoord().getLig()).getTypeC()== Case.Type.OEIL) {
                    return false;
                }
                d.getCase(x, y).removeJoueursC(this, d);
                d.getCase(x + 1, y).addJoueursC(this, d);
                this.setPosition(d.getCase(x + 1, y));
            }else if(dir == Direction.GAUCHE) {
                if(d.getCase(this.position.getCoord().getCol(),this.position.getCoord().getLig()-1).getNiveauSable()>=2
                        ||d.getCase(this.position.getCoord().getCol(),this.position.getCoord().getLig()-1).getTypeC()== Case.Type.OEIL) {
                    return false;
                }
                d.getCase(x, y).removeJoueursC(this,d);
                d.getCase(x,y-1).addJoueursC(this,d);
                this.setPosition(d.getCase(x,y-1));
            }else if(dir == Direction.DROITE) {
                if(d.getCase(this.position.getCoord().getCol(),this.position.getCoord().getLig()+1).getNiveauSable()>=2
                        || d.getCase(this.position.getCoord().getCol(),this.position.getCoord().getLig()+1).getTypeC()== Case.Type.OEIL){
                    return false;
                }
                d.getCase(x, y).removeJoueursC(this,d);
                d.getCase(x,y+1).addJoueursC(this,d);
                this.setPosition(d.getCase(x,y+1));
            }
            --this.actions;
            //d.changeJoueurTour();
            return true;
        }
    }

    public void bois(){
        --this.niveauEau;
    }

    public void creuser(){
        if(this.position.getNiveauSable() > 0 && this.actions > 0 ){
            this.position.decNiveauSable();
            this.decrActions();
        }
        this.getActions();
    }

    public static enum Direction{
        HAUT ("haut"),
        BAS ("bas"),
        DROITE ("droite"),
        GAUCHE ("gauche");

        private String name;

        private Direction(String name) {
            this.name = name;
        }
    }
}
