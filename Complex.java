public class Complex {
    private double real;
    private double imag;

    public Complex() {
        this.real = 0;
        this.imag = 0;
    }

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {
        return this.real;
    }  

    public double getImaginary() {
        return this.imag;
    }

    public boolean equals(Complex other) {
        return this.real == other.real && this.imag == other.imag;
    }

    public String toString() {
        if (this.imag < 0) {
            return real + (imag + "i");
        } else {
            return real + "+" + imag + "i";
        }
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imag + other.imag);
    }

    public Complex add(double real) {
        return this.add(new Complex(real, 0));
    }

    public Complex subtract(Complex other) {
        return new Complex(this.real - other.real, this.imag - other.imag);
    }

    public Complex subtract(double real) {
        return this.subtract(new Complex(real, 0));
    }

    public Complex multiply(Complex other) {
        double multReal = (this.real * other.real) - (this.imag * other.imag);
        double multImag = (this.real * other.imag) + (this.imag * other.real);
        return new Complex(multReal, multImag);
    }

    public Complex multiply(double real) {
        return this.multiply(new Complex(real, 0));
    }

    public Complex divide(Complex other) {
        double newReal = this.real * other.real + other.imag * this.imag;
        double newImag = other.real * this.imag - this.real * other.imag;
        double divisor = other.real * other.real + other.imag * other.imag;
        return new Complex(newReal / divisor, newImag / divisor);
    }

    public Complex divide(double real) {
        return this.divide(new Complex(real, 0));
    }

    public Complex square() {
        double newReal = this.real * this.real + (this.imag * this.imag * -1);
        double newImag = 2 * (this.real * this.imag);
        return new Complex(newReal, newImag);
    }

    public double abs() {
        return Math.sqrt((this.real * this.real) + (this.imag * this.imag));
    }
}