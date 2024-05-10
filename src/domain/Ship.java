package domain;

import java.util.Random;

/*classes abstratas nao podem ser instanciadas, servem somente para heranca, porem podem ter 
implementacoes padrao para metodos, diferente das interfaces*/
public abstract class Ship {
    protected char symbol;
    protected String name;
    protected int size;
    protected boolean isSunken;
    protected Coordinate[] coordinates;
    private static final Random random = new Random();

    public void placeShipOnMap(GameMap map) {
        Coordinate coordinate = new Coordinate();
        boolean isVertical;// auto explicativo
        do {
            isVertical = random.nextBoolean();
            // coordenadas aleatorias
            coordinate.setX(random.nextInt(map.getMap().length));
            coordinate.setY(random.nextInt(map.getMap()[0].length));
        } while (!isValidPosition(coordinate, map, isVertical));

        placeShipOnCoordinates(coordinate, map, isVertical);
    }

    private void placeShipOnCoordinates(Coordinate coordinate, GameMap map, boolean isVertical) {
        for (int i = 0; i < size; i++) {
            if (isVertical) {
                map.getMap()[coordinate.getX() + i][coordinate.getY()] = this.getSymbol();
                this.coordinates[i] = new Coordinate(coordinate.getX() + i, coordinate.getY());// armazenar coordenadas
            } else {
                map.getMap()[coordinate.getX()][coordinate.getY() + i] = this.getSymbol();
                this.coordinates[i] = new Coordinate(coordinate.getX(), coordinate.getY() + i);// armazenar coordenadas
            }
        }
    }

    // checar se posicao e valida
    private boolean isValidPosition(Coordinate coordinate, GameMap map, boolean isVertical) {
        if (isVertical) {
            // checar se o tamanho ira sair do mapa verticalmente
            if (coordinate.getX() + size > map.getMap().length)
                return false;
        } else {
            // checar se o tamanho ira sair do mapa horizontalmente
            if (coordinate.getY() + size > map.getMap()[0].length)
                return false;
        }
        // checar se navios estão proximos demais
        if (areShipsTooClose(coordinate, map, isVertical))
            return false;

        return true;
    }

    // checa se navios estao pertos demais(atualmente nao funciona tao bem)
    private boolean areShipsTooClose(Coordinate coordinate, GameMap map, boolean isVertical) {
        for (int i = Math.max(0, coordinate.getX() - 1); i < Math.min(map.getMap().length,
                coordinate.getX() + size + 1); i++) {
            for (int j = Math.max(0, coordinate.getY() - 1); j < Math.min(map.getMap()[0].length,
                    coordinate.getY() + (isVertical ? 1 : size + 1)); j++) {

                if (map.getMap()[i][j] != 'v') {
                    return true;
                }
            }
        }
        return false;
    }

    // getters e setters
    protected void sinkShip() {
        this.isSunken = true;
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

    public boolean getIsSunken() {
        return isSunken;
    }

    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSunken(boolean isSunken) {
        this.isSunken = isSunken;
    }
}
