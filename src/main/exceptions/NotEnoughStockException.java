package main.exceptions;

public class NotEnoughStockException extends Exception{
    private final String productId;

    public NotEnoughStockException(String productId){
        super();
        this.productId = productId;
    }

    public String getProductId(){
        return productId;
    }
}
