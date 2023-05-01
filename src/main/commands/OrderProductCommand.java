package main.commands;

import main.Ecommerce;
import main.Product;
import main.exceptions.NoProductException;
import main.exceptions.NotEnoughStockException;

import java.util.Map;

public class OrderProductCommand implements EcommerceCommand {
    private Ecommerce ecommerce;
    private String id;
    private int quantity;
    private int orderPrice;
    private Product product;

    public OrderProductCommand(Ecommerce ecommerce, String id, int quantity) {
        this.ecommerce = ecommerce;
        this.id = id;
        this.quantity = quantity;
        orderPrice = 0;
        product = null;
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public Product getProduct() { return product; }

    public Ecommerce getEcommerce() {
        return ecommerce;
    }

    @Override
    public void execute() throws NotEnoughStockException, NoProductException {
        if (ecommerce.getCatalog().containsKey(id)){
            Map<String, Integer> quantities = ecommerce.getQuantities();
            int availableQuantity = quantities.get(id);
            if (quantity <= availableQuantity) {
                quantities.put(id, availableQuantity - quantity);
                orderPrice = ecommerce.getCatalog().get(id).getPrice();
                product = ecommerce.getCatalog().get(id);
                ecommerce.getOrderHistory().add(this);
            } else {
                throw new NotEnoughStockException(id);
            }
        } else {
            throw new NoProductException(id);
        }
    }
}
