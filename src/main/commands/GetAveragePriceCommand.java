package main.commands;

import main.Ecommerce;
import main.exceptions.NoProductException;

import java.util.List;

public class GetAveragePriceCommand implements EcommerceCommand{
    private Ecommerce ecommerce;
    private String id;

    private double averagePrice;

    public GetAveragePriceCommand(Ecommerce ecommerce, String id) {
        this.ecommerce = ecommerce;
        this.id = id;
        this.averagePrice = 0;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    @Override
    public void execute() throws NoProductException {
        if (!ecommerce.getCatalog().containsKey(id)){
            throw new NoProductException(id);
        }

        int count = 0;
        double totalPrice = 0;

        List<PurchaseProductCommand> purchaseHistory = ecommerce.getPurchaseHistory();
        if (purchaseHistory.size() == 0){
            averagePrice = 0;
            return;
        }

        for (PurchaseProductCommand command : purchaseHistory) {
            if (command.getId().equals(id)){
                totalPrice += command.getPrice() * command.getQuantity();
                count += command.getQuantity();
            }
        }

        averagePrice = totalPrice / count;
    }
}
