package domain;

//porta-avioes
public class AircraftCarrier extends Ship {
    private final static int quantity = 2;

    AircraftCarrier(){
        this.size = 8;
        this.symbol = 'A';
        this.name = "Porta Aviões";
        this.coordinates = new Coordinate[size];
    }

    public static int getQuantity() {
        return quantity;
    }
}
