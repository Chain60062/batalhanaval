package domain;

public class Boat extends Ship{
    private final static int quantity = 6;

    Boat(){
        this.size = 2;
        this.symbol = 'B';
    }

    public static int getQuantity() {
        return quantity;
    }
}
