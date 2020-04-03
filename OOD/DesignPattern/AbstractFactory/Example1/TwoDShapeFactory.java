package OOD.DesignPattern.AbstractFactory.Example1;

import OOD.DesignPattern.Factory.*;

public class TwoDShapeFactory extends AbstractFactory {
    @Override
    GeometricShape getShape(ShapeType name) {
        if (name == ShapeType.LINE) {
            return new Line();
        } else if (name == ShapeType.CIRCLE) {
            return new Circle();
        } else if (name == ShapeType.RECTANGLE) {
            return new Rectangle();
        }
        return null;
    }
}
