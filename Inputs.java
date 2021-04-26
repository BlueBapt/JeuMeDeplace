import java.awt.event.*;
import java.util.Scanner;
import java.awt.Rectangle;

public class Inputs extends KeyAdapter{

    public void keyPressed(KeyEvent ke){
        
        if(!main.jouer){
            int plusX=Panneau.joueur.x;
            int plusY=Panneau.joueur.y;
            int plusLarX=0;
            int plusLarY=0;
            //System.out.println(ke.getKeyCode());
            switch(ke.getKeyCode()){
                case 81://q
                    if(Panneau.joueur.getX()>0){
                        plusX+=-30;
                    }
                    break;
                case 90://z
                    if(Panneau.joueur.getY()>0){
                        plusY+=-30;
                    }
                    break;
                case 68://d
                    if(Panneau.joueur.getX()<=670){
                        plusX+=30;
                    }
                    break;
                case 83://s
                    if(Panneau.joueur.getY()<=670){
                        plusY+=30;
                    }
                    break;
                

                case 39://fleche droite
                    plusLarX=15;
                    break;
                case 37://fleche gauche
                    if(Panneau.joueur.getWidth()!=30){
                        plusLarX=-15;
                    }
                    break;

                case 40://fleche bas
                    plusLarY=15;
                    break;

                case 38://fleche haut
                    if(Panneau.joueur.getHeight()!=30){
                        plusLarY=-15;
                    }
                    break;

                case 10://entree
                    Ecran.pan.hey.ajouterTerrain(Panneau.joueur);
                    break;

                default:
                    //System.out.println("yo mec "+ke.getKeyChar());
                    break;
            }
            Panneau.joueur.setLocation(plusX,plusY);
            Panneau.joueur.grow(plusLarX,plusLarY);
            if(plusLarX!=0 || plusLarY!=0){
                Panneau.joueur.setLocation(plusX,plusY);
            }
            Ecran.pan.repaint(); 

        }else{// -------------------------PARTIE JOUER
            
            switch(ke.getKeyCode()){
                case 81://q
                    if(Panneau.joueur.getX()>0){
                        ((Player)Panneau.joueur).mx=-3;
                        ((Player)Panneau.joueur).mg=true;
                        if(!Panneau.joueur.image.equals("hg_1.png"))
                            Panneau.joueur.changerImage("hg_1.png");
                    }
                    break;
                case 90://z
                    if(Panneau.joueur.getY()>0){
                        ((Player)Panneau.joueur).my=-10;
                    }
                    break;
                case 68://d
                    if(Panneau.joueur.getX()<=670){
                        ((Player)Panneau.joueur).mx=3;
                        ((Player)Panneau.joueur).md=true;
                        if(!Panneau.joueur.image.equals("hd_1.png"))
                            Panneau.joueur.changerImage("hd_1.png");
                    }
                    break;
                case 83://s
                    if(Panneau.joueur.getY()<=670){
                    }
                    break;

                default:
                    //System.out.println("yo mec "+ke.getKeyChar());
                    break;
            }
            Ecran.pan.repaint(); 
        }
        
    }

    public void keyReleased(KeyEvent ke){
        if(main.jouer){
            switch(ke.getKeyCode()){
                    case 81://q
                        if(Panneau.joueur.getX()>0){
                        ((Player)Panneau.joueur).mg=false;
                        }
                        break;
                    case 90://z
                        break;
                    case 68://d
                        if(Panneau.joueur.getX()<=670){
                            ((Player)Panneau.joueur).md=false;
                        }
                        break;
                    case 83://s
                        if(Panneau.joueur.getY()<=670){
                        }
                        break;

                    default:
                        //System.out.println("yo mec "+ke.getKeyChar());
                        break;
                }
                Ecran.pan.repaint(); 
        }
    }
}