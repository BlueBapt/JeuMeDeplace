import javax.swing.JFrame;
import javax.imageio.ImageIO;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.File;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Rectangle;
public class Ecran extends JFrame{

	public static Panneau pan = new Panneau();

	public Ecran(){
		
		this.setTitle("Plateformer");
		this.setLocation(0,0);
		try{
			this.setIconImage(ImageIO.read(new File("textures/bases/icone.png")));
		}catch (Exception e){
			System.exit(1);
		}
		
		this.setResizable(false);
		this.setLocation(0,0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan.setPreferredSize(new Dimension(720,720));
		this.setContentPane(pan);
		this.addKeyListener(new Inputs());
		this.pack();
		this.setVisible(true);
		if(main.jouer)
			this.go();

	}    
	public void go(){
        Panneau.joueur=new Player(0,0,25,56,"hd_1.png");
		try{
			LectureFichier.ChargerNiveau("1");
			pan.repaint();
		}catch (Exception e){
			System.err.println(e);
            System.exit(1);
		}
        
        int posX;
        int posY;
		int lx,ly;
        while(true){
            //Evolution dans l'espace
            lx = (int)Panneau.joueur.getX();
			posX= (int)(lx+((Player)Panneau.joueur).mx);
            ly = (int)Panneau.joueur.getY();
			posY= (int)(ly+((Player)Panneau.joueur).my);
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
			for(Terrain t : Panneau.hey.niv){
				if(t.isSolid){
					if(Panneau.joueur.intersects(t)){
						if(lx+Player.L<=t.getX() || lx>=(t.getX()+t.getWidth())){
							posX=lx;
						}else if(ly+Player.H<=t.getY()){
							posY=(int)(t.getY()-Player.H);
							((Player)Panneau.joueur).my=0;
						}else if(ly>=t.getY()+t.getHeight()){
							posY=(int)(t.getHeight()+t.getY());
							((Player)Panneau.joueur).my=0;
						}
					}
				}
			}
			Panneau.joueur.setLocation(posX,posY);
			if(Panneau.joueur.getX()<0){
				lx=0;
				Panneau.joueur.setLocation(0,posY);
			}else if(Panneau.joueur.getX()>720-Player.L){
				lx=0;
				Panneau.joueur.setLocation(720-Player.L,posY);
			}

			pan.repaint();
			try {
                Thread.sleep(10);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    } 


}
