package main;

import main.commands.OrderProductCommand;
import main.commands.PurchaseProductCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ecommerce {
    // maps product ids to products
    private final Map<String, Product> catalog;
    // maps product ids to quantity
    private final Map<String, Integer> quantities;
    private final List<PurchaseProductCommand> purchaseHistory;
    private final List<OrderProductCommand> orderHistory;

    public Map<String, Product> getCatalog() {
        return catalog;
    }

    public Map<String, Integer> getQuantities() {
        return quantities;
    }

    public List<PurchaseProductCommand> getPurchaseHistory() {
        return purchaseHistory;
    }

    public List<OrderProductCommand> getOrderHistory() {
        return orderHistory;
    }

    public Ecommerce(){
        catalog = new HashMap<>();
        quantities = new HashMap<>();
        purchaseHistory = new ArrayList<>();
        orderHistory = new ArrayList<>();
    }
}
