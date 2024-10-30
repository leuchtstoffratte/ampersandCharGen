package org.rattencorp.ampersand.core.ampersandClasses.coreClasses;

import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacter;
import org.rattencorp.ampersandlike.classes.AmpersandClass;
import org.rattencorp.ampersandlike.classes.Caster;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@Caster
public class Wizard implements AmpersandClass {
    @Override
    public Predicate<AmpersandCharacter> prerequisiteSupplier() {
        return null;
    }
}
