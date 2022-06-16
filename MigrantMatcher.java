import java.util.Scanner;


public class MigrantMatcher{
    public static void main(String[] agrs){

        Scanner src = new Scanner(System.in);
        UsersHandler activeUser;
        boolean isValidNumber;

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
                    isValidNumber = true;


                    //Loggar user
                    while(isValidNumber){
                        //Identificação por telemovel. Guardar os dados localmente em ficheiros.
                        System.out.println("Por favor identifique-se usando o seu numero de telemovel:");
                        try {
                            int phoneNumber = src.nextInt();
                            activeUser = new UsersHandler(phoneNumber);
                            Volunteer user = activeUser.createVolunteer(activeUser);

                            System.out.println("Bem vindo ID/NumeroTelemovel: " + user.phoneNumber);

                            isValidNumber = false;
                        
                        } catch (NumberFormatException e){
                            System.out.println(e.getMessage());
                            System.out.println("Por favor insira um inteiro");

                        }                        
                    }

                    

                    break;

                case "m":
                    System.out.println("Bem-Vindo Migrante!");
                    isValidNumber = true;


                    //Loggar user
                    while(isValidNumber){
                        //Identificação por telemovel. Guardar os dados localmente em ficheiros.
                        System.out.println("Por favor identifique-se usando o seu numero de nome, telemovel(Formato: 'nome,numero')");
                        String userInfoMeta = src.next();
                        try {
                            
                            String[] userInfo = userInfoMeta.split(",", 2);

                            activeUser = new UsersHandler(Integer.parseInt(userInfo[1]), userInfo[0]);
                            Migrant user = activeUser.createMigrant(activeUser);
                            System.out.println("Bem vindo: " + user.name + "! ID/NumeroTelemovel: " + user.phoneNumber);

                            isValidNumber = false;
                        
                        } catch (NumberFormatException e){
                            System.out.println(e.getMessage());
                            System.out.println("Por favor insira um inteiro");

                        }

                        
                    }

                    break;


                default:


                break;

            }    

        }
    }
}