package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game current = new Game();
        current.start();
    }
}

class Game {
    char[][] positions; 

    Game () {
        positions = new char[][] { {'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'} };
    }

    Game (String input) {
        positions = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                positions[i][j] = input.charAt(i + j * 3);
    }

    void draw() {
        System.out.println("---------");
        for (int j = 0; j < 3; j++) {
            System.out.print("| ");
            for (int i = 0; i < 3; i++){
                System.out.print(this.positions[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    Boolean completedLine(char player){
        return
                this.positions[0][0] + this.positions[1][0] + this.positions[2][0] == player*3 ||
                        this.positions[0][1] + this.positions[1][1] + this.positions[2][1] == player*3 ||
                        this.positions[0][2] + this.positions[1][2] + this.positions[2][2] == player*3 ||

                        this.positions[0][0] + this.positions[0][1] + this.positions[0][2] == player*3 ||
                        this.positions[1][0] + this.positions[1][1] + this.positions[1][2] == player*3 ||
                        this.positions[2][0] + this.positions[2][1] + this.positions[2][2] == player*3 ||

                        this.positions[0][0] + this.positions[1][1] + this.positions[2][2] == player*3 ||
                        this.positions[0][2] + this.positions[1][1] + this.positions[2][0] == player*3 ;
    }

    int count(char player) {
        int count = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                count += this.positions[i][j] == player ? 1 : 0;
        return count;
    }

    int spacesLeft() {
        int count = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                count += this.positions[i][j] == '_' ? 1 : 0;
        return count;
    }

    String getState() {   // returns the state of the match
        if ( Math.abs(this.count('X') - this.count('O')) > 1 ||
                (this.completedLine('O') && this.completedLine('X')) )
            return "Impossible";

        if (this.spacesLeft() > 0 && !this.completedLine('O') && !this.completedLine('X'))
            return "Game not finished";

        if ( !(this.spacesLeft() > 0) && !this.completedLine('O') && !this.completedLine('X'))
            return "Draw";

        if (this.completedLine('X'))
            return "X wins";
        if (this.completedLine('O'))
            return "O wins";

        return "";
    }

    boolean isNumeric(String str) {    // copied straight out of stackoverflow, thanks @CraigTP
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    void makeMove(char player){  // reads two coordinates, the first is the row and the other is the column
        Scanner scn = new Scanner(System.in);
        String inp1;
        String inp2;
        while(true){    // repeats until it gets two numbers between 1 and 3
            System.out.print("Enter the coordinates: ");
            inp1 = scn.next();
            inp2 = scn.next();
            if (!(isNumeric(inp1) && isNumeric(inp2))) {
                System.out.println("You should enter numbers!");
                continue;
            } else if (! (Integer.parseInt(inp1) > 0) || ! (Integer.parseInt(inp1) < 4) || !(Integer.parseInt(inp2) > 0) || ! (Integer.parseInt(inp2) < 4)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if (this.positions[Integer.parseInt(inp2)-1][Integer.parseInt(inp1)-1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            } else break;
        }
        this.positions[Integer.parseInt(inp2)-1][Integer.parseInt(inp1)-1] = player;
    }

    void start(){
        this.draw();
        char player = 'X';
        while(this.getState() == "Game not finished"){  // this while starts itself after each turn and ends when the game does too
            this.makeMove(player);
            this.draw();
            player = player == 'X' ? 'O' : 'X';
        }
        System.out.println(this.getState());
    }
}