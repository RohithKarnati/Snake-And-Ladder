package LLD3_ProjectModule.SnakeAndLadder.powers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Snake {
    private List<Pair> snakeCordinates;
    private int dimensions;
    public Snake(int dimensions) {
        this.dimensions = dimensions;
        snakeCordinates = new ArrayList<>();
    }
    public List<Pair> generate(Set<Integer> commonSet) {
        Random random = new Random();
        int size = random.nextInt(dimensions);
        while(size<=0){
            size = random.nextInt(dimensions);
        }
        int st = dimensions - 1;
        while (snakeCordinates.size() <= size) {
            int s = random.nextInt((st * st) + 1);
            while (commonSet.contains(s) || s < 10) {
                s = random.nextInt((st * st) + 1);
            }
            commonSet.add(s);
            int e = random.nextInt(s);
            while (commonSet.contains(e) || e==0) {
                e = random.nextInt(s);
            }
            commonSet.add(e);
            snakeCordinates.add(new Pair(s, e));
        }
        return snakeCordinates;
    }
}