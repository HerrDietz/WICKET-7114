package de.dietz;

import org.apache.wicket.DefaultMarkupIdGenerator;
import org.apache.wicket.IMarkupIdGenerator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class DefaultMarkupIdGeneratorTest {

    @BeforeEach
    public void setup() {
        //Attaches a Wicket Application to current threat
        new WicketTester();}

    /**
     * This test generates a sequence of ids, using Components with well known ids.
     */
    private void testMarkupGeneratorImpl(IMarkupIdGenerator generator){
        Label firstLabel = new Label("11", "");

        Set<String> generatedMarkupIds = new HashSet<>();

        String firstMarkupId = generator.generateMarkupId(firstLabel, true);
        generatedMarkupIds.add(firstMarkupId);

        for (int i=0;i<=15;i++){
            Label label = new Label("1");
            String generatedMarkupId = generator.generateMarkupId(label, true);
            if (generatedMarkupIds.contains(generatedMarkupId))
            {
                Assertions.fail("Markup id " + generatedMarkupId + " generated twice");
            }
        }
    }

    @Test
    public void testDefaultMarkupIdGenerator(){
        testMarkupGeneratorImpl(new DefaultMarkupIdGenerator());
    }

    @Test
    public void testPatchedDefaultMarkupIdGenerator(){
        testMarkupGeneratorImpl(new PatchedDefaultMarkupIdGenerator());
    }
}
