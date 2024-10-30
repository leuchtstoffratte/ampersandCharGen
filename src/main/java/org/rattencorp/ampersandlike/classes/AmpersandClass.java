package org.rattencorp.ampersandlike.classes;

import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacter;
import org.rattencorp.ampersand.core.attributes.AmpersandAttributeAssignmentImpl;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 * Classes and subclasses should implement this
 */
public interface AmpersandClass extends Serializable {

    Predicate<AmpersandCharacter> prerequisiteSupplier();

}
