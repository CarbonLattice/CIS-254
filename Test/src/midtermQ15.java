import java.util.Scanner;

/** midterm Q15
    
    Simon wright 

    3/22/2026 



 */
public class midtermQ15{
   public static void main(String [] args){
        Scanner imput = new Scanner(System.in);
        int n;
        int starNum = 0;
        String str = imput.next();
        for (n = 0; n < str.length(); ++n) {
            if (str.charAt(n) == '*') {
                System.out.println("Star found at index " + n );
                starNum = starNum + 1;
            }
        }
        System.out.println("star occurs " + starNum + " times");
        

      }
   }

  