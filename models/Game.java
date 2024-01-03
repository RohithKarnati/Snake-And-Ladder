package LLD3_ProjectModule.SnakeAndLadder.models;

import LLD3_ProjectModule.SnakeAndLadder.Exceptions.BoardSizeException;
import LLD3_ProjectModule.SnakeAndLadder.Exceptions.DuplicateSymbolsForPlayerException;
import LLD3_ProjectModule.SnakeAndLadder.Exceptions.PlayerCountMismatchException;
import LLD3_ProjectModule.SnakeAndLadder.powers.Pair;

import java.util.*;

public class Game {
    private List<Player> playerList;
    private List<Move> moves;
    private Board board;
    private GameState gameState;
    private int nextPlayerIndex;
    private Player winningPlayer;
    private Dice dice;
    private Scanner sc = new Scanner(System.in);
    private Game(int dimension, List<Player> playerList, int diceSize) {
        this.board = new Board(dimension);
        this.dice = new Dice(diceSize);
        this.playerList = playerList;
        nextPlayerIndex = 0;
        moves = new ArrayList<>();
        gameState = GameState.INPROGRESS;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() {
        Player player = playerList.get(nextPlayerIndex);
        System.out.println("It's "+player.getNameOfPlayer()+"'s turn please any key to continue");
        String ch = sc.nextLine();
        int diceValue = dice.rollDice(); // Get the dice number
        if(player.getPoint().getStart() == -1 && diceValue != dice.getSize()){ // Check whether it's the first move or not
            System.out.println(player.getNameOfPlayer()+" didn't got max dice number to start");
        }
        else{ // If user get max value initialize start with point 0,0
            if(player.getPoint().getStart() == -1){
                player.setPoint(new Pair(0,0));
            }
            System.out.println(player.getNameOfPlayer()+" got "+diceValue);
            while(checkCellState(player, diceValue)){
                System.out.println("Please enter any key to continue");
                String str = sc.nextLine();
                diceValue = dice.rollDice();
                System.out.println(player.getNameOfPlayer()+" got "+diceValue);
            }
            Pair coordinates = getPoint(diceValue, player);
            if(CheckCoinOutofBound(coordinates)){
                System.out.println("You can't go beyond board..... wait for your next turn");
                incrementIndex();
                return;
            }
            Cell cell = player.makeMove(board, coordinates); // Makes move
            Move move = new Move(cell, player);
            moves.add(move); // add to moves list
            checkPowers(cell, player);
            if(diceValue == dice.getSize()){ // As per rules if user gets max value Player will get a 2nd chance
                while (diceValue == dice.getSize()){ // will check until player gets max value same player
                    System.out.println("Ho hoo...! you got max value "+diceValue);
                    System.out.println("Also an another chance");
                    printBoard();
                    System.out.println("It's "+player.getNameOfPlayer()+"'s turn please any key to continue");
                    ch = sc.nextLine();
                    diceValue = dice.rollDice();
                    while(checkCellState(player, diceValue)){
                        System.out.println("Please enter any key to continue");
                        String str = sc.nextLine();
                        diceValue = dice.rollDice();
                    }
                    coordinates = getPoint(diceValue, player);
                    if(CheckCoinOutofBound(coordinates)){
                        System.out.println("You can't go beyond board..... wait for your next turn");
                        incrementIndex();
                        return;
                    }
                    System.out.println(player.getNameOfPlayer()+" got "+diceValue);
                    cell = player.makeMove(board, coordinates);
                    moves.add(new Move(cell, player));
                    checkPowers(cell, player);
                }
            }
            if(checkWinner(player)){ // Check Winner
                gameState = GameState.SUCCESS;
                winningPlayer = player;
            }
        }
        incrementIndex();
    }

    private boolean CheckCoinOutofBound(Pair coordinates) {
        if(coordinates.getStart() >= board.getDimensions()){
            return true;
        }
        return false;
    }

    private boolean checkCellState(Player player, int diceValue) {
        Pair coordinates = getPoint(diceValue, player);
        if(CheckCoinOutofBound(coordinates)){
            return false;
        }
        if((board.getCellList().get(coordinates.getStart()).get(coordinates.getEnd()).getCellState()).equals(CellState.FILLED)){
            System.out.println("Oh this Cell was occupied by other player");
            return true;
        }
        return false;
    }

    private Pair getPoint(int diceValue, Player player) {
        int s = player.getPoint().getStart();
        int e = player.getPoint().getEnd();
//        System.out.println(s+" "+e);
        int s1 = s;
        int e1 = e+diceValue;
        while (e1 > (board.getDimensions()-1) && (s!=0 || e!=0)){
            e1-=(board.getDimensions()-1);
            s1++;
        }
        if((s == 0 && e == 0) || ((e+diceValue) > (board.getDimensions()-1))){
            e1--;
        }
//        System.out.println(s1+" "+e1);
        return new Pair(s1, e1);
    }
    private void incrementIndex() {
        nextPlayerIndex++;
        nextPlayerIndex = nextPlayerIndex%(playerList.size());
    }

    private void checkPowers(Cell cell, Player player) {
        if(cell.getPowerType().equals(PowerType.SNAKE)){
            cell.setCellState(CellState.EMPTY);
            cell.setPlayer(null);
            int dimensions = board.getDimensions();
            Pair pair = cell.getPair();
            int i = (pair.getEnd())/dimensions;
            int j = (pair.getEnd())%dimensions;
            System.out.println("Oh no...! You've encountered snake :(");
            System.out.println("You will be moves to snake tail point - "+i+" "+j);
            Cell cell1 = (board.getCellList()).get(i).get(j);
            player.setPoint(new Pair((i), (j)));
            cell1.setCellState(CellState.FILLED);
            cell1.setPlayer(player);
        }
        if(cell.getPowerType().equals(PowerType.LADDER)){
            cell.setCellState(CellState.EMPTY);
            cell.setPlayer(null);
            Pair pair = cell.getPair();
            int i = (pair.getEnd())/(board.getDimensions());
            int j = (pair.getEnd())%(board.getDimensions());
            System.out.println("Hu hu :) You are at bottom of Ladder");
            System.out.println("You will be moves to top of ladder point is - "+i+" "+j);
            Cell cell1 = (board.getCellList()).get(i).get(j);
            player.setPoint(new Pair((i), (j)));
            cell1.setCellState(CellState.FILLED);
            cell1.setPlayer(player);
        }
    }

    private boolean checkWinner(Player player) {
        int i = player.getPoint().getStart();
        int j = player.getPoint().getEnd();
        if(i == j && j == (board.getDimensions()-1)){
            return true;
        }
        return false;
    }

    public GameState getGameState() {
        return gameState;
    }


    public Player getWinningPlayer() {
        return winningPlayer;
    }

    public static class Builder{
        private List<Player> playerList;
        private int dimension;
        private int diceSize;

        private Builder() {
        }

        public List<Player> getPlayerList() {
            return playerList;
        }

        public Builder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public int getDiceSize() {
            return diceSize;
        }

        public Builder setDiceSize(int diceSize) {
            this.diceSize = diceSize;
            return this;
        }



        public Game build() throws BoardSizeException, PlayerCountMismatchException, DuplicateSymbolsForPlayerException {
            // dimensions should be greater than 6
            // players count should be greater than 2
            // No two same symbols
            validateDimensions();
            validatePlayerCount();
            ValidUniqueSymbolsForPlayers();
            return new Game(dimension, playerList, diceSize);
        }

        private void validatePlayerCount() throws PlayerCountMismatchException {
            if(playerList.size() < 2 || playerList.size() > dimension){
                throw new PlayerCountMismatchException();
            }
        }

        private void validateDimensions() throws BoardSizeException {
            if(dimension <= 5){
                throw new BoardSizeException();
            }
        }
        public void ValidUniqueSymbolsForPlayers() throws DuplicateSymbolsForPlayerException {
            Set<Character> duplicateSymbol = new HashSet<>();
            for(Player player: playerList){
                if(duplicateSymbol.contains(player.getSymbol())){
                    throw new DuplicateSymbolsForPlayerException();
                }
                duplicateSymbol.add(player.getSymbol());
            }
        }
    }
}