package de.dietz.example;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

public abstract class TestComponent extends Panel {

    Model<String> model = Model.of(".");

    final RepeatingView repeater;

    public TestComponent(String id) {
        super(id);
        this.setRenderBodyOnly(false);
        this.setOutputMarkupId(true);

        repeater = new RepeatingView("repeater");
        this.add(repeater);
    }

    Label newLabel(String wicketId) {
        Label label = new Label(wicketId, model);
        label.setRenderBodyOnly(false);
        label.setOutputMarkupId(true);
        return label;
    }
}
