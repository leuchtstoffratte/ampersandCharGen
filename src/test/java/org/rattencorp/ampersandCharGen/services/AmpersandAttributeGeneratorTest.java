package org.rattencorp.ampersandCharGen.services;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.rattencorp.ampersandCharGen.core.attributes.AmpersandAttribute;
import org.rattencorp.ampersandCharGen.core.attributes.AmpersandAttributeAssignmentImpl;

import java.util.List;

class AmpersandAttributeGeneratorTest {


    private static final int[] ATTRIBUTES_FOR_TEST = new int[]{1,2,3,4,5,6};

    private final AmpersandAttributeGenerator testee = new AmpersandAttributeGenerator(()->ATTRIBUTES_FOR_TEST);

    @Test
    void generateAttributeValuesShouldYieldValuesFromTestGenerator() {
        //GIVEN
        testee.generateAttributeValues();

        //WHEN
        final List<Integer> result = testee.getAvailableValues();

        //THEN
        Assertions.assertThat(result).contains(1,2,3,4,5,6);
    }



    @Test
    void generateAttributeAssignmentShouldNotPreassignValues() {
        //GIVEN
        testee.generateAttributeValues();

        //WHEN
        final AmpersandAttributeAssignmentImpl result = testee.getCurrentAssignment();

        //THEN
        Assertions.<AmpersandAttributeAssignmentImpl>assertThat(result).isEqualTo(new AmpersandAttributeAssignmentImpl());

    }


    @ParameterizedTest
    @EnumSource(AmpersandAttribute.class)
    void assignAttributeShouldAssignAttributeProperly(AmpersandAttribute attribute) {
        //GIVEN
        testee.generateAttributeValues();
        final int value = testee.getAvailableValues().getFirst();

        //WHEN
        testee.assignValueToAttribute(value, attribute);

        //THEN
        Assertions.assertThat(testee.getCurrentAssignment().getValueForAttribute(attribute)).isEqualTo(value);

    }

    @Test
    void assignAttributeShouldRemoveValueFromAvailableValues() {
        //GIVEN
        testee.generateAttributeValues();
        final int value = testee.getAvailableValues().getFirst();

        //WHEN
        testee.assignValueToAttribute(value, AmpersandAttribute.STR);

        //THEN
        Assertions.assertThat(testee.getAvailableValues()).doesNotContain(value);
    }


}