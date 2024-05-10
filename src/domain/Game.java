package domain;

import static java.lang.System.out;

import java.util.List;
import java.util.Scanner;

public class Game {
    private GameMap map = new GameMap();
    private List<Ship> shipsPlaced = map.generateMapAndGetShipsList();
    private Player firstPlayer;
    private Player secondPlayer;
    private Player currentPlayer;
    private boolean endGame = false;
    private static final Scanner scanner = new Scanner(System.in);

    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        currentPlayer = firstPlayer;
    }

    public void initGame() {
        while (!endGame) {
            currentPlayer = currentPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;

            System.out.println("Vez de " + currentPlayer.getName());
            
            map.printHiddenMap();

            out.println("Digite uma posição: ");
            out.println("Linha(Eixo X): ");
            int x = scanner.nextInt();
            out.println("Coluna(Eixo Y): ");
            int y = scanner.nextInt();

            currentPlayer.attack(x - 1, y - 1, currentPlayer.equals(firstPlayer), this);
            
            out.println("Mapa atualizado");
            checkIfSomeoneWon();
        }
    }

    private void checkIfSomeoneWon() {
        if (firstPlayer.hasWon()) {
            out.println(firstPlayer.getName() + " venceu.");
            endGame = true;
            printSunkenShips(firstPlayer);
        }
        if (secondPlayer.hasWon()) {
            endGame = true;
            out.println(secondPlayer.getName() + " venceu.");
            printSunkenShips(secondPlayer);
        }
    }

    private void printSunkenShips(Player player) {
        System.out.println(player.getName() + " afundou: ");
        for (Ship ship : player.getShipsSunken()) {
            System.out.println(ship.getName());
        }
    }

    public void printShips() {
        for (Ship ship : shipsPlaced) {
            out.println(ship.getSymbol());
        }
    }

    public char[][] getMap() {
        return map.getMap();
    }

    public List<Ship> getShipsPlaced() {
        return shipsPlaced;
    }

    public void setShipsPlaced(List<Ship> shipsPlaced) {
        this.shipsPlaced = shipsPlaced;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
