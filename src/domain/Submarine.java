package domain;

public class Submarine extends Ship{
    private final static int quantity = 4;
 
    Submarine(){
        this.size = 4;
        this.symbol = 'S';
        this.coordinates = new Coordinate[size];
    }

    public static int getQuantity() {
        return quantity;
    }    
}