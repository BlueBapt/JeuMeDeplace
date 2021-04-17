import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.File;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.*;
public class Ecran extends JFrame{

	public static Panneau pan = new Panneau();

	public Ecran(){
		
		this.setTitle("Pog il se deplace");
		this.setVisible(true);
		this.setSize(736, 759);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
		this.addKeyListener(new Inputs());
		if(main.jouer)
			this.go();

	}    
	public void go(){
        Panneau.joueur=new Player(0,0,25,56,"sprite_0.png");
		LectureFichier.ChargerNiveau("1");
        
        int posX;
        int posY;
        while(true){
            //Evolution dans l'espace
            posX = (int)Panneau.joueur.getX();
			posX= (int)(posX+((Player)Panneau.joueur).mx);
            posY = (int)Panneau.joueur.getY();
			posY= (int)(posY+((Player)Panneau.joueur).my);
			if((int)(posY+Panneau.joueur.getHeight())>=720){
				((Player)Panneau.joueur).my=1;
				posY=720-((int)Panneau.joueur.getHeight());
				
			}
            ((Player)Panneau.joueur).my+=0.5;

			if(!((Player)Panneau.joueur).md){
				if(((Player)Panneau.joueur).mx>0){
					((Player)Panneau.joueur).mx-=0.4;
					if(((Player)Panneau.joueur).mx<0){
						((Player)Panneau.joueur).mx=0;
					}
				}
			}
			
			if(!((Player)Panneau.joueur).mg){
				if(((Player)Panneau.joueur).mx<0){
					((Player)Panneau.joueur).mx+=0.4;
					if(((Player)Panneau.joueur).mx>0){
						((Player)Panneau.joueur).mx=0;
					}
				}
			}
			
            Panneau.joueur.setLocation(posX,posY);
			try {
                Thread.sleep(10);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            pan.repaint();
        }
    } 


}
