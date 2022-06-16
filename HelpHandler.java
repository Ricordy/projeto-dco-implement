public class HelpHandler {

    protected String regionItem; //Region if "c" Item if "i"
    protected String type;
    protected int quantity;
    protected Alojamento alojamento;
    protected Item item;


    //Registar o tipo de ajuda, alojamento, ou itens
    public HelpHandler(String type, int quantity, String regionItem){
        this.type = type;
        this.quantity = quantity;
        this.regionItem = regionItem;
        
    }

    /**
     * Criar objeto Alojamento
     * @param alojamento
     */
    public void creaAlojamento(){
        
        alojamento = new Alojamento(regionItem, quantity);

    }

    /**
     * Criar objeto Item e atribuição do seu tipo, e da quantidade desse mesmo item
     * @param item
     */
    public void createItem(){
        item = new Item(type,quantity);
       
    }

}