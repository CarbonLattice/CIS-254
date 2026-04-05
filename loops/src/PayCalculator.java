/** Assignment 7B
 * program to calculate the pay of an employee based on some regulations
 * @author Simon wright
 * @since 3/1/2025
 */


public class PayCalculator {
    static final double minPay = 8.00;
    static final int maxHours = 60;
    
    public static void calculatePay(double basePay, int hoursWorked) {
        if (basePay < minPay){
            System.out.println("per regulations pay may not be less than 8.00$ an hour");
            return;
        } 
        if (hoursWorked > maxHours) {
            System.out.println("employees should not work more than a 60 hours");
            return;
        }
        if (hoursWorked <= 40) {
            double pay = (basePay * hoursWorked);
            System.out.println("employees pay toatl : " + pay);
        } else {
            int overtimeHours = hoursWorked - 40;
            double totalPay = (40 * basePay) + overtimeHours * (basePay * 1.5);
            System.out.println("this employee has worked " + overtimeHours + " hours of overtime there pay is : " + totalPay);
        }
    }

    public static void main(String[] args) throws Exception {
        calculatePay(7.50, 35);
        calculatePay(8.20, 47);
        calculatePay(10.00, 70);

    }
}
