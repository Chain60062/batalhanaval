package domain;

import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class GameMap {
    private char[][] map = new char[18][18];
    private char[][] displayMap = new char[18][18];
    private List<Ship> shipsPlaced = new ArrayList<>();

    public GameMap() {
        initializeMaps();
    }

    private void initializeMaps() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 'v';
                displayMap[i][j] = 'o';
            }
        }
    }

    public void generateMap() {
        // TODO: talvez usar strategy pattern para geracao do mapa no futuro
        out.println("Gerando mapa, aguarde...");

        for (int i = 0; i < Boat.getQuantity(); i++) {
            var newBoat = new Boat();
            newBoat.placeShipOnMap(this);
            shipsPlaced.add(newBoat);
        }

        for (int i = 0; i < Destroyer.getQuantity(); i++) {
            var newDestroyer = new Destroyer();
            newDestroyer.placeShipOnMap(this);
            shipsPlaced.add(newDestroyer);
        }

        for (int i = 0; i < Frigate.getQuantity(); i++) {
            var newFrigate = new Frigate();
            newFrigate.placeShipOnMap(this);
            shipsPlaced.add(newFrigate);
        }

        for (int i = 0; i < Carrier.getQuantity(); i++) {
            var newCarrier = new Carrier();
            newCarrier.placeShipOnMap(this);
            shipsPlaced.add(newCarrier);
        }

        for (int i = 0; i < Submarine.getQuantity(); i++) {
            var newSubmarine = new Submarine();
            newSubmarine.placeShipOnMap(this);
            shipsPlaced.add(newSubmarine);
        }

        out.println("Mapa gerado.");
    }

    public void printMap() {
        for (int i = 0; i < displayMap.length; i++) {
            for (int j = 0; j < displayMap[0].length; j++) {
                System.out.print(displayMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printHiddenMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void printShips(){
        for (Ship ship: shipsPlaced) {
            out.println(ship.getSymbol());
        }
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }
}
