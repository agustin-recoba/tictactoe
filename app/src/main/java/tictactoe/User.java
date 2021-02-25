package tictactoe;

import java.util.Scanner;

public class User extends Player {

    User(char symbol) {
        super(symbol);
    }

    boolean isNumeric(String str) {    // copied straight out of stackoverflow, thanks @CraigTP
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void makeMove(Game game){  // reads two coordinates, the first is the row and the other is the column
        Scanner scn = new Scanner(System.in);
        String inp1;
        String inp2;
        while(true){    // repeats until it gets two numbers between 1 and 3
            System.out.print("Enter the coordinates: ");
            inp1 = scn.next();
            if (!isNumeric(inp1)) {
                System.out.println("You should enter numbers!");
                continue;
            }
            inp2 = scn.next();
            if (!isNumeric(inp2)){
                System.out.println("You should enter numbers!");
                continue;
            } else if (! (Integer.parseInt(inp1) > 0) || ! (Integer.parseInt(inp1) < 4) ||
                    ! (Integer.parseInt(inp2) > 0) || ! (Integer.parseInt(inp2) < 4)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if (game.board[Integer.parseInt(inp2)-1][Integer.parseInt(inp1)-1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            } else break;
        }
        game.board[Integer.parseInt(inp2)-1][Integer.parseInt(inp1)-1] = this.getSymbol();
    }

}
