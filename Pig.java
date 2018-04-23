/* Name: Nicholas Keen
 * Course: Computer Science - CIS I
 * Section: 003
 * Assignment: 9
*/
import java.util.*;

    //creates a two player game called pig.
    public class Pig {
    
    //sets up a scanner and calls some methods.
    public static void main(String[] args) {
    
	Scanner sc = new Scanner(System.in);
	again(winner(intro(sc)));
    }

    //sets up the introduction and calls the retry
    //method, and allows the user to input wether they 
    //want to play the game or not, returns response.
    public static String intro(Scanner sc) {
    
    System.out.print("Welcome to the game of \"pig.\"\n" +
		      "This is a two­player game.\n" +
		      "The first player rolls a 6­sided die.\n" +
		      "The player can roll as many times as she/he\n" +
		      "likes until she/he wishes to stop or gets a 1.\n" +
		      "If the first player chooses to stop, she/he\n" +
		      "gets the sum of all her/his rolls added to\n" +
		      "her/his score. If the first player stops because\n" +
		      "she/he has rolled a one, she/he gets no points\n" +
		      "for that turn. The first player to reach 100\n" +
		      "points wins the game.\n" +	
		      "Do you want to play (y/n)? ");
		      
    retry(sc, "Do you want to play (y/n)? ");
    String ans = sc.nextLine();
    return ans;
    }
    
    //handles/displays the points for each player and calls the 
    //turn method, also stops the game after a player reaches 
    //one hundred points, returns which player won i.e. player
    //one or two.
    public static int game(String ans) {
    
    int player = 1;
    int score1 = 0;
    int score2 = 0;
    int win = 0;
    
    //returns -1 through the program in order 
    //to never play the game should the user
    //choose no at the intro.
    if(ans.equals("n")){
	return -1;
    }
    do{
	if(player % 2 != 0) {
	    score1 = score1 + turn(ans, 1);
	    System.out.print("\nScore\nPlayer 1: " +score1
			      + "\nPlayer 2: " +score2);
	    System.out.println();
	    if(score1 >= 100) {
		win = 1;
		return win;
	    }
	    player++;
	}
	if(player % 2 == 0) {
	    score2 = score2 + turn(ans, 2);
	    System.out.print("\nScore\nPlayer 1: " +score1
			      + "\nPlayer 2: " +score2);
	    System.out.println();
	    if(score2 >= 100) {
		win = 2;
		return win;
	    }
	    player++;
	}
    }while(win != 1 || win != 2);
    return 0;
    }
    
    //handles the individual turn of each player, 
    //returns the total sum of all rolls that turn.
    public static int turn(String ans, int player) {
    
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	int roll;
	int score = 0;
	
	//continues to pass the -1 through in case 
	//the user chose not to play.
	if(ans.equals("n")) {
	    return -1;
	}else{
	    System.out.print("\nPLAYER " + player + " ");
	    for(int i = 1; i <= 36; i++) {
		System.out.print("*");
	    }
	    System.out.print(" rolling...\n");
	    while(ans.equals("y")) {
		roll = (rand.nextInt(6) + 1);
		if(roll == 1) {
		    System.out.println("You rolled a 1. Sorry!");
		    score = 0;
		    return score;
		}else {
		    score += roll;
		    System.out.println("roll: " + roll);
		    System.out.print("Roll again (y/n)? ");
		    retry(sc, "Roll again (y/n)?");
		    ans = sc.nextLine();
		    if(ans.equals("n")) {
			System.out.println("A wise choice\n");
			return score;
		    }
		}	
	    }
	}
	return score;
    }

    //ensures that the user enters either y or n
    //returns their input once they've entered y or n.
    public static String retry(Scanner sc, String prompt) {
    
	String ans = "";
	while(!sc.hasNext("y") && !sc.hasNext("n")) {
	    sc.next();
	    System.out.println("You must answer y or n.\n" + prompt);
	    ans = sc.nextLine();
	}
    return ans;
    }

    //declares the winner or never plays the game if the
    //user chose n at intro, then asks the user if they'd
    //like to play again, returns response.
    public static String winner(String ans) {
    
	Scanner sc = new Scanner(System.in);
	int player = game(ans);
	if(player == -1) {
	    System.out.println("Gameover");
	}else{
	    System.out.print("\nPlayer "+ player + " wins");
	    System.out.println("\nWould you like to play AGAIN?");
	    retry(sc, "Would you like to play AGAIN?");
	    String n = sc.nextLine();
	    return n;
	}
	return "";
    }
    
    //plays the game again or declares gameover
    //depending on user input, also calls some methods.
    public static void again(String n) {
    
	Scanner sc = new Scanner(System.in);
	if(n.equals("y")) {
	    winner(intro(sc));
	}else if(n.equals("n")) {
	    System.out.println("Gameover");
	}
    }
}