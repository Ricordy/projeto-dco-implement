import java.util.Scanner;

public class MigrantMatcher {
    public static void main(String[] agrs) {

        Scanner src = new Scanner(System.in);
        UsersHandler activeUser;
        boolean isValidNumber;

        Storager sto = new Storager();

        boolean isRunning = true;
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
                            Volunteer user = activeUser.createVolunteer(activeUser);

                            System.out.println("Bem vindo ID/NumeroTelemovel: " + user.phoneNumber + "!");

                            isValidNumber = false;

                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Por favor insira um inteiro");

                        }
                    }

                    /*
                     * Menu de Voluntário!
                     */
                    System.out.println("(1) Registar Ajuda.");
                    System.out.println("(2) Histórico de Ajudas.");
                    switch (src.nextInt()) {

                        /*
                         * Registar ajuda Utilizador deve indicar o tipo de ajuda e efetuar
                         */
                        case 1:
                            System.out.println("(1) Fornecer casa.");
                            System.out.println("(2) Oferecer item.");
                            switch(src.nextInt()){

                                case 1:
                                    HelpHandler help;
                                    System.out.println("Por-favor indique a região da casa.");
                                    String regiao = src.next();
                                    System.out.println("Por-favor indique a lotação da casa.");
                                    int lotacao = src.nextInt();
                                    help = new HelpHandler("c", 0, regiao);
                                    help.creaAlojamento(lotacao);



                                    break;

                                case 2:
                                    HelpHandler help;
                                    System.out.println("Por favor indique que item pretende doar.");
                                    String item = src.next();
                                    System.out.println("Por favor diga a quantidade de item que pretende doar.");
                                    int quantidade = src.nextInt();
                                    help = new HelpHandler("i", quantidade, item);
                                    help.createItem();


                                    break;

                            }

                            break;
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
                            Migrant user = activeUser.createMigrant(activeUser);
                            System.out.println("Bem vindo: " + user.name + "! ID/NumeroTelemovel: " + user.phoneNumber);

                            isValidNumber = false;

                        } catch (NumberFormatException e) {
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