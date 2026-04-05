/**
 * class demo witout any constuctors
 *
 * @author Simon Wright
 * @version v1
 * @since 4/5/2026
 */



class Car {
    public String Make;
    public String Model;
    public int year;
    
    void displayinfo(){
        System.out.println("Make: " + Make);
        System.out.println("Model: " + Model);
        System.out.println("year: " + year);
    }
}

public class Cardemo {

    public static void main(String[] args) throws Exception {
        System.out.println("test first object");
        Car test1 = new Car();
        test1.Make = "Honda";
        test1.Model = "Civic";
        test1.year = 2022;
        test1.displayinfo();

        System.out.println("test second object");
        Car test2 = new Car();
        test2.Make = "toyota";
        test2.Model = "prius";
        test2.year = 2016;

        test2.displayinfo();

    }
}
