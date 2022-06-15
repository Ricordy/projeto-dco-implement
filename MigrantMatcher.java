import java.util.Scanner;


public class MigrantMatcher{
    public static void main(String[] agrs){

        Scanner src = new Scanner(System.in);
        UsersHandler activeUser;

        Storager sto = new Storager();
        sto.createCsv("/Users/rodrigobarrocas/Desktop/dco-projeto/projeto-dco-implement/oi.csv");




        boolean isRunning = true;
        while(isRunning){
            
            //Introdução e selecionar tipo de user
            System.out.println("Bem-Vindo ao MigrantMatcher");
            System.out.println("Vai queres connectar-se como voluntário(v) ou migrante?(m)");
            sto.createCsv("oi2.csv");
            
            String g = src.next();
            System.out.println(g);
            switch(g){
                
                case "v":

                    System.out.println("oi");
                    boolean isValidNumber = true;
                    while(isValidNumber){
                        //Identificação por telemovel. Guardar os dados localmente em ficheiros.
                        System.out.println("Por favor identifique-se usando o seu numero de telemovel:");
                        try {
                            int phoneNumber = src.nextInt();
                            activeUser = new UsersHandler(phoneNumber);
                            isValidNumber = false;
                        
                        } catch (NumberFormatException e){
                            System.out.println(e.getMessage());
                            System.out.println("Por favor insira um inteiro");

                        }

                        
                    }

                case "m":


                default:

            }    

        }
    }
}