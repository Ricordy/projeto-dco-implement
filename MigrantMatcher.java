import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;;

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

                            String path = "data/usersData/" + activeUser.phoneNumber + ".csv";

                            sto = new Storager("v", String.valueOf(activeUser.phoneNumber),
                                    String.valueOf(activeUser.phoneNumber));

                            // Verificar se utilizador já existe senão criasse um ficheiro para este
                            if (sto.fileExists("data/usersData/",
                                    String.valueOf(activeUser.phoneNumber) + ".csv") == false) {
                                sto.writeToFileUserInfo(path);
                            }

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
                                            ticketNumber = rand.nextInt(999999);

                                            path += ticketNumber + ".csv";
                                            sto.writeToFileUserHelp(path, help.type, help.regionItem,
                                                    String.valueOf(help.quantity), String.valueOf(helpTimeAndDate));
                                            sto.writeToUserFileListOfHelp(
                                                    "data/usersData/" + activeUser.phoneNumber + ".csv",
                                                    String.valueOf(ticketNumber), help.type);

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
                                            ticketNumber = rand.nextInt(999999);

                                            path += ticketNumber + ".csv";
                                            sto.writeToFileUserHelp(path, help.type, help.regionItem,
                                                    String.valueOf(help.quantity), String.valueOf(helpTimeAndDate));
                                            sto.writeToUserFileListOfHelp(
                                                    "data/usersData/" + activeUser.phoneNumber + ".csv",
                                                    String.valueOf(ticketNumber), help.type);

                                            break;

                                    }

                                    break;

                                /*
                                 * Verificar histórico de ajudas
                                 */
                                case 2:
                                    System.out.println("Histórico de ajudas!");
                                    System.out.println("Selecione que histórico deseja ver:");
                                    System.out.println("    ->(1) Todas as ajudas (sem detalhe).");
                                    System.out.println("    ->(2) Todas as ajudas (sem detalhe, ordenadas por data).");
                                    System.out.println("    ->(3+x) Todas as ajudas de cada tipo! Dominio(x) = {i,c}.");
                                    System.out.println(
                                            "    ->(4+x) Detalhes sobre ajuda especifica! Dominio(x) = {idTicket}");

                                    // Todo o conteudo do ficheiro correspondente ao user
                                    List<String[]> content = sto
                                            .readFile("data/usersData/" + activeUser.phoneNumber + ".csv");

                                    String input = src.next();
                                    switch (input.charAt(0)) {

                                        case '1':

                                            content.forEach((line) -> {
                                                if (line[0].equals("v")) {
                                                    System.out.println("Histórico do utilizador: " + line[3]);
                                                    System.out.println("***********************************");

                                                } else {
                                                    System.out.println(
                                                            "* Tipo de ajuda: " + line[1] + ". ID: " + line[0] + ". *");
                                                }

                                            });

                                            break;

                                    }

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
                            System.out.println("Bem vindo: " + activeUser.name + "! ID/NumeroTelemovel: "
                                    + activeUser.phoneNumber);

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