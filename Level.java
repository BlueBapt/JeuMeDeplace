import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.util.*;
public class Level{
    public Image fond;
    public ArrayList<Terrain> aPlan;
    public ArrayList<Terrain> niv;
    public String nom;

    public Level(String nom){
        this.niv= new ArrayList<Terrain>();
        this.nom=nom;
        this.aPlan=new ArrayList<Terrain>();
    }

    public Level(ArrayList<Terrain> terter,String nom){
        this.niv=new ArrayList<Terrain>(terter);
        this.nom=nom;
        this.aPlan=new ArrayList<Terrain>();
    }
    public Level(ArrayList<Terrain> terter,String nom,String nomfond){
        this.aPlan=new ArrayList<Terrain>();
        this.niv=terter;
        this.nom=nom;
        try {
            this.fond = ImageIO.read(new File(nomfond));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterTerrain(Terrain ter){
        this.niv.add(new Terrain(ter));
    }

    public void ajouterBG(Terrain ter){
        this.aPlan.add(new Terrain(ter));
    }

    public String toString(){
        return (""+this.niv.size());
    }
}