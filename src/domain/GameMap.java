package domain;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private char[][] map = new char[16][16];//mapa 'verdadeiro' do jogo, v para vazio
    private char[][] displayMap = new char[16][16];//mapa com embarcacoes escondidas, o refere-se a oculto

    public GameMap() {
        initializeMaps();
    }
    //inicializa matriz vazia
    private void initializeMaps() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 'v';
                displayMap[i][j] = 'o';
            }
        }
    }
    //gera mapa e retorna a lista com todos os navios inseridos
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

        for (int i = 0; i < AircraftCarrier.getQuantity(); i++) {
            var newCarrier = new AircraftCarrier();
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
    //imprime mapa para o jogador, caso isDisplay seja true imprima o mapa com embarcacoes ocultas, caso contrario mostra mapa real
    public void printMap(boolean isDisplay) {
        char[][] mapToPrint = isDisplay ? displayMap : map;
        //imprime primeira linha com os numeros das colunas
        printNumbersRow(mapToPrint);
        printLettersColumn(mapToPrint);
    }
    //imprime a linha com os numeros dos indices
    private void printNumbersRow(char[][] map){
        for (int i = 1; i <= map.length; i++) {
            //espaco inicial
            if (i == 1) {
                out.print("   ");
            }
            //caso numero tenha duas casas mude os espacos para alinhamento
            if (i <= 9) {
                out.printf(" %d ", i);
            } else {
                out.printf("%d ", i);
            }
        }
        out.println();
    }
    //imprime a linha com as letras dos indices
    private void printLettersColumn(char[][] map){
        char letter = 'a';
        for (int i = 0; i < map.length; i++, letter++) {
            out.printf("%2c ", Character.toUpperCase(letter));
            for (int j = 0; j < map[0].length; j++) {
                out.print(" " + map[i][j] + " ");
            }
            out.println();
        }
    }
    //getters e setters
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
