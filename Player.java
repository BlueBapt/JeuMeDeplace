import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
public class Player extends Terrain{

    public int vie;
    public double mx,my;
    public boolean mg,md;
    public static int H=56;
    public static int L=25;
    
    public Player(int x,int y,int lar,int haut,String nom){
        super(x,y,lar,haut,"abdel.png");
        this.changerImage(nom);
        this.vie=3;
        this.mx=0;
        this.my=0;
        this.md=false;
        this.mg=false;
    }

    public void changerImage(String im){
        try {
            this.nom=im;
            this.image = ImageIO.read(new File("hero/"+im));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}