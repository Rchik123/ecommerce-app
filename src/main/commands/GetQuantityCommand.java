package main.commands;

import main.Ecommerce;
import main.exceptions.NoProductException;

public class GetQuantityCommand implements EcommerceCommand {
    private Ecommerce ecommerce;
    private String id;

    private int quantity;

    public GetQuantityCommand(Ecommerce ecommerce, String id) {
        this.ecommerce = ecommerce;
        this.id = id;
    }

    @Override
    public void execute() throws NoProductException {
        if (ecommerce.getQuantities().containsKey(id)){
            this.quantity = ecommerce.getQuantities().get(id);
        } else {
            throw new NoProductException(id);
        }
    }

    public int getQuantity(){
        return quantity;
    }
}
