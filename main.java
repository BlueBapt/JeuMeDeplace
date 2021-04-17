public class main{

    public static Slide sli;
    public static Ecran fen;
    public static boolean jouer=false;
    public static void main(String[]args){
        if(args.length==1){
            if(args[0].equals("-p")){
                main.jouer=true;
            }
        }
        fen = new Ecran();
        sli = new Slide();
    }
}