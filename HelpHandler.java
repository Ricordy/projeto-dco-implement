public class HelpHandler {

    protected String region;
    protected String type;
    protected int quantity;

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
    public Alojamento creaAlojamento(HelpHandler alojamento){
        Alojamento novoAlojamento = new Alojamento(alojamento.region);
        return novoAlojamento;
    }

    /**
     * Criar objeto Item e atribuição do seu tipo, e da quantidade desse mesmo item
     * @param item
     * @return novoItem - tipo de item e quantidade desse mesmo item
     */
    public Item createItem(HelpHandler item){
        Item novoItem = new Item(item.type, item.quantity);
        return novoItem;
    }

}