package the.knights.tour ;

/*
Denzel Merrell

======================
THE KNIGHT'S TOUR GAME
======================

Description 
===========
The knight begins on the top left square of a chessboard and makes as many 
successive moves as possible with the objective being to visit each square 
exactly once. For a move to be legal, the destination square must be within the 
bounds of the chessboard, must not have been visited previously, and must abide 
by the movement restrictions of a knight in chess. 
A tour ends when there are no more legal moves

This program was completed as a part of an assignment for my programming 2 
class at Florida International University

Added Functionality
===================
After completing the original assignment, I added the ability to:
    -Run additional sets of tours 
    -See the output of the shortest tour in addition to the longest tour
    -Validate input for all user given information
*/


/**
 * Creates a new 8 by 8 chessboard of asterisks
 */
public class ChessBoard 
{
    final int NUM_ROWS ;    //The total rows on the board
    final int NUM_COLUMNS ; //The total columns on the board
    String [][] board ;     //The game board as a 2d array
    
    public ChessBoard() 
    {
        this.NUM_ROWS = 8 ;
        this.NUM_COLUMNS = 8 ;
        this.board = new String[this.NUM_ROWS][this.NUM_COLUMNS] ;
        
    
      //There will be an asterisk for each square that is not reached in the 
      //tour
      //Outer loop goes through the rows of the board
        for(int row = 0, i = 1 ; row < this.NUM_ROWS ; row++) 
        {
            //Inner loop goes through the columns of the board
            for(int col = 0 ; col < this.NUM_COLUMNS ; col++, i++)
            {
                this.board[row][col] = "*" ;
            }         
        }     
    }
    
    /**
     * Tell whether a move to a given row and column has already been made
     * 
     * @param row the chessboard row to be examined
     * @param col the chessboard column to be examined
     * @return true if the the row and column position has already been visited,
     * false otherwise
     */
    public boolean hasMoveBeenMade(int row, int col) 
    {
        //If the space is an asterisk, then it is a valid move
        return !(this.board[row][col].equals("*")) ;
    }
    
    
    /**
     * Places a number at a given row and column on the chessboard
     * 
     * @param row the chessboard row to be examined
     * @param col the chessboard column to be examined
     * @param numberToPlace the number that will be placed on the chessboard
     */
    public void placeNumber(int row, int col, int numberToPlace) 
    {    
        this.board[row][col] = Integer.toString(numberToPlace) ;
    }
    
    
    /**
     * 
     * @return A string containing the formatted chessboard to output
     */
    @Override
    public String toString() 
    {
        
        String str = "" ;   //A temporary string to hold the output
        
        //Outer loop goes through the rows of the board
        for(int row = 0 ; row < this.NUM_ROWS ; row++) 
        {
            //Inner loop goes through the columns of the board
            for(int col = 0 ; col < this.NUM_COLUMNS ; col++)
            {       
                str += String.format("%-4s", this.board[row][col]) ;
            }
            str += "\n" ;
        }
        
        return str ;
    }
}
