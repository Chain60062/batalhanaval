import domain.Game;
import domain.Player;

public class App {
    public static void main(String[] args) throws Exception {
        var firstPlayer = new Player("Vinicius");
        var secondPlayer = new Player("Ronaldo");
        
        Game game = new Game(firstPlayer, secondPlayer);
        game.initGame();
    }
}
