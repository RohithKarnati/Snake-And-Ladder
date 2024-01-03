package LLD3_ProjectModule.SnakeAndLadder.models;

import LLD3_ProjectModule.SnakeAndLadder.powers.Pair;

public class Cell {
    private int row;
    private int col;
    private Player player;
    private PowerType powerType;
    private CellState cellState;
    private Pair pair;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.powerType = PowerType.EMPTY;
        this.cellState = CellState.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PowerType getPowerType() {
        return powerType;
    }

    public void setPowerType(PowerType powerType) {
        this.powerType = powerType;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }

    public void printCell() {
        System.out.print("|");
        if(getPowerType().equals(PowerType.LADDER)){
            System.out.print("# ");
        }
        else if(getPowerType().equals(PowerType.SNAKE)){
            System.out.print("~ ");
        }
        else{
            System.out.print("  ");
        }
        if(getCellState().equals(CellState.FILLED)){
            System.out.print(player.getSymbol());
        }
        else{
            System.out.print(" ");
        }
        System.out.print(" |");
    }
}
