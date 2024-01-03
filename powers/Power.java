package LLD3_ProjectModule.SnakeAndLadder.powers;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Power {
    private Set<Integer> commonSet;
    private List<Pair> snakeList;
    private List<Pair> ladderList;
    private int dimensions;

    public Power(int dimensions) {
        this.dimensions = dimensions;
        commonSet = new HashSet<>();
    }

    public List<Pair> getSnakeCoordinates() {
        Snake snake = new Snake(dimensions);
        return snake.generate(commonSet);
    }

    public List<Pair> getLadderCoordinates() {
        Ladder ladder = new Ladder(dimensions);
        return ladder.generate(commonSet);
    }
}
