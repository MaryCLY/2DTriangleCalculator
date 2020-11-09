public class MyLine {
    private MyPoint a;
    private MyPoint b;

    public MyLine(){
        set(new MyPoint(0,0),new MyPoint(0,0));
    }

    public MyLine(MyPoint A, MyPoint B){
        a=A;
        b=B;
    }

    public void set(MyPoint A, MyPoint B){
        a=A;
        b=B;
    }

    public MyPoint getA(){
        return a;
    }

    public MyPoint getB(){
        return b;
    }
}
