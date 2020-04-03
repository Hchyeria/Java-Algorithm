package OOD.DesignPattern.AbstractFactory.Example2;

import java.util.List;

interface Button {
    void pain();
}

class WinButton implements Button {

    @Override
    public void pain() {
        System.out.println("WinButton");
    }
}

class OSXButton implements Button {

    @Override
    public void pain() {
        System.out.println("OSXButton");
    }
}

interface GUIFactory {
    Button createButton();
}

class WinFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WinButton();
    }
}

class OSXFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new OSXButton();
    }
}

public class AbstractFactoryExample {
    private static GUIFactory factory(String appearance) {
        switch (appearance) {
            case "osx":
                return new OSXFactory();
            case "win":
                return new WinFactory();
        }
        return null;
    }

    public static void main(final String[] args) {
        GUIFactory factory = factory("win");
        Button button = factory.createButton();
        button.pain();

        factory = factory("osx");
        button = factory.createButton();
        button.pain();
    }
}
