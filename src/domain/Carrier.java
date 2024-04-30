package domain;
public class Carrier extends Ship {
    private final static int quantity = 2;

    Carrier(){
        this.size = 8;
        this.symbol = 'A';
    }

    public static int getQuantity() {
        return quantity;
    }
}
