package org.rattencorp.ampersandlike.classes;

import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacter;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 * AmpersandClasses and their subclasses should implement this<br>
 * AmpersandClasses are intended as stateless singleton tokens
 *
 */
public interface AmpersandClass extends Serializable {


    /**
     * Return a predicate that checks whether the implementing class can be added to a character without issues
     *
     */
    Predicate<AmpersandCharacter> prerequisiteSupplier();

}
