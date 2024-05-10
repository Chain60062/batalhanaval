package domain;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;// nome do jogador
    private List<Ship> shipsSunken = new ArrayList<>();// navios naufragados por este jogador

    public Player(String name) {
        this.name = name;
    }

    public void attack(int x, int y, boolean isFirstPlayer, Game game) {
        var coordinate = new Coordinate(x, y);// classe coordenada para diminuir argumentos e facilitar compreensao
        char target = game.getMap()[x][y];// letra do mapa, v para vazio

        out.print("Jogador " + this + ": ");// this se refere a instancia de jogador que chamou attack
        switch (target) {
            case 'A':
                out.println("Acertou um porta aviões");
                sinkShip(game, coordinate, isFirstPlayer);
                break;
            case 'D':
                out.println("Acertou um destroyer");
                sinkShip(game, coordinate, isFirstPlayer);
                break;
            case 'F':
                out.println("Acertou uma fragata");
                sinkShip(game, coordinate, isFirstPlayer);
                break;
            case 'B':
                out.println("Acertou um bote");
                sinkShip(game, coordinate, isFirstPlayer);
                break;
            case 'S':
                out.println("Acertou um submarino");
                sinkShip(game, coordinate, isFirstPlayer);
                break;
            default:
                out.println("Míssel caiu no oceano");
                break;
        }
    }

    private void sinkShip(Game game, Coordinate coordinate, boolean isFirstPlayer) {
        for (Ship currentShip : game.getShipsPlaced()) {// pegar lista de navios do jogo

            /*
             * para cada navio, pegar lista de coordenadas, ou seja, a posicao exata do
             * navio e comparar com as coordenadas do jogador para ver se batem
             */
            for (int j = 0; j < currentShip.getCoordinates().length; j++) {
                // pegar coordenadas
                Coordinate shipCoordinates = currentShip.getCoordinates()[j];
                // checar se navio atual possui a coordenada escolhida pelo jogador
                if (shipCoordinates.equals(coordinate)) {
                    // remover navio do mapa
                    removeShipFromMapAndIncreasePoints(currentShip, game, isFirstPlayer);
                    return;
                }
            }
        }
    }

    private void removeShipFromMapAndIncreasePoints(Ship ship, Game game, boolean isFirstPlayer) {
        for (Coordinate coordinate : ship.coordinates) {// pegar coordenada do navio
            int x = coordinate.getX();
            int y = coordinate.getY();

            if (isFirstPlayer) {
                game.getMap()[x][y] = 'X';
                game.getDisplayMap()[x][y] = 'X';
            } else {
                game.getMap()[x][y] = 'Y';
                game.getDisplayMap()[x][y] = 'Y';
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
