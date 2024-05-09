package domain;
public class Destroyer extends Ship{
    private final static int quantity = 3;

    Destroyer(){
        this.size = 5;
        this.symbol = 'D';
        this.name = "Destroyer";
        this.coordinates = new Coordinate[size];        
    }

    public static int getQuantity() {
        return quantity;
    }
}
