package main.commands;

import main.Ecommerce;
import main.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMostPopularCommand implements EcommerceCommand {
    private Ecommerce ecommerce;

    String mostPopularName;

    public GetMostPopularCommand(Ecommerce ecommerce) {
        this.ecommerce = ecommerce;
        mostPopularName = null;
    }

    @Override
    public void execute() {
        Map<String, Integer> orderCounts = new HashMap<>();

        List<OrderProductCommand> orderHistory = ecommerce.getOrderHistory();
        for (OrderProductCommand command : orderHistory){
            String id = command.getId();
            if (!orderCounts.containsKey(id)){
                orderCounts.put(id, command.getQuantity());
            } else {
                orderCounts.put(id, command.getQuantity() + orderCounts.get(id));
            }
        }

        int mostOrdered = Integer.MIN_VALUE;
        Product mostOrderedProduct = null;

        for (String id : orderCounts.keySet()){
            int count = orderCounts.get(id);
            if (count > mostOrdered) {
                mostOrdered = count;
                mostOrderedProduct = ecommerce.getCatalog().get(id);
            }
        }

        if (mostOrderedProduct != null) {
            mostPopularName = mostOrderedProduct.getName();
        }
    }

    public String getMostPopularName(){
        return mostPopularName;
    }
}
