package domain;
public class Carrier extends Ship {
    private final static int quantity = 2;

    Carrier(){
        this.size = 8;
        this.symbol = 'A';
        this.name = "Porta Aviões";
        this.coordinates = new Coordinate[size];
    }

    public static int getQuantity() {
        return quantity;
    }
}
