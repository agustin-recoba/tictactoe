package tictactoe;

import java.util.Random;
import java.util.Scanner;
import tictactoe.*;

public class Game {

    char[][] board;

    Game () {
        board = new char[][] { {'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'} };
    }

    Game (String input) {
        board = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = input.charAt(i + j * 3);
    }

    Game (Game copy){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = copy.board[i][j];
            }
        }
    }

    void drawBoard() {
        System.out.println("---------");
        for (int j = 0; j < 3; j++) {
            System.out.print("| ");
            for (int i = 0; i < 3; i++){
                System.out.print(this.board[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    String getString(){
        String out = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                out = out + this.board[j][i];
            }
        }
        return out;
    }

    Boolean hasWon(char player){
        return
            this.board[0][0] + this.board[1][0] + this.board[2][0] == player*3 ||
            this.board[0][1] + this.board[1][1] + this.board[2][1] == player*3 ||
            this.board[0][2] + this.board[1][2] + this.board[2][2] == player*3 ||

            this.board[0][0] + this.board[0][1] + this.board[0][2] == player*3 ||
            this.board[1][0] + this.board[1][1] + this.board[1][2] == player*3 ||
            this.board[2][0] + this.board[2][1] + this.board[2][2] == player*3 ||

            this.board[0][0] + this.board[1][1] + this.board[2][2] == player*3 ||
            this.board[0][2] + this.board[1][1] + this.board[2][0] == player*3 ;
    }

    int countSymbols(char player) {
        int count = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                count += this.board[i][j] == player ? 1 : 0;
        return count;
    }

    int spacesLeft() {
        int count = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                count += this.board[i][j] == '_' ? 1 : 0;
        return count;
    }

    String getState() {   // returns the state of the match
        if ( Math.abs(this.countSymbols('X') - this.countSymbols('O')) > 1 ||
                (this.hasWon('O') && this.hasWon('X')) )
            return "Impossible";

        if (this.spacesLeft() > 0 && !this.hasWon('O') && !this.hasWon('X'))
            return "Game not finished";

        if ( !(this.spacesLeft() > 0) && !this.hasWon('O') && !this.hasWon('X'))
            return "Draw";

        if (this.hasWon('X'))
            return "X wins";
        if (this.hasWon('O'))
            return "O wins";

        return "";
    }

    char getEndGame() {   // returns the state of the match
        if (this.hasWon('X'))
            return 'X';
        if (this.hasWon('O'))
            return 'O';
        if ( this.spacesLeft() == 0 )
            return 'D';
        return '_';
    }
}
