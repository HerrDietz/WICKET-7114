package de.dietz;

import de.dietz.example.OuterTestComponent;
import org.apache.wicket.Component;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.util.visit.IVisitor;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

public class TestHomePage {

    /**
     * This test renders a page using a specific RuntimeConfigurationType and searches for duplicated IDs.
     * The page is specially crafted. In RuntimeConfigurationType.DEVELOPMENT, it will contain duplicated IDs.
     */
    private void runComponentAndCheckForDuplicateIds(RuntimeConfigurationType configurationType) {
        WicketTester tester = new WicketTester(new MockApplication() {
            @Override
            public RuntimeConfigurationType getConfigurationType() {
                return configurationType;
            }
        });
        OuterTestComponent testComponent = new OuterTestComponent("anyId");
        tester.startComponentInPage(testComponent);

        Map<String, Component> idToComponents = new HashMap<String, Component>();
        testComponent.visitChildren((IVisitor<Component, Void>) (newComponent, v) -> {
            String newMarkupId = newComponent.getMarkupId();
            if (idToComponents.containsKey(newMarkupId)) {
                Component oldComponent = idToComponents.get(newMarkupId);
                fail(newMarkupId + " was used twice.\nFirst Match:\t" + oldComponent.getPath() + "\nSecond Match\t" + newComponent.getPath());
            }
            idToComponents.put(newMarkupId, newComponent);
        });

    }


    @Test
    public void testNoDuplicateIdsInPageInModeDevelopment() {
        runComponentAndCheckForDuplicateIds(RuntimeConfigurationType.DEVELOPMENT);

    }

    @Test
    public void testNoDuplicateIdsInPageInModeDeployment() {
        runComponentAndCheckForDuplicateIds(RuntimeConfigurationType.DEPLOYMENT);

    }
}
