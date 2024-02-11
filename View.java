public class View{
    private int screenWidth;
    private int screenHeight;
    private double xSlope;
    private double xOffset;
    private double ySlope;
    private double yOffset;

    //initialize the view 
    public View(int w, int h){
        screenWidth = w;
        screenHeight = h;
        setComplexCorners(new Complex(-2,1),new Complex(1,-1));
    }
    
    //translate a point
    public Complex translate(int x, int y){
        double real = xSlope * x + xOffset;
        double imag = ySlope * y + yOffset;
        return new Complex(real, imag);
    }
    
    //set or update screen zoom
    public void setComplexCorners(Complex topLeft, Complex botRight){
        xOffset = topLeft.getReal();
        xSlope = (botRight.getReal() - this.xOffset) / screenWidth;
        yOffset = topLeft.getImaginary();
        ySlope = xSlope * -1;
    }
}