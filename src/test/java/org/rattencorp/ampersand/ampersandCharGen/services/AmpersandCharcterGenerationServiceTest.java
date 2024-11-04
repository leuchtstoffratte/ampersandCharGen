package org.rattencorp.ampersand.ampersandCharGen.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacter;
import org.rattencorp.ampersand.core.attributes.AmpersandAttribute;
import org.rattencorp.ampersand.core.attributes.AmpersandAttributeAssignmentImpl;
import org.rattencorp.ampersandlike.classes.AmpersandClass;


@ExtendWith(MockitoExtension.class)
class AmpersandCharcterGenerationServiceTest {

    @Mock
    private AmpersandCharcterGenerationService testee;


    @Mock
    private AmpersandAttributeGenerator ampersandAttributeGenerator;

    @Mock
    private AmpersandClassAssigner ampersandClassAssigner;


    private AmpersandAttributeAssignmentImpl defaultAttributeAssignment;




    @BeforeEach
    void setUp() {
        testee = new AmpersandCharcterGenerationService(ampersandAttributeGenerator, ampersandClassAssigner);

        defaultAttributeAssignment = new AmpersandAttributeAssignmentImpl();
        defaultAttributeAssignment.assignAttribute(AmpersandAttribute.INT, 9);
    }


    @Test
    void createCharacterShouldAssignAttributesAndAssignClass(){

        //GIVEN
        Mockito.doReturn(defaultAttributeAssignment).when(ampersandAttributeGenerator).getCurrentAssignment();

        final Class<? extends AmpersandClass> firstLevel = ServiceTestInfrastructure.TestAmpersandClassAlwaysPossible.class;

        //WHEN
        final AmpersandCharacter result = testee.createCharacter(firstLevel);

        //THEN
        Mockito.verify(ampersandClassAssigner, Mockito.times(1)).assignClassToCharacter(Mockito.any(AmpersandCharacter.class), Mockito.eq(firstLevel));
        Mockito.verify(ampersandAttributeGenerator, Mockito.times(1)).generateAttributeValues();
        Mockito.verify(ampersandAttributeGenerator, Mockito.times(1)).getCurrentAssignment();
        Assertions.assertThat(result).isNotNull();
    }





}