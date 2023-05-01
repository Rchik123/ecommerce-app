package main.commands;

import main.Ecommerce;
import main.Product;

import java.util.Map;

public class GetFewestCommand implements EcommerceCommand{
    private Ecommerce ecommerce;

    private String fewestProductName;

    public GetFewestCommand(Ecommerce ecommerce) {
        this.ecommerce = ecommerce;
        fewestProductName = null;
    }

    @Override
    public void execute() {
        int fewestQuantity = Integer.MAX_VALUE;
        Product fewestProduct = null;

        Map<String, Integer> quantities = ecommerce.getQuantities();
        for (String id : quantities.keySet()){
            int quantity = quantities.get(id);
            if (quantity < fewestQuantity) {
                fewestQuantity = quantity;
                fewestProduct = ecommerce.getCatalog().get(id);
            }
        }

        if (fewestProduct != null) {
            fewestProductName = fewestProduct.getName();
        }
    }

    public String getFewestProductName(){
        return fewestProductName;
    }
}
