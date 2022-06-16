public class UsersHandler{


    //Variaveis comuns a todos os utilizadores 
    int phoneNumber;
    String name;
    Migrant migrant;
    Volunteer volunteer;


    // Para criar utilizadores apenas com phoneNumber
    public UsersHandler(int phoneNumber){
        this.phoneNumber = phoneNumber;

    } 

    //Para criar utilizadores com phonenumber e nome
    public UsersHandler(int phoneNumber, String name){
        this.phoneNumber = phoneNumber;
        this.name = name;
    }



    //Criar objeto migrante
    public void createMigrant() {
        migrant = new Migrant(name, phoneNumber);

    }


    //Criar objeto voluntario
    public void createVolunteer() {
        volunteer = new Volunteer(phoneNumber);
    }




}
