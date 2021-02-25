package tictactoe;

public class Menu {

    public static void main(String[] args) {
        java.util.Scanner scn = new java.util.Scanner(System.in);
        int codePlayer1 = -1;
        int codePlayer2 = -1;

        while (true) {
            System.out.print("Input command: ");
            String input = scn.nextLine();

            if (input.equals("exit")) break;

            String[] arr = stringToArray(input);

            if (arr[0].equals("exit")) break;
            if (arr[0].equals("start")) {
                switch(arr[1]){
                    case "easy" : codePlayer1 = 0; break;
                    case "medium" : codePlayer1 = 1; break;
                    case "hard" : codePlayer1 = 2; break;
                    case "user" : codePlayer1 = 3; break;
                    default :
                        System.out.println("Bad parameters!"); continue;
                }
                switch(arr[2]){
                    case "easy" : codePlayer2 = 0; break;
                    case "medium" : codePlayer2 = 1; break;
                    case "hard" : codePlayer2 = 2; break;
                    case "user" : codePlayer2 = 3; break;
                    default :
                        System.out.println("Bad parameters!"); continue;
                }
                if (codePlayer1 != -1 && codePlayer2 != -1) break;
            } else   System.out.println("Bad parameters!");
        }
        start(codePlayer1, codePlayer2);
    }

    static String[] stringToArray(String input){
        String[] arr = {"", "", ""};
        int lastSpace = 0;
        input += " ";
        for (int i = 0; i<input.length(); i++ ){
            if (input.charAt(i)==' ') {
                for (int j = 0; j<3; j++) {
                    if (arr[j] == "") {
                        arr[j] = input.substring(lastSpace, i);
                        break;
                    }
                }
                lastSpace = i+1;
            }
        }
        return arr;
    }

    static void start(int level1, int level2) {
        Game game = new Game();
        char player = 'X';
        int lvl = level1;
        game.drawBoard();
        while(game.getState() == "Game not finished"){  // this while starts itself after each turn and ends when the game does too
            move(game, lvl, player);
            messageAI(lvl);
            game.drawBoard();

            player = player == 'X' ? 'O' : 'X';
            lvl = lvl == level1 ? level2 : level1;
        }
        System.out.println(game.getState());
    }

    static void move(Game game, int level, char symbol){
        Player player;
        switch (level) {
            case 0 :
                player = new easyAI(symbol);
                player.makeMove(game);
                break;
            case 1 :
                player = new mediumAI(symbol);
                player.makeMove(game);
                break;
            case 2 :
                player = new hardAI(symbol);
                player.makeMove(game);
                break;
            case 3 :
                player = new User(symbol);
                player.makeMove(game);
                break;
        }
    }

    static void messageAI(int level){
        switch (level) {
            case 0:
                System.out.println("Making move level \"easy\"");
                break;
            case 1:
                System.out.println("Making move level \"medium\"");
                break;
            case 2:
                System.out.println("Making move level \"hard\"");
                break;
        }
    }

}

