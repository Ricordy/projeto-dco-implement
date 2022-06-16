import java.util.Random;
import java.util.Scanner;
;


public class MigrantMatcher {
    public static void main(String[] agrs) {

        Scanner src = new Scanner(System.in);
        Random rand;
        
    
        UsersHandler activeUser = new UsersHandler(0);
        Storager sto = new Storager();
        HelpHandler help;
        
        
        boolean isRunning = true;
        boolean isValidNumber;
        int ticketNumber;
        long helpTimeAndDate;




    
        while (isRunning) {

            // Introdução e selecionar tipo de user
            System.out.println("Bem-Vindo ao MigrantMatcher");
            System.out.print("Vai querer connectar-se como voluntário(v) ou migrante?(m): ");

            String g = src.next();
            switch (g) {

                case "v":

                    System.out.println("Bem-Vindo Voluntário!");
                    isValidNumber = true;

                    // Loggar user
                    while (isValidNumber) {
                        // Identificação por telemovel. Guardar os dados localmente em ficheiros.
                        System.out.println("Por favor identifique-se usando o seu numero de telemovel:");
                        try {
                            int phoneNumber = src.nextInt();
                            activeUser = new UsersHandler(phoneNumber);
                            activeUser.createVolunteer();


                            String path = "data/usersData/"+activeUser.phoneNumber+".csv";
                

                            
                            sto = new Storager("v", String.valueOf(activeUser.phoneNumber), String.valueOf(activeUser.phoneNumber));
                            sto.writeToFileUserInfo(path);

                            System.out.println("Bem vindo ID/NumeroTelemovel: " + activeUser.phoneNumber + "!");

                             /*
                        * Menu de Voluntário!
                        */
                        System.out.println("    ->(1) Registar Ajuda.");
                        System.out.println("    ->(2) Histórico de Ajudas.");
                        System.out.println("    ->(3) Sair da aplicação.");
                        switch (src.nextInt()) {

                        /*
                         * Registar ajuda Utilizador deve indicar o tipo de ajuda e efetuar
                         */
                        case 1:
                            System.out.println("Selecione o tipo de ajuda:");
                            System.out.println("    ->(1) Fornecer casa.");
                            System.out.println("    ->(2) Oferecer item.");
                            System.out.println("    ->Qualquer outro input para voltar");
                            switch(src.nextInt()){

                                case 1:
                                    
                                    System.out.println("Por-favor indique a região da casa.");
                                    String regiao = src.next();
                                    System.out.println("Por-favor indique a lotação da casa.");
                                    int lotacao = src.nextInt();
                                   
                                   
                                    path = "data/helpData/";

                                    help = new HelpHandler("i", lotacao, regiao);
                                    help.creaAlojamento();
                                    helpTimeAndDate = System.currentTimeMillis();
                                    
                                    //Generate random name for file
                                    rand = new Random();
                                    ticketNumber = rand.nextInt(999999);


                                    path += ticketNumber+".csv";
                                    sto.writeToFileUserHelp(path, help.type, help.regionItem, String.valueOf(help.quantity), String.valueOf(helpTimeAndDate));
                                    sto.writeToUserFileListOfHelp("data/usersData/"+activeUser.phoneNumber+".csv", String.valueOf(ticketNumber), help.type);
                                    

                                    




                                    break;

                                case 2:
                                
                                    System.out.println("Por-favor indique o item(numa palavra ou nomenclaturaDeVariaveis)");
                                    String type = src.next();
                                    System.out.println("Por-favor indique a quantidade do item.");
                                    int quantidade = src.nextInt();

                                    path = "data/helpData/";

                                    help = new HelpHandler("i", quantidade, type);
                                    help.createItem();
                                    helpTimeAndDate = System.currentTimeMillis();
                                    
                                    //Generate random name for file
                                    rand = new Random();
                                    ticketNumber = rand.nextInt(999999);


                                    path += ticketNumber+".csv";
                                    sto.writeToFileUserHelp(path, help.type, help.regionItem, String.valueOf(help.quantity), String.valueOf(helpTimeAndDate));
                                    sto.writeToUserFileListOfHelp("data/usersData/"+activeUser.phoneNumber+".csv", String.valueOf(ticketNumber), help.type);
                                

                                    


                                    break;

                            }

                            break;

                            case 2: 

                                break;


                            case 3:
                                System.out.println("Obrigado pela utilização!");
                                isValidNumber = false;
                                break;
                        }

                            
                            
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Por favor insira um inteiro");

                        }
                        
                    }

                   


                    break;

                case "m":
                    System.out.println("Bem-Vindo Migrante!");
                    isValidNumber = true;

                    // Loggar user
                    while (isValidNumber) {
                        // Identificação por telemovel. Guardar os dados localmente em ficheiros.
                        System.out.println(
                                "Por favor identifique-se usando o seu numero de nome, telemovel(Formato: 'nome,numero')");
                        String userInfoMeta = src.next();
                        try {

                            String[] userInfo = userInfoMeta.split(",", 2);

                            activeUser = new UsersHandler(Integer.parseInt(userInfo[1]), userInfo[0]);
                            activeUser.createMigrant();
                            System.out.println("Bem vindo: " + activeUser.name + "! ID/NumeroTelemovel: " + activeUser.phoneNumber);

                            isValidNumber = false;

                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Por favor insira um inteiro");

                        }

                    }

                    break;

                default:

                    isRunning = false;
                    src.close();

                    break;

            }

        }
    }
} 