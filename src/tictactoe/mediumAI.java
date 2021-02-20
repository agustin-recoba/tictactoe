package tictactoe;

public class mediumAI extends Player {
    mediumAI(char symbol) {
        super(symbol);
    }

    void makeMove(Game game){
        int[][][] possibleLines =
                { { {0,0}, {1,0}, {2,0}}, {{0,1}, {1,1}, {2,1}}, {{0,2}, {1,2}, {2,2}},
                  { {0,0}, {0,1}, {0,2}}, {{1,0}, {1,1}, {1,2}}, {{2,0}, {2,1}, {2,2}},
                  { {0,0}, {1,1}, {2,2}}, {{0,2}, {1,1}, {2,0}} };

        char player = this.getSymbol();

        if (this.possibleWin(game)) {
            for (int i = 0; i < 8; i++) {
                if (this.possibleWinningLine(game, possibleLines[i])) {
                    for (int j = 0; j < 3; j++) {
                        if (game.board[possibleLines[i][j][0]][possibleLines[i][j][1]] == '_') {
                            game.board[possibleLines[i][j][0]][possibleLines[i][j][1]] = player;
                        }
                    }
                    break;
                }
            }
        } else if (this.possibleLoss(game)) {
            for (int i = 0; i < 8; i++) {
                if (this.possibleLosingLine(game, possibleLines[i])) {
                    for (int j = 0; j < 3; j++) {
                        if (game.board[possibleLines[i][j][0]][possibleLines[i][j][1]] == '_') {
                            game.board[possibleLines[i][j][0]][possibleLines[i][j][1]] = player;
                        }
                    }
                    break;
                }
            }
        } else {
            easyAI easy = new easyAI(this.getSymbol());
            easy.makeMove(game);
        }
    }

    boolean possibleWin(Game game){
        return  game.board[0][0] + game.board[1][0] + game.board[2][0] == this.getSymbol()*2 + '_' ||
                game.board[0][1] + game.board[1][1] + game.board[2][1] == this.getSymbol()*2 + '_' ||
                game.board[0][2] + game.board[1][2] + game.board[2][2] == this.getSymbol()*2 + '_' ||

                game.board[0][0] + game.board[0][1] + game.board[0][2] == this.getSymbol()*2 + '_' ||
                game.board[1][0] + game.board[1][1] + game.board[1][2] == this.getSymbol()*2 + '_' ||
                game.board[2][0] + game.board[2][1] + game.board[2][2] == this.getSymbol()*2 + '_' ||

                game.board[0][0] + game.board[1][1] + game.board[2][2] == this.getSymbol()*2 + '_' ||
                game.board[0][2] + game.board[1][1] + game.board[2][0] == this.getSymbol()*2 + '_';
    }

    boolean possibleLoss(Game game){
        char player = this.getSymbol() == 'X' ? 'O' : 'X';
        return  game.board[0][0] + game.board[1][0] + game.board[2][0] == player*2 + '_' ||
                game.board[0][1] + game.board[1][1] + game.board[2][1] == player*2 + '_' ||
                game.board[0][2] + game.board[1][2] + game.board[2][2] == player*2 + '_' ||

                game.board[0][0] + game.board[0][1] + game.board[0][2] == player*2 + '_' ||
                game.board[1][0] + game.board[1][1] + game.board[1][2] == player*2 + '_' ||
                game.board[2][0] + game.board[2][1] + game.board[2][2] == player*2 + '_' ||

                game.board[0][0] + game.board[1][1] + game.board[2][2] == player*2 + '_' ||
                game.board[0][2] + game.board[1][1] + game.board[2][0] == player*2 + '_';
    }

    boolean possibleWinningLine(Game game, int[][] line){
        return game.board[ line[0][0] ][ line[0][1] ] +
                game.board[ line[1][0] ][ line[1][1] ] +
                game.board[ line[2][0] ][ line[2][1] ] == this.getSymbol()*2 + '_';
    }

    boolean possibleLosingLine(Game game, int[][] line){
        char player = this.getSymbol() == 'X' ? 'O' : 'X';
        return game.board[ line[0][0] ][ line[0][1] ] +
                game.board[ line[1][0] ][ line[1][1] ] +
                game.board[ line[2][0] ][ line[2][1] ] == player*2 + '_';
    }
}
