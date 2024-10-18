package org.rattencorp.ampersandCharGen.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rattencorp.ampersandCharGen.core.attributes.AmpersandAttributeAssignmentImpl;

class AmpersandAttributeGeneratorTest {


    private static final int[] ATTRIBUTES_FOR_TEST = new int[]{1,2,3,4,5,6};

    private final AmpersandAttributeGenerator testee = new AmpersandAttributeGenerator(()->ATTRIBUTES_FOR_TEST);

    @Test
    void generateAttributeValuesShouldYieldValuesFromTestGenerator() {
        //GIVEN
        testee.generateAttributeValues();

        //WHEN
        final String result = testee.displayRemainingChoises();

        //THEN
        Assertions.assertThat(result).contains("1").contains("2").contains("3").contains("4").contains("5").contains("6");
    }



    @Test
    void generateAttributeAssignment() {
        //GIVEN

        //WHEN
        final AmpersandAttributeAssignmentImpl result = testee.generateAttributeAssignment();

        //THEN
        Assertions.<AmpersandAttributeAssignmentImpl>assertThat(result).isEqualTo(new AmpersandAttributeAssignmentImpl());

    }
}