import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.RenderingHints;
import javax.swing.JOptionPane;

public class Bouton extends JButton implements MouseListener{
  private String name;
  public static boolean change=false;
  private Font arial= new Font("Carlito",0, 15);

  public Bouton(String str){
    super(str);
    this.name = str;
    this.addMouseListener(this);
    
  }

  public void paintComponent(Graphics g){
    Graphics2D g2d = (Graphics2D)g;
    GradientPaint gp = new GradientPaint(0, 0, Color.white, 0, 20, Color.gray, true);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON); 

   // Set anti-alias for text
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 
    g2d.setFont(arial);
    g2d.setPaint(gp);
    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    g2d.setColor(Color.black);
    g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth()/ 2 /4)-15, (this.getHeight() / 2) + 5);
  }

  //Méthode appelée lors du clic de souris
  public void mouseClicked(MouseEvent event) {
    switch(this.name){
      case "Sauvegarder":
        JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
        String nom = jop.showInputDialog(null, "Sous quel nom sauvegarder?", "Sauvegarder", JOptionPane.QUESTION_MESSAGE);
        Saver save = new Saver();
        save.sauvegarder(Panneau.hey,nom);
        jop2.showMessageDialog(null, "Fichier sauvegarde sous : " + nom, "Fini!", JOptionPane.INFORMATION_MESSAGE);
        break;
      case "Charger":
        JOptionPane jop3 = new JOptionPane(), jop4 = new JOptionPane();
        String nomC = jop3.showInputDialog(null, "Quel niveau charger?", "Charger", JOptionPane.QUESTION_MESSAGE);
        LectureFichier.ChargerNiveau(nomC);
        Ecran.pan.repaint();
        jop4.showMessageDialog(null, "Fichier charge (" + nomC+")", "Fini!", JOptionPane.INFORMATION_MESSAGE);
        break;
      case "Jouer":
        main.jouer=true;
        main.sli.dispose();
        main.fen.go();
      break;
    }
  }

  //Méthode appelée lors du survol de la souris
  public void mouseEntered(MouseEvent event) { }

  //Méthode appelée lorsque la souris sort de la zone du bouton
  public void mouseExited(MouseEvent event) { 
  }

  //Méthode appelée lorsque l'on presse le bouton gauche de la souris
  public void mousePressed(MouseEvent event) { }

  //Méthode appelée lorsque l'on relâche le clic de souris
  public void mouseReleased(MouseEvent event) { 
  }   
}