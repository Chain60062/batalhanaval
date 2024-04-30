package domain;

import java.util.Random;
import static java.lang.System.out;

public class GameMap {
    private char[][] map = new char[26][26];
    private char[][] displayMap = new char[26][26];

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
        out.println("Gerando mapa, aguarde...");
        // botes
        for (int i = 0; i < Boat.getQuantity(); i++) {
            placeShip(new Boat());
        }
        // // destroyers
        for (int i = 0; i < Destroyer.getQuantity(); i++) {
            placeShip(new Destroyer());
        }
        // // fragatas
        for (int i = 0; i < Frigate.getQuantity(); i++) {
            placeShip(new Frigate());
        }
        // submarinos
        for (int i = 0; i < Submarine.getQuantity(); i++) {
            placeShip(new Submarine());
        }
        // porta avioes
        for (int i = 0; i < Carrier.getQuantity(); i++) {
            placeShip(new Carrier());
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

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

}
