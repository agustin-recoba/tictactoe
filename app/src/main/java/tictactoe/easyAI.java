package tictactoe;

import java.util.Random;

public class easyAI extends Player {
    easyAI(char symbol) {
        super(symbol);
    }

    void makeMove(Game game){
        Random rand = new Random();
        int x = rand.nextInt(3) + 1;
        int y = rand.nextInt(3) + 1;
        while (game.board[y-1][x-1] != '_') {
            x = rand.nextInt(3) + 1;
            y = rand.nextInt(3) + 1;
        }
        game.board[y-1][x-1] = this.getSymbol();
    }

}
