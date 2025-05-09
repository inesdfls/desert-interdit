package Modèles;

public class Coordonnées {
    private int col, lig;

    public Coordonnées(int x, int y){
        this.col = x;
        this.lig = y;
    }

    public int getLig(){ return this.lig; }
   public int getCol(){ return this.col; }
    public String toString() {
        return ("(x=" + this.col + ", y=" + this.lig + ")");
    }
}
