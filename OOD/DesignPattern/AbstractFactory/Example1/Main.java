package OOD.DesignPattern.AbstractFactory.Example1;

import OOD.DesignPattern.Factory.GeometricShape;
import OOD.DesignPattern.Factory.ShapeType;

public class Main {
    public static void main(String[] args) {
        AbstractFactory factory = FactoryProvider.getFactory(FactoryType.TWO_D_SHAPE_FACTORY);
        GeometricShape shape = factory.getShape(ShapeType.LINE);
        if (shape != null) {
            shape.draw();
        }

        factory = FactoryProvider.getFactory(FactoryType.THREE_D_SHAPE_FACTORY);
        shape = factory.getShape(ShapeType.SPHERE);
        shape.draw();
    }
}
