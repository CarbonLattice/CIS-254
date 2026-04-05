import java.util.Random;
import java.util.Scanner;

/** Assignment 6B
 * program to convert seconds int a hour minute second format 
 * @author Simon wright
 * @since 3/1/2025
 */


public class GuessMyNumber {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        Scanner in = new Scanner(System.in);
        int Randnumber = random.nextInt(10)+1;
        int guessNum;


        System.out.print("I'm thinking of a number between 1 and 10\n(including both). Can you guess what it is?\n");
        System.out.print("enter your first guess: ");
        guessNum = in.nextInt();
        if (guessNum == Randnumber) {
            System.out.println("You are correct the number i was thinking of was " + Randnumber);
            return;
        } else if(guessNum < Randnumber ) {
            System.out.println("Too Low! ");
        } else if (guessNum > Randnumber) {
            System.out.println("Too High! ");
        }
        System.out.print("enter your second guess: ");
        guessNum = in.nextInt();
        if (guessNum == Randnumber) {
            System.out.println("You are correct the number i was thinking of was " + Randnumber);
            return;
        } else if(guessNum < Randnumber ) {
            System.out.println("Too Low! ");
        } else if (guessNum > Randnumber) {
            System.out.println("Too High! ");
        }
        System.out.print("enter your third guess: ");
        guessNum = in.nextInt();
        if (guessNum == Randnumber) {
            System.out.println("You are correct the number i was thinking of was " + Randnumber);
            return;
        } else {
            System.out.println("Sorry, wrong again! the number that i was thinking of was " + Randnumber);
        }
    }
}
