package main.commands;

import main.Ecommerce;
import main.exceptions.NoProductException;

import java.util.List;

public class GetAveragePriceCommand implements EcommerceCommand{
    private Ecommerce ecommerce;
    private String id;

    public GetAveragePriceCommand(Ecommerce ecommerce, String id) {
        this.ecommerce = ecommerce;
        this.id = id;
    }

    @Override
    public void execute() throws NoProductException {
        boolean prodPurchased = false;

        int count = 0;
        double totalPrice = 0;

        List<PurchaseProductCommand> purchaseHistory = ecommerce.getPurchaseHistory();
        for (PurchaseProductCommand command : purchaseHistory) {
            if (command.getId().equals(id)){
                prodPurchased = true;
                totalPrice += command.getPrice() * command.getQuantity();
                count += command.getQuantity();
            }
        }

        double averagePrice = totalPrice / count;

        if (!prodPurchased && !ecommerce.getCatalog().containsKey(id)) {
            throw new NoProductException(id);
        }
        System.out.println(averagePrice);
    }
}
