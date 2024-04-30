package domain;

public class Frigate extends Ship{
    private final static int quantity = 5;

    Frigate(){
        this.size = 3;
        this.symbol = 'F';
    }

    public static int getQuantity() {
        return quantity;
    }
}
