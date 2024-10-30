package org.rattencorp.ampersandlike.classes;

import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacter;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 * Classes and subclasses should implement this
 */
public interface AmpersandClass extends Serializable {


    /**
     * Return a predicate that checks whether the implementing class can be added to a character without issues
     *
     */
    Predicate<AmpersandCharacter> prerequisiteSupplier();

}
