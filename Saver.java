import java.io.*;
import java.util.*;

public class Saver{

    public Saver(){};

    public void sauvegarder(Level lev,String nivedit){

        File file = new File("./levels/"+nivedit);
        if (!file.exists()){
            file.mkdir();
        }
        try{
            FileWriter fw = new FileWriter("./levels/"+nivedit+"/niveau.txt");
            String ligne ="";
            for(Terrain t : lev.niv){
                //nom,x,y,largeur,hauteur,solidit√©,cassabilit√©
                ligne=t.nom+","+((int)t.getX())+","+((int)t.getY())+","+((int)t.getWidth())+","+((int)t.getHeight())+","+t.isSolid+","+t.isBreakable+"\n";
                fw.write(ligne);
            }
            fw.close();

            fw = new FileWriter("./levels/"+nivedit+"/arrierePlan.txt");
            ligne ="";
            for(Terrain t : lev.aPlan){
                ligne=t.nom+","+((int)t.getX())+","+((int)t.getY())+","+((int)t.getWidth())+","+((int)t.getHeight())+"\n";
                fw.write(ligne);
            }
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}