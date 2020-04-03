package OOD.DesignPattern.AbstractFactory.Example1;

import OOD.DesignPattern.Factory.GeometricShape;
import OOD.DesignPattern.Factory.ShapeType;
import OOD.DesignPattern.Factory.Sphere;

public class ThreeDShapeFactory extends AbstractFactory {
    @Override
    GeometricShape getShape(ShapeType name) {
        if (ShapeType.SPHERE == name) {
            return new Sphere();
        }
        return null;
    }
}
