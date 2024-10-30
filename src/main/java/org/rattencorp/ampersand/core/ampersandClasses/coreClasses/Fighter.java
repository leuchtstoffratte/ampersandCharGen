package org.rattencorp.ampersand.core.ampersandClasses.coreClasses;

import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacter;
import org.rattencorp.ampersand.core.attributes.AmpersandAttribute;
import org.rattencorp.ampersandlike.classes.AmpersandClass;
import org.rattencorp.ampersandlike.classes.MartialClass;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@MartialClass
public class Fighter implements AmpersandClass {



    /**
     *
     * fighters can be added if:
     * <pre>
     *    a) the given character no class-levels yet
     *    b) the given character already has fighter levels (will be more complicated for subclasses)
     *    c) the given character has a {@link org.rattencorp.ampersand.core.attributes.AmpersandAttribute#STR} &ge 13
     *          or a {@link org.rattencorp.ampersand.core.attributes.AmpersandAttribute#DEX} &ge 13
     *
     * </pre>
     *
     */
    @Override
    public Predicate<AmpersandCharacter> prerequisiteSupplier() {
        return a -> {
            if(a.hasClass(this.getClass()) || a.getLevel() == 0){
                return true;
            } else{
                return a.getAttributeAssignment().getValueForAttribute(AmpersandAttribute.STR) >= 13
                        || a.getAttributeAssignment().getValueForAttribute(AmpersandAttribute.DEX) >= 13;
            }
        };
    }
}
