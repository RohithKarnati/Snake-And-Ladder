package LLD3_ProjectModule.SnakeAndLadder.models;

import java.util.Random;

public class Dice {
    private int size;

    public Dice(int size) {
        this.size = size;
    }

    public int rollDice(){
        Random random = new Random();
        int diceValue = random.nextInt(size+1);
        while(diceValue == 0){
            diceValue = random.nextInt(size+1);
        }
        return diceValue;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
