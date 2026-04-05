




public class App {
    
        static int height = 32;
        static int width = 512;
        static double viscosity = 0.002;
        static double omega = 1./(3*viscosity + 0.5);
        static double u0 = 0.1;
        static double four9ths = 4./9;
        static double one9th = 1./9;
        static double one36th = 1./36;

        static float n0[] = new float[height * width];
        static float nN[] = new float[height * width];
        static float nS[] = new float[height * width];
        static float nE[] = new float[height * width];
        static float nW[] = new float[height * width];
        static float nNW[] = new float[height * width];
        static float nNE[] = new float[height * width];
        static float nSE[] = new float[height * width];
        static float nSW[] = new float[height * width];

        static float bar[] = new float[height * width];

        static float rho[] = new float[height * width];
        static float ux[] = new float[height * width];
        static float uy[] = new float[height * width];
        static float speed2[] = new float[height * width];


    public static void main(String[] args) throws Exception {
        while (true) {
                    stream();
        }

        //System.out.println(n0.length);
    }

    public static void stream(){
        int x;
        int y;

        for (x=0; x<(width-1); x++) {
            for (y=0; y<(height-1); y++){
                int index = y * width + x;

                nN[index] = nN[index + width];
                nNW[index] = nNW[index + width + 1];
            }
        }
    }

}
