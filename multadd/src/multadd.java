
/** Assignment 5B
 * practising with functions and printf
 * @author Simon wright
 * @since 2/19/2025
 */


public class multadd {
    static double multAdd(double a, double b, double c) {
        return a * b + c;
    }
    
    public static void main(String[] args) throws Exception {

        System.out.printf("test multiadd with numbers (3.0, 2.0, 3.3) : %f %n", multAdd(3.0, 2.0, 3.3));
        System.out.printf("test multiadd with numbers (4.0, 2.5, 5.222) : %f %n", multAdd(4.0, 2.5, 5.222));
        System.out.printf("test multiadd with numbers (5.5, 5.5, 5.5) : %f %n", multAdd(5.5, 5.5, 5.5));
        System.out.printf("test multiadd with numbers (3.0123, 2.0123, 3.13) : %f %n", multAdd(3.0122, 2.0123, 3.13));


        System.out.printf("(sin(Pi/4)+ cos(Pi/4)/2): %.18f %n", multAdd(0.5, Math.sin(Math.PI / 4), Math.cos(Math.PI / 4)));
        System.out.printf("(log(10) + log(20)): %.18f %n", multAdd(1.0, Math.log(10), Math.log(20)));

    }
}
