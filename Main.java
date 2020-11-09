public class Main {

    public static void main(String[] args) {
        Triangle2D t1 = new Triangle2D(new MyPoint(2.5, 2), new MyPoint(4.2, 3), new MyPoint(5, 3.5));
        System.out.println("面积：" + t1.getArea());
        System.out.println("周长：" + t1.getPerimeter());
        System.out.println("包含(3,3)吗？" + t1.contain(new MyPoint(3, 3)));
        System.out.println("包含示例三角形吗？" + t1.contain(new Triangle2D(new MyPoint(2.9, 2), new MyPoint(4, 1), new MyPoint(1, 3.4))));
        System.out.println("和示例三角形重叠吗？" + t1.overlaps(new Triangle2D(new MyPoint(2, 5.5), new MyPoint(4, -3), new MyPoint(2, 6.5))));
    }
}
