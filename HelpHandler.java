public class HelpHandler {

    protected String region;
    protected String type;
    protected int quantity;
    protected Alojamento alojamento;
    protected Item item;


    //Registar o tipo de ajuda, alojamento, ou itens
    public HelpHandler(String type, int quantity, String region){
        this.type = type;
        this.quantity = quantity;
        this.region = region;
        
    }

    /**
     * Criar objeto Alojamento
     * @param alojamento
     */
    public void creaAlojamento(){
        
        alojamento = new Alojamento(region, quantity);

    }

    /**
     * Criar objeto Item e atribuição do seu tipo, e da quantidade desse mesmo item
     * @param item
     */
    public void createItem(){
        item = new Item(type,quantity);
       
    }

}