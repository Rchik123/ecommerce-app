package main;

import main.commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EcommerceCommandParser {
    private Ecommerce ecommerce;

    public EcommerceCommandParser(Ecommerce ecommerce){
        this.ecommerce = ecommerce;
    }

    public EcommerceCommand parseCommandStr(String commandStr){
        String commandName;
        int argumentsSize;
        List<String> arguments = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(commandStr, " ");
        if (tokenizer.hasMoreTokens()){
            commandName = tokenizer.nextToken();
        } else return new InvalidCommand();

        while(tokenizer.hasMoreTokens()){
            arguments.add(tokenizer.nextToken());
        }
        argumentsSize = arguments.size();

        try {
            switch (commandName) {
                case "save_product":
                    if (argumentsSize == 3) {
                        String id = arguments.get(0);
                        String name = arguments.get(1);
                        int price = Integer.parseInt(arguments.get(2));
                        Product product = new Product(id, name, price);
                        return new SaveProductCommand(ecommerce, product);
                    } else {
                        return new InvalidCommand();
                    }

                case "purchase_product":
                    if (argumentsSize == 3) {
                        String id = arguments.get(0);
                        int quantity = Integer.parseInt(arguments.get(1));
                        int price = Integer.parseInt(arguments.get(2));
                        return new PurchaseProductCommand(ecommerce, id, quantity, price);
                    } else {
                        return new InvalidCommand();
                    }

                case "order_product":
                    if (argumentsSize == 2) {
                        String id = arguments.get(0);
                        int quantity = Integer.parseInt(arguments.get(1));
                        return new OrderProductCommand(ecommerce, id, quantity);
                    } else {
                        return new InvalidCommand();
                    }

                case "get_quantity_of_product":
                    if (argumentsSize == 1) {
                        String id = arguments.get(0);
                        return new GetQuantityCommand(ecommerce, id);
                    } else {
                        return new InvalidCommand();
                    }

                case "get_average_price":
                    if (argumentsSize == 1) {
                        String id = arguments.get(0);
                        return new GetAveragePriceCommand(ecommerce, id);
                    } else {
                        return new InvalidCommand();
                    }

                case "get_product_profit":
                    if (argumentsSize == 1) {
                        String id = arguments.get(0);
                        return new GetProfitCommand(ecommerce, id);
                    } else {
                        return new InvalidCommand();
                    }

                case "get_fewest_product":
                    if (argumentsSize == 0){
                        return new GetFewestCommand(ecommerce);
                    } else {
                        return new InvalidCommand();
                    }

                case "get_most_popular_product":
                    if (argumentsSize == 0){
                        return new GetMostPopularCommand(ecommerce);
                    } else {
                        return new InvalidCommand();
                    }
                default:
                    return new InvalidCommand();
            }
        } catch (NumberFormatException ex){
            return new InvalidCommand();
        }

    }
}
