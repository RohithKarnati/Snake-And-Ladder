package LLD3_ProjectModule.SnakeAndLadder.models;

import LLD3_ProjectModule.SnakeAndLadder.powers.Pair;

import java.util.Scanner;

public class Player {
    private String nameOfPlayer;
    private int idOfPlayer;
    private char symbol;
    private Pair point;
    private Scanner sc = new Scanner(System.in);

    public Player(String nameOfPlayer, int idOfPlayer, char symbol, Pair point) {
        this.nameOfPlayer = nameOfPlayer;
        this.idOfPlayer = idOfPlayer;
        this.symbol = symbol;
        this.point = point;
    }
    public Cell makeMove(Board board, Pair point) {
        int s1 = point.getStart();
        int e1 = point.getEnd();
        if((getPoint().getStart()) != -1 || (getPoint().getEnd()) != -1){
            Cell cell = board.getCellList().get(getPoint().getStart()).get(getPoint().getEnd());
            cell.setCellState(CellState.EMPTY);
            cell.setPlayer(null);
        }
        Cell cell = board.getCellList().get(s1).get(e1);
        setPoint(new Pair(s1, e1));
        cell.setPlayer(this);
        cell.setCellState(CellState.FILLED);
        return cell;
    }
    public String getNameOfPlayer() {
        return nameOfPlayer;
    }

    public void setNameOfPlayer(String nameOfPlayer) {
        this.nameOfPlayer = nameOfPlayer;
    }

    public int getIdOfPlayer() {
        return idOfPlayer;
    }

    public void setIdOfPlayer(int idOfPlayer) {
        this.idOfPlayer = idOfPlayer;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    public Pair getPoint() {
        return point;
    }

    public void setPoint(Pair point) {
        this.point = point;
    }

}
