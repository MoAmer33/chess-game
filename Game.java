package Logic;

import GUI.GameFrame;
import java.util.ArrayList;

public class Game {
    private static Player player1;
    private static Player player2;
    private static Player currentPlayer;
    private static Board board;
    private static ArrayList<Move> moves;
    private static Result result;

    public static void main(String[] args) {
          Game g = new Game();
          GameFrame f = new GameFrame(board);
    }

    private enum Result{
        BLACK_WIN,
        WHITE_WIN,
        DRAW
    }

    public Game(){
      player1 = new Player(Color.White);
      player2 = new Player(Color.Black);
      currentPlayer = player1;
      board = new Board();
      moves = new ArrayList<Move>();
      result = null;
    }

  // Why is this static? just pass the only object of type game to the GameFrame
   public static boolean move(Position startingPosition, Position endingPosition) {
        if(board.movePiece(currentPlayer.getColor(),startingPosition, endingPosition))
        {
           //Here I can't call this function without it being static which is illogical
          if(result == null){
                determineGameResult(currentPlayer.getColor());
            }
          if(currentPlayer == player1)
          {
            currentPlayer = player2;
          }
          else
          {
            currentPlayer = player1;
          }
          moves.add(board.getLastMove());
          return true;
        }
        return false;
    }
   public static Color getCrntPlayerClr()
   {
       return currentPlayer.getColor();
   }
   public static void Undo()
   {
       if (moves.size() > 0)
       {
            board.Undo(moves.get(moves.size()-1));
            if(currentPlayer == player1)
            {
              currentPlayer = player2;
            }
            else
            {
              currentPlayer = player1;
            }
            moves.remove(moves.size()-1);
       }
   }
   public static Move getLastMove(int move)
   {
       if (moves.size() - move >= 0 && moves.size() - move < moves.size())
       {
            return moves.get(moves.size() - move);
       }
       else
       {
            return null;
       }
   }
   private static void determineGameResult(Color currentPlayerColor) {
        if (board.getIsCheckmate()) {
            if (currentPlayerColor == Color.White) 
            {
                result = Result.WHITE_WIN;
            } 
            else 
            {
                result = Result.BLACK_WIN;
            }
        } 
        else if (board.getIsStalemate()) 
        {
            result = Result.DRAW;
        }
        //For testing only
        if (result != null) 
        {
            System.out.println(result.name());
        }
    }
    
}