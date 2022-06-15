public class UsersHandler{


    //Variaveis comuns a todos os utilizadores 
    int phoneNumber;
    String name;


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
    public Migrant createMigrant(UsersHandler user) {
        Migrant novoMigrante = new Migrant(user.name, user.phoneNumber);
        return novoMigrante;
    }


    //Criar objeto voluntario
    public Volunteer createVolunteer(UsersHandler user) {
        Volunteer novoVoluntario = new Volunteer(user.phoneNumber);
        return novoVoluntario;
    }




}
