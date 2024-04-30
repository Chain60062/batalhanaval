import domain.GameMap;

public class App {
    public static void main(String[] args) throws Exception {
        GameMap map = new GameMap();
        map.generateMap();
        map.printHiddenMap();
    }
}
