public class AssignmentOperators {
    public static void main(String[] args) {
        int num = 10;

        num += 5; // same as num = num + 5
        System.out.println("After += : " + num);

        num -= 3; // same as num = num - 3
        System.out.println("After -= : " + num);

        num *= 2; // same as num = num * 2
        System.out.println("After *= : " + num);

        num /= 4; // same as num = num / 4
        System.out.println("After /= : " + num);

        // Increment and decrement
        int x = 5;
        System.out.println("x++ = " + (x++)); 
        System.out.println("++x = " + (++x)); 
    }
}