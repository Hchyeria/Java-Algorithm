package OOD.DesignPattern.AbstractFactory.Example1;

public class FactoryProvider {
    public static AbstractFactory getFactory(FactoryType factoryType) {
        if (factoryType == FactoryType.TWO_D_SHAPE_FACTORY) {
            return new TwoDShapeFactory();
        } else if (factoryType == FactoryType.THREE_D_SHAPE_FACTORY) {
            return new ThreeDShapeFactory();
        }
        return null;
    }
}
