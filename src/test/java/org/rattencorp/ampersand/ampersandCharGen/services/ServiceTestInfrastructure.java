package org.rattencorp.ampersand.ampersandCharGen.services;

import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacter;
import org.rattencorp.ampersand.core.attributes.AmpersandAttribute;
import org.rattencorp.ampersandlike.classes.AmpersandClass;

import java.util.function.Predicate;

public class ServiceTestInfrastructure {
    static class TestAmpersandClassAlwaysPossible implements AmpersandClass {

        @Override
        public Predicate<AmpersandCharacter> prerequisiteSupplier() {
            return ignored -> true;
        }
    }

    static class TestAmpersandClassAlwaysFine implements AmpersandClass {

        @Override
        public Predicate<AmpersandCharacter> prerequisiteSupplier() {
            return ignored -> true;
        }
    }

    static class TestAmpersandClassNeverPossible implements AmpersandClass {

        @Override
        public Predicate<AmpersandCharacter> prerequisiteSupplier() {
            return ignored -> false;
        }
    }

    static class TestAmpersandClassNeedsInt implements AmpersandClass {

        @Override
        public Predicate<AmpersandCharacter> prerequisiteSupplier() {
            return c -> c.getAttributeAssignment().getValueForAttribute(AmpersandAttribute.INT) > 12;
        }
    }
}