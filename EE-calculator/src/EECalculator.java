
public class EECalculator {



    public static void main(String[] args) throws Exception {
        double inductance = 14.7e-3;
        double crystalCapasitence = 0.027e-12;
        double shuntCapasitence = 5.57e-12;
        double loadCapasitence = 4.02e-12;


        double seriesFreq = 1.0 / (2 * Math.PI * Math.sqrt(inductance * crystalCapasitence));
        double antiFreq = (seriesFreq * (Math.sqrt(1 + (crystalCapasitence / shuntCapasitence))));
        double loadFreq = (seriesFreq * (1 + (crystalCapasitence / (2 * (shuntCapasitence + loadCapasitence)))));


        System.out.println(loadFreq);

    }
}
