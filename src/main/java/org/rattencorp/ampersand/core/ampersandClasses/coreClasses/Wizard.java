package org.rattencorp.ampersand.core.ampersandClasses.coreClasses;

import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacter;
import org.rattencorp.ampersand.core.attributes.AmpersandAttribute;
import org.rattencorp.ampersandlike.classes.AmpersandClass;
import org.rattencorp.ampersandlike.classes.Caster;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@Caster
public class Wizard implements AmpersandClass {

    /**
     * a Wizard-Level can be added to a {@link AmpersandCharacter} if:
     * <pre>
     *     a) the character has no class-levels yet
     *     b) the character has already a level of Wizard (only one subclass allowed, problem for subclasses)
     *     c) the character has a {@link org.rattencorp.ampersand.core.attributes.AmpersandAttribute#INT} &ge 13
     * </pre>
     */
    @Override
    public Predicate<AmpersandCharacter> prerequisiteSupplier() {
        return a -> {
            if(a.hasClass(this.getClass()) || a.getLevel() == 0){
                return true;
            }else {
                return a.getAttributeAssignment().getValueForAttribute(AmpersandAttribute.INT) >= 13;
            }
        };


    }
}
