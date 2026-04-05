/** Assignment 8C
 * using built in string fucntions to test if a string is a palindrom or if it is in alphabetical order 
 * @author Simon wright
 * @since 3/23/2025
 */


public class NiftySpelling {
    
    public static boolean abecedarian(String word) {
        boolean abecedarian = false;
        for (int i = 0; i < word.length()-1; i++){
            if ((int)word.charAt(i) < (int)word.charAt(i+1)){
                abecedarian = true;
            } else {
                abecedarian = false;
                break;
            }
        }
        return abecedarian;
    }

    public static boolean palindrome(String word){
        boolean palindrome = false;
        for (int i = 0; i < (word.length()/2); i++) {
            int frontIndex = i;
            int rearIndex = word.length() -(1+i);

            if ((int)word.charAt(frontIndex) == (int)word.charAt(rearIndex)){
                //System.out.println((int)word.charAt(i) + "=" + (int)word.charAt(word.length()));
                palindrome = true;
            } else {
                palindrome = false;
                break;
            }
        }
        //System.out.println(palindrome);
        return palindrome;
    }
    


    public static void main(String[] args) {
        System.out.println("is acknow abecedarian: " + abecedarian("acknow"));
        System.out.println("is adempt abecedarian: " + abecedarian("adempt"));
        System.out.println("is befist abecedarian: " + abecedarian("befist"));
        System.out.println("is bijoux abecedarian: " + abecedarian("bijoux"));
        System.out.println("is tester abecedarian: " + abecedarian("tester"));

        System.out.println("is otto a palindrome: " + palindrome("otto"));
        System.out.println("is palindromeemordnilap a palindrome: " + palindrome("palindromeemordnilap"));
        System.out.println("is tester a palindrome: " + palindrome("tester"));

        
    }
}
