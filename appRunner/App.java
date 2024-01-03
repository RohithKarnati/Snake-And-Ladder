package LLD3_ProjectModule.SnakeAndLadder.appRunner;

import LLD3_ProjectModule.SnakeAndLadder.Exceptions.BoardSizeException;
import LLD3_ProjectModule.SnakeAndLadder.Exceptions.DuplicateSymbolsForPlayerException;
import LLD3_ProjectModule.SnakeAndLadder.Exceptions.PlayerCountMismatchException;
import LLD3_ProjectModule.SnakeAndLadder.controller.GameController;
import LLD3_ProjectModule.SnakeAndLadder.models.Game;
import LLD3_ProjectModule.SnakeAndLadder.models.GameState;
import LLD3_ProjectModule.SnakeAndLadder.models.Player;
import LLD3_ProjectModule.SnakeAndLadder.powers.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws DuplicateSymbolsForPlayerException, BoardSizeException, PlayerCountMismatchException {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController(); // Initializing Game controller
        System.out.println("Please enter Size of board");
        int size = sc.nextInt();
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Rohith", 1, 'R', new Pair(-1, -1)));
        playerList.add(new Player("Mani Deep", 2, 'M', new Pair(-1, -1)));
//        playerList.add(new Player("Nithish", 3, 'N', new Pair(-1, -1)));
//        playerList.add(new Player("Pranay", 4, 'P', new Pair(-1, -1)));
        Game game = gameController.startGame(6, playerList, size);
        Player player = null;
        while(game.getGameState().equals(GameState.INPROGRESS)) {
            game.printBoard();
            game.makeMove();
        }
        System.out.println("Hurry "+game.getWinningPlayer().getNameOfPlayer()+" won the game");
    }
}