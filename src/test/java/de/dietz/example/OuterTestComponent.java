package de.dietz.example;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

public class OuterTestComponent extends TestComponent {

    Model<String> model = Model.of(".");

    public OuterTestComponent(String id) {
        super(id);

        for (int i = 0; i < 3; i++) {
            RepeatingView childRepeater = new RepeatingView(repeater.newChildId());
            repeater.add(childRepeater);
            childRepeater.add(newLabel(childRepeater.newChildId()));
            childRepeater.add(new InnerTestComponent(childRepeater.newChildId()));
        }


    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        tag.setName("div");
    }
}