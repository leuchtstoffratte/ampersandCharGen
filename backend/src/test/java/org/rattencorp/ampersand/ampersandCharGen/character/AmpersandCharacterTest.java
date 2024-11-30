package org.rattencorp.ampersand.ampersandCharGen.character;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rattencorp.ampersandlike.classes.AmpersandClass;

import java.util.function.Predicate;


class AmpersandCharacterTest {

    private AmpersandCharacter testee;

    private static class TestClassA implements AmpersandClass{

        @Override
        public Predicate<AmpersandCharacter> prerequisiteSupplier() {
            return ignored -> true;
        }
    }

    private static class TestSubClassOfA extends TestClassA{
        //intentionally blank
    }

    private static class TestClassB implements AmpersandClass{

        @Override
        public Predicate<AmpersandCharacter> prerequisiteSupplier() {
            return ignored -> true;
        }
    }

    private static class TestClassImpossible implements AmpersandClass{

        @Override
        public Predicate<AmpersandCharacter> prerequisiteSupplier() {
            return ignored -> false;
        }
    }

    @BeforeEach
    void setUp() {
        testee = new AmpersandCharacter(null); //null is ok for now, will need changes once attributes are subject to tests
    }


    @Test
    void levelShouldCountAllClasses() {
        //GIVEN
        testee.getClasses().push(new TestClassA());
        testee.getClasses().push(new TestClassB());

        //WHEN
        int result = testee.getLevel();

        //THEN
        Assertions.assertThat(result).isEqualTo(2);

    }

    @Test
    void blankCharacterHasNoClass() {
        //WHEN/THEN
        org.junit.jupiter.api.Assertions.assertAll(
                ()-> Assertions.assertThat(testee.hasClass(TestClassA.class)).isFalse(),
                ()-> Assertions.assertThat(testee.hasClass(TestClassB.class)).isFalse(),
                ()-> Assertions.assertThat(testee.hasClass(TestClassImpossible.class)).isFalse()
        );

    }

    @Test
    void firstLevelCharacterHasOnlyOneClass() {
        //GIVEN
        testee.getClasses().push(new TestClassA());

        //WHEN/THEN
        org.junit.jupiter.api.Assertions.assertAll(
                ()-> Assertions.assertThat(testee.hasClass(TestClassA.class)).isTrue(),
                ()-> Assertions.assertThat(testee.hasClass(TestClassB.class)).isFalse(),
                ()-> Assertions.assertThat(testee.hasClass(TestClassImpossible.class)).isFalse()
        );
    }

    @Test
    void firstLevelCharacterIsNotMultiClassed() {
        //GIVEN
        testee.getClasses().push(new TestClassA());

        //WHEN/THEN
        Assertions.assertThat(testee.isMultiClass()).isFalse();
    }

    @Test
    void characterWithOnlyLevelsInAClassAndItsOneOfItsSubClassesIsNotMultiClassed() {
        //GIVEN
        testee.getClasses().push(new TestClassA());
        testee.getClasses().push(new TestSubClassOfA());

        //WHEN/THEN
        Assertions.assertThat(testee.isMultiClass()).isFalse();

    }

    @Test
    void characterWithLevelsInTwoDistinctClassesIsMultiClassed() {
        //GIVEN
        testee.getClasses().push(new TestClassA());
        testee.getClasses().push(new TestClassB());

        //WHEN/THEN
        Assertions.assertThat(testee.isMultiClass()).isTrue();

    }

    @Test
    void levelInClassShouldCountBothClassAndSubClassLevels(){
        //GIVEN
        testee.getClasses().push(new TestClassA());
        testee.getClasses().push(new TestClassA());
        testee.getClasses().push(new TestSubClassOfA());
        testee.getClasses().push(new TestSubClassOfA());


        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(testee.getClassLevel(TestClassA.class)).isEqualTo(4),
                () -> Assertions.assertThat(testee.getClassLevel(TestSubClassOfA.class)).isEqualTo(4)
        );
    }


}