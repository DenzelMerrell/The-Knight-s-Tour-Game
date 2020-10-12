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
by a the movement restrictions of a knight in chess. 
A tour ends when there are no mor legal moves

This program was completed as a part of an assignment for my programming 2 
class at Florida International University

Added Functionality
===================
After completing the original assignment, I added the ability to:
    -Run additional sets of tours 
    -See the output of the shortest tour in addition to the longest tour
    -Validate input for all user given information
*/



import java.util.ArrayList ;
import java.util.Random ;

/**
 * Conducts and gives information about the tours
 */
public class KnightsTour 
{
 
    //The amount of moves taken in the longest and shortest tour runs
    int numMovesOfLongestTour ;
    int numMovesOfShortestTour ;
    
    int countTour ;            //Keeps a count of how many tours have been made
    int countOfLongestTour ;  //The tour number of the longest tour
    int countOfShortestTour ; //The tour number of the shortest tour
    
    //Strings to hold the output of the longest and shortest recorded tours
    String longestTour ;
    String shortestTour ;
    
    //How many tours of each length (1-64) were taken.
    //The "index + 1" (because there can't be a tour length of 0) being the 
    //tour length and the element at that index being the amount of tours taken 
    //with that length
    int [] toursOfEachLength ;
    final int MAK_TOUR_LENGTH = 64 ; //The maximum length of a tour
    
    
    public KnightsTour() 
    {
        this.numMovesOfLongestTour = 0 ;   
        this.numMovesOfShortestTour = 0 ;   
        this.countTour = 1 ;
        this.longestTour = "" ;    
        this.shortestTour = "" ;
        this.toursOfEachLength = new int[this.MAK_TOUR_LENGTH] ; 
    }
    
    /**
     * Conducts a single tour
     */
    public void makeOneTour() 
    {
        ChessBoard board = new ChessBoard() ;
        Knight knight = new Knight() ;
        
        //The amount of moves the knight has made so far and the number to 
        //place on the board when a new move is made
        int numberToPlace = 1 ;     
                                  
        
        //Start the knight on the board at (0, 0)
        board.placeNumber(0, 0, numberToPlace) ;
        
        //Update the number to place on the board on the next move
        numberToPlace++ ;
               
 
        //Stores the moves that are currently avialable to the knight
        ArrayList<Integer> potentialMoves = new ArrayList<>() ; 
        
         //The knight moves until there are no legal moves        
        do 
        {
            //Reset the potential moves, as we are on a new square with new
            //potential moves
            potentialMoves.clear() ;
            
            //Determine if the knight can move to moveType
            //If so, store moveType in an array containing all potential moves
            for(int moveType = 0 ; moveType < 8 ; moveType++) 
            {
                //Determine if the knight can make a move of type moveNum
                if(knight.canMove(moveType, board)) 
                {
                    potentialMoves.add(moveType) ;
                }

            }
            
            //If there are no potential moves, the tour is finished
            if(potentialMoves.isEmpty()) 
            {
                break ;
            }

            //Keep generating random numbers (0-7) until one is found that is a 
            //potential move
            Random gen = new Random() ;
            int randomMove ;
            //Keeps track of whether a random potential move has been found
            boolean isAPotentialMove = false ;  
            do 
            {
                randomMove = gen.nextInt(8) ;
                if(potentialMoves.contains(randomMove))
                {
                    isAPotentialMove = true ;
                }

            } while(!isAPotentialMove) ;

            //Move
            knight.move(randomMove, board, numberToPlace) ;

            //Increment the number that will be placed on the board in the next 
            //move
            numberToPlace++ ;
           
        } while(!potentialMoves.isEmpty()) ; 
        //Tour Finished
        
        
        
        //The total moves of the current tour is the same as the previous 
        //number that was placed on the board.
        int currentTourLength = numberToPlace - 1 ;
        
        //If this is the first tour, then it is also the shortest tour made 
        //thus far
        if(this.countTour == 1)
        {
            this.numMovesOfShortestTour = currentTourLength ;
        }
        
        //Add the currentTourLength to the count of the total tours of each
        //length
        this.toursOfEachLength[currentTourLength]++ ;
        
        //Determine if the currentTourLength is greater than the longest tour.
        //If so, that is the new longest tour        
        if(currentTourLength > this.numMovesOfLongestTour)
        {
            this.numMovesOfLongestTour = currentTourLength ;
            storeLongestTour(board) ;            
        }
        if(currentTourLength < this.numMovesOfShortestTour)
        {
            this.numMovesOfShortestTour = currentTourLength ;
            storeShortestTour(board) ;    
        }
        
        //Increment the count of tours made
        this.countTour++ ;
    }
    
    /**
     * Helper method called by the conductOneTour method to store the output of 
     * the longest tour
     * 
     * @param board The chessboard
     */
    public void storeLongestTour(ChessBoard board)
    {        
            this.countOfLongestTour = this.countTour ;
            
            //Clear the previous longest tour
            this.longestTour = "" ;
            //Store the new longest tour for output
            this.longestTour += "The Best Tour \n\n" ;
            
            this.longestTour += String.format
                            (
                                "%-14s", 
                                    
                                "Tour # " 
                                + this.countOfLongestTour
                                + "     Tour Length: " 
                                + this.numMovesOfLongestTour 
                                + "\n"
                            ) ;
            this.longestTour += "==============================\n" ;
            
            this.longestTour += board.toString() + "\n" ;
    }
    
    /**
     * Helper method called by the conductOneTour method to store the output of 
     * the shortest tour
     * 
     * @param board The chessboard
     */
    public void storeShortestTour(ChessBoard board)
    {
        this.countOfShortestTour = this.countTour ;
            
            //Clear the previous shortest tour
            this.shortestTour = "" ;
            //Store the new shortest tour for output
            this.shortestTour += "The Worst Tour \n\n" ;
            
            this.shortestTour += String.format
                            (
                                "%-14s", 
                                    
                                "Tour # " 
                                + this.countOfShortestTour
                                + "     Tour Length: " 
                                + this.numMovesOfShortestTour 
                                + "\n"
                            ) ;
            this.shortestTour += "==============================\n" ;
            
            this.shortestTour += board.toString() + "\n" ;
    }
    
    
    /**
     * Gets the number of moves made during the longest tour
     * @return The number of moves made during the longest tour
     */
    public int getNumMovesOfLongestTour() 
    {  
        return this.numMovesOfLongestTour ;
    }
    
    /**
     * Returns a record of the longest tour as a multi-line string
     * @return A record of the longest tour as a multi-line string
     */
    public String printLongestTour()
    {       
        return this.longestTour ;
    }
    
    /**
     * Returns a record of the shortest tour as a multi-line string
     * @return A record of the shortest tour as a multi-line string
     */
    public String printShortestTour()
    {       
        return this.shortestTour ;
    }
    
    /**
     * Returns the amount of tours conducted at each length as a multi-line
     * string
     * @return The amount of tours conducted at each length as a multi-line
     * string
     */
    public String getNumToursOfEachLength() 
    {
        
          String str = "" ; //Temporary string to hold the output 
          
          str += String.format("%-16s%-22s\n","Tour Length", "Number of Tours") 
                  ;
          str += String.format("%-16s%-22s\n","===========", "===============") 
                  ;
        
        for(int index = 0 ; index < this.toursOfEachLength.length ; index++) 
        {
            //Don't print the lengths with zero tours
            if(this.toursOfEachLength[index] != 0) 
            {
                str += String.format("%-16d%-22d\n", index, 
                                     this.toursOfEachLength[index]) ;
            }
        }
        
        return str ;
    }
    
}
