package org.rattencorp.ampersand.ampersandCharGen.services;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacter;
import org.rattencorp.ampersand.core.ampersandClasses.classProviders.AmpersandClassProvider;
import org.rattencorp.ampersand.core.attributes.AmpersandAttribute;
import org.rattencorp.ampersand.core.attributes.AmpersandAttributeAssignmentImpl;
import org.rattencorp.ampersandlike.classes.AmpersandClass;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class AmpersandClassAssignerTest {

    private AmpersandClassAssigner testee;

    @Mock
    private ConfigurableApplicationContext ctxMock;

    @Mock
    private AmpersandClassProvider ampersandClassProviderMock;

    @Mock
    private AmpersandClassProvider otherAmpersandClassProviderMock;

    @Mock
    private AmpersandCharacter ampersandCharacterMock;

    private static final AmpersandAttributeAssignmentImpl ATTRIBUTE_DUMB = new AmpersandAttributeAssignmentImpl();
    private static final AmpersandAttributeAssignmentImpl ATTRIBUTE_SMART = new AmpersandAttributeAssignmentImpl();

    private static final ServiceTestInfrastructure.TestAmpersandClassNeverPossible NEVER_POSSIBLE = new ServiceTestInfrastructure.TestAmpersandClassNeverPossible();
    private static final ServiceTestInfrastructure.TestAmpersandClassAlwaysPossible ALWAYS_POSSIBLE = new ServiceTestInfrastructure.TestAmpersandClassAlwaysPossible();
    private static final ServiceTestInfrastructure.TestAmpersandClassAlwaysFine ALWAYS_FINE = new ServiceTestInfrastructure.TestAmpersandClassAlwaysFine();
    private static final ServiceTestInfrastructure.TestAmpersandClassNeedsInt NEEDS_INT = new ServiceTestInfrastructure.TestAmpersandClassNeedsInt();


    @BeforeAll
    static void preSetup() {
        ATTRIBUTE_DUMB.assignAttribute(AmpersandAttribute.INT, 5);
        ATTRIBUTE_SMART.assignAttribute(AmpersandAttribute.INT, 15);
    }


    @BeforeEach
    void setUp() {

        testee = new AmpersandClassAssigner(List.of(ampersandClassProviderMock, otherAmpersandClassProviderMock), ctxMock);

        //Classes are stateless tokens that should never be anything but singletons
        Mockito.doReturn(NEVER_POSSIBLE).when(ctxMock).getBean(ServiceTestInfrastructure.TestAmpersandClassNeverPossible.class);
        Mockito.doReturn(ALWAYS_POSSIBLE).when(ctxMock).getBean(ServiceTestInfrastructure.TestAmpersandClassAlwaysPossible.class);
        Mockito.doReturn(ALWAYS_FINE).when(ctxMock).getBean(ServiceTestInfrastructure.TestAmpersandClassAlwaysFine.class);
        Mockito.doReturn(NEEDS_INT).when(ctxMock).getBean(ServiceTestInfrastructure.TestAmpersandClassNeedsInt.class);
    }


    @Test
    void gettingAllClassesReturnsEachClassOnce() {
        //GIVEN
        Mockito.doReturn(Set.of(ServiceTestInfrastructure.TestAmpersandClassAlwaysPossible.class, ServiceTestInfrastructure.TestAmpersandClassNeedsInt.class))
                .when(ampersandClassProviderMock).getAmpersandClasses();
        Mockito.doReturn(Set.of(ServiceTestInfrastructure.TestAmpersandClassAlwaysFine.class, ServiceTestInfrastructure.TestAmpersandClassNeedsInt.class, ServiceTestInfrastructure.TestAmpersandClassNeverPossible.class))
                .when(otherAmpersandClassProviderMock).getAmpersandClasses();

        //WHEN
        final Set<AmpersandClass> result = testee.getAllClasses();

        //THEN
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(result).hasSize(4),
                () -> Assertions.assertThat(result).contains(NEVER_POSSIBLE),
                () -> Assertions.assertThat(result).contains(ALWAYS_POSSIBLE),
                () -> Assertions.assertThat(result).contains(ALWAYS_FINE),
                () -> Assertions.assertThat(result).contains(NEEDS_INT)
        );
    }



    @Test
    void gettingOptionsForDumbCharacterShouldNotContainSmartOrNeverClasses() {
        //GIVEN
        Mockito.doReturn(Set.of(ServiceTestInfrastructure.TestAmpersandClassAlwaysPossible.class, ServiceTestInfrastructure.TestAmpersandClassNeedsInt.class))
                .when(ampersandClassProviderMock).getAmpersandClasses();
        Mockito.doReturn(Set.of(ServiceTestInfrastructure.TestAmpersandClassAlwaysFine.class, ServiceTestInfrastructure.TestAmpersandClassNeedsInt.class, ServiceTestInfrastructure.TestAmpersandClassNeverPossible.class))
                .when(otherAmpersandClassProviderMock).getAmpersandClasses();

        Mockito.doReturn(ATTRIBUTE_DUMB).when(ampersandCharacterMock).getAttributeAssignment();

        //WHEN
        final Set<AmpersandClass> result = testee.getAllOptionsForChar(ampersandCharacterMock);

        //THEN
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(result).hasSize(2),
                () -> Assertions.assertThat(result).contains(ALWAYS_POSSIBLE),
                () -> Assertions.assertThat(result).contains(ALWAYS_FINE),
                () -> Assertions.assertThat(result).doesNotContain(NEVER_POSSIBLE),
                () -> Assertions.assertThat(result).doesNotContain(NEEDS_INT)
        );
    }

    @Test
    void gettingOptionsForSmartCharacterShouldNotNeverClasses() {
        //GIVEN
        Mockito.doReturn(Set.of(ServiceTestInfrastructure.TestAmpersandClassAlwaysPossible.class, ServiceTestInfrastructure.TestAmpersandClassNeedsInt.class))
                .when(ampersandClassProviderMock).getAmpersandClasses();
        Mockito.doReturn(Set.of(ServiceTestInfrastructure.TestAmpersandClassAlwaysFine.class, ServiceTestInfrastructure.TestAmpersandClassNeedsInt.class, ServiceTestInfrastructure.TestAmpersandClassNeverPossible.class))
                .when(otherAmpersandClassProviderMock).getAmpersandClasses();

        Mockito.doReturn(ATTRIBUTE_SMART).when(ampersandCharacterMock).getAttributeAssignment();

        //WHEN
        final Set<AmpersandClass> result = testee.getAllOptionsForChar(ampersandCharacterMock);

        //THEN
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(result).hasSize(3),
                () -> Assertions.assertThat(result).contains(ALWAYS_POSSIBLE),
                () -> Assertions.assertThat(result).contains(ALWAYS_FINE),
                () -> Assertions.assertThat(result).contains(NEEDS_INT),
                () -> Assertions.assertThat(result).doesNotContain(NEVER_POSSIBLE)
        );
    }

    @Test
    void gettingOptionsForCharacterShouldNotBlowUpOnEmptyListProvided() {
        //GIVEN
        Mockito.doReturn(Collections.emptySet())
                .when(ampersandClassProviderMock).getAmpersandClasses();
        Mockito.doReturn(Set.of(ServiceTestInfrastructure.TestAmpersandClassAlwaysPossible.class, ServiceTestInfrastructure.TestAmpersandClassAlwaysFine.class,
                        ServiceTestInfrastructure.TestAmpersandClassNeedsInt.class, ServiceTestInfrastructure.TestAmpersandClassNeverPossible.class))
                .when(otherAmpersandClassProviderMock).getAmpersandClasses();

        Mockito.doReturn(ATTRIBUTE_DUMB).when(ampersandCharacterMock).getAttributeAssignment();

        //WHEN
        final Set<AmpersandClass> result = testee.getAllOptionsForChar(ampersandCharacterMock);

        //THEN
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(result).hasSize(2),
                () -> Assertions.assertThat(result).contains(ALWAYS_POSSIBLE),
                () -> Assertions.assertThat(result).contains(ALWAYS_FINE)
        );
    }






}