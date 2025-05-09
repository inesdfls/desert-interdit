package Contrôleurs;

import Modèles.Case;
import Modèles.Desert;
import Modèles.Joueurs;
import Vues.VueDesert;
import Vues.VueJoueur;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BougerJoueur extends JPanel implements KeyListener {
    private Desert desert;
    private VueDesert vd;
    private VueJoueur vjo;


    public BougerJoueur(Desert desert, VueDesert vd, VueJoueur vjo) {
        this.desert = desert;
        this.vd = vd;
        this.vjo = vjo;
    }

    public void keyPressed(KeyEvent e) {
        Joueurs joueur = desert.getJoueurTour();
        if (joueur.getActions() > 0) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    joueur.depJoueur(Joueurs.Direction.HAUT, desert);
                    this.vd.update();
                    this.vjo.update();
                    break;
                case KeyEvent.VK_DOWN:
                    joueur.depJoueur(Joueurs.Direction.BAS, desert);
                    this.vd.update();
                    this.vjo.update();
                    break;
                case KeyEvent.VK_RIGHT:
                    joueur.depJoueur(Joueurs.Direction.DROITE, desert);
                    this.vd.update();
                    this.vjo.update();
                    break;
                case KeyEvent.VK_LEFT:
                    joueur.depJoueur(Joueurs.Direction.GAUCHE, desert);
                    this.vd.update();
                    this.vjo.update();
                    break;
                case KeyEvent.VK_C:
                    joueur.creuser();
                    this.vd.update();
                    break;
                case KeyEvent.VK_E :
                    joueur.decrActions();
                    if(joueur.getPosition().getTypeC()== Case.Type.OASIS && joueur.getPosition().getExplorer()==false){
                        joueur.getPosition().Explorer();
                        for(int i =0; i<joueur.getPosition().getJoueurs().size(); i++){
                            joueur.getPosition().getJoueurs().get(i).incrNivEau();
                            joueur.getPosition().getJoueurs().get(i).incrNivEau();
                        }
                    }else if(joueur.getPosition().getTypeC()== Case.Type.MIRAGE && joueur.getPosition().getExplorer()==false){
                        joueur.getPosition().Explorer();
                        this.vd.update();
                        this.vjo.update();
                    }else if(joueur.getPosition().getTypeC()== Case.Type.TUNNELS && joueur.getPosition().getExplorer()==false) {
                        joueur.getPosition().Explorer();
                        this.vd.update();
                        this.vjo.update();
                    }else if(joueur.getPosition().getTypeC()== Case.Type.GOUVERNAIL && joueur.getPosition().getExplorer()==false){
                        joueur.getPosition().Explorer();
                        joueur.addPiece("Gouvernail", this.desert);
                        this.vd.update();
                        this.vjo.update();
                    }else if(joueur.getPosition().getTypeC()== Case.Type.ENERGIE && joueur.getPosition().getExplorer()==false){
                        joueur.getPosition().Explorer();
                        joueur.addPiece("Energie", this.desert);
                        this.vd.update();
                        this.vjo.update();
                    }else if(joueur.getPosition().getTypeC()== Case.Type.MOTEUR && joueur.getPosition().getExplorer()==false){
                        joueur.getPosition().Explorer();
                        joueur.addPiece("Moteur", this.desert);
                        this.vd.update();
                        this.vjo.update();
                    }else if(joueur.getPosition().getTypeC()== Case.Type.HELICE && joueur.getPosition().getExplorer()==false){
                        joueur.getPosition().Explorer();
                        joueur.addPiece("Helice", this.desert);
                        this.vd.update();
                        this.vjo.update();
                    }
                    break;
                case KeyEvent.VK_T:
                    joueur.decrActions();
                    if(joueur.getPosition().getTypeC() == Case.Type.TUNNELS && joueur.getPosition().getExplorer()==true) {
                        if(joueur.getPosition() == desert.getCase(3,3)){
                            joueur.setPosition(desert.getCase(0, 4));
                            desert.getCase(3,3).removeJoueursC(joueur,desert);
                            desert.getCase(0,4).addJoueursC(joueur,desert);
                            this.vd.update();
                            this.vjo.update();
                        }else if(joueur.getPosition() == desert.getCase(0,4)){
                            joueur.setPosition(desert.getCase(3, 3));
                            desert.getCase(0,4).removeJoueursC(joueur,desert);
                            desert.getCase(3,3).addJoueursC(joueur,desert);
                            this.vd.update();
                            this.vjo.update();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

}

