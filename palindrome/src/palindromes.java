/** Assignment 7A
 * a program that tests wether or not a number is a palindrome or not 
 * @author Simon wright
 * @since 3/1/2025
 */
import java.util.Scanner;

public class palindromes {
    public static boolean isPalindromes(int number){
        int enteredNum = number;
        int reverse = 0;
        do {
            reverse = reverse * 10 + number % 10;
            number = number /10;
        } while (number > 0);

        //System.out.print(reverse);
        if(enteredNum == reverse) {
            return true;
        } else {
            return false;
        }
        
    }
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int imputNum;
        //test one
        System.out.print("enter a number: ");
        imputNum = in.nextInt();
        if (isPalindromes(imputNum)) {
            System.out.println(imputNum + " is a palindrome");
        } else {
            System.out.println(imputNum + " is not a palindrome");
        }
        //test two
        System.out.print("enter a number: ");
        imputNum = in.nextInt();
        if (isPalindromes(imputNum)) {
            System.out.println(imputNum + " is a palindrome");
        } else {
            System.out.println(imputNum + " is not a palindrome");
        }
        //System.out.println(isPalindromes(141));
    }
}
