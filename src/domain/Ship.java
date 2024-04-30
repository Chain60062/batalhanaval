package domain;

import java.util.Random;

public abstract class Ship {
    protected char symbol;
    protected int size;
    protected boolean isDestroyed;
    protected Coordinate[] coordinates = new Coordinate[size];
    private static final Random random = new Random();

    private void placeShipOnMap(GameMap map) {
        // decidir posicao aleatoriamente
        boolean isVertical = random.nextBoolean();
        Coordinate coordinate = new Coordinate();
        
        do {
            coordinate.setX(random.nextInt(map.getMap().length));// 0 - 16
            coordinate.setY(random.nextInt(map.getMap()[0].length));// 0 - 16
        } while (!isValidPosition(coordinate, this.getSize(), map, isVertical));
        
        placeShipOnCoordinates(coordinate, map, isVertical);
    }

    private void placeShipOnCoordinates(Coordinate coordinate, GameMap map, boolean isVertical) {
        for (int i = 0; i < this.getSize(); i++) {
            if (isVertical) {
                map.getMap()[coordinate.getX()+ i][coordinate.getY()] = this.getSymbol();
                this.coordinates[i] = new Coordinate(coordinate.getX() + i, coordinate.getY());// armazenar coordenadas do navio
            } else {
                map.getMap()[coordinate.getX()][coordinate.getY() + i] = this.getSymbol();
            }
        }
    }

    private boolean isValidPosition(Coordinate coordinate, int size, GameMap map, boolean isVertical) {
        if (isVertical) {
            if (coordinate.getX() + size > map.getMap().length)
                return false; // checar se o tamanho ira sair do mapa verticalmente
        } else {
            if (coordinate.getY() + size > map.getMap()[0].length)
                return false; // checar se o tamanho ira sair do mapa horizontalmente
        }
        if (areShipsTooClose(coordinate, size, map, isVertical))
            return false;

        return true;
    }

    private boolean areShipsTooClose(Coordinate coordinate, GameMap map,  boolean isVertical) {
        for (int i = Math.max(0, coordinate.getX() - 1); i < Math.min(map.getMap().length, coordinate.getX() + this.getSize() + 1); i++) {
            for (int j = Math.max(0, coordinate.getY() - 1); j < Math.min(map.getMap()[0].length,
                    coordinate.getY() + (isVertical ? 1 : this.getSize() + 1)); j++) {
                if (map.getMap()[i][j] != 'v')
                    return true;
            }
        }
        return false;
    }

    protected void destroyShip() {
        this.isDestroyed = true;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }
}
