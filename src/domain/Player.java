package domain;

import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class Player {
    private String name;
    private int points;
    private List<Ship> sinkedShips = new ArrayList<>();

    private void attack(int row, int col, GameMap map) {
        char target = map.getMap()[row][col];
        if (target == 'v') {
            out.println("Míssel caiu no oceano");
        } else {
            out.print("Jogador " + this + " ");
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
    @Override
    public String toString() {
        return name.trim();
    }
}
