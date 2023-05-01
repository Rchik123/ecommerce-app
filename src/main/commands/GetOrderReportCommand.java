package main.commands;

import main.Ecommerce;
import main.Product;

import java.util.List;

public class GetOrderReportCommand implements EcommerceCommand{
    Ecommerce ecommerce;

    String orderReport;

    public GetOrderReportCommand(Ecommerce ecommerce) {
        this.ecommerce = ecommerce;
        orderReport = null;
    }

    @Override
    public void execute() throws Exception {
        List<OrderProductCommand> orderHistory = ecommerce.getOrderHistory();
        if (orderHistory.size() == 0){
            orderReport = "No orders to report";
            return;
        }
        orderReport = "";

        for (int i = 0; i < orderHistory.size(); i++){
            OrderProductCommand command = orderHistory.get(i);

            Product product = command.getProduct();

            String productId = product.getId();
            String productName = product.getName();
            int quantity = command.getQuantity();
            int price = command.getOrderPrice();

            GetAveragePriceCommand avgPriceCommand = new GetAveragePriceCommand(
                    command.getEcommerce(), productId);
            avgPriceCommand.execute();

            double cogs = avgPriceCommand.getAveragePrice() * quantity;
            int sellingPrice = command.getEcommerce().getCatalog().get(productId).getPrice();

            orderReport += "product id: " + productId +
                    ", product name: " + productName +
                    ", quantity: " + quantity +
                    ", price: " + price +
                    ", cogs: " + cogs +
                    ", selling price: " + sellingPrice;
            if (i != orderHistory.size() - 1) orderReport += '\n';
        }
    }

    public String getOrderReport() {
        return orderReport;
    }
}
