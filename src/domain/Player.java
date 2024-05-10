package domain;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;//nome do jogador
    private List<Ship> shipsSunken = new ArrayList<>();//navios naufragados por este jogador

    public Player(String name) {
        this.name = name;
    }

    public void attack(int x, int y, boolean isFirstPlayer, Game game) {
        var coordinate = new Coordinate(x, y);//classe coordenada para diminuir argumentos e facilitar compreensao
        char target = game.getMap()[x][y];//letra do mapa, v para vazio

        out.print("Jogador " + this + " ");//this se refere a instancia de jogador que chamou attack
        switch (target) {
            case 'A':
                out.println("acertou um porta aviões");
                sinkShip(game, coordinate, isFirstPlayer);
                break;
            case 'D':
                out.println("acertou um destroyer");
                sinkShip(game, coordinate, isFirstPlayer);
                break;
            case 'F':
                out.println("acertou uma fragata");
                sinkShip(game, coordinate, isFirstPlayer);
                break;
            case 'B':
                out.println("acertou um bote");
                sinkShip(game, coordinate, isFirstPlayer);
                break;
            case 'S':
                out.println("acertou um submarino");
                sinkShip(game, coordinate, isFirstPlayer);
                break;
            default:
                out.println("Míssel caiu no oceano");
                break;
        }
    }

    // metodo principal para afundar navios
    private void sinkShip(Game game, Coordinate coordinate, boolean isFirstPlayer) {
        // checar a coordenada se as coordenadas recebidas são de algum navio existente
        int playerX = coordinate.getX();
        int playerY = coordinate.getY();

        checkEachShipCoordinates(game, isFirstPlayer, playerX, playerY);
    }

    private void checkEachShipCoordinates(Game game, boolean isFirstPlayer, int x, int y) {
        for (Ship currentShip : game.getShipsPlaced()) {// pegar lista de navios do jogo

            /*
             * para cada navio, pegar lista de coordenadas, ou seja, a posicao exata do
             * navio e comparar com as coordenadas do jogador para ver se batem
             */
            for (int j = 0; j < currentShip.getCoordinates().length; j++) {
                // pegar coordenadas
                int shipX = currentShip.getCoordinates()[j].getX();
                int shipY = currentShip.getCoordinates()[j].getY();
                // checar se navio atual possui a coordenada escolhida pelo jogador
                if (shipX == x && shipY == y) {
                    // remover navio do mapa
                    removeShipFromMapAndIncreasePoints(currentShip, game, isFirstPlayer);
                    return;
                }
            }
        }
    }

    private void removeShipFromMapAndIncreasePoints(Ship ship, Game game, boolean isFirstPlayer) {
        for (Coordinate coordinate : ship.coordinates) {// pegar coordenada do navio
            if (isFirstPlayer) {
                game.getMap()[coordinate.getX()][coordinate.getY()] = 'X';
                game.getDisplayMap()[coordinate.getX()][coordinate.getY()] = 'X';
            } else {
                game.getMap()[coordinate.getX()][coordinate.getY()] = 'Y';
                game.getDisplayMap()[coordinate.getX()][coordinate.getY()] = 'Y';
            }
        }
        ship.sinkShip();// marcar navio como afundado (por enquanto não faz nada)
        shipsSunken.add(ship);// adicionar navio a lista de navios afundados por este jogador
    }

    // getters e setters
    public boolean hasWon() {
        return shipsSunken.size() >= 5;
    }

    public String getName() {
        return name;
    }

    public List<Ship> getShipsSunken() {
        return shipsSunken;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name.trim();
    }
}
