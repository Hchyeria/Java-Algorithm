package OOD.DesignPattern.Factory;

public class Main {
    public static void main(String[] args) {
        GeometricShape circle = ShapeFactory.getShape(ShapeType.CIRCLE);
        circle.draw();
        GeometricShape rectangle = ShapeFactory.getShape(ShapeType.RECTANGLE);
        rectangle.draw();
    }
}
