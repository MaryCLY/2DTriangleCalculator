
public class Triangle2D {
    private MyPoint p1 = new MyPoint();
    private MyPoint p2 = new MyPoint();
    private MyPoint p3 = new MyPoint();
    //三个顶点，分别约定为点ABC
    private final MyLine lineAB = new MyLine();
    private final MyLine lineAC = new MyLine();
    private final MyLine lineBC = new MyLine();

    private final MyVector vectorAB = new MyVector();
    private final MyVector vectorAC = new MyVector();
    private final MyVector vectorBC = new MyVector();

    public Triangle2D() {
        p1.set(0, 0);
        p2.set(1, 1);
        p3.set(2, 5);
        setLines();
    }

    public Triangle2D(MyPoint P1, MyPoint P2, MyPoint P3) {
        //创建指定顶点的三角形，输入格式是三个点
        p1 = P1;
        p2 = P2;
        p3 = P3;
        setLines();
    }

    private void setLines() {
        lineAB.set(p1, p2);
        lineAC.set(p1, p3);
        lineBC.set(p2, p3);
        setVectors();
    }

    private void setVectors() {
        vectorAB.set(lineAB);
        vectorAC.set(lineAC);
        vectorBC.set(lineBC);
    }


    public double getArea() {
        //求面积
        return Math.abs(vectorAB.getX() * vectorAC.getY() - vectorAB.getY() * vectorAC.getX()) / 2;
    }

    public double getPerimeter() {
        //求周长
        return vectorAB.getLength() + vectorAC.getLength() + vectorBC.getLength();
    }

    public boolean contain(MyPoint p) {
        //如果该点跟某端点重合，点P肯定不在三角形里
        if (Equal(p, p1) || Equal(p, p2) || Equal(p, p3)) {
            return false;
        } else {
            MyVector vectorPA = new MyVector(p, p1);
            MyVector vectorPB = new MyVector(p, p2);
            MyVector vectorPC = new MyVector(p, p3);
            //如果某两个向量共线，点P肯定不在三角形里，也无法正常形成三个三角形
            if (Collinear(vectorPA, vectorPB) || Collinear(vectorPA, vectorPC) || Collinear(vectorPB, vectorPC)) {
                return false;
            } else {
                Triangle2D trianglePAB = new Triangle2D(p, p1, p2);
                Triangle2D trianglePAC = new Triangle2D(p, p1, p3);
                Triangle2D trianglePBC = new Triangle2D(p, p2, p3);
                return trianglePAB.getArea() + trianglePAC.getArea() + trianglePBC.getArea() == getArea();
                //如果分成三个小三角形的面积和等于大三角形的面积，点P在三角形内
            }
        }

    }

    public boolean contain(Triangle2D t) {
        return contain(t.p1) && contain(t.p2) && contain(t.p3);
        //如果三个点都在大三角形里，小三角形一定包含
    }

    public boolean overlaps(Triangle2D t) {
        if (contain(t)) {
            return false;
            //如果t在本三角形里，那么一定不重叠
            //如果任意两边相交则重叠，如果都不相交则不重叠
        } else if (Intersect(t.lineAB, lineAB) || Intersect(t.lineAB, lineAC) || Intersect(t.lineAB, lineBC)) {
            return true;
        } else if (Intersect(t.lineAC, lineAB) || Intersect(t.lineAC, lineAC) || Intersect(t.lineAC, lineBC)) {
            return true;
        } else
            return Intersect(t.lineBC, lineAB) || Intersect(t.lineBC, lineAC) || Intersect(t.lineBC, lineBC);
    }

    public boolean Equal(MyPoint p1, MyPoint p2) {
        //判断点相等的辅助函数
        return p1.getX() == p2.getX() && p1.getY() == p2.getY();
    }

    public boolean Equal(MyVector v1, MyVector v2) {
        //判断向量相等的辅助函数
        return v1.getX() == v2.getX() && v1.getY() == v2.getY();
    }

    public boolean Opposite(MyVector v1, MyVector v2) {
        //判断向量相反的辅助函数
        return v1.getX() == (-v2.getX()) && v1.getY() == (-v2.getY());
    }

    public boolean Collinear(MyVector v1, MyVector v2) {
        //判断向量共线的辅助函数
        return Equal(v1.getDirection(), v2.getDirection()) || Opposite(v1.getDirection(), v2.getDirection());
    }

    public boolean Intersect(MyPoint p, MyLine l) {
        //判断点在线上的辅助函数
        if (Equal(p, l.getA()) || Equal(p, l.getB())) {
            return true;
            //如果点跟线的端点重合，则点一定在线上
        } else {
            MyVector vectorPA = new MyVector(p, l.getA());
            MyVector vectorPB = new MyVector(p, l.getB());
            return Opposite(vectorPA, vectorPB);
            //如果PA和PB相反，则点一定在线上，否则不在
        }
    }

    public boolean Intersect(MyLine l1, MyLine l2) {
        //判断两条边有相交的辅助函数
        //如果某一边的端点在另一边上，那么一定有相交
        if (Intersect(l1.getA(), l2) || Intersect(l1.getB(), l2)) {
            return true;
        } else if (Intersect(l2.getA(), l1) || Intersect(l2.getB(), l1)) {
            return true;
        } else {
            //判断两条边是否平行，如果是，那么一定不相交
            MyVector vectorL1 = new MyVector(l1);
            MyVector vectorL2 = new MyVector(l2);
            if (Collinear(vectorL1, vectorL2)) {
                return false;
            } else {
                //判断两条边的X是否重叠，如果不重叠，那么一定不相交
                double left1 = Math.min(l1.getA().getX(), l1.getB().getX());
                double right1 = Math.max(l1.getA().getX(), l1.getB().getX());
                double left2 = Math.min(l2.getA().getX(), l2.getB().getX());
                double right2 = Math.max(l2.getA().getX(), l2.getB().getX());

                double left = Math.max(left1, left2);
                double right = Math.min(right1, right2);
                //取窄的范围

                //如果left在right的右边，说明x的范围没有交汇（相等也有交汇）
                if (left > right) {
                    return false;
                } else {
                    //算两条边对应的直线的交点，令直线方程为y=kx+b
                    //k=(ya-yb)/(xa-xb)
                    double k1 = (l1.getA().getY() - l1.getB().getY()) / (l1.getA().getX() - l1.getB().getX());
                    double k2 = (l2.getA().getY() - l2.getB().getY()) / (l2.getA().getX() - l2.getB().getX());
                    //b=y-kx
                    double b1 = l1.getA().getY() - l1.getA().getX() * k1;
                    double b2 = l2.getA().getY() - l2.getA().getX() * k2;

                    double intersectionX = (b2 - b1) / (k1 - k2);
                    //如果交点的X坐标在l1和l2的X坐标共同范围，那么两条边相交，否则不相交
                    return intersectionX >= left && intersectionX <= right;
                }
            }
        }
    }
}
