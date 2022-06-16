public class HelpHandler {

    protected String region;
    protected String type;
    protected int quantity;
    protected Alojamento alojamento;
    protected Item item;


    //Registar o tipo de ajuda, alojamento, ou itens
    public HelpHandler(String type, int quantity, String region){
        this.type = type;
        if(type == "i" || type == "item"){
            this.quantity = quantity;
        }else{
            this.quantity = 0;
            this.region = region;
        }
    }

    /**
     * Criar objeto Alojamento
     * @param alojamento
     * @return novoAlojamento - 
     */
    public void creaAlojamento(int lotacao){
        alojamento = new Alojamento(region, lotacao);

    }

    /**
     * Criar objeto Item e atribuição do seu tipo, e da quantidade desse mesmo item
     * @param item
     * @return novoItem - tipo de item e quantidade desse mesmo item
     */
    public void createItem(){
        item = new Item(type,quantity);
       
    }

}