package main.commands;

import main.Ecommerce;

import java.util.Map;

public class PurchaseProductCommand implements EcommerceCommand {
    private Ecommerce ecommerce;
    private String id;
    private int quantity;
    private int price;

    public PurchaseProductCommand(Ecommerce ecommerce, String id, int quantity, int price) {
        this.ecommerce = ecommerce;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public void execute() {
        int newQuantity = quantity;
        Map<String, Integer> quantities = ecommerce.getQuantities();
        if (quantities.containsKey(id)) newQuantity += quantities.get(id);
        quantities.put(id, newQuantity);
        ecommerce.getPurchaseHistory().add(this);
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
