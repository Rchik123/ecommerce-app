package main.exceptions;

public class NoProductException extends Exception{
    private final String productId;

    public NoProductException(String productId){
        super();
        this.productId = productId;
    }

    public String getProductId(){
        return productId;
    }
}
