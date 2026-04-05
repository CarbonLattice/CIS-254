import java.util.Scanner;

/** Assignment 4B
 * program to convert seconds int a hour minute second format 
 * @author Simon wright
 * @since 2/8/2025
 */

public class SecoundsConversion {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        final int SECOUNDS_IN_HOUR = 3600;
        final int SECOUNDS_IN_MINUTE = 60;
        int inputSeconds;
        int hour;
        int minutes;
        int seconds;

        System.out.print("Enter the time in seconds: ");
        inputSeconds = in.nextInt();
        hour = inputSeconds/SECOUNDS_IN_HOUR;
        minutes = (inputSeconds%SECOUNDS_IN_HOUR) / SECOUNDS_IN_MINUTE;
        seconds = inputSeconds%SECOUNDS_IN_MINUTE;
        //System.out.println(hour);
        //System.out.println(minutes);
        //System.out.println(seconds);
        System.out.println("The time in seconds = " + inputSeconds);
        System.out.println("\n" + inputSeconds + " seconds = " + hour + " hours, " + minutes + " minutes, and "
            + seconds + " seconds" 
         );


    }
}
