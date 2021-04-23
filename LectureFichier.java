import java.io.*;
import java.util.Scanner;
import javax.imageio.ImageIO;
public class LectureFichier{

    public static void ChargerNiveau(String niveau){
        BufferedReader reader;
        boolean pasFini = true;
        int caractere;
        String chaine ="";
        int place=0;
        String nom="";
        int posX =0,posY=0,lar=0,hau=0;
        Level niveauCharge = new Level(niveau);
        try {
            niveauCharge.fond = ImageIO.read(new File("levels/"+niveau+"/fond.png"));
        } catch (IOException e) {
            System.out.println("pas de fond a ce niveau");
        }

        try {
            reader = new BufferedReader(new FileReader("levels/"+niveau+"/niveau.txt"));
            while (pasFini) {
                caractere = reader.read();
                if (caractere != -1) { // ce n'est pas encore la fin du fichier
                    if(caractere==10){
                        place=0;
                        hau=Integer.parseInt(chaine);
                        chaine="";
                        niveauCharge.ajouterTerrain(new Terrain(posX,posY,lar,hau,nom));
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
        chaine="";
        caractere=1;
        pasFini=true;
        Panneau.hey=niveauCharge;

    }
    

    public static String[] listerDoss(String doss){
        try{
            File repertoire = new File(doss);
            String liste[] = repertoire.list();      
            return liste;
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return null;
        
    }

    
}