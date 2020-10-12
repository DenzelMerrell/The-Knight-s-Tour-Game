package the.knights.tour;

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



import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * Tests the knights tour output
 */
public class KnightsTourTester 
{

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, 
                                                  IOException 
    {
        //A "set" is a number of tours, specified by the user, 
        //conducted consecutively 
        
        //Keeps track of whether the user wants to see another set of tours
        String another = "Y" ;  
        //The amount of sets that the user has run
        int setNumber = 0 ;
               
        while(another.equals("Y"))
        {
            setNumber++ ;
            KnightsTour tour = new KnightsTour() ;

            //Get the number of tours from the user 
            Scanner scanner = new Scanner(System.in) ;

            System.out.println("Enter the number of tours: ") ;

            //Input Validation
            while(!scanner.hasNextInt()) 
            {
                System.out.println("Enter the number of tours: ") ;
            }

            int numTours = scanner.nextInt() ;

            //Make the number of tours specified by the user
            for(int i = 0 ; i < numTours ; i++) 
            {
                tour.makeOneTour() ;
            }
            
            System.out.println("\nSet Number: " + setNumber);
            System.out.println("=============\n");
            System.out.println(tour.printLongestTour()) ;
            System.out.println(tour.printShortestTour()) ;
            System.out.println(tour.getNumToursOfEachLength()) ;


            //Write the results of the tour to a file
            PrintWriter writer ; 
            
            //If this is the first set, overwrite the file, otherwise append
            //to the existing file
            if(setNumber == 1)
                writer = new PrintWriter(new FileWriter("output.txt", false)) ;
            else
                writer = new PrintWriter(new FileWriter("output.txt", true)) ;
            
            writer.print(tour.printLongestTour()) ;
            writer.print(tour.printShortestTour()) ;
            writer.print(tour.getNumToursOfEachLength()) ;
            writer.close() ;

            System.out.println("Would you like to conduct more tours? (Y or N)");
            another = scanner.next() ;

            while(!another.equals("Y") && !another.equals("N")) 
            {
                System.out.println("Would you like to conduct more tours? "
                                    + "(Y or N)");
                another = scanner.next() ;
            }
        }
            
    }
    
}
