package main.commands;

import main.Ecommerce;
import main.exceptions.NoProductException;

import java.util.List;

public class GetProfitCommand implements EcommerceCommand{
    Ecommerce ecommerce;
    String id;

    public GetProfitCommand(Ecommerce ecommerce, String id) {
        this.ecommerce = ecommerce;
        this.id = id;
    }

    @Override
    public void execute() throws NoProductException {
        if (!ecommerce.getCatalog().containsKey(id)) {
            throw new NoProductException(id);
        }

        double profit = 0;

        List<PurchaseProductCommand> purchaseHistory = ecommerce.getPurchaseHistory();
        List<OrderProductCommand> orderHistory = ecommerce.getOrderHistory();

        if (orderHistory.size() == 0 || purchaseHistory.size() == 0){
            System.out.println(profit);
            return;
        }

        int quantityPurchased = 0;
        double purchasePriceTotal = 0;
        int quantityOrdered = 0;
        double orderPriceTotal = 0;

        for (PurchaseProductCommand command : purchaseHistory) {
            if (command.getId().equals(id)) {
                quantityPurchased += command.getQuantity();
                purchasePriceTotal += command.getQuantity() * command.getPrice();
            }
        }

        for (OrderProductCommand command : orderHistory) {
            if (command.getId().equals(id)) {
                quantityOrdered += command.getQuantity();
                orderPriceTotal += command.getQuantity() * command.getOrderPrice();
            }
        }

        double averagePurchase = purchasePriceTotal / quantityPurchased;
        double averageOrdered = orderPriceTotal / quantityOrdered;

        profit = (averageOrdered - averagePurchase) * quantityOrdered;

        System.out.println(profit);
    }
}
