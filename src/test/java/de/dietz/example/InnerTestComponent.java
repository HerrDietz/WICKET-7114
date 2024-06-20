package de.dietz.example;

public class InnerTestComponent extends TestComponent {

    public InnerTestComponent(String id) {
        super(id);

        for (int i = 0; i < 11; i++) {
            repeater.add(newLabel(repeater.newChildId()));
        }
    }


}
