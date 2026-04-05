import java.util.Calendar;

/**
 * simple program to convert time to seconds
 * @author Simon wright
 * @since 2/8/2025
 */

public class Time {
    public static void main(String[] args) throws Exception {
        //got it to work by usesing system time insted of just numbers which i think is prety cool 
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        //int hour = 16;
        //int minute = 25;
        //int second = 33;
        final int HOUR_TO_SEC = 3600;
        final int MIN_TO_SEC = 60;

        int secondsSinceMidnight = (hour * HOUR_TO_SEC) + (minute * MIN_TO_SEC) + second;
        int secondsRemaining = (24 *HOUR_TO_SEC) - secondsSinceMidnight;
        double percentagePased = (secondsSinceMidnight/(24.0 * HOUR_TO_SEC)*100);
        System.out.println("seconds since midnight " + secondsSinceMidnight);
        System.out.println("seconds remaining in the day " + secondsRemaining);
        System.out.print("Percentage of the day passed: ");
        System.out.printf("%.2f\n", percentagePased);
    }
}
