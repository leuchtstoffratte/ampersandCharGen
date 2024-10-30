package org.rattencorp.ampersand.core.ampersandClasses.coreClasses;

import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacter;
import org.rattencorp.ampersandlike.classes.AmpersandClass;
import org.rattencorp.ampersandlike.classes.MartialClass;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@MartialClass
public class Rogue implements AmpersandClass {
    @Override
    public Predicate<AmpersandCharacter> prerequisiteSupplier() {
        return null;
    }
}
