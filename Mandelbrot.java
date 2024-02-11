public class Mandelbrot {
    public static int iterationLimit = 500;

    public static int testPoint(Complex c) {
        Complex z = new Complex();
        for (int i = 1; i <= iterationLimit; i++) {
            z = z.square().add(c);
            if (z.abs() > 2) {
                return i;
            }
        }
        return -1;
    }
}