package core_oop;

public class InterfaceDemo {
    public static void main(String[] args) {
        Circle myCircle = new Circle(10.0);
        Shape shape = myCircle;

        System.out.println("Demonstrating Interface Implementation: ");
        shape.display();

        double area = shape.getArea();
        System.out.printf("The calculated area is: %.2f%n", area);
    }
}