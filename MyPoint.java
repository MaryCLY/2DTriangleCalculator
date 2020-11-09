public class MyPoint {
    private double x;
    private double y;

    //坐标
    public void set(double X, double Y) {
        x = X;
        y = Y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public MyPoint() {
        set(0, 0);
    }

    public MyPoint(double X, double Y) {
        set(X, Y);
    }
}

