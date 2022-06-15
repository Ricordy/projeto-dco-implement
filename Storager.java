import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.BufferedWriter;  
import java.io.FileWriter;  
import java.util.ArrayList;
import java.util.List;



public class Storager {


    //Atributos guardados nos vários CSV
    String identificador;
    String nome;
    String phoneNumber;
    String csvAttatched;


    //Utilização geral
    BufferedReader csvReader;
    BufferedWriter csvWriter;



    //No caso de se criar a primeira instancia para um voluntário
    public Storager (String identificador, String phoneNumber, String csvAttatched){
        this.identificador = identificador;
        this.phoneNumber = phoneNumber;
        this.nome = "null";
        this.csvAttatched = csvAttatched;


    }


    //No caso de se criar a primeira instância para um Migrante
    public Storager (String identificador, String nome, String phoneNumber, String csvAttatched){
        this.identificador = identificador;
        this.phoneNumber = phoneNumber;
        this.nome = nome;
        this.csvAttatched = csvAttatched;

    }


    
    /** 
     * Metodo chamado para escrever o conteudo atual no csv pretendido.
     * @param toSafe
     * @param path
     */
    //
    public void writeToFile(String path){
        List<String[]> content = new ArrayList<>();
        String[] contentToAdd = new String[4];

        
        
        //Ler todo o conteudo pré-existente no ficheiro.
        
        
        try {
            csvReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = csvReader.readLine()) != null) {
                content.add(line.split(","));
            }
            
        } catch (Exception e) {
            //TODO: handle exception
        }

        
        
        
        
        //Escrever o conteudo pretendido no ficheiro
        
        
        contentToAdd[0]=identificador;
        contentToAdd[1]=nome;
        contentToAdd[2]=phoneNumber;
        contentToAdd[3]=csvAttatched;

        content.add(contentToAdd);


        try {
            csvWriter = new BufferedWriter(new FileWriter(path));
            csvWriter.
        } catch (Exception e) {
            //TODO: handle exception
        }


        

        
 
    }




    //Setters and getters;


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