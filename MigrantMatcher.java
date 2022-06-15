import java.util.Scanner;


public class MigrantMatcher{
    public static void main(String[] agrs){

        Scanner src = new Scanner(System.in);
        UsersHandler activeUser;

        Storager sto = new Storager();




        boolean isRunning = true;
        while(isRunning){
            
            //Introdução e selecionar tipo de user
            System.out.println("Bem-Vindo ao MigrantMatcher");
            System.out.print("Vai querer connectar-se como voluntário(v) ou migrante?(m): ");

            
            String g = src.next();
            switch(g){
                
                case "v":

                    System.out.println("Bem-Vindo Voluntário!");
                    boolean isValidNumber = true;
                    while(isValidNumber){
                        //Identificação por telemovel. Guardar os dados localmente em ficheiros.
                        System.out.println("Por favor identifique-se usando o seu numero de telemovel:");
                        try {
                            int phoneNumber = src.nextInt();
                            activeUser = new UsersHandler(phoneNumber);
                            activeUser.createVolunteer(activeUser);
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