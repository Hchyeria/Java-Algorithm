package OOD.DesignPattern.AbstractFactory.Example1;


import OOD.DesignPattern.Factory.GeometricShape;
import OOD.DesignPattern.Factory.ShapeType;

public abstract class AbstractFactory {
    abstract GeometricShape getShape(ShapeType name);
}
