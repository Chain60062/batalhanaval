package domain;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private char[][] map = new char[16][16];
    private char[][] displayMap = new char[16][16];

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

    public List<Ship> generateMapAndGetShipsList() {
        List<Ship> shipsPlaced = new ArrayList<>();
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
        return shipsPlaced;
    }

    public void printMap(boolean isDisplay) {
        var mapToPrint = isDisplay ? displayMap : map;
        // Print the row index in the first row
        for (int i = 0; i < mapToPrint.length; i++) {
            if(i == 0){
                out.print("   ");
            }
            if(i <= 9){
                System.out.printf(" %d ", i);
            }else{
                System.out.printf("%d ", i);
            }
        }
        System.out.println();


        for (int i = 0; i < mapToPrint.length; i++) {
            System.out.printf("%2d ", i); 
            for (int j = 0; j < mapToPrint[0].length; j++) {
                System.out.print(" " + mapToPrint[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char[][] getMap() {
        return map;
    }
    
    public char[][] getDisplayMap() {
        return displayMap;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }
}
