import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.awt.Rectangle;
public class Terrain extends Rectangle{
    public Image image;
    public String nom;
    public boolean isBreakable;
    public boolean isSolid;

    public Terrain(int x,int y,int lon,int hau,String im){
        super(x,y,lon,hau);
        this.nom=im;
        try {
            this.image = ImageIO.read(new File("textures/"+im));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Terrain(Terrain ter){
        super((Rectangle)ter);
        this.nom=ter.nom;
        this.image=ter.image;
        this.isBreakable=ter.isBreakable;
        this.isSolid=ter.isSolid;
    }
    
    public void changerImage(String im){
        try {
            this.nom=im;
            this.image = ImageIO.read(new File("textures/"+im));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBreakable(boolean t){
        this.isBreakable=t;
    }

    public void setSolid(boolean t){
        this.isSolid=t;
    }


}