

/** Assignment 5A
 * program to convert seconds int a hour minute second format 
 * @author Simon wright
 * @since 2/19/2025
 */

public class PhoneNumber  {

    public static void print(long number ){
     long partThree = number%10000;
     number = number / 10000;
     long partTwo = number% 1000;
     number = number / 1000;
     long partOne = number% 1000;
     System.out.printf("%d-%d-%d", partOne, partTwo, partThree);
    }


    public static long checksum(long number) {

        long sum = 0;
        long diget = number % 10;
        sum = sum + diget;
        number = number/10;
        diget = number % 10;
        sum = sum + diget;
        number = number/10;
        diget = number % 10;
        sum = sum + diget;
        number = number/10;
        diget = number % 10;
        sum = sum + diget;
        number = number/10;
        return sum;
    }
    public static void main(String[] args) throws Exception {
        System.out.println(checksum(1234567890L));
        print(1234567890);
    }
}


