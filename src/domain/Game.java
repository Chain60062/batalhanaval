package domain;

import static java.lang.System.out;//para usar metodos do out diretamente(ex: out.printf) sem ter de referenciar System.out

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

            out.println("Vez de " + currentPlayer.getName());

            map.printMap(true);// mostrar mapa com navios ocultos
            //map.printMap(false);

            out.println("Digite uma posição: ");

            out.println("Linha(a,A,b,B,c,C...): ");
            char x = verifyCharInput();
            out.println("Coluna(1,2,3...): ");
            int y = verifyNumberInput();

            currentPlayer.attack(letterToNumber(x), y - 1, currentPlayer.equals(firstPlayer), this);
            out.println("Mapa atualizado");
            checkIfSomeoneWon();
        }
    }

    private char verifyCharInput() {
        while (true) {
            String x = scanner.next();
            if (x.matches("[a-tA-T]")) {
                System.out.println("Você escolheu " + Character.toLowerCase(x.charAt(0)));
                return Character.toLowerCase(x.charAt(0));
            } else {
                System.out.println("Posição inválida, tem de ser de A até T");
            }
        }
    }

    private int verifyNumberInput() {
        while (true) {
            if (scanner.hasNextInt()) { // Check if the next input is an integer
                int input = scanner.nextInt();
                if (input >= 1 && input <= 20) { // Check if the input is within the range
                    return input;
                } else {
                    System.out.println("Posição inválida, tem se de 1 a 20.");
                }
            } else {
                System.out.println("Posição não é um número.");
                scanner.next(); // Consumir input invalido
            }
        }

    }

    private int letterToNumber(char letter) {
        return letter - 'a';
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
        out.println("---------------------------------");
        out.println(player.getName() + " afundou: ");
        for (Ship ship : player.getShipsSunken()) {
            out.println(1 + " " + ship.getName());
        }
        out.println("---------------------------------");
    }

    // getters e setters
    public char[][] getMap() {
        return map.getMap();
    }

    public char[][] getDisplayMap() {
        return map.getDisplayMap();
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
