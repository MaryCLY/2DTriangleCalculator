public class MyVector {
    private double x;
    private double y;

    public void set(double X, double Y) {
        x = X;
        y = Y;
    }

    public void set(MyPoint start, MyPoint stop) {
        set(stop.getX() - start.getX(), stop.getY() - start.getY());
    }

    public void set(MyLine line) {
        set(line.getA(), line.getB());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLength() {
        return Math.pow((x * x + y * y), 0.5);
    }

    public MyVector getDirection() {
        return new MyVector(x / getLength(), y / getLength());
    }

    public MyVector() {
        set(0, 0);
    }

    public MyVector(double X, double Y) {
        set(X, Y);
    }

    public MyVector(MyPoint start, MyPoint stop) {
        set(stop.getX() - start.getX(), stop.getY() - start.getY());
    }

    public MyVector(MyLine line) {
        //A到B
        set(line.getA(), line.getB());
    }
}
