package tictactoe;

public class hardAI extends Player {

    hardAI(char symbol) {
        super(symbol);
    }

    void makeMove(Game game){
        int bestScore = -10000;
        int[] bestMove = {1, 1};
        int score;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.board[i][j] == '_') {
                    game.board[i][j] = this.getSymbol();
                    score = minimax(game, this.getSymbol(), false, 0);
                    game.board[i][j] = '_';
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        game.board[bestMove[0]][bestMove[1]] = this.getSymbol();
    }

    int minimax(Game game, char player, boolean isMaximizing, int depth){
        if (game.getEndGame() == 'D') return 0;
        else if (game.getEndGame() == player) return 10;
        else if (game.getEndGame() == (player == 'X' ? 'O' : 'X')) return -10;

        if (isMaximizing){
            int bestScore = -100;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game.board[i][j] == '_') {
                        game.board[i][j] = player;
                        int score = minimax(game, player, false, depth+1);
                        game.board[i][j] = '_';
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return  bestScore;

        } else {
            char opposite = player == 'X' ? 'O' : 'X';
            int worstScore = 100;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game.board[i][j] == '_') {
                        game.board[i][j] = opposite;
                        int score = minimax(game, player, true, depth+1);
                        game.board[i][j] = '_';
                        worstScore = Math.min(score, worstScore);
                    }
                }
            }
            return worstScore;
        }
    }

}
