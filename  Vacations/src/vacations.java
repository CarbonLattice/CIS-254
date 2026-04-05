import java.util.Scanner;
/** Assignment 4A
 * Madlibs program but actualy interactive this time yay
 * @author Simon wright
 * @since 2/8/2025
 */


public class vacations {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String Adjective1;
        String Adjective2;
        String Noun1;
        String Noun2;

        System.out.print("Adjective: ");
        Adjective1 = in.next();
        System.out.print("Adjective: ");
        Adjective2 = in.next();
        System.out.print("Noun: ");
        Noun1 = in.next();
        System.out.print("Noun: ");
        Noun2 = in.next();

        System.out.print("\nA vacation is when you take a trip to some " + Adjective1 + " place with your "
        + Adjective2 + " family.\nUsually you would go to some place that is near a/an " + Noun1 + " or up a/an " + Noun2 + ".\n");
    }
}
