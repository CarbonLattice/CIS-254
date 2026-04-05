/**
 * CIS Exercise 8A CodingBat String-1.
 *
 * @author Simon Wright
 * @version 1.0
 * @since 3/14/2026
 */
public class BatString1 {

    /**
     * Given a string of even length, return the first half. So the string
     * "WooHoo" yields "Woo".
     *
     * @param str input string
     * @return result string
     */
    public static String firstHalf(String str) {
        String a = str;
        int len = str.length();
        return a.substring(0, len/2);
    }

    /**
     * Given a string, return a version w/o the first and last char. So "Hello"
     * yields "ell". The string length will be at least 2.
     *
     * @param str input string
     * @return result string
     */
    public static String withoutEnd(String str) {
        int len = str.length();
        return str.substring(1, len-1);
    }

    /**
     * Given a string, return the string made of its first two chars, so the
     * String "Hello" yields "He". If the string is shorter than length 2,
     * return whatever there is, so "X" yields "X", and the empty string ""
     * yields the empty string "".
     *
     * @param str input string
     * @return result string
     */
    public static String firstTwo(String str) {
      int len = str.length();
        if (len <= 2) {
            return str;
        } else {
            return str.substring(0,2);
        }
    }

    /**
     * Given a string, return a new string made of 3 copies of the last 2 chars
     * of the original string. The string length will be at least 2.
     *
     * @param str input string
     * @return result string
     */
    public static String extraEnd(String str) {
        int len = str.length();
        String copy = str.substring(len-2) + str.substring(len-2) + str.substring(len-2);
        return copy;
    }

    /**
     * Given two strings, append them together (known as "concatenation") and
     * return the result. However, if the concatenation creates a double-char,
     * then omit one of the chars, so "abc" and "cat" yields "abcat".
     *
     * @param a first string
     * @param b second string
     * @return result string
     */
    public static String conCat(String a, String b) {
        int Alen = a.length();
        int Blen = b.length();

        if (Alen > 0 && Blen > 0) {
            String last = a.substring(Alen - 1);
            String first = b.substring(0, 1);

            if (last.equals(first)) {
            return a.substring(0, Alen - 1) + b;
            }
        }
        return a + b;
    }

}