package main.commands;

import main.Ecommerce;
import main.Product;

public class SaveProductCommand implements EcommerceCommand {
    private final Ecommerce ecommerce;
    private final Product product;

    public SaveProductCommand(Ecommerce ecommerce, Product product) {
        this.ecommerce = ecommerce;
        this.product = product;
    }

    @Override
    public void execute() {
        String id = product.getId();
        ecommerce.getCatalog().put(id, product);
        if (!ecommerce.getQuantities().containsKey(id)) {
            ecommerce.getQuantities().put(id, 0);
        }
    }
}
