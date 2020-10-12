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
 * Creates a knight that keeps track of its position on the board and all 
 * potential move types
 */
public class Knight 
{
    int row ;               //The current row occupied by the knight
    int col ;               //The current column occupied by the knight
    int [] rowMoves ;       //The vertical move types that the knight can make
    int [] colMoves ;       //The horizontal move types that the knight can 
                            //make
    
    
    public Knight()  
    {
        this.row = 0 ;
        this.col = 0 ;
        this.rowMoves = new int[] {-1, -2, -2, -1, 1, 2, 2, 1} ;
        this.colMoves = new int[] {2, 1, -1, -2, -2, -1, 1, 2} ;
    }
    
    /**
     * 
     * @return The current row occupied by the knight
     */
    public int getCurrentRow() 
    {
        return this.row ;
    }
    
    /**
     * 
     * @return The current column occupied by the knight
     */
    public int getCurrentCol() 
    {
        return this.col ;
    }
    
    /**
     * Determines whether a particular move can be made (Tests whether a move
     * would take the knight off the board and, if not, whether the move has 
     * already been made
     * 
     * @param moveType The type of move, between 0 and 7, to be made
     * @param board the chessboard
     * @return True if a move to the specified row and column can be made, 
     * false otherwise
     */
    public boolean canMove(int moveType, ChessBoard board) 
    {       
        //The row/col move is the current position + the move type
        int rowMove = this.row + this.rowMoves[moveType] ;
        int colMove = this.col + this.colMoves[moveType] ;
        
        //Check if a move of type moveNum would take the knight off the board  
        //for the row and column
        if(rowMove <= 7 && rowMove >= 0 && 
           colMove <= 7 && colMove >= 0)
        {     
            //Determine if the move has already been made
            if(board.hasMoveBeenMade(rowMove, colMove)) 
            {
                return false ;
            }
            else 
            {
                return true ;
            }
        }
      
        return false ;
    }
    
    /**
     * Moves the knight to the specified row and column on the board
     * 
     * @param moveType The type of move, between 0 and 7, to be made
     * @param board the chessboard
     * @param numberToPlace The number to place on the board (increments after 
     * every move)
     */
    public void move(int moveType, ChessBoard board, int numberToPlace) 
    {
        //Calculate the row and column after a move of type moveNum
        int rowNum = this.row + this.rowMoves[moveType] ;
        int colNum = this.col + this.colMoves[moveType] ;
        
        //Move
        board.placeNumber(rowNum, colNum, numberToPlace) ;
        
        //Update the knights position
        this.row = rowNum ;
        this.col = colNum ;
    }
    
}
