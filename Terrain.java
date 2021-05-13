import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.awt.Rectangle;
public class Terrain extends Rectangle{
    public Image image;
    public String nom;
    public boolean isBreakable;

    public Terrain(int x,int y,int lon,int hau,String im){
        super(x,y,lon,hau);
        this.nom=im;
        try {
            this.image = ImageIO.read(new File("textures/"+im));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Terrain(Rectangle r,String im){
        super(r);
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
    }
    
    public void changerImage(String im){
        try {
            this.nom=im;
            if(Slide.selection.equals("b")){
                this.image = ImageIO.read(new File("textures/bases/objets/"+im));
            }else{
                this.image = ImageIO.read(new File("textures/"+im));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}