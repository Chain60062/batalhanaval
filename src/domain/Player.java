package domain;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Ship> shipsSunken = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void attack(int x, int y, Game game) {
        var coordinate = new Coordinate(x, y);
        char target = game.getMap()[x][y];
        System.out.println("TARGET: " + target);
        out.print("Jogador " + this + " ");
        switch (target) {
            case 'A':
                out.println("acertou um porta navios");
                sinkShip(game, coordinate, false);
                break;
            case 'D':
                out.println("acertou um destroyer");
                sinkShip(game, coordinate, false);
                break;
            case 'F':
                out.println("acertou uma fragata");
                sinkShip(game, coordinate, false);
                break;
            case 'B':
                out.println("acertou um bote");
                sinkShip(game, coordinate, false);
                break;
            default:
                out.println("Míssel caiu no oceano");
                break;
        }
    }

    private void sinkShip(Game game, Coordinate coordinate, boolean isFirstPlayer) {
        // checar a coordenada se as coordenadas recebidas são de algum navio existente
        int playerX = coordinate.getX();
        int playerY = coordinate.getY();

        System.out.println(game.getShipsPlaced().size());
        
        for (Ship currentShip : game.getShipsPlaced()) {
            for (int j = 0; j < currentShip.getCoordinates().length; j++) {
                System.out.println(currentShip.getCoordinates()[j]);
                int shipX = currentShip.getCoordinates()[j].getX();
                int shipY = currentShip.getCoordinates()[j].getY();
                // checar se navio atual possuem a coordenada escolhida
                if (shipX == playerX && shipY == playerY) {
                    removeShipFromMapAndIncreasePoints(currentShip, game, isFirstPlayer);
                    return;
                }
            }
        }
    }

    private void removeShipFromMapAndIncreasePoints(Ship ship, Game game, boolean isFirstPlayer) {
        for (Coordinate coordinate : ship.coordinates) {
            if (isFirstPlayer) {
                game.getMap()[coordinate.getX()][coordinate.getY()] = 'X';
            } else {
                game.getMap()[coordinate.getX()][coordinate.getY()] = 'Y';
            }
            ship.sinkShip();
            shipsSunken.add(ship);
        }
    }

    public boolean hasWon() {
        return shipsSunken.size() >= 5;
    }

    @Override
    public String toString() {
        return name.trim();
    }
}
