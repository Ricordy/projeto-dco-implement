import java.util.Scanner;


public class MigrantMatcher{
    public static void main(String[] agrs){

        Scanner src = new Scanner(System.in);
        UsersHandler activeUser;

        boolean isRunning = true;
        while(isRunning){
            
            //Introdução e selecionar tipo de user
            System.out.println("Bem-Vindo ao MigrantMatcher");
            System.out.println("Vai queres connectar-se como voluntário(v) ou migrante?(m)");
            
            
            
            //Se user for voluntario
            if(src.nextLine() == "v" || src.nextLine() == "voluntario"){
                
                boolean isValidNumber = true;
                while(isValidNumber){
                    //Identificação por telemovel. Guardar os dados localmente em ficheiros.
                    System.out.println("Por favor identifique-se usando o seu numero de telemovel:");
                    try {
                        int phoneNumber = src.nextInt();
                        activeUser = new UsersHandler(phoneNumber);
                        
                    } catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                        System.out.println("Por favor insira um inteiro");

                    }

                    
                }








            //Se user for migrante
            }else if (src.nextLine() == "m" || src.nextLine() == "migrante"){







            // Se user inseriu algo que não seja correto    
            } else {
                System.out.println("Por favor selecione uma opção válida.");
                isRunning = false;
            }

        }
    }
}