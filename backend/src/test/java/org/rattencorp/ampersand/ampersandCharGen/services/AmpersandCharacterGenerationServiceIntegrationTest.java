package org.rattencorp.ampersand.ampersandCharGen.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacterBuilder;
import org.rattencorp.ampersand.core.attributes.AmpersandAttribute;
import org.rattencorp.ampersand.core.attributes.AmpersandAttributeAssignmentImpl;
import org.rattencorp.ampersandlike.classes.AmpersandClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@Disabled("will build eventually")
@ActiveProfiles("test")
@SpringBootTest
class AmpersandCharacterGenerationServiceIntegrationTest {

    @Autowired
    private AmpersandCharcterGenerationService testee;


    private AmpersandAttributeAssignmentImpl defaultAttributeAssignment;




    @BeforeEach
    void setUp() {
        defaultAttributeAssignment = new AmpersandAttributeAssignmentImpl();
        defaultAttributeAssignment.assignAttribute(AmpersandAttribute.INT, 9);
    }


    @Test
    void newlyCreatedCharacterShouldBeFirstLevel(){

        //GIVEN

        //WHEN
        final AmpersandCharacterBuilder result = testee.createCharacter(ServiceTestInfrastructure.TestAmpersandClassAlwaysPossible.class);

        //THEN
        Assertions.assertThat(result.getLevel()).isEqualTo(1);
    }


    @Test
    void createCharacterShouldAssignASingleLevelOfGiveAmpersandClass(){

        //GIVEN

        final Class<? extends AmpersandClass> firstLevel = ServiceTestInfrastructure.TestAmpersandClassAlwaysPossible.class;

        //WHEN
        final AmpersandCharacterBuilder result = testee.createCharacter(firstLevel);

        //THEN
        Assertions.assertThat(result).isNotNull();
    }




}