
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;;

public class MigrantMatcher {
    public static void main(String[] agrs) {

        Scanner src = new Scanner(System.in);
        Random rand;
        SimpleDateFormat simpleFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date date;

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
                            boolean isSameUser = true;
                            int phoneNumber = src.nextInt();
                            activeUser = new UsersHandler(phoneNumber);
                            activeUser.createVolunteer();

                            String path = "data/usersData/" + activeUser.phoneNumber + ".csv";

                            sto = new Storager("v", String.valueOf(activeUser.phoneNumber),
                                    String.valueOf(activeUser.phoneNumber));

                            // Verificar se utilizador já existe senão criasse um ficheiro para este
                            if (sto.fileExists("data/usersData/",
                                    String.valueOf(activeUser.phoneNumber) + ".csv") == false) {
                                sto.writeToFileUserInfo(path);
                            }

                            System.out.println("Bem vindo ID/NumeroTelemovel: " + activeUser.phoneNumber + "!");

                            while (isSameUser) {
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
                                        switch (src.nextInt()) {

                                            // User quer forncer casa
                                            case 1:

                                                System.out.println("Por-favor indique a região da casa.");
                                                String regiao = src.next();
                                                System.out.println("Por-favor indique a lotação da casa.");
                                                int lotacao = src.nextInt();

                                                path = "data/helpData/";

                                                help = new HelpHandler("c", lotacao, regiao);
                                                help.creaAlojamento();
                                                helpTimeAndDate = System.currentTimeMillis();

                                                // Generate random name for file
                                                rand = new Random();
                                                ticketNumber = rand.nextInt(999999 - 111111) + 111111;

                                                path += ticketNumber + ".csv";
                                                sto.writeToFileUserHelp(path, help.type, help.regionItem,
                                                        String.valueOf(help.quantity), String.valueOf(helpTimeAndDate));
                                                sto.writeToUserFileListOfHelp(
                                                        "data/usersData/" + activeUser.phoneNumber + ".csv",
                                                        String.valueOf(ticketNumber), help.type, "false");

                                                break;

                                            // User quer forncer item
                                            case 2:

                                                System.out.println(
                                                        "Por-favor indique o item(numa palavra ou nomenclaturaDeVariaveis)");
                                                String type = src.next();
                                                System.out.println("Por-favor indique a quantidade do item.");
                                                int quantidade = src.nextInt();

                                                path = "data/helpData/";

                                                help = new HelpHandler("i", quantidade, type);
                                                help.createItem();
                                                helpTimeAndDate = System.currentTimeMillis();

                                                // Generate random name for file
                                                rand = new Random();
                                                ticketNumber = rand.nextInt(999999 - 111111) + 111111;

                                                path += ticketNumber + ".csv";
                                                sto.writeToFileUserHelp(path, help.type, help.regionItem,
                                                        String.valueOf(help.quantity), String.valueOf(helpTimeAndDate));
                                                sto.writeToUserFileListOfHelp(
                                                        "data/usersData/" + activeUser.phoneNumber + ".csv",
                                                        String.valueOf(ticketNumber), help.type, "false");

                                                break;

                                            default:

                                                break;

                                        }

                                        break;

                                    /*
                                     * Verificar histórico de ajudas
                                     */
                                    case 2:
                                        boolean isOnHistorico = true;
                                        while (isOnHistorico) {

                                            System.out.println("Histórico de ajudas!");
                                            System.out.println("Selecione que histórico deseja ver:");
                                            System.out.println(
                                                    "    ->(1) Todas as ajudas (sem detalhe, ordenadas por data).");
                                            System.out.println(
                                                    "    ->(2-x) Todas as ajudas de cada tipo! Dominio(x) = {i,c}.");
                                            System.out.println(
                                                    "    ->(3-x) Detalhes sobre ajuda especifica! Dominio(x) = {idTicket}");
                                            System.out.println("    ->(4) Voltar");

                                            // Todo o conteudo do ficheiro correspondente ao user
                                            List<String[]> content = sto
                                                    .readFile("data/usersData/" + activeUser.phoneNumber + ".csv");

                                            String input = src.next();
                                            switch (input.charAt(0)) {

                                                /*
                                                 * Histótico geral do utilizador
                                                 *
                                                 */

                                                case '1':

                                                    content.forEach((line) -> {
                                                        if (line[0].equals("v")) {
                                                            System.out.println("Histórico do utilizador: " + line[3]);
                                                            System.out.println("*************************************");
                                                            System.out.println("*                                   *");

                                                        } else {
                                                            System.out.println("* Tipo de ajuda: " + line[1] + ". ID: "
                                                                    + line[0] + ".     *");
                                                        }

                                                    });
                                                    System.out.println("*                                   *");
                                                    System.out.println("*************************************");

                                                    break;

                                                /*
                                                 * Histótico epecifico do utilizador
                                                 *
                                                 */

                                                case '2':

                                                    /*
                                                     * Histótico especifico {i} do utilizador
                                                     *
                                                     */
                                                    if (input.charAt(2) == 'i') {
                                                        content.forEach((line) -> {
                                                            if (line[0].equals("v")) {
                                                                System.out.println("Histórico do utilizador: " + line[3]
                                                                        + "! Especifico para Itens.");
                                                                System.out.println(
                                                                        "*************************************");
                                                                System.out.println(
                                                                        "*                                   *");

                                                            } else if (line[1].equals("i")) {
                                                                System.out.println("* Tipo de ajuda: " + line[1]
                                                                        + ". ID: " + line[0] + ".     *");
                                                            }

                                                        });
                                                        System.out.println("*                                   *");
                                                        System.out.println("*************************************");

                                                        /*
                                                         * Histótico especifico {c} do utilizador
                                                         *
                                                         */
                                                    } else if (input.charAt(2) == 'c') {
                                                        content.forEach((line) -> {
                                                            if (line[0].equals("v")) {
                                                                System.out.println("Histórico do utilizador: " + line[3]
                                                                        + "! Especifico para Alojamentos.");
                                                                System.out.println(
                                                                        "*************************************");
                                                                System.out.println(
                                                                        "*                                   *");

                                                            } else if (line[1].equals("c")) {
                                                                System.out.println("* Tipo de ajuda: " + line[1]
                                                                        + ". ID: " + line[0] + ".     *");
                                                            }

                                                        });
                                                        System.out.println("*                                   *");
                                                        System.out.println("*************************************");

                                                    }

                                                    break;

                                                /*
                                                 * Informações especificas sobre ticket de ID especifico
                                                 *
                                                 */

                                                case '3':
                                                    String[] inputDivided = input.split("-");

                                                    List<String[]> contentFromID = sto
                                                            .readFile("data/helpData/" + inputDivided[1] + ".csv");
                                                    date = new Date(Long.parseLong(contentFromID.get(0)[3]));
                                                    System.out.println("Informações sobre ticket: " + inputDivided[1]);
                                                    System.out.println("*******************************");
                                                    System.out.println("*                             *");

                                                    /*
                                                     * Informação especifica sobre ajudas de Alojamentos
                                                     *
                                                     */
                                                    if (contentFromID.get(0)[0].equals("c")) {
                                                        System.out.println("* Tipo: Alojamento.           *");
                                                        System.out.println("* Localizado em: " + contentFromID.get(0)[1]
                                                                + ".      *");
                                                        System.out.println("* Lotação máxima: "
                                                                + contentFromID.get(0)[2] + ".          *");
                                                        System.out.println(
                                                                "* Data: " + simpleFormat.format(date) + ".   *");
                                                    }
                                                    /*
                                                     * Informação especifica sobre ajudas de Items
                                                     *
                                                     */

                                                    else if (contentFromID.get(0)[0].equals("i")) {
                                                        System.out.println("* Tipo: Item.                 *");
                                                        System.out.println(
                                                                "* O que é: " + contentFromID.get(0)[1] + ".    *");
                                                        System.out.println("* Quantidade: " + contentFromID.get(0)[2]
                                                                + ".            *");
                                                        System.out.println(
                                                                "* Data: " + simpleFormat.format(date) + ".   *");
                                                    }
                                                    System.out.println("*                             *");
                                                    System.out.println("*******************************");

                                                    break;

                                                case '4':
                                                    isOnHistorico = false;
                                                    break;

                                                default:

                                                    break;
                                            }

                                        }

                                        break;

                                    case 3:

                                        System.out.println("Obrigado pela utilização!");
                                        isValidNumber = false;
                                        isSameUser = false;
                                        break;

                                    default:

                                        break;
                                }
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

                            String path = "data/usersData/" + activeUser.phoneNumber + ".csv";

                            sto = new Storager("m", activeUser.name, String.valueOf(activeUser.phoneNumber),
                                    String.valueOf(activeUser.phoneNumber));

                            // Verificar se utilizador já existe senão criasse um ficheiro para este
                            if (sto.fileExists("data/usersData/",
                                    String.valueOf(activeUser.phoneNumber) + ".csv") == false) {
                                sto.writeToFileUserInfo(path);
                            }

                            System.out.println("Bem vindo: " + activeUser.name + "! ID/NumeroTelemovel: "
                                    + activeUser.phoneNumber);

                            /*
                             * Menu de Migrante!
                             */


                            String[] listaCont;
                            List<String[]> atual;
                            String id;

                            boolean isUsingMigrant = true;
                            while (isUsingMigrant) {
                                System.out.println("    ->(1) Registar pedido de Ajuda.");
                                System.out.println("    ->(2) Histórico de pedidos de Ajudas.");
                                System.out.println("    ->(3) Sair da aplicação.");
                                switch (src.nextInt()) {

                                    /*
                                     * Registar ajuda pedida pelo Utilizador deve indicar o tipo de ajuda requerido.
                                     */
                                    case 1:
                                        System.out.println("Selecione o tipo de ajuda pretendido:");
                                        System.out.println("    ->(1) Pedir alojamento.");
                                        System.out.println("    ->(2) Pedir item.");
                                        System.out.println("    ->Qualquer outro input para voltar");
                                        switch (src.nextInt()) {


                                            //Pedidos de alojamento.
                                            case 1:
                                               

                                                listaCont = sto.listaFilesAtPath("data/helpData/");
                                                
                    
                                                //Apresentar ao user todas as casas existentes
                                                System.out.println("Estes são os alojamentos disponíveis e as suas lotações: ");
                                                for (String s : listaCont) {
                                                    atual = sto.readFile(s);
                                                    if(atual.get(0)[0].equals("c")){
                                                        System.out.println("Localização: "+ atual.get(0)[1]+ ", lotação máxima: " + atual.get(0)[2]+" e ID: " + s.replaceAll("[^0-9]", "")+".");
                                                        
                                                    }
                                                    
                                                    
                                                }
                                                
                                                System.out.println("Escolha o alojamento pretendido.");
                                                id = src.next();

                                                atual = sto.readFile("data/helpData/"+id+".csv");
                                                
                                                path = "data/helpRequests/";
                                                helpTimeAndDate = System.currentTimeMillis();


                                                // Generate random name for file
                                                rand = new Random();
                                                ticketNumber = rand.nextInt(999999 - 111111) + 111111;

                                                path += ticketNumber + ".csv";
                                                sto.writeToFileUserHelp(path, atual.get(0) [0], atual.get(0) [1],
                                                        String.valueOf(atual.get(0) [2]), String.valueOf(helpTimeAndDate));
                                                sto.writeToUserFileListOfHelp(
                                                        "data/usersData/" + activeUser.phoneNumber + ".csv",
                                                        String.valueOf(ticketNumber), atual.get(0)[0], "true");

                                                break;

                                            case 2:
                                            
                                                listaCont = sto.listaFilesAtPath("data/helpData/");

                    
                                                //Apresentar ao user todas as casas existentes
                                                System.out.println("Estes são os itens disponíveis e as suas quantidades: ");
                                                for (String s : listaCont) {
                                                    atual = sto.readFile(s);
                                                    if(atual.get(0)[0].equals("i")){
                                                        System.out.println("Item: "+ atual.get(0)[1]+ ", Quantidade: " + atual.get(0)[2]+" e ID: " + s.replaceAll("[^0-9]", "")+".");
                                                        
                                                    }
                                                    
                                                    
                                                }
                                                
                                                System.out.println("Escolha o id do item pretendido.");
                                                id = src.next();

                                                atual = sto.readFile("data/helpData/"+id+".csv");
                                                
                                                path = "data/helpRequests/";
                                                helpTimeAndDate = System.currentTimeMillis();


                                                // Generate random name for file
                                                rand = new Random();
                                                ticketNumber = rand.nextInt(999999 - 111111) + 111111;

                                                path += ticketNumber + ".csv";
                                                sto.writeToFileUserHelp(path, atual.get(0) [0], atual.get(0) [1],
                                                        String.valueOf(atual.get(0) [2]), String.valueOf(helpTimeAndDate));
                                                sto.writeToUserFileListOfHelp(
                                                        "data/usersData/" + activeUser.phoneNumber + ".csv",
                                                        String.valueOf(ticketNumber), atual.get(0)[0], "true");
                               
                                                break;
                                            



                                        }
                                        break;



                                    //Historico de pedidos efetuados pelo utilizador
                                    case 2:
                                        System.out.println("Histórico de pedidos do utilizador: " + activeUser.name);
                                        boolean isOnHistorico = true;
                                        while (isOnHistorico) {

                                            System.out.println("Selecione que histórico deseja ver:");
                                            System.out.println(
                                                    "    ->(1) Todas os pedidos de ajudas (sem detalhe, ordenadas por data).");
                                            System.out.println(
                                                    "    ->(2-x) Todas os pedidos de ajudas de cada tipo! Dominio(x) = {i,c}.");
                                            System.out.println(
                                                    "    ->(3-x) Detalhes sobre pedidos de ajuda especificos! Dominio(x) = {idTicket}");
                                            System.out.println("    ->(4) Voltar");

                                            // Todo o conteudo do ficheiro correspondente ao user
                                            List<String[]> content = sto
                                                    .readFile("data/usersData/" + activeUser.phoneNumber + ".csv");

                                            String input = src.next();
                                            switch (input.charAt(0)) {

                                                /*
                                                 * Histótico geral do utilizador
                                                 *
                                                 */

                                                case '1':

                                                    content.forEach((line) -> {
                                                        if (line[0].equals("m")) {
                                                            System.out.println("Histórico do utilizador: " + line[3]);
                                                            System.out.println("*************************************");
                                                            System.out.println("*                                   *");

                                                        } else {
                                                            System.out.println("* Tipo de ajuda: " + line[1] + ". ID: "
                                                                    + line[0] + ".     *");
                                                        }

                                                    });
                                                    System.out.println("*                                   *");
                                                    System.out.println("*************************************");

                                                    break;

                                                /*
                                                 * Histótico epecifico do utilizador
                                                 *
                                                 */

                                                case '2':

                                                    /*
                                                     * Histótico especifico {i} do utilizador
                                                     *
                                                     */
                                                    if (input.charAt(2) == 'i') {
                                                        content.forEach((line) -> {
                                                            if (line[0].equals("m")) {
                                                                System.out.println("Histórico do utilizador: " + line[3]
                                                                        + "! Especifico para Itens.");
                                                                System.out.println(
                                                                        "*************************************");
                                                                System.out.println(
                                                                        "*                                   *");

                                                            } else if (line[1].equals("i")) {
                                                                System.out.println("* Tipo de ajuda: " + line[1]
                                                                        + ". ID: " + line[0] + ".     *");
                                                            }

                                                        });
                                                        System.out.println("*                                   *");
                                                        System.out.println("*************************************");

                                                        /*
                                                         * Histótico especifico {c} do utilizador
                                                         *
                                                         */
                                                    } else if (input.charAt(2) == 'c') {
                                                        content.forEach((line) -> {
                                                            if (line[0].equals("m")) {
                                                                System.out.println("Histórico do utilizador: " + line[3]
                                                                        + "! Especifico para Alojamentos.");
                                                                System.out.println(
                                                                        "*************************************");
                                                                System.out.println(
                                                                        "*                                   *");

                                                            } else if (line[1].equals("c")) {
                                                                System.out.println("* Tipo de ajuda: " + line[1]
                                                                        + ". ID: " + line[0] + ".     *");
                                                            }

                                                        });
                                                        System.out.println("*                                   *");
                                                        System.out.println("*************************************");

                                                    }

                                                    break;

                                                /*
                                                 * Informações especificas sobre ticket de ID especifico
                                                 *
                                                 */

                                                case '3':
                                                    String[] inputDivided = input.split("-");

                                                    List<String[]> contentFromID = sto
                                                            .readFile("data/helpRequests/" + inputDivided[1] + ".csv");
                                                    date = new Date(Long.parseLong(contentFromID.get(0)[3]));
                                                    System.out.println("Informações sobre ticket: " + inputDivided[1]);
                                                    System.out.println("*******************************");
                                                    System.out.println("*                             *");

                                                    /*
                                                     * Informação especifica sobre ajudas de Alojamentos
                                                     *
                                                     */
                                                    if (contentFromID.get(0)[0].equals("c")) {
                                                        System.out.println("* Tipo: Alojamento.           *");
                                                        System.out.println("* Localizado em: " + contentFromID.get(0)[1]
                                                                + ".      *");
                                                        System.out.println("* Lotação máxima: "
                                                                + contentFromID.get(0)[2] + ".          *");
                                                        System.out.println(
                                                                "* Data: " + simpleFormat.format(date) + ".   *");
                                                    }
                                                    /*
                                                     * Informação especifica sobre ajudas de Items
                                                     *
                                                     */

                                                    else if (contentFromID.get(0)[0].equals("i")) {
                                                        System.out.println("* Tipo: Item.                 *");
                                                        System.out.println(
                                                                "* O que é: " + contentFromID.get(0)[1] + ".    *");
                                                        System.out.println("* Quantidade: " + contentFromID.get(0)[2]
                                                                + ".            *");
                                                        System.out.println(
                                                                "* Data: " + simpleFormat.format(date) + ".   *");
                                                    }
                                                    System.out.println("*                             *");
                                                    System.out.println("*******************************");

                                                    break;

                                                case '4':
                                                    isOnHistorico = false;
                                                    break;

                                                default:

                                                    break;
                                            }
                                        

                                        }
                    

                                        break;

                                    case 3:

                                        System.out.println("Esperemos que tenha consguido o desejado!");
                                        isUsingMigrant = false;

                                        break;

                                    default:

                                        break;
                                }
                            }

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