package LLD3_ProjectModule.SnakeAndLadder.powers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Ladder{
    private int start;
    private int end;
    private int dimensions;
    private List<Pair> ladderCordinates;

    public Ladder(int dimensions) {
        this.dimensions = dimensions;
        ladderCordinates = new ArrayList<>();
    }

    public List<Pair> generate(Set<Integer> commonSet) {
        Random random = new Random();
        int size = random.nextInt(dimensions);
        while(size<=0){
            size = random.nextInt(dimensions);
        }
        int st = dimensions-1;
        while (ladderCordinates.size() <= size){
            int s = random.nextInt((st * st) + 1);
            while(commonSet.contains(s) || s==0){
                s = random.nextInt((st * st) + 1);
            }
            commonSet.add(s);
            int e = random.nextInt((dimensions * dimensions) + 1);
            while(commonSet.contains(e) || (e<((s/10)*10)) || e==0){
                e = random.nextInt((dimensions * dimensions) + 1);
            }
            commonSet.add(e);
            ladderCordinates.add(new Pair(s, e));
        }
        return ladderCordinates;
    }
}
