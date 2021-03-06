import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
public class LectureFichier{

    public static void ChargerNiveau(String niveau) throws Exception{
        BufferedReader reader;
        boolean pasFini = true;
        int caractere;
        String chaine ="";
        int place=0;
        String nom="";
        Terrain terInter;
        int posX =0,posY=0,lar=0,hau=0;
        boolean breakable=false,solid=false;
        Level niveauCharge = new Level(niveau);
        //chargement de l'image de fond
        try {
            niveauCharge.fond = ImageIO.read(new File("levels/"+niveau+"/fond.png"));
        } catch (IOException e) {
            try{
                niveauCharge.fond = ImageIO.read(new File("textures/bases/fond.png"));
            }catch(IOException ioe){
                System.out.println("fond non trouve");
            }
            
        }

        //chargement du niveau
        try {
            reader = new BufferedReader(new FileReader("levels/"+niveau+"/niveau.txt"));
            while (pasFini) {
                caractere = reader.read();
                if (caractere != -1) { // ce n'est pas encore la fin du fichier
                    if(caractere==10){
                        place=0;
                        breakable=Boolean.parseBoolean(chaine);
                        chaine="";
                        terInter = new Terrain(posX,posY,lar,hau,nom);
                        terInter.setBreakable(breakable);
                        terInter.setSolid(solid);
                        niveauCharge.ajouterTerrain(terInter);
                    }
                    if(caractere==44){
                        switch(place){
                            case 0:
                                nom=chaine;
                                place++;
                                break;
                            case 1:
                                posX=Integer.parseInt(chaine);
                                place++;
                                break;
                            case 2:
                                posY=Integer.parseInt(chaine);
                                place++;
                                break;
                            case 3:
                                lar=Integer.parseInt(chaine);
                                place++;
                                break;
                            case 4:
                                hau=Integer.parseInt(chaine);
                                place++;
                                break;
                            case 5:
                                solid=Boolean.parseBoolean(chaine);
                                place++;
                                break;
                        }
                        chaine="";
                    }else{
                        if((char)caractere!='\n' && (char)caractere!='\r'){
                            chaine = chaine+((char)caractere);
                        }
                    }
                        

                } else {
                    pasFini = false;
                }
            }
            reader.close();
        } catch (IOException ioe) {
            throw ioe;
        }
        
        chaine="";
        caractere=1;
        place=0;
        posX =0;
        posY=0;
        lar=0;
        hau=0;
        nom="";
        pasFini=true;

        //chargement de l'arriere plan
        try {
            reader = new BufferedReader(new FileReader("levels/"+niveau+"/arrierePlan.txt"));
            while (pasFini) {
                caractere = reader.read();
                if (caractere != -1) { // ce n'est pas encore la fin du fichier
                    if(caractere==10){
                        place=0;
                        hau=Integer.parseInt(chaine);
                        chaine="";
                        niveauCharge.ajouterBG(new Terrain(posX,posY,lar,hau,nom));
                        
                    }
                    if(caractere==44){
                        switch(place){
                            case 0:
                                nom=chaine;
                                place++;
                                break;
                            case 1:
                                posX=Integer.parseInt(chaine);
                                place++;
                                break;
                            case 2:
                                posY=Integer.parseInt(chaine);
                                place++;
                                break;
                            case 3:
                                lar=Integer.parseInt(chaine);
                                place++;
                                break;
                        }
                        chaine="";
                    }else{
                        if((char)caractere!='\n' && (char)caractere!='\r'){
                            chaine = chaine+((char)caractere);
                        }
                    }
                        

                } else {
                    pasFini = false;
                }
            }
            reader.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }

        Panneau.hey=niveauCharge;

    }
    

    public static String[] listerDoss(String doss){
        try{
            File repertoire = new File(doss);
            String liste[] = repertoire.list();
            ArrayList<String> l=new ArrayList<String>();
            for(String s : liste){
                if(s.substring(s.length()-4).equals(".png")){
                    l.add(s);
                }
            }
            String[] res = new String[l.size()];
            for(int i=0;i<res.length;i++){
                res[i] = l.get(i);
            }

            return res;
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return null;
        
    }


    
}