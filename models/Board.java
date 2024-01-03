package LLD3_ProjectModule.SnakeAndLadder.models;

import LLD3_ProjectModule.SnakeAndLadder.powers.Pair;
import LLD3_ProjectModule.SnakeAndLadder.powers.Power;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> cellList;
    private int dimensions;
    private List<Pair> snakesList;
    private List<Pair> laddersList;

    public Board(int dimensions) {
        this.dimensions = dimensions;
        cellList = new ArrayList<>();
        Power power = new Power(dimensions);
        snakesList = power.getSnakeCoordinates();
        laddersList = power.getLadderCoordinates();
        for(int i=0;i<dimensions;i++){
            cellList.add(new ArrayList<>());
            for(int j=0;j<dimensions;j++){
                cellList.get(i).add(new Cell(i, j));
            }
        }
        addSnakestoBoard();
        addLadderstoBoard();
    }

    private void addLadderstoBoard() {
        for(Pair pair: laddersList){
            int s = (pair.getStart());
            Cell cell = cellList.get(s/dimensions).get(s%dimensions);
            cell.setPowerType(PowerType.LADDER);
            cell.setPair(pair);
        }
    }

    private void addSnakestoBoard() {
        for(Pair pair: snakesList){
            int s = (pair.getStart());
//            int e = (pair.getEnd())%dimensions;
            Cell cell = cellList.get(s/dimensions).get(s%dimensions);
            cell.setPowerType(PowerType.SNAKE);
            cell.setPair(pair);
        }
    }

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public List<List<Cell>> getCellList() {
        return cellList;
    }

    public void setCellList(List<List<Cell>> cellList) {
        this.cellList = cellList;
    }

    public void printBoard() {
        for(int i=0;i<dimensions;i++){
//            System.out.print(i+" ");
            for(int j=0;j<dimensions;j++){
                Cell cell = cellList.get(i).get(j);
                cell.printCell();
            }
            System.out.println();
        }
        System.out.println("'#' this Indicates Ladder");
        System.out.println("'~' this Indicates Snake");
    }
}
