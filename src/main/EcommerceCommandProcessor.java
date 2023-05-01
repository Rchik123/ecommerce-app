package main;

import main.commands.*;
import main.exceptions.NoProductException;
import main.exceptions.NotEnoughStockException;

public class EcommerceCommandProcessor {
    public void processCommand(EcommerceCommand command){
        try {
            command.execute();
            if (command instanceof GetQuantityCommand) {
                System.out.println(((GetQuantityCommand) command).getQuantity());
            } else if (command instanceof GetFewestCommand) {
                String name = ((GetFewestCommand) command).getFewestProductName();
                if (name != null) {
                    System.out.println(name);
                } else {
                    System.out.println("No products in store");
                }
            } else if (command instanceof GetMostPopularCommand) {
                String name = ((GetMostPopularCommand) command).getMostPopularName();
                if (name != null) {
                    System.out.println(name);
                } else {
                    System.out.println("No products have been ordered");
                }
            } else if (command instanceof GetAveragePriceCommand){
                System.out.println(((GetAveragePriceCommand) command).getAveragePrice());
            } else if (command instanceof GetOrderReportCommand) {
                System.out.println(((GetOrderReportCommand) command).getOrderReport());
            }
        } catch (NoProductException e) {
            System.out.println("Product with id: " + e.getProductId() + " does not exist");
        } catch (NotEnoughStockException e) {
            System.out.println("Not enough stock for the product with id: " + e.getProductId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
