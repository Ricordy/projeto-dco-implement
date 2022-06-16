import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Storager {

    // Atributos guardados nos vários CSV
    String identificador;
    String nome;
    String phoneNumber;
    String csvAttatched;

    // Utilização geral
    BufferedReader csvReader;
    PrintWriter csvWriter;
    String[] contentToAdd;

    public Storager() {

    }

    // No caso de se criar a primeira instancia para um voluntário
    public Storager(String identificador, String phoneNumber, String csvAttatched) {
        this.identificador = identificador;
        this.phoneNumber = phoneNumber;
        this.nome = "null";
        this.csvAttatched = csvAttatched;

    }

    // No caso de se criar a primeira instância para um Migrante
    public Storager(String identificador, String nome, String phoneNumber, String csvAttatched) {
        this.identificador = identificador;
        this.phoneNumber = phoneNumber;
        this.nome = nome;
        this.csvAttatched = csvAttatched;

    }

    /**
     * Metodo chamado para escrever o conteudo atual sobre o utilizador no csv
     * pretendido.
     * 
     * @param path
     */
    //
    public void writeToFileUserInfo(String path) {
        List<String[]> content = new ArrayList<>();
        contentToAdd = new String[4];

        // Ler todo o conteudo pré-existente no ficheiro.

        try {
            csvReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = csvReader.readLine()) != null) {
                content.add(line.split(","));
                System.out.println(content.toString());
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        // Escrever o conteudo pretendido no ficheiro

        contentToAdd[0] = identificador;
        contentToAdd[1] = nome;
        contentToAdd[2] = phoneNumber;
        contentToAdd[3] = csvAttatched;

        content.add(contentToAdd);

        try {

            csvWriter = new PrintWriter(new File(path));

            // Transformar String[]... em String1,String2,String3,... e escrever
            if (content.size() == 1) {
                String toWrite = contentToAdd[0] + "," + contentToAdd[1] + "," + contentToAdd[2] + ","
                        + contentToAdd[3];
                System.out.println(toWrite + " at path: " + path);
                csvWriter.write(toWrite + '\n');

            } else {
                content.forEach((line) -> {
                    String toWrite = line[0] + "," + line[1] + "," + line[2] + "," + line[3];

                    System.out.println(toWrite + " at path: " + path);
                    csvWriter.write(toWrite + '\n');

                });

            }

            csvWriter.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * Metodo chamado para escrever o conteudo atual sobre a ajuda no csv
     * pretendido (path).
     * 
     * @param path
     * @param tipoAjuda     "c" ou "i"
     * @param localItem     "localDaCasa" ou "nomeDoItem"
     * @param lotQuantidade "lotacaoDaCasa" ou "quantidadeDeItens"
     * @param data          "data da oferta"
     */
    //
    public void writeToFileUserHelp(String path, String tipoAjuda, String localItem, String lotQuantidade,
            String data) {
        List<String[]> content = new ArrayList<>();
        contentToAdd = new String[4];

        // Ler todo o conteudo pré-existente no ficheiro.

        try {
            csvReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = csvReader.readLine()) != null) {
                content.add(line.split(","));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Não foi possivel ler o ficheiro");
            // TODO: handle exception
        }

        // Escrever o conteudo pretendido no ficheiro

        contentToAdd[0] = tipoAjuda;
        contentToAdd[1] = localItem;
        contentToAdd[2] = lotQuantidade;
        contentToAdd[3] = data;

        content.add(contentToAdd);

        try {
            csvWriter = new PrintWriter(new File(path));

            // Transformar String[]... em String1,String2,String3,... e escrever
            if (content.size() == 1) {
                String toWrite = contentToAdd[0] + "," + contentToAdd[1] + "," + contentToAdd[2] + ","
                        + contentToAdd[3];
                System.out.println(toWrite + " at path: " + path);
                csvWriter.write(toWrite + '\n');

            } else {
                content.forEach((line) -> {
                    String toWrite = line[0] + "," + line[1] + "," + line[2] + "," + line[3];

                    System.out.println(toWrite + " at path: " + path);
                    csvWriter.write(toWrite + '\n');

                });

            }

            csvWriter.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Não foi possivel ecrever no ficheiro");
        }

    }


    /**
     * Metodo chamado para escrever o conteudo atual sobre a ajuda no csv
     * pretendido (path).
     * 
     * @param path
     * @param tipoAjuda     "c" ou "i"
     * @param localItem     "localDaCasa" ou "nomeDoItem"
     * @param lotQuantidade "lotacaoDaCasa" ou "quantidadeDeItens"
     * @param data          "data da oferta"
     */
    //
    public void writeToUserFileListOfHelp(String path, String idTicket, String tipoAjuda) {
        List<String[]> content = new ArrayList<>();
        contentToAdd = new String[4];

        // Ler todo o conteudo pré-existente no ficheiro.

        try {
            csvReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = csvReader.readLine()) != null) {
                content.add(line.split(","));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Não foi possivel ler o ficheiro");
            // TODO: handle exception
        }

        // Escrever o conteudo pretendido no ficheiro

        contentToAdd[0] = idTicket;
        contentToAdd[1] = tipoAjuda;
        contentToAdd[2] = "null";
        contentToAdd[3] = "null";


        content.add(contentToAdd);

        try {
            csvWriter = new PrintWriter(new File(path));

            // Transformar String[]... em String1,String2,String3,... e escrever
            if (content.size() == 1) {
                String toWrite = contentToAdd[0] + "," + contentToAdd[1] + "," + contentToAdd[2] + ","
                        + contentToAdd[3];
                System.out.println(toWrite + " at path: " + path);
                csvWriter.write(toWrite + '\n');

            } else {
                content.forEach((line) -> {
                    String toWrite = line[0] + "," + line[1] + "," + line[2] + "," + line[3];

                    System.out.println(toWrite + " at path: " + path);
                    csvWriter.write(toWrite + '\n');

                });

            }

            csvWriter.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Não foi possivel ecrever no ficheiro");
        }

    }

    /**
     * 
     * Criar csv pretendido em local nome exemplo: nome = "src/lcl/userinfo.csv"
     * Guardado em: src/lcl/ Nome: userinfo.csv
     * 
     * @param name
     */
    public void createCsv(String name) {
        File csvFile = new File(name);
        try {
            csvFile.createNewFile();
            System.out.println("CSV Sucessefuly created at: " + name);
        } catch (Exception e) {
            System.out.println("Erro na criação do csv");
        }

    }

    /**
     * LEr o ficheiro pretendido para históricos e confirmações
     * 
     * @param path
     * @return List<String[]>
     */
    public List<String[]> readFile(String path) {
        List<String[]> content = new ArrayList<>();

        // Ler o ficheiro e guardar em content -> ArrayList
        try {
            csvReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = csvReader.readLine()) != null) {
                content.add(line.split(","));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Não foi possivel ler o ficheiro! caminho:" + path);
        }

        return content;
    }

    // Setters and getters;

    /**
     * @param identificador
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param csvAttatched
     */
    public void setCsvAttatched(String csvAttatched) {
        this.csvAttatched = csvAttatched;
    }

    /**
     * @return String
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @return String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return String
     */
    public String getCsvAttatched() {
        return csvAttatched;
    }
}
