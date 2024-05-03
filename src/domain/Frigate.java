package domain;

public class Frigate extends Ship{
    private final static int quantity = 5;

    Frigate(){
        this.size = 3;
        this.symbol = 'F';
        this.coordinates = new Coordinate[size];
    }

    public static int getQuantity() {
        return quantity;
    }
}
