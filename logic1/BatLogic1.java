/**
 * CIS 254 CodingBat Exercise on Conditional Statements 
 *
 * @author simon wright
 * @version 2/24/2025
 */
   public class BatLogic1 {

    /**
     * The squirrels in Palo Alto spend most of the day playing. In particular,
     * they play if the temperature is between 60 and 90 (inclusive). Unless it
     * is summer, then the upper limit is 100 instead of 90. Given an int
     * temperature and a boolean isSummer, return true if the squirrels play and
     * false otherwise.
     *
     * @param temp the current temperature
     * @param isSummer true if it's summer
     * @return whether squirrels should play
     */
    public static boolean squirrelPlay(int temp, boolean isSummer) {
      if (isSummer == true) {
        if (temp >= 60 && temp <=100) {
          return true;
        } else {
          return false;
        }
      } else {
        if (temp >= 60 && temp <=90) {
          return true;
        } else {
          return false;
        }
     }
    }

    /**
     * You are driving a little too fast, and a police officer stops you. Write
     * code to compute the result, encoded as an int value: 0=no ticket, 1=small
     * ticket, 2=big ticket. If speed is 60 or less, the result is 0. If speed
     * is between 61 and 80 inclusive, the result is 1. If speed is 81 or more,
     * the result is 2. Unless it is your birthday -- on that day, your speed
     * can be 5 higher in all cases.
     *
     * @param speed your driving speed
     * @param isBirthday true if it's your birthday
     * @return 0=no ticket, 1=small ticket, 2=big ticket
     */
    public static int caughtSpeeding(int speed, boolean isBirthday) {
        int ticket = 0;
        if (isBirthday == false) {
          if (speed > 60 && speed <= 81) {
            ticket = 1;
          } else if (speed > 81) {
            ticket = 2;
          } else {
            ticket = 0;
          }
        } else {
          if (speed > 66 && speed <= 85) {
            ticket = 1;
          } else if (speed > 85) {
            ticket = 2;
          } else {
            ticket = 0;
          }
        }
        return ticket;
      }


    /**
     * Given two ints, each in the range 10..99, return true if there is a digit
     * that appears in both numbers, such as the 2 in 12 and 23. (Note:
     * division, e.g. n/10, gives the left digit while the % "mod" n%10 gives
     * the right digit.)
     *
     * @param a 1st number
     * @param b 2nd number
     * @return true if digit in both numbers
     */
    public static boolean shareDigit(int a, int b) {
    int one = a%10;
    int two = a/10;
    int three = b%10;
    int four = b/10;
      if (one == three || one == four || two == three || two == four) {
        return true;
      } else {
        return false;
      }
    }
  public static void main(String[] args) throws Exception {
    System.out.println("squirrelPlay(70, false) - " + squirrelPlay(70, false));
    System.out.println("squirrelPlay(95, false) - " + squirrelPlay(95, false));
    System.out.println("squirrelPlay(95, true) - " + squirrelPlay(95, true) + "\n");

    System.out.println("caughtSpeeding(60, false) - " + caughtSpeeding(60, false));
    System.out.println("caughtSpeeding(65, false) - " + caughtSpeeding(65, false));
    System.out.println("caughtSpeeding(65, true) - " + caughtSpeeding(65, true) + "\n");

    System.out.println("shareDigit(12, 23) - " + shareDigit(12, 23));
    System.out.println("shareDigit(12, 43) - " + shareDigit(12, 43));
    System.out.println("shareDigit(12, 44) - " + shareDigit(12, 44));
  }

}