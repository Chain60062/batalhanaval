package domain;
public class Destroyer extends Ship{
    private final static int quantity = 3;

    Destroyer(){
        this.size = 5;
        this.symbol = 'D';
    }

    public static int getQuantity() {
        return quantity;
    }
}
