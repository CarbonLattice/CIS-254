/**
 * CodingBat String-2.
 *
 * @author Simon Wright
 * @version v1
 * @since 3/15/2026
 */
public class CodingBatString2 {

    /**
     * Given a string, return a string where for every char in the original,
     * there are two chars.
     *
     * @param str input string
     * @return result string
     */
    public static String doubleChar(String str) {
    String result = "";
    for(int i=0; i<str.length(); i++) {
        result = result + str.substring(i, i+1) + str.substring(i, i+1);
    }
    return result;
    }

    /**
     * Return true if the string "cat" and "dog" appear the same number of times
     * in the given string.
     *
     * @param str input string
     * @return result boolean
     */
    public static boolean catDog(String str) {
    int dogstart = 0;
    int catstart = 0;
    int catNum = 0;
    int dogNum = 0;
    while (true) {
        int catfound = str.indexOf("cat",catstart);
        if (catfound != -1) {
        catNum = catNum + 1;
        }
        if (catfound == -1) break;
        catstart = catfound + 2;
    }
    while (true) {
        int dogfound = str.indexOf("dog",dogstart);
        if (dogfound != -1) {
        dogNum = dogNum + 1;
        }
        if (dogfound == -1) break;
        dogstart = dogfound + 2;
    }
    if (catNum==dogNum) {
        return true;
    }
    return false;
    }

    public static void main(String[] args) throws Exception {

        System.out.println("test with catdog - should be true  - " + catDog("catdog"));

        System.out.println("test with catcat - should be false - " + catDog("catcat"));

        System.out.println("test with 1cat1cadodog - should be true - " + catDog("1cat1cadodog"));

        System.out.println("test doublechar - " + doubleChar("catdog"));

        System.out.println("test doublechar - " + doubleChar("the"));

        System.out.println("test doublechar - " + doubleChar("aaaa"));


    }

}