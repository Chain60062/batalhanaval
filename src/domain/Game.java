package domain;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

public class Game {
    GameMap map = new GameMap();
    List<Player> players = new ArrayList<>();
    Player currentPlayer;

    private void attack(int row, int col) {
        char target = map.getMap()[row][col];
        if (target == 'v') {
            out.println("Míssel caiu no oceano");
        } else {
            out.print("Jogador " + currentPlayer + " ");
            switch (target) {
                case 'A':
                    out.println("acertou um porta navios");
                    break;
                case 'D':
                    out.println("acertou um destroyer");
                    break;
                case 'F':
                    out.println("acertou uma fragata");
                    break;
                case 'B':
                    out.println("acertou um bote");
            }
        }
    }

    private void sinkShip() {

    }
}
