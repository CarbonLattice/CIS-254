/**
 * class demo with constuctors
 * much easyer this way
 * @author Simon Wright
 * @version v1
 * @since 4/5/2026
 */

class Car {
    public String Make;
    public String Model;
    public int year;
    
    public Car(String Make, String Model, int year) {
        this.Make = Make;
        this.Model = Model;
        this.year = year;
    }

    void displayinfo(){
        System.out.println("Make: " + Make);
        System.out.println("Model: " + Model);
        System.out.println("year: " + year);
    }
}

public class Cardemo2 {

    public static void main(String[] args) throws Exception {
        System.out.println("test first object");
        Car test1 = new Car("honda", "Civic", 2022);
        test1.displayinfo();
        
        System.out.println("test second object");
        Car test2 = new Car("toyota", "prius", 2016);
        test2.displayinfo();

    }
}
