package tictactoe;

abstract class Player {
    char sym;
    Player(char symbol){
        this.sym = symbol;
    }

    public char getSymbol() {
        return sym;
    }

    abstract void makeMove(Game game);
}

