# tictactoe
A simple command line program written in Java that lets you play Tic-Tac-Toe against a friend (locally) or an IA with 3 different difficulty levels. 

This program was written by @agustin-recoba entirely except from the method "isNumeric" in the main.java file, which was copied straight out of stackoverflow (thanks @CraigTP).

Commands:    

      start *playerX* *playerO* (1).
      exit (2)
      
Uses: 

(1) starts a new game and either player can be: "user", "easy", "medium" or "hard".
       user: takes X Y input for moves
       easy: AI that makes random moves
       medium: AI that makes random moves bau can also complete a line to win or to avoid losing
       ard: AI that implements the minimax algorithm, never loses
      
(2) ends the program
