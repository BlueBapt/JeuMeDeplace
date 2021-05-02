import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
public class Level{
    public Image fond;
    public Terrain[] aPlan;
    public Terrain[] niv;
    public String nom;

    public Level(String nom){
        this.niv= new Terrain[0];
        this.nom=nom;
        this.aPlan=new Terrain[0];
    }

    public Level(Terrain[] terter,String nom){
        this.niv=terter;
        this.nom=nom;
        this.aPlan=new Terrain[0];
    }
    public Level(Terrain[] terter,String nom,String nomfond){
        this.aPlan=new Terrain[0];
        this.niv=terter;
        this.nom=nom;
        try {
            this.fond = ImageIO.read(new File(nomfond));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterTerrain(Terrain ter){
        Terrain[] nouveau = new Terrain[this.niv.length+1];
        for(int i=0;i<this.niv.length;i++){
            nouveau[i]=this.niv[i];
        }
        this.niv=nouveau;
        this.niv[this.niv.length-1]=new Terrain(ter);
    }

    public void ajouterBG(Terrain ter){
        Terrain[] nouveau = new Terrain[this.aPlan.length+1];
        for(int i=0;i<this.aPlan.length;i++){
            nouveau[i]=this.aPlan[i];
        }
        this.aPlan=nouveau;
        this.aPlan[this.aPlan.length-1]=new Terrain(ter);
    }

    public String toString(){
        return (""+this.niv.length);
    }
}