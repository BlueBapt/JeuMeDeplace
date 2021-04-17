import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Rectangle;

public class Panneau extends JPanel{

  private Font comic= new Font("Carlito",0, 20);
  public static Terrain joueur = new Terrain(0,0,30,30,"abdel.png");
  public static Terrain[] terrain = new Terrain[0];
  public static Level hey = new Level(terrain,"test");
  
  public void paintComponent(Graphics g){
    super.paintComponent(g); 
    Graphics2D g2D = (Graphics2D)g;

    //Set  anti-alias!
    g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON); 

   // Set anti-alias for text
    g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 

    if(hey.fond!=null){
      g.drawImage(hey.fond,0,0,720,720,this);
    }
    g.setColor(Color.black);
    for(Terrain t : hey.niv){
      g.drawImage(t.image,t.x,t.y,t.width,t.height,this);
      if(!main.jouer){
        g.drawRect(t.x,t.y,t.width,t.height);
      }
    }

    g.setColor(Color.red);
    g.drawImage(joueur.image,joueur.x,joueur.y,joueur.width,joueur.height,this);
    if(!main.jouer){
      g.drawRect(joueur.x,joueur.y,joueur.width,joueur.height);
    }

  }
}