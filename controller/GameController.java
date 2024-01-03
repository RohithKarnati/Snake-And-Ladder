package LLD3_ProjectModule.SnakeAndLadder.controller;

import LLD3_ProjectModule.SnakeAndLadder.Exceptions.BoardSizeException;
import LLD3_ProjectModule.SnakeAndLadder.Exceptions.DuplicateSymbolsForPlayerException;
import LLD3_ProjectModule.SnakeAndLadder.Exceptions.PlayerCountMismatchException;
import LLD3_ProjectModule.SnakeAndLadder.models.Game;
import LLD3_ProjectModule.SnakeAndLadder.models.Player;

import java.util.List;

public class GameController {
    public Game startGame(int diceSize, List<Player> playerList, int dimensions) throws DuplicateSymbolsForPlayerException, BoardSizeException, PlayerCountMismatchException {
        // Creates a  Game and returns to main function
        return Game.getBuilder().setDiceSize(diceSize).setDimension(dimensions).setPlayerList(playerList).build();
    }
    public void printBoard(Game game){
        game.printBoard();
    }

    public void makeMove(Game game){
        game.makeMove();
    }
}
