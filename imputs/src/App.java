import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        final double CM_PER_INCH = 2.54;
        int inch;
        double cm;
        Scanner in = new Scanner(System.in);
        System.out.print(4.0 / 3.0);
        System.out.print("how many inches? ");
        inch = in.nextInt();
        cm = inch * CM_PER_INCH;
        System.out.printf("%d in = %f cm\n", inch, cm);
        System.out.print(inch + " in = ");
        System.out.println(cm + " cm");
        int x=7;
        int y=2;
        double result = x/y;
        System.out.print(result);
    }
}
